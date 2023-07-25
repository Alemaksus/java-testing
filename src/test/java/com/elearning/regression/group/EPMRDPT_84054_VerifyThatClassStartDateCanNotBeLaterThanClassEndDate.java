package com.epmrdpt.regression.group;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.APPLICATIONS_DEPARTMENT_AFFILIATE;
import static com.epmrdpt.utils.StringUtils.getLocaleDateFromString;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactGroupDetailsScreen;
import com.epmrdpt.services.ReactClassService;
import com.epmrdpt.services.ReactLoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.time.LocalDate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_84054_VerifyThatClassStartDateCanNotBeLaterThanClassEndDate",
    groups = {"full", "regression"})
public class EPMRDPT_84054_VerifyThatClassStartDateCanNotBeLaterThanClassEndDate {

  private static final String DATE_PATTERN = "dd.MM.yyyy";

  private final String trainingName = "Department Affiliate";
  private final String className = "Department affiliate 1";
  private ReactDetailTrainingScreen reactDetailTrainingScreen;

  @BeforeClass
  public void loginAndGoToTraining() {
    new ReactLoginService()
        .loginAndGoToReactTrainingManagement(asTrainingManager());
    reactDetailTrainingScreen = new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(trainingName)
        .waitScreenLoaded();
  }

  @Test(priority = 1)
  public void checkTypeOfTraining() {
    assertEquals(
        reactDetailTrainingScreen.getTypeText(),
        getValueOf(APPLICATIONS_DEPARTMENT_AFFILIATE),
        "Training's type is not correct!"
    );
  }

  @Test(priority = 2)
  public void checkThatStartDayLaterThanEndDateIsIgnored() {
    ReactGroupDetailsScreen reactGroupDetailsScreen = reactDetailTrainingScreen
        .clickGroupsTabs()
        .waitUntilGroupNameByLinkIsDisplayed(className)
        .clickGroupByName(className);
    LocalDate nextDateAfterEndDate = getLocaleDateFromString(
        reactGroupDetailsScreen.getEndDateInputValue(),
        DATE_PATTERN)
        .plusDays(1);
    new ReactClassService()
        .enterStartDate(nextDateAfterEndDate)
        .clickStartDateLabel()
        .waitUntilStartDateAttributeValueIsEmpty();
    assertTrue(
        reactGroupDetailsScreen.isInvalidMessageDisplayed(),
        "Invalid message isn't displayed!"
    );
  }
}