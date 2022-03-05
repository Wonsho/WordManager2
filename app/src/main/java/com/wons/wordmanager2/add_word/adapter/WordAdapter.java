package com.wons.wordmanager2.add_word.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wons.wordmanager2.R;
import com.wons.wordmanager2.add_word.diaog.CallBackInAddWordForIndex;
import com.wons.wordmanager2.add_word.diaog.CallBackInAddWordForString;
import com.wons.wordmanager2.add_word.value.WordEnglish;

import java.util.ArrayList;
import java.util.HashMap;

public class WordAdapter extends BaseAdapter {
    private ArrayList<WordEnglish> wordEnglishes;
    private HashMap<String, String> wordsPercentage; // todo 키값 : word의 english
    private HashMap<String, String> koreanMap;
    private CallBackInAddWordForString callBackInAddWordForString;
    private CallBackInAddWordForIndex callBackInAddWordForBoolean;

    public WordAdapter(CallBackInAddWordForString callBackInAddWordForString, CallBackInAddWordForIndex callBackInAddWordForBoolean) {
        this.callBackInAddWordForBoolean = callBackInAddWordForBoolean;
        this.callBackInAddWordForString = callBackInAddWordForString;
        this.wordEnglishes = new ArrayList<>();
        this.wordsPercentage = new HashMap<>();
    }

    @Override
    public int getCount() {
        return wordEnglishes.size();
    }

    @Override
    public WordEnglish getItem(int i) {
        return wordEnglishes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("SetTextI18n")
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
        tv_english.setText(wordEnglishes.get(i).english);
        String korean = koreanMap.get(wordEnglishes.get(i).english);
        String[] koreans = korean.split(",");
        for(int j = 0 ; j<koreans.length ; j++) {
            if (j == 0) {
                tv_korean.setText("1. "+koreans[j].trim());
            } else {
                String str = tv_korean.getText().toString();
                tv_korean.setText(str+"\n"+String.valueOf(j+1)+". "+koreans[j].trim());
            }
        }
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
        int percentage = Integer.parseInt(wordsPercentage.get(wordEnglishes.get(i).english));
        if (percentage != 0) {
            if (percentage >= 0 && percentage <= 10) {
                tv_correctPercentage.setTextColor(Color.parseColor("#B80000"));
            } else if (percentage > 10 && percentage <= 30) {
                tv_correctPercentage.setTextColor(Color.parseColor("#FF5722"));
            } else if (percentage > 30 && percentage <= 40) {
                tv_correctPercentage.setTextColor(Color.parseColor("#FF9800"));
            } else if (percentage > 40 && percentage <= 60) {
                tv_correctPercentage.setTextColor(Color.parseColor("#1D1D1F"));
            } else if (percentage > 60 && percentage <= 80) {
                tv_correctPercentage.setTextColor(Color.parseColor("#00BCD4"));
            } else {
                tv_correctPercentage.setTextColor(Color.parseColor("#3F51B5"));
            }
            tv_mark.setVisibility(View.VISIBLE);
            tv_correctPercentage.setText(String.valueOf(percentage));
        } else {
            tv_correctPercentage.setText("측정 불가");
            tv_mark.setVisibility(View.GONE);
        }
        //todo 온클릭 리스너 달아주기

        return view;
    }

    public void setWords(ArrayList<WordEnglish> wordEnglishes, HashMap<String, String> wordsPercentageMap, HashMap<String, String> koreanMap) {
        this.wordEnglishes = wordEnglishes;
        this.wordsPercentage = wordsPercentageMap;
        this.koreanMap = koreanMap;
    }
}
