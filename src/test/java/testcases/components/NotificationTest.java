package testcases.components;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import testcases.MasterTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class NotificationTest extends MasterTest {
    @ParameterizedTest
    @ValueSource(strings = { "Success", "Info", "Warning", "Error" })
    void verifyNotification(String label){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/components/notification");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        String notificationButtonXpath = String.format("//div[contains(concat(' ',normalize-space(@class),' '),' ant-space-item ')]//button[.//text()[normalize-space()='%s']]", label);
        Locator notificationButtonLocator = page.locator(notificationButtonXpath);
        notificationButtonLocator.click();
        String expectedElementXpath = String.format("//div[contains(concat(' ',normalize-space(@class),' '),' ant-notification-notice-description ') and .//text()[normalize-space()='You have clicked the %s button.']]", label.toUpperCase());
        Locator expectedElementLocator = page.locator(expectedElementXpath);
        assertThat(expectedElementLocator).hasText(String.format("You have clicked the %s button.", label.toUpperCase()));
    }
}
