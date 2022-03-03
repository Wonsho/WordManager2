package com.wons.wordmanager2.test_word;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wons.wordmanager2.databinding.ActivityTestListBinding;

public class TestListActivity extends AppCompatActivity {
    private ActivityTestListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}