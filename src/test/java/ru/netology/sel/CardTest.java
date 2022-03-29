package ru.netology.sel;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CardTest {


    @Test
    void shouldDeliveredCard(){

        open("http://localhost:9999");

        LocalDate today = LocalDate.now();
        LocalDate futureDay = today.plusDays(4);

        $("[data-test-id='city'] .input__control").setValue("Рязань");
        $("[data-test-id='date'] .input__control").setValue(futureDay.toString());
        $("[data-test-id='name'] .input__control").setValue("Сергей Иванов");
        $("[data-test-id='phone'] .input__control").setValue("+79105633365");
        $("[data-test-id='agreement'] .checkbox__box").click();

        $$("[type='button']").find(exactText("Забронировать")).click();
        $(withText("Успешно")).shouldBe(appear, Duration.ofSeconds(14));
    }



}
