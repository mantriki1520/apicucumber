Feature: Global Search API

  @GlobalSearch @VCT-282 @VCT-420 @VCT-TC-271 @VCT-TC-509
  Scenario: Verify 200 response status code with response body with query params(query=test&top=2&skip=0) and  This will return the first 2 json objects of relative query search.
  Verify if the system correctly parses user search and derives intent

    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | top | skip |
      | test  | 2   | 0    |
    And I send a GET-API request to "/v1/globalSearch/search"
    Then I check response status code is "200"
    And I validate "EVENTS" field "2" json objects are fetching for GET Global Search API
    And I validate "GROUPS" field "2" json objects are fetching for GET Global Search API
    And I validate "USERS" field "2" json objects are fetching for GET Global Search API
    And I validate "JOBS" field "2" json objects are fetching for GET Global Search API
    And I validate "COURSES" field "2" json objects are fetching for GET Global Search API
    And I validate "ENTITIES" field "2" json objects are fetching for GET Global Search API


  @GlobalSearch @VCT-282 @VCT-420 @VCT-TC-272 @VCT-TC-510
  Scenario: Verify 200 response status code with response body with query params(query=test&top=1&skip=0) and This will return next 1 json objects (skipping the first 1) of relative query search.
  Verify if the system correctly parses user search and skips the top results based on count
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | top | skip |
      | vbc   | 1   | 0    |
    And I send a GET-API request to "/v1/globalSearch/search"
    And I check response status code is "200"
    And I validate response for Get Global Search API contains "1" Json Objects
    When I set Query Params For this API
      | query | top | skip |
      | vbc   | 1   | 1    |
    And I send a GET-API request to "/v1/globalSearch/search"
    And I check response status code is "200"
    Then I validate the response body for the correct data based on the skip and top query parameters


  @GlobalSearch @VCT-282 @VCT-TC-273
  Scenario: Verify 200 response status code with response body with query params(query=vbc&top=2&skip=3) and This will return next 2 json objects (skipping the first 3) of relative query search.
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | top | skip |
      | vbc   | 2   | 0    |
    And I send a GET-API request to "/v1/globalSearch/search"
    And I check response status code is "200"
    And I validate response for Get Global Search API contains "2" Json Objects
    When I set Query Params For this API
      | query | top | skip |
      | vbc   | 2   | 3    |
    And I send a GET-API request to "/v1/globalSearch/search"
    And I check response status code is "200"
    Then I validate the response body for the correct data based on the skip and top query parameters


  @GlobalSearch @VCT-282 @VCT-TC-274
  Scenario: Verify 200 response status code with expected response body with query params(query=test&top=0&skip=0) and This will return empty response body.
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | top | skip |
      | test  | 0   | 0    |
    And I send a GET-API request to "/v1/globalSearch/search"
    Then I check response status code is "200"
    And I validate empty response

  @GlobalSearch @VCT-282 @VCT-TC-275 # need to check
  Scenario: Verify 200 response status code with expected response body with query params(query=123&top=2&skip=0) and This will return first 2 json objects as response body.
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | top | skip |
      | 123   | 2   | 0    |
    And I send a GET-API request to "/v1/globalSearch/search"
    Then I check response status code is "200"
    And I validate response for Get Global Search API contains "2" Json Objects


  @GlobalSearch @VCT-282 @VCT-420 @VCT-TC-276 @VCT-TC-511
  Scenario: Verify 200 response status code with expected response body with query params(query=@&top=0&skip=0) and This will return empty response body.
  Verify if the system responds to invalid search query
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | top | skip |
      | @     | 0   | 0    |
    And I send a GET-API request to "/v1/globalSearch/search"
    Then I check response status code is "200"
    And I validate empty response

  @GlobalSearch @VCT-282 @VCT-TC-277
  Scenario: Verify 200 response status code with expected response body with query params as blank(query=&top=&skip=) and This will return all json objects present in db table
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | top | skip |
      |       |     |      |
    And I send a GET-API request to "/v1/globalSearch/search"
    Then I check response status code is "200"
    And I validate response

  @GlobalSearch @VCT-282 @VCT-TC-278
  Scenario: Verify 400 response status code with expected response msg with out query in query params(top=&skip=)
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | top | skip |
      |     |      |
    And I send a GET-API request to "/v1/globalSearch/search"
    Then I check response status code is "400"

  @GlobalSearch @VCT-282 @VCT-TC-279
  Scenario: Verify 400 response status code with expected response msg with out top in query params(query=&skip=)
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | skip |
      |       |      |
    And I send a GET-API request to "/v1/globalSearch/search"
    Then I check response status code is "400"

  @GlobalSearch @VCT-282 @VCT-TC-280
  Scenario: Verify 400 response status code with expected response msg with out skip in query params(query=&top=)
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | top |
      |       |     |
    And I send a GET-API request to "/v1/globalSearch/search"
    Then I check response status code is "400"

  @GlobalSearch @VCT-282 @VCT-TC-281
  Scenario: Verify 400 response status code with expected response msg with invalid query params(query=test&top=true&skip=1)
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | top  | skip |
      | test  | true | 1    |
    And I send a GET-API request to "/v1/globalSearch/search"
    Then I check response status code is "400"

  @GlobalSearch @VCT-282 @VCT-TC-282
  Scenario: Verify 400 response status code with expected response msg with invalid query params(query=test&top=2&skip=false)
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | top | skip  |
      | test  | 2   | false |
    And I send a GET-API request to "/v1/globalSearch/search"
    Then I check response status code is "400"


  @GlobalSearch @VCT-282 @VCT-TC-283
  Scenario: Verify 200 response status code with response body with query params(query=test&domainApp=EVENTS&top=2&skip=0) and This will return the first 2 json objects of relative query search.
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | domainApp | top | skip |
      | test  | EVENTS    | 2   | 0    |
    And I send a GET-API request to "/v1/globalSearch/searchApp"
    Then I check response status code is "200"
    And I validate "EVENTS" field "2" json objects are fetching for GET Global Search API


  @GlobalSearch @VCT-282 @VCT-TC-284
  Scenario: Verify 200 response status code with response body with query params(query=vbc&domainApp=GROUPS&top=2&skip=1) and This will return next 2 json objects (skipping the first 1) of relative query search.
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | domainApp | top | skip |
      | vbc   | GROUPS    | 2   | 0    |
    And I send a GET-API request to "/v1/globalSearch/searchApp"
    And I check response status code is "200"
    And I validate response for Get Global Search API contains "2" Json Objects
    And I set Query Params For this API
      | query | domainApp | top | skip |
      | vbc   | GROUPS    | 2   | 1    |
    And I send a GET-API request to "/v1/globalSearch/searchApp"
    Then I check response status code is "200"
    And I validate "GROUPS" field "2" json objects are fetching for GET Global Search API
    And I validate the response body for the correct data based on the skip and top query parameters


  @GlobalSearch @VCT-282 @VCT-TC-285
  Scenario: Verify 200 response status code with response body with query params(query=test&domainApp=USERS&top=2&skip=3) and This will return next 2 json objects (skipping the first 3) of relative query search.
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | domainApp | top | skip |
      | test  | USERS     | 2   | 0    |
    And I send a GET-API request to "/v1/globalSearch/searchApp"
    And I check response status code is "200"
    And I validate response for Get Global Search API contains "2" Json Objects
    And I set Query Params For this API
      | query | domainApp | top | skip |
      | test  | USERS     | 2   | 3    |
    And I send a GET-API request to "/v1/globalSearch/searchApp"
    Then I check response status code is "200"
    And I validate "USERS" field "2" json objects are fetching for GET Global Search API
    And I validate the response body for the correct data based on the skip and top query parameters


  @GlobalSearch @VCT-282 @VCT-TC-287
  Scenario: Verify 200 response status code with expected response body with query params(query=test&domainApp=0&top=0&skip=0) and This will return empty response body.
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | domainApp | top | skip |
      | test  | 0         | 0   | 0    |
    And I send a GET-API request to "/v1/globalSearch/searchApp"
    Then I check response status code is "200"
    And I validate empty response


  @GlobalSearch @VCT-282 @VCT-420 @VCT-TC-288 @VCT-TC-514
  Scenario: Verify 200 response status code with expected response body with query params(query=123&domainApp=345&top=2&skip=0) and This will return empty response body.
  Verify if the system correctly parses user search and derives intent with invalid domainApp Query
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | domainApp | top | skip |
      | 123   | 345       | 2   | 0    |
    And I send a GET-API request to "/v1/globalSearch/searchApp"
    Then I check response status code is "200"
    And I validate empty response

  @GlobalSearch @VCT-282 @VCT-420 @VCT-TC-289 @VCT-TC-516
  Scenario: Verify 200 response status code with expected response body with query params(query=@&domainApp=%23&top=2&skip=0) and This will return empty reponsne body.
  Verify if the system responds to invalid search query
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | domainApp | top | skip |
      | @     | %23       | 2   | 0    |
    And I send a GET-API request to "/v1/globalSearch/searchApp"
    Then I check response status code is "200"
    And I validate empty response


  @GlobalSearch @VCT-282 @VCT-420 @VCT-TC-290 @VCT-TC-517
  Scenario: Verify 200 response status code with expected response body with query params as blank(query=&tdomainApp=&top=&skip=) and This will return empty response body.
  Verify if the system responds to invalid empty space search query with top and skip value max.
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | domainApp | top | skip |
      |       |           |     |      |
    And I send a GET-API request to "/v1/globalSearch/searchApp"
    Then I check response status code is "200"
    And I validate empty response


  @GlobalSearch @VCT-282 @VCT-TC-291
  Scenario: Verify 400 response status code with expected response msg with out query in query params(domainApp=&top=&skip=).
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | domainApp | top | skip |
      |           |     |      |
    And I send a GET-API request to "/v1/globalSearch/searchApp"
    Then I check response status code is "400"


  @GlobalSearch @VCT-282 @VCT-TC-292
  Scenario: Verify 400 response status code with expected response msg with out domainApp in query params(query=&top=&skip=).
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | top | skip |
      |       |     |      |
    And I send a GET-API request to "/v1/globalSearch/searchApp"
    Then I check response status code is "400"


  @GlobalSearch @VCT-282 @VCT-TC-293
  Scenario: Verify 400 response status code with expected response msg with out top in query params(query=test&domainApp=&skip=2).
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | domainApp | skip |
      | test  |           | 2    |
    And I send a GET-API request to "/v1/globalSearch/searchApp"
    Then I check response status code is "400"


  @GlobalSearch @VCT-282 @VCT-TC-294
  Scenario: Verify 400 response status code with expected response msg with out skip in query params(query=test&domainApp=&top=1).
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | domainApp | top |
      | test  |           | 1   |
    And I send a GET-API request to "/v1/globalSearch/searchApp"
    Then I check response status code is "400"


  @GlobalSearch @VCT-282 @VCT-TC-295
  Scenario: Verify 400 response status code with expected response msg with invalid query params(query=test&domainApp=USERS&top=true&skip=0).
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | domainApp | top  | skip |
      | test  | USERS     | true | 0    |
    And I send a GET-API request to "/v1/globalSearch/searchApp"
    Then I check response status code is "400"


  @GlobalSearch @VCT-282 @VCT-TC-296
  Scenario: Verify 400 response status code with expected response msg with invalid query params(query=EVENTS&top=2&skip=false).
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | domainApp | top | skip  |
      | test  | EVENTS    | 2   | false |
    And I send a GET-API request to "/v1/globalSearch/searchApp"
    Then I check response status code is "400"


  @GlobalSearch @VCT-420 @VCT-TC-512
  Scenario: Verify if the system responds to invalid empty space search query with top and skip value max.
  Verify if the system responds to invalid search query
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | top | skip |
      |       | 12  | 12   |
    And I send a GET-API request to "/v1/globalSearch/search"
    Then I check response status code is "200"
    And I validate response


  @GlobalSearch @VCT-420 @VCT-TC-513
  Scenario: Verify if the system correctly parses user search and derives intent.
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | domainApp | top | skip |
      | test  | EVENTS    | 2   | 0    |
    And I send a GET-API request to "/v1/globalSearch/searchApp"
    Then I check response status code is "200"
    And I validate "EVENTS" field "2" json objects are fetching for GET Global Search API

    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | domainApp | top | skip |
      | test  | GROUPS    | 1   | 0    |
    And I send a GET-API request to "/v1/globalSearch/searchApp"
    Then I check response status code is "200"
    And I validate "GROUPS" field "1" json objects are fetching for GET Global Search API

    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | domainApp | top | skip |
      | test  | JOBS    | 2   | 0    |
    And I send a GET-API request to "/v1/globalSearch/searchApp"
    Then I check response status code is "200"
    And I validate "JOBS" field "2" json objects are fetching for GET Global Search API

    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | domainApp | top | skip |
      | test  | ENTITIES  | 1   | 0    |
    And I send a GET-API request to "/v1/globalSearch/searchApp"
    Then I check response status code is "200"
    And I validate "ENTITIES" field "1" json objects are fetching for GET Global Search API

    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | domainApp | top | skip |
      | test  | USERS   | 2   | 0    |
    And I send a GET-API request to "/v1/globalSearch/searchApp"
    Then I check response status code is "200"
    And I validate "USERS" field "2" json objects are fetching for GET Global Search API

    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | domainApp | top | skip |
      | test  | COURSES   | 1   | 0    |
    And I send a GET-API request to "/v1/globalSearch/searchApp"
    Then I check response status code is "200"
    And I validate "COURSES" field "1" json objects are fetching for GET Global Search API


  @GlobalSearch @VCT-420 @VCT-TC-515
  Scenario: Verify if the system correctly parses user search and skips the top results based on count
    Given I have navigated to "Global Search Services"
    When I set Query Params For this API
      | query | domainApp | top | skip |
      | vbc   | JOBS         | 2   | 0    |
    And I send a GET-API request to "/v1/globalSearch/searchApp"
    And I check response status code is "200"
    And I validate response for Get Global Search API contains "2" Json Objects
    And I set Query Params For this API
      | query | domainApp | top | skip |
      | vbc   | JOBS         | 2   | 1    |
    And I send a GET-API request to "/v1/globalSearch/searchApp"
    Then I check response status code is "200"
    And I validate "JOBS" field "2" json objects are fetching for GET Global Search API
    And I validate the response body for the correct data based on the skip and top query parameters



