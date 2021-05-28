# BDD-Java-Framework

## Author: Parvez Akhtar
BDD Automation framework for Web_UI and API Testing

##Steps to Use this Framework: 

1. Install JDK & Maven
2. Set Java and Maven home
3. Clone the repository
5. Run "My TestRunner" file OR Execute "mvn install" command in terminal/cmd
*Example*

```gherkin
   Feature: coinmarketcap APIs feature testing
	Verify different GET operations on coinmarketcap back-end api

  @currencyConversion_BackendTask1
  Scenario: Convert the bitcoin BTC usd tether USDT and Ethereum ETH currencies to Bolivian Boliviano
    Given I invoke the endpoint "/cryptocurrency/map" to get the bitcoin IDs
    Then I should get the success response status code "200" for the endpoint
    Then I should get the list of IDs for bitcoin and tether and Ethereum currencies
    When I invoke the endpoint "/tools/price-conversion" to convert the currencies
    Then I should get the list of currencies with the currencies converted value

  @RetrieveTechnicalDoc_BackendTask2
  Scenario: Retrieve TechnicalDoc for Ethereum website
    Given I invoke the Ethereum technical documentation website endpoint "/cryptocurrency/info"
      |id|1027|
    Then I should get the success response status code "200" for the endpoint
    And I should get the logo URL as "https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png"
    And I should get the technical_doc Url as "https://github.com/ethereum/wiki/wiki/White-Paper"
    And I should get the symbol as "ETH"
    And I should get the date_added as "2015-08-07T00:00:00.000Z"
    And I should get the mineable tag as "mineable"

  @RetrieveCurrencies_BackendTask3
  Scenario: Retrieve first 10 Currencies and perform tag validation
    Given I invoke the Ethereum technical documentation website endpoint "/cryptocurrency/info"
      |id|1,2,3,4,5,6,7,8,9,10|
    Then I should get the success response status code "200" for the endpoint
    And Validate and display all the currencies having mineable tag associated
	
	#####
   
 Feature: This feature file contains coinmarketcap website functional test scenarios

  Background:
    Given User is on the home page

   @coinmarketcap
  Scenario: As a user I want to validate login Page title
    And User observe the title of the page
    And Home page title should display "Cryptocurrency Prices, Charts And Market Capitalizations | CoinMarketCap"
    When User select Show Rows dropdown value as 100
    Then Verify that "100" rows are displayed

   @coinmarketcap
  Scenario: Validate filter functionality
    And User observe the title of the page
    And Home page title should display "Cryptocurrency Prices, Charts And Market Capitalizations | CoinMarketCap"
    And User clicks on Filters button and apply MarketCap as $1B - $10B and 'Price'as $101 - $1000
    Then Validate the filtered out record should be inline with the filtered applied	
	
```