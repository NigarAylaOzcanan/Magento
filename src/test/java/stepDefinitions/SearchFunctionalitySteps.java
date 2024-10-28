package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.Parent;
import pages.AddingRemovingProductsPOM;
import pages.SearchFunctionalityPOM;

public class SearchFunctionalitySteps extends Parent {

    AddingRemovingProductsPOM us5 = new AddingRemovingProductsPOM();
    SearchFunctionalityPOM us7 = new SearchFunctionalityPOM();

    @And("Select Product and Search by Code")
    public void selectProductAndSearchByCode() {
        myClick(us5.productNameLink.get(randomGenerator(11)));
        mySendKeys(us7.searchBoxPlc, us7.productCodeText.getText());
        myClick(us7.searchBoxCl);
    }

    @Then("The user must be able to see the product")
    public void theUserMustBeAbleToSeeTheProduct() {
        Assert.assertTrue(us7.productText.isDisplayed(), "Searched product not found");

    }


    @And("Incorrect Product Code {string}")
    public void incorrectProductCode(String code) {
        mySendKeys(us7.searchBoxPlc, code);
        if (code.length() < 3) {
            myClick(us7.searchBoxCl);
            verifyContainsText(us7.noResultText, "length is 3");
        } else if (code.length() > 4) {
            myClick(us7.searchBoxCl);
            verifyContainsText(us7.noResultText, "no results.");
        }
    }
}
