package Domain;

import Exceptions.CardRetainedException;
import Exceptions.InsufficientFundsException;

import java.math.BigDecimal;

public class ATM {
    private BigDecimal moneyLeft;

    public BigDecimal withdraw(Card card, BigDecimal amount) {
        if (card.isValid()) {
            if (moneyLeft.compareTo(amount) >= 0) {
                moneyLeft = moneyLeft.subtract(amount);
                return amount;
            } else {
                throw new InsufficientFundsException();
            }
        } else {
            throw new CardRetainedException();
        }
    }

}
