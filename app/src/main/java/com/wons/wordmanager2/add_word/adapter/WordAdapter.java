package com.wons.wordmanager2.add_word.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wons.wordmanager2.R;
import com.wons.wordmanager2.add_word.diaog.CallBackInAddWord;
import com.wons.wordmanager2.add_word.diaog.CallBackInAddWordForBoolean;
import com.wons.wordmanager2.add_word.diaog.CallBackInAddWordForString;
import com.wons.wordmanager2.add_word.value.Word;

import java.util.ArrayList;
import java.util.Locale;

public class WordAdapter extends BaseAdapter {
    private ArrayList<Word> words;
    private CallBackInAddWordForString callBackInAddWordForString;
    private CallBackInAddWordForBoolean callBackInAddWordForBoolean;

    public WordAdapter(CallBackInAddWordForString callBackInAddWordForString, CallBackInAddWordForBoolean callBackInAddWordForBoolean) {
        this.callBackInAddWordForBoolean = callBackInAddWordForBoolean;
        this.callBackInAddWordForString = callBackInAddWordForString;
        this.words = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return words.size();
    }

    @Override
    public Word getItem(int i) {
        return words.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.list_view_word, viewGroup, false);
        }
        TextView tv_english = view.findViewById(R.id.tv_english);
        TextView tv_korean = view.findViewById(R.id.tv_korean);
        TextView tv_mark = view.findViewById(R.id.tv_percentM);
        ImageView btn_sound = view.findViewById(R.id.btn_sound);
        ImageView btn_delete = view.findViewById(R.id.btn_delete);
        TextView tv_correctPercentage = view.findViewById(R.id.tv_correctPercentage);
        tv_english.setText(words.get(i).english);
        tv_korean.setText(words.get(i).korean);
        btn_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBackInAddWordForString.callBack(tv_english.getText().toString().trim());
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBackInAddWordForBoolean.callBack(true, i);
            }
        });
        if (Integer.parseInt(words.get(i).getTestedCount()) != 0) {
            int percent = words.get(i).getPercentage_of_correct();
            if (percent >= 0 && percent <= 10) {
                tv_correctPercentage.setTextColor(Color.parseColor("#B80000"));
            } else if (percent > 10 && percent <= 30) {
                tv_correctPercentage.setTextColor(Color.parseColor("#FF5722"));
            } else if (percent > 30 && percent <= 40) {
                tv_correctPercentage.setTextColor(Color.parseColor("#FF9800"));
            } else if (percent > 40 && percent <= 60) {
                tv_correctPercentage.setTextColor(Color.parseColor("#1D1D1F"));
            } else if (percent > 60 && percent <= 80) {
                tv_correctPercentage.setTextColor(Color.parseColor("#00BCD4"));
            } else {
                tv_correctPercentage.setTextColor(Color.parseColor("#3F51B5"));
            }
            tv_mark.setVisibility(View.VISIBLE);
            tv_correctPercentage.setText(String.valueOf(words.get(i).getPercentage_of_correct()));
        } else {
            tv_correctPercentage.setText("측정 불가");
            tv_mark.setVisibility(View.GONE);
        }
        //todo 온클릭 리스너 달아주기

        return view;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }
}
