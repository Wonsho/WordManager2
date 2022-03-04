package com.wons.wordmanager2.add_word.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wons.wordmanager2.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class WordInfoAdapter extends BaseAdapter {
    private ArrayList<WordInfoEnum> wordInfoes;
    private HashMap<String, ArrayList<String>> wordKoreans;
    WordInfoAdapter() {
        wordKoreans = new HashMap<>();
        wordInfoes = new ArrayList<>();
    }
    @Override
    public int getCount() {
        return wordInfoes.size();
    }

    @Override
    public Object getItem(int i) {
        return wordInfoes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            LayoutInflater inflater = LayoutInflater.from(view.getContext());
            view = inflater.inflate(R.layout.list_in_word_list, viewGroup, false);
        }
        TextView tv_wordType = view.findViewById(R.id.tv_wordType);
        TextView tv_wordInfo = view.findViewById(R.id.tv_wordInfo);
        tv_wordType.setText(wordInfoes.get(i).wordInfoKorean);
        ArrayList<String> words = wordKoreans.get(wordInfoes.get(i).wordInfoKorean);
        //TODO 파싱된 클린한 데이터 받기
        for(int j = 0 ; j < words.size() ; j++) {
            if(j == 0) {
                tv_wordInfo.setText("1. "+words.get(i));
            } else {
                tv_wordInfo.setText(tv_wordInfo.getText().toString()+"\n"+(j+1)+". "+words.get(j));
            }
        }
        return view;
    }
}
