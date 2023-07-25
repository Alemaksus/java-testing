package com.epmrdpt.services;

import com.epmrdpt.screens.EventPreviewScreen;

public class EventRegistrationService {

  public void cancelRegistrationToTheEvent(String reasonCancelRegistration) {
    new EventPreviewScreen()
        .clickCancelRegistrationButton()
        .typeReasonOfCancelRegistration(reasonCancelRegistration)
        .clickSubmitButton()
        .waitRegistrationButtonDisplayed();
  }
}
