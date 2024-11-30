package com.example.personalfinancialmanagement;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TransactionDao {
    @Query("DELETE FROM transactions")
    void deleteAllTransactions(); // Method to delete all transactions

    @Insert
    void insert(Transaction transaction); // Insert a new transaction

    @Query("SELECT * FROM transactions ORDER BY date DESC")
    List<Transaction> getAllTransactions(); // Fetch all transactions, sorted by date

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'Income'")
    double getTotalIncome(); // Get total income

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'Expense'")
    double getTotalExpense(); // Get total expense

}

