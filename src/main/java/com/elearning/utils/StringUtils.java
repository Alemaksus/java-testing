package com.epmrdpt.utils;

import static com.epmrdpt.framework.loging.Log.logInfoMessage;
import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_ENGLISH;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN;
import static com.epmrdpt.framework.properties.LocalePropertyConst.SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.lang3.RandomStringUtils;

public class StringUtils {

  public static boolean isDateMatchExpectedPattern(String date, String pattern) {
    String locale = System.getProperty("locale");
    if (locale.equals("ukr")) {
      locale = "uk";
    }
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern,
        new Locale(locale.toUpperCase()));
    try {
      simpleDateFormat.parse(date);
      return true;
    } catch (ParseException e) {
      return false;
    }
  }

  public static String generateRandomPassword() {
    String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
    String specialCharacters = "!@#$";
    String numbers = "1234567890";
    int length = 8;
    String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
    SecureRandom random = new SecureRandom();
    String password =
        String.valueOf(lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length())))
            + capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()))
            + specialCharacters.charAt(random.nextInt(specialCharacters.length()))
            + numbers.charAt(random.nextInt(numbers.length()));
    return password + RandomStringUtils.random(length - 4, combinedChars);
  }

  public static LocalDate getLocaleDateFromString(String date, String pattern) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern)
        .withLocale(getLocaleForDate());
    return LocalDate.parse(date, formatter);
  }

  public static List<LocalDate> getDatesListInDateFromString(List<String> datesListInString,
      DateTimeFormatter dateTimeFormatter) {
    List<LocalDate> datesListInDate = new ArrayList<>();
    datesListInString.stream().forEach(dateInString ->
        datesListInDate.add(LocalDate.parse(dateInString, dateTimeFormatter)));
    return datesListInDate;
  }

  public static String getDateStringWithFormattedMonth(LocalDate date, TextStyle textStyle) {
    String month = date.getMonth().getDisplayName(textStyle, getLocaleForDate());
    return String.format("%s %s", date.getDayOfMonth(), month);
  }

  private static Locale getLocaleForDate() {
    String localeName = System.getProperty("locale");
    if (localeName.equals("ukr")) {
      localeName = "UK";
    }
    return new Locale(localeName.toUpperCase());
  }

  public static List<Integer> extractNumbersFromString(List<String> string) {
    List<Integer> numbersFromString = new ArrayList<>();
    for (String s:string) {
      Pattern pattern = Pattern.compile("\\d+");
      Matcher matcher = pattern.matcher(s);
      while (matcher.find()) {
        numbersFromString.add(Integer.parseInt(matcher.group()));
        System.err.println(Integer.parseInt(matcher.group()));
      }
    }

    return numbersFromString;
  }

  public static String getCurrentDateInGivenFormat(String pattern) {
    return new SimpleDateFormat(pattern, getLocaleForDate()).format(new Date());
  }

  public static String getCurrentMonthValueByLanguageCode(String languageCode) {
    return Month.of(LocalDate.now().getMonth().getValue())
        .getDisplayName(TextStyle.FULL, Locale.forLanguageTag(languageCode))
        .toUpperCase().substring(0, 3);
  }

  public static String getCurrentDayOfWeekValue() {
    return DayOfWeek.of(LocalDate.now().getDayOfWeek().getValue())
        .getDisplayName(TextStyle.FULL_STANDALONE, getLocaleForDate())
        .toUpperCase();
  }

  public static String[] getShortWeekdaysByLanguageCode(String languageCode) {
    DateFormatSymbols symbols = new DateFormatSymbols(Locale.forLanguageTag(languageCode));
    return symbols.getShortWeekdays();
  }

  public static String[] getDayOfWeekValues() {
    DateFormatSymbols symbols = new DateFormatSymbols(getLocaleForDate());
    return symbols.getWeekdays();
  }

  public static List<String> getTimeEvery30MinutesListByFromAndTo(String from, String to) {
    List<String> timeEvery30MinutesList = new ArrayList<>();
    LocalTime fromTime = LocalTime.parse(from);
    LocalTime toTime = LocalTime.parse(to);
    for (LocalTime timeCounter = fromTime; timeCounter.compareTo(toTime) <= 0;
        timeCounter = timeCounter.plusMinutes(30)) {
      timeEvery30MinutesList.add(timeCounter.toString());
    }
    return timeEvery30MinutesList;
  }

  public static LocalDate getTodayDate() {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    return LocalDate.parse(dateTimeFormatter.format(LocalDate.now()), dateTimeFormatter);
  }

  public static String getGeneratedEmail() {
    return RandomStringUtils.randomAlphanumeric(10, 15) + "@"
        + RandomStringUtils.randomAlphabetic(4, 8) + "."
        + RandomStringUtils.randomAlphabetic(2, 3);
  }

  public static String getStringValueByRegex(String regex, String input) {
    Matcher matcher = Pattern.compile(regex).matcher(input);
    if (matcher.find()) {
      return matcher.group();
    } else {
      throw new IllegalArgumentException(String
          .format("Exception during using regular expression:"
              + "string '%s' doesn't contain substring by regex '%s'", input, regex));
    }
  }

  public static List<Calendar> getCalendarListWithMonthAndYear(List<String> dates) {
    List<String> dateWithMonthAndYear = dates
        .stream()
        .map(date -> {
          if (date.trim().split("\\s+").length == 3) {
            return org.apache.commons.lang3.StringUtils.substringAfter(date.trim(), " ");
          }
          return date;
        })
        .collect(Collectors.toList());
    return getCalendarFromString(dateWithMonthAndYear, "MMMM yyyy");
  }

  public static List<Calendar> getCalendarFromString(List<String> datesListInString, String pattern){
    SimpleDateFormat formatter = new SimpleDateFormat(pattern,getLocaleForDate());
    List<Calendar> datesListInCalendar = new ArrayList<>();
    try {
      for(String date : datesListInString){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatter.parse(date));
        datesListInCalendar.add(calendar);
      }
    } catch (ParseException e){
      logInfoMessage("Date can't be parsed!");
    }
    return datesListInCalendar;
  }

  public static List<Calendar> getCalendarListWithFullDate(List<String> dates){
    List<String> dateWithFullDate = dates
        .stream()
        .filter(date -> date.trim().split("\\s+").length == 3)
        .collect(Collectors.toList());
    return getCalendarFromString(dateWithFullDate, "dd MMMM yyyy");
  }

  public static List<String> getListOfStringValues(String values) {
    return Arrays.stream(values.split(",")).collect(Collectors.toList());
  }

  public static String getCurrentLocaleLanguage() {
    switch (System.getProperty("locale")) {
      case "ru":
        return getValueOf(SETTINGS_LANGUAGE_DROPDOWN_RUSSIAN);
      case "en":
        return getValueOf(SETTINGS_LANGUAGE_DROPDOWN_ENGLISH);
      case "ukr":
        return getValueOf(SETTINGS_LANGUAGE_DROPDOWN_UKRAINIAN);
      default:
        throw new NoSuchElementException("There is no such locale");
    }
  }

  public static String getLinkFromSystemClipboard() {
    try {
      String link = Toolkit.getDefaultToolkit()
          .getSystemClipboard()
          .getData(DataFlavor.stringFlavor).toString();
      return link;
    } catch (UnsupportedFlavorException | IOException e) {
      e.printStackTrace();
    }
    return "Invalid link";
  }

  public static void clearSystemClipboard() {
    Toolkit.getDefaultToolkit()
        .getSystemClipboard()
        .setContents(new StringSelection(null), null);
  }

  public static String getJsonStringFromObject(Object object) {
    ObjectMapper mapper = new ObjectMapper();
    String jsonString = null;
    try {
      jsonString = mapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return jsonString;
  }
}
