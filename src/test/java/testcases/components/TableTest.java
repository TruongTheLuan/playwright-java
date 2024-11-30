package testcases.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.Locator;
import model.Customer;
import org.junit.jupiter.api.Test;
import testcases.MasterTest;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static data.TableTestData.CUSTOMERS_EXPECTED_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TableTest extends MasterTest {
    String[] definedHeaders = {"Name", "Age", "Address", "Tags"};
    @Test
    void verifyTable() throws InterruptedException, JsonProcessingException, NoSuchFieldException, IllegalAccessException {
        page.navigate("https://test-with-me-app.vercel.app/learning/web-elements/components/table");
        assertThat(page).hasTitle("Test With Me aka Tho Test");
        Thread.sleep(5000);
        String tableXpath = "(//div[.//text()[normalize-space()='Table']]//following::table)[1]";
        String tableHeaderXpath = String.format("%s//th", tableXpath);
        Locator tableHeaderLocator = page.locator(tableHeaderXpath);
        List<Locator> headerLocators = tableHeaderLocator.all();
        List<String> headerList = new ArrayList<>();
        for(Locator header : headerLocators){
            headerList.add(header.textContent());
        }

        List<Customer> actualCustomers = new ArrayList<>();
        String rowDataXpath = String.format("%s//tbody//tr", tableXpath);
        Locator rowDataLocator = page.locator(rowDataXpath);
        String nextButtonXpath = "//li[contains(concat(' ',normalize-space(@class),' '),' ant-pagination-next ') and @title='Next Page']";
        boolean isNext;
        do{
            List<Locator> rowDataLocators = rowDataLocator.all();
            for(Locator row : rowDataLocators){
                Customer customer = new Customer();
                for(String header : definedHeaders){
                    Field field = Customer.class.getDeclaredField(header.toLowerCase());
                    field.setAccessible(true);
                    int index = headerList.indexOf(header);
                    String cellLocator = String.format("//td[%s]", index + 1);
                    String value = row.locator(cellLocator).textContent();
                    if("Age".equals(header)){
                        field.set(customer, Integer.parseInt(value));
                    }else{
                        field.set(customer, value);
                    }
                }
                actualCustomers.add(customer);
            }
            Locator nextButtonLocator = page.locator(nextButtonXpath);
            isNext = !Boolean.parseBoolean(nextButtonLocator.getAttribute("aria-disabled"));
            if(isNext){
                nextButtonLocator.click();
            }
        }while(isNext);
        ObjectMapper mapper = new ObjectMapper();
        List<Customer> expectedCustomers = mapper.readValue(CUSTOMERS_EXPECTED_DATA, new TypeReference<List<Customer>>() {
        });
        boolean isActualContainsAllExpected = actualCustomers.containsAll(expectedCustomers);
        assertTrue(isActualContainsAllExpected);
        boolean isExpectedContainsAllActual = expectedCustomers.containsAll(actualCustomers);
        assertTrue(isExpectedContainsAllActual);
    }
}