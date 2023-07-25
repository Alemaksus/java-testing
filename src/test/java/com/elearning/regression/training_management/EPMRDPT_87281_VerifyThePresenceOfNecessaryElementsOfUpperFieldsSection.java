package com.epmrdpt.regression.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_ADD_IMAGE_BUTTON;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_CUSTOM_IMAGE_TITLE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_DESCRIPTION_LABEL_NAMES;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactDescriptionTabScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.utils.StringUtils;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_87281_VerifyThePresenceOfNecessaryElementsOfUpperFieldsSection",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_87281_VerifyThePresenceOfNecessaryElementsOfUpperFieldsSection {

  private ReactDescriptionTabScreen reactDescriptionTabScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactDescriptionTabScreen = new LoginService()
        .loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(
            UserFactory.asTrainingManager())
        .clickReactTrainingManagementLink()
        .clickCreateNewButton()
        .waitScreenLoading()
        .clickDescriptionTabFromTrainingScreen()
        .waitAllSpinnersAreNotDisplayed();
  }

  @Test()
  public void verifyInputLabelsAndInputsOfUpperFieldsSection() {
    List<String> labelNames = StringUtils
        .getListOfStringValues(getValueOf(TRAINING_DESCRIPTION_LABEL_NAMES));
    softAssert = new SoftAssert();
    for (String label : labelNames) {
      softAssert.assertTrue(reactDescriptionTabScreen.isInputLabelDisplayedByName(label),
          String.format("'%s' label isn't displayed!", label));
      if (!label.equals(getValueOf(TRAINING_DESCRIPTION_CUSTOM_IMAGE_TITLE))) {
        softAssert.assertTrue(reactDescriptionTabScreen.isInputFieldDisplayedByName(label),
            String.format("'%s' input isn't displayed!", label));
      }
    }
    softAssert.assertAll();
  }

  @Test
  public void verifyAddImageButton() {
    softAssert = new SoftAssert();
    softAssert.assertTrue(reactDescriptionTabScreen.isAddImageButtonDisplayed(),
        "'Add image' button isn't displayed!");
    softAssert.assertEquals(reactDescriptionTabScreen.getAddImageButtonText(),
        getValueOf(TRAINING_DESCRIPTION_ADD_IMAGE_BUTTON),
        "'Add image' button has incorrect text!");
    softAssert.assertAll();
  }
}
