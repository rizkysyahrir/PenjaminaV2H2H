Feature: Post Penjaminan Baru

  @Penjaminan @NegativeCase
  Scenario: Post Penjaminan Baru failed if token not found
    Given Post penjaminan baru
    When Send request Penjaminan Baru
    Then Response body success and shown message "Success create Penjaminan Baru"
    Then Status code response should be 201
    And Validate json schema post Penjaminan Baru fail

  @Penjaminan @NegativeCase @Validation
  Scenario Outline: Post permohonan baru empty validation body
    Given Post permohonan baru Mikro Sumut
    When Send request Penjaminan Baru
    Then Response body success "<success>" and message "<message>"
    And Status code response should be 01
  Examples:
  |success|message|
  |  false     | cif(cif) tidak boleh kosong, mohon cek ulang data permohonan pada object : list_debitur[0].cif      |
  #@Penjaminan @NegativeCase
  #Scenario: Post Penjaminan Baru failed if token expired
  #Given Post

