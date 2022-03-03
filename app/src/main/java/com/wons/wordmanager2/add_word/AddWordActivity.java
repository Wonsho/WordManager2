package com.wons.wordmanager2.add_word;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wons.wordmanager2.databinding.ActivityAddWordBinding;

public class AddWordActivity extends AppCompatActivity {
    private ActivityAddWordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddWordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}