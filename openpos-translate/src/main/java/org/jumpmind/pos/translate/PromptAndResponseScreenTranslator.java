package org.jumpmind.pos.translate;

import org.jumpmind.pos.core.model.IMaskSpec;
import org.jumpmind.pos.core.screen.DefaultScreen;
import org.jumpmind.pos.core.screen.MenuItem;
import org.jumpmind.pos.core.screen.PromptScreen;

public class PromptAndResponseScreenTranslator<T extends PromptScreen> extends AbstractPromptScreenTranslator<T> {

    private boolean addLocalMenuItems = false;
    
    
    public PromptAndResponseScreenTranslator(ILegacyScreen legacyScreen, Class<T> screenClass, boolean addLocalMenuItems) {
        this(legacyScreen, screenClass, addLocalMenuItems, null);
    }

    public PromptAndResponseScreenTranslator(ILegacyScreen legacyScreen, Class<T> screenClass, boolean addLocalMenuItems, IMaskSpec promptMask) {
        this(legacyScreen, screenClass, addLocalMenuItems, promptMask, null, null);
    }

    public PromptAndResponseScreenTranslator(ILegacyScreen legacyScreen, Class<T> screenClass, boolean addLocalMenuItems, IMaskSpec promptMask, Integer minLength, Integer maxLength) {
        super(legacyScreen, screenClass);
        getScreen().setTemplate(DefaultScreen.TEMPLATE_SELL);
        this.addLocalMenuItems = addLocalMenuItems;
        getScreen().setPromptMask(promptMask);
        getScreen().setMinLength(minLength);
        getScreen().setMaxLength(maxLength);
    }
    
    @Override
    protected void buildMainContent() {
        super.buildMainContent();
        screen.setRefreshAlways(true);
        this.configureScreenResponseField();
        if (addLocalMenuItems) {
            screen.setLocalMenuItems(generateUIActionsForLocalNavButtons(MenuItem.class, true));    
        }
        
    }

}
