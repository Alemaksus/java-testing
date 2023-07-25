package com.epmrdpt.regression.profile;

import static com.epmrdpt.utils.StringUtils.getStringValueByRegex;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.HeaderScreen;
import com.epmrdpt.screens.ProfileEditionScreen;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.services.LoginService;
import java.lang.reflect.Method;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_13164_VerifyThatItIsPossibleToSetCompanyNameAsTheUsersPostgraduateWorkplace",
    groups = {"regression", "full"})
public class EPMRDPT_13164_VerifyThatItIsPossibleToSetCompanyNameAsTheUsersPostgraduateWorkplace {

  private static final String GRADUATION_YEAR = "2010";
  private static final String COMPANY_NAME = "AutoTest_Company";
  private static final String CITY_NAME_REGEX = "^[^,]+";
  private int indexOfEducationSectionToBeAdded = 0;
  private int indexOfCompanyFieldToBeAdded = 0;
  private int indexOfYearFieldToBeAdded = 0;
  private int indexOfAssignmentFieldToBeAdded = 0;
  private String fieldToBeSet = "";

  private ProfileEditionScreen profileEditionScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void screenInitialization() {
    profileEditionScreen = new ProfileEditionScreen();
  }

  @BeforeMethod(inheritGroups = false, alwaysRun = true)
  private void initializeIndexesBasedOnPreviouslyPresentData(Method method) {
    if (method.getName().equals("checkCityAndEducationInfoIsFilled")) {
      indexOfEducationSectionToBeAdded = profileEditionScreen.getEducationSectionsQuantity();
      indexOfCompanyFieldToBeAdded = profileEditionScreen.getCompanyOfEducationSectionFieldCount();
      indexOfYearFieldToBeAdded = profileEditionScreen.getYearFieldCount();
      indexOfAssignmentFieldToBeAdded = profileEditionScreen.getAssignmentFieldCount();
    }
  }

  @Test(priority = 1)
  public void checkUserLogin() {
    assertTrue(
        new LoginService().loginAndSetLanguage(UserFactory.withSimplePermissionAndWithTraining())
            .isProfilePhotoImageDisplayed(), "User isn't logged in!");
  }

  @Test(priority = 2)
  public void checkEditProfileScreenIsOpened() {
    assertTrue(new HeaderScreen().clickProfileMenu().clickProfileButton().clickEditProfileLink()
        .isScreenLoaded(), "Edit profile screen isn't opened!");
  }

  @Test(priority = 3)
  public void checkCityAndEducationInfoIsFilled() {
    softAssert = new SoftAssert();
    profileEditionScreen.clickAddEducationButton();
    fieldToBeSet = LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_MINSK_FIELD);
    String cityOfStudy = profileEditionScreen
        .clickCityOfStudyFieldByIndex(indexOfEducationSectionToBeAdded)
        .typeTextToCityOfStudyInputByIndex(fieldToBeSet,
            indexOfEducationSectionToBeAdded + 1)
        .waitSpinnerOfLoadingInvisibility()
        .waitCityOfStudyDropdownValuesDisplayed()
        .clickCityOfStudyDropDownByIndexAndText(fieldToBeSet,
            indexOfEducationSectionToBeAdded + 1)
        .getCityOfStudyValueByIndex(indexOfEducationSectionToBeAdded);
    softAssert.assertEquals(getStringValueByRegex(CITY_NAME_REGEX, cityOfStudy), fieldToBeSet,
        "City of study field isn't filled!");
    fieldToBeSet = profileEditionScreen
        .clickEducationFormFieldByIndex(indexOfEducationSectionToBeAdded)
        .getEducationFormRandomValueByIndex(indexOfEducationSectionToBeAdded);
    softAssert.assertEquals(profileEditionScreen.clickEducationFormDropDownByText(fieldToBeSet)
            .getEducationFormValueByIndex(indexOfEducationSectionToBeAdded),
        fieldToBeSet,
        "Education form field isn't filled!");
    fieldToBeSet = profileEditionScreen
        .clickEducationalEstablishmentFieldByIndex(indexOfEducationSectionToBeAdded)
        .getEducationalEstablishmentRandomValueByIndex(indexOfEducationSectionToBeAdded);
    softAssert.assertEquals(profileEditionScreen
            .clickEducationalEstablishmentDropDownByText(fieldToBeSet)
            .getEducationEstablishmentValueByIndex(indexOfEducationSectionToBeAdded), fieldToBeSet,
        "Educational establishment field isn't filled!");
    softAssert.assertAll();
  }

  @Test(priority = 4)
  public void checkDegreeAndYearInfoIsFilled() {
    softAssert = new SoftAssert();
    fieldToBeSet = profileEditionScreen
        .clickDegreeInformationFieldByIndex(indexOfEducationSectionToBeAdded)
        .getDegreeInformationRandomValueByIndex(indexOfEducationSectionToBeAdded);
    softAssert.assertEquals(profileEditionScreen.clickDegreeInformationDropDownByText(fieldToBeSet)
            .getDegreeInformationValueByIndex(indexOfEducationSectionToBeAdded),
        fieldToBeSet, "Degree information field isn't filled!");
    softAssert.assertEquals(
        profileEditionScreen.clickGraduationYearFieldByIndex(indexOfEducationSectionToBeAdded)
            .clickGraduationYearDropDownByText(GRADUATION_YEAR)
            .getGraduationYearValueByIndex(indexOfEducationSectionToBeAdded),
        GRADUATION_YEAR,
        "Graduation year field isn't filled!");
    softAssert.assertAll();
  }

  @Test(priority = 5)
  public void checkOtherFieldsAreFilledIfPresent() {
    softAssert = new SoftAssert();
    if (!profileEditionScreen.isFacultyChosenPresent() && !profileEditionScreen
        .isFacultyChosenPresentForIndex(indexOfEducationSectionToBeAdded)
        && !profileEditionScreen
        .isFacultyChosenDisabledForIndex(indexOfEducationSectionToBeAdded)) {
      fieldToBeSet = profileEditionScreen.clickFacultyFieldByIndex(indexOfEducationSectionToBeAdded)
          .getFacultyRandomValueByIndex(indexOfEducationSectionToBeAdded);
      softAssert.assertEquals(profileEditionScreen
              .clickFacultyDropDownByText(fieldToBeSet)
              .getFacultyValueByIndex(indexOfEducationSectionToBeAdded), fieldToBeSet,
          "Faculty field isn't filled!");
    }
    if (!profileEditionScreen.isDepartmentChosenPresent() && !profileEditionScreen
        .isDepartmentChosenPresentForIndex(indexOfEducationSectionToBeAdded)
        && !profileEditionScreen
        .isDepartmentChosenDisabledForIndex(indexOfEducationSectionToBeAdded)) {
      fieldToBeSet = profileEditionScreen
          .clickDepartmentFieldByIndex(indexOfEducationSectionToBeAdded)
          .getDepartmentRandomValueByIndex(indexOfEducationSectionToBeAdded);
      softAssert.assertEquals(
          profileEditionScreen.clickDepartmentDropDownByText(fieldToBeSet)
              .getDepartmentValueByIndex(indexOfEducationSectionToBeAdded), fieldToBeSet,
          "Department field isn't filled!");
    }
    if (!profileEditionScreen.isUniversityCourseChosenPresent() && !profileEditionScreen
        .isUniversityCourseChosenPresentForIndex(indexOfYearFieldToBeAdded)
        && !profileEditionScreen
        .isUniversityCourseChosenDisabledForIndex(indexOfYearFieldToBeAdded)) {
      fieldToBeSet = profileEditionScreen.clickYearFieldByIndex(indexOfYearFieldToBeAdded)
          .getUniversityCourseRandomValueByIndex(indexOfYearFieldToBeAdded);
      softAssert.assertEquals(profileEditionScreen.clickUniversityCourseDropDownByText(fieldToBeSet)
              .getUniversityCourseValueByIndex(indexOfYearFieldToBeAdded),
          fieldToBeSet, "University course year isn't filled!");
    }
    softAssert.assertAll();
  }

  @Test(priority = 6)
  public void checkCompanyFieldIsDisplayed() {
    softAssert = new SoftAssert();
    fieldToBeSet = LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_ASSIGNED_FIELD);
    softAssert.assertEquals(
        profileEditionScreen.clickAssignmentFieldByIndex(indexOfAssignmentFieldToBeAdded)
            .clickAssignmentDropDownByText(fieldToBeSet)
            .getAssignmentValueByIndex(indexOfAssignmentFieldToBeAdded), fieldToBeSet,
        "Assignment field isn't filled with Assigned as value!");
    softAssert.assertTrue(profileEditionScreen
            .isCompanyOfEducationSectionInputFieldDisplayedForIndex(indexOfCompanyFieldToBeAdded),
        "Company filed isn't displayed after assigned value is set for assignment!");
    softAssert.assertAll();
  }

  @Test(priority = 7)
  public void checkCompanyNameIsFilledIn() {
    assertEquals(profileEditionScreen
            .typeCompanyNameInEducationSection(COMPANY_NAME, indexOfCompanyFieldToBeAdded)
            .getCompanyNameInEducationSection(indexOfCompanyFieldToBeAdded), COMPANY_NAME,
        "Company field isn't filled in!");
  }

  @Test(priority = 8)
  public void checkCompanyNameIsSet() {
    assertEquals(profileEditionScreen.clickSaveProfileButton().waitSpinnerOfLoadingInvisibility()
            .waitTrainingInfoDisplayed().clickProfessionalInfoButton().getCompanyName(), COMPANY_NAME,
        "Company name isn't filled in!");
  }

  @AfterMethod(inheritGroups = false, alwaysRun = true)
  private void deleteEducation(Method method, ITestResult result) {
    if (method.getName().equals("checkCompanyNameIsSet") && result.isSuccess()) {
      new ProfileScreen().clickEditProfileLink()
          .removeEducationTemplateWithCompanyName(COMPANY_NAME).clickContinueOnDeleteAlert()
          .scrollToTop();
      profileEditionScreen.clickSaveProfileButton()
          .waitScreenLoading();
    }
  }
}
