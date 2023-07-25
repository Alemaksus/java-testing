package com.epmrdpt.services;

import com.epmrdpt.screens.TasksPopUpScreenOnLearningPageScreen;
import com.epmrdpt.screens.TasksTabOnLearningPageScreen;

public class LearningService {

  private TasksTabOnLearningPageScreen tasksTabOnLearningPageScreen = new TasksTabOnLearningPageScreen();

  public TasksPopUpScreenOnLearningPageScreen navigateToTaskOfEpamTrainingInUrgentTab(
      String groupName,
      String taskName) {
    tasksTabOnLearningPageScreen
        .clickTasksTab()
        .waitTaskSectionForVisibility()
        .clickEpamTrainingTab();
    return applyPaginationAndOpenTaskPopUpInUrgentTab(groupName, taskName);
  }

  public TasksPopUpScreenOnLearningPageScreen navigateToTaskOfDepartmentAffiliateInUrgentTab(
      String className,
      String taskName) {
    tasksTabOnLearningPageScreen
        .clickTasksTab()
        .waitTaskSectionForVisibility()
        .clickDepartmentAffiliateTab();
    return applyPaginationAndOpenTaskPopUpInUrgentTab(className, taskName);
  }

  private TasksPopUpScreenOnLearningPageScreen applyPaginationAndOpenTaskPopUpInUrgentTab(
      String groupName, String taskName) {
    if (tasksTabOnLearningPageScreen.isPaginationBlockDisplayedShortTimeOut()) {
      while (!tasksTabOnLearningPageScreen.isNextInPaginationButtonDisabled()) {
        if (tasksTabOnLearningPageScreen.isTaskByNameInUrgentTabDisplayed(groupName, taskName)) {
          break;
        }
        tasksTabOnLearningPageScreen.clickNextInPaginationButton();
      }
    }
    return tasksTabOnLearningPageScreen
        .clickTaskByNameInUrgentTab(groupName, taskName)
        .waitPopUpForVisibility();
  }

  public TasksPopUpScreenOnLearningPageScreen navigateToTaskInAllTasksTab(String groupName,
      String taskName) {
    return tasksTabOnLearningPageScreen
        .clickTasksTab()
        .waitTaskSectionForVisibility()
        .clickAllTasksTab()
        .clickEpamTrainingTab()
        .clickGroupByName(groupName)
        .clickTaskByNameInAllTasksTab(groupName, taskName)
        .waitPopUpForVisibility();
  }
}
