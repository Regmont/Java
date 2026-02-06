package com.example.mobilecalculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText display;
    private String currentNumber = "";
    private String operator = "";
    private double firstNumber = 0;
    private boolean isOperatorClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        Button btnClear = findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(v -> {
            currentNumber = "";
            operator = "";
            firstNumber = 0;
            display.setText("0");
            isOperatorClicked = false;
        });

        Button btnEquals = findViewById(R.id.btn_equals);
        btnEquals.setOnClickListener(v -> calculateResult());

        Button btnDot = findViewById(R.id.btn_dot);
        btnDot.setOnClickListener(v -> {
            if (!currentNumber.contains(".")) {
                currentNumber += ".";
                display.setText(currentNumber);
            }
        });

        Button btnPlusMinus = findViewById(R.id.btn_plus_minus);
        btnPlusMinus.setOnClickListener(v -> {
            if (!currentNumber.isEmpty()) {
                double num = Double.parseDouble(currentNumber);
                num = -num;
                currentNumber = String.valueOf(num);
                display.setText(currentNumber);
            }
        });

        Button btnPercent = findViewById(R.id.btn_percent);
        btnPercent.setOnClickListener(v -> {
            if (!currentNumber.isEmpty()) {
                double num = Double.parseDouble(currentNumber);
                num = num / 100;
                currentNumber = String.valueOf(num);
                display.setText(currentNumber);
            }
        });

        int[] numberButtonIds = {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3,
                R.id.btn_4, R.id.btn_5, R.id.btn_6,
                R.id.btn_7, R.id.btn_8, R.id.btn_9
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(v -> {
                Button button = (Button) v;
                String digit = button.getText().toString();

                if (isOperatorClicked) {
                    currentNumber = digit;
                    isOperatorClicked = false;
                } else {
                    currentNumber += digit;
                }

                display.setText(currentNumber);
            });
        }

        int[] operatorButtonIds = {
                R.id.btn_plus, R.id.btn_minus,
                R.id.btn_multiply, R.id.btn_divide
        };

        for (int id : operatorButtonIds) {
            findViewById(id).setOnClickListener(v -> {
                Button button = (Button) v;
                if (!currentNumber.isEmpty()) {
                    firstNumber = Double.parseDouble(currentNumber);
                    operator = button.getText().toString();
                    isOperatorClicked = true;
                }
            });
        }
    }

    private void calculateResult() {
        if (!operator.isEmpty() && !currentNumber.isEmpty()) {
            double secondNumber = Double.parseDouble(currentNumber);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    result = firstNumber - secondNumber;
                    break;
                case "X":
                    result = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }

            if (result == (long) result) {
                display.setText(String.valueOf((long) result));
            } else {
                display.setText(String.valueOf(result));
            }

            currentNumber = String.valueOf(result);
            operator = "";
            isOperatorClicked = true;
        }
    }
}
