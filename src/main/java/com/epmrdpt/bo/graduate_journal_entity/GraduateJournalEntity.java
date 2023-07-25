package com.epmrdpt.bo.graduate_journal_entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class GraduateJournalEntity {

  private String startDate;
  private String finishDate;
  private Boolean isActiveStudent;
  private Boolean isNativeName;
  private Integer statusFilterId;
  private Integer page;
  private Integer counterPage;
  private String journalSortBy;
  private String sortOrder;

  public GraduateJournalEntity withStartDate(String startDate) {
    this.startDate = startDate;
    return this;
  }

  public GraduateJournalEntity withFinishDate(String finishDate) {
    this.finishDate = finishDate;
    return this;
  }

  public GraduateJournalEntity withActiveStudent(Boolean activeStudent) {
    this.isActiveStudent = activeStudent;
    return this;
  }

  public GraduateJournalEntity withNativeName(Boolean nativeName) {
    this.isNativeName = nativeName;
    return this;
  }

  public GraduateJournalEntity withStatusFilterId(Integer statusFilterId) {
    this.statusFilterId = statusFilterId;
    return this;
  }

  public GraduateJournalEntity withPage(Integer page) {
    this.page = page;
    return this;
  }

  public GraduateJournalEntity withCounterPage(Integer counterPage) {
    this.counterPage = counterPage;
    return this;
  }

  public GraduateJournalEntity withJournalSortBy(String journalSortBy) {
    this.journalSortBy = journalSortBy;
    return this;
  }

  public GraduateJournalEntity withSortOrder(String sortOrder) {
    this.sortOrder = sortOrder;
    return this;
  }

  public String getStartDate() {
    return startDate;
  }

  public String getFinishDate() {
    return finishDate;
  }

  public Boolean isActiveStudent() {
    return isActiveStudent;
  }

  public Boolean isNativeName() {
    return isNativeName;
  }

  public Integer getStatusFilterId() {
    return statusFilterId;
  }

  public Integer getPage() {
    return page;
  }

  public Integer getCounterPage() {
    return counterPage;
  }

  public String getJournalSortBy() {
    return journalSortBy;
  }

  public String getSortOrder() {
    return sortOrder;
  }
}
