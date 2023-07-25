package com.epmrdpt.screens;

import static java.lang.String.format;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;
import java.util.List;
import javax.swing.text.html.CSS.Attribute;

public class TasksPopUpScreenOnLearningPageScreen extends AbstractScreen {

  private static final String ATTRIBUTE_VALUE = "value";
  private static final String ATTRIBUTE_PLACEHOLDER = "placeholder";
  private static final String ATTACHED_TASK_FILE_NAME_LOCATOR_PATTERN =
      "//div[@class='task-attached-files__file']//div[text()='%s']";
  private static final String FILE_NAME_LOCATOR_PATTERN = "//div[text()='%s']";
  private static final String REMOVE_FILE_BUTTON_LOCATOR_PATTERN =
      FILE_NAME_LOCATOR_PATTERN + "/../../div[@class='task-chat__attached-files__link']";
  private static final String USER_COMMENT_ATTACHED_FILE_LOCATOR_PATTERN =
      "//div[contains(@class,'attached-files') and text()='%s']";

  private Element popUp = Element.byCss("div.modal-content");
  private Element popUpTitle = Element.byId("popup-title__name");
  private Element taskName = Element.byCss("div.task-header__name");
  private Element groupName = Element.byClassName("task-header__subject");
  private Element trainerName =
      Element.byXpath("//div[@class='name-trainer']/p[1]");
  private Element headerIcon = Element.byClassName("icon__hat");
  private Element mark = Element.byXpath("//div[@class='task-statistic__mark']//p[1]");
  private Element trainerRole = Element.byXpath("//*[contains(@class,'trainers__trainer')]//p[2]");
  private Element regularTaskComment = Element.byCss("div.trainer-comments");
  private Element onlineTaskIconGlobe = Element.byClassName("icon__globe");
  private Element trainerPhoto =
      Element.byXpath("//*[contains(@class,'trainers__trainer')]/div[@class='icon-trainer']");
  private Element startDateLabel = Element.byXpath("//*[@class='task-statistic__start-date']/p[1]");
  private Element startDate = Element.byXpath("//*[@class='task-statistic__start-date']/p[2]");
  private Element deadlineDateLabel =
      Element.byXpath("//*[contains(@class, 'task-statistic__deadline-date')]/p[1]");
  private Element deadlineDate =
      Element.byXpath("//*[contains(@class, 'task-statistic__deadline-date')]/p[2]");
  private Element messageToUserBasedOnTaskStatus = Element
      .byClassName("user-comments__about-message");
  private Element reviewerName =
      Element.byXpath("(//*[contains(@class,'trainers__reviewer')]//p[1])[1]");
  private Element reviewerRole =
      Element.byXpath("//*[contains(@class,'trainers__reviewer')]//p[2]");
  private Element reviewerPhoto =
      Element.byXpath("//*[contains(@class,'trainers__reviewer')]/div[@class='icon-trainer']");
  private Element workCommentsTitle = Element.byClassName("trainer-comments__header");
  private Element workCommentsSectionTrainerName =
      Element.byXpath("//*[contains(@class,'trainer-comments')]//p[1]");
  private Element workCommentsSectionTrainerCommentDate =
      Element.byXpath("//*[contains(@class,'trainer-comments')]//p[2]");
  private Element trainerCommentsMessage = Element.byClassName("trainer-comments__message");
  private Element userName = Element.byClassName("task-chat__user-name");
  private Element userNameInSubmittedUserComment = Element
      .byCss("div.user-comments div.task-chat__user-name>p:first-child");
  private Element userCommentsMessage = Element.byClassName("user-comments__message");
  private Element attempts = Element
      .byXpath("//div[@class='task-statistic__mark']//p[@ng-if='checkOnlineTask']");
  private Element userPhoto = Element.byClassName("task-chat__user-icon");
  private Element userPhotoInSubmittedUserComment = Element
      .byCss("div.user-comments div.task-chat__user-icon");
  private Element closeButton = Element.byCss("div.popup-title__close");
  private Element assignedStatusIcon = Element.byCss("div.task-header__status > p > i");
  private Element statusLabel = Element.byCss("div.task-header__status > p");
  private Element submitButton = Element.byCss("input.task-chat__user-action-button-submit");
  private Element commentField = Element.byName("commentField");
  private Element addFileButton = Element.byClassName("task-chat__user-action-add-files-link");
  private Element addFileInputField = Element.byId("file");
  private Element modalErrorMessage = Element.byCss("div.error-modal-message-text>span");
  private Element uploadedTaskFileName = Element
      .byXpath("//div[contains(@class,'task-chat__attached-files-file-name')]");
  private Element addFileIconAndLabel = Element.byXpath("//label[@for='file']");
  private Element taskChatDescription = Element.byCss("div.task-chat__description");
  private Element completeTaskDescription = Element.byClassName("task-description");
  private Element taskDescriptionArea = Element.byXpath(
      "//div[contains(@class,'task-description')]//*[contains(text(),'Task description')]");
  private Element attachedTaskFileName = Element
      .byXpath("//div[contains(@class,'task-attached-files__file-name')]");
  private Element openButtonForAttachedTaskFile = Element.byCss("a.task-attached-files__link");
  private Element duplicateFileUploadError = Element.byXpath("//p[@ng-show='sameFileChecked']");
  private Element maximumPermissibleFileUploadReachedError = Element
      .byXpath("//p[@ng-show='selectedFiles.length === 10']");
  private Element showMoreTextForUserComment = Element.byXpath("//div[contains(@class,'showFullTextButton')]");
  private Element showLessTextForUserComment = Element.byXpath("//div[contains(@class,'showLessTextButton')]");
  private Element openButtonForUserCommentAttachedFile = Element
      .byXpath("//div[@class='popup-wrapper task-modal-bg']//a");
  private Element assignmentStatusAboutMessage = Element.byCss("div.user-comments__about-message");
  private Element noAttemptsLeftAlertMessage = Element
      .byXpath("//span[@ng-if='allAttemptsUse && isStatusRejected']");
  private Element allCommentsLabel = Element.byCss("div.comments-history__header");
  private Element allCommentsToggle = Element
      .byCss("div.comments-history__header span");
  private Element studentNameInAllCommentsSection = Element.byXpath(
      "//div[contains(@class,'comments-history__content')][1]/div[contains(@class,'name-trainer')]/p[1]");
  private Element commentSubmissionDateInAllCommentsSection = Element.byXpath(
      "//div[contains(@class,'comments-history__content')][1]/div[contains(@class,'name-trainer')]/p[2]");
  private Element reviewerNameInAllCommentsSection = Element.byXpath(
      "//div[contains(@class,'comments-history__content')][2]/div[contains(@class,'name-trainer')]/p[1]");
  private Element commentReviewDateInAllCommentsSection = Element.byXpath(
      "//div[contains(@class,'comments-history__content')][2]/div[contains(@class,'name-trainer')]/p[2]");
  private Element studentPhotoInAllCommentsSection = Element.byXpath(
      "//div[contains(@class,'comments-history__content')][1]/div[contains(@class,'icon-trainer')]");
  private Element reviewerPhotoInAllCommentsSection = Element.byXpath(
      "//div[contains(@class,'comments-history__content')][2]/div[contains(@class,'icon-trainer')]");
  private Element studentCommentInAllCommentsSection = Element.byXpath(
      "//div[contains(@class,'comments-history__content')][1]/div[contains(@class,'comments-history__content-one-note-message')]");
  private Element reviewerCommentInAllCommentsSection = Element.byXpath(
      "//div[contains(@class,'comments-history__content')][2]/div[contains(@class,'comments-history__content-one-note-message')]");
  private Element workCommentSectionTitle = Element.byXpath(
      "//*[contains(@class, 'trainer-comments__header')]");
  private Element workCommentsSectionTrainerPhoto =
      Element.byXpath(
          "//*[contains(@class, 'trainer-comments ng-scope')]//*[contains(@class, 'icon-trainer')]");

  @Override
  public boolean isScreenLoaded() {
    return popUp.isDisplayed();
  }

  public TasksPopUpScreenOnLearningPageScreen waitPopUpForVisibility() {
    popUp.waitForVisibility();
    return this;
  }

  public boolean isTitleInWorkCommentSectionDisplayed() {
    return workCommentSectionTitle.isDisplayed();
  }

  public boolean isTrainerPhotoInWorkCommentsSectionDisplayed() {
    return workCommentsSectionTrainerPhoto.isDisplayed();
  }

  public boolean isDataInWorkCommentSectionDisplayed() {
    return workCommentsSectionTrainerCommentDate.isDisplayed();
  }

  public TasksPopUpScreenOnLearningPageScreen clickOnTrainerCommentsMessageField() {
    trainerCommentsMessage.click();
    return this;
  }

  public boolean isPopUpTitleDisplayed() {
    return popUpTitle.isDisplayed();
  }

  public String getPopUpTitleText() {
    return popUpTitle.getText();
  }

  public String getHeaderIconBackground() {
    return headerIcon.getCssValue(Attribute.BACKGROUND.toString());
  }

  public boolean isHeaderIconDisplayed() {
    return headerIcon.isDisplayed();
  }

  public boolean isTaskNameDisplayed() {
    return taskName.isDisplayed();
  }

  public String getTaskNameText() {
    return taskName.getText();
  }

  public boolean isGroupNameDisplayed() {
    return groupName.isDisplayed();
  }

  public String getGroupNameText() {
    return groupName.getText();
  }

  public boolean isMarkDisplayed() {
    return mark.isDisplayed();
  }

  public String getMarkText() {
    return mark.getText();
  }

  public boolean isTrainerNameDisplayed() {
    return trainerName.isDisplayed();
  }

  public String getTrainerNameText() {
    return trainerName.getText();
  }

  public boolean isTrainerRoleDisplayed() {
    return trainerRole.isDisplayed();
  }

  public boolean isTrainerPhotoDisplayed() {
    return trainerPhoto.isDisplayed();
  }

  public boolean isOnlineTaskIconDisplayed() {
    return onlineTaskIconGlobe.isDisplayed();
  }

  public String getOnlineTaskIconBackground() {
    return onlineTaskIconGlobe.getCssValue(Attribute.BACKGROUND.toString());
  }

  public boolean isReviewerNameDisplayed() {
    return reviewerName.isDisplayed();
  }

  public String getReviewerNameText() {
    return reviewerName.getText();
  }

  public boolean isReviewerRoleDisplayed() {
    return reviewerRole.isDisplayed();
  }

  public String getMessageToUserBasedTaskStatus() {
    return messageToUserBasedOnTaskStatus.getText();
  }

  public boolean isReviewerPhotoDisplayed() {
    return reviewerPhoto.isDisplayed();
  }

  public boolean isUserNameDisplayed() {
    return userName.isDisplayed();
  }

  public String getUserNameText() {
    return userName.getText();
  }

  public String getUserNameInSubmittedUserCommentText() {
    return userNameInSubmittedUserComment.getText();
  }

  public boolean isUserCommentsMessageDisplayed() {
    return userCommentsMessage.isDisplayed();
  }

  public String getUserCommentsMessageText() {
    return userCommentsMessage.getText();
  }

  public boolean isTrainerCommentsMessageDisplayed() {
    return trainerCommentsMessage.isDisplayed();
  }

  public String getTrainerCommentsMessageText() {
    return trainerCommentsMessage.getText();
  }

  public boolean isTrainerNameInWorkCommentsSectionDisplayed() {
    return workCommentsSectionTrainerName.isDisplayed();
  }

  public String getStartDateLabelText() {
    return startDateLabel.getText();
  }

  public String getDeadlineDateLabelText() {
    return deadlineDateLabel.getText();
  }

  public String getStartDateText() {
    return startDate.getText();
  }

  public String getDeadlineDateText() {
    return deadlineDate.getText();
  }

  public String getWorkCommentsTitleText() {
    return workCommentsTitle.getText();
  }

  public String getAttemptsText() {
    return attempts.getText();
  }

  public String getTrainerRoleText() {
    return trainerRole.getText();
  }

  public String getReviewerRoleText() {
    return reviewerRole.getText();
  }

  public boolean isUserPhotoDisplayed() {
    return userPhoto.isDisplayed();
  }

  public boolean isCloseButtonDisplayed() {
    return closeButton.isDisplayed();
  }

  public boolean isAssignedStatusIconDisplayed() {
    return assignedStatusIcon.isDisplayed();
  }

  public String getStatusLabelText() {
    return statusLabel.getText();
  }

  public boolean isStatusLabelDisplayed() {
    return statusLabel.isDisplayed();
  }

  public boolean isSubmitButtonDisplayed() {
    return submitButton.isDisplayed();
  }

  public String getSubmitButtonText() {
    return submitButton.getAttributeValue(ATTRIBUTE_VALUE);
  }

  public boolean isSubmitButtonDisplayedNoWait() {
    return submitButton.isDisplayedNoWait();
  }

  public boolean isCommentFieldDisplayed() {
    return commentField.isDisplayed();
  }

  public boolean isAddFileButtonDisplayed() {
    return addFileButton.isDisplayed();
  }

  public boolean isAddFileButtonClickable() {
    return addFileButton.isClickable();
  }

  public String getAddFileIconAndLabelText() {
    return addFileIconAndLabel.getText();
  }

  public boolean isTaskChatDescriptionDisplayed() {
    return taskChatDescription.isDisplayed();
  }

  public String getTaskChatDescriptionText() {
    return taskChatDescription.getText();
  }

  public boolean isCompleteTaskDescriptionDisplayed() {
    return completeTaskDescription.isDisplayed();
  }

  public TasksPopUpScreenOnLearningPageScreen clickOnTaskDescriptionArea() {
    taskDescriptionArea.click();
    return this;
  }

  public String getCompleteTaskDescriptionText() {
    return completeTaskDescription.getText();
  }

  public boolean isAttachedTaskFileDisabled() {
    return attachedTaskFileName.isDisabled();
  }

  public boolean isAttachedTaskFileByNameDisplayed(String fileName) {
    return Element.byXpath(format(ATTACHED_TASK_FILE_NAME_LOCATOR_PATTERN, fileName)).isDisplayed();
  }

  public String getOpenButtonForAttachedTaskFileText() {
    return openButtonForAttachedTaskFile.getText();
  }

  public String getCommentFieldPlaceHolder() {
    return commentField.getAttributeValue(ATTRIBUTE_PLACEHOLDER);
  }

  public TasksPopUpScreenOnLearningPageScreen typeComment(String text) {
    commentField.waitForClickable();
    commentField.type(text);
    return this;
  }

  public boolean isSubmitButtonEnabled() {
    return submitButton.isEnabled();
  }

  public TasksTabOnLearningPageScreen clickSubmitButton() {
    submitButton.waitForClickableAndClick();
    return new TasksTabOnLearningPageScreen();
  }

  public boolean isErrorMessageDisplayed() {
    return modalErrorMessage.isDisplayed();
  }

  public String getErrorMessageText() {
    return modalErrorMessage.getText();
  }

  public String getCommentFieldText() {
    return commentField.getAttributeValue(ATTRIBUTE_VALUE);
  }

  public boolean isAddFileDisabled() {
    return addFileInputField.isDisabled();
  }

  public TasksPopUpScreenOnLearningPageScreen uploadTaskFile(String path) {
    addFileInputField.clearAndSendFilePath(path);
    return this;
  }

  public boolean isTaskFileByNameDisplayed(String taskFileName) {
    return Element.byXpath(FILE_NAME_LOCATOR_PATTERN, taskFileName).isDisplayed();
  }

  public boolean isTaskFileByNameNotDisplayed(String taskFileName) {
    return Element.byXpath(FILE_NAME_LOCATOR_PATTERN, taskFileName).isNotDisplayed();
  }

  public int getUploadedTaskFilesCount() {
    return uploadedTaskFileName.isDisplayed() ? uploadedTaskFileName.getElements().size() : 0;
  }

  public List<String> getUploadedTaskFileNames() {
    return uploadedTaskFileName.getElementsText();
  }

  public String getDuplicateFileUploadErrorText() {
    return duplicateFileUploadError.getText();
  }

  public String getMaximumPermissibleFileUploadReachedErrorText() {
    return maximumPermissibleFileUploadReachedError.getText();
  }

  public String getTrainersComment() {
    return trainerCommentsMessage.getText();
  }

  public TasksPopUpScreenOnLearningPageScreen clickRemoveFileButtonByName(String taskFileName) {
    Element.byXpath(REMOVE_FILE_BUTTON_LOCATOR_PATTERN, taskFileName).click();
    return this;
  }

  public TasksPopUpScreenOnLearningPageScreen clickCrossMark() {
    closeButton.click();
    return this;
  }

  public String getCommentFromRegularTask() {
    return regularTaskComment.getText();
  }

  public boolean isShowMoreOfUserCommentDisplayed() {
    return showMoreTextForUserComment.isDisplayed();
  }

  public String getShowMoreTextOfUserComment() {
    return showMoreTextForUserComment.getText();
  }

  public TasksPopUpScreenOnLearningPageScreen clickShowMoreTextOfUserComment() {
    showMoreTextForUserComment.click();
    return this;
  }

  public boolean isShowLessOfUserCommentDisplayed() {
    return showLessTextForUserComment.isDisplayed();
  }

  public String getShowLessTextOfUserComment() {
    return showLessTextForUserComment.getText();
  }

  public TasksPopUpScreenOnLearningPageScreen clickShowLessTextOfUserComment() {
    showLessTextForUserComment.click();
    return this;
  }

  public boolean isUserPhotoInSubmittedUserCommentDisplayed() {
    return userPhotoInSubmittedUserComment.isDisplayed();
  }

  public boolean isUserCommentAttachedTaskFileByNameDisplayed(String fileName) {
    return Element.byXpath(USER_COMMENT_ATTACHED_FILE_LOCATOR_PATTERN, fileName).isDisplayed();
  }

  public String getOpenButtonForUserCommentAttachedFileText() {
    return openButtonForUserCommentAttachedFile.getText();
  }

  public String getAssignmentStatusAboutMessageText() {
    return assignmentStatusAboutMessage.getText();
  }

  public TasksPopUpScreenOnLearningPageScreen waitForVisibilityAssignmentStatusAboutMessage() {
    assignmentStatusAboutMessage.waitForVisibility(LONG_TIME_OUT_IN_SECONDS);
    return this;
  }

  public String getNoAttemptsLeftAlertMessageText() {
    return noAttemptsLeftAlertMessage.getText();
  }

  public String getAllCommentsLabelText() {
    return allCommentsLabel.getText();
  }

  public boolean isAllCommentsToggleDisplayed() {
    return allCommentsToggle.isPresent();
  }

  public TasksPopUpScreenOnLearningPageScreen clickAllCommentsToggle() {
    allCommentsToggle.click();
    return this;
  }

  public String getStudentNameInAllCommentsSectionText() {
    return studentNameInAllCommentsSection.getText();
  }

  public String getCommentSubmissionDateInAllCommentsSectionText() {
    return commentSubmissionDateInAllCommentsSection.getText();
  }

  public String getReviewerNameInAllCommentsSectionText() {
    return reviewerNameInAllCommentsSection.getText();
  }

  public String getCommentReviewDateInAllCommentsSectionText() {
    return commentReviewDateInAllCommentsSection.getText();
  }

  public boolean isStudentPhotoInAllCommentsSectionDisplayed() {
    return studentPhotoInAllCommentsSection.isDisplayed();
  }

  public boolean isReviewerPhotoInAllCommentsSectionDisplayed() {
    return reviewerPhotoInAllCommentsSection.isDisplayed();
  }

  public boolean isStudentCommentInAllCommentsSectionDisplayed() {
    return studentCommentInAllCommentsSection.isDisplayed();
  }

  public boolean isReviewerCommentInAllCommentsSectionDisplayed() {
    return reviewerCommentInAllCommentsSection.isDisplayed();
  }
}
