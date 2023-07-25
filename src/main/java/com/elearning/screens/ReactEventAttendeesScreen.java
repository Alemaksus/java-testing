package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReactEventAttendeesScreen extends AbstractScreen {

  private static final String SURVEY_ICON_BY_NAME_PATTERN
          = "//div[text()='%s']/ancestor::div[@class='fullName']/../following-sibling::*/div/div/div//*[name()='svg']";
  private static final String SURVEY_POPUP_PATTERN = "//div[contains(@class, 'popover-container')]";
  private Element excelButton = Element.byXpath("//div[text()='Excel']");
  private Element fullNameColumnLabel = Element.byXpath("//div[@data-id='columnTitle']");
  private Element surveyColumnLabel = Element.byXpath("//div[@data-id='columnSurvey']");
  private Element emailColumnLabel = Element.byXpath("//div[@data-id='columnEmail']");
  private Element contactPhoneColumnLabel = Element.byXpath("//div[@data-id='columnContactPhone']");
  private Element locationColumnLabel = Element.byXpath("//div[@data-id='columnParticipantsLocation']");
  private Element statusColumnLabel = Element.byXpath("//div[@data-id='columnStatus']");
  private Element attendeeFullName = Element.byXpath("//div[@data-id='attendeeInfo']");
  private Element attendeesTable =  Element.byXpath("//div[contains(@class, 'uui-table-header-row')]/following-sibling::div");
  private final Element surveyPopUp = Element.byXpath(SURVEY_POPUP_PATTERN);
  private final Element surveyPopUpTitle = Element.byXpath(SURVEY_POPUP_PATTERN + "//following::div[@font-size][1]//div");
  private final Element surveyPopUpQuestion = Element.byXpath(SURVEY_POPUP_PATTERN + "//following::div[@font-size][2]//div");
  private final Element surveyPopUpAnswer = Element.byXpath(SURVEY_POPUP_PATTERN + "//following::div[@font-size][3]//div");
  private final Element statusPopOverText = Element.byXpath("//div[contains(@class, 'uui-tooltip-container')]//div[@class='uui-tooltip-body']");
  private final Element statusIcon = Element.byXpath("//div[contains(text(),'Registration cancelled')]/../../div[starts-with(@class, 'CMMN')][2]");

  @Override
  public boolean isScreenLoaded() {
    return isExcelButtonDisplayed();
  }

  public boolean isExcelButtonDisplayed() {
    return excelButton.isDisplayed();
  }

  public String getFullNameColumnText() {
    return fullNameColumnLabel.getText();
  }

  public String getSurveyColumnText() {
    return surveyColumnLabel.getText();
  }

  public String getEmailColumnText() {
    return emailColumnLabel.getText();
  }

  public String getContactPhoneColumnText() {
    return contactPhoneColumnLabel.getText();
  }

  public String getLocationColumnText() {
    return locationColumnLabel.getText();
  }

  public String getStatusColumnText() {
    return statusColumnLabel.getText();
  }

  public boolean isFirstAttendeeDisplayed() {
    return attendeeFullName.isDisplayed();
  }

  public ReactEventAttendeesScreen clickExcelButton() {
    excelButton.click();
    return this;
  }

  public ReactEventAttendeesScreen clickSurveyIconOfAttendeeByName(String attendeeName) {
    Element.byXpath(SURVEY_ICON_BY_NAME_PATTERN, attendeeName).click();
    return this;
  }

  public boolean isSurveyPopUpDisplayed() {
    return surveyPopUp.isDisplayed();
  }

  public String getSurveyPopUpTitleText() {
    return surveyPopUpTitle.getText();
  }

  public String getSurveyPopUpQuestionText() {
    return surveyPopUpQuestion.getText();
  }

  public boolean isSurveyPopUpAnswerDisplayed() {
    return surveyPopUpAnswer.isDisplayed();
  }

  public ReactEventAttendeesScreen waitAttendeesTableDisplayed() {
    attendeesTable.waitForVisibility();
    return this;
  }

  public String getStatusText(){
    statusIcon.mouseOver();
    return statusPopOverText.getText();
  }
}
