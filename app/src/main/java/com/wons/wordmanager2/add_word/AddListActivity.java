package com.wons.wordmanager2.add_word;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.wons.wordmanager2.add_word.adapter.WordListAdapter;
import com.wons.wordmanager2.add_word.diaog.CallBackInAddWord;
import com.wons.wordmanager2.add_word.diaog.CallBackInAddWordForIndex;
import com.wons.wordmanager2.add_word.diaog.CallBackKey;
import com.wons.wordmanager2.add_word.diaog.DialogsInAddWord;
import com.wons.wordmanager2.add_word.value.WordList;
import com.wons.wordmanager2.databinding.ActivityAddListBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class AddListActivity extends AppCompatActivity {
    private ActivityAddListBinding binding;
    private AddWordList_ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(AddWordList_ViewModel.class);
        onC();
        setView();
        getData();
    }

    private void onC() {
        binding.btnAddList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alertDialog = new DialogsInAddWord().getDialogForAddList(AddListActivity.this, new CallBackInAddWord() {
                    @Override
                    public void callBack(HashMap<CallBackKey, String> callback) {
                        String listName = callback.get(CallBackKey.LIST_NAME);
                        WordList wordList = new WordList(listName);
                        if(viewModel.checkSameListName(wordList)) {
                            viewModel.insertWordList(wordList);
                            getData();
                            return;
                        }
                        Toast.makeText(getApplicationContext(), "??????????????? ?????? ???????????? ????????????", Toast.LENGTH_SHORT).show();
                        getData();
                    }
                });
                alertDialog.show();
            }
        });
        binding.lv.setClickable(true);
        binding.lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), AddWordActivity.class);
                intent.putExtra("listName",((WordListAdapter) binding.lv.getAdapter()).getItem(i).listName);
                startActivity(intent);

            }
        });

    }

    private void getData() {
        ArrayList<WordList> wordLists = viewModel.getAllList();
        ((WordListAdapter) binding.lv.getAdapter()).setWordLists(wordLists);
        if (wordLists.size() != 0) {
            binding.tvInfoList.setVisibility(View.GONE);
            binding.lv.setVisibility(View.VISIBLE);
        } else {
            binding.tvInfoList.setVisibility(View.VISIBLE);
            binding.lv.setVisibility(View.GONE);
        }
        setView();
    }

    private void setView() {
        if (binding.lv.getAdapter() == null) {
            binding.lv.setAdapter(new WordListAdapter(new CallBackInAddWordForIndex() {
                @Override
                public void callBack(Boolean check, int index1) {
                    AlertDialog alertDialog = new DialogsInAddWord().dialogForDelete(AddListActivity.this, new CallBackInAddWordForIndex() {
                        @Override
                        public void callBack(Boolean check, int index) {
                            if (check) {
                                Toast.makeText(getApplicationContext(), "?????????????????????", Toast.LENGTH_SHORT).show();
                                viewModel.deleteWordList(((WordListAdapter) binding.lv.getAdapter()).getItem(index1));
                                getData();
                            }
                        }
                    });
                    alertDialog.setMessage(((WordListAdapter) binding.lv.getAdapter()).getItem(index1).listName + " ???????????? ????????????????????????? \n???????????? ????????? ????????? ?????? ??? ??? ????????????");
                    alertDialog.show();
                }
            }, new CallBackInAddWordForIndex() {
                @Override
                public void callBack(Boolean check, int index) {
                   String words =  viewModel.getWordInList(((WordListAdapter) binding.lv.getAdapter()).getItem(index).listName);
                   Toast.makeText(getApplicationContext(), words, Toast.LENGTH_LONG).show();
                }
            }));
        }
        ((WordListAdapter) binding.lv.getAdapter()).notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getData();
    }
}