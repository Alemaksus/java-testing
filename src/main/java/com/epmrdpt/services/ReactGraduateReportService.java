package com.epmrdpt.services;

import com.epmrdpt.screens.ReactCommentPopUpScreen;
import com.epmrdpt.screens.ReactGraduateReportScreen;

public class ReactGraduateReportService {

  private ReactCommentPopUpScreen reactCommentPopUpScreen = new ReactCommentPopUpScreen();
  private ReactCommentPopUpService reactCommentPopUpService = new ReactCommentPopUpService();

  public ReactGraduateReportScreen moveScrollBarToTheEnd() {
    return new ReactGraduateReportScreen()
        .isFirstStudentsCommentIconButtonDisplayed()
        ? new ReactGraduateReportScreen()
        : new ReactGraduateReportScreen()
            .clickAfterScrollBar();
  }

  public ReactGraduateReportScreen addCommentToFirstStudent(String comment) {
    moveScrollBarToTheEnd()
        .clickFirstStudentsCommentIconButton();
    reactCommentPopUpScreen.typeComment(comment);
    return reactCommentPopUpService.closeCommentPopUpScreen();
  }

  public ReactGraduateReportScreen addCommentToFirstStudentAndMoveScrollBarToTheEnd(
      String comment) {
    addCommentToFirstStudent(comment);
    return moveScrollBarToTheEnd();
  }
}
