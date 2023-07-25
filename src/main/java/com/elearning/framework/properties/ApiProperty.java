package com.epmrdpt.framework.properties;

public enum ApiProperty {

  TRAINING_CENTER_ID("training_center.id"),
  TRAINING_ID("training.id"),
  ASSIGNMENT_CONTAINER_ID("assignment_container.id"),
  PLAN_OWNER_ID("plan_owner.id"),
  TAKE_TEST_ID("take_test.id"),
  NEWS_ID("news.id"),
  NEWS_IMAGE_NAME("news.image_name"),
  ASSIGNMENT_ID("assignment.id"),
  TASK_ID("task.id"),
  TASK_JOURNAL_CONTROLLER_OFFLINE_TASK_ID("task_journal_controller.offline_task_id"),
  CLOSED_TRAINING_REGISTRATION_LINK("closed_training_registration.link"),
  CLOSED_REGISTRATION_EVENT_DETAILS_LINK("closed_registration_event_details.link"),
  RESPONSE_USER_MANAGEMENT_GET_USER_ROLES("response.user_management.get_user_roles"),
  GROUP_ID("group.id"),
  EVENT_MANAGER_ID("event_manager.id");

  private String propertyName;

  ApiProperty(String propertyName) {
    this.propertyName = propertyName;
  }

  public String getPropertyName() {
    return propertyName;
  }
}
