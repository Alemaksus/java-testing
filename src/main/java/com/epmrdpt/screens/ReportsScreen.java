package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReportsScreen extends AbstractScreen {

  private Element reportsSubTab = Element.byXpath("//nav/a[@href='/Reports']");
  private Element reportPathDropDown = Element.byId("ReportPath");
  private Element countriesDropDown = Element.byId("Countries");
  private Element countriesDDLItem = Element.byXpath("//select[@id='Countries']/option");
  private Element citiesDDLItem = Element.byXpath("//select[@id='Cities']/option");
  private Element skillsDDLItem = Element.byXpath("//select[@id='Skills']/option");
  private Element startDateField = Element.byXpath("//input[@id='DateStart']");
  private Element endDateField = Element.byXpath("//input[@id='DateEnd']");
  private Element groupIdField = Element.byId("trainingId");
  private Element trainingIdField = Element.byId("planId");
  private Element downloadReportButton = Element.byXpath("//input[@type='submit']");
  private Element signOutButton = Element.byCss("div.UserDropDownMenu a[href$='LogoutExt']");

  @Override
  public boolean isScreenLoaded() {
    return reportsSubTab.isDisplayed();
  }

  public ReportsScreen waitScreenLoading() {
    reportsSubTab.waitForVisibility();
    return this;
  }

  public ReportsScreen selectReportTypeByName(String reportType) {
    reportPathDropDown.selectByValue(reportType);
    return this;
  }

  public ReportsScreen waitCountriesDropDownDisplayed() {
    countriesDropDown.waitForVisibility();
    return this;
  }

  public ReportsScreen selectCountryByIndex(int countryIndex) {
    countriesDDLItem.getElements().get(countryIndex).click();
    return this;
  }

  public ReportsScreen selectCityByIndex(int cityIndex) {
    citiesDDLItem.getElements().get(cityIndex).click();
    return this;
  }

  public ReportsScreen selectSkillByIndex(int skillIndex) {
    skillsDDLItem.getElements().get(skillIndex).click();
    return this;
  }

  public ReportsScreen fillStartDate(String startDate) {
    startDateField.type(startDate);
    return this;
  }

  public ReportsScreen fillEndDate(String endDate) {
    endDateField.type(endDate);
    return this;
  }

  public ReportsScreen waitGroupIdFieldDisplayed() {
    groupIdField.waitForVisibility();
    return this;
  }

  public ReportsScreen fillGroupIdField(String groupId) {
    groupIdField.type(groupId);
    return this;
  }

  public ReportsScreen waitTrainingIdFieldDisplayed() {
    trainingIdField.waitForVisibility();
    trainingIdField.waitForTextToBeNotPresent();
    return this;
  }

  public ReportsScreen fillTrainingIdField(String trainingId) {
    trainingIdField.type(trainingId);
    return this;
  }

  public ReportsScreen clickDownloadReportButton() {
    downloadReportButton.click();
    return this;
  }

  public HeaderScreen clickSignOutButton() {
    signOutButton.click();
    return new HeaderScreen();
  }
}
