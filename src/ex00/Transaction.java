package ex00;

import java.util.UUID;

public class Transaction {
    public enum Type {
        DEBT, CREDIT;

        Type antonym() {
            return (this == DEBT) ? CREDIT : DEBT;
        }

        int senderAmount(int amount) {
            return (this == DEBT) ? amount : -amount;
        }

        int recepientAmount(int amount) {
            return (this == DEBT) ? -amount : amount;
        }
    }

    private static final String toStringFormat = "Transaction %s {%n" +
            "   Recepient id: %d;%n" +
            "   Sender id: %d;%n" +
            "   Transaction type: %s;%n" +
            "   Transaction amount: %d;%n" +
            "}";

    private static final String negAmountMsgFormat = "Transaction %s" +
        "is held with negative amount!";

    private UUID id;
    private User recepient;
    private User sender;
    private Type type;
    private int amount;

    public Transaction(UUID id,
            User recepient,
            User sender,
            Type transactionType,
            int amount) {
        if (amount < 0) {
            System.err.printf(negAmountMsgFormat, id);
            System.exit(-1);
        }
        this.id = id;
        this.recepient = recepient;
        this.sender = sender;
        this.type = transactionType;
        this.amount = amount;
    }

    public void perform() {
        sender.changeBalance(type.senderAmount(amount));
        recepient.changeBalance(type.recepientAmount(amount));
    }

    public String toString() {
        return String.format(toStringFormat,
                             id,
                             recepient.getID(),
                             sender.getID(),
                             type,
                             amount);
    }

    public boolean equals(Transaction other) {
        if (other == null || other.id == null || id == null)
            return false;
        return id.equals(other.id);
    }

    public boolean equals(UUID id) {
        if (id == null || this.id == null)
            return false;
        return id.equals(this.id);
    }
}
