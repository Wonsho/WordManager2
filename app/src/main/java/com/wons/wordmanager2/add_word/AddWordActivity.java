package com.wons.wordmanager2.add_word;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Toast;

import com.wons.wordmanager2.R;
import com.wons.wordmanager2.add_word.adapter.WordAdapter;
import com.wons.wordmanager2.add_word.diaog.CallBackInAddWord;
import com.wons.wordmanager2.add_word.diaog.CallBackInAddWordForIndex;
import com.wons.wordmanager2.add_word.diaog.CallBackInAddWordForString;
import com.wons.wordmanager2.add_word.diaog.CallBackKey;
import com.wons.wordmanager2.add_word.diaog.DialogsInAddWord;
import com.wons.wordmanager2.add_word.value.WordEnglish;
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
        overridePendingTransition(R.anim.vertical_enter,R.anim.non);
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
                        WordEnglish wordEnglish = new WordEnglish(callback.get(CallBackKey.ENGLISH).trim(), viewModel.getListCode(getIntent().getStringExtra("listName")));
                        if(!viewModel.isCheckSameWordInList(viewModel.getListCode(getIntent().getStringExtra("listName")), callback.get(CallBackKey.ENGLISH))) {  // todo 리스트안에 중복으로 바꿈!
                            viewModel.insertWord(wordEnglish, callback.get(CallBackKey.KOREAN).trim());
                            getData();
                        }  else {
                            Toast.makeText(getApplicationContext(), "중복되는 단어가 리스트에 존재합니다", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                });
                alertDialog.show();
            }
        });

        binding.mv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void getData() {
        ArrayList<WordEnglish> wordEnglishes = viewModel.getAllWordsInList(binding.tvListName.getText().toString().trim());
        ((WordAdapter)binding.lv.getAdapter()).setWords(wordEnglishes, viewModel.getWordPercentageMap(wordEnglishes), viewModel.getWordKorean(wordEnglishes));
        if(wordEnglishes.size() != 0) {
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
            }, new CallBackInAddWordForIndex() {
                @Override
                public void callBack(Boolean check, int indexOfValue) {
                    if(check) {
                        AlertDialog alertDialog = new DialogsInAddWord().dialogForDelete(AddWordActivity.this, new CallBackInAddWordForIndex() {
                            @Override
                            public void callBack(Boolean check, int index) {
                                if(check) {
                                    viewModel.deleteWord(((WordAdapter)binding.lv.getAdapter()).getItem(indexOfValue));
                                    Toast.makeText(getApplicationContext(), "삭제되었습니다", Toast.LENGTH_SHORT).show();
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

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.non,R.anim.vertical_exit);
    }
}