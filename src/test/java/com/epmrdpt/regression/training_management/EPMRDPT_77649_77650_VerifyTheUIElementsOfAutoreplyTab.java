package com.epmrdpt.regression.training_management;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactAutoreplyTabScreen;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.services.LoginService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_77649_77650_VerifyTheUIElementsOfAutoreplyTab",
    groups = {"full", "regression"})
public class EPMRDPT_77649_77650_VerifyTheUIElementsOfAutoreplyTab {

  private ReactAutoreplyTabScreen reactAutoreplyTabScreen;
  private ReactDetailTrainingScreen reactDetailTrainingScreen;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactDetailTrainingScreen = new ReactDetailTrainingScreen();
    reactAutoreplyTabScreen = new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asTrainingManager())
        .waitLinksToRedirectOnOtherSectionsDisplayed()
        .clickReactTrainingManagementLink()
        .waitTrainingScreenIsLoaded()
        .clickCreateNewButton()
        .clickAutoreplyTabFromTrainingScreen();
  }

  @Test
  public void checkIfEditCheckBoxIsUnchecked() {
    Assert.assertFalse(reactAutoreplyTabScreen.isEditCheckBoxChecked(),
        "Edit checkbox is not unchecked");
  }

  @Test(priority = 1)
  public void checkTheUIElementsOfAutoreplyTab() {
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(reactAutoreplyTabScreen.isAutoreplyTextLabelDisplayed(),
        "Autoreply text label is not presented");
    softAssert.assertTrue(reactAutoreplyTabScreen.isSendTestLetterButtonDisplayed(),
        "Send test letter button is not presented");
    softAssert.assertTrue(reactAutoreplyTabScreen.isEditCheckBoxDisplayed(),
        "Edit checkbox is not presented");
    softAssert.assertTrue(reactAutoreplyTabScreen.isDisabledTextAreaHasCorrectNumberOfTemplateTexts(),
        "Text area is not disabled");
    softAssert.assertAll();
  }

  @Test(priority = 2)
  public void checkLetterTemplatesForEnglishLocaleInTheTextBlock() {
    List<String> expectedLetterTemplatesTextInEnglish = new ArrayList<>(Arrays.asList(
        "Thank you for registering to <training name>.",
        "Dates: <start date> - <end date>",
        "Our recruiter will get in touch with you when staffing is in process."));
    Assert.assertEquals(reactAutoreplyTabScreen.getLetterTemplates(),
        expectedLetterTemplatesTextInEnglish,
        "Letter templates for English locale do not match");
  }

  @Test(priority = 3)
  public void checkLetterTemplatesForRussianLocaleInTheTextBlock() {
    List<String> expectedLetterTemplatesTextInRussian = new ArrayList<>(Arrays.asList(
        "Спасибо за регистрацию на <training name>.",
        "Даты: <start date> - <end date>",
        "Наши сотрудники свяжутся с вами, когда начнется набор на тренинг."));
    reactDetailTrainingScreen.clickSelectLanguageButton()
        .clickRussianLanguageButton();
    Assert.assertEquals(reactAutoreplyTabScreen.getLetterTemplates(),
        expectedLetterTemplatesTextInRussian,
        "Letter templates for Russian locale do not match");
  }

  @Test(priority = 4)
  public void checkLetterTemplatesForUkrainianLocaleInTheTextBlock() {
    List<String> expectedLetterTemplatesTextInUkrainian = new ArrayList<>(Arrays.asList(
        "Дякуємо за реєстрацію на <training name>.",
        "Дати: <start date> - <end date>",
        "Наші співробітники зв'яжуться з вами, коли почнеться набір на програму."));
    reactDetailTrainingScreen.clickUkrainianLanguageButton();
    Assert.assertEquals(reactAutoreplyTabScreen.getLetterTemplates(),
        expectedLetterTemplatesTextInUkrainian,
        "Letter templates for Ukrainian locale do not match");
  }
}
