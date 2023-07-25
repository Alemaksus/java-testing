package com.epmrdpt.bo.graduate_journal_entity;

public class GraduateJournalEntityFactory {

  public static GraduateJournalEntity getDefaultGraduateJournal() {
    return new GraduateJournalEntity()
        .withStartDate("2021-06-23T12:40:34.135Z")
        .withFinishDate("2021-06-23T12:40:34.135Z")
        .withActiveStudent(true)
        .withNativeName(true)
        .withStatusFilterId(0)
        .withPage(1)
        .withCounterPage(1)
        .withJournalSortBy("None")
        .withSortOrder("None");
  }
}
