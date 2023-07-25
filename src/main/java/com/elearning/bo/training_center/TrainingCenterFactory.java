package com.epmrdpt.bo.training_center;

import com.epmrdpt.utils.FileUtils;

public class TrainingCenterFactory {

  private static final int FACT_NUMBER = 123;
  private static final String PHOTO_IMAGE_PATH = String
      .format("%s%s", System.getProperty("user.dir"), "\\src\\test\\testData\\image.jpg");
  private static final String TRAINING_CENTER_NAME = "TrainingCenterName";
  private static final String TRAINING_CENTER_DESCRIPTION = "TrainingCenterDescription";
  private static final String TRAINING_CENTER_FACT_DESCRIPTION = "TrainingCenterFactDescription";
  private static final String TRAINING_CENTER_SKILL = "Java";
  private static final String TRAINING_CENTER_COUNTRY = "AutoTestCountry";
  private static final String TRAINING_CENTER_CITY = "AutoTestCityDelete";
  private static final String TRAINING_CENTER_UNIVERSITY_NAME="UniversityName";
  private static final String TRAINING_CENTER_UNIVERSITY_SHORT_NAME="UN";
  private static final String TRAINING_CENTER_UNIVERSITY_DESCRIPTION_TEXT="UniversityDescriptionText";
  private static final String TRAINING_CENTER_FEEDBACK_AUTHORS_NAME="FeedbackAuthorsName";
  private static final String TRAINING_CENTER_FEEDBACK_JOB_FUNCTIONAL="FeedbackJobFunctional";
  private static final String TRAINING_CENTER_FEEDBACK ="FeedbackDescription";

  private TrainingCenterFactory() {
  }

  public static TrainingCenter createTrainingCenter() {
    return new TrainingCenter()
        .withCountry(TRAINING_CENTER_COUNTRY)
        .withCity(TRAINING_CENTER_CITY)
        .withSkill(TRAINING_CENTER_SKILL)
        .withDescription(TRAINING_CENTER_DESCRIPTION)
        .withName(TRAINING_CENTER_NAME)
        .withFactNumber(FACT_NUMBER)
        .withFactDescription(TRAINING_CENTER_FACT_DESCRIPTION)
        .withImgBase64(FileUtils.convertImageToBase64(PHOTO_IMAGE_PATH));
  }

  public static TrainingCenter createTrainingCenterWithCountryAndCityName(String countryName,
      String cityName) {
    return new TrainingCenter()
        .withCountry(countryName)
        .withCity(cityName)
        .withSkill(TRAINING_CENTER_SKILL)
        .withDescription(TRAINING_CENTER_DESCRIPTION)
        .withName(TRAINING_CENTER_NAME)
        .withFactNumber(FACT_NUMBER)
        .withFactDescription(TRAINING_CENTER_FACT_DESCRIPTION)
        .withImgBase64(FileUtils.convertImageToBase64(PHOTO_IMAGE_PATH));
  }
  public static TrainingCenter createTrainingCenterWithUniversityAndFeedback(String countryName,
      String cityName){
    return new TrainingCenter()
        .withCountry(countryName)
        .withCity(cityName)
        .withSkill(TRAINING_CENTER_SKILL)
        .withDescription(TRAINING_CENTER_DESCRIPTION)
        .withName(TRAINING_CENTER_NAME)
        .withFactNumber(FACT_NUMBER)
        .withFactDescription(TRAINING_CENTER_FACT_DESCRIPTION)
        .withImgBase64(FileUtils.convertImageToBase64(PHOTO_IMAGE_PATH))
        .withUniversityName(TRAINING_CENTER_UNIVERSITY_NAME)
        .withUniversityShortName(TRAINING_CENTER_UNIVERSITY_SHORT_NAME)
        .withUniversityDescription(TRAINING_CENTER_UNIVERSITY_DESCRIPTION_TEXT)
        .withFeedbackAuthorsName(TRAINING_CENTER_FEEDBACK_AUTHORS_NAME)
        .withFeedbackJobFunctional(TRAINING_CENTER_FEEDBACK_JOB_FUNCTIONAL)
        .withFeedbackDescription(TRAINING_CENTER_FEEDBACK);
  }
}
