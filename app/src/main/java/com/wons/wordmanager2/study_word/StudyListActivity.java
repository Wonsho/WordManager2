package com.wons.wordmanager2.study_word;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.wons.wordmanager2.databinding.ActivityStudyListBinding;

public class StudyListActivity extends AppCompatActivity {

    private ActivityStudyListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudyListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}