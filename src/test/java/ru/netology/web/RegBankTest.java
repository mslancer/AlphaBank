package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegBankTest {

    @BeforeEach
    void beforeTest() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTestValid() {
        $("[data-test-id=name] input").setValue("Кузьмин Владислав");
        $("[data-test-id=phone] input").setValue("+79276380430");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestValidDoubleName() {
        $("[data-test-id=name] input").setValue("Кузьмин Владислав-Владимир");
        $("[data-test-id=phone] input").setValue("+79276380430");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));


    }

    @Test
    void shouldTestValidDoubleSurname() {
        $("[data-test-id=name] input").setValue("Кузьмин-Кузнецов Владислав");
        $("[data-test-id=phone] input").setValue("+79276380430");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));


    }

    @Test
    void shouldTestInvalidNumber() {
        $("[data-test-id=name] input").setValue("Кузьмин Владислав");
        $("[data-test-id=phone] input").setValue("+792763804300");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));


    }

    @Test
    void shouldEnglishName() {
        $("[data-test-id=name] input").setValue("Kuzmin Vladislav");
        $("[data-test-id=phone] input").setValue("+79276380430");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));


    }

    @Test
    void shouldNameWithoutSpace() {
        $("[data-test-id=name] input").setValue("КузьминВладислав");
        $("[data-test-id=phone] input").setValue("+79276380430");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));


    }

    @Test
    void shouldNameWithoutSurname() {
        $("[data-test-id=name] input").setValue("Кузьмин");
        $("[data-test-id=phone] input").setValue("+79276380430");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));


    }

    @Test
    void shouldNameWithoutFullName() {
        $("[data-test-id=phone] input").setValue("+79276380430");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));


    }

    @Test
    void shouldTestWithoutNumber() {
        $("[data-test-id=name] input").setValue("Кузьмин Владислав");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));


    }

    @Test
    void shouldTestWithoutCheckbox() {
        $("[data-test-id=name] input").setValue("Кузьмин Владислав");
        $("[data-test-id=phone] input").setValue("+79276380430");
        $(".button").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));


    }
}
