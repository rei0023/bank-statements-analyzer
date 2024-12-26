package org.example;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount() {
        double totalAmount = 0;
        for (BankTransaction bankTransaction : bankTransactions) {
            totalAmount += bankTransaction.getAmount();
        }
        return totalAmount;
    }

    public double calculateTotalInMonth(final Month month) {
        double totalAmount = 0;
        for (BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month) {
                totalAmount += bankTransaction.getAmount();
            }
        }
        return totalAmount;
    }

    public double calculateTotalForCategory(final String category) {
        double totalAmount = 0;
        for (BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDescription().equals(category)) {
                totalAmount += bankTransaction.getAmount();
            }
        }
        return totalAmount;
    }

    public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount) {
        return findTransactions(bT -> bT.getAmount() >= amount);
    }

    public List<BankTransaction> findTransactionsInMonth(final Month month) {
        final List<BankTransaction> result = new ArrayList<>();
        for (BankTransaction bankTransaction : bankTransactions) {
            if (bankTransaction.getDate().getMonth() == month) {
                result.add(bankTransaction);
            }
        }
        return result;
    }

    public List<BankTransaction> findTransactions(final BankTransactionFilterI bankTransactionFilter) {
        final List<BankTransaction> result = new ArrayList<>();
        for (BankTransaction bankTransaction : bankTransactions) {
            if (bankTransactionFilter.test(bankTransaction)) {
                result.add(bankTransaction);
            }
        }
        return result;
    }

    public double summarizeTransactions(final BankTransactionSummarizerI bankTransactionSummarizer) {
        double result = 0d;
        for (final BankTransaction bankTransaction : bankTransactions) {
            bankTransactionSummarizer.summarize(result, bankTransaction);
        }
        return result;
    }

    public double calculateInMonth(final Month month) {
        return summarizeTransactions((acc, bT) -> bT.getDate().getMonth() == month ?
                acc + bT.getAmount() : acc
        );
    }
}
