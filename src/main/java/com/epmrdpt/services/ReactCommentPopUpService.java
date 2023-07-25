package com.epmrdpt.services;

import com.epmrdpt.screens.ReactCommentPopUpScreen;
import com.epmrdpt.screens.ReactGraduateReportScreen;

public class ReactCommentPopUpService {

  private ReactCommentPopUpScreen reactCommentPopUpScreen = new ReactCommentPopUpScreen();

  public ReactGraduateReportScreen closeCommentPopUpScreen() {
    reactCommentPopUpScreen.clickSavePopUpButton();
    reactCommentPopUpScreen.waitForCommentPopUpScreenInvisibilityShortTimeout();
    return new ReactGraduateReportScreen();
  }

  public ReactGraduateReportScreen deleteAllCommitsInCommentPopUpScreen() {
    int limiter = reactCommentPopUpScreen.getNumberComments() * 2;
    int counter = 0;
    do {
      reactCommentPopUpScreen =
          new ReactCommentPopUpScreen().isDeleteSureButtonDisplayed()
              ? new ReactCommentPopUpScreen().clickDeleteSureButton()
              : new ReactCommentPopUpScreen().clickDeleteBinButton();
      counter++;
    } while (reactCommentPopUpScreen.isDeleteBinButtonDisplayed() && counter < limiter);
    return new ReactGraduateReportScreen();
  }
}
