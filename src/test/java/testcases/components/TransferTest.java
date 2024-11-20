package testcases.components;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import testcases.MasterTest;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TransferTest extends MasterTest {
    void moveItems(String[] inputItems, String target){
        String itemXpath = "//li[contains(concat(' ',normalize-space(@class),' '),' ant-transfer-list-content-item ') and .//text()[normalize-space()='%s']]";
        for(String input : inputItems){
            Locator itemLocator = page.locator(String.format(itemXpath, input));
            itemLocator.click();
        }
        String targetXpath = String.format("//div[contains(concat(' ',normalize-space(@class),' '),' ant-transfer-operation ')]//button[.//span[@aria-label='%s']]", target);
        Locator targetLocator = page.locator(targetXpath);
        targetLocator.click();
    }

    void verifyPanel(String[] expected, String target){
        String PanelXpath = String.format("//span[contains(concat(' ',normalize-space(@class),' '),' ant-transfer-list-header-title ') and text()[normalize-space()='%s']]/..//following-sibling::div//li[contains(concat(' ',normalize-space(@class),' '),' ant-transfer-list-content-item ')]", target);
        List<String> allInnerTextPanel = page.locator(PanelXpath).allInnerTexts();
        assertArrayEquals(expected, allInnerTextPanel.toArray());
    }

    @Test
    void verifyTransfer(){
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/components/transfer");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        String[] moveToRightInputs = {"Apple", "Banana"};
        moveItems(moveToRightInputs, "right");
        String[] rightPanelExpected = {"Apple", "Banana", "Orange", "Pineapple", "Strawberry"};
        verifyPanel(rightPanelExpected, "Target");

        String[] moveToLeftInputs = {"Orange", "Pineapple"};
        moveItems(moveToLeftInputs, "left");
        String[] leftPanelExpected = {"Kiwi", "Orange", "Pineapple"};
        verifyPanel(leftPanelExpected, "Source");
    }
}
