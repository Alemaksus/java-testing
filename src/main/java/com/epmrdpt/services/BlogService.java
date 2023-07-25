package com.epmrdpt.services;

import com.epmrdpt.screens.BlogScreen;
import com.epmrdpt.screens.BlogScreenAfterSearchHashTagScreen;
import com.epmrdpt.utils.RandomUtils;
import java.util.List;

public class BlogService {

  private BlogScreen blogScreen = new BlogScreen();

  public BlogScreen loadAllNewsArticleCards() {
    int differenceArticleCardsListSizeBeforeAndAfterClick = 1;
    while (differenceArticleCardsListSizeBeforeAndAfterClick > 0) {
      int articleCardsListBeforeClick = blogScreen.getSizeArticleCardsList();
      blogScreen.clickViewMoreLink()
          .waitArticleCardsListAfterClickViewMoreButton(articleCardsListBeforeClick);
      if (blogScreen.isViewMoreLinkWithHideClassPresent()) {
        break;
      }
      int articleCardsListAfterClick = blogScreen.getSizeArticleCardsList();
      differenceArticleCardsListSizeBeforeAndAfterClick =
          articleCardsListAfterClick - articleCardsListBeforeClick;
      if (differenceArticleCardsListSizeBeforeAndAfterClick == 0) {
        break;
      }
    }
    return new BlogScreen();
  }

  public BlogScreen clickAnyBlogTab() {
    List<String> displayedTabNames = blogScreen.getDisplayedTabNameTexts();
    int randomNumber = RandomUtils.getRandomNumber(displayedTabNames.size());
    return blogScreen.clickTabByCategoryName(displayedTabNames.get(randomNumber));
  }

  public BlogScreen searchForHashTagByText(String hashTag) {
    while (blogScreen.isViewMoreLinkDisplayed()) {
      if (blogScreen.isHashTagLinkDisplayedByText(hashTag)) {
        break;
      }
      blogScreen.clickViewMoreLink();
    }
    return new BlogScreen();
  }

  public BlogScreen searchForArticleByText(String text) {
    while (blogScreen.isViewMoreLinkDisplayed()) {
      if (blogScreen.getArticleTitleTextList().contains(text)) {
        break;
      }
      int listSize = blogScreen.getArticleTitleListSize();
      blogScreen.clickViewMoreLink();
      blogScreen.waitArticleCardsListAfterClickViewMoreButton(listSize);
    }
    return new BlogScreen();
  }

  public BlogScreenAfterSearchHashTagScreen clickRandomHashTag() {
    blogScreen.clickShowMoreButton();
    int hashtagCount = blogScreen.getAllHashTagsAtTheTopOfTheScreenCount();
    int randomInt = RandomUtils.getRandomNumber(hashtagCount);
    return blogScreen.clickHashTagByIndex(randomInt);
  }
}
