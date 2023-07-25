package com.epmrdpt.screens;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;
import javax.swing.text.html.HTML.Attribute;

public class BlogDescriptionScreen extends AbstractScreen {

  private static final String HASH_TAG_ON_ARTICLE_PAGE = "//div[contains(@class,'news-item-details')]//li//a[text()='%s']";
  private static final String ATTRIBUTE_SRC = Attribute.SRC.toString();
  private static final String UNDERLINE_CSS_ATTRIBUTE_NAME = "text-decoration-line";
  private static final String UNDERLINED_CSS_ATTRIBUTE_VALUE = "underline";
  private Element articleTitleOnDescriptionPage = Element.byCss("div.news-item__title> h1");
  private Element otherArticlesLink = Element.byXpath(
      "//div[@class='news-item__main__services']//li[contains(@class,'news-item__main__other-news__list-item')]/a");
  private Element otherArticlesLabel = Element.byXpath(
      "//div[@class='news-item__main__services']//div[contains(@class,'main__other-news__title')]");
  private Element allArticlesLink = Element.byXpath(
      "//div[@class='news-item__main__services']//a[contains(@class,'news-item__main__all-news-link')]");
  private Element articleFirstImageInSlider = Element
      .byXpath("//*[contains(@alt,'AutoTest_Abstract Painting')]");
  private Element categoryLabel = Element.byXpath("//div[contains(@class,'block__category')]");
  private Element hashtags = Element.byXpath("//li[contains(@class,'tags-item')]");
  private Element publicationDate = Element.byXpath(
      "//span[contains(@class,'item__counter-date')]");
  private Element viewIcon = Element.byXpath("//i[contains(@class,'fa-eye')]");
  private Element viewIconText = Element.byXpath("//i[contains(@class,'fa-eye')]/parent::span");
  private Element likeIcon = Element.byXpath("//i[contains(@class,'news-item__like')]");
  private Element likeIconText = Element.byXpath(
      "//i[contains(@class,'news-item__like')]/parent::span");
  private Element articleDescription = Element.byXpath("//div[contains(@class,'main__content')]");

  @Override
  public boolean isScreenLoaded() {
    return articleTitleOnDescriptionPage.isDisplayed();
  }

  public boolean isArticleTitleDisplayed() {
    return articleTitleOnDescriptionPage.isDisplayed();
  }

  public boolean isCategoryLabelDisplayed() {
    return categoryLabel.isDisplayed();
  }

  private List<Element> getViewIconList() {
    return viewIcon.getElements();
  }

  public int getViewIconListSize() {
    return getViewIconList().size();
  }

  private List<Element> getHashtagsList() {
    return hashtags.getElements();
  }

  public int getHashtagsListSize() {
    return getHashtagsList().size();
  }

  private List<Element> getPublicationDateList() {
    return publicationDate.getElements();
  }

  public int getPublicationDateListSize() {
    return getPublicationDateList().size();
  }

  private List<Element> getLikeIconList() {
    return likeIcon.getElements();
  }

  public int getLikeIconListSize() {
    return getLikeIconList().size();
  }

  public String getViewIconText() {
    return viewIconText.getText();
  }

  public String getLikeIconText() {
    return likeIconText.getText();
  }

  public boolean areAllHashtagsElementsDisplayed() {
    return getHashtagsList().stream()
        .allMatch(hashtag -> hashtag.isDisplayed());
  }

  public boolean areAllPublicationDateElementsDisplayed() {
    return getPublicationDateList().stream()
        .allMatch(publicationDate -> publicationDate.isDisplayed());
  }

  public boolean areAllViewIconElementsDisplayed() {
    return getViewIconList().stream()
        .allMatch(viewIcon -> viewIcon.isDisplayed());
  }

  public boolean areAllLikeIconElementsDisplayed() {
    return getLikeIconList().stream()
        .allMatch(likeIcon -> likeIcon.isDisplayed());
  }

  public boolean areAllOtherArticlesLinkElementsDisplayed() {
    return getAllOtherArticlesLinkElements().stream()
        .allMatch(link -> link.isDisplayed());
  }

  public boolean isArticleDescriptionDisplayed() {
    return articleDescription.isDisplayed();
  }

  public boolean isAllArticlesLinkDisplayed() {
    return allArticlesLink.isDisplayed();
  }

  public String getOtherArticlesLabelText() {
    return otherArticlesLabel.getText();
  }

  public String getOtherArticlesLinkColor() {
    return otherArticlesLink.getCssValue(Attribute.COLOR.toString());
  }

  public boolean isOtherArticlesLinkHasUnderlineWhileHovered() {
    return isElementHasUnderlineWhileHovered(otherArticlesLink);
  }

  public boolean isAllArticlesLinkHasUnderlineWhileHovered() {
    return isElementHasUnderlineWhileHovered(allArticlesLink);
  }

  private boolean isElementHasUnderlineWhileHovered(Element element) {
    return element.getHoveredCssValue(UNDERLINE_CSS_ATTRIBUTE_NAME)
        .equals(UNDERLINED_CSS_ATTRIBUTE_VALUE);
  }

  public String getArticleTitleOnDescriptionPageText() {
    return articleTitleOnDescriptionPage.getText();
  }

  public boolean isNewsHasHashTagOnDescriptionScreen(String hashTag) {
    return Element
        .byXpath(String.format(HASH_TAG_ON_ARTICLE_PAGE, hashTag)).isDisplayed();
  }

  public BlogDescriptionScreen waitArticleTitleIsNotEmpty() {
    articleTitleOnDescriptionPage.waitForTextToBePresent();
    return this;
  }

  private List<Element> getAllOtherArticlesLinkElements() {
    return otherArticlesLink.getElements();
  }

  public int getOtherArticlesLinkSize() {
    return getAllOtherArticlesLinkElements().size();
  }

  public BlogDescriptionScreen clickOtherArticleLinkByIndex(int index) {
    getAllOtherArticlesLinkElements().get(index).click();
    return this;
  }

  public String getOtherArticlesLinkTextByIndex(int index) {
    return getAllOtherArticlesLinkElements().get(index).getText();
  }

  public List<String> getAllOtherArticleTitles() {
    return otherArticlesLink.getElementsText();
  }

  public BlogScreen clickAllArticlesLink() {
    allArticlesLink.click();
    return new BlogScreen();
  }

  public boolean isArticleFirstImageInSliderDisplayed() {
    return articleFirstImageInSlider.isDisplayed();
  }

  public String getArticleFirstImageInSliderSrcAttribute() {
    return articleFirstImageInSlider.getAttributeValue(ATTRIBUTE_SRC);
  }
}
