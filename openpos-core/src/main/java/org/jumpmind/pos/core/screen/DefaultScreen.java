package org.jumpmind.pos.core.screen;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DefaultScreen implements Serializable {

    public enum ScanType { CAMERA_CORDOVA, NONE }

    private static final long serialVersionUID = 1L;
    public static final String TITLE_OPEN_STATUS = "Open";
    public static final String TITLE_CLOSED_STATUS = "Closed";

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private String name;
    private String prompt;
    private ScreenType type;
    private String template = "Blank";    
    private MenuItem backButton;
    private Workstation workstation;
    private String operatorName;
    private MenuItem storeStatus;
    private MenuItem registerStatus;
    private String userDisplayName = "Jane Doe";
    private int sequenceNumber;
    private boolean refreshAlways = false;
    private String theme = "openpos-theme";
    private String icon;  
    private String instructions;
    
    private List<MenuItem> menuItems = new ArrayList<>();
    private List<MenuItem> localMenuItems = new ArrayList<>();
    
    private boolean showScan = false;
    private String scanActionName = "Scan";
    private ScanType scanType = ScanType.NONE;

    public DefaultScreen() {
    }

    public DefaultScreen(ScreenType type) {
        this(type, null);
    }
    
    public DefaultScreen(ScreenType type, String name) {
        if (name != null) {
            put("name", name);
        }
        this.type = type;
    }
    
    public DefaultScreen(String name) {
        put("name", name);
    }

    @JsonAnyGetter
    public Map<String, Object> any() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void put(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public boolean contains(String name) {
        return this.additionalProperties.containsKey(name);
    }

    public Object get(String name) {
        return additionalProperties.get(name);
    }
    
    public void setTemplate(String template) {
        this.template = template;
    }
    
    public String getTemplate() {
        return template;
    }

    public void clearAdditionalProperties() {
        this.additionalProperties.clear();
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = ScreenType.valueOf(type);
    }

    public void setType(ScreenType type) {
        this.type = type;
    }
    
    public String getType() {
        return this.type.name();
    }
    
    public void setScreenType(ScreenType type) {
        this.type = type;
    }
    
    public ScreenType getScreenType() {
        return this.type;
    }

    @SuppressWarnings("unchecked")
    public void addToGroup(String groupName, String dataName, Object value) {
        Object group = get(groupName);
        Map<String, Object> map = null;
        if (group == null || !(group instanceof Map)) {
            map = new HashMap<>();
            put(groupName, map);
        } else {
            map = (Map<String, Object>) group;
        }
        map.put(dataName, value);
    }

    @SuppressWarnings("unchecked")
    public void addToList(String dataName, Object value) {
        Object obj = get(dataName);
        List<Object> list = null;
        if (obj == null || !(obj instanceof List)) {
            list = new ArrayList<>();
            put(dataName, list);
        } else {
            list = (List<Object>) obj;
        }
        if (!list.contains(value)) {
            list.add(value);
        }
    }

    public void setBackButton(MenuItem backButton) {
        this.backButton = backButton;
    }

    public MenuItem getBackButton() {
        return backButton;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
    
    public void addMenuItem(MenuItem menuItem) {
        if (this.menuItems == null) {
            this.menuItems = new ArrayList<>();
        }
        this.menuItems.add(menuItem);
    }
    
    public void setMenuItems(List<MenuItem> menuItem) {
        this.menuItems = menuItem;
    }

    public MenuItem getMenuItemByAction(String action) {
        return this. menuItems.stream().filter( mi -> action.equalsIgnoreCase(mi.getAction())).findFirst().orElse(null);
    }
    
    public MenuItem getMenuItemByTitle(String title) {
        return this.menuItems.stream().filter( mi -> title.equalsIgnoreCase(mi.getTitle())).findFirst().orElse(null);
    }
    
    public void clearMenuItems() {
        this.menuItems.clear();
    }
    
    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Workstation getWorkstation() {
        return workstation;
    }

    public void setWorkstation(Workstation workstation) {
        this.workstation = workstation;
    }

    public MenuItem getStoreStatus() {
        return storeStatus;
    }

    public void setStoreStatus(MenuItem storeStatus) {
        this.storeStatus = storeStatus;
    }

    public MenuItem getRegisterStatus() {
        return registerStatus;
    }

    public void setRegisterStatus(MenuItem registerStatus) {
        this.registerStatus = registerStatus;
    }

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }
    
    public int getSequenceNumber() {
        return sequenceNumber;
    }
    
    public void setRefreshAlways(boolean refreshAlways) {
        this.refreshAlways = refreshAlways;
    }
    
    public boolean isRefreshAlways() {
        return refreshAlways;
    }

    public <T> T convertActionData(Object actionData, Class<T> convertToInstanceOf) {
        return new ObjectMapper().convertValue(actionData, convertToInstanceOf);
    }
    
    public String getTheme() {
        return theme;
    }
    
    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void addLocalMenuItem(MenuItem menuItem) {
        this.localMenuItems.add(menuItem);
    }
    
    public void setLocalMenuItems(List<MenuItem> localMenuItems) {
        this.localMenuItems = localMenuItems;
    }
    
    public List<MenuItem> getLocalMenuItems() {
        return localMenuItems;
    }

    public ScanType getScanType() {
        return scanType;
    }

    public void setScanType(ScanType scanType) {
        this.scanType = scanType;
    }

    public void setScanActionName(String scanActionName) {
        this.scanActionName = scanActionName;
    }

    public String getScanActionName() {
        return scanActionName;
    }
    
    public void setShowScan(boolean showScan) {
        this.showScan = showScan;
    }
    
    public boolean isShowScan() {
        return showScan;
    }
    
    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }
    
    public String getPrompt() {
        return prompt;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public String getIcon() {
        return icon;
    }
    
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    
    public String getInstructions() {
        return instructions;
    }
}
