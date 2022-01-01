Scenario:  Card has been disabled

Given the card is disabled
When the account holder requests 20
Then the ATM should retain the card
