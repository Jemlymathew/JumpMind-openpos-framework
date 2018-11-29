package org.jumpmind.pos.devices.javapos.proxy;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.jumpmind.pos.devices.service.print.Barcode;
import org.jumpmind.pos.devices.service.print.BeginInsert;
import org.jumpmind.pos.devices.service.print.BeginRemoval;
import org.jumpmind.pos.devices.service.print.CutPaper;
import org.jumpmind.pos.devices.service.print.EndInsert;
import org.jumpmind.pos.devices.service.print.EndRemoval;
import org.jumpmind.pos.devices.service.print.FileImage;
import org.jumpmind.pos.devices.service.print.Line;
import org.jumpmind.pos.devices.service.print.MemoryImage;
import org.jumpmind.pos.devices.service.print.POSPrinterSettings;
import org.jumpmind.pos.devices.service.print.PrintRequest;
import org.jumpmind.pos.devices.service.print.PrintableDocument;
import org.jumpmind.pos.devices.service.print.PrinterSettingsRequest;
import org.jumpmind.pos.devices.service.print.PrinterSettingsResult;
import org.jumpmind.pos.devices.service.print.RuleLine;
import org.jumpmind.pos.service.ServiceResult;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import jpos.JposException;
import jpos.POSPrinterConst;
import jpos.services.POSPrinterService114;

public class ProxyPOSPrinterService extends AbstractBaseService implements POSPrinterService114 {

    static final Log logger = LogFactory.getLog(ProxyPOSPrinterService.class);

    protected Logger printerLogger = Logger.getLogger(ProxyPOSPrinterService.class);

    protected POSPrinterSettings settings = null;

    protected Map<Integer, PrintableDocument> workingDocuments = new HashMap<>();

    public ProxyPOSPrinterService() {
        logger.trace(String.format("%s created", this.getClass()));
    }

    @Override
    public void reset() {
        workingDocuments.clear();
    }

    protected PrintableDocument getPrintableDocument(int station) {
        PrintableDocument doc = workingDocuments.get(station);
        if (doc == null) {
            doc = new PrintableDocument(station);
            workingDocuments.put(station, doc);
        }
        return doc;
    }

    protected void refreshSettings() throws JposException {
        if (settings == null || settings.getSettingsCapturedTime().getTime() - System.currentTimeMillis() > 10000) {
           this.settings = sendSettings(null);
        }
    }

    protected void sendSettings() throws JposException {
        this.settings = sendSettings(settings);
    }
    
    protected POSPrinterSettings sendSettings(POSPrinterSettings settings) throws JposException {
        RestTemplate restTemplate = getRestTemplate();

        PrinterSettingsRequest req = new PrinterSettingsRequest(profile, deviceName, settings);
        HttpEntity<PrinterSettingsRequest> requestEntity = new HttpEntity<PrinterSettingsRequest>(req);
        HttpEntity<PrinterSettingsResult> response = restTemplate.exchange(getBaseHttpUrl() + "/print", HttpMethod.PUT, requestEntity,
                PrinterSettingsResult.class);

        PrinterSettingsResult result = response.getBody();
        processServiceResult(result);
        return result.getSettings();
    }

    public void printBarCode(int station, String data, int symbology, int height, int width, int alignment, int textPosition)
            throws JposException {
        getPrintableDocument(station).addElement(new Barcode(data, symbology, height, width, alignment, textPosition));
    }

    public void printBitmap(int station, String fileName, int width, int alignment) throws JposException {
        getPrintableDocument(station).addElement(new FileImage(fileName, width, alignment));
    }

    public void printImmediate(int station, String data) throws JposException {
        getPrintableDocument(station).addElement(new Line(data));
        print(station);
    }

    public void printNormal(int station, String data) throws JposException {
        getPrintableDocument(station).addElement(new Line(data));
    }

    public void printMemoryBitmap(int station, byte[] data, int type, int width, int alignment) throws JposException {
        getPrintableDocument(station).addElement(new MemoryImage(data, type, width, alignment));
    }

    @Override
    public void drawRuledLine(int station, String positionList, int lineDirection, int lineWidth, int lineStyle, int lineColor)
            throws JposException {
        getPrintableDocument(station).addElement(new RuleLine(positionList, lineDirection, lineWidth, lineStyle, lineColor));
    }

    public void transactionPrint(int station, int control) throws JposException {
        // used with async print
    }

    public void cutPaper(int percentage) throws JposException {
        PrintableDocument doc = getPrintableDocument(POSPrinterConst.PTR_S_RECEIPT);
        doc.addElement(new CutPaper());
        print(POSPrinterConst.PTR_S_RECEIPT);
    }

    protected void print(int station) throws JposException {
        PrintableDocument doc = workingDocuments.remove(station);
        if (doc != null) {
            RestTemplate restTemplate = getRestTemplate();

            PrintRequest req = new PrintRequest(profile, deviceName, doc);
            HttpEntity<PrintRequest> requestEntity = new HttpEntity<PrintRequest>(req);
            HttpEntity<ServiceResult> response = restTemplate.exchange(getBaseHttpUrl() + "/print", HttpMethod.PUT, requestEntity,
                    ServiceResult.class);

            ServiceResult result = response.getBody();
            processServiceResult(result);
        }
    }

    public void clearPrintArea() throws JposException {
        this.reset();
    }

    public void beginInsertion(int timeout) throws JposException {
        getPrintableDocument(POSPrinterConst.PTR_S_SLIP).addElement(new BeginInsert(timeout));
        print(POSPrinterConst.PTR_S_SLIP);
    }

    public void beginRemoval(int timeout) throws JposException {
        getPrintableDocument(POSPrinterConst.PTR_S_SLIP).addElement(new BeginRemoval(timeout));
        print(POSPrinterConst.PTR_S_SLIP);
    }

    public void clearOutput() throws JposException {
        this.reset();
    }

    public void endInsertion() throws JposException {
        getPrintableDocument(POSPrinterConst.PTR_S_SLIP).addElement(new EndInsert());
        print(POSPrinterConst.PTR_S_SLIP);
    }

    public void endRemoval() throws JposException {
        getPrintableDocument(POSPrinterConst.PTR_S_SLIP).addElement(new EndRemoval());
        print(POSPrinterConst.PTR_S_SLIP);
    }

    public String getCharacterSetList() throws JposException {
        // TODO call get printer status and the return character set list
        return "";
    }

    public void printTwoNormal(int stations, String data1, String data2) throws JposException {
    }

    public void setLogo(int i, String s) throws JposException {

    }

    public void compareFirmwareVersion(String s, int[] ai) throws JposException {
    }

    public boolean getCapCompareFirmwareVersion() throws JposException {
        return false;
    }

    public boolean getCapConcurrentPageMode() throws JposException {
        return false;
    }

    public boolean getCapRecPageMode() throws JposException {
        return false;
    }

    public boolean getCapSlpPageMode() throws JposException {
        return false;
    }

    public boolean getCapUpdateFirmware() throws JposException {
        return false;
    }

    public String getPageModeArea() throws JposException {
        return null;
    }

    public int getPageModeDescriptor() throws JposException {
        return 0;
    }

    public int getPageModeHorizontalPosition() throws JposException {
        return 0;
    }

    public String getPageModePrintArea() throws JposException {
        return null;
    }

    public int getPageModePrintDirection() throws JposException {
        return 0;
    }

    public int getPageModeStation() throws JposException {
        return 0;
    }

    public int getPageModeVerticalPosition() throws JposException {
        return 0;
    }

    public void pageModePrint(int i) throws JposException {
    }

    public void setPageModeHorizontalPosition(int i) throws JposException {
    }

    public void setPageModePrintArea(String s) throws JposException {
    }

    public void setPageModePrintDirection(int i) throws JposException {
    }

    public void setPageModeStation(int i) throws JposException {
    }

    public void setPageModeVerticalPosition(int i) throws JposException {
    }

    public void updateFirmware(String s) throws JposException {
    }

    public boolean getCapStatisticsReporting() throws JposException {
        return false;
    }

    public boolean getCapUpdateStatistics() throws JposException {
        return false;
    }

    public void resetStatistics(String s) throws JposException {
    }

    public void retrieveStatistics(String[] as) throws JposException {
    }

    public void updateStatistics(String s) throws JposException {
    }

    public boolean getCapMapCharacterSet() throws JposException {
        return false;
    }

    public boolean getMapCharacterSet() throws JposException {
        return false;
    }

    public String getRecBitmapRotationList() throws JposException {
        return null;
    }

    public String getSlpBitmapRotationList() throws JposException {
        return null;
    }

    public void setMapCharacterSet(boolean flag) throws JposException {
    }

    public void changePrintSide(int i) throws JposException {
    }

    public int getCapJrnCartridgeSensor() throws JposException {
        return 0;
    }

    public int getCapJrnColor() throws JposException {
        return 0;
    }

    public int getCapRecCartridgeSensor() throws JposException {
        return 0;
    }

    public int getCapRecColor() throws JposException {
        return 0;
    }

    public int getCapRecMarkFeed() throws JposException {
        return 0;
    }

    public boolean getCapSlpBothSidesPrint() throws JposException {
        return false;
    }

    public int getCapSlpCartridgeSensor() throws JposException {
        return 0;
    }

    public int getCapSlpColor() throws JposException {
        return 0;
    }

    public int getCartridgeNotify() throws JposException {
        return 0;
    }

    public int getJrnCartridgeState() throws JposException {
        return 0;
    }

    public int getJrnCurrentCartridge() throws JposException {
        return 0;
    }

    public int getRecCartridgeState() throws JposException {
        return 0;
    }

    public int getRecCurrentCartridge() throws JposException {
        return 0;
    }

    public int getSlpCartridgeState() throws JposException {
        return 0;
    }

    public int getSlpCurrentCartridge() throws JposException {
        return 0;
    }

    public int getSlpPrintSide() throws JposException {
        return 0;
    }

    public void markFeed(int i) throws JposException {
    }

    public void setCartridgeNotify(int i) throws JposException {
    }

    public void setJrnCurrentCartridge(int i) throws JposException {
    }

    public void setRecCurrentCartridge(int i) throws JposException {
    }

    public void setSlpCurrentCartridge(int i) throws JposException {
    }

    public int getCapPowerReporting() throws JposException {
        return 0;
    }

    public int getPowerState() throws JposException {

        return 0;
    }

    public boolean getAsyncMode() throws JposException {
        refreshSettings();
        return this.settings.isAsyncMode();
    }

    public int getCapCharacterSet() throws JposException {
        return 0;
    }

    public boolean getCapConcurrentJrnRec() throws JposException {
        return false;
    }

    public boolean getCapConcurrentJrnSlp() throws JposException {
        return false;
    }

    public boolean getCapConcurrentRecSlp() throws JposException {
        return false;
    }

    public boolean getCapCoverSensor() throws JposException {
        return false;
    }

    public boolean getCapJrn2Color() throws JposException {
        return false;
    }

    public boolean getCapJrnBold() throws JposException {
        return false;
    }

    public boolean getCapJrnDhigh() throws JposException {
        return false;
    }

    public boolean getCapJrnDwide() throws JposException {
        return false;
    }

    public boolean getCapJrnDwideDhigh() throws JposException {
        return false;
    }

    public boolean getCapJrnEmptySensor() throws JposException {
        return false;
    }

    public boolean getCapJrnItalic() throws JposException {
        return false;
    }

    public boolean getCapJrnNearEndSensor() throws JposException {
        return false;
    }

    public boolean getCapJrnPresent() throws JposException {
        return false;
    }

    public boolean getCapJrnUnderline() throws JposException {
        return false;
    }

    public boolean getCapRec2Color() throws JposException {
        return false;
    }

    public boolean getCapRecBarCode() throws JposException {
        return false;
    }

    public boolean getCapRecBitmap() throws JposException {
        return false;
    }

    public boolean getCapRecBold() throws JposException {
        return false;
    }

    public boolean getCapRecDhigh() throws JposException {
        return false;
    }

    public boolean getCapRecDwide() throws JposException {
        return false;
    }

    public boolean getCapRecDwideDhigh() throws JposException {
        return false;
    }

    public boolean getCapRecEmptySensor() throws JposException {

        return false;
    }

    public boolean getCapRecItalic() throws JposException {

        return false;
    }

    public boolean getCapRecLeft90() throws JposException {

        return false;
    }

    public boolean getCapRecNearEndSensor() throws JposException {

        return false;
    }

    public boolean getCapRecPapercut() throws JposException {

        return false;
    }

    public boolean getCapRecPresent() throws JposException {

        return false;
    }

    public boolean getCapRecRight90() throws JposException {

        return false;
    }

    public boolean getCapRecRotate180() throws JposException {

        return false;
    }

    public boolean getCapRecStamp() throws JposException {

        return false;
    }

    public boolean getCapRecUnderline() throws JposException {

        return false;
    }

    public boolean getCapSlp2Color() throws JposException {

        return false;
    }

    public boolean getCapSlpBarCode() throws JposException {

        return false;
    }

    public boolean getCapSlpBitmap() throws JposException {

        return false;
    }

    public boolean getCapSlpBold() throws JposException {

        return false;
    }

    public boolean getCapSlpDhigh() throws JposException {

        return false;
    }

    public boolean getCapSlpDwide() throws JposException {

        return false;
    }

    public boolean getCapSlpDwideDhigh() throws JposException {

        return false;
    }

    public boolean getCapSlpEmptySensor() throws JposException {

        return false;
    }

    public boolean getCapSlpFullslip() throws JposException {

        return false;
    }

    public boolean getCapSlpItalic() throws JposException {

        return false;
    }

    public boolean getCapSlpLeft90() throws JposException {

        return false;
    }

    public boolean getCapSlpNearEndSensor() throws JposException {

        return false;
    }

    public boolean getCapSlpPresent() throws JposException {

        return false;
    }

    public boolean getCapSlpRight90() throws JposException {

        return false;
    }

    public boolean getCapSlpRotate180() throws JposException {

        return false;
    }

    public boolean getCapSlpUnderline() throws JposException {

        return false;
    }

    public boolean getCapTransaction() throws JposException {

        return false;
    }

    public int getCharacterSet() throws JposException {
        refreshSettings();
        return this.settings.getCharacterSet();
    }

    public boolean getCoverOpen() throws JposException {
        refreshSettings();
        return this.settings.isCoverOpen();
    }

    public int getErrorLevel() throws JposException {

        return 0;
    }

    public int getErrorStation() throws JposException {

        return 0;
    }

    public String getErrorString() throws JposException {

        return null;
    }

    public boolean getFlagWhenIdle() throws JposException {
        refreshSettings();
        return settings.isFlagWhenIdle();
    }

    public String getFontTypefaceList() throws JposException {

        return null;
    }

    public boolean getJrnEmpty() throws JposException {

        return false;
    }

    public boolean getJrnLetterQuality() throws JposException {

        return false;
    }

    public int getJrnLineChars() throws JposException {

        return 0;
    }

    public String getJrnLineCharsList() throws JposException {

        return null;
    }

    public int getJrnLineHeight() throws JposException {

        return 0;
    }

    public int getJrnLineSpacing() throws JposException {

        return 0;
    }

    public int getJrnLineWidth() throws JposException {

        return 0;
    }

    public boolean getJrnNearEnd() throws JposException {

        return false;
    }

    public int getMapMode() throws JposException {

        return 0;
    }

    public int getOutputID() throws JposException {

        return 0;
    }

    public String getRecBarCodeRotationList() throws JposException {

        return null;
    }

    public boolean getRecEmpty() throws JposException {

        return false;
    }

    public boolean getRecLetterQuality() throws JposException {

        return false;
    }

    public int getRecLineChars() throws JposException {

        return 44;
    }

    public String getRecLineCharsList() throws JposException {

        return null;
    }

    public int getRecLineHeight() throws JposException {

        return 0;
    }

    public int getRecLineSpacing() throws JposException {

        return 0;
    }

    public int getRecLineWidth() throws JposException {

        return 0;
    }

    public int getRecLinesToPaperCut() throws JposException {

        return 0;
    }

    public boolean getRecNearEnd() throws JposException {

        return false;
    }

    public int getRecSidewaysMaxChars() throws JposException {

        return 0;
    }

    public int getRecSidewaysMaxLines() throws JposException {

        return 0;
    }

    public int getRotateSpecial() throws JposException {

        return 0;
    }

    public String getSlpBarCodeRotationList() throws JposException {

        return null;
    }

    public boolean getSlpEmpty() throws JposException {

        return false;
    }

    public boolean getSlpLetterQuality() throws JposException {
        refreshSettings();
        return settings.isSlpLetterQuality();
    }

    public int getSlpLineChars() throws JposException {
        refreshSettings();
        return settings.getSlpLineChars();
    }

    public String getSlpLineCharsList() throws JposException {
        return null;
    }

    public int getSlpLineHeight() throws JposException {
        refreshSettings();
        return settings.getSlpLineHeight();
    }

    public int getSlpLineSpacing() throws JposException {
        refreshSettings();
        return settings.getSlpLineSpacing();
    }

    public int getSlpLineWidth() throws JposException {

        return 0;
    }

    public int getSlpLinesNearEndToEnd() throws JposException {

        return 0;
    }

    public int getSlpMaxLines() throws JposException {

        return 0;
    }

    public boolean getSlpNearEnd() throws JposException {

        return false;
    }

    public int getSlpSidewaysMaxChars() throws JposException {

        return 0;
    }

    public int getSlpSidewaysMaxLines() throws JposException {

        return 0;
    }

    public void rotatePrint(int i, int j) throws JposException {

    }

    public void setAsyncMode(boolean flag) throws JposException {
        this.settings.setAsyncMode(flag);
        sendSettings();
    }

    public void setBitmap(int i, int j, String s, int k, int l) throws JposException {
    }

    public void setCharacterSet(int i) throws JposException {
        this.settings.setCharacterSet(i);
        sendSettings();
    }

    public void setFlagWhenIdle(boolean flag) throws JposException {
        this.settings.setFlagWhenIdle(flag);
        sendSettings();
    }

    public void setJrnLetterQuality(boolean flag) throws JposException {
        this.settings.setJrnLetterQuality(flag);
        sendSettings();
    }

    public void setJrnLineChars(int i) throws JposException {
        this.settings.setJrnLineChars(i);
        sendSettings();
    }

    public void setJrnLineHeight(int i) throws JposException {
        this.settings.setJrnLineHeight(i);
        sendSettings();
    }

    public void setJrnLineSpacing(int i) throws JposException {
        this.settings.setJrnLineSpacing(i);
        sendSettings();
    }

    public void setMapMode(int i) throws JposException {
        this.settings.setMapMode(i);
        sendSettings();
    }

    public void setRecLetterQuality(boolean flag) throws JposException {
        this.settings.setRecLetterQuality(flag);
        sendSettings();
    }

    public void setRecLineChars(int i) throws JposException {
        this.settings.setRecLineChars(i);
        sendSettings();
    }

    public void setRecLineHeight(int i) throws JposException {
        this.settings.setRecLineHeight(i);
        sendSettings();
    }

    public void setRecLineSpacing(int i) throws JposException {
        this.settings.setRecLineSpacing(i);
        sendSettings();
    }

    public void setRotateSpecial(int i) throws JposException {
        this.settings.setRotateSpecial(i);
        sendSettings();
    }

    public void setSlpLetterQuality(boolean flag) throws JposException {
        this.settings.setSlpLetterQuality(flag);
        sendSettings();
    }

    public void setSlpLineChars(int i) throws JposException {
        this.settings.setSlpLineChars(i);
        sendSettings();
    }

    public void setSlpLineHeight(int i) throws JposException {
        this.settings.setSlpLineHeight(i);
        sendSettings();
    }

    public void setSlpLineSpacing(int i) throws JposException {
        this.settings.setSlpLineSpacing(i);
        sendSettings();
    }

    public void validateData(int i, String s) throws JposException {

    }

    public void checkHealth(int i) throws JposException {

    }

    public void directIO(int i, int[] ai, Object obj) throws JposException {

    }

    public String getCheckHealthText() throws JposException {
        return null;
    }

    public String getDeviceServiceDescription() throws JposException {
        return null;
    }

    public String getPhysicalDeviceDescription() throws JposException {
        return null;
    }

    public String getPhysicalDeviceName() throws JposException {
        return null;
    }

    @Override
    public void deleteInstance() throws JposException {
    }

    public boolean getCapServiceAllowManagement() throws JposException {
        return false;
    }

    @Override
    public int getCapRecRuledLine() throws JposException {
        return 0;
    }

    @Override
    public int getCapSlpRuledLine() throws JposException {
        return 0;
    }

}
