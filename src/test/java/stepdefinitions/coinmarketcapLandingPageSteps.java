package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.coinmarketcapLandingPage;
import factory.DriverFactory;

import org.junit.Assert;

public class coinmarketcapLandingPageSteps {

    private coinmarketcapLandingPage coinmarketcapLandingPage = new coinmarketcapLandingPage(DriverFactory.getDriver());
    private static String loginPageTitle;
    private static String homePageTitle;

    @Given("User is on the home page")
    public void User_is_on_the_login_page() {
        System.out.println("User navigated to the login page");
    }

    @And("User observe the title of the page")
    public void user_observe_the_title_of_the_page() {
        loginPageTitle = coinmarketcapLandingPage.getLoginPageTitle();
        System.out.println("Login Page title is: " + loginPageTitle);
    }

    @And("Home page title should display {string}")
    public void home_page_title_should_display(String expectedTitle) {
        Assert.assertTrue(loginPageTitle.contains(expectedTitle));
    }

    @When("User select Show Rows dropdown value as 100")
    public void user_select_show_row_value() throws InterruptedException {
        coinmarketcapLandingPage.selectShowRowValue();
    }

    @And("User clicks on Filters button and apply MarketCap as $1B - $10B and 'Price'as $101 - $1000")
    public void user_click_on_Filter() throws InterruptedException {
        coinmarketcapLandingPage.applyFilter();
    }

    @Then("Validate the filtered out record should be inline with the filtered applied")
    public void validate_Filter_Functioning() throws InterruptedException {
        coinmarketcapLandingPage.validateFilter();
    }

    @When("Verify that {string} rows are displayed")
    public void verify_rows_displayed(String noOfRows) throws InterruptedException{
        coinmarketcapLandingPage.validateRows(noOfRows);
    }

    @Then("Home page should display")
    public void home_page_should_display() {
        homePageTitle = coinmarketcapLandingPage.getHomePageTitle();
    }

    @Then("Home page title should be {string}")
    public void home_page_title_should_be(String expectedHPTitle) {
        Assert.assertTrue(homePageTitle.contains(expectedHPTitle));
    }

}
