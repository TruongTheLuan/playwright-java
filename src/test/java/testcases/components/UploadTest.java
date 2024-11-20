package testcases.components;

import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import testcases.MasterTest;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class UploadTest extends MasterTest {
    @Test
    void verifyUpload() throws InterruptedException {
        String fileName = "test01.pdf";
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/components/upload");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        String buttonUploadXpath = "//button[normalize-space(.//text())='Click to Upload']";
        Locator buttonUploadLocator = page.locator(buttonUploadXpath);
        FileChooser fileChooser = page.waitForFileChooser(() -> buttonUploadLocator.click());
        fileChooser.setFiles(Paths.get(String.format("/Users/geotech/Desktop/%s", fileName)));
        String expectedElementXpath = String.format("//span[normalize-space(.//text())='test01.pdf']", fileName);
        Locator expectedElementLocator = page.locator(expectedElementXpath);
        assertThat(expectedElementLocator).hasText(fileName);
    }

    @Test
    void verifyRemoveFileAfterUpload() throws InterruptedException {
        String fileName = "test01.pdf";
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/components/upload");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        String buttonUploadXpath = "//button[normalize-space(.//text())='Click to Upload']";
        Locator buttonUploadLocator = page.locator(buttonUploadXpath);
        FileChooser fileChooser = page.waitForFileChooser(() -> buttonUploadLocator.click());
        fileChooser.setFiles(Paths.get(String.format("/Users/geotech/Desktop/%s", fileName)));
        String expectedElementXpath = String.format("//span[normalize-space(.//text())='test01.pdf']", fileName);
        Locator expectedElementLocator = page.locator(expectedElementXpath);
        assertThat(expectedElementLocator).hasText(fileName);
    }
}
