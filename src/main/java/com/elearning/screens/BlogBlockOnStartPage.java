package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.html.CSS;
import org.openqa.selenium.WebElement;

public class BlogBlockOnStartPage extends AbstractScreen {

  private static final int MAX_LENGTH_SHORT_TITLE = 70;
  private static final String HASH_TAG = "#";
  private static final String ATTRIBUTE_TEXT = "text";
  private static final String VIEW_LIKE_ICON_PSEUDO_ELEMENT = "content";
  private static final String HASH_TAG_LOCATOR = "//div[contains(@class,'news__item-hashtags')]";
  private static final String PREVIEW_IMAGE_LOCATOR = "//div[contains(@class,'news__item-image')]";
  private static final String CATEGORY_LOCATOR = "//div[contains(@class,'news__item-category')]";
  private static final String DATE_CREATING_CARD_LOCATOR =
      "//div[contains(@class,'news__item-date')]";
  private static final String VIEW_COUNTER_NUMBER_LOCATOR =
      "//div[contains(@class,'news__item-views')]";
  private static final String VIEW_COUNTER_ICON_LOCATOR = VIEW_COUNTER_NUMBER_LOCATOR
      + "[contains(@class,'ng-binding')]";
  private static final String LIKE_COUNTER_NUMBER_LOCATOR =
      "//div[contains(@class,'news__item-likes')]";
  private static final String LIKE_COUNTER_ICON_LOCATOR = LIKE_COUNTER_NUMBER_LOCATOR
      + "[contains(@class,'ng-binding')]";
  private static final String ARTICLE_TITLE_LOCATOR = "//a[contains(@class,'news__item-title')]";
  private static final String ARTICLE_INTRODUCTION_LOCATOR =
      "//div[contains(@class,'news__item-description')]";
  private Element blogTittle = Element.byCss("div.section-ui__title.ng-binding");
  private Element newsLink = Element.byXpath("//div[@data-id='1']");
  private Element storiesLink = Element.byXpath("//div[@data-id='2']");
  private Element materialsLink = Element.byXpath("//div[@data-id='3']");
  private Element hardSkillsLink = Element.byXpath("//div[@data-id='6']");
  private Element softSkillsLink = Element.byXpath("//div[@data-id='7']");
  private Element leftArrowButton = Element.byCss("section.news-list button.slider-arrow--prev");
  private Element articleCardsList = Element.byXpath("//*[contains(@class,'news-list')]"
      + "//descendant::div[contains(@class,'owl-item')][contains(@class,'active')]");
  private Element rightArrowButton = Element.byCss("section.news-list button.slider-arrow--next");
  private Element allArticlesLink = Element.byClassName("news-list__all-news-link");
  private Element titleOfArticle =
      Element.byXpath("//div[@class ='news__item-info']/a");
  private Element articleImage = Element.byXpath("//img[@class='news__item-image']");
  private Element articleTitleElement = Element.byXpath(".//div[@class='news__item-info']/a");
  private Element articleCardWithHashTagLink = Element.byXpath(HASH_TAG_LOCATOR +
      "/ancestor::div[contains(@class,'owl-item')]");

  public boolean isScreenLoaded() {
    return isAllArticlesLinkDisplayed();
  }

  public boolean isBlogTittleDisplayed() {
    return blogTittle.isDisplayed();
  }

  public boolean isNewsLinkDisplayed() {
    return newsLink.isDisplayed();
  }

  public boolean isStoriesLinkDisplayed() {
    return storiesLink.isDisplayed();
  }

  public boolean isMaterialsLinkDisplayed() {
    return materialsLink.isDisplayed();
  }

  public boolean isHardSkillsLinkDisplayed() {
    return hardSkillsLink.isDisplayed();
  }

  public boolean isSoftSkillsLinkDisplayed() {
    return softSkillsLink.isDisplayed();
  }

  public boolean isArticlesCardsListDisplayed() {
    return articleCardsList.isAllElementsDisplayed();
  }

  public boolean isAllArticlesLinkDisplayed() {
    return allArticlesLink.isDisplayed();
  }

  public BlogScreen clickAllArticlesLink() {
    allArticlesLink.mouseOver();
    allArticlesLink.click();
    return new BlogScreen();
  }

  public BlogBlockOnStartPage waitScreenLoading() {
    allArticlesLink.waitForVisibility();
    return this;
  }

  public String getAllArticlesLinkColor() {
    return allArticlesLink.getCssValue(CSS.Attribute.COLOR.toString());
  }

  public String getHoveredAllArticlesLinkColor() {
    return allArticlesLink.getHoveredCssValue(CSS.Attribute.COLOR.toString());
  }

  private List<Element> getArticlesTitleLinksList() {
    return titleOfArticle.getElements();
  }

  private Element getArticleElementByIndex(int articleIndex) {
    List<Element> articlesList = getArticlesTitleLinksList();
    new FooterScreen().scrollToFooter();
    for (int i = 0; i <= articlesList.size(); i++) {
      if (!articlesList.get(articleIndex).isDisplayed()) {
        clickLeftArrowButton();
      }
    }
    return articlesList.get(articleIndex);
  }

  public void clickLeftArrowButton() {
    leftArrowButton.click();
  }

  private void clickRightArrowButton() {
    rightArrowButton.click();
  }

  public BlogDescriptionScreen clickArticleByIndex(int articleIndex) {
    getArticleElementByIndex(articleIndex).click();
    return new BlogDescriptionScreen();
  }

  public String getArticleTitleTextByIndex(int articleIndex) {
    return getArticleElementByIndex(articleIndex).getText();
  }

  public int getArticleTitlesCount() {
    return getArticlesTitleLinksList().size();
  }

  public BlogBlockOnStartPage waitArticleImageVisibility() {
    articleImage.waitForVisibility();
    return this;
  }

  private List<Element> getArticleImageLinksList() {
    return articleImage.getElements();
  }

  public BlogDescriptionScreen clickPreviewImageByIndex(int index) {
    getArticleImageLinksList().get(index).mouseOver();
    getArticleImageLinksList().get(index).click();
    return new BlogDescriptionScreen();
  }

  private List<Element> getElementsWithShortTitleList(List<Element> articleCardsList) {
    List<Element> allArticleWithHashTagWithShortTitle = new ArrayList<>();
    for (Element article : articleCardsList) {
      if (article.findChild(articleTitleElement)
          .getAttribute(ATTRIBUTE_TEXT).length() <= MAX_LENGTH_SHORT_TITLE) {
        allArticleWithHashTagWithShortTitle.add(article);
      }
    }
    return allArticleWithHashTagWithShortTitle;
  }

  private List<Element> getArticleCardsWithHashTagAndShortTitleList() {
    return getElementsWithShortTitleList(getAllArticleCardsWithHashTag());
  }

  public int getArticlesWithHashTagWithShortTitleQuantity() {
    return getArticleCardsWithHashTagAndShortTitleList().size();
  }

  private List<Element> getAllArticleCardsWithHashTag() {
    return articleCardWithHashTagLink.getElements();
  }

  private WebElement getChildArticleWithHashTagAndShortTitleByIndex(int index, String pattern) {
    return getArticleCardsWithHashTagAndShortTitleList()
        .get(index)
        .findChild(Element.byXpath("." + pattern));
  }

  public void clickRightArrowButtonIfIsNotDisplayedArticleCard(int index) {
    int clickAttempt = 0;
    while (!getArticleCardsWithHashTagAndShortTitleList().get(index).isDisplayedNoWait()
        && clickAttempt <= getArticlesWithHashTagWithShortTitleQuantity()) {
      clickRightArrowButton();
      clickAttempt++;
    }
  }

  public boolean isArticleCardWithHashTagAndShortTitleByIndexHasPreviewImage(int index) {
    return getChildArticleWithHashTagAndShortTitleByIndex(index, PREVIEW_IMAGE_LOCATOR)
        .isDisplayed();
  }

  public boolean isArticleCardWithHashTagAndShortTitleByIndexHasHashTag(int index) {
    return getChildArticleWithHashTagAndShortTitleByIndex(index, HASH_TAG_LOCATOR).isDisplayed();
  }

  public boolean isArticleCardWithHashTagAndShortTitleByIndexHasHashTagText(int index) {
    return getChildArticleWithHashTagAndShortTitleByIndex(index, HASH_TAG_LOCATOR)
        .getText().contains(HASH_TAG);
  }

  public boolean isArticleCardWithHashTagAndShortTitleByIndexHasCategory(int index) {
    return getChildArticleWithHashTagAndShortTitleByIndex(index, CATEGORY_LOCATOR).isDisplayed();
  }

  public boolean isArticleCardWithHashTagAndShortTitleByIndexHasDate(int index) {
    return getChildArticleWithHashTagAndShortTitleByIndex(index, DATE_CREATING_CARD_LOCATOR)
        .isDisplayed();
  }

  public String getViewCounterIconOnArticleCardByIndexValue(int index) {
    return getChildArticleWithHashTagAndShortTitleByIndex(index, VIEW_COUNTER_ICON_LOCATOR)
        .getCssValue(VIEW_LIKE_ICON_PSEUDO_ELEMENT);
  }

  public boolean isArticleCardWithHashTagAndShortTitleByIndexHasViewCounter(int index) {
    return getChildArticleWithHashTagAndShortTitleByIndex(index, VIEW_COUNTER_NUMBER_LOCATOR)
        .isDisplayed();
  }

  public String getLikeCounterIconOnArticleCardByIndexValue(int index) {
    return getChildArticleWithHashTagAndShortTitleByIndex(index, LIKE_COUNTER_ICON_LOCATOR)
        .getCssValue(VIEW_LIKE_ICON_PSEUDO_ELEMENT);
  }

  public boolean isArticleCardWithHashTagAndShortTitleByIndexHasLikeCounter(int index) {
    return getChildArticleWithHashTagAndShortTitleByIndex(index, LIKE_COUNTER_NUMBER_LOCATOR)
        .isDisplayed();
  }

  public boolean isArticleCardWithHashTagAndShortTitleByIndexHasTitle(int index) {
    return getChildArticleWithHashTagAndShortTitleByIndex(index, ARTICLE_TITLE_LOCATOR)
        .isDisplayed();
  }

  public boolean isArticleCardWithHashTagAndShortTitleByIndexHasIntroduction(int index) {
    return getChildArticleWithHashTagAndShortTitleByIndex(index, ARTICLE_INTRODUCTION_LOCATOR)
        .isDisplayed();
  }
}
