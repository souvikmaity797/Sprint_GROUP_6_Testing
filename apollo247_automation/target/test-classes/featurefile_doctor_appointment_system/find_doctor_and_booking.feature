   @apollo247
Feature: Apollo247 Core Functionalities
  As a logged-in user on Apollo247
  I want to search for doctors, apply filters, schedule appointments, and validate patient details
  So that I can manage my healthcare needs effectively

  Background:
    Given the user has opened the browser and navigated to the Apollo247 website
    And the user is logged in to the website
    And the user is on the Apollo247 home page

  # ─────────────────────────────────────────────
  # TC_Apollo247_01 – Doctor Search by Name
  # ─────────────────────────────────────────────
  @TC_Apollo247_01 @doctor_search @REQ_Apollo247_01
  Scenario: Search for a doctor by valid name
    When the user performs doctor search using "TC01"
    Then relevant doctors should be displayed
    And the searched doctor should be present in the results

  # ─────────────────────────────────────────────
  # TC_Apollo247_02 – Doctor Search by Specialty
  # ─────────────────────────────────────────────
  @TC_Apollo247_02 @doctor_search @REQ_Apollo247_01
  Scenario: Search for doctors by valid specialty
    When the user performs doctor search using "TC02"
    Then doctors related to the specialty should be displayed

  # ─────────────────────────────────────────────
  # TC_Apollo247_03 – Doctor Search with Invalid Input
  # ─────────────────────────────────────────────
  @TC_Apollo247_03 @doctor_search @REQ_Apollo247_01
  Scenario: Search for a doctor with an invalid name
    When the user performs doctor search using "TC03"
    Then a no result message should be displayed for "TC03"

  # ─────────────────────────────────────────────
  # TC_Apollo247_04 – Filter Doctors by Experience
  # ─────────────────────────────────────────────
  @TC_Apollo247_04 @doctor_filter @REQ_Apollo247_02
  Scenario: Filter doctors by experience range of 0-5 years
    Given the user has already searched for doctors using "TC02"
    When the user applies experience filter from 0 to 5 years
    Then only doctors within the selected experience range should be displayed

  # ─────────────────────────────────────────────
  # TC_Apollo247_10 – Navigate to Appointment Details Page
  # ─────────────────────────────────────────────
  @TC_Apollo247_10 @appointment @REQ_Apollo247_04
  Scenario: Verify navigation to Appointment Details page from doctor profile
    Given the user has searched and selected a doctor using "TC01"
    When the user proceeds to schedule an appointment
    Then the appointment details page should be displayed

  # ─────────────────────────────────────────────
  # TC_Apollo247_11 – Invalid Phone Number (Less Digits)
  # ─────────────────────────────────────────────
  @TC_Apollo247_11 @patient_details @REQ_Apollo247_05
  Scenario: Validate patient contact number with less than required digits
    When the user completes appointment process using "TC11"
    Then an appropriate validation alert should be displayed

  # ─────────────────────────────────────────────
  # TC_Apollo247_12 – Empty Contact Number
  # ─────────────────────────────────────────────
  @TC_Apollo247_12 @patient_details @REQ_Apollo247_05
  Scenario: Validate patient contact number when left empty
    When the user completes appointment process using "TC12"
    Then an appropriate validation alert should be displayed