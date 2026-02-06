package com.example.cookielife;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView counterTextView;
    private Button cookieButton;
    private Button upgradeButton;
    private int clickCount = 0;
    private int clickPower = 1;
    private int upgradeCost = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTextView = findViewById(R.id.counterTextView);
        cookieButton = findViewById(R.id.cookieButton);
        upgradeButton = findViewById(R.id.upgradeButton);
        Button resetButton = findViewById(R.id.resetButton);

        updateCounter();
        updateUpgradeButton();

        cookieButton.setOnClickListener(v -> {
            clickCount += clickPower;
            updateCounter();

            cookieButton.animate()
                    .scaleX(0.9f).scaleY(0.9f)
                    .setDuration(100)
                    .withEndAction(() -> cookieButton.animate()
                            .scaleX(1f).scaleY(1f)
                            .setDuration(100));
        });

        upgradeButton.setOnClickListener(v -> {
            if (clickCount >= upgradeCost) {
                clickCount -= upgradeCost;
                clickPower++;
                upgradeCost *= 2;
                updateCounter();
                updateUpgradeButton();
            }
        });

        resetButton.setOnClickListener(v -> {
            clickCount = 0;
            clickPower = 1;
            upgradeCost = 10;
            updateCounter();
            updateUpgradeButton();
        });
    }

    private void updateCounter() {
        counterTextView.setText(String.valueOf(clickCount));
    }

    @SuppressLint("SetTextI18n")
    private void updateUpgradeButton() {
        upgradeButton.setText("Сила +" + clickPower + " (Стоимость: " + upgradeCost + ")");
    }
}