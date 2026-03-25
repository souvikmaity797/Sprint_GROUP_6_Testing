@apollo247
Feature: Apollo247 Core Functionalities
  As a logged-in user on Apollo247
  I want to be able to search for doctors, schedule appointments, update patient details, and make payments
  So that I can manage my healthcare needs effectively

  Background:
    Given the user has opened the browser and navigated to the Apollo247 website
    And the user is logged in to the website

  # ─────────────────────────────────────────────
  # TC_Apollo247_01 – Doctor Search by Name
  # ─────────────────────────────────────────────
  @TC_Apollo247_01 @doctor_search @REQ_Apollo247_01
  Scenario: Search for a doctor by valid name
   Given the user is on the Apollo247 home page
  When the user clicks on "Find Doctor"
   And the user enters "Ramna Banerjee" in the search bar
  Then a list of doctors should appear
   And "Ramna Banerjee" should be part of that list

  # ─────────────────────────────────────────────
  # TC_Apollo247_02 – Doctor Search by Specialty
  # ─────────────────────────────────────────────
 # @TC_Apollo247_02 @doctor_search @REQ_Apollo247_01
  #Scenario: Search for doctors by valid specialty
   # Given the user is on the Apollo247 home page
    #When the user clicks on "Find Doctor"
    #And the user enters "Gynaecology" in the search bar
    #Then a list of doctors and specialties related to gynaecology should appear

  # ─────────────────────────────────────────────
  # TC_Apollo247_03 – Doctor Search with Invalid Input
  # ─────────────────────────────────────────────
  @TC_Apollo247_03 @doctor_search @REQ_Apollo247_01
  Scenario: Search for a doctor with an invalid name
    Given the user is on the Apollo247 home page
    When the user clicks on "Find Doctor"
    And the user enters "xyz" in the search bar
    Then a "No result found" message should appear

  # ─────────────────────────────────────────────
  # TC_Apollo247_04 – Filter Doctors by Experience
  # ─────────────────────────────────────────────
  #@TC_Apollo247_04 @doctor_filter @REQ_Apollo247_02
  #Scenario: Filter doctors by experience range of 0-5 years
  #  Given the user is on the Apollo247 home page
   # And the user has searched for a specialty on the "Find Doctors" page
   # And the user has clicked on the required specialty from the list
   # When the user selects the "0-5" option under the "Experience" filter section
   # Then only doctors with 0 to 5 years of experience should be displayed

  # ─────────────────────────────────────────────
  # TC_Apollo247_11 – Invalid Phone Number (Less Digits)
  # ─────────────────────────────────────────────
  #@TC_Apollo247_11 @patient_details @REQ_Apollo247_05
  #Scenario: Validate patient contact number with less than required digits
   # Given the user is on the Apollo247 home page
    #And the user has searched for a doctor and scheduled an appointment
    #And the user is on the "Appointment Details" page
    #When the user navigates to the patient's contact number field
    #And the user enters "1234567" in the contact number field
    #And the user clicks on the "Pay & Confirm" button
    #Then an "invalid mobile number" alert should be displayed

  # ─────────────────────────────────────────────
  # TC_Apollo247_12 – Empty Contact Number
  # ─────────────────────────────────────────────
  #@TC_Apollo247_12 @patient_details @REQ_Apollo247_05
  #Scenario: Validate patient contact number when left empty
   # Given the user is on the Apollo247 home page
   # And the user has searched for a doctor and scheduled an appointment
   # And the user is on the "Appointment Details" page
    #When the user navigates to the patient's contact number field
    #And the user clears the existing contact number
    #And the user clicks on the "Pay & Confirm" button
    #Then an "invalid mobile number" alert should be displayed

  # ─────────────────────────────────────────────
  # TC_Apollo247_17 – Credit/Debit Card Validation with Valid Details
  # ─────────────────────────────────────────────
  #@TC_Apollo247_17 @payment @REQ_Apollo247_07
  #Scenario: Verify payment option is enabled with valid credit/debit card details
   # Given the user is on the Apollo247 home page
   # And the user has searched for a doctor and scheduled an appointment
   # And the user is on the "Payment Options" page
   # When the user selects the "Credit/Debit Cards" payment option
   # And the user enters "4111 1111 1111 1111" as the card number
   # And the user enters "12/26" as the expiry date
   # And the user enters "123" as the CVV
   # Then the payment option should be enabled
