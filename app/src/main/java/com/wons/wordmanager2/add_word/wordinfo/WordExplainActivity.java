package com.wons.wordmanager2.add_word.wordinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.wons.wordmanager2.add_word.diaog.CallBackCheck;
import com.wons.wordmanager2.add_word.diaog.DialogsInAddWord;
import com.wons.wordmanager2.databinding.ActivityWordExplainBinding;

public class WordExplainActivity extends AppCompatActivity {

    private ActivityWordExplainBinding binding;
    private WordExPlain_ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWordExplainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(WordExPlain_ViewModel.class);
        binding.tvTitle.setText(getIntent().getStringExtra("english"));
        binding.etMemo.requestFocus();
        if (viewModel.checkDialogSetting()) {
            AlertDialog alertDialog = new DialogsInAddWord().getDialogForNotice(WordExplainActivity.this, new CallBackCheck() {
                @Override
                public void callBack(Boolean check, int index) {
                    if (check) {
                        viewModel.changeDialogCode();

                    }
                }
            });
            alertDialog.show();
        }
        showSoftKeyboard();
        setExPlainView();
        binding.etMemo.setSelection(binding.etMemo.getText().toString().length());
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveExplain();
            }
        });
        binding.lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSoftKeyboard();
            }
        });
    }


    private void showSoftKeyboard() {
        View view = binding.etMemo;
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            boolean isShowing = imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
            if (!isShowing) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            }
        }
    }

    private void setExPlainView() {
        String str = viewModel.getWordExplain(getIntent().getStringExtra("english"));
        if (str != null) {
            Log.e("setExPlainView", "null!");
            if (str.trim().isEmpty()) return;
            binding.etMemo.setText(str);
            return;
        }
        Log.e("setExPlainView", "null");
    }

    private void saveExplain() {
        viewModel.saveExplain(getIntent().getStringExtra("english"), binding.etMemo.getText().toString().trim());
        if (binding.etMemo.getText().toString().trim().isEmpty()) {
            viewModel.deleteExplain(getIntent().getStringExtra("english"));
        }
        Toast.makeText(getApplicationContext(), "?????????????????????", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.etMemo.requestFocus();
        showSoftKeyboard();
    }

    @Override
    public void onBackPressed() {
        String strInMemo = binding.etMemo.getText().toString().trim();
        if (!(strInMemo.isEmpty()) && (viewModel.getWordExplain(getIntent().getStringExtra("english")) == null || viewModel.getWordExplain(getIntent().getStringExtra("english")).isEmpty())) {
            showCheck();
            return;
        }
        if (strInMemo.isEmpty() && (viewModel.getWordExplain(getIntent().getStringExtra("english")) == null || viewModel.getWordExplain(getIntent().getStringExtra("english")).isEmpty())) {
            finish();
            return;
        }

        if (!viewModel.getWordExplain(getIntent().getStringExtra("english").trim()).equals(strInMemo)) {
            showCheck();
            return;
        }
        finish();
        super.onBackPressed();
    }

    public void showCheck() {
        AlertDialog.Builder builder = new AlertDialog.Builder(WordExplainActivity.this);
        builder.setTitle("??????");
        builder.setMessage("?????? ????????? ???????????? \n?????? ??? ??????????????????????");
        builder.setPositiveButton("????????? ?????????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                saveExplain();
                finish();
            }
        });
        builder.setNegativeButton("?????? ?????? ?????????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.create().show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        binding.etMemo.requestFocus();
        showSoftKeyboard();

    }
}
