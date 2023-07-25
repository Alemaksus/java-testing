package com.epmrdpt.services;

import com.epmrdpt.bo.LessonDetails;
import com.epmrdpt.screens.ReactEditClassPopUpScreen;
import com.epmrdpt.screens.ReactLessonInfoPopUpScreen;
import com.epmrdpt.utils.RandomUtils;

public class ReactLessonInfoPopUpService {

  private ReactLessonInfoPopUpScreen reactLessonInfoPopUpScreen = new ReactLessonInfoPopUpScreen();
  private ReactEditClassPopUpScreen reactEditClassPopUpScreen = new ReactEditClassPopUpScreen();

  public String getClassTopicFromEditPopUpText() {
    return reactLessonInfoPopUpScreen
        .clickEditIcon()
        .waitClassTopicFromEditNotEmpty()
        .getClassTopicFromEditValue();
  }

  public LessonDetails getLessonDetailsFromEditPopUpText() {
    reactEditClassPopUpScreen = reactLessonInfoPopUpScreen.clickEditIcon();
    reactLessonInfoPopUpScreen
        .clickApplyButtonInSelectEditingIfNeeded()
        .waitClassTopicFromEditNotEmpty();
    LessonDetails lessonDetails = new LessonDetails();
    return lessonDetails
        .withTopic(reactEditClassPopUpScreen.getClassTopicFromEditValue())
        .withDescription(reactEditClassPopUpScreen.getClassDescriptionText())
        .withType(reactEditClassPopUpScreen.getClassTypeText())
        .withLocation(reactEditClassPopUpScreen.getClassLocationValue());
  }

  public ReactEditClassPopUpScreen chooseRandomClassTypeFromEditPopUpText() {
    int size = reactEditClassPopUpScreen.getAllTypesOfClasses().size();
    int ind = RandomUtils.getRandomNumberInInterval(1, size);
    return reactEditClassPopUpScreen.clickClassTypeByIndex(ind);
  }
}
