package com.epmrdpt.regression.traininglist;

import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_ENGLISH;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_CARD_PRICE_FREE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_CARD_PRICE_FREEMIUM;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_CARD_PRICE_PAID;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_LEARNING_FORMAT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_LEARNING_FORMAT_BLENDED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_LEARNING_FORMAT_FACE_TO_FACE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_LEARNING_FORMAT_ONLINE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_LEARNING_LANGUAGE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_LEARNING_LANGUAGE_ARMENIAN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_LEARNING_LEVEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_LEARNING_LEVEL_ADVANCED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_LEARNING_LEVEL_BASIC;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_LEARNING_LEVEL_INTERMEDIATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_LEARNING_LEVEL_PREREQUISITE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_LEARNING_PRICING;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_LEARNING_TYPE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_LEARNING_TYPE_INTERNSHIP;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_LEARNING_TYPE_LABORATORY;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_LEARNING_TYPE_MENTORING;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_LEARNING_TYPE_SELF_STUDY;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_LEARNING_TYPE_TRAINING;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_LEARNING_TYPE_WORKSHOP;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TRAINING_LIST_TYPE_TAB;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.screens.TrainingBlockScreen;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Test(description = "EPMRDPT_34313_VerifyThatAllNewFilterOptionsArePresentedInANewTabByTypeAddedToTheCurrentFilters",
    groups = {"full", "general", "regression"})
public class EPMRDPT_34313_VerifyThatOptionsArePresentedOnByTypeTab {

  private TrainingBlockScreen trainingBlockScreen;
  private SoftAssert softAssert;

  @BeforeClass(inheritGroups = false, alwaysRun = true)
  public void setupByTypeTabInDropdown() {
    trainingBlockScreen = new TrainingBlockScreen()
        .clickDropDownArrowButton()
        .clickTypesFiltersTab()
        .waitDropDownVisibility();
  }

  @Test
  public void checkAllCheckboxesAreDisplayed() {
    assertTrue(trainingBlockScreen.isAllTypesCheckboxesDisplayed(),
        "All checkboxes in dropdown aren't displayed!");
  }

  @Test
  public void checkByTypeTabName() {
    assertEquals(trainingBlockScreen.getTypesFiltersTabText(),
        LocaleProperties.getValueOf(TRAINING_LIST_TYPE_TAB),
        "'By type' tab has incorrect text!");
  }

  @Test
  public void checkContentInTypeColumn() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(trainingBlockScreen.getTypeColumnText(),
        LocaleProperties.getValueOf(TRAINING_LIST_LEARNING_TYPE),
        "'Type' header on column has incorrect text!");
    softAssert.assertEquals(trainingBlockScreen.getAllFilterTypesText(), getExpectedTypesList(),
        "Options on 'Type' column aren't displayed properly!");
    softAssert.assertAll();
  }

  @Test
  public void checkContentInFormatColumn() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(trainingBlockScreen.getFormatColumnText(),
        LocaleProperties.getValueOf(TRAINING_LIST_LEARNING_FORMAT),
        "'Format' header on column has incorrect text!");
    softAssert.assertEquals(trainingBlockScreen.getAllFilterFormatsText(), getExpectedFormatsList(),
        "Options on 'Format' column aren't displayed properly!");
    softAssert.assertAll();
  }

  @Test
  public void checkContentInPricingColumn() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(trainingBlockScreen.getPricingColumnText(),
        LocaleProperties.getValueOf(TRAINING_LIST_LEARNING_PRICING),
        "'Pricing' header on column has incorrect text!");
    softAssert.assertEquals(trainingBlockScreen.getAllFilterPricingText(), getExpectedPricingList(),
        "Options on 'Pricing' column aren't displayed properly!");
    softAssert.assertAll();
  }

  @Test
  public void checkContentInLanguageColumn() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(trainingBlockScreen.getLanguageColumnText(),
        LocaleProperties.getValueOf(TRAINING_LIST_LEARNING_LANGUAGE),
        "'Language' header on column has incorrect text!");
    softAssert.assertEquals(trainingBlockScreen.getAllFilterLanguagesText(),
        getExpectedLanguagesList(),
        "Options on 'Language' column aren't displayed properly!");
    softAssert.assertAll();
  }

  @Test
  public void checkContentInLevelColumn() {
    softAssert = new SoftAssert();
    softAssert.assertEquals(trainingBlockScreen.getLevelColumnText(),
        LocaleProperties.getValueOf(TRAINING_LIST_LEARNING_LEVEL),
        "'Level' header on column has incorrect text!");
    softAssert.assertEquals(trainingBlockScreen.getAllFilterLevelsText(), getExpectedLevelsList(),
        "Options on 'Level' column aren't displayed properly!");
    softAssert.assertAll();
  }

  private List<String> getExpectedTypesList() {
    List<String> expectedTypesList = new ArrayList<>();
    expectedTypesList.add(LocaleProperties.getValueOf(TRAINING_LIST_LEARNING_TYPE_TRAINING));
    expectedTypesList.add(LocaleProperties.getValueOf(TRAINING_LIST_LEARNING_TYPE_MENTORING));
    expectedTypesList.add(LocaleProperties.getValueOf(TRAINING_LIST_LEARNING_TYPE_WORKSHOP));
    expectedTypesList.add(LocaleProperties.getValueOf(TRAINING_LIST_LEARNING_TYPE_INTERNSHIP));
    expectedTypesList.add(LocaleProperties.getValueOf(TRAINING_LIST_LEARNING_TYPE_LABORATORY));
    expectedTypesList.add(LocaleProperties.getValueOf(TRAINING_LIST_LEARNING_TYPE_SELF_STUDY));
    return expectedTypesList;
  }

  private List<String> getExpectedFormatsList() {
    List<String> expectedFormatsList = new ArrayList<>();
    expectedFormatsList.add(
        LocaleProperties.getValueOf(TRAINING_LIST_LEARNING_FORMAT_FACE_TO_FACE));
    expectedFormatsList.add(LocaleProperties.getValueOf(TRAINING_LIST_LEARNING_FORMAT_ONLINE));
    expectedFormatsList.add(LocaleProperties.getValueOf(TRAINING_LIST_LEARNING_FORMAT_BLENDED));
    return expectedFormatsList;
  }

  private List<String> getExpectedPricingList() {
    List<String> expectedPricingList = new ArrayList<>();
    expectedPricingList.add(LocaleProperties.getValueOf(TRAINING_CARD_PRICE_FREE));
    expectedPricingList.add(LocaleProperties.getValueOf(TRAINING_CARD_PRICE_PAID));
    expectedPricingList.add(LocaleProperties.getValueOf(TRAINING_CARD_PRICE_FREEMIUM));
    return expectedPricingList;
  }

  private List<String> getExpectedLanguagesList() {
    List<String> expectedLanguagesList = new ArrayList<>();
    expectedLanguagesList.add(LocaleProperties.getValueOf(SETTINGS_LANGUAGE_DROPDOWN_ENGLISH));
    expectedLanguagesList.add(LocaleProperties.getValueOf(SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN));
    expectedLanguagesList.add(LocaleProperties.getValueOf(SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN));
    expectedLanguagesList.add(LocaleProperties.getValueOf(TRAINING_LIST_LEARNING_LANGUAGE_ARMENIAN));
    return expectedLanguagesList;
  }

  private List<String> getExpectedLevelsList() {
    List<String> expectedLevelsList = new ArrayList<>();
    expectedLevelsList.add(LocaleProperties.getValueOf(TRAINING_LIST_LEARNING_LEVEL_PREREQUISITE));
    expectedLevelsList.add(LocaleProperties.getValueOf(TRAINING_LIST_LEARNING_LEVEL_BASIC));
    expectedLevelsList.add(LocaleProperties.getValueOf(TRAINING_LIST_LEARNING_LEVEL_INTERMEDIATE));
    expectedLevelsList.add(LocaleProperties.getValueOf(TRAINING_LIST_LEARNING_LEVEL_ADVANCED));
    return expectedLevelsList;
  }
}
