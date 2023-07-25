package com.epmrdpt.services;

import static com.epmrdpt.framework.properties.UserProperty.getValueOf;
import static com.epmrdpt.framework.properties.UserPropertyConst.GROUP_ID;
import static com.epmrdpt.framework.properties.UserPropertyConst.TRAINING_ID;
import static com.epmrdpt.utils.FileUtils.waitForFileExistence;
import com.epmrdpt.screens.ReactReportScreen;
import com.epmrdpt.utils.FileUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReactReportsService {
  private static String DOWNLOADS_PATH = FileUtils.getDownloadedFilePath();

  private final String GROUP_INFO_REPORT_DDL_ITEM = "GroupInfoReports";
  private final String PARTICIPANTS_INFO_REPORT_DDL_ITEM = "ParticipantsInfoReports";
  private final String REGISTRATION_SURVEY_REPORT_DDL_ITEM = "RegistrationSurveyReports";
  private final String TRAINING_PARTICIPANTS_INFO_REPORT_DDL_ITEM = "TrainingParticipantsInfoReports in Excel";
  private final String UTM_TAGS_REPORTS_REPORT_DDL_ITEM = "UtmTagsReports";
  private final int groupInfoReportIndex = 0;
  private final int participantsInfoReportIndex = 1;
  private final int registrationSurveyReportIndex = 2;
  private final int trainingParticipantsInfoReportIndex = 3;
  private final int utmTagsReportItem = 4;

  ReactReportScreen reactReportScreen = new ReactReportScreen();

  public ReactReportsService downloadParticipantsInfoReports() {
    reactReportScreen.selectReportTypeByName(PARTICIPANTS_INFO_REPORT_DDL_ITEM)
        .enterId(getValueOf(GROUP_ID))
        .clickDownloadReportButton();
    waitForFileExistence(DOWNLOADS_PATH, getReportsFileNames().get(participantsInfoReportIndex));
    return this;
  }

  public ReactReportsService downloadTrainingParticipantsInfoReportsInExcel() {
    reactReportScreen.selectReportTypeByName(TRAINING_PARTICIPANTS_INFO_REPORT_DDL_ITEM)
        .enterId(getValueOf(TRAINING_ID))
        .clickDownloadReportButton();
    waitForFileExistence(DOWNLOADS_PATH, getReportsFileNames().get(trainingParticipantsInfoReportIndex));
    return this;
  }

  public ReactReportsService downloadGroupsInfoReport(int countryIndex, int cityIndex,
      int skillIndex, String startDate, String endDate) {
    reactReportScreen.selectReportTypeByName(GROUP_INFO_REPORT_DDL_ITEM)
        .selectCountryByIndex(countryIndex)
        .selectCityByIndex(cityIndex)
        .selectSkillByIndex(skillIndex)
        .fillStartDate(startDate)
        .fillEndDate(endDate)
        .clickDownloadReportButton();
    waitForFileExistence(DOWNLOADS_PATH, getReportsFileNames().get(groupInfoReportIndex));
    return this;
  }
   public ReactReportsService downloadRegistrationSurveyReports() {
     reactReportScreen.selectReportTypeByName(REGISTRATION_SURVEY_REPORT_DDL_ITEM)
         .enterId(getValueOf(TRAINING_ID))
         .clickDownloadReportButton();
     waitForFileExistence(DOWNLOADS_PATH, getReportsFileNames().get(registrationSurveyReportIndex));
     return this;
   }

   public ReactReportsService downloadUtmTagsReports() {
     reactReportScreen.selectReportTypeByName(UTM_TAGS_REPORTS_REPORT_DDL_ITEM)
         .enterId(getValueOf(TRAINING_ID))
         .clickDownloadReportButton();
     waitForFileExistence(DOWNLOADS_PATH, getReportsFileNames().get(utmTagsReportItem));
     return this;
   }

  public static List<String> getReportsFileNames() {
    return new ArrayList<>(
        Arrays.asList("GroupsInfoReport.xlsx",
            String.format("ParticipantsInfoReport_%s", getValueOf(GROUP_ID) + ".xlsx"),
            String.format("RegistrationSurveyReport_%s", getValueOf(TRAINING_ID) + ".xlsx"),
            String.format("TrainingParticipantsInfoReport_%s", getValueOf(TRAINING_ID) + ".xlsx"),
            String.format("UtmTagReport_%s", getValueOf(TRAINING_ID) + ".xlsx")));
  }
}
