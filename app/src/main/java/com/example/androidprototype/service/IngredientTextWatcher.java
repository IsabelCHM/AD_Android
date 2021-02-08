package com.example.androidprototype.service;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class IngredientTextWatcher implements TextWatcher {

    private EditText editText;

    public IngredientTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //int position = (int) editText.getTag();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
