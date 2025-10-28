package com.example.labcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView txtDisplay;
    private String currentInput = "";
    private double firstOperand = 0;
    private String currentOperator = "";
    private boolean resetScreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupClickListeners();
    }

    private void initializeViews() {
        txtDisplay = findViewById(R.id.txtDisplay);
    }

    private void setupClickListeners() {
        // Number buttons
        setupNumberButton(R.id.btn0, "0");
        setupNumberButton(R.id.btn1, "1");
        setupNumberButton(R.id.btn2, "2");
        setupNumberButton(R.id.btn3, "3");
        setupNumberButton(R.id.btn4, "4");
        setupNumberButton(R.id.btn5, "5");
        setupNumberButton(R.id.btn6, "6");
        setupNumberButton(R.id.btn7, "7");
        setupNumberButton(R.id.btn8, "8");
        setupNumberButton(R.id.btn9, "9");

        // Operation buttons
        findViewById(R.id.btnAdd).setOnClickListener(v -> setOperation("+"));
        findViewById(R.id.btnSubtract).setOnClickListener(v -> setOperation("-"));
        findViewById(R.id.btnMultiply).setOnClickListener(v -> setOperation("×"));
        findViewById(R.id.btnDivide).setOnClickListener(v -> setOperation("÷"));
        findViewById(R.id.btnSqrt).setOnClickListener(v -> calculateSquareRoot());

        // Utility buttons
        findViewById(R.id.btnClear).setOnClickListener(v -> clearCalculator());
        findViewById(R.id.btnBack).setOnClickListener(v -> backspace());
        findViewById(R.id.btnSign).setOnClickListener(v -> changeSign());
        findViewById(R.id.btnEquals).setOnClickListener(v -> calculateResult());
        findViewById(R.id.btnDecimal).setOnClickListener(v -> appendDecimal());
    }

    private void setupNumberButton(int buttonId, String number) {
        findViewById(buttonId).setOnClickListener(v -> appendNumber(number));
    }

    private void appendNumber(String number) {
        if (resetScreen) {
            currentInput = "";
            resetScreen = false;
        }

        if (currentInput.equals("0")) {
            currentInput = number;
        } else {
            currentInput += number;
        }

        updateDisplay();
    }

    private void appendDecimal() {
        if (resetScreen) {
            currentInput = "0";
            resetScreen = false;
        }

        if (!currentInput.contains(".")) {
            if (currentInput.isEmpty()) {
                currentInput = "0";
            }
            currentInput += ".";
            updateDisplay();
        }
    }

    private void setOperation(String operator) {
        if (!currentInput.isEmpty()) {
            if (!currentOperator.isEmpty()) {
                calculateResult();
            }
            firstOperand = Double.parseDouble(currentInput);
            currentOperator = operator;
            resetScreen = true;
        }
    }

    private void calculateResult() {
        if (!currentInput.isEmpty() && !currentOperator.isEmpty()) {
            double secondOperand = Double.parseDouble(currentInput);
            double result = performCalculation(firstOperand, secondOperand, currentOperator);

            if (result == (long) result) {
                currentInput = String.valueOf((long) result);
            } else {
                currentInput = String.valueOf(result);
            }

            currentOperator = "";
            updateDisplay();
            resetScreen = true;
        }
    }

    private double performCalculation(double first, double second, String operator) {
        switch (operator) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "×":
                return first * second;
            case "÷":
                if (second == 0) {
                    showToast(getString(R.string.error_divide_by_zero));
                    return 0;
                }
                return first / second;
            default:
                return 0;
        }
    }

    private void calculateSquareRoot() {
        if (!currentInput.isEmpty()) {
            double number = Double.parseDouble(currentInput);
            if (number >= 0) {
                double result = Math.sqrt(number);

                if (result == (long) result) {
                    currentInput = String.valueOf((long) result);
                } else {
                    currentInput = String.valueOf(result);
                }
            } else {
                showToast(getString(R.string.error_invalid_sqrt));
                return;
            }
            updateDisplay();
            resetScreen = true;
        }
    }

    private void clearCalculator() {
        currentInput = "";
        firstOperand = 0;
        currentOperator = "";
        updateDisplay();
    }

    private void backspace() {
        if (!currentInput.isEmpty()) {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
            if (currentInput.isEmpty()) {
                currentInput = "0";
            }
            updateDisplay();
        }
    }

    private void changeSign() {
        if (!currentInput.isEmpty() && !currentInput.equals("0")) {
            double number = Double.parseDouble(currentInput);
            number = -number;

            if (number == (long) number) {
                currentInput = String.valueOf((long) number);
            } else {
                currentInput = String.valueOf(number);
            }

            updateDisplay();
        }
    }

    private void updateDisplay() {
        if (currentInput.isEmpty()) {
            txtDisplay.setText("0");
        } else {
            txtDisplay.setText(currentInput);
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}