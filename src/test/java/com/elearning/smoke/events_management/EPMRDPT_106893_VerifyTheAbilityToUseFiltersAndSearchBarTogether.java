package com.epmrdpt.smoke.events_management;

import com.epmrdpt.framework.properties.LocaleProperties;
import com.epmrdpt.framework.properties.LocalePropertyConst;
import com.epmrdpt.screens.ReactEventsManagementScreen;
import com.epmrdpt.services.LoginService;
import com.epmrdpt.services.ReactEventsManagementService;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.epmrdpt.bo.user.UserFactory.asEventManager;

@Test(description = "EPMRDPT_106893_VerifyTheAbilityToUseFiltersAndSearchBarTogether",
        groups = {"full", "smoke"})

public class EPMRDPT_106893_VerifyTheAbilityToUseFiltersAndSearchBarTogether {
    private final String EVENT_NAME = "Autotest 106893";
    private final String EVENT_COUNTRY = LocaleProperties
            .getValueOf(LocalePropertyConst.ABOUT_MAP_SECTION_ARMENIA_TAB);

    private ReactEventsManagementScreen reactEventsManagementScreen;

    @BeforeClass(inheritGroups = false, alwaysRun = true)
    public void setUp() {
        new LoginService()
                .login(asEventManager())
                .clickEventsManagementLink();

        reactEventsManagementScreen = new ReactEventsManagementService()
                .searchEventByNameAndCountry(EVENT_NAME, EVENT_COUNTRY);
    }

    @Test
    public void isEventVisibleWithGivenNameAndCountryInSearchResults() {
        Assert.assertTrue(
                reactEventsManagementScreen.isEventDisplayed(EVENT_NAME),
                "There is no event with " + EVENT_NAME + " name"
        );
    }

    @Test
    public void isSelectedSearchDropDownOptionIsDisplayed() {
        Assert.assertTrue(
                reactEventsManagementScreen.isSelectedCountryDropDownSearchOptionIsDisplayed(EVENT_COUNTRY),
                "Selected option " + EVENT_COUNTRY + " in country dropdown is not displayed after search."
        );
    }

}
