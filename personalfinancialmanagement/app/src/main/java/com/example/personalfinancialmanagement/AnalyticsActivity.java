package com.example.personalfinancialmanagement;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class AnalyticsActivity extends AppCompatActivity {
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        TextView tvTotalIncome = findViewById(R.id.tvTotalIncome);
        TextView tvTotalExpense = findViewById(R.id.tvTotalExpense);
        TextView tvBalance = findViewById(R.id.tvBalance);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "finance_db")
                .allowMainThreadQueries()
                .build();

        double income = db.transactionDao().getTotalIncome();
        double expense = db.transactionDao().getTotalExpense();
        double balance = income - expense;

        tvTotalIncome.setText("Total Income: RM" + income);
        tvTotalExpense.setText("Total Expense: RM" + expense);
        tvBalance.setText("Balance: RM" + balance);
    }
}
