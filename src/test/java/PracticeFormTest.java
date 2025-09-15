import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PracticeFormTest {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920×1080";
        Configuration.timeout = 5000;
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void myFirstSomeTest() {
        PracticeFormPage page = new PracticeFormPage();
        page.openPage("/automation-practice-form");

        String firstName = "Chiki";
        String lastName = "Bamboni";
        String gender = "Male";
        String email = "boba@biba.com";
        String mobile = "88005553535";
        String dob = "05.05.2005";
        String subjects = "Maths";
        String hobbies = "Sports";
        String curAddress = "Zazhopinsk";

        page.setFirstname(firstName);
        page.setLastname(lastName);
        page.setEmail(email);
        page.setgenderInput();
        page.setMobile(mobile);
        page.setDateBirth(dob);
        page.setSubjects(subjects);
        page.setHobbies();
        page.setAddress(curAddress);
        page.submitForm();

        $(".table-responsive").shouldBe(visible);

        Map<String, String> results = page.getSubmissionResults();

        results.keySet().forEach(System.out::println);

        assertThat(results)
                .containsEntry("Student name", firstName + " " + lastName)
                .containsEntry("Student email", email)
                .containsEntry("Gender", gender)
                .containsEntry("Mobile", mobile)
                .containsEntry("Date of Birth", dob)
                .containsEntry("Subjects", subjects)
                .containsEntry("Hobbies", hobbies)
                .containsEntry("Address", curAddress);
    }
}