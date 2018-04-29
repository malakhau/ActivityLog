package com.log.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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

    private float mHeight = 0f;
    private float mWeight = 0f;
    private float mBMI = 0f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_settings, container, false);

        saveButton = view.findViewById(R.id.button_save);
        heightEditText = view.findViewById(R.id.editText_height);
        weightEditText = view.findViewById(R.id.editText_weight);
        BMIeditText = view.findViewById(R.id.editText_BMI);
        BMIbar = view.findViewById(R.id.progressBar_BMI);
        underweightText = view.findViewById(R.id.textView_underweight);
        healthyweightText = view.findViewById(R.id.textView_healthy_weight);
        overweightText = view.findViewById(R.id.textView_overweight);

        loadData();

        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                hideKeyboard(v);
                handleSaveButtonClick();
            }
        });

        return view;
    }

    private void hideKeyboard(View aView) {
        if (aView != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(aView.getWindowToken(), 0);
        }
    }

    private void handleSaveButtonClick() {
        float height = Float.parseFloat(heightEditText.getText().toString());
        float weight = Float.parseFloat(weightEditText.getText().toString());

        float bmi = weight / (height * height);

        BMIeditText.setText(String.valueOf(Math.round(bmi * 100) / 100));

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

    private void saveData() {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putFloat(getString(R.string.saved_height), mHeight);
        editor.putFloat(getString(R.string.saved_width), mWeight);
        editor.putFloat(getString(R.string.saved_bmi), mBMI);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        mHeight = sharedPref.getFloat(getString(R.string.saved_height), mHeight);
        mWeight = sharedPref.getFloat(getString(R.string.saved_width), mWeight);
        mBMI = sharedPref.getFloat(getString(R.string.saved_bmi), mBMI);

        setLoadedData();
        changeTextColorDependsUponBMI();
    }

    private void setLoadedData() {
        heightEditText.setText(String.valueOf(mHeight));
        weightEditText.setText(String.valueOf(mWeight));
        setBMI();
    }
}
