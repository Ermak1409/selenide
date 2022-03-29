package ru.netology.sel;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CardTest {

    String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

    }


    String planningDate = generateDate(3);

    @Test
    void shouldDeliveredCard() {

        open("http://localhost:9999");

        $("[data-test-id='city'] .input__control").setValue("Рязань");
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] .input__control").setValue(planningDate);
        $("[data-test-id='name'] .input__control").setValue("Сергей Иванов");
        $("[data-test-id='phone'] .input__control").setValue("+79105633365");
        $("[data-test-id='agreement'] .checkbox__box").click();

        $$("[type='button']").find(exactText("Забронировать")).click();
        $(withText("Успешно")).shouldBe(appear, Duration.ofSeconds(15));

        $("[data-test-id='notification']")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15));

    }

}
