package com.epmrdpt.screens;

import static com.epmrdpt.framework.properties.LocaleProperties.getValueOf;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DETAILS_AUTOREPLY_DAY_LEFT_BEFORE_EVENT_START_TEMPLATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DETAILS_AUTOREPLY_EVENT_CANCELLED_TEMPLATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DETAILS_AUTOREPLY_EVENT_COMING_SOON_TEMPLATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DETAILS_AUTOREPLY_EVENT_RECORDING_AVAILABLE_TEMPLATE;
import static com.epmrdpt.framework.properties.LocalePropertyConst.REACT_EVENTS_MANAGEMENT_DETAILS_AUTOREPLY_THANKS_FOR_REGISTRATION_TEMPLATE;

import com.epmrdpt.framework.ui.AbstractScreen;
import com.epmrdpt.framework.ui.element.Element;

public class ReactEventAutoreplyScreen extends AbstractScreen {
  private static final String INPUT_BY_VALUE_PATTERN = "//input[@value='%s']";
  private final Element thanksForRegistrationTemplate = Element
      .byXpath(INPUT_BY_VALUE_PATTERN,
          getValueOf(REACT_EVENTS_MANAGEMENT_DETAILS_AUTOREPLY_THANKS_FOR_REGISTRATION_TEMPLATE));
  private final Element dayLeftBeforeEventStartTemplate = Element
      .byXpath(INPUT_BY_VALUE_PATTERN,
          getValueOf(REACT_EVENTS_MANAGEMENT_DETAILS_AUTOREPLY_DAY_LEFT_BEFORE_EVENT_START_TEMPLATE));
  private final Element eventComingSoonTemplate = Element
      .byXpath(INPUT_BY_VALUE_PATTERN,
          getValueOf(REACT_EVENTS_MANAGEMENT_DETAILS_AUTOREPLY_EVENT_COMING_SOON_TEMPLATE));
  private final Element eventCancelledTemplate = Element
      .byXpath(INPUT_BY_VALUE_PATTERN,
          getValueOf(REACT_EVENTS_MANAGEMENT_DETAILS_AUTOREPLY_EVENT_CANCELLED_TEMPLATE));
  private final Element eventRecordingAvailableTemplate = Element
      .byXpath(INPUT_BY_VALUE_PATTERN,
          getValueOf(REACT_EVENTS_MANAGEMENT_DETAILS_AUTOREPLY_EVENT_RECORDING_AVAILABLE_TEMPLATE));

  @Override
  public boolean isScreenLoaded() {
    return false;
  }

  public boolean isThanksForRegistrationTemplateDisplayed() {
    return thanksForRegistrationTemplate.isDisplayed();}

  public boolean isDayLeftBeforeEventStartTemplateDisplayed() {
    return dayLeftBeforeEventStartTemplate.isDisplayed();}

  public boolean isEventComingSoonTemplateDisplayed() {
    return eventComingSoonTemplate.isDisplayed();}

  public boolean isEventCancelledTemplateDisplayed() {
    return eventCancelledTemplate.isDisplayed();}

  public boolean isEventRecordingAvailableTemplateDisplayed() {
    return eventRecordingAvailableTemplate.isDisplayed();}
}
