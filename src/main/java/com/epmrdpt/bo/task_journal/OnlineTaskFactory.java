package com.epmrdpt.bo.task_journal;

import com.epmrdpt.bo.task_journal.Duration.Unit;
import com.epmrdpt.utils.RandomUtils;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OnlineTaskFactory {

  private static final Random RANDOM_GENERATOR = new Random();
  private static final int MAX_MARK = 10;

  public static OnlineTask generateOnlineTask(String taskName, List<Integer> groupIds,
      int headTrainerId) {
    return new OnlineTask()
        .withName(taskName)
        .withDescription("")
        .withSpecificWeight(RandomUtils.getRandomNumberInInterval(1, 10))
        .withGroupIds(groupIds)
        .withHeadTrainerId(headTrainerId)
        .withAdditionalTrainerIds(new ArrayList<>())
        .withStartDateTime(getStartDate())
        .withDeadlineDateTime(getDeadlineDate())
        .withMaxMark(MAX_MARK)
        .withAllowFractionalMark(RANDOM_GENERATOR.nextBoolean())
        .withPassMark(RandomUtils.getRandomNumberInInterval(1, 5))
        .withDefaultMark(0)
        .withNumberOfSubmissionAttempts(RandomUtils
            .getRandomNumberInInterval(1, 3))
        .withIncludeDismissedStudentsInCalculations(RANDOM_GENERATOR.nextBoolean())
        .withNotifyStudentAfterAssign(RANDOM_GENERATOR.nextBoolean())
        .withNotifyStudentBeforeDeadline(true)
        .withStudentNotificationTimeBeforeDeadline(new Duration(2, Unit.DAY))
        .withAttachedLinks(new ArrayList<>())
        .withAttachedFileIds(new ArrayList<>())
        .withVariants(new ArrayList<>());
  }

  private static String getStartDate() {
    return Instant.now().toString();
  }

  private static String getDeadlineDate() {
    return Instant.now()
        .plus(2, ChronoUnit.DAYS)
        .toString();
  }
}
