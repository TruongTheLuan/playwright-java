package testcases.components;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import testcases.MasterTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RatingTest extends MasterTest{
    void selectRating(float input, String label){
        boolean isHalfStar = input < Math.ceil(input);
        String firstOrSecond = isHalfStar ? "ant-rate-star-first" : "ant-rate-star-second";
        String startXpath = String.format("(//div[normalize-space() = '%s']//following::div[@aria-posinset = '%s']//div[contains(concat(' ',normalize-space(@class),' '),' %s ')])[1]", label, Math.round(Math.ceil(input)), firstOrSecond);
        String currentFullStarXpath = String.format("//div[normalize-space() = '%s']//following::ul[1]//li[contains(concat(' ',normalize-space(@class),' '),' ant-rate-star-full ')]", label);
        String currentHalfStarXpath = String.format("//div[normalize-space() = '%s']//following::ul[1]//li[contains(concat(' ',normalize-space(@class),' '),' ant-rate-star-half ')]", label);
        Locator starLocator = page.locator(startXpath);
        float currentRating = page.locator(currentFullStarXpath).count();
        if(page.locator(currentHalfStarXpath).count() != 0){
            currentRating += 0.5f;
        }
        if(currentRating != input){
            starLocator.click();
        }
    }
    @ParameterizedTest
    @CsvSource({
            "1, terrible",
            "2, bad",
            "3, normal",
            "4, good",
            "5, wonderful"
    })
    void verifyRatingFullStar(int input, String expectedOutput){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/components/rating");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        selectRating(input, "Rate");
        String expectedLabelXpath = "//div[contains(., 'Current rating: ') and ./span[contains(concat(' ', normalize-space(@class),' '),' text-rose-500 ')]][1]";
        Locator expectedLabelLocator = page.locator(expectedLabelXpath);
        assertThat(expectedLabelLocator).hasText(String.format(" Current rating: %s", expectedOutput));
    }

    @ParameterizedTest
    @ValueSource(floats = {0.5f, 1, 1.5f, 2, 2.5f, 3, 3.5f, 4, 4.5f, 5})
    void verifyRatingHalfStar(float input){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/components/rating");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        selectRating(input, "Haft Rate");
        String expectedLabelXpath = "//div[contains(., 'Current rating: ') and ./span[contains(concat(' ', normalize-space(@class),' '),' text-rose-500 ')]][2]";
        String expectedValue = String.valueOf(input);;
        if(!(input < Math.ceil(input))){
            expectedValue = String.valueOf(Math.round(input));
        }
        Locator expectedLabelLocator = page.locator(expectedLabelXpath);
        assertThat(expectedLabelLocator).hasText(String.format(" Current rating: %s", expectedValue));
    }
}
