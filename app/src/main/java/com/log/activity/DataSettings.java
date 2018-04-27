package com.log.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class DataSettings extends Fragment {

    private Button saveButton;
    private EditText heightEditText;
    private EditText weightEditText;
    private EditText BMIeditText;
    private ProgressBar BMIbar;

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

        int minValueOfBMI = 16;
        int multiplierProgressBar = 4;
        int bmiProgressBar = Math.round((bmi - minValueOfBMI) * multiplierProgressBar);
        BMIbar.setProgress(bmiProgressBar);
    }


}
