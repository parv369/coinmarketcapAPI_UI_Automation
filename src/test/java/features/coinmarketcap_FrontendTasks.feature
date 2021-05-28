@coinmarketcapFeatureTest
Feature: This feature file contains coinmarketcap website functional test scenarios

  Background:
    Given User is on the home page

   @coinmarketcap
  Scenario: Validate rows filter functionality
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