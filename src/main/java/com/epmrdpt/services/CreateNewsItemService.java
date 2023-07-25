package com.epmrdpt.services;

import com.epmrdpt.bo.NewsItem;
import com.epmrdpt.screens.CreateNewsItemScreen;
import com.epmrdpt.screens.NewsManagementScreen;

public class CreateNewsItemService {

  public static CreateNewsItemScreen createNewsItemWithData(NewsItem newsItem) {
    return new NewsManagementScreen().clickCreateNewsItem().waitCreateNewsScreenIsLoaded()
        .clickStatusDropDown().clickValueFromDropDownValues(newsItem.getStatus())
        .typeDateOfPublication(newsItem.getDateOfPublication())
        .clickCategoryDropDown().clickValueFromDropDownValues(newsItem.getCategory())
        .selectEnglishLanguage().typeTitleInEnglish(newsItem.getTitleInEnglish())
        .typeIntroductionInEnglish(newsItem.getIntroductionInEnglish())
        .typeDescriptionInEnglish(newsItem.getDescriptionInEnglish()).selectRussianLanguage()
        .typeTitleInRussian(newsItem.getTitleInRussian())
        .typeIntroductionInRussian(newsItem.getIntroductionInRussian())
        .typeDescriptionInRussian(newsItem.getDescriptionInRussian())
        .selectUkrainianLanguage().typeTitleInUkrainian(newsItem.getTitleInUkrainian())
        .typeIntroductionInUkrainian(newsItem.getIntroductionInUkrainian())
        .typeDescriptionInUkrainian(newsItem.getDescriptionInUkrainian()).clickCreateNewsButton();
  }

  public static CreateNewsItemScreen createNewsItemWithLinkToVideo(NewsItem newsItem) {
    return new NewsManagementScreen().clickCreateNewsItem().waitCreateNewsScreenIsLoaded()
        .typeDateOfPublication(newsItem.getDateOfPublication())
        .clickCategoryDropDown().clickValueFromDropDownValues(newsItem.getCategory())
        .selectEnglishLanguage().typeTitleInEnglish(newsItem.getTitleInEnglish())
        .typeIntroductionInEnglish(newsItem.getIntroductionInEnglish())
        .typeDescriptionInEnglish(newsItem.getDescriptionInEnglish())
        .typeLinkToVideo(newsItem.getLinkToVideo())
        .clickCreateNewsButton();
  }
}
