package com.wons.wordmanager2.game_word.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wons.wordmanager2.R;
import com.wons.wordmanager2.game_word.game_value.GameInfo;

import java.util.ArrayList;

public class GameListAdapter extends BaseAdapter {
    private ArrayList<GameInfo> gameInfoArr;

    public GameListAdapter() {
        this.gameInfoArr = new ArrayList<>();
    }
    @Override
    public int getCount() {
        return gameInfoArr.size();
    }

    @Override
    public Object getItem(int i) {
        return gameInfoArr.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_view_game, viewGroup, false);
        }
        String gameName = gameInfoArr.get(i).gameName;
        String gameExplain = gameInfoArr.get(i).gameInfo;
        int image = gameInfoArr.get(i).gamePic;

        ((TextView)view.findViewById(R.id.tv_gameName)).setText(gameName);
        ((TextView)view.findViewById(R.id.tv_explain)).setText(gameExplain);
        ((ImageView)view.findViewById(R.id.imv_gamePic)).setImageResource(image);
        return view;
    }

    public void setGame(ArrayList<GameInfo> game) {
        this.gameInfoArr = game;
    }
}
