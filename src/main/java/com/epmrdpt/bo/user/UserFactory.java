package com.epmrdpt.bo.user;

import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_FACTORY_REGISTRATION_WIZARD_USER_EDUCATIONAL_ESTABLISHMENT_WITHOUT_ABBREVIATION_TWO;
import static com.epmrdpt.framework.properties.UserPropertyConst.*;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_FACTORY_REGISTRATION_WIZARD_USER_ASSIGNMENT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_FACTORY_REGISTRATION_WIZARD_USER_ASSIGNMENT_TWO;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_FACTORY_REGISTRATION_WIZARD_USER_CITY_OF_RESIDENCE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_FACTORY_REGISTRATION_WIZARD_USER_CITY_OF_STUDY;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_FACTORY_REGISTRATION_WIZARD_USER_CITY_OF_STUDY_TWO;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_FACTORY_REGISTRATION_WIZARD_USER_DEGREE_INFORMATION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_FACTORY_REGISTRATION_WIZARD_USER_DEGREE_INFORMATION_TWO;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_FACTORY_REGISTRATION_WIZARD_USER_DEPARTMENT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_FACTORY_REGISTRATION_WIZARD_USER_DEPARTMENT_TWO;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_FACTORY_REGISTRATION_WIZARD_USER_EDUCATIONAL_ESTABLISHMENT;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_FACTORY_REGISTRATION_WIZARD_USER_EDUCATIONAL_ESTABLISHMENT_WITHOUT_ABBREVIATION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_FACTORY_REGISTRATION_WIZARD_USER_EDUCATION_FORM;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_FACTORY_REGISTRATION_WIZARD_USER_EDUCATION_FORM_TWO;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_FACTORY_REGISTRATION_WIZARD_USER_ENGLISH_LEVEL;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_FACTORY_REGISTRATION_WIZARD_USER_FACULTY;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_FACTORY_REGISTRATION_WIZARD_USER_FACULTY_TWO;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_FACTORY_REGISTRATION_WIZARD_USER_PREFERRED_METHOD_OF_COMMUNICATION;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_FACTORY_REGISTRATION_WIZARD_USER_YEAR;
import static com.epmrdpt.framework.properties.LocalePropertyConst.USER_FACTORY_REGISTRATION_WIZARD_USER_YEAR_TWO;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.UserProperty;
import com.epmrdpt.utils.RandomUtils;
import java.time.LocalDate;
import org.apache.commons.lang3.RandomStringUtils;

public class UserFactory {

  private static final int RANDOM_STRING_LENGTH = 10;
  private static final int RANDOM_STRING_MIDDLE_LENGTH = 50;
  private static final int RANDOM_STRING_LONG_LENGTH = 100;
  private static final int MIN_YEAR = 1980;
  private static final int MIN_PHONE_NUMBER_LENGTH = 7;
  private static final int MAX_PHONE_NUMBER_LENGTH = 8;
  private static final String REGISTRATION_WIZARD_USER_NAME = "Auto";
  private static final String REGISTRATION_WIZARD_USER_LAST_NAME = "Test";
  private static final LocalDate REGISTRATION_WIZARD_USER_BIRTH_DATE = LocalDate.of(1998, 8, 3);
  private static final int REGISTRATION_WIZARD_USER_PHONE_NUMBER = 3578515;
  private static final String REGISTRATION_WIZARD_USER_GRADUATION_YEAR = "2015";
  private static final String REGISTRATION_WIZARD_USER_GRADUATION_YEAR_TWO = "2016";

  public static User withSimplePermission() {
    return new User(UserProperty.getValueOf(USER_LOGIN),
        UserProperty.getValueOf(USER_PASSWORD))
        .setFirstName("User with Simple Permission");
  }

  public static User withSimplePermissionAndEmptyProfile() {
    return new User(UserProperty.getValueOf(USER_LOGIN_OF_EMPTY_PROFILE),
        UserProperty.getValueOf(USER_PASSWORD_OF_EMPTY_PROFILE))
        .setFirstName("User with Simple Permission and Empty Profile");
  }

  public static User withSimplePermissionAndWithoutTrainings() {
    return new User(UserProperty.getValueOf(USER_LOGIN_WITHOUT_TRAININGS),
        UserProperty.getValueOf(USER_PASSWORD_WITHOUT_TRAININGS))
        .setFirstName("User with Simple Permission and without Trainings");
  }

  public static User withSimplePermissionAndWithoutTrainingsNew() {
    return new User(UserProperty.getValueOf(NEW_USER_LOGIN_WITHOUT_TRAININGS),
        UserProperty.getValueOf(NEW_USER_PASSWORD_WITHOUT_TRAININGS))
        .setFirstName("User with Simple Permission and without Trainings");
  }

  public static User withSimplePermissionAndWithTraining() {
    return new User(UserProperty.getValueOf(USER_LOGIN_WITH_TRAINING),
        UserProperty.getValueOf(USER_PASSWORD_WITH_TRAINING))
        .setFirstName("User with Simple Permission and with Training");
  }

  public static User withDepartmentTraining() {
    return new User(UserProperty.getValueOf(USER_LOGIN_WITH_DEPARTMENT_AFFILIATE),
        UserProperty.getValueOf(USER_PASSWORD_WITH_DEPARTMENT_AFFILIATE))
        .setFirstName("User with Department Training");
  }

  public static User randomUserForRegistration() {
    String userName = String
        .format(UserProperty.getValueOf(USER_LOGIN_FOR_REGISTRATION),
            RandomStringUtils.randomAlphabetic(RANDOM_STRING_LENGTH)).toLowerCase();
    return new User(userName,
        UserProperty.getValueOf(USER_PASSWORD_FOR_REGISTRATION))
        .setFirstName("Random User For Registration");
  }

  public static User userWithRandomData() {
    User user = randomUserForRegistration();
    user.setPhoneNumber(Integer.parseInt(
        RandomStringUtils.randomNumeric(MIN_PHONE_NUMBER_LENGTH, MAX_PHONE_NUMBER_LENGTH)));
    user.setFirstName(RandomStringUtils.randomAlphabetic(RANDOM_STRING_LENGTH));
    user.setLastName(RandomStringUtils.randomAlphabetic(RANDOM_STRING_LENGTH));
    user.setNativeName(RandomStringUtils.randomAlphabetic(RANDOM_STRING_LENGTH));
    user.setCompanyName(RandomStringUtils.randomAlphabetic(RANDOM_STRING_LENGTH));
    user.setPosition(RandomStringUtils.randomAlphabetic(RANDOM_STRING_LENGTH));
    user.setBirthDate(RandomUtils.getRandomDate(MIN_YEAR, LocalDate.now().getYear() - 9));
    return user;
  }

  public static User userWithTwoEducationsAndWorkExperiences() {
    User user = new User();
    user.setFirstName(RandomStringUtils.randomAlphabetic(RANDOM_STRING_LENGTH));
    user.setLastName(RandomStringUtils.randomAlphabetic(RANDOM_STRING_LENGTH));
    user.setNativeName(RandomStringUtils.randomAlphabetic(RANDOM_STRING_LENGTH));
    user.setBirthDate(REGISTRATION_WIZARD_USER_BIRTH_DATE);
    user.setCityOfResidence(
        LocaleProperties.getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_CITY_OF_RESIDENCE));
    user.setPhoneNumber(Integer.parseInt(
        RandomStringUtils.randomNumeric(MIN_PHONE_NUMBER_LENGTH, MAX_PHONE_NUMBER_LENGTH)));
    user.setSocialNetwork(RandomStringUtils.randomAlphabetic(RANDOM_STRING_LENGTH));
    user.setPreferredMethodOfCommunication(LocaleProperties
        .getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_PREFERRED_METHOD_OF_COMMUNICATION));
    user.setEnglishLevel(
        LocaleProperties.getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_ENGLISH_LEVEL));
    user.setCityOfStudy(
        LocaleProperties.getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_CITY_OF_STUDY));
    user.setCityOfStudy(
        LocaleProperties.getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_CITY_OF_STUDY_TWO));
    user.setEducationalEstablishment(LocaleProperties
        .getValueOf(
            USER_FACTORY_REGISTRATION_WIZARD_USER_EDUCATIONAL_ESTABLISHMENT_WITHOUT_ABBREVIATION_TWO));
    user.setEducationalEstablishment(LocaleProperties
        .getValueOf(
            USER_FACTORY_REGISTRATION_WIZARD_USER_EDUCATIONAL_ESTABLISHMENT_WITHOUT_ABBREVIATION));
    user.setFaculty(LocaleProperties.getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_FACULTY));
    user.setFaculty(LocaleProperties.getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_FACULTY_TWO));
    user.setDepartment(
        LocaleProperties.getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_DEPARTMENT));
    user.setDepartment(
        LocaleProperties.getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_DEPARTMENT_TWO));
    user.setEducationForm(
        LocaleProperties.getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_EDUCATION_FORM));
    user.setEducationForm(
        LocaleProperties.getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_EDUCATION_FORM_TWO));
    user.setDegreeInformation(
        LocaleProperties.getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_DEGREE_INFORMATION));
    user.setDegreeInformation(
        LocaleProperties.getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_DEGREE_INFORMATION_TWO));
    user.setUniversityCourseYear(
        LocaleProperties.getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_YEAR));
    user.setUniversityCourseYear(
        LocaleProperties.getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_YEAR_TWO));
    user.setGraduationYear(REGISTRATION_WIZARD_USER_GRADUATION_YEAR);
    user.setGraduationYear(REGISTRATION_WIZARD_USER_GRADUATION_YEAR_TWO);
    user.setAssignment(
        LocaleProperties.getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_ASSIGNMENT));
    user.setAssignment(
        LocaleProperties.getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_ASSIGNMENT_TWO));
    user.setCompanyName(RandomStringUtils.randomAlphabetic(RANDOM_STRING_LENGTH));
    user.setCompanyName(RandomStringUtils.randomAlphabetic(RANDOM_STRING_LENGTH));
    user.setPosition(RandomStringUtils.randomAlphabetic(RANDOM_STRING_LENGTH));
    user.setPosition(RandomStringUtils.randomAlphabetic(RANDOM_STRING_LENGTH));
    user.setStartDate(LocalDate.now().minusYears(2));
    user.setEndDate(LocalDate.now().minusMonths(2));
    user.setStartDate(LocalDate.now().minusYears(1));
    user.setEndDate(LocalDate.now().minusMonths(1));
    user.setTillNowCheckBox(false);
    user.setTillNowCheckBox(false);
    user.setAdditionalInformationFromEducationSectionForm(
        RandomStringUtils.randomAlphabetic(RANDOM_STRING_LONG_LENGTH));
    user.setProfessionalActivitiesFromEducationSectionForm(
        RandomStringUtils.randomAlphabetic(RANDOM_STRING_LONG_LENGTH));
    user.setAdditionalInformationFromWorkExperienceSectionForm(
        RandomStringUtils.randomAlphabetic(RANDOM_STRING_MIDDLE_LENGTH));
    user.setAdditionalInformationFromWorkExperienceSectionForm(
        RandomStringUtils.randomAlphabetic(RANDOM_STRING_MIDDLE_LENGTH));
    return user;
  }

  public static User userForWorkExperienceForm() {
    User user = new User();
    user.setCompanyName(RandomStringUtils.randomAlphabetic(RANDOM_STRING_LENGTH));
    user.setPosition(RandomStringUtils.randomAlphabetic(RANDOM_STRING_LENGTH));
    user.setStartDate(LocalDate.now().minusDays(2));
    user.setEndDate(LocalDate.now().minusDays(1));
    return user;
  }

  public static User userForRegistrationWizard() {
    return new User(UserProperty.getValueOf(USER_LOGIN_FOR_REGISTRATION_WIZARD),
        UserProperty.getValueOf(USER_PASSWORD_FOR_REGISTRATION_WIZARD))
        .setFirstName("User For Registration Wizard");
  }

  public static User userWithDataForRegistrationWizard() {
    User user = userForRegistrationWizard();
    user.setEmail(UserProperty.getValueOf(USER_LOGIN_FOR_REGISTRATION_WIZARD));
    user.setFirstName(REGISTRATION_WIZARD_USER_NAME);
    user.setLastName(REGISTRATION_WIZARD_USER_LAST_NAME);
    user.setBirthDate(REGISTRATION_WIZARD_USER_BIRTH_DATE);
    user.setCityOfResidence(
        LocaleProperties.getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_CITY_OF_RESIDENCE));
    user.setPhoneNumber(REGISTRATION_WIZARD_USER_PHONE_NUMBER);
    user.setCityOfStudy(
        LocaleProperties.getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_CITY_OF_STUDY));
    user.setEducationalEstablishment(LocaleProperties
        .getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_EDUCATIONAL_ESTABLISHMENT));
    user.setFaculty(
        LocaleProperties.getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_FACULTY));
    user.setDepartment(
        LocaleProperties.getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_DEPARTMENT));
    user.setEducationForm(
        LocaleProperties.getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_EDUCATION_FORM));
    user.setDegreeInformation(LocaleProperties
        .getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_DEGREE_INFORMATION));
    user.setUniversityCourseYear(LocaleProperties
        .getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_YEAR));
    user.setGraduationYear(REGISTRATION_WIZARD_USER_GRADUATION_YEAR);
    user.setAssignment(
        LocaleProperties.getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_ASSIGNMENT));
    user.setEnglishLevel(
        LocaleProperties.getValueOf(USER_FACTORY_REGISTRATION_WIZARD_USER_ENGLISH_LEVEL));
    return user;
  }

  public static User userWithDataForSimplifiedRegistrationWizard() {
    User user = userForRegistrationWizard();
    user.setEmail(UserProperty.getValueOf(USER_LOGIN_FOR_REGISTRATION_WIZARD));
    user.setFirstName(REGISTRATION_WIZARD_USER_NAME);
    user.setLastName(REGISTRATION_WIZARD_USER_LAST_NAME);
    user.setPhoneNumber(REGISTRATION_WIZARD_USER_PHONE_NUMBER);
    return user;
  }

  public static User asTrainingManager() {
    return new User(UserProperty.getValueOf(USER_LOGIN_AS_TRAINING_MANAGER),
        UserProperty.getValueOf(USER_PASSWORD_AS_TRAINING_MANGER))
        .setFirstName("Training Manager");
  }

  public static User forPasswordReset() {
    return new User(UserProperty.getValueOf(USER_LOGIN_FOR_PASSWORD_RESET),
        UserProperty.getValueOf(USER_PASSWORD_FOR_PASSWORD_RESET))
        .setFirstName("User for Password Reset");
  }

  public static User asTrainer() {
    return new User(UserProperty.getValueOf(USER_LOGIN_AS_TRAINER),
        UserProperty.getValueOf(USER_PASSWORD_AS_TRAINER))
        .setFirstName("Trainer");
  }

  public static User asSuperAdmin() {
    return new User(UserProperty.getValueOf(USER_LOGIN_AS_SUPER_ADMIN),
        UserProperty.getValueOf(USER_PASSWORD_AS_SUPER_ADMIN))
        .setFirstName("Super Admin");
  }

  public static User asAdmin() {
    return new User(UserProperty.getValueOf(USER_LOGIN_AS_ADMIN),
        UserProperty.getValueOf(USER_PASSWORD_AS_ADMIN))
        .setFirstName("Admin");
  }

  public static User forLanguageSwitch() {
    return new User(UserProperty.getValueOf(USER_LOGIN_FOR_LANGUAGE_SWITCH),
        UserProperty.getValueOf(USER_PASSWORD_FOR_LANGUAGE_SWITCH))
        .setFirstName("User for Language Switch");
  }

  public static User forNotificationLanguageSwitch() {
    return new User(UserProperty.getValueOf(USER_LOGIN_FOR_NOTIFICATION_LANGUAGE_SWITCH),
        UserProperty.getValueOf(USER_PASSWORD_FOR_NOTIFICATION_LANGUAGE_SWITCH))
        .setFirstName("User for Notification Language Switch");
  }

  public static User asTrainingDirector() {
    return new User(UserProperty.getValueOf(USER_LOGIN_AS_TRAINING_DIRECTOR),
        UserProperty.getValueOf(USER_PASSWORD_AS_TRAINING_DIRECTOR))
        .setFirstName("Training Director");
  }

  public static User asTrainingDirectorLocatedRoleAutoTestCity() {
    return new User(UserProperty.getValueOf(USER_LOGIN_AS_TRAINING_DIRECTOR_AUTO_TEST_CITY),
        UserProperty.getValueOf(USER_PASSWORD_AS_TRAINING_DIRECTOR_AUTO_TEST_CITY))
        .setFirstName("Training Director located in AutoTestCity");
  }

  public static User asRecruiter() {
    return new User(UserProperty.getValueOf(USER_LOGIN_AS_RECRUITER),
        UserProperty.getValueOf(USER_PASSWORD_AS_RECRUITER))
        .setFirstName("Recruiter");
  }

  public static User asRecruiterLocatedRoleAutoTestCity() {
    return new User(UserProperty.getValueOf(USER_LOGIN_AS_RECRUITER_AUTO_TEST_CITY),
        UserProperty.getValueOf(USER_PASSWORD_AS_RECRUITER_AUTO_TEST_CITY))
        .setFirstName("Recruiter located in AutoTestCity");
  }

  public static User asResourceManager() {
    return new User(UserProperty.getValueOf(USER_LOGIN_AS_RESOURCE_MANAGER),
        UserProperty.getValueOf(USER_PASSWORD_AS_RESOURCE_MANAGER))
        .setFirstName("Resource Manager");
  }

  public static User asResourceManagerLocatedRoleAutoTestCity() {
    return new User(UserProperty.getValueOf(USER_LOGIN_AS_RESOURCE_MANAGER_AUTO_TEST_CITY),
        UserProperty.getValueOf(USER_PASSWORD_AS_RESOURCE_MANAGER_AUTO_TEST_CITY))
        .setFirstName("Resource Manager located in AutoTestCity");
  }

  public static User asTrainingManagerLocatedRoleAutoTestCity() {
    return new User(UserProperty.getValueOf(USER_LOGIN_AS_TRAINING_MANAGER_AUTO_TEST_CITY),
        UserProperty.getValueOf(USER_PASSWORD_AS_TRAINING_MANAGER_AUTO_TEST_CITY))
        .setFirstName("Training Manager located in AutoTestCity");
  }

  public static User asAdminLocatedRoleAutoTestCity() {
    return new User(UserProperty.getValueOf(USER_LOGIN_AS_ADMIN_AUTO_TEST_CITY),
        UserProperty.getValueOf(USER_PASSWORD_AS_ADMIN_AUTO_TEST_CITY))
        .setFirstName("Admin located in AutoTestCity");
  }

  public static User asNewsCreator() {
    return new User(UserProperty.getValueOf(USER_LOGIN_AS_NEWS_CREATOR),
        UserProperty.getValueOf(USER_PASSWORD_AS_NEWS_CREATOR))
        .setFirstName("News Creator");
  }

  public static User asUserManager() {
    return new User(UserProperty.getValueOf(USER_LOGIN_AS_USER_MANAGER),
        UserProperty.getValueOf(USER_PASSWORD_AS_USER_MANAGER))
        .setFirstName("User Manager");
  }

  public static User asSuperAdminForLanguageSwitch() {
    return new User(UserProperty.getValueOf(USER_LOGIN_FOR_SUPER_ADMIN_LANGUAGE_SWITCH),
        UserProperty.getValueOf(USER_PASSWORD_FOR_SUPER_ADMIN_LANGUAGE_SWITCH))
        .setFirstName("Super Admin for Language Switch");
  }

  public static User asNewsCreatorForLanguageSwitch() {
    return new User(UserProperty.getValueOf(USER_LOGIN_FOR_NEWS_CREATOR_LANGUAGE_SWITCH),
        UserProperty.getValueOf(USER_PASSWORD_FOR_NEWS_CREATOR_LANGUAGE_SWITCH))
        .setFirstName("News Creator for Language Switch");
  }

  public static User asLearningStudent() {
    return new User(UserProperty.getValueOf(USER_LOGIN_AS_LEARNING_STUDENT),
        UserProperty.getValueOf(USER_PASSWORD_AS_LEARNING_STUDENT))
        .setFirstName("Learning Student");
  }

  public static User asStudentForTrainingPage() {
    return new User(UserProperty.getValueOf(USER_LOGIN_AS_STUDENT_FOR_TRAINING_PAGE),
        UserProperty.getValueOf(USER_PASSWORD_AS_STUDENT_FOR_TRAINING_PAGE))
        .setFirstName("Training Student");
  }

  public static User asStudentForNotificationPage() {
    return new User(UserProperty.getValueOf(USER_LOGIN_AS_STUDENT_FOR_NOTIFICATION_PAGE),
        UserProperty.getValueOf(USER_PASSWORD_AS_STUDENT_FOR_NOTIFICATION_PAGE))
        .setFirstName("Student Notification");
  }

  public static User asStudentForLearningPage() {
    return new User(UserProperty.getValueOf(USER_LOGIN_AS_STUDENT_FOR_LEARNING_PAGE),
        UserProperty.getValueOf(USER_PASSWORD_AS_STUDENT_FOR_LEARNING_PAGE))
        .setFirstName("Student Learning");
  }

  public static User asStudentWithOnlineTasks() {
    return new User(UserProperty.getValueOf(USER_LOGIN_AS_STUDENT_WITH_ONLINE_TASKS),
        UserProperty.getValueOf(USER_PASSWORD_AS_STUDENT_WITH_ONLINE_TASKS))
        .setFirstName("Learning Student");
  }

  public static User asStudentWithNotifications() {
    return new User(UserProperty.getValueOf(USER_LOGIN_AS_STUDENT_WITH_NOTIFICATIONS),
            UserProperty.getValueOf(USER_PASSWORD_AS_STUDENT_WITH_NOTIFICATIONS))
            .setFirstName("Learning Student");
  }

  public static User asStudentWithoutNotifications() {
    return new User(UserProperty.getValueOf(USER_LOGIN_AS_STUDENT_WITHOUT_NOTIFICATIONS),
        UserProperty.getValueOf(USER_PASSWORD_AS_STUDENT_WITHOUT_NOTIFICATIONS))
        .setFirstName("Learning Student");
  }

  public static User asEventManager() {
    return new User(UserProperty.getValueOf(USER_NAME_AS_EVENT_MANAGER),
        UserProperty.getValueOf(USER_PASSWORD_AS_EVENT_MANAGER))
        .setFirstName("Event Manager");
  }

  public static User asStudentWithTasks() {
    return new User(UserProperty.getValueOf(USER_LOGIN_AS_STUDENT_WITH_TASKS),
        UserProperty.getValueOf(USER_PASSWORD_AS_STUDENT_WITH_TASKS))
        .setFirstName("Learning Student");
  }
}
