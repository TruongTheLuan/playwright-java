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
    void verifyUserCanSelectNowTime(String inputPlaceholder) throws InterruptedException {
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/elements/date-time");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        LocalTime beforeObj = LocalTime.now();
        /*DateTimeFormatter beforeFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("before: " + beforeObj.format(beforeFormatObj));*/
        Thread.sleep(5000);
        String selectTimeXpath = String.format(inputXpath, inputPlaceholder);
        Locator selectTimeLocator = page.locator(selectTimeXpath);
        Locator nowLocator = page.locator("//a[text()[normalize-space()='Now']]");
        String currentTimeXpath = String.format(currentDateTimeXpath, inputPlaceholder);
        Locator currentTimeLocator = page.locator(currentTimeXpath);
        selectTimeLocator.click();
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
    void verifyUserCanSelectDatePicker(String inputPlaceholder){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/elements/date-time");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        LocalDate today = LocalDate.now();
        String selectDateXpath = String.format(inputXpath,inputPlaceholder);
        Locator selectDateLocator = page.locator(selectDateXpath);
        String currentDateXpath = String.format(currentDateTimeXpath, inputPlaceholder);
        Locator currentDateLocator = page.locator(currentDateXpath);
        Locator todayLocator = page.locator("//a[text()[normalize-space()='Today']]");
        selectDateLocator.click();
        todayLocator.click();
        String _currentDate = currentDateLocator.textContent().substring(14,24);
        LocalDate currentDate = LocalDate.parse(_currentDate);
        assertTrue(verifyEqualDate(today,currentDate));
    }

    @ParameterizedTest
    @CsvSource({
            "Select year,         2024",
            "Select year,        2025"
    })
    void verifyUserCanSelectYearPicker(String inputPlaceholder, String year) {
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/elements/date-time");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        LocalDate today = LocalDate.now();
        String selectYearXpath = String.format(inputXpath,inputPlaceholder);
        Locator selectDateLocator = page.locator(selectYearXpath);
        String currentDateXpath = String.format(currentDateTimeXpath, inputPlaceholder);
        Locator currentDateLocator = page.locator(currentDateXpath);
        String yearInCalendarXpath = String.format("//div[text()[normalize-space()='%s']]", year);
        Locator yearInCalendarLocator = page.locator(yearInCalendarXpath);
        selectDateLocator.click();
        yearInCalendarLocator.click();
        assertThat(currentDateLocator).hasText(String.format("Current date: %s", year));
    }

    @Test
    void luantest(){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/elements/date-time");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        Locator selectMonthLocator = page.locator("//input[@placeholder='Select month']");
        Locator selectMonthInCalendarLocartor = page.locator("//div[text()[normalize-space()='May']]");
        Locator getMonthLocator = page.locator("//div[text()[normalize-space()='May']]//parent::td");
        Locator resultLocator = page.locator("//input[@placeholder='Select month']//following::div[contains(., 'Current')][1]");
        selectMonthLocator.click();
        selectMonthInCalendarLocartor.click();
        String result = resultLocator.textContent();
        String titleValue = getMonthLocator.getAttribute("title");
        String _result = "Current date: " + titleValue;
        System.out.println(_result);
    }
}
