package com.wons.wordmanager2.add_word.wordinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wons.wordmanager2.databinding.ActivityWordExplainBinding;

public class WordExplainActivity extends AppCompatActivity {

    private ActivityWordExplainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWordExplainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}