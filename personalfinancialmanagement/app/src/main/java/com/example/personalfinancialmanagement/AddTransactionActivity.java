package com.example.personalfinancialmanagement;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddTransactionActivity extends AppCompatActivity {
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "finance_db")
                .allowMainThreadQueries()
                .build();

        EditText etAmount = findViewById(R.id.etAmount);
        EditText etCategory = findViewById(R.id.etCategory);
        RadioGroup rgType = findViewById(R.id.rgType);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> {
            String type = ((RadioButton) findViewById(rgType.getCheckedRadioButtonId())).getText().toString();
            String category = etCategory.getText().toString();
            double amount = Double.parseDouble(etAmount.getText().toString());
            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

            // Use the correct Transaction class
            com.example.personalfinancialmanagement.Transaction transaction = new com.example.personalfinancialmanagement.Transaction();
            transaction.type = type;
            transaction.category = category;
            transaction.amount = amount;
            transaction.date = date;

            db.transactionDao().insert(transaction);
            finish();
        });
    }
}
