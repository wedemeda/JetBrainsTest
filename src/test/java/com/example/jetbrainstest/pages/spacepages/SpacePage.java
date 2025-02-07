package com.example.jetbrainstest.pages.spacepages;

import com.example.jetbrainstest.AllureLogger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.time.Duration;

// page_url = https://www.jetbrains.com/space
public class SpacePage {

    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(SpacePage.class));
    WebDriver driver;
    Actions actions;

    @FindBy(css = "[aria-label='Navigate to main page'][data-test='site-logo']")
    private WebElement logoButton;

    @FindBy(css = "[aria-label='Developer Tools: Open submenu'][data-test='main-menu-item-action']")
    private WebElement devToolsButton;

    @FindBy(css = "._mainSubmenu__content_6pz0jp_1")
    private WebElement topMenu;

    @FindBy(css = "[aria-label='Team Tools: Open submenu'][data-test='main-menu-item-action']")
    private WebElement teamToolsButton;

    @FindBy(css = "[aria-label='Open language selection'][data-test='language-picker']")
    private WebElement langMenuButton;

    @FindBy(xpath = "//*[text() = 'Русский']")
    private WebElement rusLangButton;

    @FindBy(css = "[data-test='footer-country-button']")
    private WebElement countryButton;

    @FindBy(css = "[data-test='search-input']")
    private WebElement changeCountryField;

    @FindBy(css = "[data-test='footer-popup-confirm-country']")
    private WebElement changeCountryButton;

    @FindBy(css = "body")
    private WebElement bodyPage;

    @FindBy(css = "[data-test='site-header-search-action']")
    private WebElement searchIconButton;

    @FindBy(css = "[data-test-id='search-input'][placeholder]")
    private WebElement searchField;

    @FindBy(xpath = "//*[text()='On-Premises']")
    private WebElement onPremisButton;

    @FindBy(css = "[data-jetbrains-cookies-banner-action='ACCEPT_ALL']")
    private WebElement cookiesBannerButton;

    @FindBy(xpath = "//button[contains(text(), 'Learn how to review code from the IDE')]")
    private WebElement learnVideoButton;

    @FindBy(xpath = "//iframe[contains(@src, 'youtube.com/embed')]")
    private WebElement iframeYoPlayer;

    @FindBy(css = "button[data-title-no-tooltip='Pause']")
    private WebElement pauseButton;

    @FindBy(css = "div.wt-col-3:nth-child(1)")
    private WebElement secureBlock;

    @FindBy(css = "a[class*='wt-link']")
    private WebElement linkMoreSpace;

    public void clickCookiesBannerButton() {
        cookiesBannerButton.click();
        LOG.info("Кликнули по кнопке принятия куки");
    }

    public void clickLogoButton() {
        logoButton.click();
        LOG.info("Кликнули по кнопке лого");
    }

    public Boolean isDisplayedDevToolsButton() {
        return devToolsButton.isDisplayed();
    }

    public Boolean isDisplTopMenu() {
        devToolsButton.click();
        LOG.info("Кликнули по кнопке " + devToolsButton.getText());
        return topMenu.isDisplayed();
    }

    public String getOpacityOnFocusTeamToolsButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        devToolsButton.sendKeys(Keys.TAB);
        LOG.info("Установили фокус на кнопке " + teamToolsButton.getText());
        wait.until(ExpectedConditions.attributeToBe(teamToolsButton, "opacity", "1"));
        return teamToolsButton.getCssValue("opacity");
    }

    public Boolean isDisplayedRusLangButton() {
        langMenuButton.click();
        LOG.info("Кликнули по кнопке выбора языка");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(rusLangButton));
        return rusLangButton.isDisplayed();
    }

    public String changeCountry() {
        countryButton.click();
        LOG.infoWithScreenshot("Кликнули по кнопке выбора страны");
        changeCountryField.sendKeys("France");
        LOG.info("Напечатали в поле ввода: France");
        changeCountryField.sendKeys(Keys.ENTER);
        LOG.info("Нажали Enter");
        changeCountryButton.click();
        LOG.info("Кликнули по кнопке Choose");
        return countryButton.getText();
    }

    public void goSearchPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        bodyPage.sendKeys(Keys.CONTROL + "k");
        LOG.info("Нажали комбинацию клавиш Ctrl+K");
        wait.until(ExpectedConditions.urlToBe("https://www.jetbrains.com/space/?s=full"));
    }

    public String getSearchFieldText() {
        searchIconButton.click();
        LOG.info("Кликнули по иконке лупы");
        return searchField.getDomAttribute("placeholder");
    }

    public String getColorOnPremisButton() {
        return onPremisButton.getCssValue("background-color");
    }

    public String getCurrUrl() {
        return driver.getCurrentUrl();
    }

    public Boolean isLernVideoPlayed() {
        learnVideoButton.click();
        LOG.info("Кликнули по иконке, вызывающей обучающее видео");
        driver.switchTo().frame(iframeYoPlayer);
        LOG.info("Переключаемся в iframe с видео плеером");
        return pauseButton.isDisplayed();
    }

    public String getGradientBackground() {
        onPremisButton.click();
        LOG.info("Кликнули по кнопке " + onPremisButton.getText());
        actions.moveToElement(linkMoreSpace).perform();
        LOG.info("Скроллим к блоку " + secureBlock.getText());
        actions.moveToElement(secureBlock).perform();
        LOG.info("Установили фокус на блоке " + secureBlock.getText());
        return secureBlock.getCssValue("background-image");
    }

    public SpacePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }
}