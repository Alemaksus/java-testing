package com.epmrdpt.framework.ui.element;

import static com.epmrdpt.framework.loging.Log.logInfoMessage;
import static com.epmrdpt.framework.ui.AbstractScreen.DEFAULT_TIMEOUT_FOR_PAGE_LOAD;
import static com.epmrdpt.framework.ui.AbstractScreen.DEFAULT_TIME_OUT_IN_SECONDS;
import static com.epmrdpt.framework.ui.AbstractScreen.LONG_TIME_OUT_IN_SECONDS;
import static com.epmrdpt.framework.ui.AbstractScreen.SHORT_TIME_OUT_IN_SECONDS;
import static com.epmrdpt.framework.ui.element.LogManager.logClickOnAction;
import static com.epmrdpt.framework.ui.element.LogManager.logFillInAction;
import static com.epmrdpt.framework.ui.element.LogManager.logGetTextAction;
import static com.epmrdpt.framework.ui.element.LogManager.logScrollOnAction;
import static com.epmrdpt.framework.ui.element.LogManager.logSearchElement;
import static com.epmrdpt.framework.ui.element.LogManager.logStateElementOnAction;

import com.epmrdpt.framework.exceptions.UnsupportedLocatorStrategyException;
import com.epmrdpt.framework.ui.CustomConditions;
import com.epmrdpt.framework.ui.webdriver.WebDriverSingleton;
import com.google.common.base.Function;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Element {

  public static final String DISABLED_ATTRIBUTE = "disabled";
  public static final String ENABLED_ATTRIBUTE = "enabled";
  public static final String ATTRIBUTE_CLASS = "class";
  public static final String ATTRIBUTE_CHECKED = "checked";

  private final By by;
  private final LocatorManager locatorManager;
  private final WebDriver webDriver =
      WebDriverSingleton.getInstance().getDriver();

  private Element(By by, LocatorManager locatorManager) {
    this.by = by;
    this.locatorManager = locatorManager;
  }



  /* Strategies*/


  public static Element byXpath(String xpath, Object... params) {
    String locator = String.format(xpath, params);
    logSearchElement("xpath", locator);
    return new Element(By.xpath(locator),
        LocatorManager.byXpath(xpath, params));
  }

  public static Element byCss(String css, Object... params) {
    String locator = String.format(css, params);
    logSearchElement("css", locator);
    return new Element(By.cssSelector(String.format(css, params)),
        LocatorManager.byCss(css, params));
  }

  public static Element byClassName(String className) {
    By locator = By.className(className);
    logSearchElement("className", className);
    return new Element(locator, LocatorManager.byClassName(className));
  }

  public static Element byId(String id) {
    By locator = By.id(id);
    logSearchElement("id", id);
    return new Element(locator, LocatorManager.byId(id));
  }

  public static Element byName(String name) {
    By locator = By.name(name);
    logSearchElement("name", name);
    return new Element(locator, LocatorManager.byName(name));
  }

  public static Element byLinkText(String text) {
    By locator = By.linkText(text);
    logSearchElement("linkText", text);
    return new Element(locator, LocatorManager.byLinkText(text));
  }

  public static Element byTagName(String tagName) {
    By locator = By.tagName(tagName);
    logSearchElement("tagName", tagName);
    return new Element(locator, LocatorManager.byTagName(tagName));
  }


  /*Actions*/


  public void click() {
    logClickOnAction();
    waitForClickable();
    nativeClick();
  }

  public void waitForClickableAndClick() {
    logClickOnAction();
    waitForClickable();
    getWrappedWebElement().click();
  }

  public void tryClick() {
    logClickOnAction();
    waitForVisibility().click();
  }

  public void tryClickNoWait() {
    logClickOnAction();
    nativeClick();
  }

  public void doubleClick() {
    logClickOnAction();
    new Actions(webDriver).doubleClick(waitForClickable());
  }

  public void scrollAndClick() {
    logScrollOnAction();
    scrollIntoView();
    waitForClickable();
    nativeClick();
  }

  public void scrollAndClickJS() {
    logScrollOnAction();
    scrollIntoView();
    waitForVisibility();
    getJavascriptExecutor().executeScript("arguments[0].click()", getWrappedWebElement());
  }

  public void mouseOverAndClick() {
    new Actions(webDriver).moveToElement(getWrappedWebElement()).click().build().perform();
    logInfoMessage("Mouse over and click", getWrappedWebElement());
  }

  public void mouseOverAndClickAndHold() {
    new Actions(webDriver).moveToElement(getWrappedWebElement()).clickAndHold().perform();
    logInfoMessage("Mouse over and click and hold", getWrappedWebElement());
  }

  public void drugAndDropMouse(Element target) {
    mouseOverAndClickAndHold();
    new Actions(webDriver)
            .moveToElement(target.getWrappedWebElement())
            .moveByOffset(0, 0)
            .release()
            .build()
            .perform();
  }

  public void scrollIntoView(WebElement element) {
    logScrollOnAction();
    getJavascriptExecutor().executeScript("window.scrollTo(0, 0);");
    Actions actions = new Actions(webDriver);
    actions.moveToElement(element, element.getSize().getWidth(), element.getSize().getHeight());
    actions.perform();
  }

  public void scrollIntoView() {
    scrollIntoView(getWrappedWebElement());
  }

  public void scrollIntoViewJS() {
    logScrollOnAction();
    ((JavascriptExecutor) webDriver).executeScript("argument[0].scrollIntoView(true);",
        getWrappedWebElement());
  }

  public void tick() {
    logClickOnAction();
    waitForClickable();
    if (isCheckBoxUnchecked()) {
      getWrappedWebElement().click();
    }
  }

  public void unTick() {
    logClickOnAction();
    waitForClickable();
    if (!isCheckBoxUnchecked()) {
      getWrappedWebElement().click();
    }
  }

  public void selectByText(String value) {
    logFillInAction(value);
    new Select(getWrappedWebElement()).selectByVisibleText(value);
  }

  public void selectByValue(String value) {
    logFillInAction(value);
    new Select(getWrappedWebElement()).selectByValue(value);
  }

  public void selectByIndex(int value) {
    logFillInAction(Integer.toString(value));
    new Select(getWrappedWebElement()).selectByIndex(value);
  }

  public void deselectAll() {
    logFillInAction("Deselecting all options");
    new Select(getWrappedWebElement()).deselectAll();
  }

  public void type(String value) {
    logFillInAction(value);
    WebElement element = getWrappedWebElement();
    clearInput();
    element.sendKeys(value);
  }

  public void typeWithoutClear(String value) {
    logFillInAction(value);
    getWrappedWebElement().sendKeys(value);
  }

  public void clearInput() {
    logClickOnAction();
    waitForVisibility().clear();
  }

  public void clearInputUsingBackspace(){
    logClickOnAction();
    WebElement fieldToBeCleared = waitForVisibility();
    for(int i = fieldToBeCleared.getAttribute("value").length(); i > 0; i--){
      fieldToBeCleared.sendKeys(Keys.BACK_SPACE);
    }
  }

  public void nativeClearAndType(String value) {
    logFillInAction(value);
    waitForVisibility().sendKeys(Keys.HOME, Keys.chord(Keys.SHIFT, Keys.END), value);
  }

  private void nativeClick() {
    logClickOnAction();
    new Actions(webDriver).click(getWrappedWebElement()).build().perform();
  }

  public void pageDown() {
    logInfoMessage("Page Down", webDriver);
    new Actions(webDriver).sendKeys(Keys.PAGE_DOWN).build().perform();
  }

  public void enter() {
    logInfoMessage("ENTER", webDriver);
    new Actions(webDriver).sendKeys(Keys.ENTER).build().perform();
  }

  public void mouseOverJS() {
    String mouseOverScript = "var element = arguments[0];"
        + "var mouseEventObj = document.createEvent('MouseEvents');"
        + "mouseEventObj.initEvent( 'mouseover', true, true );"
        + "element.dispatchEvent(mouseEventObj);";
    getJavascriptExecutor().executeScript(mouseOverScript, getWrappedWebElement());
  }

  public void mouseOver() {
    new Actions(webDriver).moveToElement(getWrappedWebElement()).build().perform();
    logInfoMessage("Mouse over", getWrappedWebElement());
  }

  public void mouseOverCoordinates(int xOffset, int yOffset) {
    logInfoMessage("Mouse over %s xOffset, %s yOffsetCoordinates", xOffset, yOffset);
    new Actions(webDriver).moveToElement(getWrappedWebElement(), xOffset, yOffset).build()
        .perform();
  }

  public void mouseOverCoordinatesAndClick(int xOffset, int yOffset) {
    new Actions(webDriver).moveToElement(getWrappedWebElement(), xOffset, yOffset).click().build()
        .perform();
    logInfoMessage("Mouse over %s xOffset, %s yOffset coordinates and click", xOffset, yOffset);
  }

  public WebElement findChild(Element childElement) {
    logInfoMessage("Search %s child element from %s parent element", childElement.by, by);
    return waitForPresenceOfNestedElement(by, childElement.getBy());
  }

  public WebElement findChildNoWait(Element childElement) {
    logInfoMessage("Search %s child element from %s parent element without wait", childElement.by,
        by);
    return waitForPresenceOfNestedElement(by, childElement.getBy(), 0);
  }

  public boolean hasChild(Element childElement) {
    boolean hasChild;
    try {
      hasChild = findChildNoWait(childElement) != null;
    } catch (TimeoutException e) {
      hasChild = false;
    }
    return hasChild;
  }

  public List<WebElement> findChildren(Element childElement) {
    logInfoMessage("Search %s child elements from %s parent element", childElement.by, this.by);
    return waitForPresenceOfNestedElements(by, childElement.getBy());
  }

  public void clickOnInvisibleElement() {
    String script = "var object = arguments[0];"
        + "var theEvent = document.createEvent(\"MouseEvent\");"
        + "theEvent.initMouseEvent(\"click\", true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
        + "object.dispatchEvent(theEvent);";
    ((JavascriptExecutor) webDriver).executeScript(script, getWrappedWebElement());
  }

  public void clickJs() {
    getJavascriptExecutor().executeScript("arguments[0].click();", getWrappedWebElement());
  }

  public void switchToFrame() {
    WebDriverSingleton.getInstance().getDriver().switchTo()
        .frame(this.getWrappedWebElement());
  }

  public void sendFilePath(String value) {
    logFillInAction(value);
    WebElement element = getWrappedWebElement();
    element.sendKeys(value);
  }

  public void clearAndSendFilePath(String value) {
    logFillInAction(value);
    WebElement element = getWrappedWebElement();
    clearFilePath();
    element.sendKeys(value);
  }

  public void clearFilePath() {
    logStateElementOnAction();
    waitForPresence().clear();
  }

  /*States*/


  public boolean isEnabled() {
    logStateElementOnAction();
    boolean isEnabled = getWrappedWebElement().isEnabled();
    return isEnabled;
  }

  public boolean isDisplayed(int timeOutInSeconds) {
    logStateElementOnAction();
    boolean isDisplayed;
    try {
      isDisplayed = waitForVisibility(timeOutInSeconds) != null;
    } catch (TimeoutException e) {
      isDisplayed = false;
    }
    return isDisplayed;
  }

  public boolean isNotDisplayed() {
    logStateElementOnAction();
    boolean isNotDisplayed;
    try {
      isNotDisplayed = waitForInvisibility();
    } catch (TimeoutException e) {
      isNotDisplayed = true;
    }
    return isNotDisplayed;
  }

  public boolean isDisplayed() {
    return isDisplayed(DEFAULT_TIME_OUT_IN_SECONDS);
  }

  public boolean isDisplayedShortTimeOut() {
    return isDisplayed(SHORT_TIME_OUT_IN_SECONDS);
  }

  public boolean isDisplayedNoWait() {
    return isDisplayed(0);
  }

  public boolean isAboveAnotherElement(Element anotherElement) {
    logInfoMessage("Check that %s element is above %s element", getWrappedWebElement(),
        anotherElement);
    int elementHeight = getWrappedWebElement().getLocation().getY();
    int anotherElementHeight = anotherElement.getWrappedWebElement().getLocation().getY();
    return elementHeight < anotherElementHeight;
  }

  public boolean isBelowAnotherElement(Element anotherElement) {
    logInfoMessage("Check that %s element is above %s element", getWrappedWebElement(),
        anotherElement);
    int elementHeight = getWrappedWebElement().getLocation().getY();
    int anotherElementHeight = anotherElement.getWrappedWebElement().getLocation().getY();
    return elementHeight > anotherElementHeight;
  }

  public boolean isToTheRightAnotherElement(Element anotherElement) {
    logInfoMessage("Check that %s element is to the right %s element", getWrappedWebElement(),
        anotherElement);
    int elementWidth = getWrappedWebElement().getLocation().getX();
    int anotherElementWidth = anotherElement.getWrappedWebElement().getLocation().getX();
    return elementWidth > anotherElementWidth;
  }

  public boolean isAllChildrenDisplayImages() {
    logInfoMessage("Check all children display images");
    for (Element element : getElements()) {
      WebElement image = findChild(Element.byTagName("img"));
      element.scrollIntoView(image);
      if (!image.isDisplayed()) {
        return false;
      }
    }
    return true;
  }

  public boolean isCheckBoxUnchecked() {
    logStateElementOnAction();
    return getWrappedWebElement().getAttribute("class").contains("unchecked");
  }

  public boolean isCheckBoxChecked() {
    logStateElementOnAction();
    return isSelected();
  }

  private boolean isPresent(boolean expected, int timeout) {
    logStateElementOnAction();
    try {
      webDriver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
      return (!webDriver.findElements(by).isEmpty()) == expected;
    } finally {
      webDriver.manage().timeouts().implicitlyWait(0L, TimeUnit.SECONDS);
    }
  }

  public boolean isPresent() {
    return isPresent(SHORT_TIME_OUT_IN_SECONDS);
  }

  public boolean isPresent(int timeout) {
    return isPresent(true, timeout);
  }

  public boolean isPresentNoWait() {
    return isPresent(true, 0);
  }

  public boolean isAbsent(int timeout) {
    return isPresent(false, timeout);
  }

  public boolean isAbsent() {
    return isPresent(false, SHORT_TIME_OUT_IN_SECONDS);
  }

  public boolean isClickable() {
    return waitForClickable() != null;
  }

  public boolean isClickable(int timeout) {
    return waitForClickable(timeout) != null;
  }

  public boolean isVisibleInViewPoint() {
    logInfoMessage("Check that %s element is visible in view point", getWrappedWebElement());
    return (boolean) getJavascriptExecutor().executeScript(
        "var elem = arguments[0],               " +
            "  box = elem.getBoundingClientRect(),    " +
            "  cx = box.left + box.width / 2,         " +
            "  cy = box.top + box.height / 2,         " +
            "  e = document.elementFromPoint(cx, cy); " +
            "for (; e; e = e.parentElement) {         " +
            "  if (e === elem)                        " +
            "    return true;                         " +
            "}                                        " +
            "return false;                            ", getWrappedWebElement());
  }

  public boolean isCenteredInParentHorizontally(Element parentElement) {
    logInfoMessage("Check that %s element is centered in parent go", getWrappedWebElement());
    return (boolean) getJavascriptExecutor().executeScript(
        "var element = arguments[0];                                                         " +
            "var parentElement = arguments[1];                                                 " +
            "var elementBox = element.getBoundingClientRect();                                 " +
            "var elementBoxCenterX = elementBox.left + elementBox.width / 2;                   " +
            "var parentElementBox = parentElement.getBoundingClientRect();                     " +
            "var parentElementBoxCenterX = parentElementBox.left + parentElementBox.width / 2; " +
            "return (Math.abs(elementBoxCenterX - parentElementBoxCenterX) < 2) ? true : false;",
        getWrappedWebElement(), parentElement.getWrappedWebElement());
  }

  public boolean isPreChecked() {
    logStateElementOnAction();
    return (Boolean) getJavascriptExecutor()
        .executeScript("return arguments[0].checked", getWrappedWebElement());
  }

  public boolean isAllElementsDisplayed() {
    return getElements().stream().allMatch(Element::isDisplayed);
  }

  public boolean isAllElementsPresent() {
    return getElements().stream().allMatch(Element::isPresent);
  }

  public boolean isAllElementsDisplayed(int timeout) {
    return getElements(timeout).stream().allMatch(Element::isDisplayed);
  }

  public boolean isAllElementsDisplayedNoWait() {
    return getElements().stream().allMatch(Element::isDisplayedNoWait);
  }

  public boolean isHasScrollbar() {
    logInfoMessage("Check that %s element has scrollbar", getWrappedWebElement());
    int totalHeight = Integer.parseInt(this.getAttributeValue("scrollHeight"));
    int visibleHeight = Integer.parseInt(this.getAttributeValue("offsetHeight"));
    return totalHeight > visibleHeight;
  }

  public boolean isSelected() {
    logInfoMessage("Check that %s element is selected", getWrappedWebElement());
    return getWrappedWebElement().isSelected();
  }


  /*Waits*/


  public WebElement waitForPresence() {
    return new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS))
        .until(ExpectedConditions.presenceOfElementLocated(by));
  }

  public List<WebElement> waitForPresenceOfAllElements(int timeout) {
    return new WebDriverWait(webDriver, Duration.ofSeconds(timeout))
        .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
  }

  public WebElement waitForPresenceOfNestedElement(By parentLocator, By childLocator, int timeout) {
    return new WebDriverWait(webDriver, Duration.ofSeconds(timeout))
        .until(ExpectedConditions.presenceOfNestedElementLocatedBy(parentLocator, childLocator));
  }

  public WebElement waitForPresenceOfNestedElement(By parentLocator, By childLocator) {
    return waitForPresenceOfNestedElement(parentLocator, childLocator, DEFAULT_TIME_OUT_IN_SECONDS);
  }

  public List<WebElement> waitForPresenceOfNestedElements(By parentLocator, By childLocator) {
    return new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS))
        .until(ExpectedConditions.presenceOfNestedElementsLocatedBy(parentLocator, childLocator));
  }

  public WebElement waitForVisibility(int timeout) {
    logInfoMessage("Wait element will be visibility");
    return new WebDriverWait(webDriver, Duration.ofSeconds(timeout))
        .until(ExpectedConditions.visibilityOfElementLocated(by));
  }

  public List<WebElement> waitUntilAllElementsLocatedByAreVisible() {
    logInfoMessage("Wait until all elements will be visible");
    return new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS))
        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getBy()));
  }

  public List<WebElement> waitUntilAllElementsLocatedByAreVisible(int timeout) {
    logInfoMessage("Wait until all elements will be visible");
    return new WebDriverWait(webDriver, Duration.ofSeconds(timeout))
        .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getBy()));
  }

  public boolean waitUntilRequiredElementsAreInvisible(List<WebElement> elements) {
    logInfoMessage("Wait until all elements will be invisible");
    return new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS))
        .until(ExpectedConditions.invisibilityOfAllElements(elements));
  }

  public boolean waitUntilRequiredElementsAreInvisible(List<WebElement> elements, int timeout) {
    logInfoMessage("Wait until all elements will be invisible");
    return new WebDriverWait(webDriver, Duration.ofSeconds(timeout))
        .until(ExpectedConditions.invisibilityOfAllElements(elements));
  }

  public WebElement waitForVisibility() {
    return waitForVisibility(DEFAULT_TIME_OUT_IN_SECONDS);
  }

  public WebElement waitForClickable() {
    logInfoMessage("Wait element will be clickable");
    return new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS))
        .until(ExpectedConditions.elementToBeClickable(by));
  }

  public WebElement waitForClickable(int timeout) {
    logInfoMessage("Wait element will be clickable");
    return new WebDriverWait(webDriver, Duration.ofSeconds(timeout))
        .until(ExpectedConditions.elementToBeClickable(by));
  }

  public boolean waitForInvisibility() {
    logInfoMessage("Wait until the element is invisible");
    return new WebDriverWait(webDriver, Duration.ofSeconds(LONG_TIME_OUT_IN_SECONDS))
        .until(ExpectedConditions.invisibilityOfElementLocated(by));
  }

  public boolean waitForInvisibilityWithPageLoadTimeout() {
    logInfoMessage("Wait until the element is invisible");
    return new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIMEOUT_FOR_PAGE_LOAD))
        .until(ExpectedConditions.invisibilityOfElementLocated(by));
  }

  public boolean waitForInvisibilityShortTimeout() {
    logInfoMessage("Wait until the element is invisible");
    return new WebDriverWait(webDriver, Duration.ofSeconds(SHORT_TIME_OUT_IN_SECONDS))
        .until(ExpectedConditions.invisibilityOfElementLocated(by));
  }

  public void waitForAbsence(int timeout) {
    logInfoMessage("Wait element absence");
    new WebDriverWait(webDriver, Duration.ofSeconds(timeout))
        .until((Function<WebDriver, Boolean>) localWebDriver -> {
          if (isPresent(1)) {
            return false;
          }
          return true;
        });
  }

  public void waitForAbsence() {
    waitForAbsence(DEFAULT_TIMEOUT_FOR_PAGE_LOAD);
  }

  public void waitForDisappear() {
    logInfoMessage("Wait element disappear");
    if (isPresentNoWait()) {
      waitForAbsence();
    }
  }

  public void waitUntilAttributeGetsValue(String attributeName, String expectedValue) {
    logInfoMessage("Wait until %s attribute gets %s value", attributeName, expectedValue);
    new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS))
        .until(
            ExpectedConditions.attributeToBe(getWrappedWebElement(), attributeName, expectedValue));
  }

  public void waitUntilAttributeGetsValue(String attributeName, String expectedValue, int seconds) {
    logInfoMessage("Wait until %s attribute gets %s value", attributeName, expectedValue);
    new WebDriverWait(webDriver, Duration.ofSeconds(seconds))
        .until(
            ExpectedConditions.attributeToBe(getWrappedWebElement(), attributeName, expectedValue));
  }

  public void waitUntilAttributeChangeCssValue(String cssName, String expectedValue) {
    logInfoMessage("Wait until attribute get %s value from %s", expectedValue, cssName);
    new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS))
        .until(new ExpectedCondition<Boolean>() {
          public Boolean apply(WebDriver webDriver) {
            if (!getWrappedWebElement().getCssValue(cssName).equals(expectedValue)) {
              return true;
            }
            return false;
          }
        });
  }

  public void waitForTextToBePresent() {
    new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS))
        .until(
            ExpectedConditions.not(
                ExpectedConditions.textToBe(getBy(), "")));
  }

  public void waitForTextToBeNotPresent() {
    new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS))
        .until(ExpectedConditions.textToBe(getBy(), ""));
  }

  public void waitAttributeValueIsNotEmpty(String attribute) {
    new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS))
        .until(ExpectedConditions.attributeToBeNotEmpty(getWrappedWebElement(), attribute));
  }

  public void waitTextNotToBePresentInElement(String text) {
    waitTextNotToBePresentInElement(text, DEFAULT_TIME_OUT_IN_SECONDS);
  }

  public void waitTextNotToBePresentInElement(String text, int timeout) {
    new WebDriverWait(webDriver, Duration.ofSeconds(timeout))
        .until(ExpectedConditions.not(
            ExpectedConditions
                .textToBePresentInElement(getWrappedWebElement(), text)));
  }

  public void waitTextToBePresentInElement(String text) {
    new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS))
        .until(ExpectedConditions.textToBePresentInElement(getWrappedWebElement(), text));
  }

  public void waitNumberOfElementsToBeMoreThan(int initialValue) {
    new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS))
        .until(ExpectedConditions
            .numberOfElementsToBeMoreThan(getBy(),
                initialValue));
  }

  public void waitNumberOfElementsToBeLessThan(int initialValue) {
    new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS))
        .until(ExpectedConditions
            .numberOfElementsToBeLessThan(getBy(),
                initialValue));
  }

  public void waitNeedValueAttributeContaining(String attribute, String value) {
    waitNeedValueAttributeContainingWithTimeout(attribute, value, DEFAULT_TIMEOUT_FOR_PAGE_LOAD);
  }

  public void waitNeedValueAttributeContainingWithTimeout(String attribute, String value,
      int timeout) {
    new WebDriverWait(webDriver, Duration.ofSeconds(timeout))
        .until(ExpectedConditions.attributeContains(getWrappedWebElement(), attribute, value));
  }

  public void waitTextToBeAfterScreenRefresh(String expectedText) {
    waitTextToBeAfterScreenRefresh(expectedText, DEFAULT_TIME_OUT_IN_SECONDS);
  }

  public void waitTextToBeAfterScreenRefresh(String expectedText, int timeOut) {
    new WebDriverWait(webDriver, Duration.ofSeconds(timeOut))
        .until(
            ExpectedConditions
                .refreshed(ExpectedConditions.textToBe(by, expectedText)));
  }

  public void waitForVisibilityAfterScreenRefresh() {
    new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS))
        .until(
            ExpectedConditions
                .refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(by)));
  }

  public void waitStalenessOfElement() {
    new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS))
        .until(
            ExpectedConditions
                .stalenessOf(getWrappedWebElement()));
  }

  public void waitForEnabled() {
    logInfoMessage("Wait until element be enabled %s", getWrappedWebElement().toString());
    new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS))
        .until((Function<WebDriver, Boolean>) localWebDriver -> {
          return isEnabled();
        });
  }

  public boolean isElementClassDisabled() {
    return getWrappedWebElement()
        .getAttribute(Element.ATTRIBUTE_CLASS)
        .contains(Element.DISABLED_ATTRIBUTE);
  }

  public boolean isElementClassEnabled() {
    return getWrappedWebElement()
        .getAttribute(Element.ATTRIBUTE_CLASS)
        .contains(Element.ENABLED_ATTRIBUTE);
  }

  public Element waitForElementClassDisabled() {
    logInfoMessage("Wait until element class is disabled %s",
        getAttributeValue(Element.ATTRIBUTE_CLASS));
    new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS))
        .until(ExpectedConditions.attributeContains(getWrappedWebElement(), Element.ATTRIBUTE_CLASS,
            Element.DISABLED_ATTRIBUTE));
    return this;
  }

  public Element waitForElementClassEnabled() {
    logInfoMessage("Wait until element class is enabled %s",
        getAttributeValue(Element.ATTRIBUTE_CLASS));
    new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS))
        .until(ExpectedConditions.attributeContains(getWrappedWebElement(), Element.ATTRIBUTE_CLASS,
            Element.ENABLED_ATTRIBUTE));
    return this;
  }

  /*Getters*/


  public WebElement getWrappedWebElement() {
    return waitForPresence();
  }

  public List<WebElement> getWrappedWebElements(int timeout) {
    return waitForPresenceOfAllElements(timeout);
  }

  public List<Element> getElements() {
    return getElements(DEFAULT_TIME_OUT_IN_SECONDS);
  }

  public List<Element> getElements(int timeout) {
    logInfoMessage("Get elements");
    if (!LocatorManager.LocatorStrategy.XPATH.equals(getLocatorManager().getStrategy())) {
      throw new UnsupportedLocatorStrategyException(
          "To get elements by index need to use Xpath strategy!");
    }
    int listSize = getWrappedWebElements(timeout).size();
    if (listSize == 0) {
      throw new NoSuchElementException(
          "List of requested elements is empty! Try to get the list immediately before use or adjust the locator: "
              + by);
    }
    String xpath = getLocatorManager().getLocatorString();
    List<Element> elements = new ArrayList<>();
    for (int i = 1; i <= listSize; i++) {
      Element element = Element.byXpath("(%s)[%s]", xpath, Integer.toString(i));
      elements.add(element);
    }
    return elements;
  }

  public String getText() {
    String text = waitForVisibility().getText();
    logGetTextAction(text);
    return text;
  }

  public String getTextJS() {
    String text = getJavascriptExecutor().executeScript("return arguments[0].innerHTML",
        getWrappedWebElement()).toString();
    logGetTextAction(text);
    return text;
  }

  public List<String> getElementsText() {
    return getElements().stream().map(Element::getText).collect(Collectors.toList());
  }

  public List<String> getElementsClassName() {
    return getElements().stream().map(x -> x.getAttributeValue(ATTRIBUTE_CLASS))
        .collect(Collectors.toList());
  }

  public boolean isAttributePresent(String attribute) {
    logStateElementOnAction();
    return getWrappedWebElement().getAttribute(attribute) != null;
  }

  public String getAttributeValue(String attribute) {
    String attributeValue = getWrappedWebElement().getAttribute(attribute);
    logInfoMessage("Value of attribute '%s' is: '%s'", attribute, attributeValue);
    return attributeValue;
  }

  public String getCssValue(String property) {
    String cssValue = getWrappedWebElement().getCssValue(property);
    logInfoMessage("CSS value of property '%s' is: '%s'", property, cssValue);
    return cssValue;
  }

  public String getHoveredCssValue(String property) {
    mouseOver();
    String cssValue = getWrappedWebElement().getCssValue(property);
    logInfoMessage("When element '%s' are hovered CSS value of property '%s' is: '%s'",
        getWrappedWebElement(), property, cssValue);
    return getCssValue(property);
  }

  public int getHeight() {
    int elementHeight = getWrappedWebElement().getSize().getHeight();
    logInfoMessage("Height of '%s' is: '%s'", getWrappedWebElement(), elementHeight);
    return elementHeight;
  }

  public int getWidth() {
    int elementWidth = getWrappedWebElement().getSize().getWidth();
    logInfoMessage("Width of '%s' is: '%s'", getWrappedWebElement(), elementWidth);
    return elementWidth;
  }

  public By getBy() {
    return by;
  }

  private LocatorManager getLocatorManager() {
    return locatorManager;
  }

  private JavascriptExecutor getJavascriptExecutor() {
    return (JavascriptExecutor) webDriver;
  }

  public boolean isDisabled() {
    boolean isDisabled;
    try {
      isDisabled = getAttributeValue(DISABLED_ATTRIBUTE).equals("true");
    } catch (NullPointerException e) {
      isDisabled = false;
    }
    return isDisabled;
  }

  public void mouseDown() {
    Locatable mouseDownItem = (Locatable) getWrappedWebElement();
    Mouse mouse = ((HasInputDevices) webDriver).getMouse();
    mouse.mouseDown(mouseDownItem.getCoordinates());
  }

  public void waitUntilDocumentReadiness() {
    new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIMEOUT_FOR_PAGE_LOAD))
        .until(CustomConditions.jQueryAJAXCallsHaveCompleted());
    new WebDriverWait(webDriver, Duration.ofSeconds(DEFAULT_TIMEOUT_FOR_PAGE_LOAD))
        .until(CustomConditions.jsReadyStateCompleted());
  }

  public String getTooltipText(Element child) {
    mouseOverCoordinates(1, 1);
    return findChild(child).getText();
  }
}
