package com.epmrdpt.services;

import static com.epmrdpt.bo.user.UserFactory.asEventManager;

import com.epmrdpt.bo.events.EventSpeaker;
import com.epmrdpt.screens.EventPreviewScreen;
import com.epmrdpt.screens.ReactEventDetailsScreen;
import com.epmrdpt.utils.StringUtils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EventPreviewService {

  private final EventPreviewScreen eventPreviewScreen = new EventPreviewScreen();

  public List<EventSpeaker> getEventSpeakers() {
    List<EventSpeaker> eventSpeakerList = new ArrayList<>();
    int eventSpeakerNumber = 1;
    while (eventPreviewScreen.isEventSpeakerPresent(eventSpeakerNumber)) {
      eventSpeakerList.add(new EventSpeaker(
          eventPreviewScreen.getEventSpeakerNameAttributeValue(eventSpeakerNumber),
          eventPreviewScreen.getEventSpeakerJobTitleAttributeValue(eventSpeakerNumber),
          eventPreviewScreen.getEventSpeakerDescriptionAttributeValue(eventSpeakerNumber)));
      eventSpeakerNumber++;
    }
    return eventSpeakerList;
  }

  private LocalDate getValidDate(String dateAsText) {
    LocalDate validDate;
    if (Objects.equals(System.getProperty("locale"), "en")) {
      validDate = StringUtils.getLocaleDateFromString(dateAsText, "MMMM d, yyyy");
    } else {
      validDate = StringUtils.getLocaleDateFromString(dateAsText
              .replaceAll(" г\\.", "")
              .replaceAll(" р\\.", "")
          , "d MMMM yyyy");
    }
    return validDate;
  }

  public void passageToAttendeesOfEventsCardAsEventManager(String eventName) {
    new LoginService().loginWithoutTrainingCardAppearingWaitAndLanguageSettingWait(asEventManager())
        .clickEventsNavigationLink()
        .clickUpcomingEventsShowMoreButton()
        .clickEventByName(eventName)
        .clickEditButton()
        .switchToLastWindow();
    new ReactEventDetailsScreen()
        .clickButtonAttendees();
  }
}
