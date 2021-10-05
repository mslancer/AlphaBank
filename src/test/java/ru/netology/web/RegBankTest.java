package ru.netology.web;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegBankTest {

    @Test
    void shouldTestValid() {
        open("http://localhost:7777");
        $("[data-test-id=name] input").setValue("Кузьмин Владислав");
        $("[data-test-id=phone] input").setValue("+79276380430");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestValidDoubleName() {
        open("http://localhost:7777");
        $("[data-test-id=name] input").setValue("Кузьмин Владислав-Владимир");
        $("[data-test-id=phone] input").setValue("+79276380430");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));


    }

    @Test
    void shouldTestValidDoubleSurname() {
        open("http://localhost:7777");
        $("[data-test-id=name] input").setValue("Кузьмин-Кузнецов Владислав");
        $("[data-test-id=phone] input").setValue("+79276380430");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));


    }

    @Test
    void shouldTestInvalidNumber() {
        open("http://localhost:7777");
        $("[data-test-id=name] input").setValue("Кузьмин Владислав");
        $("[data-test-id=phone] input").setValue("+792763804300");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=phone] .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));


    }

    @Test
    void shouldEnglishName() {
        open("http://localhost:7777");
        $("[data-test-id=name] input").setValue("Kuzmin Vladislav");
        $("[data-test-id=phone] input").setValue("+79276380430");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));


    }

    @Test
    void shouldNameWithoutSpace() {
        open("http://localhost:7777");
        $("[data-test-id=name] input").setValue("КузьминВладислав");
        $("[data-test-id=phone] input").setValue("+79276380430");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));


    }

    @Test
    void shouldNameWithoutSurname() {
        open("http://localhost:7777");
        $("[data-test-id=name] input").setValue("Кузьмин");
        $("[data-test-id=phone] input").setValue("+79276380430");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));


    }
}
