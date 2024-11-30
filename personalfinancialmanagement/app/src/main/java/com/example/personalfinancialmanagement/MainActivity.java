package com.example.personalfinancialmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {
    private AppDatabase db;
    private TextView tvBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvBalance = findViewById(R.id.tvBalance);
        Button btnAddTransaction = findViewById(R.id.btnAddTransaction);
        Button btnViewHistory = findViewById(R.id.btnViewHistory);
        Button btnViewAnalytics = findViewById(R.id.btnViewAnalytics);
        Button btnFinancialEducation = findViewById(R.id.tvEducationContent);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "finance_db").allowMainThreadQueries().build();

        updateBalance();

        btnAddTransaction.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddTransactionActivity.class));
        });

        btnViewHistory.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, HistoryActivity.class));
        });

        btnViewAnalytics.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AnalyticsActivity.class));
        });

        Button tvEducationContent = findViewById(R.id.tvEducationContent);
        btnFinancialEducation.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, FinancialEducationActivity.class));
        });

        Button btnFinancialPlanning = findViewById(R.id.btnFinancialPlanning);
        btnFinancialPlanning.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, FinancialPlanningActivity.class));
        });

        Button btnResetBalance = findViewById(R.id.btnResetBalance);

// Set the click listener for the button
        btnResetBalance.setOnClickListener(v -> {
            // Show a confirmation dialog before deleting all transactions
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Reset Balance")
                    .setMessage("Are you sure you want to delete all transactions?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        // Call the method to delete all transactions
                        db.transactionDao().deleteAllTransactions();

                        // Update the balance after deletion
                        updateBalance();

                        // Show a confirmation toast message
                        Toast.makeText(MainActivity.this, "All transactions have been deleted.", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)  // If the user clicks "No", nothing happens
                    .show(); // Show the dialog
        });
    }

    private void updateBalance() {
        double income = db.transactionDao().getTotalIncome();
        double expense = db.transactionDao().getTotalExpense();
        double balance = income - expense;

        tvBalance.setText("Balance: RM" + balance);
    }
}
