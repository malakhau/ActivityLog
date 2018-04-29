package com.log.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class DataSettings extends Fragment {

    private Button saveButton;
    private EditText heightEditText;
    private EditText weightEditText;
    private EditText BMIeditText;
    private ProgressBar BMIbar;
    private TextView underweightText;
    private TextView healthyweightText;
    private TextView overweightText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_settings, container, false);

        saveButton = view.findViewById(R.id.button_save);
        heightEditText = view.findViewById(R.id.editText_height);
        weightEditText = view.findViewById(R.id.editText_weight);
        BMIeditText = view.findViewById(R.id.editText_BMI);
        BMIbar = view.findViewById(R.id.progressBar_BMI);

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                handleSaveButtonClick();
            }
        });

        return view;
    }

    private void handleSaveButtonClick() {
        float height = Float.parseFloat(heightEditText.getText().toString());
        float weight = Float.parseFloat(weightEditText.getText().toString());

        float bmi = weight / (height * height);

        BMIeditText.setText(String.valueOf(bmi));

        final int minValueOfBMI = 16;
        final int multiplierProgressBar = 4;
        int bmiProgressBar = Math.round((bmi - minValueOfBMI) * multiplierProgressBar);
        BMIbar.setProgress(bmiProgressBar);
        changeTextColorDependsUponBMI(bmi);
    }

    private void changeTextColorDependsUponBMI(float aBMI) {
        resetBMItextCollorToDefault();

        if (aBMI < 18.5) {
            underweightText.setTextColor(Color.RED);
        } else if (aBMI >= 18.5 && aBMI <= 25.0) {
            healthyweightText.setTextColor(Color.GREEN);
        } else if (aBMI > 25.0) {
            overweightText.setTextColor(Color.RED);
        }
    }

    private void resetBMItextCollorToDefault() {
        int defaultColor = getResources().getColor(android.R.color.darker_gray);
        underweightText.setTextColor(defaultColor);
        healthyweightText.setTextColor(defaultColor);
        overweightText.setTextColor(defaultColor);
    }
}
