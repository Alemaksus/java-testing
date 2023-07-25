package com.epmrdpt.smoke.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainingManager;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactParticipantsTrainingScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactTrainingManagementService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(description = "EPMRDPT_92341_VerifyThatTheCountryFieldContainsOnlyTheCountriesOfTheTrainingParticipants",
    groups = {"full", "smoke"})
public class EPMRDPT_92341_VerifyThatTheCountryFieldContainsOnlyTheCountriesOfTheTrainingParticipants {

  private ReactParticipantsTrainingScreen reactParticipantsTrainingScreen;
  private Set<String> participantsCountries = new HashSet<>();
  private final static String TRAINING_NAME = "AutoTestTraining92341";

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asTrainingManager())
        .clickReactTrainingManagementLink();
    new ReactTrainingManagementService()
        .searchTrainingByNameAndRedirectOnIt(TRAINING_NAME);
    reactParticipantsTrainingScreen = new ReactDetailTrainingScreen()
        .clickReactParticipantsTab()
        .clickSelectApplicationCountryInput();
  }

  @Test
  public void checkAllCountriesInDDLMatchWithParticipants() {
    Set<String> ddlCountries = new HashSet<>(
        List.of(
            reactParticipantsTrainingScreen.getTextOfElementsInTableOrDDL().get(0).split("\n")));
    reactParticipantsTrainingScreen.getAllCountriesAndCitiesOfParticipants()
        .forEach(x -> participantsCountries.add(x.replaceAll(",.*", "")));
    assertTrue(ddlCountries.containsAll(participantsCountries),
        "Count of countries of participants does not match with count in DDL!");
  }
}
