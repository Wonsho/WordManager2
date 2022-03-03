package com.wons.wordmanager2.add_word;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;

import com.wons.wordmanager2.add_word.adapter.WordAdapter;
import com.wons.wordmanager2.add_word.diaog.CallBackInAddWord;
import com.wons.wordmanager2.add_word.diaog.CallBackKey;
import com.wons.wordmanager2.add_word.diaog.DialogsInAddWord;
import com.wons.wordmanager2.add_word.value.Word;
import com.wons.wordmanager2.databinding.ActivityAddWordBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class AddWordActivity extends AppCompatActivity {
    private ActivityAddWordBinding binding;
    private AddWordViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddWordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvListName.setText(getIntent().getStringExtra("listName"));
        viewModel = new ViewModelProvider(this).get(AddWordViewModel.class);
        setView();
        onC();
        getData();
    }

    private void onC() {
        binding.btnAddList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new DialogsInAddWord().getDialogForAddWord(AddWordActivity.this, new CallBackInAddWord() {
                    @Override
                    public void callBack(HashMap<CallBackKey, String> callback) {
                        Word word = new Word(callback.get(CallBackKey.ENGLISH), callback.get(CallBackKey.KOREAN),viewModel.getListCode(binding.tvListName.getText().toString().trim()));
                        viewModel.insertWord(word);
                        getData();
                    }
                });
                alertDialog.show();
            }
        });
    }

    private void getData() {
        ArrayList<Word> words = viewModel.getAllWords(binding.tvListName.getText().toString().trim());
        ((WordAdapter)binding.lv.getAdapter()).setWords(words);
        if(words.size() != 0) {
            binding.tvInfoList.setVisibility(View.GONE);
            binding.lv.setVisibility(View.VISIBLE);
        } else {
            binding.tvInfoList.setVisibility(View.VISIBLE);
            binding.lv.setVisibility(View.GONE);
        }
        setView();
    }

    private void setView() {
        if(binding.lv.getAdapter() == null) {
            binding.lv.setAdapter(new WordAdapter());
        }
        ((WordAdapter)binding.lv.getAdapter()).notifyDataSetChanged();
    }
}