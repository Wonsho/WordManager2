package com.wons.wordmanager2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wons.wordmanager2.add_word.AddListActivity;
import com.wons.wordmanager2.databinding.ActivityMainBinding;
import com.wons.wordmanager2.game_word.GameListActivity;
import com.wons.wordmanager2.study_word.StudyListActivity;
import com.wons.wordmanager2.test_word.TestListActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.dataBaseBuild(getApplicationContext());
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddListActivity.class));
            }
        });
        binding.btnStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), StudyListActivity.class));
            }
        });
        binding.btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TestListActivity.class));
            }
        });
        binding.btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), GameListActivity.class));
            }
        });
    }
}