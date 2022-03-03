package com.wons.wordmanager2.add_word.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wons.wordmanager2.R;
import com.wons.wordmanager2.add_word.value.Word;

import java.util.ArrayList;

public class WordAdapter extends BaseAdapter {
    private ArrayList<Word> words;

    WordAdapter(/*콜백받기*/) {
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
        ImageView btn_sound = view.findViewById(R.id.btn_sound);
        ImageView btn_delete = view.findViewById(R.id.btn_delete);
        TextView tv_correctPercentage = view.findViewById(R.id.tv_correctPercentage);
        tv_english.setText(words.get(i).english);
        tv_korean.setText(words.get(i).korean);
        if (words.get(i).getPercentage_of_correct() != 0) {
            int percent = words.get(i).getPercentage_of_correct();
            if (percent >= 0 && percent <= 20) {
                tv_correctPercentage.setTextColor(Color.parseColor("#FF5722"));
            } else if (percent > 20 && percent <= 40) {
                tv_correctPercentage.setTextColor(Color.parseColor("#FF9800"));
            } else if (percent > 40 && percent <= 60) {
                tv_correctPercentage.setTextColor(Color.parseColor("#1D1D1F"));
            } else if (percent > 60 && percent <= 80) {
                tv_correctPercentage.setTextColor(Color.parseColor("#00BCD4"));
            } else {
                tv_correctPercentage.setTextColor(Color.parseColor("#3F51B5"));
            }
        }
        //todo 온클릭 리스너 달아주기
        tv_correctPercentage.setText(String.valueOf(words.get(i).getPercentage_of_correct()));
        return view;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }
}