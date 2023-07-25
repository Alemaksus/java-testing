package com.epmrdpt.screens;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.time.LocalDate;

public class CreateNewsItemScreen extends AbstractScreen {

  private static final String CHOOSE_LANGUAGE_LOCATOR = "//div[contains(text(),'%s')]";
  private static final String DROPDOWN_VALUE_LOCATOR = "//li[text()='%s']";

  private Element categoryDropDown = Element.byId("category_chosen");
  private Element statusDropDown = Element.byId("statusId_chosen");
  private Element dateOfPublicationInputBox = Element.byXpath("//input[@ng-model='calendarDate']");
  private Element titleInEnglish = Element.byId("title-english");
  private Element introductionInEnglish = Element
      .byXpath("//div[@id='introduction-english']/div[contains(@class,'ql-editor')]/p");
  private Element descriptionInEnglish = Element
      .byXpath("//div[@id='editorEnglish']/div[contains(@class,'ql-editor')]/p");
  private Element titleInRussian = Element.byId("title-russian");
  private Element introductionInRussian = Element
      .byXpath("//div[@id='introduction-russian']/div[contains(@class,'ql-editor')]/p");
  private Element descriptionInRussian = Element
      .byXpath("//div[@id='editorRussian']/div[contains(@class,'ql-editor')]/p");
  private Element titleInUkrainian = Element.byId("title-ukranian");
  private Element introductionInUkrainian = Element
      .byXpath("//div[@id='introduction-ukranian']/div[contains(@class,'ql-editor')]/p");
  private Element descriptionInUkrainian = Element
      .byXpath("//div[@id='editorUkranian']/div[contains(@class,'ql-editor')]/p");
  private Element createNewsItemButton = Element
      .byXpath("//*[contains(@class,'create-news-item__create')]");
  private Element cancelNewsItemButton = Element
      .byXpath("//*[contains(@class,'create-news-item__cancel')]");
  private Element saveNewsItemButton = Element.byXpath(String.format(
      "//div[contains(@class,'create-news-item')]//*[contains(text(),'%1$s') or @value='%1$s']",
      LocaleProperties.getValueOf(LocalePropertyConst.CREATE_NEWS_ITEM_SAVE)));
  private Element linkToVideoField = Element.byId("link-to-video");

  @Override
  public boolean isScreenLoaded() {
    return categoryDropDown.isDisplayed();
  }

  public CreateNewsItemScreen waitCreateNewsScreenIsLoaded() {
    createNewsItemButton.waitForVisibility();
    return this;
  }

  public CreateNewsItemScreen clickCategoryDropDown() {
    categoryDropDown.click();
    return this;
  }

  public CreateNewsItemScreen clickStatusDropDown() {
    statusDropDown.click();
    return this;
  }

  public CreateNewsItemScreen clickValueFromDropDownValues(String valueName) {
    Element.byXpath(DROPDOWN_VALUE_LOCATOR, valueName).click();
    return this;
  }

  public CreateNewsItemScreen typeDateOfPublication(LocalDate date) {
    dateOfPublicationInputBox.type(date.toString());
    return this;
  }

  public CreateNewsItemScreen typeTitleInEnglish(String title) {
    titleInEnglish.type(title);
    return this;
  }

  public CreateNewsItemScreen typeIntroductionInEnglish(String introduction) {
    introductionInEnglish.click();
    introductionInEnglish.type(introduction);
    return this;
  }

  public CreateNewsItemScreen typeDescriptionInEnglish(String description) {
    descriptionInEnglish.click();
    descriptionInEnglish.type(description);
    return this;
  }

  public CreateNewsItemScreen typeTitleInRussian(String title) {
    titleInRussian.type(title);
    return this;
  }

  public CreateNewsItemScreen typeIntroductionInRussian(String introduction) {
    introductionInRussian.click();
    introductionInRussian.type(introduction);
    return this;
  }

  public CreateNewsItemScreen typeDescriptionInRussian(String description) {
    descriptionInRussian.click();
    descriptionInRussian.type(description);
    return this;
  }

  public CreateNewsItemScreen typeTitleInUkrainian(String title) {
    titleInUkrainian.type(title);
    return this;
  }

  public CreateNewsItemScreen typeIntroductionInUkrainian(String introduction) {
    introductionInUkrainian.click();
    introductionInUkrainian.type(introduction);
    return this;
  }

  public CreateNewsItemScreen typeDescriptionInUkrainian(String description) {
    descriptionInUkrainian.click();
    descriptionInUkrainian.type(description);
    return this;
  }

  public CreateNewsItemScreen selectEnglishLanguage() {
    Element.byXpath(CHOOSE_LANGUAGE_LOCATOR,
            LocaleProperties.getValueOf(LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_ENGLISH))
        .clickJs();
    return this;
  }

  public CreateNewsItemScreen selectRussianLanguage() {
    Element.byXpath(CHOOSE_LANGUAGE_LOCATOR,
            LocaleProperties.getValueOf(LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN))
        .clickJs();
    return this;
  }

  public CreateNewsItemScreen selectUkrainianLanguage() {
    Element.byXpath(CHOOSE_LANGUAGE_LOCATOR,
            LocaleProperties.getValueOf(LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN))
        .clickJs();
    return this;
  }

  public CreateNewsItemScreen clickCreateNewsButton() {
    createNewsItemButton.clickJs();
    return this;
  }

  public NewsManagementScreen clickCancelNewsItemButton() {
    cancelNewsItemButton.clickJs();
    return new NewsManagementScreen();
  }

  public CreateNewsItemScreen waitSaveNewsItemVisibility() {
    saveNewsItemButton.waitForVisibility();
    return this;
  }

  public CreateNewsItemScreen typeLinkToVideo(String linkToVideo) {
    linkToVideoField.type(linkToVideo);
    return this;
  }
}
