package com.example.personalfinancialmanagement;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FinancialEducationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_education);

        TextView tvEducationContent = findViewById(R.id.tvEducationContent);

        String educationContent = "Welcome to Financial Education! Here are some tips:\n\n" +
                "1. Budgeting: Track your income and expenses to control spending.\n" +
                "2. Saving: Set aside at least 20% of your income for emergencies.\n" +
                "3. Investing: Learn about stocks, mutual funds, and other investment options.\n" +
                "4. Avoid Debt: Use credit cards wisely and pay off debts promptly.\n" +
                "5. Financial Goals: Set achievable short-term and long-term goals.\n\n" +
                "Explore more on financial literacy and take charge of your financial future!";
        tvEducationContent.setText(educationContent);
    }
}
