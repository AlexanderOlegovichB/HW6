import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;


public class PracticeFormPage {

    private String BASE_URL = "https://demoqa.com";
    private SelenideElement firstnameInput = $("input#firstName");
    private SelenideElement lastnameInput = $("input#lastName");
    private SelenideElement emailInput = $("input[placeholder=\"name@example.com\"]");
    private SelenideElement genderInputMale = $("input#genterWrapper > div.col-md-9.col-sm-12 > div:nth-child(1) > label");
    private SelenideElement mobileInput = $("input.mr-sm-2.form-control[placeholder='Mobile Number']");
    private SelenideElement dateBirthInput = $("input#dateOfBirthInput");
    private SelenideElement subjectsInput = $(By.xpath("//*[@id=\"subjectsContainer\"]/div/div[1]"));
    private SelenideElement hobbiesInput = $("input[type='checkbox'][value='1']");
    private SelenideElement currentaddressInput = $("input#currentAddress");
    private SelenideElement submitButton =  $("button.btn.btn-primary[type='submit']");


    public void openPage(String value) {
        open(BASE_URL + value);
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

    void setgenderInput() {
        SelenideElement genderInput1 = genderInputMale;
        genderInput1.click();
    }

    void setMobile(String value) {
        mobileInput.setValue(value);
    }

    void setDateBirth(String value) {
        dateBirthInput.setValue(value);
    }

    void setSubjects(String value) {
        subjectsInput.setValue(value);
    }
    void setHobbies() {
        SelenideElement hobbiesInput1 = hobbiesInput;
        hobbiesInput1.click();
    }
    void setAddress(String value) {
        currentaddressInput.setValue(value);
    }

    void submitForm() {
        SelenideElement submitButton1 = submitButton;
        submitButton1.click();
    }

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