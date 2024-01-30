Feature: Login Mitra

  @Penjaminan @PositiveCase
  Scenario: As a mitra can be login
    Given Login Penjaminan with valid json
    When Send request post login mitra
    Then Response body should be: "Success login." and get token
    And Status code response should be 200
    And Validate json schema login as an mitra


  @Penjaminan @NegativeCase
  Scenario Outline: As a mitra can't login if credential account false
    Given Login akun with "<username>" and "<password>"
    When Send request post login mitra
    Then Response body message should be as status "null" as message "Error, Password dan email tidak sama." as data "null"
    And Status code should be 500
    And Validate json schema login failed
    Examples:
      | username            | password  |
      | adaewe134           | jamkrindo |
      | bpd_sulselbar_pusat | isi       |
      | bank_dki2            | jamkrindo |
      | bpd_sulselbar_pusat | bankdki   |

  @Penjaminan @NegativeCase
  Scenario Outline: As a mitra can't login if empty username or password
    Given Login akun with "<username>" and "<password>"
    When Send request post login mitra
    Then Response body message should be as status "false" as message "<expectedMessage>" as data "null"
    And Status code should be 422
    And Validate json schema login failed
    Examples:
      | username            | password  | expectedMessage       |
      |                     | jamkrindo | username wajib diisi. |
      | bpd_sulselbar_pusat |           | password wajib diisi. |