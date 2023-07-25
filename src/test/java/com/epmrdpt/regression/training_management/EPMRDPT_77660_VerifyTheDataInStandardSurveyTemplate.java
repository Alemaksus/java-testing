package com.epmrdpt.regression.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_SURVEY_AND_TESTING_TAB_SCREEN_ANSWER_LABEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SURVEY_SCREEN_QUESTION;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactDetailTrainingScreen;
import com.epmrdpt.screens.ReactSurveyAndTestingTabScreen;
import com.epmrdpt.services.LoginService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_77660_VerifyTheDataInStandardSurveyTemplate",
    groups = {"full", "react", "regression"})
public class EPMRDPT_77660_VerifyTheDataInStandardSurveyTemplate {

  private ReactSurveyAndTestingTabScreen reactSurveyAndTestingTabScreen;
  private final String QUESTION = getValueOf(SURVEY_SCREEN_QUESTION);
  private final String ANSWER_OPTION = getValueOf(REACT_SURVEY_AND_TESTING_TAB_SCREEN_ANSWER_LABEL);

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asTrainingManager())
        .clickReactTrainingManagementLink()
        .clickCreateNewButton()
        .waitScreenLoading();
    new ReactDetailTrainingScreen()
        .clickSelectLanguageButton()
        .clickUkrainianLanguageButton()
        .clickRussianLanguageButton();
    reactSurveyAndTestingTabScreen = new ReactSurveyAndTestingTabScreen()
        .clickSurveyAndTestingTabFromTrainingScreen()
        .selectStandardSurveyType();
  }

  @Test
  public void checkTheDataInStandardSurveyTemplate() {
    List<String> expectedPreFilledForQuestion = new ArrayList<>(Arrays.asList(
        "Where did you learn about our training?",
        "Откуда вы узнали об этом тренинге?",
        "Звiдки ви дiзналися про нашi програми?"));
    List<String> expectedPreFilledForAnswer1 = new ArrayList<>(Arrays.asList(
        "From friends/colleagues/EPAM employees",
        "От друзей/коллег/сотрудников EPAM",
        "Вiд друзiв/ колег/ співробітників EPAM"));
    List<String> expectedPreFilledForAnswer2 = new ArrayList<>(Arrays.asList(
        "Self-search on the Internet",
        "Самостоятельный поиск в интернете",
        "Самостійно шукав/ла в інтернеті"));
    List<String> expectedPreFilledForAnswer3 = new ArrayList<>(Arrays.asList(
        "From social networks",
        "Из социальных сетей",
        "Iз соціальних мереж"));
    List<String> expectedPreFilledForAnswer4 = new ArrayList<>(Arrays.asList(
        "From the EPAM mailing list",
        "Из рассылки EPAM",
        "Iз розсилки EPAM"));
    List<String> expectedPreFilledForAnswer5 = new ArrayList<>(Arrays.asList(
        "Events",
        "На мероприятиях",
        "Пiд час заходів"));
    List<String> expectedPreFilledForAnswer6 = new ArrayList<>(Arrays.asList(
        "From educational institution (printed materials, teacher)",
        "Из учебного заведения (печатные материалы, преподаватель)",
        "У навчальному закладі (друкована продукція, матеріали, викладач)"));
    List<String> expectedPreFilledForAnswer7 = new ArrayList<>(Arrays.asList(
        "HeadHunter",
        "HeadHunter",
        "HeadHunter"));
    Map<String, List<String>> answerAndQuestionLabelsWithPreFilledText =
        reactSurveyAndTestingTabScreen.getPreFilledTextByLabelName();
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(answerAndQuestionLabelsWithPreFilledText.get(QUESTION),
        expectedPreFilledForQuestion,
        String.format("Pre-filled text input for '%s' isn't correct!", QUESTION));
    softAssert.assertEquals(answerAndQuestionLabelsWithPreFilledText.get(ANSWER_OPTION + " 1"),
        expectedPreFilledForAnswer1,
        String.format("Pre-filled text input for '%s 1' isn't correct!", ANSWER_OPTION));
    softAssert.assertEquals(answerAndQuestionLabelsWithPreFilledText.get(ANSWER_OPTION + " 2"),
        expectedPreFilledForAnswer2,
        String.format("Pre-filled text input for '%s 2' isn't correct!", ANSWER_OPTION));
    softAssert.assertEquals(answerAndQuestionLabelsWithPreFilledText.get(ANSWER_OPTION + " 3"),
        expectedPreFilledForAnswer3,
        String.format("Pre-filled text input for '%s 3' isn't correct!", ANSWER_OPTION));
    softAssert.assertEquals(answerAndQuestionLabelsWithPreFilledText.get(ANSWER_OPTION + " 4"),
        expectedPreFilledForAnswer4,
        String.format("Pre-filled text input for '%s 4' isn't correct!", ANSWER_OPTION));
    softAssert.assertEquals(answerAndQuestionLabelsWithPreFilledText.get(ANSWER_OPTION + " 5"),
        expectedPreFilledForAnswer5,
        String.format("Pre-filled text input for '%s 5' isn't correct!", ANSWER_OPTION));
    softAssert.assertEquals(answerAndQuestionLabelsWithPreFilledText.get(ANSWER_OPTION + " 6"),
        expectedPreFilledForAnswer6,
        String.format("Pre-filled text input for '%s 6' isn't correct!", ANSWER_OPTION));
    softAssert.assertEquals(answerAndQuestionLabelsWithPreFilledText.get(ANSWER_OPTION + " 7"),
        expectedPreFilledForAnswer7,
        String.format("Pre-filled text input for '%s 7' isn't correct!", ANSWER_OPTION));
    softAssert.assertAll();
  }
}
