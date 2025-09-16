import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PracticeFormTest {

    @BeforeAll
    static void setUp() {
        /**
         * Настраиваем браузер, таймер страницы увеличен до минуты из-за долгой работы драйвера
         */
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadTimeout = 60000;
    }

    @Test
    void myFirstSomeTest() {
        /**
         * Переменные, дату рождения пришлось разбить на несколько
         */
        PracticeFormPage page = new PracticeFormPage();
        page.openPage("/automation-practice-form");
        String firstName = "Chiki";
        String lastName = "Bamboni";
        String gender = "Male";
        String email = "boba@biba.com";
        String mobile = "8005553535";
        String dobYear = "1995";
        String dobMonth = "September";
        String dobDay = "10";
        String subjects = "Maths";
        List<String> hobbies = (List.of("Sports", "Music"));
        String curAddress = "Zazhopinsk";

        /**
         * Ниже идет собственно работа с элементами страницы
         */
        page.setFirstname(firstName);
        page.setLastname(lastName);
        page.setEmail(email);
        page.setGender(gender);
        page.setMobile(mobile);
        page.setDateOfBirth(dobDay, dobMonth, dobYear);
        page.setSubjects(subjects);
        page.setHobbies(hobbies);
        page.setAddress(curAddress);
        page.submitForm();

        // Сначала на всякий проверяем что форма видна вообще
        $(".table-responsive").shouldBe(visible);


        /**
         * Вот тут вызываем метод сбора инфы из финальной таблички
         */
        Map<String, String> results = page.getSubmissionResults();

/*      Это использовалось для отладки сравнения ключей
        results.keySet().forEach(System.out::println);*/


        /**
         * Вот тут проводим сравнение полученных ранее данных с ожидаемыми из изначальных переменных
         */
        // Строка для правильности сравнения нескольких значений хобби
        String expectedHobbies = String.join(", ", hobbies);

        assertThat(results)
                .containsEntry("Student Name", firstName + " " + lastName)
                .containsEntry("Student Email", email)
                .containsEntry("Gender", gender)
                .containsEntry("Mobile", mobile)
                .containsEntry("Date of Birth", dobDay + " " + dobMonth + "," + dobYear)
                .containsEntry("Subjects", subjects)
                .containsEntry("Hobbies", expectedHobbies)
                .containsEntry("Address", curAddress);
    }
}