package com.example.personalfinancialmanagement;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transactions")
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    public int id; // Auto-incremented ID (Primary Key)

    public String type; // "Income" or "Expense"
    public String category; // Category of the transaction
    public double amount; // Transaction amount
    public String date; // Date of the transaction
}
