import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class PracticeFormPage {

    /**
     * Вот тут я постарался разными способами (конечно с пояснениями от ии) добраться до разных элементов
     */
    private String BASE_URL = "/automation-practice-form";
    private SelenideElement firstnameInput = $("input#firstName");
    private SelenideElement lastnameInput = $("input#lastName");
    private SelenideElement emailInput = $("input[placeholder=\"name@example.com\"]");
    private SelenideElement genderInputMale = $(By.xpath("//*[@id=\"genterWrapper\"]/div[2]/div[1]"));
    private SelenideElement genderInputFemale = $(By.xpath("//*[@id=\"genterWrapper\"]/div[2]/div[2]"));
    private SelenideElement genderInputOther = $(By.xpath("//*[@id=\"genterWrapper\"]/div[2]/div[3]"));
    private SelenideElement mobileInput = $("input.mr-sm-2.form-control[placeholder='Mobile Number']");
    private SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    private SelenideElement dateOfBirthYear = $(".react-datepicker__year-select");
    private SelenideElement dateOfBirthMonth = $(".react-datepicker__month-select");
    private ElementsCollection dateOfBirthDay = $$(".react-datepicker__day");
    private SelenideElement subjectsInput = $("#subjectsInput");
    private SelenideElement hobbiesSports = $("#hobbies-checkbox-1");
    private SelenideElement hobbiesReading = $("#hobbies-checkbox-2");
    private SelenideElement hobbiesMusic = $("#hobbies-checkbox-3");
    private SelenideElement tableResponsive = $(".table-responsive");
    private SelenideElement currentaddressInput = $("#currentAddress");
    private SelenideElement submitButton = $("#submit");

    /**
     * Тут ниже идут мои методы (прости господи) которыми будем работать с элементамии
     * @param value это значения которые будут принимать методы для работы с элементами
     */

    public void openPage() {
        open(BASE_URL);
    }

    void setFirstname(String value) {
        firstnameInput.setValue(value);
    }

    void setLastname(String value) {
        lastnameInput.setValue(value);
    }

    void setEmail(String value) {
        emailInput.setValue(value);
    }

/*  Старый метод работы с гендером
    void setgenderInput() {
        SelenideElement genderInput1 = genderInputMale;
        genderInput1.click();
    }*/

    // Новый метод через Свитч для динамическйо работы с гендером
    public void setGender(String gender) {
        SelenideElement genderId = switch (gender) {
            case "Male" -> genderInputMale;
            case "Female" -> genderInputFemale;
            case "Other" -> genderInputOther;
            default -> throw new IllegalArgumentException("Unknown gender: " + gender);
        };
        genderId.click();
    }

    void setMobile(String value) {
        mobileInput.setValue(value);
    }

/*    Старый вариант попытки вбить дату
    void setDateofBirth(String value) {
        dateBirthInput.click();
        dateBirthInput.clear();
        dateBirthInput.click();
        dateBirthInput.setValue(value);
        dateBirthInput.pressEnter();
    }*/

    // Новый вариант вбить дату с использованием селектОпшн
    public void setDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();
        dateOfBirthYear.selectOption(year);
        dateOfBirthMonth.selectOption(month);
        dateOfBirthDay.findBy(text(day)).click();
    }

    void setSubjects(String value) {
        subjectsInput.click();
        subjectsInput.setValue(value);
        subjectsInput.pressEnter();
    }

    void tableVisible () {
        tableResponsive.shouldBe(visible);
    }

/*  Старый вариант работы с хобби
    void setHobbies() {
        SelenideElement hobbiesInput1 = hobbiesInput;
        hobbiesInput1.click();
    }*/
    // Новый вариант работы с хобби через Свитч для динамического выбора НЕСКОЛЬКИХ значений
    public void setHobbies(List<String> hobbies) {
        for (String hobby : hobbies) {
            SelenideElement element = switch (hobby) {
                case "Sports" -> hobbiesSports;
                case "Reading" -> hobbiesReading;
                case "Music" -> hobbiesMusic;
                default -> throw new IllegalArgumentException("Unknown hobby: " + hobby);
            };
            element.parent().click();
        }
    }

    void setAddress(String value) {
        currentaddressInput.setValue(value);
    }

    void submitForm() {
        SelenideElement submitButton1 = submitButton;
        submitButton1.scrollTo();
        submitButton1.click();
    }

    // Тут описываем метод сбора в коллекцию значений из финальной формы как в теории модуля
    ElementsCollection rows = $$("div.table-responsive tbody tr");

    public Map<String, String> getSubmissionResults() {
        Map<String, String> submissionResults = new HashMap<>();

        for (SelenideElement row : rows) {
            SelenideElement label = row.$("td", 0);
            SelenideElement value = row.$("td", 1);
            submissionResults.put(label.text(), value.text());
        }
        return submissionResults;
    }
}