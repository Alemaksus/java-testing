package com.epmrdpt.regression.training_management;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_FORM_GROUP_TRAINING_STATUS;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_TRAINING_DURATION_TIME_LIMITED;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_TRAINING_MANAGEMENT_TRAINING_TYPE_TRAINING;
import static org.testng.Assert.*;

import com.epmrdpt.bo.user.UserFactory;
import com.epmrdpt.screens.ReactGroupScreen;
import com.epmrdpt.services.ReactLoginService;
import java.util.HashSet;
import java.util.Set;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(
    description = "EPMRDPT_84044_VerifyThatAGroupCanBeCreatedIfTrainingWithEndDateIsInFormGroupStatus",
    groups = {"full", "regression", "manager"})
public class EPMRDPT_84044_VerifyThatAGroupCanBeCreatedIfTrainingWithEndDateIsInFormGroupStatus {

  private ReactGroupScreen reactGroupScreen;
  private int numberOfGroupsBeforeCreatingGroup;
  private int numberGroupsAfterCreatingGroups;
  private Set<String> newGroups;

  @BeforeClass (inheritGroups = false, alwaysRun = true)
  public void setup() {
    reactGroupScreen = new ReactLoginService()
        .loginAndGoToReactTrainingManagement(UserFactory.asTrainingManager())
        .waitAllSpinnersAreNotDisplayed()
        .clickTrainingTypeDropDownTypeName(
            getValueOf(REACT_TRAINING_MANAGEMENT_TRAINING_TYPE_TRAINING))
        .clickTrainingDurationDropDownTypeName(
            getValueOf(REACT_TRAINING_MANAGEMENT_TRAINING_DURATION_TIME_LIMITED))
        .clickCurrentStatusDropDownCurrentStatusName(
            getValueOf(REACT_TRAINING_MANAGEMENT_FORM_GROUP_TRAINING_STATUS))
        .clickApplyButton()
        .waitTrainingScreenIsLoaded()
        .clickTrainingNameByIndex(0)
        .clickGroupsTabs();
    Set<String> oldGroups = new HashSet<>(reactGroupScreen.getGroupTitleText());
    numberOfGroupsBeforeCreatingGroup = oldGroups.size();
    reactGroupScreen.clickAddGroupButton()
        .waitNumberGroupNameToBeMore(numberOfGroupsBeforeCreatingGroup);
    newGroups = new HashSet<>(reactGroupScreen.getGroupTitleText());
    numberGroupsAfterCreatingGroups = newGroups.size();
    newGroups.removeAll(oldGroups);
  }

  @Test
  public void verifyGroupCreated() {
    assertTrue(numberOfGroupsBeforeCreatingGroup < numberGroupsAfterCreatingGroups,
        "A new group has not been created");
  }

  @Test
  public void verifyGroupDisplayedInGroupsList() {
    assertTrue(reactGroupScreen.isGroupNameIsDisplayedInGroupList(newGroups.iterator().next()),
        "The new group is not displayed on screen");
  }
}
