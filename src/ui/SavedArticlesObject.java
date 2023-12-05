package ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SavedArticlesObject extends MainPageObject {
    private static final String
    SAVE_BUTTON = "//android.widget.TextView[@content-desc=\"Сохранить\"]",
    ADD_TO_LIST_BUTTON = "//android.widget.Button[@resource-id=\"org.wikipedia:id/snackbar_action\"]",
    LIST_NAME_TEXT_EDITOR = "//android.widget.EditText[@resource-id=\"org.wikipedia:id/text_input\"]",
    OK_BUTTON = "//android.widget.Button[@resource-id=\"android:id/button1\"]",
    BACK_BUTTON = "//android.widget.ImageButton[@content-desc=\"Перейти вверх\"]",
    SAVED_TAB_BUTTON = "//android.widget.TextView[@resource-id=\"org.wikipedia:id/navigation_bar_item_small_label_view\" and @text=\"Сохранено\"]",
    ARTICLES_LIST_BUTTON = "//android.widget.TextView[@resource-id=\"org.wikipedia:id/item_title\" and @text=\"{SUBSTRING}\"]",
    ARTICLES_LIST_MORE_BUTTON = "//android.widget.ImageView[@content-desc=\"Ещё\"]",
    DELETE_BUTTON = "//android.widget.TextView[@resource-id=\"org.wikipedia:id/title\" and @text=\"Удалить список\"]",
    MAIN_PAGE_LOGO = "//android.widget.ImageView[@resource-id=\"org.wikipedia:id/main_toolbar_wordmark\"]"; // //android.widget.Button[@resource-id="android:id/button1"]



    public SavedArticlesObject(AppiumDriver driver) {
        super(driver);
    }

    public void saveArticle(String toListName) {
        waitForElementAndClick(By.xpath(SAVE_BUTTON), "Невозможно найти кнопку Сохранить", 15);
        waitForElementAndClick(By.xpath(ADD_TO_LIST_BUTTON), "Невозможно найти кнопку Добавить в список", 15);
        waitForElementAndSendKeys(By.xpath(LIST_NAME_TEXT_EDITOR), toListName, "Невозможно добавить значение  поле", 15);
        waitForElementAndClick(By.xpath(OK_BUTTON), "Невозможно найти кнопку OK", 15);
    }

    public void returnToMainPage() {
        while(!elementExist(By.xpath(MAIN_PAGE_LOGO), "Невозможно найти логотип", 15)) {
            waitForElementAndClick(By.xpath(BACK_BUTTON), "Невозможно найти кнопку Вернуться", 15);
        }
    }

    public void openSavedTab() {
        waitForElementAndClick(By.xpath(SAVED_TAB_BUTTON), "Невозможно найти кнопку Сохранено", 15);
    }

     public static String getArticlesListXpath(String substring) {
        return ARTICLES_LIST_BUTTON.replace("{SUBSTRING}", substring);
    }

    public void deleteArticlesList(String toListName) {
        String articlesListXpath = getArticlesListXpath(toListName);
        waitForElementAndClick(By.xpath(articlesListXpath), "Невозможно найти " + toListName, 15);
        waitForElementAndClick(By.xpath(ARTICLES_LIST_MORE_BUTTON), "Невозможно найти кнопку Еще", 15);
        waitForElementAndClick(By.xpath(DELETE_BUTTON), "Невозможно найти кнопку Удалить", 15);
        waitForElementAndClick(By.xpath(OK_BUTTON), "Невозможно найти кнопку Удалить", 15);
    }
}
