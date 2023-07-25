package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class EditNewsItemScreen extends AbstractScreen {

  public static final String ATTRIBUTE_VALUE = "value";
  private Element editNewsItemHeader = Element.byCss(".create-news-item__header");
  private Element titleOfEnglishTabInputText = Element.byId("title-english");
  private Element titleOfRussianTabInputText = Element.byId("title-russian");
  private Element titleOfUkrainianTabInputText = Element.byId("title-ukranian");

  @Override
  public boolean isScreenLoaded() {
    return editNewsItemHeader.isDisplayed();
  }

  public EditNewsItemScreen waitScreenLoading() {
    editNewsItemHeader.waitForVisibility();
    return this;
  }

  public String getTitleOfEnglishTabInputTextValue() {
    return titleOfEnglishTabInputText.getAttributeValue(ATTRIBUTE_VALUE);
  }

  public String getTitleOfRussianTabInputTextValue() {
    return titleOfRussianTabInputText.getAttributeValue(ATTRIBUTE_VALUE);
  }

  public String getTitleOfUkrainianTabInputTextValue() {
    return titleOfUkrainianTabInputText.getAttributeValue(ATTRIBUTE_VALUE);
  }
}
