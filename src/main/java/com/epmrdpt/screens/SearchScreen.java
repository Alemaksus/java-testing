//TODO delete SearchScreen in case of successful smoke tests launch with ReactSearchScreen
//package com.epmrdpt.screens;
//
//import com.epmrdpt.framework.ui.AbstractScreen;
//import com.epmrdpt.framework.ui.element.Element;
//import java.util.List;
//
//public class SearchScreen extends AbstractScreen {
//
//  private static final String ATTRIBUTE_PLACEHOLDER = "placeholder";
//  private Element findButton = Element
//      .byXpath("//div[text()='Find']");
//  private Element searchInput = Element.byCss("input.uui-input");
//  private Element searchResultsByFullNameColumn = Element
//      .byXpath("(//a[contains(@href,'UserProfile#!/Main')])[1]");
//  private Element searchResultsByEmailColumn = Element
//      .byXpath("//div[contains(@class,'table-item') and contains(@class,'ng-scope')]/div[3]");
//
//  @Override
//  public boolean isScreenLoaded() {
//    return findButton.isDisplayed();
//  }
//
//  public SearchScreen waitForScreenLoaded() {
//    this.isScreenLoaded();
//    return this;
//  }
//
//  public String getSearchInputPlaceholder() {
//    return searchInput.getAttributeValue(ATTRIBUTE_PLACEHOLDER);
//  }
//
//  public SearchScreen enterSearchInputText(String input) {
//    searchInput.type(input);
//    return this;
//  }
//
//  public SearchScreen clickFindButton() {
//    findButton.click();
//    return this;
//  }
//
//  public List<String> getSearchResultsByFullNameColumn() {
//    return searchResultsByFullNameColumn.getElementsText();
//  }
//
//  public List<String> getSearchResultsByEmailColumn() {
//    return searchResultsByEmailColumn.getElementsText();
//  }
//
//  public ProfileScreen clickSearchResultByFullName() {
//    searchResultsByFullNameColumn.click();
//    return new ProfileScreen();
//  }
//}
