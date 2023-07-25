package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;

public class ReactSearchScreen extends AbstractScreen {

  private static final String SEARCH_RESULT_LINK_BY_EMAIL_PATTERN = "(//div[text()='%s']/parent::div/parent::div/preceding-sibling::*)[1]//a";

  private final Element findButton = Element
      .byXpath("//div[text()='Find']");
  private final Element searchInput = Element.byCss("input.uui-input");
  private final Element searchResultFullNames = Element.byXpath(
      "//a[contains(@href,'/UserProfile#!/Main')]/div/div");
  private final Element searchResultEmails = Element.byXpath(
      "(//a[contains(@href,'/UserProfile#!/Main')]/parent::div/parent::div)/div[3]/div/div");

  @Override
  public boolean isScreenLoaded() {
    return findButton.isDisplayed();
  }

  public ReactSearchScreen waitForScreenLoaded() {
    this.isScreenLoaded();
    return this;
  }

  public String getSearchInputPlaceholder() {
    return searchInput.getAttributeValue(PLACEHOLDER_CSS_PROPERTY);
  }

  public ReactSearchScreen enterSearchInputText(String input) {
    searchInput.type(input);
    return this;
  }

  public ReactSearchScreen clickFindButton() {
    findButton.click();
    return this;
  }

  public List<String> getSearchResultFullNames() {
    return searchResultFullNames.getElementsText();
  }

  public List<String> getSearchResultEmails() {
    return searchResultEmails.getElementsText();
  }

  public ProfileScreen clickSearchResultByEmail(String email) {
    Element.byXpath(String.format(SEARCH_RESULT_LINK_BY_EMAIL_PATTERN, email)).click();
    return new ProfileScreen();
  }

  public boolean isSearchInputDisplayed() {
    return searchInput.isDisplayed();
  }

  public boolean isFindButtonDisplayed() {
    return findButton.isDisplayed();
  }
}
