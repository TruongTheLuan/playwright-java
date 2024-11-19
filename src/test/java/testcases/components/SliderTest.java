package testcases.components;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import testcases.MasterTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SliderTest extends MasterTest {
    @Test
    void verifySlider(){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/components/slider");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        String sliderXpath = "//div[contains(concat(' ',normalize-space(@class),' '),' ant-slider-rail ')]";
        Locator sliderLocator = page.locator(sliderXpath);
        double input = 0.7;
        double sliderLength = sliderLocator.boundingBox().width * input;
        sliderLocator.hover();
        page.mouse().move(sliderLocator.boundingBox().x, sliderLocator.boundingBox().y);
        page.mouse().down();
        page.mouse().move(sliderLocator.boundingBox().x + sliderLength, sliderLocator.boundingBox().y );
        page.mouse().up();
        String expectedElementXpath = "//div[contains(., 'Current Value: ') and ./span[contains(concat(' ', normalize-space(@class),' '),' text-rose-500 ')]]";
        assertThat(page.locator(expectedElementXpath)).hasText("Current Value: 70");
    }
}
