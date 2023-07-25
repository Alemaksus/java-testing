package com.epmrdpt.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TrainerGroup {
    @JsonProperty("Id")
    private int id;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("StartTrainingDate")
    private String startTrainingDate;

    @JsonProperty("FinishTrainingDate")
    private String finishTrainingDate;

    public TrainerGroup() {
    }

    public TrainerGroup(String startTrainingDate, String finishTrainingDate, int id, String name) {
        this.startTrainingDate = startTrainingDate;
        this.finishTrainingDate = finishTrainingDate;
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStartTrainingDate() {
        return startTrainingDate;
    }

    public String getFinishTrainingDate() {
        return finishTrainingDate;
    }

    @Override
    public String toString() {
        return "TrainerGroup{" + "\n" +
                "\t\"startTrainingDate\": " + startTrainingDate + "\n" +
                "\t\"finishTrainingDate\": " + finishTrainingDate + "\n" +
                "\tid=" + id + "\n" +
                "\tname='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TrainerGroup)) {
            return false;
        }
        TrainerGroup trainerGroup = (TrainerGroup) o;
        return Objects.equals(getStartTrainingDate(), trainerGroup.getStartTrainingDate())
                && Objects.equals(getFinishTrainingDate(), trainerGroup.getFinishTrainingDate())
                && Objects.equals(getName(), trainerGroup.getName())
                && getId() == trainerGroup.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartTrainingDate(), getFinishTrainingDate(), getId(), getName());
    }
}
