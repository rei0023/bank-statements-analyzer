package org.example;

@FunctionalInterface
public interface BankTransactionSummarizerI {
    double summarize(double accumulator, BankTransaction bankTransaction);
}
