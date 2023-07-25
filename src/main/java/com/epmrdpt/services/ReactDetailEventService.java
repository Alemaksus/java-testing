package com.epmrdpt.services;

import com.epmrdpt.bo.events.EventSpeaker;
import com.epmrdpt.bo.events.EventWhatsOn;
import com.epmrdpt.screens.ReactEventDescriptionScreen;
import java.util.ArrayList;
import java.util.List;

public class ReactDetailEventService {

  private ReactEventDescriptionScreen eventDescriptionScreen = new ReactEventDescriptionScreen();

  public List<EventSpeaker> getEventSpeakersList() {
    List<EventSpeaker> eventSpeakerList = new ArrayList<>();
    List<String> speakerNames = eventDescriptionScreen.getSpeakerNameValue();
    List<String> speakerRoles = eventDescriptionScreen.getSpeakerRoleValue();
    List<String> speakerDescriptions = eventDescriptionScreen.getSpeakerDescriptionValue();
    for (int i = 0; i < speakerNames.size(); i++) {
      eventSpeakerList.add(new EventSpeaker(
          speakerNames.get(i),
          speakerRoles.get(i),
          speakerDescriptions.get(i)
      ));
    }
    return eventSpeakerList;
  }

  public List<EventWhatsOn> getEventWhatsOnList() {
    List<EventWhatsOn> eventWhatsOnList = new ArrayList<>();
    List<String> whatsOnIcons = eventDescriptionScreen.getWhatsOnIconPlaceholder();
    List<String> whatsOnDescriptions = eventDescriptionScreen.getWhatsOnDescriptionValue();
    for (int i = 0; i < whatsOnIcons.size(); i++) {
      eventWhatsOnList.add(new EventWhatsOn(
          whatsOnIcons.get(i),
          whatsOnDescriptions.get(i)
      ));
    }
    return eventWhatsOnList;
  }
}
