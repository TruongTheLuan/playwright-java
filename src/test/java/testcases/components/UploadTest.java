package testcases.components;

import com.microsoft.playwright.FileChooser;
import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import testcases.MasterTest;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class UploadTest extends MasterTest {
    @Test
    void verifyUploadFile(){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/components/upload");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        ClassLoader classLoader = getClass().getClassLoader();
        URL inputStream = classLoader.getResource("upload/my-upload-file.txt");
        String buttonUploadXpath = "(//button[span[normalize-space(text())='Click to Upload']]//preceding-sibling::input[@type='file'])[1]";
        Locator buttonUploadLocator = page.locator(buttonUploadXpath);
        buttonUploadLocator.setInputFiles(Path.of(inputStream.getPath()));
        String expectedElementXpath = "//span[normalize-space(.//text())='my-upload-file.txt']";
        Locator expectedElementLocator = page.locator(expectedElementXpath);
        assertThat(expectedElementLocator).hasText("my-upload-file.txt");
    }
}
