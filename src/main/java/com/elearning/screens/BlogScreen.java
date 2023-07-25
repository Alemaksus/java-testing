package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.html.CSS;
import javax.swing.text.html.HTML.Attribute;
import org.openqa.selenium.WebElement;

public class BlogScreen extends AbstractScreen {

  private static final String HASH_TAG = "#";
  private static final String VIEW_LIKE_ICON_PSEUDO_ELEMENT = "content";
  private static final String SEARCH_HASH_TAG_LOCATOR_PATTERN = "//a[text() = '%s']";
  private static final String ARTICLE_CARD_BY_NUMBER_LOCATOR =
      "//div[contains(@class,'news__list-item')][contains(@class,'ng-scope')][%s]";
  private static final String HASH_TAG_LOCATOR = "//div[contains(@class,'news__item-hashtags')]";
  private static final String CATEGORY_LOCATOR = "//div[contains(@class,'news__item-category')]";
  private static final String DATE_CREATING_CARD_LOCATOR =
      "//div[contains(@class,'news__item-date')]";
  private static final String VIEW_COUNTER_NUMBER_LOCATOR =
      "//div[contains(@class,'news__item-views')]";
  private static final String LIKE_COUNTER_NUMBER_LOCATOR =
      "//div[contains(@class,'news__item-likes')]";
  private static final String VIEW_COUNTER_ICON_LOCATOR =
      "//div[contains(@class,'news__item-views')][contains(@class,'ng-binding')]";
  private static final String LIKE_COUNTER_ICON_LOCATOR =
      "//div[contains(@class,'news__item-likes')][contains(@class,'ng-binding')]";
  private static final String ARTICLE_DESCRIPTION_LOCATOR =
      "//div[contains(@class,'news__item-description')]";
  private static final String ARTICLE_TITLE_LOCATOR = "//a[contains(@class,'news__item-title')]";
  private static final String PREVIEW_IMAGE_LOCATOR = "//div[contains(@class,'news__item-image')]";
  private static final String TAB_TEXT_LOCATOR = "//span[text()='%s']";
  private static final String ATTRIBUTE_SRC = Attribute.SRC.toString();
  private static final String SEARCH_ARTICLE_TITLE_PATTERN =
      "//a[contains(@class,'news__item-title') and contains(text(),'%s')]";
  private Element blogPageTitle = Element.byCss("div.container.ng-scope > h1");
  private Element newsTab = Element.byXpath("//a[@data-id='1']");
  private Element realStoriesTab = Element.byXpath("//a[@data-id='2']");
  private Element materialsTab = Element.byXpath("//a[@data-id='3']");
  private Element hardSkillsTab = Element.byXpath("//a[@data-id='6']");
  private Element softSkillsTab = Element.byXpath("//a[@data-id='7']");
  private Element eventsTab = Element.byXpath("//a[@data-id='8']");
  private Element viewMoreLink = Element.byXpath(
      "//div[not(contains(@class,'ng-hide'))]/button[contains(@ng-click,'viewMoreNews')]");
  private Element viewMoreLinkWithHideClass = Element.byCss("div.news__view-more.ng-hide > button");
  private Element bannerText = Element.byCss("div.news-banner__heading.ng-binding");
  private Element articleTitle = Element.byXpath(ARTICLE_TITLE_LOCATOR);
  private Element readButton = Element.byCss("div.news-banner__button a");
  private Element previewImage = Element
      .byXpath("//div[contains(@class,'news__item-image-holder')]");
  private Element homeLink = Element.byCss("li.breadcrumb__item.ng-scope a");
  private Element banner = Element.byCss("div.news-banner.ng-scope");
  private Element articleCard = Element
      .byXpath("//div[contains(@class,'news__list-item')][contains(@class,'ng-scope')]");
  private Element searchArticleCardsLocator = Element.byXpath(
      "//div[contains(@class,'news__list-item')][contains(@class,'ng-scope')]");
  private Element hashTagLink = Element.byXpath("//a[contains(@class,'news__item-hashtag')]");
  private Element articleCardWithHashTagLink = Element.byXpath(HASH_TAG_LOCATOR +
      "/ancestor::div[contains(@class,'news__list-item')][contains(@class,'ng-scope')]");
  private ArrayList<String> articleCardsWithoutHashTagList;
  private Element articleTitleElement = Element.byXpath(".//div[@class='news__item-info']/a");
  private Element articleWithNoImage = Element.byXpath(
      "//a[@class='news__item-title ng-binding' and contains(text(),'No Image')]");
  private Element previewImageOfArticleWithNoImage = Element
      .byXpath("//a[contains(text(),'No Image')]/parent::div/parent::div//img");
  private Element articleWithSeveralImages = Element
      .byXpath(
          "//a[@class='news__item-title ng-binding' and contains(text(),'Abstract Painting')]");
  private Element previewImageOfArticleWithSeveralImages = Element
      .byXpath("//a[contains(text(),'Abstract Painting')]/parent::div/parent::div//img");
  private Element blogTab = Element.byXpath("//*[contains(@class,'tab-nav__item')]");
  private Element activeBlogTab = Element.byCss("a.tab-nav__item[class*='active']");
  private Element viewIcon = Element.byXpath(VIEW_COUNTER_ICON_LOCATOR);
  private Element hashTags = Element.byXpath("//ul[contains(@class,'news-hashtags__list')]");
  private Element footer = Element.byXpath("//*[@id='footer']");
  private Element skillsBlock = Element.byXpath(
      "//section[@class='container section-ui ng-scope']");
  private Element skillsCard = Element.byXpath("//a[@class='skill-item ng-scope']");
  private Element skillsIcon = Element.byXpath("//img[@class='skill-item__icon']");
  private Element skillsName = Element.byXpath("//div[@class='skill-item__title ng-binding']");
  private Element showMoreButton = Element.byXpath("//span[@ng-click='toggleHashtags()']");
  private Element allHashTags = Element.byXpath("//li[contains(@class,'hashtag')]");

  public String getBlogPageTitleText() {
    return blogPageTitle.getText();
  }

  public BlogScreenAfterSearchHashTagScreen clickHashTagByText(String tagText) {
    Element.byXpath(String.format(SEARCH_HASH_TAG_LOCATOR_PATTERN, tagText)).mouseOverAndClick();
    return new BlogScreenAfterSearchHashTagScreen();
  }

  public HeaderScreen clickHomeLink() {
    homeLink.mouseOver();
    homeLink.click();
    return new HeaderScreen();
  }

  @Override
  public boolean isScreenLoaded() {
    Element[] awaitedElement = getAwaitedElements();
    for (Element element : awaitedElement) {
      element.waitForVisibility();
      if (!(element.isDisplayed())) {
        return false;
      }
    }
    return true;
  }

  private Element[] getAwaitedElements() {
    return new Element[]{blogPageTitle, readButton, bannerText, previewImage};
  }

  public boolean isBlogTitleDisplayed() {
    return blogPageTitle.isDisplayed();
  }

  public String getFromNewsTabText() {
    return newsTab.getText();
  }

  public String getRealStoriesTabText() {
    return realStoriesTab.getText();
  }

  public String getMaterialsTabText() {
    return materialsTab.getText();
  }

  public String getHardSkillsTabText() {
    return hardSkillsTab.getText();
  }

  public String getSoftSkillsTabText() {
    return softSkillsTab.getText();
  }

  public String getEventsTabText() {
    return eventsTab.getText();
  }

  public BlogScreen waitPreviewImageDisplayed() {
    previewImage.waitForVisibility();
    return this;
  }

  public boolean isHomeLinkDisplayed() {
    return homeLink.isDisplayed();
  }

  public boolean isBannerDisplayed() {
    return banner.isDisplayed();
  }

  public String getBannerText() {
    return bannerText.getText();
  }

  private List<Element> getArticleTitleList() {
    return articleTitle.getElements();
  }

  public String getArticleTitleText() {
    return articleTitle.getText();
  }

  public int getViewIconCountByIndex(int index) {
    return Integer.parseInt(viewIcon.getElements().get(index).getText());
  }

  private List<Element> getPreviewImageList() {
    return previewImage.getElements();
  }

  public String getArticleTitleTextByIndex(int index) {
    return getArticleTitleList().get(index).getText();
  }

  public BlogDescriptionScreen clickArticleTitleByIndex(int index) {
    getArticleTitleList().get(index).click();
    return new BlogDescriptionScreen();
  }

  public List<String> getArticleTitleTextList() {
    return articleTitle.getElementsText();
  }

  public BlogDescriptionScreen clickPreviewImageByIndex(int index) {
    getPreviewImageList().get(index).click();
    return new BlogDescriptionScreen();
  }

  public String getReadButtonText() {
    return readButton.waitForVisibility().getText();
  }

  private List<Element> getArticleCardsList() {
    return articleCard.getElements();
  }

  public int getSizeArticleCardsList() {
    return getArticleCardsList().size();
  }

  public BlogScreen waitArticleCardsListAfterClickViewMoreButton(int cardsCount) {
    searchArticleCardsLocator.waitNumberOfElementsToBeMoreThan(cardsCount);
    return new BlogScreen();
  }

  public BlogScreen clickViewMoreLink() {
    viewMoreLink.mouseOver();
    viewMoreLink.click();
    return this;
  }

  private List<String> getArticleCardsWithoutHashTagList() {
    articleCardsWithoutHashTagList = new ArrayList<>();
    for (int articleCardNumber = 1; articleCardNumber <= getSizeArticleCardsList();
        articleCardNumber++) {
      if (!Element.byXpath((String.format(ARTICLE_CARD_BY_NUMBER_LOCATOR, articleCardNumber)
          + HASH_TAG_LOCATOR)).isDisplayedNoWait()) {
        articleCardsWithoutHashTagList
            .add(String.format(ARTICLE_CARD_BY_NUMBER_LOCATOR, articleCardNumber));
      }
    }
    return articleCardsWithoutHashTagList;
  }

  public boolean isHashTagLinkDisplayedByText(String hashtag) {
    return getHashTagList().contains(hashtag);
  }

  public int getArticleCardsWithoutHashTagCount() {
    return getArticleCardsWithoutHashTagList().size();
  }

  private WebElement getChildArticleWithoutHashTagAndShortTitleByIndex(int index, String pattern) {
    return getArticleCardsWithoutHashTagAndShortTitleList()
        .get(index)
        .findChild(Element.byXpath("." + pattern));
  }

  private WebElement getChildArticleWithHashTagAndShortTitleByIndex(int index, String pattern) {
    return getArticleCardsWithHashTagAndShortTitleList()
        .get(index)
        .findChild(Element.byXpath("." + pattern));
  }

  public boolean isArticleCardWithoutHashTagAndShortTitleByIndexHasPreviewImage(int index) {
    return getChildArticleWithoutHashTagAndShortTitleByIndex(index, PREVIEW_IMAGE_LOCATOR)
        .isDisplayed();
  }

  public boolean isArticleCardWithoutHashTagAndShortTitleByIndexHasCategory(int index) {
    return getChildArticleWithoutHashTagAndShortTitleByIndex(index, CATEGORY_LOCATOR).isDisplayed();
  }

  public boolean isArticleCardWithoutHashTagShortTimeByIndexHasDate(int index) {
    return getChildArticleWithoutHashTagAndShortTitleByIndex(index, DATE_CREATING_CARD_LOCATOR)
        .isDisplayed();
  }

  public boolean isArticleCardWithoutHashTagAndShortTitleByIndexHasViewCounter(int index) {
    return getChildArticleWithoutHashTagAndShortTitleByIndex(index, VIEW_COUNTER_NUMBER_LOCATOR)
        .isDisplayed();
  }

  public boolean isArticleCardWithoutHashTagAndShortTitleByIndexHasLikeCounter(int index) {
    return getChildArticleWithoutHashTagAndShortTitleByIndex(index, LIKE_COUNTER_NUMBER_LOCATOR)
        .isDisplayed();
  }

  public boolean isArticleCardWithoutHashTagAndShortTitleByIndexHasTitle(int index) {
    return getChildArticleWithoutHashTagAndShortTitleByIndex(index, ARTICLE_TITLE_LOCATOR)
        .isDisplayed();
  }

  public boolean isArticleCardWithoutHashTagAndShowTitleByIndexHasDescription(int index) {
    return getChildArticleWithoutHashTagAndShortTitleByIndex(index, ARTICLE_DESCRIPTION_LOCATOR)
        .isDisplayed();
  }

  public boolean isArticleCardWithoutHashTagAndShortTimeByIndexHasViewCounterIcon(int index) {
    return getChildArticleWithoutHashTagAndShortTitleByIndex(index, VIEW_COUNTER_ICON_LOCATOR)
        .getCssValue(VIEW_LIKE_ICON_PSEUDO_ELEMENT).isEmpty();
  }

  public boolean isArticleCardWithoutHashTagAndShortTitleByIndexHasLikeCounterIcon(int index) {
    return getChildArticleWithoutHashTagAndShortTitleByIndex(index, LIKE_COUNTER_ICON_LOCATOR)
        .getCssValue(VIEW_LIKE_ICON_PSEUDO_ELEMENT).isEmpty();
  }

  public boolean isArticleCardWithHashTagAndShortTitleByIndexHasPreviewImage(int index) {
    return getChildArticleWithHashTagAndShortTitleByIndex(index,
        PREVIEW_IMAGE_LOCATOR).isDisplayed();
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

  public boolean isArticleCardWithHashTagShortTimeByIndexHasDate(int index) {
    return getChildArticleWithHashTagAndShortTitleByIndex(index,
        DATE_CREATING_CARD_LOCATOR).isDisplayed();
  }

  public boolean isArticleCardWithHashTagAndShortTimeByIndexHasViewCounterIcon(int index) {
    return getChildArticleWithHashTagAndShortTitleByIndex(index, VIEW_COUNTER_ICON_LOCATOR)
        .getCssValue(VIEW_LIKE_ICON_PSEUDO_ELEMENT).isEmpty();
  }

  public boolean isArticleCardWithHashTagAndShortTitleByIndexHasViewCounter(int index) {
    return getChildArticleWithHashTagAndShortTitleByIndex(index,
        VIEW_COUNTER_NUMBER_LOCATOR).isDisplayed();
  }

  public boolean isArticleCardWithHashTagAndShortTitleByIndexHasLikeCounterIcon(int index) {
    return getChildArticleWithHashTagAndShortTitleByIndex(index, LIKE_COUNTER_ICON_LOCATOR)
        .getCssValue(VIEW_LIKE_ICON_PSEUDO_ELEMENT).isEmpty();
  }

  public boolean isArticleCardWithHashTagAndShortTitleByIndexHasLikeCounter(int index) {
    return getChildArticleWithHashTagAndShortTitleByIndex(index, LIKE_COUNTER_NUMBER_LOCATOR)
        .isDisplayed();
  }

  public boolean isArticleCardWithHashTagAndShortTitleByIndexHasTitle(int index) {
    return getChildArticleWithHashTagAndShortTitleByIndex(index, ARTICLE_TITLE_LOCATOR)
        .isDisplayed();
  }

  public boolean isArticleCardWithHashTagAndShowTitleByIndexHasDescription(int index) {
    return getChildArticleWithHashTagAndShortTitleByIndex(index, ARTICLE_DESCRIPTION_LOCATOR)
        .isDisplayed();
  }

  public boolean isViewMoreLinkWithHideClassPresent() {
    return viewMoreLinkWithHideClass.isPresent();
  }

  public boolean isViewMoreLinkDisplayed() {
    return viewMoreLink.isDisplayed();
  }

  public String getViewMoreLinkColor() {
    return viewMoreLink.getCssValue(CSS.Attribute.COLOR.toString());
  }

  public String getHoveredViewMoreLinkColor() {
    return viewMoreLink.getHoveredCssValue(CSS.Attribute.COLOR.toString());
  }

  private List<Element> getAllCategoryFromCardsList() {
    return Element.byXpath(CATEGORY_LOCATOR).getElements();
  }

  public BlogScreen clickTabByCategoryName(String categoryName) {
    Element.byXpath(String.format(TAB_TEXT_LOCATOR, categoryName)).click();
    return new BlogScreen();
  }

  public BlogDescriptionScreen clickArticleByTitle(String articleTitle) {
    Element.byXpath(String.format(SEARCH_ARTICLE_TITLE_PATTERN, articleTitle)).click();
    return new BlogDescriptionScreen();
  }

  public boolean isAllCardsHasRequiredCategory(String categoryTypeName) {
    List<Element> allArticleCards = getAllCategoryFromCardsList();
    return allArticleCards.stream()
        .allMatch(categoryByCard -> categoryByCard.getText().contentEquals(categoryTypeName));
  }

  public boolean isReadButtonDisplayed() {
    return readButton.isDisplayed();
  }

  public BlogDescriptionScreen clickReadButton() {
    readButton.click();
    return new BlogDescriptionScreen();

  }

  public List<String> getHashTagList() {
    List<String> hashTagList = new ArrayList<>();
    for (Element element : hashTagLink.getElements()) {
      if (element.isDisplayed()) {
        hashTagList.add(element.getText());
      }
    }
    return hashTagList;
  }

  private List<Element> getElementsWithShortTitleList(List<Element> articleCardsList) {
    List<Element> allArticleWithHashTagWithShortTitle = new ArrayList<>();
    for (Element article : articleCardsList) {
      if (article.findChild(articleTitleElement).getText().length() <= 70) {
        allArticleWithHashTagWithShortTitle.add(article);
      }
    }
    return allArticleWithHashTagWithShortTitle;
  }

  private List<Element> getArticleCardsWithoutHashTagAndShortTitleList() {
    return getElementsWithShortTitleList(getAllArticleWithoutHashTag());
  }

  public int getArticlesWithoutHashTagWithShortTitleQuantity() {
    return getArticleCardsWithoutHashTagAndShortTitleList().size();
  }

  private List<Element> getAllArticleWithoutHashTag() {
    List<Element> articleCardsWithoutHashTagList = new ArrayList<>();
    for (int articleCardNumber = 1; articleCardNumber <= getSizeArticleCardsList();
        articleCardNumber++) {
      if (!Element.byXpath((String.format(ARTICLE_CARD_BY_NUMBER_LOCATOR, articleCardNumber)
          + HASH_TAG_LOCATOR)).isDisplayedNoWait()) {
        articleCardsWithoutHashTagList.add(getArticleCardsList().get(articleCardNumber - 1));
      }
    }
    return articleCardsWithoutHashTagList;
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

  public int getArticleTitleListSize() {
    return articleTitle.getElements().size();
  }

  public BlogScreen waitArticleWithSeveralImagesVisibility() {
    articleWithSeveralImages.waitForVisibility();
    return this;
  }

  public BlogScreen clickArticleWithSeveralImages() {
    articleWithSeveralImages.click();
    return this;
  }

  public String getArticlePreviewImageSrc() {
    return previewImageOfArticleWithSeveralImages.getAttributeValue(ATTRIBUTE_SRC);
  }

  public BlogScreen waitArticleWithNoImageVisibility() {
    articleWithNoImage.waitForVisibility();
    return this;
  }

  public BlogScreen clickArticleWithNoImage() {
    articleWithNoImage.click();
    return this;
  }

  public String getNoImageArticlePreviewImageSrc() {
    return previewImageOfArticleWithNoImage.getAttributeValue(ATTRIBUTE_SRC);
  }

  public boolean isArticleWithNoImageDisplayed() {
    return articleWithNoImage.isDisplayed();
  }

  public List<String> getDisplayedTabNameTexts() {
    return blogTab.getElementsText();
  }

  public String getBlogPageTitleColor() {
    return blogPageTitle.getCssValue(CSS.Attribute.COLOR.toString());
  }

  public String getActiveBlogTabColor() {
    return activeBlogTab.getCssValue(CSS.Attribute.COLOR.toString());
  }

  public String getActiveBlogTabCategory() {
    return activeBlogTab.getText();
  }

  public boolean isHashTagsTextDisplayed() {
    return hashTags.isDisplayed();
  }

  public boolean isSkillsBlockIsPlacedAboveFooter() {
    return footer.getWrappedWebElement().getLocation().getY()
        > skillsBlock.getWrappedWebElement().getLocation().getY();
  }

  public boolean isHashTagsTextUnderBlog() {
    return hashTags.getWrappedWebElement().getLocation().getY()
        > blogPageTitle.getWrappedWebElement().getLocation().getY();
  }

  public int getSkillCardCount() {
    return skillsCard.getElements().size();
  }

  public boolean isSkillIconDisplayed() {
    return skillsIcon.isDisplayed();
  }

  public boolean isSkillNameDisplayed() {
    String textSkillsName = skillsName.getText();
    return textSkillsName.length() > 0;
  }

  public BlogScreen clickShowMoreButton() {
    showMoreButton.click();
    return this;
  }

  private List<Element> getAllHashTagsAtTheTopOfTheScreen() {
    return allHashTags.getElements();
  }

  public BlogScreenAfterSearchHashTagScreen clickHashTagByIndex(int index) {
    getAllHashTagsAtTheTopOfTheScreen().get(index).click();
    return new BlogScreenAfterSearchHashTagScreen();
  }

  public int getAllHashTagsAtTheTopOfTheScreenCount() {
    return getAllHashTagsAtTheTopOfTheScreen().size();
  }
}
