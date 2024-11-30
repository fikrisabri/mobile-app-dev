package com.example.personalfinancialmanagement;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FinancialPlanningActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_planning);

        EditText etGoalName = findViewById(R.id.etGoalName);
        EditText etTargetAmount = findViewById(R.id.etTargetAmount);
        EditText etTimelineMonths = findViewById(R.id.etTimelineMonths);
        Button btnCalculate = findViewById(R.id.btnCalculate);
        TextView tvResult = findViewById(R.id.tvResult);

        btnCalculate.setOnClickListener(v -> {
            String goalName = etGoalName.getText().toString().trim();
            String targetAmountStr = etTargetAmount.getText().toString().trim();
            String timelineMonthsStr = etTimelineMonths.getText().toString().trim();

            if (goalName.isEmpty() || targetAmountStr.isEmpty() || timelineMonthsStr.isEmpty()) {
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double targetAmount = Double.parseDouble(targetAmountStr);
                int timelineMonths = Integer.parseInt(timelineMonthsStr);

                if (timelineMonths <= 0) {
                    Toast.makeText(this, "Timeline must be greater than 0 months", Toast.LENGTH_SHORT).show();
                    return;
                }

                double monthlySavings = targetAmount / timelineMonths;
                tvResult.setText(String.format("To achieve your goal '%s', save RM%.2f monthly.", goalName, monthlySavings));
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid number format!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
