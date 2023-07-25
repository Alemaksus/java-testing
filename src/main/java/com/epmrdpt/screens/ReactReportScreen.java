package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReactReportScreen extends AbstractScreen {

  private static final String DROP_DOWN_ELEMENT_CHECKBOX_PATTERN = "//div[@class='uui-popper']//div[contains(@class,'-clickable') and @tabindex and not(contains(@class,'uui-input'))]";
  private static final String DROP_DOWN_ARROW_PATTERN = "//div[@data-id and .//div[text()='%s']]//div[contains(@class,'uui-icon uui-enabled') and not(contains(@class,'cancel')) and not(contains(@class,'clickable'))]";
  private static final String REPORT_TYPE_DROP_DOWN_PATTERN = "//div[contains(@class, 'clickable') and .//div[text()='%s']]";
  private static final String DATE_FIELD_PATTERN = "//div[@id and not(contains(@id,'app')) and .//div[@class='uui-label' and text()='%s']]//input";

  private Element reportTypeDDL = Element.byXpath(String.format(DROP_DOWN_ARROW_PATTERN, "Report type"));
  private Element countriesDDL = Element.byXpath(String.format(DROP_DOWN_ARROW_PATTERN, "Countries"));
  private Element citiesDDL = Element.byXpath(String.format(DROP_DOWN_ARROW_PATTERN, "Cities"));
  private Element skillsDDL = Element.byXpath(String.format(DROP_DOWN_ARROW_PATTERN, "Skills"));
  private Element idField = Element.byXpath("//div[contains(@class, 'uui-input-box')]/input");
  private Element downloadReportButton = Element
      .byXpath("//div[contains(@class,'uui-button-box')]/div[text()='Download report']");
  private Element startDateField = Element.byXpath(String.format(DATE_FIELD_PATTERN, "Start date"));
  private Element endDateField = Element.byXpath(String.format(DATE_FIELD_PATTERN, "End date"));

  @Override
  public boolean isScreenLoaded() {
    return reportTypeDDL.isDisplayed();
  }

  public ReactReportScreen waitScreenLoading() {
    reportTypeDDL.waitForVisibility();
    return this;
  }

  public ReactReportScreen selectReportTypeByName(String reportName) {
    String reportTypeLocator = String.format(REPORT_TYPE_DROP_DOWN_PATTERN, reportName);
    reportTypeDDL.waitForClickableAndClick();
    Element.byXpath(reportTypeLocator).click();
    return this;
  }

  public ReactReportScreen selectCountryByIndex(int countryIndex) {
    countriesDDL.click();
    selectDropDownCheckboxValueByIndex(countryIndex);
    countriesDDL.click();
    return this;
  }

  public ReactReportScreen selectCityByIndex(int cityIndex) {
    citiesDDL.waitForClickableAndClick();
    selectDropDownCheckboxValueByIndex(cityIndex);
    citiesDDL.click();
    return this;
  }

  public ReactReportScreen enterId(String groupId) {
    idField.type(groupId);
    return this;
  }

  public ReactReportScreen clickDownloadReportButton() {
    downloadReportButton.click();
    return this;
  }

  public ReactReportScreen selectSkillByIndex(int skillIndex) {
    skillsDDL.click();
    selectDropDownCheckboxValueByIndex(skillIndex);
    skillsDDL.click();
    return this;
  }

  public ReactReportScreen fillStartDate(String startDate) {
    startDateField.type(startDate);
    return this;
  }

  public ReactReportScreen fillEndDate(String endDate) {
    endDateField.waitForClickable();
    endDateField.type(endDate);
    return this;
  }

  private ReactReportScreen selectDropDownCheckboxValueByIndex(int valueIndex) {
    Element.byXpath(DROP_DOWN_ELEMENT_CHECKBOX_PATTERN).getElements().get(valueIndex)
        .waitForVisibility().click();
    return this;
  }
}
