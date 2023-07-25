package com.epmrdpt.services;

import com.epmrdpt.bo.training_class.TrainingClass;
import com.epmrdpt.screens.ReactEditClassPopUpScreen;

public class ReactTrainingClassService {

  private ReactEditClassPopUpScreen reactEditClassPopUpScreen = new ReactEditClassPopUpScreen();

  public TrainingClass getTrainingClassFromEditClassPopUp() {
    return new TrainingClass()
        .withName(reactEditClassPopUpScreen.getClassTopicFromEditValue())
        .withGroup(reactEditClassPopUpScreen.getGroupNameFieldText())
        .withStartDate(reactEditClassPopUpScreen.getTrainingClassDate())
        .withStartTime(reactEditClassPopUpScreen.getTrainingClassStartTime())
        .withEndTime(reactEditClassPopUpScreen.getTrainingClassEndTime())
        .withMainTrainer(reactEditClassPopUpScreen.getTrainerInputText());
  }
}
