package com.epmrdpt.bo.task_journal;

import com.epmrdpt.bo.task_journal.Duration.Unit;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class AssignmentFactory {

  public static Assignment setAssignment(int taskId, int groupId, List<Integer> reviewersIds) {
    return new Assignment()
        .withTaskId(taskId)
        .withGroupId(groupId)
        .withAssignedAt(getStartDate())
        .withDeadline(getDeadlineDate())
        .withReviewersIds(reviewersIds)
        .withNotice(new Notice()
            .withNotify(new Duration(2, Unit.DAY), getDeadlineDate()))
        .withNumberOfVariant(0)
        .withTrainingPortalLink("");
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
