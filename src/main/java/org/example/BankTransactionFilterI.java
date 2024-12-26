package org.example;

@FunctionalInterface
public interface BankTransactionFilterI {
    boolean test(BankTransaction bankTransaction);
}
