package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ConfirmDeleteNotificationPopUpScreen extends AbstractScreen {

    private final Element popUpOkButton = Element.byXpath("//button[contains(@class, 'message-btn-ok')]");

    @Override
    public boolean isScreenLoaded() {
        return popUpOkButton.isDisplayed();
    }

    public NotificationScreen clickOkButton() {
        popUpOkButton.clickJs();
        popUpOkButton.waitForInvisibility();
        return new NotificationScreen();
    }
}
