package com.epmrdpt.screens;

import static java.lang.String.format;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.text.html.CSS.Attribute;

public class NewsManagementScreen extends AbstractScreen {

  private final static String ATTRIBUTE_VALUE = "value";
  private static final String ATTRIBUTE_TITLE = "title";
  private static final String ATTRIBUTE_PLACEHOLDER = "placeholder";

  private static final String COMMON_DESKTOP_LOCATOR = "//div[@ng-include='templateUrls.desktop']";
  private final static String SEARCH_RESULTS_COLUMN_PATTERN =
      COMMON_DESKTOP_LOCATOR + "//div[contains(@class,'table-row')]/div/div[%d]";
  private final static String DROP_DOWN_HINT_PATTERN =
      "//div[@id='%s_chosen']//span[contains(text(),'%s')]";
  private final static String CATEGORY_DDL_ITEM_LOCATOR_PATTERN =
      "//div[@id='category_chosen']//li[text()='%s']";
  private static final String STATUS_DDL_ITEM_LOCATOR_PATTERN = "//div[@id='status_chosen']//li[text()='%s']";
  private static final String HASH_TAG_DDL_ITEM_LOCATOR_PATTERN = "//div[@id='tags_chosen']//li[text()='%s']";
  private static final String CATEGORY = "category";
  private static final String STATUS = "status";
  private static final String AUTHOR = "author";
  private static final String TAGS = "tags";
  private static final String ELLIPSIS_ACTION_MENU_LIST_LOCATOR_PATTERN =
      COMMON_DESKTOP_LOCATOR + "//a[contains(text(),'%s')]";
  private static final String ELLIPSIS_BY_SUBJECT_NAME_LOCATOR = COMMON_DESKTOP_LOCATOR
      + "//a[@title='%s']/ancestor::div[@class='rd-table__row ng-scope']//span[contains(@ng-class,'active-ellipsis')]";
  private Element newsManagementHeader = Element.byXpath("//div[@ng-include='templateUrls.desktop']/div[1]");

  private Element publicationDates = Element.byXpath(
      "//div[contains(@class,'rd-table--desktop')]//div[@class='status draft']/parent::div/following-sibling::div[contains(@class,'ng-binding')]");
  private Element searchBySubjectInputText = Element
      .byXpath("//input[@ng-model='searchQuery']");
  private Element categoryDropDown = Element.byId("category_chosen");
  private Element statusDropDown = Element.byId("status_chosen");
  private Element authorDropDown = Element.byId("author_chosen");
  private Element hashTagsDropDown = Element.byId("tags_chosen");
  private Element startDateInputText = Element
      .byXpath("//*[@id='startDate']//div[@class='datepicker-btn']/preceding-sibling::input");
  private Element endDateInputText = Element
      .byXpath("//*[@id='endDate']//div[@class='datepicker-btn']/preceding-sibling::input");
  private Element applyButton = Element.byXpath("//button[@ng-click='applyFilters()']");
  private Element resetButton = Element.byXpath("//button[@ng-click='resetFilters()']");
  private Element authorNameInputText = Element
      .byXpath("//div[@id='author_chosen']//input[@class='chosen-search-input']");
  private Element authorNameInDropDownList = Element.byXpath(
      "//div[@id='author_chosen']//input[@class='chosen-search-input']/parent::div/parent::div//li[@class='active-result highlighted']");
  private Element categoryDropDownHint = Element
      .byXpath(format(DROP_DOWN_HINT_PATTERN, CATEGORY,
          LocaleProperties.getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_SELECT_CATEGORY)));
  private Element statusDropDownHint = Element
      .byXpath(format(DROP_DOWN_HINT_PATTERN, STATUS,
          LocaleProperties.getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_SELECT_STATUS)));
  private Element authorDropDownHint = Element
      .byXpath(format(DROP_DOWN_HINT_PATTERN, AUTHOR,
          LocaleProperties.getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_SELECT_AUTHOR)));
  private Element hashTagsDropDownHint = Element
      .byXpath(format(DROP_DOWN_HINT_PATTERN, TAGS,
          LocaleProperties.getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_SELECT_HASH_TAGS)));
  private Element subjectColumnInSearchResult = Element
          .byXpath("//div[contains(@class,'table-row')]/div[1]//a");
  private Element categoryColumnInSearchResult = Element
      .byXpath(format(SEARCH_RESULTS_COLUMN_PATTERN, 2));
  private Element authorColumnInSearchResult = Element
      .byXpath(format(SEARCH_RESULTS_COLUMN_PATTERN, 3));
  private Element statusColumnInSearchResult = Element
      .byXpath(format(SEARCH_RESULTS_COLUMN_PATTERN, 4));
  private Element publicationDateColumnInSearchResult = Element
      .byXpath(format(SEARCH_RESULTS_COLUMN_PATTERN, 5));
  private Element findButton = Element.byClassName("filter-search__find-button");
  private Element categoryLabel = Element.byXpath("//label[@for='category']");
  private Element categoryDropDownSelectedValue = Element
      .byXpath("//div[@id='category_chosen']//span");
  private Element currentStatusLabel = Element.byXpath("//label[@for='status']");
  private Element currentStatusDropdownValues = Element.byXpath("//div[@id='status_chosen']//li");
  private Element authorLabel = Element.byXpath("//label[@for='author']");
  private Element authorDropDownSelectedValue = Element.byXpath("//div[@id='author_chosen']//span");
  private Element hashTagLabel = Element.byXpath("//label[@for='tags']");
  private Element hashTagDropDownSelectedValue = Element.byXpath("//div[@id='tags_chosen']//span");
  private Element startDateLabel = Element.byXpath("//label[@for='startDate']");
  private Element startDateInputPicker = Element.byXpath("//rd-calendar[@id='startDate']//input");
  private Element endDateLabel = Element.byXpath("//label[@for='endDate']");
  private Element endDatePickerInputPicker = Element.byXpath("//rd-calendar[@id='endDate']//input");
  private Element ellipsisActionMenu = Element.byXpath(
      "//div[@ng-include='templateUrls.desktop']//span[contains(@ng-class,'active-ellipsis')]");
  private Element viewLink = Element
      .byXpath(format(ELLIPSIS_ACTION_MENU_LIST_LOCATOR_PATTERN,
          LocaleProperties
              .getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_ELLIPSIS_ACTION_MENU_VIEW)));
  private Element editLink = Element
      .byXpath(format(ELLIPSIS_ACTION_MENU_LIST_LOCATOR_PATTERN,
          LocaleProperties
              .getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_ELLIPSIS_ACTION_MENU_EDIT)));
  private Element deleteLink = Element
      .byXpath(format("//div[@ng-include='templateUrls.desktop']//div[contains(text(),'%s')]",
          LocaleProperties
              .getValueOf(LocalePropertyConst.NEWS_MANAGEMENT_ELLIPSIS_ACTION_MENU_DELETE)));
  private Element actionMenuPopUp = Element.byXpath(
      "//div[@ng-include='templateUrls.desktop']//div[contains(@class,'news-tooltip') and contains(@class,'ng-scope')]");
  private Element deleteAction = Element.byXpath(
      "//div[@ng-include='templateUrls.desktop']//div[contains(@ng-click,'removeNewsItem')]");
  private Element confirmationPopUp = Element.byXpath("//div[@class='popup-wrapper']");
  private Element cancelButtonOnConfirmationPopUp = Element
      .byXpath("//button[contains(@class,'message-btn-cancel')]");
  private Element newsItemSubject = Element
      .byXpath("//div[@ng-include='templateUrls.desktop']//a[@title]");
  private Element createNewsItem = Element.byXpath("//a[contains(@class,'create-news__button')]");
  private Element okButtonOnConfirmationPopUp = Element
      .byXpath("//button[contains(@class,'message-btn-ok')]");
  private Element hashTagsDDLItem = Element.byXpath("//div[@id='tags_chosen']//li");
  private Element categoryDDLItem = Element.byXpath("//div[@id='category_chosen']//li");
  private Element subjectNewsItems = Element.byXpath(
      "//div[@ng-include='templateUrls.desktop']//div[contains(@class,'table-row')]//a");

  @Override
  public boolean isScreenLoaded() {
    return newsManagementHeader.isDisplayed();
  }

  public NewsManagementScreen waitScreenLoading() {
    newsManagementHeader.waitForVisibility();
    return this;
  }

  public NewsManagementScreen waitSearchResultsLoading() {
    subjectColumnInSearchResult.waitForVisibility(DEFAULT_TIME_OUT_FOR_PAGE_REFRESH);
    return this;
  }

  public int getPublicationDatesCount() {
    return publicationDates.getElements().size();
  }

  public List<String> getPublicationDates() {
    return publicationDates.getElementsText();
  }

  public NewsManagementScreen enterSearchBySubjectInput(String subject) {
    searchBySubjectInputText.type(subject);
    return this;
  }

  public NewsManagementScreen clickCategoryDropDown() {
    categoryDropDown.click();
    return this;
  }

  public NewsManagementScreen waitCategoryDDLDisplayed() {
    categoryDDLItem.waitForVisibility();
    return this;
  }

  public NewsManagementScreen clickStatusDropDown() {
    statusDropDown.click();
    return this;
  }

  public NewsManagementScreen clickAuthorDropDown() {
    authorDropDown.click();
    return this;
  }

  public NewsManagementScreen clickHashTagsDropDown() {
    hashTagsDropDown.click();
    return this;
  }

  public NewsManagementScreen waitHashTagsDDLDisplayed() {
    hashTagsDDLItem.waitForVisibility();
    return this;
  }

  public NewsManagementScreen clickApplyButton() {
    applyButton.click();
    return this;
  }

  public NewsManagementScreen clickResetButton() {
    resetButton.click();
    return this;
  }

  public boolean isCategoryDDLItemByNameDisplayed(String categoryName) {
    return Element.byXpath(CATEGORY_DDL_ITEM_LOCATOR_PATTERN, categoryName).isDisplayed();
  }

  public NewsManagementScreen clickCategoryInDropDownListByName(String categoryName) {
    Element.byXpath(format(CATEGORY_DDL_ITEM_LOCATOR_PATTERN, categoryName)).click();
    return this;
  }

  public NewsManagementScreen clickStatusInDropDownListByName(String statusType) {
    Element.byXpath(format(STATUS_DDL_ITEM_LOCATOR_PATTERN, statusType)).click();
    return this;
  }

  public NewsManagementScreen enterAuthorNameInputText(String authorName) {
    authorNameInputText.type(authorName);
    return this;
  }

  public NewsManagementScreen clickAuthorNameInDropDownList() {
    authorNameInDropDownList.click();
    return this;
  }

  public int getHashTagsCountInHashTagDropdown() {
    return hashTagsDDLItem.getElements().size();
  }

  public NewsManagementScreen clickHashTagInDropDownListByName(String hashTagName) {
    Element.byXpath(format(HASH_TAG_DDL_ITEM_LOCATOR_PATTERN, hashTagName)).click();
    return this;
  }

  public NewsManagementScreen enterStartDateInputText(String expectedStartDate) {
    startDateInputText.type(expectedStartDate);
    return this;
  }

  public NewsManagementScreen enterEndDateInputText(String expectedEndDate) {
    endDateInputText.type(expectedEndDate);
    return this;
  }

  public boolean isCategoryDropDownHintDisplayed() {
    return categoryDropDownHint.isDisplayed();
  }

  public boolean isStatusDropDownHintDisplayed() {
    return statusDropDownHint.isDisplayed();
  }

  public boolean isAuthorDropDownHintDisplayed() {
    return authorDropDownHint.isDisplayed();
  }

  public boolean isHashTagsDropDownHintDisplayed() {
    return hashTagsDropDownHint.isDisplayed();
  }

  public String getStartDateAttributeValue() {
    return startDateInputText.getAttributeValue(ATTRIBUTE_VALUE);
  }

  public String getEndDateAttributeValue() {
    return endDateInputText.getAttributeValue(ATTRIBUTE_VALUE);
  }

  public String getSearchBySubjectInputTextValue() {
    return searchBySubjectInputText.getAttributeValue(ATTRIBUTE_VALUE);
  }

  public List<String> getSearchResultsBySubjectColumn() {
    List<String> elementsText = new ArrayList<>();
    for(Element element : subjectColumnInSearchResult.getElements()) {
      elementsText.add(element.getAttributeValue(ATTRIBUTE_TITLE));
    }
    return elementsText;
  }

  public String getSearchResultsItemBySubjectColumn() {
    return subjectColumnInSearchResult.getAttributeValue(ATTRIBUTE_TITLE);
  }

  public List<String> getSearchResultsByCategoryColumn() {
    return categoryColumnInSearchResult.getElementsText();
  }

  public List<String> getSearchResultsByStatusColumn() {
    return statusColumnInSearchResult.getElementsText();
  }

  public List<String> getSearchResultsByAuthorColumn() {
    return authorColumnInSearchResult.getElementsText();
  }

  public List<String> getSearchResultsByPublicationDateColumn() {
    return publicationDateColumnInSearchResult.getElementsText();
  }

  public NewsManagementScreen clickNewsItemSubjectByIndex(int index) {
    subjectColumnInSearchResult.getElements().get(index).clickJs();
    return this;
  }

  public NewsManagementScreen clickRandomNewsItemSubject() {
    return clickNewsItemSubjectByIndex(
            (int) (Math.random() * subjectColumnInSearchResult.getElements().size()));
  }

  public NewsManagementScreen clickFindButton() {
    findButton.click();
    return this;
  }

  public NewsManagementScreen clearSearchBySubjectInput() {
    searchBySubjectInputText.clearInput();
    return this;
  }

  public String getPlaceHolderTextOfSearchBySubject() {
    return searchBySubjectInputText.getAttributeValue(ATTRIBUTE_PLACEHOLDER);
  }

  public String getTextOnSearchFindButton() {
    return findButton.getAttributeValue(ATTRIBUTE_VALUE);
  }

  public String getCategoryLabelText() {
    return categoryLabel.getText();
  }

  public String getCategoryDropDownSelectedValue() {
    return categoryDropDownSelectedValue.getText();
  }

  public String getCurrentStatusLabelText() {
    return currentStatusLabel.getText();
  }

  public List<String> getCurrentStatusDropDownValueTexts() {
    return currentStatusDropdownValues.getElementsText();
  }

  public String getAuthorLabelText() {
    return authorLabel.getText();
  }

  public String getAuthorDropDownSelectedValue() {
    return authorDropDownSelectedValue.getText();
  }

  public String getHashTagLabelText() {
    return hashTagLabel.getText();
  }

  public String getHashTagDropDownSelectedValue() {
    return hashTagDropDownSelectedValue.getText();
  }

  public String getStartDateLabelText() {
    return startDateLabel.getText();
  }

  public String getStartDateInputPlaceholderValue() {
    return startDateInputPicker.getAttributeValue(ATTRIBUTE_PLACEHOLDER);
  }

  public String getEndDateLabelText() {
    return endDateLabel.getText();
  }

  public String getEndDateInputPlaceholderValue() {
    return endDatePickerInputPicker.getAttributeValue(ATTRIBUTE_PLACEHOLDER);
  }

  public NewsManagementScreen clickEllipsisActionMenu() {
    ellipsisActionMenu.clickJs();
    return this;
  }

  public boolean isViewLinkDisplayed() {
    return viewLink.isDisplayed();
  }

  public boolean isEditLinkDisplayed() {
    return editLink.isDisplayed();
  }

  public boolean isDeleteLinkDisplayed() {
    return deleteLink.isDisplayed();
  }

  private String getLinkTextColor(Element element) {
    return element.getCssValue(Attribute.COLOR.toString());
  }

  public String getViewLinkTextColor() {
    return getLinkTextColor(viewLink);
  }

  public String getEditLinkTextColor() {
    return getLinkTextColor(editLink);
  }

  public String getDeleteLinkTextColor() {
    return getLinkTextColor(deleteLink);
  }

  private String getHoveredLinkTextColor(Element element) {
    return element.getHoveredCssValue(Attribute.COLOR.toString());
  }

  public String getHoveredViewLinkTextColor() {
    return getHoveredLinkTextColor(viewLink);
  }

  public String getHoveredEditLinkTextColor() {
    return getHoveredLinkTextColor(editLink);
  }

  public String getHoveredDeleteLinkTextColor() {
    return getHoveredLinkTextColor(deleteLink);
  }

  private List<Element> getEllipsisActionMenu() {
    return ellipsisActionMenu.getElements();
  }

  public NewsManagementScreen clickEllipsisActionMenuByIndex(int index) {
    getEllipsisActionMenu().get(index).click();
    return this;
  }

  public NewsManagementScreen clickEllipsisActionMenuBySubjectName(String subjectName) {
    Element.byXpath(ELLIPSIS_BY_SUBJECT_NAME_LOCATOR, subjectName).click();
    return this;
  }

  public boolean isActionMenuPopUpDisplayed() {
    return actionMenuPopUp.isDisplayed();
  }

  public boolean isConfirmationPopUpDisplayed() {
    return confirmationPopUp.isDisplayed();
  }

  public NewsManagementScreen clickDeleteAction() {
    deleteAction.click();
    return this;
  }

  public List<String> getAllNewsItemsSubjectsText() {
    return newsItemSubject.getElements().stream()
        .map(subjectElement -> subjectElement.getAttributeValue(ATTRIBUTE_TITLE))
        .collect(Collectors.toList());
  }

  public String getNewsItemSubjectTextByIndex(int index) {
    return getAllNewsItemsSubjectsText().get(index);
  }

  public NewsManagementScreen clickCancelButtonOnConfirmationPopUp() {
    cancelButtonOnConfirmationPopUp.click();
    return this;
  }

  public boolean isNewsItemWithSubjectPresent(String subjectText) {
    if (newsItemSubject.isAbsent()) {
      return false;
    }
    return getAllNewsItemsSubjectsText().stream()
        .anyMatch(subjectName -> subjectName.equals(subjectText));
  }

  public CreateNewsItemScreen clickCreateNewsItem() {
    createNewsItem.clickJs();
    return new CreateNewsItemScreen();
  }

  public NewsManagementScreen typeSearchBySubject(String subjectName) {
    searchBySubjectInputText.type(subjectName);
    return this;
  }

  public NewsManagementScreen clickOkOnConfirmationPopUp() {
    okButtonOnConfirmationPopUp.clickJs();
    return this;
  }
}
