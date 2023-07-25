package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class UserQuestionaryScreen extends AbstractScreen {

  private static final String FIND_SURVEY_ICON_BY_NAME = "//td[@title='%s']//following-sibling::td/div/i";

  private Element mainTitle = Element.byCss("(//a[@rel='noopener noreferrer'])[2]");
  private Element surveyColumn = Element.byXpath("//article[@class='applicationContainer']//th[3]");
  private Element generalInformationSection = Element
      .byXpath("//div[contains(@data-id,'user-info-table')]");
  private Element educationSection = Element
      .byXpath("//div[contains(@data-id,'user-education-table')]");
  private Element professionalSkillsSection = Element
      .byXpath("//div[contains(@data-id,'user-skills-table')]");
  private Element currentStatusSection = Element
      .byXpath("//div[contains(@data-id,'user-training-status-table')]");
  private Element studentGroupSection = Element
      .byXpath("//div[contains(@data-id,'student-groups-table')]");
  private Element applicationsSection = Element
      .byXpath("//div[contains(@data-id,'user-applications-table')]");
  private Element backButton = Element
      .byXpath("//*[contains(@class,'fa-arrow-left')]//ancestor::*[contains(@class,'uui-button')]");
  private Element profileButton = Element
      .byXpath("(//a[@rel='noopener noreferrer'])[2]");

  @Override
  public boolean isScreenLoaded() {
    return isMainTitleDisplayed();
  }

  public boolean isMainTitleDisplayed() {
    return mainTitle.isDisplayed();
  }

  public String getMainTitleText() {
    return profileButton.getText();
  }

  private Element findSurveyIconByTrainingName(String trainingName) {
    return Element.byXpath(FIND_SURVEY_ICON_BY_NAME, trainingName);
  }

  public boolean isTrainingHasSurveyIcon(String trainingName) {
    return findSurveyIconByTrainingName(trainingName).isDisplayed();
  }

  public boolean isSurveyColumnDisplayed() {
    return surveyColumn.isDisplayed();
  }

  public SurveyQuestionaryPopUpScreen clickSurveyIconByTrainingName(String trainingName) {
    findSurveyIconByTrainingName(trainingName).click();
    return new SurveyQuestionaryPopUpScreen();
  }

  public boolean isGeneralInformationSectionDisplayed() {
    return generalInformationSection.isDisplayed();
  }

  public boolean isEducationSectionDisplayed() {
    return educationSection.isDisplayed();
  }

  public boolean isProfessionalSkillsSectionDisplayed() {
    return professionalSkillsSection.isDisplayed();
  }

  public boolean isCurrentStatusSectionDisplayed() {
    return currentStatusSection.isDisplayed();
  }

  public boolean isStudentGroupSectionDisplayed() {
    return studentGroupSection.isDisplayed();
  }

  public boolean isApplicationsSectionDisplayed() {
    return applicationsSection.isDisplayed();
  }

  public ParticipantsTrainingScreen clickBackButtonInQuestionaryScreen() {
    backButton.click();
    return new ParticipantsTrainingScreen();
  }

  public ProfileScreen clickProfileButton() {
    profileButton.click();
    return new ProfileScreen();
  }
}
