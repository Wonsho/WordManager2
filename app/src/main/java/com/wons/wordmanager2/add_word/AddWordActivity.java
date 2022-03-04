package com.wons.wordmanager2.add_word;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;

import com.wons.wordmanager2.add_word.adapter.WordAdapter;
import com.wons.wordmanager2.add_word.diaog.CallBackInAddWord;
import com.wons.wordmanager2.add_word.diaog.CallBackInAddWordForBoolean;
import com.wons.wordmanager2.add_word.diaog.CallBackInAddWordForString;
import com.wons.wordmanager2.add_word.diaog.CallBackKey;
import com.wons.wordmanager2.add_word.diaog.DialogsInAddWord;
import com.wons.wordmanager2.add_word.value.Word;
import com.wons.wordmanager2.databinding.ActivityAddWordBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class AddWordActivity extends AppCompatActivity {
    private ActivityAddWordBinding binding;
    private AddWord_ViewModel viewModel;
    private TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddWordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvListName.setText(getIntent().getStringExtra("listName"));
        viewModel = new ViewModelProvider(this).get(AddWord_ViewModel.class);
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR)
                tts.setLanguage(Locale.ENGLISH);
            }
        });
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
                        if(viewModel.getAllWord_inDB(word.english).size() == 0) {
                            viewModel.insertWord(word);
                            getData();
                        }  else {
//                            AlertDialog alertDialog1 = new DialogsInAddWord()
                        }

                    }
                });
                alertDialog.show();
            }
        });
    }

    private void getData() {
        ArrayList<Word> words = viewModel.getAllWordsInList(binding.tvListName.getText().toString().trim());
        ((WordAdapter)binding.lv.getAdapter()).setWords(words, viewModel.getWordPercentageMap(words));
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
            binding.lv.setAdapter(new WordAdapter(new CallBackInAddWordForString() {
                @Override
                public void callBack(String str) {
                    tts.speak(str.trim(),TextToSpeech.QUEUE_FLUSH, null);
                }
            }, new CallBackInAddWordForBoolean() {
                @Override
                public void callBack(Boolean check, int indexOfValue) {
                    if(check) {
                        AlertDialog alertDialog = new DialogsInAddWord().dialogForDelete(AddWordActivity.this, new CallBackInAddWordForBoolean() {
                            @Override
                            public void callBack(Boolean check, int index) {
                                if(check) {
                                    viewModel.deleteWord(((WordAdapter)binding.lv.getAdapter()).getItem(indexOfValue));
                                    getData();
                                }
                            }
                        });
                        alertDialog.setMessage(((WordAdapter)binding.lv.getAdapter()).getItem(indexOfValue).english + "\n단어를 삭제 하시겠습니까?");
                        alertDialog.show();
                    }
                }
            }));
        }
        ((WordAdapter)binding.lv.getAdapter()).notifyDataSetChanged();
    }
}