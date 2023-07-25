package com.epmrdpt.bo.user_management;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Role {

  @JsonProperty("Id")
  private int id;
  @JsonProperty("Name")
  private String Name;

  @Getter
  @AllArgsConstructor
  public enum RoleNames {
    ADMIN("Admin"),
    EVENT_MANAGER("EventManager"),
    FAQ_EDITOR("FAQEditor"),
    GROUP_OWNER("Group.Owner"),
    GROUP_STUDENT("Group.Student"),
    GROUP_TRAINER("Group.Trainer"),
    NEWS_OWNER("News.Owner"),
    NEWS_CREATOR("NewsCreator"),
    NEWS_PUBLISHER("NewsPublisher"),
    PLAN_OWNER("Plan.Owner"),
    RECRUITER("Recruiter"),
    REGISTERED_USER("RegisteredUser"),
    SUPER_ADMIN("Superadmin"),
    TEXT_REVIEWER("TextReviewer"),
    TRAINER("Trainer"),
    TRAINER_MANAGER("TrainingManager"),
    USER_MANAGER("UserManager");

    private String roleName;
  }
}
