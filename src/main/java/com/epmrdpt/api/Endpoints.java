package com.epmrdpt.api;

public class Endpoints {

  private Endpoints() {
  }

  private static final String API = "/api";
  private static final String V1 = "/v1";

  public static final String USER_MANAGEMENT = API + "/user-management";
  public static final String USER_MANAGEMENT_ROLES = USER_MANAGEMENT + "/{userId}/roles";
  public static final String USER_MANAGEMENT_INFO = USER_MANAGEMENT + "/{userId}/info";
  public static final String USER_MANAGEMENT_ROLES_LIST = USER_MANAGEMENT + "/roles-list";
  public static final String USER_MANAGEMENT_FILTER = USER_MANAGEMENT + "/filter";

  public static final String STUDENT_LEARNING = API + "/studentLearning";
  public static final String STUDENT_LEARNING_EVENTS = STUDENT_LEARNING + "/events";

  public static final String MESSAGES = API + "/messages";
  public static final String MESSAGES_SEARCH = MESSAGES + "/search";

  public static final String PROFILE = API + "/profile";
  public static final String PROFILE_BASIC_INFO = PROFILE + "/basic-info";
  public static final String PROFILE_VERIFICATION_EMAIL = PROFILE + "/verification-email";
  public static final String PROFILE_CONTACTS_INFO = PROFILE + "/contacts-info";
  public static final String PROFILE_EDUCATIONS = PROFILE + "/educations";
  public static final String PROFILE_WORK_EXPERIENCES = PROFILE + "/work-experiences";
  public static final String PROFILE_ENGLISH_INFO = PROFILE + "/english-info";
  public static final String PROFILE_ENGLISH_TEST_RESULT = PROFILE + "/english-test-result";
  public static final String PROFILE_STUDENT_GROUPS = PROFILE + "/student-groups";
  public static final String PROFILE_APPLICATIONS = PROFILE + "/applications";
  public static final String PROFILE_CURRENT_TESTING = PROFILE + "/current-testing";
  public static final String PROFILE_PHOTO = PROFILE + "/photo";
  public static final String PROFILE_UPDATE_PHOTO = PROFILE + "/update-photo";
  public static final String PROFILE_USER_SKILLS = PROFILE + "/user-skills";

  public static final String APPLICATIONS = API + "/applications";

  public static final String AUTH_CURRENT = API + "/users/current";

  public static final String ASSIGNMENT_CONTAINER = API + "/AssignmentContainer";
  public static final String ASSIGNMENT_CONTAINERS_ALL_LIST = ASSIGNMENT_CONTAINER + "/all";
  public static final String ASSIGNMENT_CONTAINERS_ACTIVE_LIST = ASSIGNMENT_CONTAINER + "/active";
  public static final String ASSIGNMENT_CONTAINER_BY_ID = ASSIGNMENT_CONTAINER + "/{containerId}";
  public static final String ASSIGNMENT_CONTAINER_ACTIVE =
      ASSIGNMENT_CONTAINER + "/{containerId}/active";
  public static final String ASSIGNMENT_CONTAINER_LINK =
      ASSIGNMENT_CONTAINER + "/{containerId}/{planOwnerId}/link";
  public static final String ASSIGNMENT_CONTAINER_TAKE_TEST =
      ASSIGNMENT_CONTAINER + "/take-test/{takeTestId}";

  public static final String TRAINING_PROCESS = API + "/training-process";
  public static final String TRAINING_PROCESS_USER_DATA = TRAINING_PROCESS + "/user/data";
  public static final String TRAINING_PROCESS_GRADUATE_JOURNAL =
      TRAINING_PROCESS + "/graduate-journal";
  public static final String TRAINING_PROCESS_GRADUATE_JOURNAL_STATUS =
      TRAINING_PROCESS_GRADUATE_JOURNAL + "/status";
  public static final String TRAINING_PROCESS_GRADUATE_JOURNAL_EXCEL =
      TRAINING_PROCESS_GRADUATE_JOURNAL + "/excel";
  public static final String TRAINING_PROCESS_GRADUATE_JOURNAL_STATUS_COLUMN =
      TRAINING_PROCESS_GRADUATE_JOURNAL + "/status-column";
  public static final String TRAINING_PROCESS_ASSIGNMENT_GET_HISTORY =
      TRAINING_PROCESS + "/assignment/history";
  public static final String TRAINING_PROCESS_FINAL_COMMENT_CONTROLLER_GET =
      TRAINING_PROCESS + "/final-comment/all";
  public static final String TRAINING_PROCESS_FINAL_COMMENT_CONTROLLER_POST =
      TRAINING_PROCESS + "/final-comment";
  public static final String TRAINING_PROCESS_FINAL_COMMENT_CONTROLLER_DELETE =
      TRAINING_PROCESS + "/final-comment/{commentId}";
  public static final String TRAINING_PROCESS_BREADCRUMBS_CONTROLLER =
      API + "/training-process/breadcrumbs/by-group?groupId={groupId}";
  public static final String SKILLS = API + "/skills";
  public static final String SKILLS_SHORT = SKILLS + "/short";
  public static final String SKILLS_EXTENDED = SKILLS + "/extended";
  public static final String SKILLS_PICTURES = SKILLS + "/{id}/pictures";

  public static final String SKILLS_DESCRIPTIONS = API + "/skills-descriptions";
  public static final String SKILLS_DESCRIPTIONS_BY_STRING_ID = SKILLS_DESCRIPTIONS + "/{stringId}";
  public static final String SKILLS_DESCRIPTIONS_BY_ID_GET_LOCALIZED = SKILLS_DESCRIPTIONS + "/get-localized";
  public static final String SKILLS_DESCRIPTIONS_BY_ID_IS_LOCALIZED = SKILLS_DESCRIPTIONS + "/is-localized";
  public static final String SKILLS_DESCRIPTIONS_ACTIVE = SKILLS_DESCRIPTIONS + "/active";

  public static final String USER_ACCESS = API + "/user-access";
  public static final String USER_ACCESS_HEADER_LINKS = USER_ACCESS + "/header-links";
  public static final String USER_ACCESS_USER_ROLE = USER_ACCESS + "/user-role";

  public static final String REGISTRATION_WIZARD = API + "/registration-wizard";
  public static final String REGISTRATION_WIZARD_TRAINING_SURVEY = REGISTRATION_WIZARD +
      "/training-survey";
  public static final String REGISTRATION_WIZARD_EDUCATION_DATA = REGISTRATION_WIZARD +
      "/education-data";

  public static final String SEARCH_USER_SEARCH = API + "/user-search";

  public static final String LOCATIONS = API + "/locations";
  public static final String LOCATIONS_COUNTRIES = LOCATIONS + "/countries";
  public static final String LOCATIONS_ALL_COUNTRIES = LOCATIONS + "/all-countries";
  public static final String LOCATIONS_CITIES = LOCATIONS + "/{countryId}/cities";
  public static final String LOCATIONS_ADDRESSES = LOCATIONS + "/addresses";
  public static final String LOCATIONS_CITIES_ID_UNIVERSITIES =
      LOCATIONS + "/cities/%s/universities";
  public static final String LOCATIONS_CURRENT_USER_LOCATION = LOCATIONS + "/current-user-location";
  public static final String LOCATIONS_GET_DOMAIN_COUNTRY_ID = LOCATIONS + "/get-domain-country-id";

  public static final String HASHTAGS = API + "/hashtags";
  public static final String HASHTAGS_GET_ALL = HASHTAGS + "/get-all";
  public static final String HASHTAGS_GET_ALL_WITH_NEWS = HASHTAGS + "/get-all-with-news";
  public static final String HASHTAGS_CREATE = HASHTAGS + "/create";
  public static final String HASHTAGS_CREATE_MANY = HASHTAGS + "/create-many";
  public static final String HASHTAGS_DELETE = HASHTAGS + "/delete";

  public static final String DREAM_TRAINING = API + "/dreamtraining";

  public static final String FAQ = API + "/faq";

  public static final String FEEDBACK_GET_MODEL = API + "/feedback/getModel";

  public static final String DEPARTMENT_AFFILIATE_USER_DATA = API + "/affiliate/user-data";

  public static final String TRAINING_CENTER_MANAGEMENT = API + "/center-management";
  public static final String TRAINING_CENTER_MANAGEMENT_STATUSES =
      TRAINING_CENTER_MANAGEMENT + "/statuses";
  public static final String TRAINING_CENTER_MANAGEMENT_PUBLISHED_CENTERS =
      TRAINING_CENTER_MANAGEMENT + "/published-centers";
  public static final String TRAINING_CENTER_MANAGEMENT_CENTERS_LIGHT =
      TRAINING_CENTER_MANAGEMENT + "/centers-light";
  public static final String TRAINING_CENTER_MANAGEMENT_COUNTRIES_WITH_CITIES =
      TRAINING_CENTER_MANAGEMENT + "/countries-with-cities";
  public static final String TRAINING_CENTER_MANAGEMENT_IMAGE =
      TRAINING_CENTER_MANAGEMENT + "/%s" + "/image";

  public static final String PREFERENCES = API + "/preferences";
  public static final String PREFERENCES_USER_DATA = PREFERENCES + "/user-data";
  public static final String PREFERENCES_COMMON_DATA = PREFERENCES + "/common-data";

  public static final String GROUP_SEARCH_FILTER_DATA = TRAINING_PROCESS + "/group-search/filter-data";

  public static final String TRAININGS = API + "/trainings";
  public static final String TRAININGS_REGISTERED = TRAININGS + "/registered";
  public static final String TRAININGS_DISABLED = TRAININGS + "/disabled";
  public static final String TRAININGS_ACTIVE = TRAININGS + "/active";
  public static final String TRAININGS_ID_PARTIAL_DETAILS = TRAININGS + "/%s" + "/partial-details";
  public static final String TRAININGS_V1_DETAILS = API + V1 + "/trainings/details";
  public static final String TRAININGS_ID_USER = TRAININGS + "/%s" + "/user";
  public static final String TRAININGS_PLAN_FILTER_DATA = TRAININGS + "/plan-filter-data";
  public static final String TRAININGS_FREEMIUM = TRAININGS + "/freemium";
  public static final String TRAININGS_STUDENT_STATUSES = TRAININGS + "/students-statuses";
  public static final String TRAININGS_CHECK_STATUS = TRAININGS + "/check-status";
  public static final String TRAININGS_INTEGRATIONS_INFO = TRAININGS + "/integrations-info";
  public static final String TRAININGS_RECOMMENDED_PLANS = TRAININGS + "/recommended-plans";
  public static final String TRAININGS_ID_DETAILS_USER = TRAININGS + "/%s" + "/details/user";

  public static final String TRAINING_PROCESS_OFFLINE_TASKS = TRAINING_PROCESS + "/offline-tasks";

  public static final String TRAINING_PROCESS_EXTERNAL_TASK_CONTAINER = TRAINING_PROCESS + "/external-task-container";

  public static final String TRAINING_PROCESS_STUDENTS_STATUS = TRAINING_PROCESS + "/student-status/{groupId}";

  public static final String NEWS = API + "/news";
  public static final String NEWS_PREVIEW = NEWS + "/preview";
  public static final String NEWS_ID_LIGHT = NEWS + "/%s/light";
  public static final String NEWS_GET_ALL = NEWS + "/get-all-old";
  public static final String NEWS_GET_AUTHORS = NEWS + "/get-authors";
  public static final String NEWS_GET_NEWS_IMAGE_ID_IMAGE_NAME = NEWS + "/get-news-image/%s/%s";
  public static final String NEWS_GET_IMAGE_NAMES = NEWS + "/get-image-names";
  public static final String NEWS_GET_CATEGORIES_WITH_NEWS = NEWS + "/get-categories-with-news";
  public static final String NEWS_GET_ALL_NEWS_CATEGORIES = NEWS + "/get-all-news-categories";
  public static final String NEWS_GET_NEWS_ID = NEWS + "/%s";

  public static final String TRAINING_PROCESS_TRAINERS = TRAINING_PROCESS + "/trainers";
  public static final String TRAINING_PROCESS_GROUPS = TRAINING_PROCESS + "/groups/{trainerId}/{trainingPlanId}";

  public static final String TRAINING_MANAGEMENT = API + "/training-management";
  public static final String SURVEY_TESTING = TRAINING_MANAGEMENT + "/plan/survey-testing";
  public static final String GENERAL_TAB_CONTROLLER = TRAINING_MANAGEMENT + "/plan/general";
  public static final String TRAINING_MANAGEMENT_FILTER_CONTROLLER = TRAINING_MANAGEMENT + "/filter";
  public static final String TRAINING_MANAGEMENT_PARTICIPANT_STATUS
      = TRAINING_MANAGEMENT + "/{planId}/participant-statuses";
  public static final String TRAINING_MANAGEMENT_PARTICIPANT_STATUS_REASONS
      = TRAINING_MANAGEMENT + "/{planId}/participant-statuses/reasons";
  public static final String TRAINING_MANAGEMENT_STUDENT_GROUP_CONTROLLER_STUDENT_GROUPS =
      TRAINING_MANAGEMENT + "/{planId}/classes/{classId}/student-groups";
  public static final String TRAINING_MANAGEMENT_STUDENT_GROUP_CONTROLLER_STUDENT_GROUPS_LIST =
      TRAINING_MANAGEMENT + "/{planId}/classes/{classId}/student-groups/list";
  public static final String TRAINING_MANAGEMENT_DESCRIPTION_TAB_CONTROLLER_TRAININGS =
      TRAINING_MANAGEMENT + "/plan/description/trainings";
  public static final String TRAINING_MANAGEMENT_DESCRIPTION_TAB_CONTROLLER_PLAN_ID =
      TRAINING_MANAGEMENT + "/plan/description/{planId}";
  public static final String TRAINING_MANAGEMENT_DESCRIPTION_TAB_PLAN_ID_COPY
      = TRAINING_MANAGEMENT_DESCRIPTION_TAB_CONTROLLER_PLAN_ID + "/copy";
  public static final String TRAINING_MANAGEMENT_FILTER_CONTROLLER_COUNTRY =
      TRAINING_MANAGEMENT_FILTER_CONTROLLER + "/country";
  public static final String TRAINING_MANAGEMENT_FILTER_CONTROLLER_SKILL =
      TRAINING_MANAGEMENT_FILTER_CONTROLLER + "/skill";
  public static final String TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_SKILLS =
      GENERAL_TAB_CONTROLLER + "/skills";
  public static final String TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_PLAN_ID =
      GENERAL_TAB_CONTROLLER + "/{planId}";
  public static final String TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_CURRENCY =
      GENERAL_TAB_CONTROLLER + "/currency";
  public static final String TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_UNIVERSITY =
      GENERAL_TAB_CONTROLLER + "/university";
  public static final String TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_CONTACT_CENTERS =
      GENERAL_TAB_CONTROLLER + "/contact-centers";
  public static final String TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_LOCATION =
      GENERAL_TAB_CONTROLLER + "/location";
  public static final String TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_OWNER =
      GENERAL_TAB_CONTROLLER + "/owner";
  public static final String TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_SUPERVISORS =
      GENERAL_TAB_CONTROLLER + "/supervisors";
  public static final String TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_LAB =
      GENERAL_TAB_CONTROLLER + "/lab";
  public static final String TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_PERMITTED_LOCATIONS =
      GENERAL_TAB_CONTROLLER + "/permitted-locations";
  public static final String TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_PLAN_ID_PUBLIC_ID =
      GENERAL_TAB_CONTROLLER + "/{planId}/public-id";
  public static final String TRAINING_MANAGEMENT_GENERAL_TAB_CONTROLLER_PLAN_ID_COPY =
      GENERAL_TAB_CONTROLLER + "/{planId}/copy";
  public static final String TRAINING_MANAGEMENT_GROUP_TRAINER_CONTROLLER_TRAINERS =
      TRAINING_MANAGEMENT + "/{planId}/groups/{groupId}/trainers";
  public static final String TRAINING_MANAGEMENT_GROUP_STATUS_CONTROLLER_STATUSES =
      TRAINING_MANAGEMENT + "/{planId}/groups/{groupId}/status";
  public static final String TRAINING_MANAGEMENT_GROUP_TRAINER_CONTROLLER_TRAINERS_FIND =
      TRAINING_MANAGEMENT + "/{planId}/groups/{groupId}/trainers/find";
  public static final String TRAINING_MANAGEMENT_MENTOR_CONTROLLER_GROUP_ID =
      TRAINING_MANAGEMENT +  "/mentor/{groupId}";
  public static final String TRAINING_MANAGEMENT_GROUP_PARTICIPANT_CONTROLLER_PARTICIPANTS =
      TRAINING_MANAGEMENT + "/{planId}/groups/{groupId}/participants";
  public static final String TRAINING_MANAGEMENT_GROUP_PARTICIPANT_CONTROLLER_PARTICIPANTS_LIST =
      TRAINING_MANAGEMENT_GROUP_PARTICIPANT_CONTROLLER_PARTICIPANTS +  "/list";
  public static final String TRAINING_MANAGEMENT_PARTICIPANT_CONTROLLER_PARTICIPANTS_COUNTRIES =
      TRAINING_MANAGEMENT +  "/{planId}/participants/countries";
  public static final String TRAINING_MANAGEMENT_PARTICIPANT_CONTROLLER_PARTICIPANTS_ENGLISH_LEVELS =
      TRAINING_MANAGEMENT +  "/{planId}/participants/english-levels";
  public static final String TRAINING_MANAGEMENT_PARTICIPANT_CONTROLLER_PARTICIPANTS = TRAINING_MANAGEMENT +  "/{planId}/participants";
  public static final String TRAINING_MANAGEMENT_GROUP_CONTROLLER_TRAINING_GET_GROUPS =
      TRAINING_MANAGEMENT + "/{planId}/groups?sortColumn=GroupName&sortDirection=Unspecified";
  public static final String TRAINING_MANAGEMENT_GROUP_CONTROLLER_GROUPS =
      TRAINING_MANAGEMENT + "/{planId}/groups";
  public static final String TRAINING_MANAGEMENT_GROUP_CONTROLLER_GROUP_WITH_ID =
      TRAINING_MANAGEMENT + "/{planId}/groups/{groupId}";
  public static final String TRAINING_MANAGEMENT_GROUP_CONTROLLER_SUMMARY =
      TRAINING_MANAGEMENT + "/{planId}/groups/{groupId}/summary";
  public static final String TRAINING_MANAGEMENT_PARTICIPANT_GROUP_CONTROLLER_PARTICIPANT_GROUPS =
      TRAINING_MANAGEMENT + "/{planId}/participant-groups";
  public static final String TRAINING_MANAGEMENT_NOTIFICATION_CONTROLLER =
      "api/training-management/notification/general-reject?planId=%s";
  public static final String TRAINING_MANAGEMENT_SURVEY_TESTING_DEFAULT =
      SURVEY_TESTING + "/default";
  public static final String TRAINING_MANAGEMENT_SURVEY_TESTING_ELEARN =
      SURVEY_TESTING + "/elearn";
  public static final String TRAINING_MANAGEMENT_SURVEY_TESTING_PLAN_ID =
      SURVEY_TESTING + "/{planId}";
  public static final String TRAINING_MANAGEMENT_SURVEY_TESTING_PLAN_ID_COPY =
      SURVEY_TESTING + "/{planId}/copy";
  public static final String TRAINING_MANAGEMENT_SURVEY_USER_ANSWERS =
      TRAINING_MANAGEMENT + "/survey/user-answers";
  public static final String TRAINING_MANAGEMENT_TRAINING_STATUS_CONTROLLER =
      TRAINING_MANAGEMENT + "/plan/status/{planId}";
  public static final String TRAINING_MANAGEMENT_STUFFING_DESK_INTEGRATION_CONTROLLER_PLAN_ID =
      TRAINING_MANAGEMENT + "/plan/sd/{planId}";
  public static final String TRAINING_MANAGEMENT_STUFFING_DESK_INTEGRATION_CONTROLLER_PLAN_ID_All =
      TRAINING_MANAGEMENT + "/plan/sd/{planId}/all";
  public static final String TRAINING_MANAGEMENT_STUFFING_DESK_TAG_PLAN_ID =
      TRAINING_MANAGEMENT + "/staffing-tag/{planId}";
  public static final String TRAINING_MANAGEMENT_STUFFING_DESK_TAG_CONTROLLER_ACTIVE =
      TRAINING_MANAGEMENT + "/staffing-tag/active";
  public static final String TRAINING_MANAGEMENT_REGISTRATION_CONTROLLER_All =
      TRAINING_MANAGEMENT + "/plan/registration/all";
  public static final String TRAINING_MANAGEMENT_REGISTRATION_CONTROLLER_REGISTRATION =
      TRAINING_MANAGEMENT + "/plan/registration?templates=%s";
  public static final String TRAINING_MANAGEMENT_REGISTRATION_CONTROLLER_REGISTRATION_PLAN_ID =
      TRAINING_MANAGEMENT + "/plan/registration/{planId}";
  public static final String TRAINING_MANAGEMENT_PLAN_CONTROLLER_PLAN_ID =
      TRAINING_MANAGEMENT + "/plans/{planId}";
  public static final String TRAINING_MANAGEMENT_PLAN_CONTROLLER_PLANS =
      TRAINING_MANAGEMENT + "/plans";
  public static final String TRAINING_MANAGEMENT_FILTER_TYPE = TRAINING_MANAGEMENT_FILTER_CONTROLLER + "/type";
  public static final String TRAINING_MANAGEMENT_AUTO_REPLY_TAB_CONTROLLER_AUTOMATED =
      TRAINING_MANAGEMENT + "/plan/automated";
  public static final String TRAINING_MANAGEMENT_AUTO_REPLY_TAB_CONTROLLER_AUTOMATED_PLANID =
      TRAINING_MANAGEMENT_AUTO_REPLY_TAB_CONTROLLER_AUTOMATED + "/{planId}";
  public static final String TRAINING_MANAGEMENT_AUTO_REPLY_TAB_CONTROLLER_AUTOMATED_DEFAULT =
      TRAINING_MANAGEMENT_AUTO_REPLY_TAB_CONTROLLER_AUTOMATED + "/default";

  public static final String TRAINING_PROCESS_ONLINE_TASKS = TRAINING_PROCESS + "/online-tasks";
  public static final String TRAINING_PROCESS_TASK_JOURNAL = TRAINING_PROCESS + "/task-journal";
  public static final String TRAINING_PROCESS_TASK_JOURNAL_ALL_ASSIGNMENT =
      TRAINING_PROCESS_TASK_JOURNAL + "/all-assignment";
  public static final String TRAINING_PROCESS_TASK_JOURNAL_OFFLINE_TASKS_MARK_FORM =
      TRAINING_PROCESS_TASK_JOURNAL + "/{offlineTaskId}/mark-form";
  public static final String TRAINING_PROCESS_TASK_JOURNAL_TASK_COMMENTS =
      TRAINING_PROCESS_TASK_JOURNAL + "/task-comments";
  public static final String TRAINING_PROCESS_EVENTS = TRAINING_PROCESS + "/events";
  public static final String TRAINING_PROCESS_EVENTS_BY_TRAINER = TRAINING_PROCESS_EVENTS + "/by-trainer";
  public static final String TRAINING_PROCESS_EVENTS_FORM_DATA_BY_GROUPS =
      TRAINING_PROCESS_EVENTS + "/form-data/by-groups";
  public static final String TRAINING_PROCESS_EVENTS_CALENDAR_DATES = TRAINING_PROCESS_EVENTS + "/calendar-dates";

  public static final String EVENT_MANAGEMENT = API + "/event-management";
  public static final String EVENT_MANAGEMENT_USER_DATA = EVENT_MANAGEMENT + "/user/data";
  public static final String EVENT_MANAGEMENT_SURVEY_USER_ANSWERS = EVENT_MANAGEMENT + "/survey/user-answers";
  public static final String EVENT_MANAGEMENT_SURVEY_EVENT_ID_STATUS = EVENT_MANAGEMENT + "/{eventId}/status ";
  public static final String EVENT_MANAGEMENT_STAFFING_TAGS_EVENT_ID = EVENT_MANAGEMENT + "/staffing-tags/{eventId}";
  public static final String EVENT_MANAGEMENT_STAFFING_TAGS_ACTIVE = EVENT_MANAGEMENT + "/staffing-tags/active";
  public static final String EVENT_MANAGEMENT_EVENT_ID_GENERAL_INFORMATION =
      EVENT_MANAGEMENT + "/{eventId}/general-information";
  public static final String EVENT_MANAGEMENT_AUTO_REPLY = EVENT_MANAGEMENT + "/autoreply";
  public static final String EVENT_MANAGEMENT_LIST = EVENT_MANAGEMENT + "/list";
  public static final String EVENT_MANAGEMENT_TAGS = EVENT_MANAGEMENT + "/tags";
  public static final String EVENT_MANAGEMENT_LANGUAGES = EVENT_MANAGEMENT + "/languages";
  public static final String EVENT_MANAGEMENT_FILTER_CONTROLLER = EVENT_MANAGEMENT + "/filter";
  public static final String EVENT_MANAGEMENT_FILTER_CONTROLLER_SKILL =
      EVENT_MANAGEMENT_FILTER_CONTROLLER + "/skills";
  public static final String EVENT_MANAGEMENT_FILTER_CONTROLLER_COUNTRY =
      EVENT_MANAGEMENT_FILTER_CONTROLLER + "/countries";
  public static final String EVENT_MANAGEMENT_GENERAL_HASH_ID = EVENT_MANAGEMENT + "/{eventId}/general/hash-id";
  public static final String EVENT_MANAGEMENT_FILTER_CONTROLLER_STATUS =
      EVENT_MANAGEMENT_FILTER_CONTROLLER + "/statuses";
}
