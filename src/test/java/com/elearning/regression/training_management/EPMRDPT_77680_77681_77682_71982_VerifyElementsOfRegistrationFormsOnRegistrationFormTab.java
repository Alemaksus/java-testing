package com.epmrdpt.regression.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.APPLICATIONS_DEPARTMENT_AFFILIATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_CUSTOM_FORM_OPTION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_DEPARTMENT_AFFILIATE_FORM_ELEMENTS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_DEPARTMENT_AFFILIATE_FORM_OPTION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_EDUCATION_LABEL_ELEMENTS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_GENERAL_INFO_LABEL_ELEMENTS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_SIMPLIFIED_FORM_ELEMENTS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_SIMPLIFIED_FORM_OPTION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_STANDARD_FORM_OPTION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_REGISTRATION_FORM_TAB_SCREEN_WORKS_EXPERIENCE_AND_SKILLS_LABEL_ELEMENTS;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactCreateTrainingScreen;
import com.epmrdpt.screens.ReactRegistrationFormTabScreen;
import com.epmrdpt.services.LoginService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_77680_77681_77682_71982_VerifyElementsOfRegistrationFormsOnRegistrationFormTab",
    groups = {"full", "regression"})
public class EPMRDPT_77680_77681_77682_71982_VerifyElementsOfRegistrationFormsOnRegistrationFormTab {

  private final String generalLabelElements = getValueOf(
      REACT_REGISTRATION_FORM_TAB_SCREEN_GENERAL_INFO_LABEL_ELEMENTS);
  private final String educationLabelElements = getValueOf(
      REACT_REGISTRATION_FORM_TAB_SCREEN_EDUCATION_LABEL_ELEMENTS);
  private final String workLabelElements = getValueOf(
      REACT_REGISTRATION_FORM_TAB_SCREEN_WORKS_EXPERIENCE_AND_SKILLS_LABEL_ELEMENTS);
  private ReactRegistrationFormTabScreen reactRegistrationFormTab;
  private ReactCreateTrainingScreen reactCreateTrainingScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactRegistrationFormTab = new ReactRegistrationFormTabScreen();
    reactCreateTrainingScreen = new ReactCreateTrainingScreen();
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asTrainingManager())
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickReactTrainingManagementLink()
        .waitTrainingScreenIsLoaded()
        .clickCreateNewButton()
        .clickRegistrationFormTabFromTrainingScreen()
        .waitScreenLoading();
  }

  @Test(priority = 1)
  public void checkTheElementsOfSimplifiedRegistrationForm() {
    reactRegistrationFormTab.clickRegistrationFormDDL()
        .selectFormByName(getValueOf(REACT_REGISTRATION_FORM_TAB_SCREEN_SIMPLIFIED_FORM_OPTION));
    String simplifiedFormElements = getValueOf(
        REACT_REGISTRATION_FORM_TAB_SCREEN_SIMPLIFIED_FORM_ELEMENTS);
    List<String> expectedSimplifiedFormElements = getExpectedLabelElements(simplifiedFormElements);
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactRegistrationFormTab.isFormDisplayedByName(
            getValueOf(REACT_REGISTRATION_FORM_TAB_SCREEN_SIMPLIFIED_FORM_OPTION)),
        "Simplified form is not displayed");
    softAssert.assertTrue(reactRegistrationFormTab.isGeneralInfoLabelDisplayed(),
        "General Info label is not displayed");
    for (String element : expectedSimplifiedFormElements) {
      softAssert.assertTrue(reactRegistrationFormTab.isElementDisplayedByName(element),
          element + " is not presented on Simplified form");
    }
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkTheElementsOfCustomRegistrationForm() {
    reactRegistrationFormTab.clickRegistrationFormDDL()
        .selectFormByName(getValueOf(REACT_REGISTRATION_FORM_TAB_SCREEN_CUSTOM_FORM_OPTION));
    List<String> customFormElements = concatenate(getExpectedLabelElements(generalLabelElements),
        getExpectedLabelElements(educationLabelElements),
        getExpectedLabelElements(workLabelElements));
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactRegistrationFormTab.isFormDisplayedByName(
            getValueOf(REACT_REGISTRATION_FORM_TAB_SCREEN_CUSTOM_FORM_OPTION)),
        "Custom form is not displayed");
    softAssert.assertTrue(reactRegistrationFormTab.isGeneralInfoLabelDisplayed(),
        "General Info label is not displayed");
    softAssert.assertTrue(reactRegistrationFormTab.isEducationLabelDisplayed(),
        "Education label is not displayed");
    softAssert.assertTrue(reactRegistrationFormTab.isWorksExperienceAndSkillsLabelDisplayed(),
        "Works experience and skills label is not displayed");
    for (String element : customFormElements) {
      softAssert.assertTrue(reactRegistrationFormTab.isElementDisplayedByName(element),
          element + " is not presented on Custom form");
    }
    softAssert.assertAll();
  }

  @Test(priority = 3)
  public void checkTheElementsOfStandardRegistrationForm() {
    reactRegistrationFormTab.clickRegistrationFormDDL()
        .selectFormByName(getValueOf(REACT_REGISTRATION_FORM_TAB_SCREEN_STANDARD_FORM_OPTION));
    List<String> standardFormElements = concatenate(getExpectedLabelElements(generalLabelElements),
        getExpectedLabelElements(educationLabelElements),
        getExpectedLabelElements(workLabelElements));
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactRegistrationFormTab.isFormDisplayedByName(
            getValueOf(REACT_REGISTRATION_FORM_TAB_SCREEN_STANDARD_FORM_OPTION)),
        "Standard form is not displayed");
    softAssert.assertTrue(reactRegistrationFormTab.isGeneralInfoLabelDisplayed(),
        "General Info label is not displayed");
    softAssert.assertTrue(reactRegistrationFormTab.isEducationLabelDisplayed(),
        "Education label is not displayed");
    softAssert.assertTrue(reactRegistrationFormTab.isWorksExperienceAndSkillsLabelDisplayed(),
        "Works experience and skills label is not displayed");
    for (String element : standardFormElements) {
      softAssert.assertTrue(reactRegistrationFormTab.isElementDisplayedByName(element),
          element + " is not presented on Standard form");
    }
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkTheElementsOfDepartmentAffiliateRegistrationForm() {
    reactCreateTrainingScreen.clickGeneralInfoTabFromTrainingScreen()
        .clickTrainingTypeDropDown()
        .selectTrainingTypeByName(getValueOf(APPLICATIONS_DEPARTMENT_AFFILIATE));
    reactRegistrationFormTab = reactCreateTrainingScreen.clickRegistrationFormTabFromTrainingScreen();
    String departmentAffiliateFormElements = getValueOf(
        REACT_REGISTRATION_FORM_TAB_SCREEN_DEPARTMENT_AFFILIATE_FORM_ELEMENTS);
    List<String> expectedDepartmentAffiliateFormElements = getExpectedLabelElements(
        departmentAffiliateFormElements);
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactRegistrationFormTab.isFormDisplayedByName(
            getValueOf(REACT_REGISTRATION_FORM_TAB_SCREEN_DEPARTMENT_AFFILIATE_FORM_OPTION)),
        "Department affiliate form is not displayed");
    softAssert.assertTrue(reactRegistrationFormTab.isGeneralInfoLabelDisplayed(),
        "General Info label is not displayed");
    softAssert.assertTrue((reactRegistrationFormTab.isEducationLabelDisplayed()),
        "Education label is not displayed");
    for (String element : expectedDepartmentAffiliateFormElements) {
      softAssert.assertTrue(reactRegistrationFormTab.isElementDisplayedByName(element),
          element + " is not presented on Department Affiliate form");
    }
    softAssert.assertAll();
  }

  private List<String> getExpectedLabelElements(String labelElements) {
    return Arrays.stream(labelElements.split(";")).collect(Collectors.toList());
  }

  private static <T> List<T> concatenate(List<T>... lists) {
    return Stream.of(lists)
        .flatMap(x -> x.stream())
        .collect(Collectors.toList());
  }
}
