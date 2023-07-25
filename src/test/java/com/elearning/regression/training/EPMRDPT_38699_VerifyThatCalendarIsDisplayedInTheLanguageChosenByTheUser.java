package com.epmrdpt.regression.training;

import static com.epmrdpt.bo.user.UserFactory.asTrainer;
import static com.epmrdpt.utils.StringUtils.getCurrentMonthValueByLanguageCode;
import static com.epmrdpt.utils.StringUtils.getShortWeekdaysByLanguageCode;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.apache.commons.lang3.StringUtils.lowerCase;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.screens.ReactTrainingScreen;
import com.epmrdpt.services.LanguageSwitchingService;
import com.epmrdpt.services.ReactLoginService;
import java.util.Calendar;
import java.util.Locale;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_38699_VerifyThatCalendarIsDisplayedInTheLanguageChosenByTheUser",
    groups = {"full", "react", "regression"})
public class EPMRDPT_38699_VerifyThatCalendarIsDisplayedInTheLanguageChosenByTheUser {

  private ReactTrainingScreen reactTrainingScreen;
  private String chosenLanguageCode;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  private void setupReactTrainingPage() {
    reactTrainingScreen = new ReactLoginService()
        .loginAndGoToReactTrainingScreen(asTrainer())
        .waitCalendarForVisibility();
    chosenLanguageCode = new Locale(LanguageSwitchingService.getLocaleLanguage().getLanguageCode())
        .toString();
  }

  @Test
  public void checkMonthInCalendar() {
    String currentMonth = getCurrentMonthValueByLanguageCode(chosenLanguageCode);
    assertTrue(reactTrainingScreen.getMonthAndYearInCalendarText().contains(currentMonth),
        "Month in 'Calendar' isn't displayed correctly in chosen language!");
  }

  @Test
  public void checkDayOfWeekInCalendar() {
    SoftAssert softAssert = new SoftAssert();
    String[] expectedDaysOfWeek = getShortWeekdaysByLanguageCode(chosenLanguageCode);
    String dayOfWeekInProperFormat;
    for (int i = 1; i < Calendar.DAY_OF_WEEK_IN_MONTH; i++) {
      if (System.getProperty("locale").equals("en")) {
        dayOfWeekInProperFormat = capitalize(expectedDaysOfWeek[i]);
      } else {
        dayOfWeekInProperFormat = lowerCase(expectedDaysOfWeek[i]);
      }
      softAssert.assertTrue(reactTrainingScreen.isDayOfWeekDisplayed(dayOfWeekInProperFormat),
          format("Day of week '%s' in 'Calendar' isn't displayed correctly in chosen language!",
              expectedDaysOfWeek[i]));
    }
    softAssert.assertAll();
  }
}
