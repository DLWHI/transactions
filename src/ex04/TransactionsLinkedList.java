package ex04;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private Transaction head = new Transaction(null, null, null, null, 0);
    private int length;

    public TransactionsLinkedList() {
        head.bind(head, head);
    }

    public int getLength() {
        return length;
    }

    public void addTransaction(Transaction transaction) {
        Transaction last = head.getPrev();
        last.bind(transaction, Transaction.Direction.NEXT);
        head.bind(transaction, Transaction.Direction.PREV);
        transaction.bind(last, head);
        length++;
    }

    public void removeTransaction(UUID id) throws TransactionNotFoundException {
        Transaction node = head.getNext();
        for (; node != head && !node.equals(id); node = node.getNext());
        if (node != head) {
            node.unbind();
            length--;
        } else {
            throw new TransactionNotFoundException(
                        "Removing non-existent transaction!");
        }
    }
    
    public Transaction[] toArray() {
        Transaction[] array = new Transaction[length];
        Transaction node = head.getNext();
        for (int i = 0; node != head; node = node.getNext(), ++i) {
            array[i] = node;
        }
        return array;
    }
}
