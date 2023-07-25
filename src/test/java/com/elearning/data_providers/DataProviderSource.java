package com.epmrdpt.data_providers;

import com.epmrdpt.bo.user.UserFactory;
import org.testng.annotations.DataProvider;


public class DataProviderSource {

  @DataProvider(name = "Provider of users with News Management tab")
  public static Object[][] provideUsersWithNewsManagementTab() {
    return new Object[][]{
        {UserFactory.asSuperAdmin()},
        {UserFactory.asNewsCreator()},
    };
  }

  @DataProvider(name = "Provider of users with Reports tab")
  public static Object[][] provideUsersWithReportsTab() {
    return new Object[][]{
        {UserFactory.asSuperAdmin()},
        {UserFactory.asTrainingManager()},
        {UserFactory.asRecruiter()},
    };
  }

  @DataProvider(name = "Provider of users with Graduates tab")
  public static Object[][] provideUsersWithGraduatesTab() {
    return new Object[][]{
        {UserFactory.asAdmin()},
        {UserFactory.asSuperAdmin()},
        {UserFactory.asTrainingManager()}
    };
  }

  @DataProvider(name = "Provider of users with Search tab")
  public static Object[][] provideUsersWithSearchTab() {
    return new Object[][]{
        {UserFactory.asAdmin()},
        {UserFactory.asSuperAdmin()},
        {UserFactory.asTrainingManager()},
        {UserFactory.asRecruiter()},
        {UserFactory.asTrainer()},
    };
  }

  @DataProvider(name = "Provider of users for user-creation")
  public static Object[][] provideUsersForUserCreation() {
    return new Object[][]{
        {UserFactory.asAdmin()},
        {UserFactory.asSuperAdmin()},
    };
  }

  @DataProvider(name = "Provider of Users for checking survey on different pages")
  public static Object[][] provideUsersForCheckingSurvey() {
    return new Object[][]{
        {UserFactory.asTrainingManager()},
        {UserFactory.asRecruiter()},
        {UserFactory.asRecruiterLocatedRoleAutoTestCity()},
    };
  }

  @DataProvider(name = "Provider of users with Training Management tab")
  public static Object[][] provideUsersWithTrainingManagementTab() {
    return new Object[][]{
        {UserFactory.asRecruiter()},
        {UserFactory.asTrainingManager()}
    };
  }

  @DataProvider(name = "Provider of users with News Management tab for language switch")
  public static Object[][] provideUsersForLanguageSwitchWithNewsManagementTab() {
    return new Object[][]{
        {UserFactory.asSuperAdminForLanguageSwitch()},
        {UserFactory.asNewsCreatorForLanguageSwitch()},
    };
  }

  @DataProvider(name = "Provider of users with Training tab")
  public static Object[][] provideUsersWithTrainingTab() {
    return new Object[][]{
        {UserFactory.asTrainer()},
        {UserFactory.asTrainingManager()}
    };
  }

  @DataProvider(name = "Provider of users with React Training permissions")
  public static Object[][] provideUsersWithReactTrainingPermissions() {
    return new Object[][]{
        {UserFactory.asTrainer()},
        {UserFactory.asSuperAdmin()},
        {UserFactory.asTrainingManager()}
    };
  }

  @DataProvider(name = "Provider of users with React Training Management")
  public static Object[][] provideUsersWithReactTrainingManagement() {
    return new Object[][]{
        {UserFactory.asSuperAdmin()},
        {UserFactory.asTrainingManager()}
    };
  }

  @DataProvider(name = "Provider of users with different cities options")
  public static Object[][] provideUsersWithCities() {
    return new Object[][]{
        {UserFactory.asSuperAdmin(), "AutoTestCityDelete"},
        {UserFactory.asNewsCreator(), "AutoTestCityAlsoDelete"},
    };
  }

  @DataProvider(name = "Provider of users with user management permissions")
  public static Object[][] provideUsersWithUserManagementPermissions() {
    return new Object[][]{
        {UserFactory.asSuperAdmin()},
        {UserFactory.asRecruiter()},
    };
  }

  @DataProvider(name = "Provider of users with different trainings")
  public static Object[][] provideUsersWithTrainings() {
    return new Object[][]{
        {UserFactory.asSuperAdmin(), "AutoTest_TrainingFor_Karaganda_City"},
        {UserFactory.asTrainingManager(), "Autotest_ForLearningTabTesting"},
    };
  }

  @DataProvider(name = "Provider of users with events management tab")
  public static Object[][] provideUsersWithEventsManagementTab() {
    return new Object[][]{
        {UserFactory.asSuperAdmin()},
        {UserFactory.asEventManager()},
    };
  }
}
