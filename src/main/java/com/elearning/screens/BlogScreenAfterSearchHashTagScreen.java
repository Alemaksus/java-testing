package com.epmrdpt.screens;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;

public class BlogScreenAfterSearchHashTagScreen extends AbstractScreen {

  private final static String HASH_TAG_IN_NEWS_CARDS_LOCATOR = ".//div/a[text()='%s']";
  private final static String TITLE_NEWS_CARDS_LOCATOR = "/../../following-sibling::a";
  private final static String HASH_TAG_LOCATOR = "//li/a[text()='%s']";

  private Element titleHashTag = Element.byCss("h1.news-banner__heading.ng-binding");
  private Element totalItems = Element.byXpath("//h1/following-sibling::div");
  private Element newsCardElement = Element.byXpath("//div[@class='news__list']/div");
  private Element showMoreLink = Element.byXpath("//span[text()='" + LocaleProperties.getValueOf(
      LocalePropertyConst.BLOG_SEARCH_BY_HASH_TAG_SECTION_SHOW_MORE) + "']");
  private Element showLessLink = Element.byXpath("//span[text()='" + LocaleProperties.getValueOf(
      LocalePropertyConst.BLOG_SEARCH_BY_HASH_TAG_SECTION_SHOW_LESS) + "']");
  private Element viewMoreNewsCards = Element.byXpath(
      "//button[@ng-click='viewMoreNews()']");
  private Element hashTagList = Element.byXpath("//ul[@class='news-hashtags__list']");

  public String getTitleHashTagText() {
    return titleHashTag.getText();
  }

  private Element getNewsCardHashTag(String hashTag) {
    return Element.byXpath(String.format(HASH_TAG_IN_NEWS_CARDS_LOCATOR, hashTag));
  }

  private List<Element> getAllNewsCardsOnPage() {
    return newsCardElement.getElements();
  }

  public int getNewsCardsCount() {
    return getAllNewsCardsOnPage().size();
  }

  public boolean isNewsCardByIndexHasHashTagOnHashTagScreen(String hashTag, int index) {
    return getAllNewsCardsOnPage().get(index).findChild(getNewsCardHashTag(hashTag)).isDisplayed();
  }

  public BlogDescriptionScreen clickArticleTittleByHashTag(String hashTag) {
    Element.byXpath(
            String.format(HASH_TAG_IN_NEWS_CARDS_LOCATOR, hashTag) + TITLE_NEWS_CARDS_LOCATOR)
        .click();
    return new BlogDescriptionScreen();
  }

  public BlogScreenAfterSearchHashTagScreen waitUntilPageLoaded() {
    totalItems.waitForVisibility();
    return this;
  }

  public BlogScreenAfterSearchHashTagScreen waitNewCardElementVisibility() {
    newsCardElement.waitForVisibility();
    return this;
  }

  @Override
  public boolean isScreenLoaded() {
    waitUntilPageLoaded();
    return totalItems.isDisplayed();
  }

  public BlogScreenAfterSearchHashTagScreen waitTitleTextPresent() {
    titleHashTag.waitForTextToBePresent();
    return this;
  }

  public boolean isShowMoreLinkDisplayed() {
    return showMoreLink.isDisplayed();
  }

  public boolean isShowLessLinkDisplayed() {
    return showLessLink.isDisplayed();
  }

  public BlogScreenAfterSearchHashTagScreen clickShowMoreLink() {
    showMoreLink.click();
    return this;
  }

  public boolean isHashTagLinkInHashTagListDisplayed(String hashTag) {
    return Element.byXpath(String.format(HASH_TAG_LOCATOR, hashTag)).isDisplayed();
  }

  public String getTotalItemsText() {
    return totalItems.getText();
  }

  public boolean isViewMoreLinkDisplayed() {
    return viewMoreNewsCards.isDisplayed();
  }

  public BlogScreenAfterSearchHashTagScreen clickViewMoreLink() {
    viewMoreNewsCards.click();
    return this;
  }

  public BlogScreenAfterSearchHashTagScreen waitForNewsCardsToLoadAfterViewMoreClicked(
      int previousNewsCardCount) {
    newsCardElement.waitNumberOfElementsToBeMoreThan(previousNewsCardCount);
    return this;
  }

  public boolean isHashTagListDisplayed() {
    return hashTagList.isDisplayed();
  }

  public BlogDescriptionScreen clickOnNewsCardByIndex(int index) {
    getAllNewsCardsOnPage().get(index).click();
    return new BlogDescriptionScreen();
  }
}

