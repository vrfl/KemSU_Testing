package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {
    private static final String
    SEARCH_INIT_ELEMENT = "//android.widget.TextView[@text=\"Поиск по Википедии\"]",
    SEARCH_INPUT = "//android.widget.EditText[@resource-id=\"org.wikipedia:id/search_src_text\"]",
    SEARCH_RESULT = "//android.widget.TextView[@resource-id=\"org.wikipedia:id/page_list_item_description\" and @text=\"высшее учебное заведение в Кемерове\"]";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Невозможно нажать на поле ввода", 15);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Невозможно найти поле ввода", 15);
    }

    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT.replace("{SUBSTRING}", substring);
    }

    public void waitForSearchResult(String substring) {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Невозможно найти" + substring, 15);
    }
}
