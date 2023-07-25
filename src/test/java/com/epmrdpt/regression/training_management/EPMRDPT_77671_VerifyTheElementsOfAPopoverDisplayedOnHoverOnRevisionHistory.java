package com.epmrdpt.regression.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_CREATED_AT_ELEMENT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_CREATED_BY_ELEMENT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_LAST_MODIFIED_BY_ELEMENT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_GENERAL_INFO_TAB_SCREEN_MODIFIED_ELEMENT;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactGeneralInfoTabScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_77671_VerifyTheElementsOfAPopoverDisplayedOnHoverOnRevisionHistory",
    groups = {"full", "manager", "regression"})
public class EPMRDPT_77671_VerifyTheElementsOfAPopoverDisplayedOnHoverOnRevisionHistory {

  private final String trainingName = "AUTOTEST WITH AC";
  private final String createdAtElement = getValueOf(
      REACT_GENERAL_INFO_TAB_SCREEN_CREATED_AT_ELEMENT);
  private final String createdByElement = getValueOf(
      REACT_GENERAL_INFO_TAB_SCREEN_CREATED_BY_ELEMENT);
  private final String modifiedElement = getValueOf(REACT_GENERAL_INFO_TAB_SCREEN_MODIFIED_ELEMENT);
  private final String lastModifiedByElement = getValueOf(
      REACT_GENERAL_INFO_TAB_SCREEN_LAST_MODIFIED_BY_ELEMENT);
  private final String regexOfDate = "(\\d{2}).(\\d{2}).(\\d{4}) (\\d{2}):(\\d{2})";
  private final String regexOfFirstAndLastName = "^[A-z]+\\s[A-z]+$";
  private ReactGeneralInfoTabScreen reactGeneralInfoTabScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactGeneralInfoTabScreen = new ReactGeneralInfoTabScreen();
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asTrainingManager())
        .clickReactTrainingManagementLink();
    new ReactTrainingManagementService().searchTrainingByNameAndRedirectOnIt(trainingName);
  }

  @Test(priority = 1)
  public void checkIfRevisionHistoryPopoverDisplayed() {
    Assert.assertTrue(reactGeneralInfoTabScreen.isRevisionHistoryTextButtonDisplayed());
  }

  @Test(priority = 2)
  public void checkTheElementsOfPopoverRevisionHistory() {
    reactGeneralInfoTabScreen.mouseOverRevisionHistoryTextButton();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactGeneralInfoTabScreen.isElementDisplayedByName(createdAtElement),
        "Created at element is not displayed");
    softAssert.assertTrue(reactGeneralInfoTabScreen.getValueOfPopoverElementByName(createdAtElement)
        .matches(regexOfDate), "Date value of 'Created at' element is not displayed");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isElementDisplayedByName(createdByElement),
        "Created by element is not displayed");
    softAssert.assertTrue(reactGeneralInfoTabScreen.getValueOfPopoverElementByName(createdByElement)
            .matches(regexOfFirstAndLastName),
        "First and last name value of 'Created by' element is not displayed");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isElementDisplayedByName(modifiedElement),
        "Modified element is not displayed");
    softAssert.assertTrue(reactGeneralInfoTabScreen.getValueOfPopoverElementByName(modifiedElement)
        .matches(regexOfDate), "Date value of 'Modified' element is not displayed");
    softAssert.assertTrue(reactGeneralInfoTabScreen.isElementDisplayedByName(lastModifiedByElement),
        "Last modified by element is not displayed");
    softAssert.assertTrue(
        reactGeneralInfoTabScreen.getValueOfPopoverElementByName(lastModifiedByElement)
            .matches(regexOfFirstAndLastName),
        "First and last name value of 'Last modified by' element is not displayed");
  }
}
