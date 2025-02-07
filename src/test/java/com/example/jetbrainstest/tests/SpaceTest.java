package com.example.jetbrainstest.tests;

import com.example.jetbrainstest.MyExtension;
import com.example.jetbrainstest.pages.spacepages.SpacePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyExtension.class)
public class SpaceTest extends BaseTest {
    private SpacePage spacePage;

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        getDriver().get("https://www.jetbrains.com/space/");
        spacePage = new SpacePage(getDriver());
        spacePage.clickCookiesBannerButton();
    }

    @Test
    @DisplayName("Проверяем, что по клику кнопки лого осуществляется переход на главную страницу https://www.jetbrains.com/")
    public void buttonCheck() {
        spacePage.clickLogoButton();
        assertEquals("https://www.jetbrains.com/", spacePage.getCurrUrl(), "Не перешли на https://www.jetbrains.com/");
    }

    @Test
    @DisplayName("Проверям, что в верхнем меню отображается кнопка Developer Tools")
    public void displayedDevToolsButtonCheck() {
        assertTrue(spacePage.isDisplayedDevToolsButton(), "Кнопка Developer Tools не отображается");
    }

    @Test
    @DisplayName("Проверяем, что при нажатии на кнопку Developer Tools отображается меню")
    public void displayedMenuAfterClickCheck() {
        assertTrue(spacePage.isDisplTopMenu(), "Меню не отображается");
    }

    @Test
    @DisplayName("Проверям, что при наведении фокуса на кнопку Team Tools, " + "её текст становится белого цвета (Отключается прозрачность).")
    public void onFocusColorTeamToolButtonCheck() {
        assertEquals("1", spacePage.getOpacityOnFocusTeamToolsButton(), "Кнопка Developer Team Tools осталась серого цвета.");
    }

    @Test
    @DisplayName("Проверяем, что в меню открывшемся после нажатия по значку выбора языка, есть кнопка выбора русского языка.")
    public void rusLangButtonCheck() {
        assertTrue(spacePage.isDisplayedRusLangButton(), "В языковом меню нет кнопки выбора русского языка.");
    }

    @Test
    @DisplayName("Проверяем, что страна France после выбора  действительно установлена.")
    public void countryCheck() {
        assertEquals("France", spacePage.changeCountry(), "Страна не соответствует выбранной.");
    }

    @Test
    @DisplayName("Проверяем, что по комбинации CTRL+k открывается страница поиска  https://www.jetbrains.com/space/?s=full")
    public void searchPageCheck() {
        spacePage.goSearchPage();
        assertEquals("https://www.jetbrains.com/space/?s=full", spacePage.getCurrUrl(), "Не перешли на https://www.jetbrains.com/space/?s=full");
    }

    @Test
    @DisplayName("Проверям текст подсказки  в строке поиска")
    public void textSearchFieldCheck() {
        assertEquals("Ctrl+K for advanced search", spacePage.getSearchFieldText(), "Текст подсказки не соответсвует проверяемому.");
    }

    @Test
    @DisplayName("Проверяем, что кнопка On-Premises прозрачная.")
    public void colorOnPremisButtonCheck() {
        assertEquals("rgba(0, 0, 0, 0)", spacePage.getColorOnPremisButton(), "Кнопка On-Premises не прозрачная.");
    }

    @Test
    @DisplayName("Проверяем, что после нажатия на кнопку 'Learn how to review code from the IDE', " + "начинает воспроизводиться видео")
    public void videoPlayedCheck() {
        assertTrue(spacePage.isLernVideoPlayed(), "Видео не воспроизводится.");
    }

    @Test
    @DisplayName("Проверяем, что при установке фокуса на блоке 'Keep complete control over your data in a secure environment' " + "страницы https://www.jetbrains.com/space/download/  появляется градиент фона.")
    public void gradientBackgroundCheck() {
        assertNotEquals("none", spacePage.getGradientBackground(), "Градиент фона не появляется.");
    }
}