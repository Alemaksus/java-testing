package com.epmrdpt.services;

import static com.epmrdpt.framework.properties.LocalePropertyConst.ARMENIA_CONTAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.BELARUS_CONTAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.COLUMBIA_CONTAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.CROATIA_CONTAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.DOMINIKAN_REPUBLIC_CONTAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.GEORGIA_CONTAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.HUNGARY_CONTAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.INDIA_CONTAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.KAZAKHSTAN_CONTAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LATVIA_CONTAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.LITHUANIA_CONTAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.MEXICO_CONTAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.POLAND_CONTAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.ROMANIA_CONTAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.RUSSIA_CONTAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SPAIN_CONTAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.TURKEY_CONTAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.UKRAINE_CONTAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.UZBEKISTAN_CONTAINER;
import static com.epmrdpt.framework.properties.LocalePropertyConst.VIETNAM_CONTAINER;

import com.epmrdpt.framework.properties.LocaleProperties;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CountryContainerService {

  private List<String> countryContainersList = getCountryContainersList();

  public boolean areCheckingCityBelongsChosenCountry(String chosenCountryName,
      String checkingCityName) {
    String globalLocationName = "Global";
    if (checkingCityName.equals(globalLocationName)) {
      return true;
    }
    for (String countryContainer : countryContainersList) {
      if (countryContainer.contains(
          chosenCountryName) && countryContainer.contains(checkingCityName)) {
        return true;
      }
    }
    return false;
  }

  private List<String> getCountryContainersList() {
    List<String> countryContainersList = new ArrayList<>();
    countryContainersList.add(LocaleProperties.getValueOf(ARMENIA_CONTAINER));
    countryContainersList.add(LocaleProperties.getValueOf(UKRAINE_CONTAINER));
    countryContainersList.add(LocaleProperties.getValueOf(RUSSIA_CONTAINER));
    countryContainersList.add(LocaleProperties.getValueOf(KAZAKHSTAN_CONTAINER));
    countryContainersList.add(LocaleProperties.getValueOf(BELARUS_CONTAINER));
    countryContainersList.add(LocaleProperties.getValueOf(UZBEKISTAN_CONTAINER));
    countryContainersList.add(LocaleProperties.getValueOf(POLAND_CONTAINER));
    countryContainersList.add(LocaleProperties.getValueOf(HUNGARY_CONTAINER));
    countryContainersList.add(LocaleProperties.getValueOf(LATVIA_CONTAINER));
    countryContainersList.add(LocaleProperties.getValueOf(LITHUANIA_CONTAINER));
    countryContainersList.add(LocaleProperties.getValueOf(GEORGIA_CONTAINER));
    countryContainersList.add(LocaleProperties.getValueOf(SPAIN_CONTAINER));
    countryContainersList.add(LocaleProperties.getValueOf(INDIA_CONTAINER));
    countryContainersList.add(LocaleProperties.getValueOf(VIETNAM_CONTAINER));
    countryContainersList.add(LocaleProperties.getValueOf(COLUMBIA_CONTAINER));
    countryContainersList.add(LocaleProperties.getValueOf(ROMANIA_CONTAINER));
    countryContainersList.add(LocaleProperties.getValueOf(DOMINIKAN_REPUBLIC_CONTAINER));
    countryContainersList.add(LocaleProperties.getValueOf(MEXICO_CONTAINER));
    countryContainersList.add(LocaleProperties.getValueOf(TURKEY_CONTAINER));
    countryContainersList.add(LocaleProperties.getValueOf(CROATIA_CONTAINER));
    countryContainersList
        .add("AutoTestCountry AutoTestCity AutoTestCityDelete AutoTestCityAlsoDelete");
    return countryContainersList;
  }

  private static final Map<Integer, String> countryContainerMap = new HashMap<Integer, String>() {
    {
      put(4, getCityValuesFromCountryContainerMap(ARMENIA_CONTAINER));
      put(9, getCityValuesFromCountryContainerMap(RUSSIA_CONTAINER));
      put(11, getCityValuesFromCountryContainerMap(KAZAKHSTAN_CONTAINER));
      put(35, getCityValuesFromCountryContainerMap(INDIA_CONTAINER));
      put(55, getCityValuesFromCountryContainerMap(UZBEKISTAN_CONTAINER));
      put(56, getCityValuesFromCountryContainerMap(POLAND_CONTAINER));
      put(57, getCityValuesFromCountryContainerMap(HUNGARY_CONTAINER));
      put(58, getCityValuesFromCountryContainerMap(LITHUANIA_CONTAINER));
      put(59, getCityValuesFromCountryContainerMap(GEORGIA_CONTAINER));
      put(62, getCityValuesFromCountryContainerMap(SPAIN_CONTAINER));
      put(68, getCityValuesFromCountryContainerMap(COLUMBIA_CONTAINER));
    }
  };

  public static Map<Integer, String> getCountryContainerMap() {
    return countryContainerMap;
  }

  public static String getCityValuesFromCountryContainerMap(String containersName) {
    String countryNameValue = LocaleProperties.getValueOf(containersName).split(" ")[0];
    return LocaleProperties.getValueOf(containersName).substring(countryNameValue.length());
  }

  public static List<String> getCityNamesListByCountryIdFromCountryContainerMap(int countryId) {
    return Arrays.stream(countryContainerMap.get(countryId).split("\\s"))
        .map(e -> e.replace("_", " "))
        .collect(Collectors.toList());
  }
}
