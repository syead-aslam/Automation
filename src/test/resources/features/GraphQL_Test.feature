Feature: Validating GraphQL API's
  @GraphQL @Api
  Scenario Outline: Verify GraphQL API Get Call
    Given Get Character Details with <CharacterID>  <EpisodeID>
    When user do graphQL get calls
    Then verify name in response "<name>"

    Examples:
      | CharacterID | EpisodeID | name  |
      | 14          | 16        | Jenin |

  @GraphQL @GraphQL_Mut @Api
  Scenario Outline: Verify GraphQL API Post Call
    Given Character Name with "<CharacterName>"
    When user do a graph post call
    Then verify status code is 200

    Examples:
      | CharacterName |
      | Mogamboo      |