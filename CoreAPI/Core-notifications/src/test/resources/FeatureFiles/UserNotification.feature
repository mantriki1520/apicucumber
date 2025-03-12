Feature: Verify User Notifications Services Endpoints


  @NotificationService @VCT-45 @VCT-TC-42 @VCT-TC-44 @VCT-TC-45 @VCT-TC-50
  Scenario: Verify Post Notifications Endpoints for sending notifications to user
  Verify Get API should fetch and list all the notifications for a given notification Id( "status": "SENT", "isDeleted": false)
  Verify PATCH API once the user has opened and read the notification, the status should change and get updated to Read(1)
  Verify after performing PATCH Operation/Update the status details READ(1)/SENT(0)then make sure it will reflect in get call as per expected

    Given I have navigated to "User Services"
    And I have Generated token Using Credentials "testkpvsavvas@gmail.com"
    And I have "Post User Profile" Request Type
    And I have "valid" Guid as userId
    And I create my "Valid" request for "Post User Profile"
    When I send POST Request to "/v1/user/{id}/profile" for User Services
    And I check response status code is "201" for "user services"

    Given I have navigated to "Notification Services"
    And I have "Post Event Notification" Request Type
    When I set userEmail field to "testkpvsavvas@gmail.com"
    And I set "valid" Guid as Id for post user Notification
    And I create my "Valid" request for "Post Event Notification"
    And I send POST Request to "/v1/notifications/internal/events/webhook"
    Then I check response status code is "200" for "notification services"
    And I validate "Event submitted successfully" response Message

#  validating Get user notificatons api

    When I set "userId" Query Params from the user profile response
    And I set Query Params For this API
      | status | isDeleted |
      | SENT   | false     |
    And I send a GET-API request to "/v1/usernotifications"
    Then I check response status code is "200" for "notification services"
    And I Validate expected "userId" present in response for User Notifications
    And I Validate "sourceDomain" value as "EVENT_BOOKING" in response for Get User Notifications
    And I Validate "status" value as "SENT" in response for Get User Notifications
    And I Validate "isDeleted" value as "false" in response for Get User Notifications

#  validating patch user notificatons api

    When I have "Update User Notification" Request Type
    And I set "valid" user notification id for user notification
    And I set "isDeleted" value as "false"
    And I set "status" value as "READ"
    And I create my "Valid" request for "Update User Notification"
    And I send PATCH Request to "/v1/usernotifications/{id}"
    Then I check response status code is "202" for "notification services"
    And I Validate expected "user notification Id" present in response for User Notifications
    And I Validate "status" value as "READ" in response
    And I Validate "isDeleted" value as "false" in response


#  validating Get user notificatons api after update

    When I set "userId" Query Params from the user profile response
    And I set Query Params For this API
      | status | isDeleted |
      | SENT   | false     |
    And I send a GET-API request to "/v1/usernotifications"
    Then I check response status code is "200" for "notification services"
    And I Validate expected "user notification Id" disappear in response for User Notifications


  @NotificationService @VCT-45 @VCT-TC-46 @VCT-TC-50
  Scenario: Verify PATCH API once the user has opened and read the notification, the status should change and get updated to Send(0)
  Verify after performing PATCH Operation/Update the status details READ(1)/SENT(0)then make sure it will reflect in get call as per expected

    When I have "Update User Notification" Request Type
    And I set "valid existing" user notification id for user notification
    And I set "isDeleted" value as "false"
    And I set "status" value as "SENT"
    And I create my "Valid" request for "Update User Notification"
    And I send PATCH Request to "/v1/usernotifications/{id}"
    Then I check response status code is "202" for "notification services"
    And I Validate expected "existing user notification Id" present in response for User Notifications
    And I Validate "status" value as "SENT" in response
    And I Validate "isDeleted" value as "false" in response

#  validating Get user notificatons api after update

    When I set "existing userId" Query Params from the user profile response
    And I set Query Params For this API
      | status | isDeleted |
      | SENT   | false     |
    And I send a GET-API request to "/v1/usernotifications"
    Then I check response status code is "200" for "notification services"
    And I Validate expected "existing userId" present in response for User Notifications
    And I Validate "sourceDomain" value as "EVENT_BOOKING" in response for Get User Notifications
    And I Validate "status" value as "SENT" in response for Get User Notifications
    And I Validate "isDeleted" value as "false" in response for Get User Notifications

    When I set "existing userId" Query Params from the user profile response
    And I set Query Params For this API
      | status | isDeleted |
      | READ      | false     |
    And I send a GET-API request to "/v1/usernotifications"
    Then I check response status code is "200" for "notification services"
    And I Validate expected "existing user notification Id" disappear in response for User Notifications



    When I have "Update User Notification" Request Type
    And I set "valid existing" user notification id for user notification
    And I set "isDeleted" value as "false"
    And I set "status" value as "READ"
    And I create my "Valid" request for "Update User Notification"
    And I send PATCH Request to "/v1/usernotifications/{id}"
    Then I check response status code is "202" for "notification services"
    And I Validate expected "existing user notification Id" present in response for User Notifications
    And I Validate "status" value as "READ" in response
    And I Validate "isDeleted" value as "false" in response

#  validating Get user notificatons api after update

    When I set "existing userId" Query Params from the user profile response
    And I set Query Params For this API
      | status | isDeleted |
      | READ   | false     |
    And I send a GET-API request to "/v1/usernotifications"
    Then I check response status code is "200" for "notification services"
    And I Validate expected "existing userId" present in response for User Notifications
    And I Validate "sourceDomain" value as "EVENT_BOOKING" in response for Get User Notifications
    And I Validate "status" value as "READ" in response for Get User Notifications
    And I Validate "isDeleted" value as "false" in response for Get User Notifications

    When I set "existing userId" Query Params from the user profile response
    And I set Query Params For this API
      | status | isDeleted |
      | SENT      | false     |
    And I send a GET-API request to "/v1/usernotifications"
    Then I check response status code is "200" for "notification services"
    And I Validate expected "existing user notification Id" disappear in response for User Notifications


  @NotificationService @VCT-45 @VCT-TC-47 @VCT-TC-48 @VCT-TC-51
  Scenario: Verify PATCH API once the user has opened and read the notification, the status should change and get updated to "isDeleted": true
  Verify PATCH API once the user has opened and read the notification, the status should change and get updated to "isDeleted": fasle
  Verify after performing PATCH Operation/Update the isDeleted: TRUE/FALSE details then make sure it will reflect in get call as per expected

    When I have "Update User Notification" Request Type
    And I set "valid existing" user notification id for user notification
    And I set "isDeleted" value as "true"
    And I set "status" value as "READ"
    And I create my "Valid" request for "Update User Notification"
    And I send PATCH Request to "/v1/usernotifications/{id}"
    Then I check response status code is "202" for "notification services"
    And I Validate expected "existing user notification Id" present in response for User Notifications
    And I Validate "status" value as "READ" in response
    And I Validate "isDeleted" value as "true" in response

#  validating Get user notificatons api after update

    When I set "existing userId" Query Params from the user profile response
    And I set Query Params For this API
      | status | isDeleted |
      | READ   | true      |
    And I send a GET-API request to "/v1/usernotifications"
    Then I check response status code is "200" for "notification services"
    And I Validate expected "existing userId" present in response for User Notifications
    And I Validate "sourceDomain" value as "EVENT_BOOKING" in response for Get User Notifications
    And I Validate "status" value as "READ" in response for Get User Notifications
    And I Validate "isDeleted" value as "true" in response for Get User Notifications

    When I set "existing userId" Query Params from the user profile response
    And I set Query Params For this API
      | status | isDeleted |
      | READ   | false     |
    And I send a GET-API request to "/v1/usernotifications"
    Then I check response status code is "200" for "notification services"
    And I Validate expected "existing user notification Id" disappear in response for User Notifications


    When I have "Update User Notification" Request Type
    And I set "valid existing" user notification id for user notification
    And I set "isDeleted" value as "false"
    And I set "status" value as "READ"
    And I create my "Valid" request for "Update User Notification"
    And I send PATCH Request to "/v1/usernotifications/{id}"
    Then I check response status code is "202" for "notification services"
    And I Validate expected "existing user notification Id" present in response for User Notifications
    And I Validate "status" value as "READ" in response
    And I Validate "isDeleted" value as "false" in response

#  validating Get user notificatons api after update


    When I set "existing userId" Query Params from the user profile response
    And I set Query Params For this API
      | status | isDeleted |
      | READ   | false     |
    And I send a GET-API request to "/v1/usernotifications"
    Then I check response status code is "200" for "notification services"
    And I Validate expected "existing userId" present in response for User Notifications
    And I Validate "sourceDomain" value as "EVENT_BOOKING" in response for Get User Notifications
    And I Validate "status" value as "READ" in response for Get User Notifications
    And I Validate "isDeleted" value as "false" in response for Get User Notifications

    When I set "existing userId" Query Params from the user profile response
    And I set Query Params For this API
      | status | isDeleted |
      | READ   | true         |
    And I send a GET-API request to "/v1/usernotifications"
    Then I check response status code is "200" for "notification services"
    And I Validate expected "existing user notification Id" disappear in response for User Notifications


  @NotificationService @VCT-45 @VCT-TC-49
  Scenario: Verify DELETE API If the user deletes the notification, it should not appear in the notifications list i.e "isDeleted": true


    When I set "valid existing" user notification id for user notification
    And I send DELETE Request to "/v1/usernotifications/{id}"
    Then I check response status code is "202" for "notification services"
    And I Validate expected "existing user notification Id" present in response for User Notifications
    And I Validate "status" value as "READ" in response
    And I Validate "isDeleted" value as "true" in response

#  validating Get user notificatons api after update

    When I set "existing userId" Query Params from the user profile response
    And I set Query Params For this API
      | status | isDeleted |
      | READ   | false         |
    And I send a GET-API request to "/v1/usernotifications"
    Then I check response status code is "200" for "notification services"
    And I Validate expected "existing user notification Id" disappear in response for User Notifications

    When I set "existing userId" Query Params from the user profile response
    And I set Query Params For this API
      | status | isDeleted |
      | READ   | true      |
    And I send a GET-API request to "/v1/usernotifications"
    Then I check response status code is "200" for "notification services"
    And I Validate expected "existing userId" present in response for User Notifications
    And I Validate "sourceDomain" value as "EVENT_BOOKING" in response for Get User Notifications
    And I Validate "status" value as "READ" in response for Get User Notifications
    And I Validate "isDeleted" value as "true" in response for Get User Notifications


  @NotificationService @VCT-510 @VCT-TC-487 @VCT-TC-488 @VCT-TC-489 @VCT-TC-490
  Scenario: Verify success response and boolean flag value when new notification arrived
  Verify success response and count when new notification arrived
  Verify success response and boolean flag value when all notification read
  Verify success response and count when all notification read

    # Creating new UserId

    Given I have navigated to "User Services"
    And I have Generated token Using Credentials "testkpvsavvas@gmail.com"
    And I have "Post User Profile" Request Type
    And I have "valid" Guid as userId
    And I create my "Valid" request for "Post User Profile"
    When I send POST Request to "/v1/user/{id}/profile" for User Services
    And I check response status code is "201" for "user services"

    # Sending new notification to new user

    Given I have navigated to "Notification Services"
    And I have "Post Event Notification" Request Type
    When I set userEmail field to "testkpvsavvas@gmail.com"
    And I set "valid" Guid as Id for post user Notification
    And I create my "Valid" request for "Post Event Notification"
    And I send POST Request to "/v1/notifications/internal/events/webhook"
    Then I check response status code is "200" for "notification services"
    And I validate "Event submitted successfully" response Message

    # validating red dot status  as true for the user when new notification came

    Given I set "userId" from the user profile response
    When I send a GET-API request to "/v1/usernotifications/notifications/{userId}/has-recent"
    Then I check response status code is "200" for "notification services"
    And I validate "true" response Message

    # validating count of the notifications(user create notification and event notification total 2 notification) for this user

    Given I set "userId" from the user profile response
    When I send a GET-API request to "/v1/usernotifications/notifications/{userId}/recent-count"
    Then I check response status code is "200" for "notification services"
    And I validate "2" response Message

    # doing read the notification by the user

    When I set "userId" Query Params from the user profile response
    And I set Query Params For this API
      | status | isDeleted |
      | READ   | true      |
    And I send a GET-API request to "/v1/usernotifications"
    Then I check response status code is "200" for "notification services"

    # validating red dot status as false for the user when new notification view by the user

    Given I set "userId" from the user profile response
    When I send a GET-API request to "/v1/usernotifications/notifications/{userId}/has-recent"
    Then I check response status code is "200" for "notification services"
    And I validate "false" response Message

    # validating count of the notifications for this user

    Given I set "userId" from the user profile response
    When I send a GET-API request to "/v1/usernotifications/notifications/{userId}/recent-count"
    Then I check response status code is "200" for "notification services"
    And I validate "0" response Message












