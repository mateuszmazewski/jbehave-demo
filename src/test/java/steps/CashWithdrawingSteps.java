package steps;

import Domain.ATM;
import Domain.Account;
import Domain.Card;
import Exceptions.CardRetainedException;
import Exceptions.InsufficientFundsException;
import org.jbehave.core.annotations.*;
import org.junit.Assert;

import java.math.BigDecimal;

//@Component
public class CashWithdrawingSteps { // Look, Ma', I'm a POJO!

    private Account account;
    private ATM atm;
    private Card card;
    private BigDecimal dispense;
    private Throwable throwable;

    /**
     * Callback method triggered before each scenario.
     */
    @BeforeScenario
    public void beforeScenario() {
        this.card = null;
        this.account = null;
        this.atm = null;
        this.dispense = null;
        this.throwable = null;
    }

    @Given("the card is disabled")
    public void givenCardIsDisabled() {
        card = new Card();
        card.setValid(false);

        atm = new ATM();
    }

    /**
     * @param amount the amount of requested money
     */
    @When("the account holder requests $amount")
    public void whenAccountHolderRequestsMoney(@Named("amount") BigDecimal amount) {
        try {
            dispense = atm.withdraw(card, amount);
        } catch (CardRetainedException exception) {
            throwable = exception;
        } catch (InsufficientFundsException exception) {
            throwable = exception;
        }
    }

    @Then("the ATM should retain the card")
    public void thenATMShouldRetainCard() {
        Assert.assertNull(dispense);
        Assert.assertTrue(throwable instanceof CardRetainedException);
    }

}
