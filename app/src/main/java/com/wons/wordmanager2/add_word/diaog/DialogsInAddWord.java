package com.wons.wordmanager2.add_word.diaog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.wons.wordmanager2.R;

import java.util.HashMap;

public class DialogsInAddWord {
    boolean check;
    @SuppressLint("ResourceType")
    public AlertDialog getDialogForAddWord(Context context, CallBackInAddWord callBackInAddWord) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_add_word, null);
        EditText et_english = view.findViewById(R.id.et_english);
        EditText et_korean = view.findViewById(R.id.et_korean);

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setPositiveButton("추가", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!(et_english.getText().toString().isEmpty()) && !(et_korean.getText().toString().isEmpty())) {
                    String english = et_english.getText().toString().trim();
                    char[] chars = english.toCharArray();
                    for (char m : chars) {
                        if (m == 32) {
                            Toast.makeText(context, "띄어쓰기는 포함 하실수 없습니다", Toast.LENGTH_LONG).show();
                            getDialogForAddWord(context, callBackInAddWord).show();
                            return;
                        }
                        if (!((m >= 97 && m <= 122) || (m >= 65 && m <= 90))) { // 97~ 122, 65 ~ 90
                            Toast.makeText(context, "영어를 제대로 적어주세요", Toast.LENGTH_SHORT).show();
                            getDialogForAddWord(context, callBackInAddWord).show();
                            return;
                        }
                        if (et_english.getText().toString().trim().equals(et_korean.getText().toString().trim())) {
                            Toast.makeText(context, "영어 단어와 단어의 뜻을 똑같이\n적을 수 없습니다.", Toast.LENGTH_LONG).show();
                            getDialogForAddWord(context, callBackInAddWord).show();
                            return;
                        }

                    }
                    HashMap<CallBackKey, String> map = new HashMap<>();
                    map.put(CallBackKey.ENGLISH, et_english.getText().toString().trim());
                    map.put(CallBackKey.KOREAN, et_korean.getText().toString().trim());
                    callBackInAddWord.callBack(map);
                } else {
                    Toast.makeText(context, "항목을 제대로 적어주세요", Toast.LENGTH_SHORT).show();
                    getDialogForAddWord(context, callBackInAddWord).show();
                }
            }
        });

        builder.setView(view);
        return builder.create();
    }

    public AlertDialog getDialogForAddList(Context context, CallBackInAddWord callBackInAddWord) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_add_word_list, null);
        EditText et_listName = view.findViewById(R.id.et_listName);
        builder.setView(view);

        builder.setPositiveButton("추가", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!et_listName.getText().toString().isEmpty()) {
                    HashMap<CallBackKey, String> map = new HashMap<>();
                    map.put(CallBackKey.LIST_NAME, et_listName.getText().toString().trim());
                    callBackInAddWord.callBack(map);
                } else {
                    Toast.makeText(context, "항목을 적어주세요!", Toast.LENGTH_SHORT).show();
                    getDialogForAddList(context, callBackInAddWord).show();
                }
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        return builder.create();
    }

    public AlertDialog dialogForDelete(Context context, CallBackInAddWordForIndex callBack) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("삭제");
        builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                callBack.callBack(true, -1);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        return builder.create();
    }

    public AlertDialog getDialogForNotice(Context context, CallBackCheck callBackCheck) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_notice, null);
        CheckBox checkBox = view.findViewById(R.id.checkBox);
        builder.setView(view);
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(checkBox.isChecked()) {
                    callBackCheck.callBack(true,0);
                    return;
                }
                callBackCheck.callBack(false,0);
            }
        });
        return builder.create();
    }
}
