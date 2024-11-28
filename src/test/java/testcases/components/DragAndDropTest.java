package testcases.components;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;
import testcases.MasterTest;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DragAndDropTest extends MasterTest {
    void moveItems(String[] inputItems, String target){
        String itemXpath = "//div[contains(concat(' ',normalize-space(@class),' '),' ant-space-item ')]//button[.//text()[normalize-space()='%s']]";
        String containerXpath = String.format("//span[.//text()='Drag n Drop']//following::div[contains(concat(' ',normalize-space(@class),' '),' ant-space-item ')][%s]", target);
        Locator containerLocator = page.locator(containerXpath);
        for(String input : inputItems){
            Locator itemLocator = page.locator(String.format(itemXpath, input));
            itemLocator.dragTo(containerLocator);
        }
    }

    void verifyPanel(String[] expected, String target){
        String PanelXpath = String.format("//span[.//text()='Drag n Drop']//following::div[contains(concat(' ',normalize-space(@class),' '),' ant-space-item ')][%s]//button", target);
        List<String> allInnerTextPanel = page.locator(PanelXpath).allInnerTexts();
        System.out.print("allInnerTextPanel" + allInnerTextPanel);
        assertArrayEquals(expected, allInnerTextPanel.toArray());
    }

    @Test
    void verifyDragAndDrop() {
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/components/drag-n-drop");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        String[] moveToRightInputs = {"Apple", "Peach"};
        moveItems(moveToRightInputs, "2");
        String[] rightPanelExpected = {"Strawberry", "Mango", "Pineapple", "Grapes", "Apple", "Peach"};
        verifyPanel(rightPanelExpected, "2");

        String[] moveToLeftInputs = {"Mango", "Grapes"};
        moveItems(moveToLeftInputs, "1");
        String[] leftPanelExpected = {"Banane", "Orange", "Mango", "Grapes"};
        verifyPanel(leftPanelExpected, "1");
    }
}