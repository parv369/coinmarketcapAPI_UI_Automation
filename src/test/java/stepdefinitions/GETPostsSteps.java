package stepdefinitions;

//import cucumber.api.java.en.Given;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.hu.Ha;
import io.restassured.response.Response;
import org.json.simple.parser.ParseException;
import utilities.RestAssuredExtension;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class GETPostsSteps {


    public static Response response;

    public static Map<String,Long> currencyIds;
    private RestAssuredExtension restAssuredExtension = new RestAssuredExtension();

    @Given("I invoke the endpoint {string} to get the bitcoin IDs")
    public void i_perform_get_operation_for(String url) throws URISyntaxException {
        response = restAssuredExtension.GetIDs(url,null);
    }

    @Given("I invoke the Ethereum technical documentation website endpoint {string}")
    public void Ethereum(String url, DataTable data) throws URISyntaxException {
        Map<String, String> quaryParams = new HashMap<String, String>();
        quaryParams.put(data.cell(0,0),data.cell(0,1));
        response = restAssuredExtension.GetIDs(url,quaryParams);
    }

    @And("I should get the success response status code {string} for the endpoint")
    public void apiResponseStatus(String status) throws URISyntaxException, ParseException {
        restAssuredExtension.apiStatusCode(response, status);
    }

    @And("I should get the logo URL as {string}")
    public void logoURL(String url) throws URISyntaxException, ParseException {
        restAssuredExtension.validateLogoURL(response, url);
    }

    @And("I should get the technical_doc Url as {string}")
    public void technicalDocURL(String url) throws URISyntaxException, ParseException {
        restAssuredExtension.validateTechnicalDocURL(response, url);
    }

    @And("I should get the symbol as {string}")
    public void validateSymbol(String symbol) throws URISyntaxException, ParseException {
        restAssuredExtension.validateSymbol(response, symbol);
    }

    @And("I should get the date_added as {string}")
    public void validateDate_added(String date_added) throws URISyntaxException, ParseException {
        restAssuredExtension.validateDate_added(response, date_added);
    }

    @And("I should get the mineable tag as {string}")
    public void validateTag(String tag) throws URISyntaxException, ParseException {
        restAssuredExtension.validateTag(response, tag);
    }

    @And("Validate and display all the currencies having mineable tag associated")
    public void filterCurrenciesWithMineableTag() throws URISyntaxException, ParseException {
        restAssuredExtension.filterCurrenciesWithMineableTag(response);
    }


    @Given("validate api should return the required data")
    public void validateRequredData() throws URISyntaxException, ParseException {
        restAssuredExtension.validateRequredData(response);
    }

    @Then("I should get the list of IDs for bitcoin and tether and Ethereum currencies")
    public void i_perform_get_operation_for() throws URISyntaxException, ParseException {
         currencyIds = restAssuredExtension.responseAssertion(response);
    }


    @Given("currency conversion should have happend")
    public void validate_currency_conversion() throws URISyntaxException, ParseException {
        restAssuredExtension.responseCurrencyAssertion(response);
    }

    @When("I invoke the endpoint {string} to convert the currencies")
    public void hit_the_currency_conversion_endpoint(String url) throws URISyntaxException, ParseException {
        response = restAssuredExtension.GetConvertedCurrencies(url, currencyIds);
    }

    @Then("I should get the list of currencies with the currencies converted value")
    public void displayConvertedCurriencies() throws URISyntaxException, ParseException {
         restAssuredExtension.displayConvertedCurriencies();
    }

}
