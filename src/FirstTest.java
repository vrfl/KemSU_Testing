import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ui.MainPageObject;
import ui.SavedArticlesObject;
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

    //В поисковой строке найти «Хоббит, или Туда и обратно».
    // Перейти в статью, в крайнем верхнем правом углу нажать на три точки
    // и выбрать «Добавить в список для чтения».
    // Нажать «Понятно», указать название «Хоббит», нажать «Ок».
    // Выбрать внизу «Просмотр списка».
    // После чего нажать на три точки и удалить список для чтения полностью.
    // Удостовериться, что в списке отсутствует добавленный ранее раздел «Хоббит».
    @Test
    public void testWorkWithReadingList() {
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        SavedArticlesObject savedArticlesObject = new SavedArticlesObject(driver);

        String listName = "Хоббит";

        searchPageObject.initSearchInput();

        searchPageObject.typeSearchLine("Хоббит, или Туда и обратно");
        searchPageObject.clickBySearchResult("повесть английского писателя Джона Р. Р. Толкина");

        savedArticlesObject.saveArticle(listName);

        savedArticlesObject.returnToMainPage();

        savedArticlesObject.openSavedTab();
        savedArticlesObject.deleteArticlesList(listName);

        String articlesListXpath = savedArticlesObject.getArticlesListXpath(listName);
        Boolean isListExist = mainPageObject.elementExist(By.xpath(articlesListXpath), "Невозможно найти " + listName, 30);

        Assert.assertEquals("Такой сохраненный список есть", Boolean.FALSE, isListExist);
    }
}
