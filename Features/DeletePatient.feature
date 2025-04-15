@DeletePatient
Feature: Delete Patient reacord feature
  As a user I want to delete patient record

  @DeleteMultiplePatients
  Scenario Outline: Delete Multiple Patients record
    Given the user Open Mrs Home Page
    When the user cliks on "Find Patient Record" tile
    And the user searches Patient record with Patien Name "<Name>"
    And the user clicks search results first record
    And the user delete the Patient record with reason "<Reason>"
    And the user searches Patient record with Patien Name "<Name>"
    Then the No matching records messgae must be displayed

    Examples: 
      | Name            | Reason |
      | Ramesh Babu H   | Other1 |
      | Kishore Kumar M | Other2 |
      | Kumar Raju Ch   | Other3 |