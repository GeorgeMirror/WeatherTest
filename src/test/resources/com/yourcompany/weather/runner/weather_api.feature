Feature: Weather API tests with WireMock

  Background:
    Given WireMock stub is running

  Scenario Outline: Positive test for current weather by city compared to standard city
    When I request current weather for city "<city>" with expected data:
      | temp_c  | humidity | condition    |
      | <temp_c>| <humidity>| <condition>  |

    Then the response contains weather data close to standard city:
      | city         | temp_c | humidity | condition |
      | StandardCity | 20.0   | 50       | Sunny     |

    Examples:
      | city     | temp_c | humidity | condition    |
      | Moscow   | 19.0   | 48       | Sunny        |
      | London   | 15.0   | 55       | Partly cloudy|
      | Paris    | 21.0   | 50       | Sunny        |
      | New York | 23.0   | 60       | Cloudy       |

  Scenario Outline: Negative test for API errors
    When I request weather for city "<city>" with error scenario "<errorCode>"
    Then the response contains API error code "<errorCode>"

    Examples:
      | city     | errorCode |
      | Moscow   | 1006      | # No matching location found.
      | London   | 1008      | # API key not supplied.
      | Paris    | 2006      | # API request limit reached.
      | New York | 9999      | # Custom error for testing
