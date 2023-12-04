import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui.MainPageObject;
import ui.SearchPageObject;

import java.net.URL;

public class FirstTest extends CoreTestCase {
    private MainPageObject mainPageObject;

    @Before
    protected void setUp() throws Exception {
        super.setUp();
        mainPageObject = new MainPageObject(driver);
    }

    @After
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void firstTest() {
        System.out.println("Our first test");
    }

    @Test
    public void testSearch() {
        mainPageObject.waitForElementAndClick(By.xpath("//android.widget.TextView[@text=\"Поиск по Википедии\"]"), "Невозможно нажать на поле ввода", 15);
        mainPageObject.waitForElementAndSendKeys(By.xpath("//android.widget.EditText[@resource-id=\"org.wikipedia:id/search_src_text\"]"), "Кемеровский государственный университет", "Невозможно нажать на поле ввода", 15 );
        mainPageObject.waitForElementAndClick(By.xpath("//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_description\" and @text=\"высшее учебное заведение в Кемерове\"]"), "Невозможно нажать на элемент", 15);

        WebElement title_element = mainPageObject.waitForElementPresent(By.xpath("//android.view.View[@text=\"Кемеровский государственный университет\"]"), "Невозможно найти 'Кемеровский государственный университет'", 15);
        String result = title_element.getText();

        Assert.assertEquals("Найдено несовпадение в названии статьи", "Кемеровский государственный университет", result);
    }

    @Test
    public void testSearchElement() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Кемеровский государственный университет");
        searchPageObject.waitForSearchResult("Высшее учебное заведение в Кемерове");

    }
}
