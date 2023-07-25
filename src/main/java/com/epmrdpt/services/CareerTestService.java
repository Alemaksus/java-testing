package com.epmrdpt.services;

import static com.epmrdpt.utils.RandomUtils.getRandomNumberInInterval;

import com.epmrdpt.screens.CareerTestQuestionnaireScreen;
import com.epmrdpt.screens.CareerTestResultScreen;
import java.util.stream.IntStream;

public class CareerTestService {

  private final CareerTestQuestionnaireScreen careerTestQuestionnaireScreen = new CareerTestQuestionnaireScreen();

  public CareerTestResultScreen takeCareerTest() {
    int pagesAmount = 5;
    for (int i = 0; i < pagesAmount; i++) {
      IntStream.range(1, careerTestQuestionnaireScreen.getQuestionsQuantityOnCurrentPage() + 1)
          .forEach(questionNumber ->
              careerTestQuestionnaireScreen.clickAnswerInQuestion(questionNumber,
                  getRandomNumberInInterval(1, 5)));
      careerTestQuestionnaireScreen.clickNextQuestionsPageButton();
    }
    return careerTestQuestionnaireScreen.clickGetTestResultButton();
  }
}
