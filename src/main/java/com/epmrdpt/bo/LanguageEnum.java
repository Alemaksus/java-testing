package com.epmrdpt.bo;

import com.fasterxml.jackson.annotation.JsonValue;

public enum LanguageEnum {

  EN("en", "en"),
  RU("ru", "ru"),
  UKR("ukr", "ua");

  private String name;
  private String acronym;

  LanguageEnum(String name, String acronym) {
    this.name = name;
    this.acronym = acronym;
  }

  public String getName() {
    return name;
  }

  @JsonValue
  public String getAcronym() {
    return acronym;
  }

  public static LanguageEnum getLanguageEnumByLocaleName(String localeName) {
    switch (localeName) {
      case "en":
        return EN;
      case "ru":
        return RU;
      case "ukr":
        return UKR;
      default:
        throw new IllegalArgumentException("Unknown Locale name [" + localeName + "].");
    }
  }
}
