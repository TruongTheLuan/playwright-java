package testcases.components;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import testcases.MasterTest;

import java.util.Locale;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TreeSelectTest extends MasterTest {
    @ParameterizedTest
    @ValueSource(strings = { "Light > Bamboo", "Heavy > Walnut", "Light > Pine", "Heavy" })
    void verifyTreeSelect(){
        String inputs = "Heavy > Walnut";
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/components/tree-select");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        String inputXpath = "//div[contains(concat(' ',normalize-space(@class),' '),' ant-select-selector ')]";
        Locator inputLocator = page.locator(inputXpath);
        inputLocator.click();

        String[] inputStrings = inputs.split(">");
        for(int i=0; i < inputStrings.length; i++){
            if(i < inputStrings.length - 1){
                String arrowXpath= String.format("(//span[normalize-space(text())='%s']//preceding::span[contains(concat(' ',normalize-space(@class),' '),' ant-select-tree-switcher ')])[last()]", inputStrings[i].trim());
                Locator arrowXLocator = page.locator(arrowXpath);
                String classValue = arrowXLocator.getAttribute("class");
                if(!classValue.contains("open")){
                    arrowXLocator.click();
                }
            }
            else{
                String inputItemXpath = String.format("//span[contains(concat(' ',normalize-space(@class),' '),' ant-select-tree-title ')][normalize-space(text())='%s']", inputStrings[i].trim());
                Locator inputItemLocator = page.locator(inputItemXpath);
                inputItemLocator.click();
            }
        }

        String expectedElementXpath = "//div[contains(., 'Current value: ') and ./span[contains(concat(' ', normalize-space(@class),' '),' text-rose-500 ')]]";
        assertThat(page.locator(expectedElementXpath)).hasText(String.format("Current value: %s", inputStrings[inputStrings.length - 1].trim().toLowerCase()));

    }
}
