package com.wons.wordmanager2.add_word.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wons.wordmanager2.R;
import com.wons.wordmanager2.add_word.diaog.CallBackInAddWordForBoolean;
import com.wons.wordmanager2.add_word.value.WordList;

import java.util.ArrayList;

public class WordListAdapter extends BaseAdapter {
    private ArrayList<WordList> wordLists;
    private CallBackInAddWordForBoolean callback;
    public WordListAdapter(CallBackInAddWordForBoolean callback) {
        this.callback = callback;
        wordLists = new ArrayList<>();
    }
    @Override
    public int getCount() {
        return wordLists.size();
    }

    @Override
    public WordList getItem(int i) {
        return wordLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            view = inflater.inflate(R.layout.list_view_word_folder, viewGroup, false);
        }
        TextView tv_listName = view.findViewById(R.id.tv_listName);
        TextView tv_wordCountInList = view.findViewById(R.id.tv_wordCount);
        ImageView btn_delete = view.findViewById(R.id.btn_delete);

        tv_listName.setText(wordLists.get(i).listName);
        tv_wordCountInList.setText(wordLists.get(i).getWordCountInList());
        if(Integer.parseInt(wordLists.get(i).getWordCountInList()) != 0) {
            view.findViewById(R.id.tv_infoList).setVisibility(View.GONE);
        }
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.callBack(true, i);
            }
        });
        return view;
    }

    public void setWordLists(ArrayList<WordList> wordLists) {
        this.wordLists = wordLists;
    }
}
