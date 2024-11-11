package testcases.elements;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateTimeTest extends MasterTest{
    String inputXpath = "//input[@placeholder='%s']";
    String currentDateTimeXpath = "//input[@placeholder='%s']//following::div[contains(., 'Current')][1]";
    String _startValueXpath = "//td[contains(@title, '%s')]//div[text()[normalize-space()='%s']]";
    String _endValueXpath = "//td[contains(@title, '%s')]//div[text()[normalize-space()='%s']]";
    String _itemInCalendarXpath = "//div[text()[normalize-space()='%s']]";
    String _valueFromTitleXpath = "//div[text()[normalize-space()='%s']]//parent::td";
    boolean verifyNowTimeIsBetweenBeforeAndAfter(LocalTime before, LocalTime after, LocalTime now){
        if(now.isAfter(before) && now.isBefore(after)){
            return true;
        }
        else{
            return false;
        }
    }

    @ParameterizedTest
    @ValueSource(strings = { "Select time" })
    void verifyUserCanSelectTimeAtTimePicker(String inputPlaceholder) throws InterruptedException {
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/elements/date-time");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        LocalTime beforeObj = LocalTime.now();
        /*DateTimeFormatter beforeFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("before: " + beforeObj.format(beforeFormatObj));*/
        Thread.sleep(5000);
        String inputTimeXpath = String.format(inputXpath, inputPlaceholder);
        Locator inputTimeLocator = page.locator(inputTimeXpath);
        Locator nowLocator = page.locator("//a[text()[normalize-space()='Now']]");
        String currentTimeXpath = String.format(currentDateTimeXpath, inputPlaceholder);
        Locator currentTimeLocator = page.locator(currentTimeXpath);
        inputTimeLocator.click();
        nowLocator.click();
        String _nowTime = currentTimeLocator.textContent().substring(14,22);
        LocalTime nowTime = LocalTime.parse(_nowTime);
        /*DateTimeFormatter nowFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("Now time: " + nowTime.format(nowFormatObj));*/
        Thread.sleep(5000);
        LocalTime afterObj = LocalTime.now();
        /*DateTimeFormatter afterFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("after: " + afterObj.format(afterFormatObj));*/
        assertTrue(verifyNowTimeIsBetweenBeforeAndAfter(beforeObj,afterObj,nowTime));
    }

    boolean verifyEqualDate(LocalDate _current, LocalDate current){
        if(_current.isEqual(current)){
            return true;
        }
        else{
            return false;
        }
    }
    @ParameterizedTest
    @ValueSource(strings = { "Select date" })
    void verifyUserCanSelectTodayAtDatePicker(String inputPlaceholder){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/elements/date-time");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        LocalDate today = LocalDate.now();
        String inputDateXpath = String.format(inputXpath,inputPlaceholder);
        Locator inputDateLocator = page.locator(inputDateXpath);
        String currentDateXpath = String.format(currentDateTimeXpath, inputPlaceholder);
        Locator currentDateLocator = page.locator(currentDateXpath);
        Locator todayLocator = page.locator("//a[text()[normalize-space()='Today']]");
        inputDateLocator.click();
        todayLocator.click();
        String _currentDate = currentDateLocator.textContent().substring(14,24);
        LocalDate currentDate = LocalDate.parse(_currentDate);
        assertTrue(verifyEqualDate(today,currentDate));
    }

    @ParameterizedTest
    @CsvSource({
            "Select date,         14",
            "Select month,         May",
            "Select quarter,        Q1",
            "Select year,        2020"
    })
    void verifyUserCanSelectDateAtDatePicker(String inputPlaceholder, String value) {
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/elements/date-time");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        String inputDateXpath = String.format(inputXpath,inputPlaceholder);
        Locator inputDateLocator = page.locator(inputDateXpath);
        String itemInCalendarXpath = String.format(_itemInCalendarXpath, value);
        Locator itemInCalendarLocator = page.locator(itemInCalendarXpath);
        String valueFromTitleXpath = String.format(_valueFromTitleXpath, value);
        Locator valueFromTitleLocator = page.locator(valueFromTitleXpath);
        String currentDateXpath = String.format(currentDateTimeXpath, inputPlaceholder);
        Locator currentDateLocator = page.locator(currentDateXpath);
        inputDateLocator.click();
        itemInCalendarLocator.click();
        assertThat(currentDateLocator).containsText(valueFromTitleLocator.getAttribute("title"));
    }

    @ParameterizedTest
    @CsvSource({
            "Start month, End month, 2024, Jun, 2025, Jul",
            "Start quarter, End quarter, 2024, Q1, 2025, Q3",
            "Start year, End year, 2022, 2022, 2038, 2038"
    })
    void verifyUserCanSelectTimeAtDateRangePicker(String inputStartPlaceholer, String inputEndPlaceholer, String startYear, String startValue, String endYear, String endValue) {
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/elements/date-time");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        String startDateXpath = String.format(inputXpath,inputStartPlaceholer);
        Locator startDateLocator = page.locator(startDateXpath);
        String endDateXpath = String.format(inputXpath,inputEndPlaceholer);
        Locator endDateLocator = page.locator(endDateXpath);
        String startValueXpath = String.format(_startValueXpath, startYear, startValue);
        Locator startValueLocator = page.locator(startValueXpath);
        String endValueXpath = String.format(_endValueXpath, endYear, endValue);
        Locator endValueLocator = page.locator(endValueXpath);
        String currentDateXpath = String.format(currentDateTimeXpath, inputStartPlaceholer);
        Locator currentDateLocator = page.locator(currentDateXpath);
        startDateLocator.click();
        startValueLocator.click();
        endValueLocator.click();
        startDateLocator.getAttribute("value");
        endDateLocator.getAttribute("value");
        String result = startDateLocator.getAttribute("value") + " - " + endDateLocator.getAttribute("value");
        assertThat(currentDateLocator).containsText(result);
    }
}
