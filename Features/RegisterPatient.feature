Feature: Register Patient Feature
  I want to register the patient details, so that I can access the partient details in future.

  @RegisterPatient
  Scenario: Register patient with valid details
    Given the user Open Mrs Home Page
    When the user cliks on "Register a patient" tile
    And the user enters the Patient details with Name "Ram, Kumar", Gender "Male", DateOfBirth "01, January, 1990", Address "S R Nagar, Hyderabad, Telangana, India, 500038" and PhoneNumber "9876543210"
    Then patient details Name "Ram, Kumar", Gender "Male", DateOfBirth "01, January, 1990" and PhoneNumber "9876543210" must be entered properly
    When the user clicks confirm button
    Then the Patient details must be registered and Patient Name "Ram, Kumar" must be diplayed properly
    And PatientId created successfully

  @RegisterMultiplePatient
  Scenario Outline: Register multiple patient with valid details
    Given the user Open Mrs Home Page
    When the user cliks on "Register a patient" tile
    And the user enters the Patient details with Name "<Name>", Gender "<Gender>", DateOfBirth "<DateOfBirth>", Address "<Address>" and PhoneNumber "<PhoneNumber>"
    Then patient details Name "<Name>", Gender "<Gender>", DateOfBirth "<DateOfBirth>" and PhoneNumber "<PhoneNumber>" must be entered properly
    When the user clicks confirm button
    Then the Patient details must be registered and Patient Name "<Name>" must be diplayed properly
    And PatientId created successfully

    Examples: 
      | Name             | Gender | DateOfBirth        | Address                                     | PhoneNumber |
      | Ramesh Babu, H   | Male   | 26, December, 2000 | S R Nagar,Hyderabad,Telangana,India,500033  |  9876543210 |
      | Kishore Kumar, M | Male   | 10, November, 1998 | HitechCity,Hyderabad,Telangana,India,500033 |  9876543211 |
      | Kumar Raju, Ch   | Male   | 17, July, 2002     | Kukatpally,Hyderabad,Telangana,India,500033 |  9876543212 |