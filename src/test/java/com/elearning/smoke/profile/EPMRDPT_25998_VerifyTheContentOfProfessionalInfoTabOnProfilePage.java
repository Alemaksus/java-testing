package com.epmrdpt.smoke.profile;

import static org.testng.Assert.assertTrue;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.FooterScreen;
import com.epmrdpt.screens.ProfileScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.utils.StringUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_25998_VerifyTheContentOfProfessionalInfoTabOnProfilePage",
    groups = {"full", "general", "smoke"})
public class EPMRDPT_25998_VerifyTheContentOfProfessionalInfoTabOnProfilePage {

  private ProfileScreen profileScreen;
  private SoftAssert softAssert;
  private String patternDate = "dd.mm.yyyy";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupProfessionalInfoTab() {
    profileScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.withSimplePermissionAndWithoutTrainings())
        .clickProfileMenu()
        .clickProfileButton()
        .clickProfessionalInfoButton();
  }

  @Test
  public void checkEducationBlockTitle() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isEducationBlockDisplayed(),
        "'Education' block on 'Professional info' tab isn't displayed!");
    softAssert.assertEquals(profileScreen.getEducationBlockText(),
        LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_EDUCATION_BLOCK_NAME),
        "'Education' block has incorrect title!");
    softAssert.assertAll();
  }

  @Test
  public void checkCityOfStudyRowInEducationBlock() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isCityOfStudyTitleDisplayed(),
        "'City of study' title in 'Education' block isn't displayed!");
    softAssert.assertEquals(profileScreen.getCityOfStudyTitleText(),
        LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_CITY_OF_EDUCATION_TITLE),
        "Title 'City of study' in 'Education' block has incorrect text!");
    softAssert.assertFalse(profileScreen.getCityOfStudyDescriptionText().isEmpty(),
        "Field 'City of study' in 'Education' block hasn't description!");
    softAssert.assertAll();
  }

  @Test
  public void checkEducationalEstablishmentRowInEducationBlock() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isEducationalEstablishmentTitleDisplayed(),
        "'Educational establishment' title in 'Education' block isn't displayed!");
    softAssert.assertEquals(profileScreen.getEducationalEstablishmentTitleText(),
        LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_EDUCATIONAL_ESTABLISHMENT_TITLE),
        "Title 'Educational establishment' in 'Education' block has incorrect text!");
    softAssert.assertFalse(profileScreen.getEducationalEstablishmentDescriptionText().isEmpty(),
        "Field 'Educational establishment' in 'Education' block hasn't description!");
    softAssert.assertAll();
  }

  @Test
  public void checkFacultyRowInEducationBlock() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isFacultyTitleDisplayed(),
        "'Faculty' title in 'Education' block isn't displayed!");
    softAssert.assertEquals(profileScreen.getFacultyFieldFTitleText(),
        LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_FACULTY_TITLE_TITLE),
        "Title 'Faculty' in 'Education' block has incorrect text!");
    softAssert.assertFalse(profileScreen.getFacultyDescriptionText().isEmpty(),
        "Field 'Faculty' in 'Education' block hasn't description!");
    softAssert.assertAll();
  }

  @Test
  public void checkDepartmentRowInEducationBlock() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isDepartmentTitleDisplayed(),
        "'Department' title in 'Education' block isn't displayed!");
    softAssert.assertEquals(profileScreen.getDepartmentTitleText(),
        LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_DEPARTMENT_TITLE),
        "Title 'Department' in 'Education' block has incorrect text!");
    softAssert.assertFalse(profileScreen.getDepartmentDescriptionText().isEmpty(),
        "Field 'Department' in 'Education' block hasn't description!");
    softAssert.assertAll();
  }

  @Test
  public void checkEducationFormRowInEducationBlock() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isEducationFormTitleDisplayed(),
        "'Education form' title in 'Education' block isn't displayed!");
    softAssert.assertEquals(profileScreen.getEducationFormTitleText(),
        LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_EDUCATION_FORM_TITLE_TITLE),
        "Title 'Education form' in 'Education' block has incorrect text!");
    softAssert.assertFalse(profileScreen.getEducationFormDescriptionText().isEmpty(),
        "Field 'Education form' in 'Education' block hasn't description!");
    softAssert.assertAll();
  }

  @Test
  public void checkDegreeInformationRowInEducationBlock() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isDegreeInformationTitleDisplayed(),
        "'Degree information' title in 'Education' block isn't displayed!");
    softAssert.assertEquals(profileScreen.getDegreeInformationTitleText(),
        LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_DEGREE_INFORMATION_TITLE),
        "Title 'Degree information' in 'Education' block has incorrect text!");
    softAssert.assertFalse(profileScreen.getDegreeInformationDescriptionText().isEmpty(),
        "Field 'Degree information' in 'Education' block hasn't description!");
    softAssert.assertAll();
  }

  @Test
  public void checkYearRowInEducationBlock() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isYearTitleDisplayed(),
        "'Year' title in 'Education' block isn't displayed!");
    softAssert.assertEquals(profileScreen.getYearTitleText(),
        LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_YEAR_TITLE),
        "Title 'Year' in 'Education' block has incorrect text!");
    softAssert.assertFalse(profileScreen.getYearDescriptionText().isEmpty(),
        "Field 'Year' in 'Education' block hasn't description!");
    softAssert.assertAll();
  }

  @Test
  public void checkGraduationYearRowInEducationBlock() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isGraduationYearTitleDisplayed(),
        "'Graduation year' title in 'Education' block isn't displayed!");
    softAssert.assertEquals(profileScreen.getGraduationYearTitleText(),
        LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_GRADUATION_YEAR_TITLE),
        "Title 'Graduation year' in 'Education' block has incorrect text!");
    softAssert.assertFalse(profileScreen.getGraduationYearDescriptionText().isEmpty(),
        "Field 'Graduation year' in 'Education' block hasn't description!");
    softAssert.assertAll();
  }

  @Test
  public void checkAssignmentRowInEducationBlock() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isAssignmentTitleDisplayed(),
        "'Assignment' title in 'Education' block isn't displayed!");
    softAssert.assertEquals(profileScreen.getAssignmentTitleText(),
        LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_ASSIGNMENT_TITLE),
        "Title 'Assignment' in 'Education' block has incorrect text!");
    softAssert.assertFalse(profileScreen.getGraduationYearDescriptionText().isEmpty(),
        "Field 'Assignment' in 'Education' block hasn't description!");
    softAssert.assertAll();
  }

  @Test
  public void checkCompanyRowInEducationBlock() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isCompanyTitleDisplayed(),
        "'Company' title in 'Education' block isn't displayed!");
    softAssert.assertEquals(profileScreen.getCompanyTitleText(),
        LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_COMPANY_TITLE),
        "Title 'Company' in 'Education' block has incorrect text!");
    softAssert.assertFalse(profileScreen.getCompanyDescriptionText().isEmpty(),
        "Field 'Company' in 'Education' block hasn't description!");
    softAssert.assertAll();
  }

  @Test
  public void checkWorkExperienceBlockTitle() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isWorkExperienceDisplayed(),
        "'Work experience' block on 'Professional info' tab isn't displayed!");
    softAssert.assertEquals(profileScreen.getWorkExperienceBlockText(),
        LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_WORK_EXPERIENCE_TITLE),
        "'Work experience' block has incorrect title!");
    softAssert.assertAll();
  }

  @Test
  public void checkCompanyNameRowInWorkExperienceBlock() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isCompanyNameTitleDisplayed(),
        "'Company name' title in 'Work experience' block isn't displayed!");
    softAssert.assertEquals(profileScreen.getCompanyNameTitleText(),
        LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_WORK_EXPERIENCE_COMPANY_NAME),
        "Title 'Company name' in 'Work experience' block has incorrect text!");
    softAssert.assertFalse(profileScreen.getCompanyNameDescriptionText().isEmpty(),
        "Field 'Company name' in 'Work experience' block hasn't description!");
    softAssert.assertAll();
  }

  @Test
  public void checkPositionRowInWorkExperienceBlock() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isPositionTitleDisplayed(),
        "'Position' title in 'Work experience' block isn't displayed!");
    softAssert.assertEquals(profileScreen.getPositionTitleText(),
        LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_WORK_EXPERIENCE_POSITION),
        "Title 'Position' in 'Work experience' block has incorrect text!");
    softAssert.assertFalse(profileScreen.getPositionDescriptionText().isEmpty(),
        "Field 'Position' in 'Work experience' block hasn't description!");
    softAssert.assertAll();
  }

  @Test
  public void checkPeriodRowInWorkExperienceBlock() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isPeriodTitleDisplayed(),
        "'Period' title in 'Work experience' block isn't displayed!");
    softAssert.assertEquals(profileScreen.getPeriodTitleText(),
        LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_WORK_EXPERIENCE_PERIOD),
        "Title 'Period' in 'Work experience' block has incorrect text!");
    softAssert.assertTrue(StringUtils
            .isDateMatchExpectedPattern(profileScreen.getStartWorkPeriodDescriptionText(), patternDate),
        "Start work date in 'Period' field has incorrect format!");
    softAssert.assertTrue(StringUtils
            .isDateMatchExpectedPattern(profileScreen.getEndWorkPeriodDescriptionText(), patternDate),
        "End work date in 'Period' field has incorrect format!");
    softAssert.assertAll();
  }

  @Test
  public void checkAdditionalInformationInWorkExperienceBlock() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isAdditionalInformationAboutWorkTitleDisplayed(),
        "'Additional information' title in 'Work experience' block isn't displayed!");
    softAssert.assertEquals(profileScreen.getAdditionalInformationAboutWorkTitleText(),
        LocaleProperties.getValueOf(
            LocalePropertyConst.PROFILE_WORK_EXPERIENCE_ADDITIONAL_INFORMATION),
        "Title 'Additional information' in 'Work experience' block has incorrect text!");
    softAssert.assertFalse(
        profileScreen.getAdditionalInformationAboutWorkDescriptionText().isEmpty(),
        "Field 'Additional information' in 'Work experience' block hasn't description!");
    softAssert.assertAll();
  }

  @Test
  public void checkProfessionalSkillsBlock() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isProfessionalSkillsBlockDisplayed(),
        "'Professional skills' block on 'Professional info' tab isn't displayed!");
    softAssert.assertEquals(profileScreen.getProfessionalSkillsBlockText(),
        LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_PROFESSIONAL_SKILLS),
        "'Professional skills' block has incorrect title!");
    softAssert.assertFalse(profileScreen.getProfessionalSkillsDescriptionText().isEmpty(),
        "'Professional skills' block hasn't description!");
    softAssert.assertAll();
  }

  @Test
  public void checkAdditionalInformationBlock() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(profileScreen.getAdditionalInformationBlockTitle(),
        LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_ADDITIONAL_INFORMATION_TITLE),
        "'Additional information' block on 'Professional info' tab has incorrect title!");
    softAssert.assertFalse(profileScreen.getAdditionalInformationBlockDescription().isEmpty(),
        "'Additional information' block on 'Professional info' tab hasn't description!");
    softAssert.assertAll();
  }

  @Test
  public void checkProfessionalActivitiesBlock() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(profileScreen.getProfessionalActivitiesBlockTitle(),
        LocaleProperties.getValueOf(LocalePropertyConst.PROFILE_PROFESSIONAL_ACTIVITIES_TITLE),
        "'Professional activities' block on 'Professional info' tab has incorrect title!");
    softAssert.assertFalse(profileScreen.getProfessionalActivitiesBlockDescription().isEmpty(),
        "'Professional activities' block on 'Professional info' tab hasn't description!");
    softAssert.assertAll();
  }

  @Test
  public void checkDocumentsBlock() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isDocumentsBlockDisplayed(),
        "'Documents' block on 'Professional info' tab isn't displayed!");
    softAssert.assertTrue(profileScreen.isAttachedDocumentBlockDisplayed(),
        "Attached document in 'Documents' block isn't displayed!");
    softAssert.assertAll();
  }

  @Test
  public void checkUploadButtonInDocumentsBlock() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(profileScreen.isUploadButtonDisplayed(),
        "'Upload' button in 'Documents' block isn't displayed!");
    softAssert.assertEquals(profileScreen.getDocumentDescriptionText(),
        LocaleProperties.getValueOf(
            LocalePropertyConst.REGISTRATION_WIZARD_SCREEN_UPLOAD_FILE_HINT),
        "'Upload' button in 'Documents' block has incorrect description!");
    softAssert.assertAll();
  }

  @Test
  public void checkDownloadAllButtonInDocumentsBlock() {
    assertTrue(profileScreen.isDownloadAllDocumentsButtonDisplayed(),
        "'Download all' button in 'Documents' block isn't displayed!");
  }

  @Test
  public void checkFooterContainerDisplayed() {
    assertTrue(new FooterScreen().isScreenLoaded(),
        "'Footer' section on 'Professional info' tab isn't displayed!");
  }
}
