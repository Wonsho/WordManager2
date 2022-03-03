package com.wons.wordmanager2.game_word;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wons.wordmanager2.R;
import com.wons.wordmanager2.databinding.ActivityGameListBinding;
import com.wons.wordmanager2.game_word.adapter.GameListAdapter;
import com.wons.wordmanager2.game_word.game_value.GameEnum;
import com.wons.wordmanager2.game_word.game_value.GameInfo;

import java.util.ArrayList;

public class GameListActivity extends AppCompatActivity {
    private ActivityGameListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setView();
        setGameInfo();

    }

    private void setGameInfo() {
        ArrayList<GameInfo> gameList = new ArrayList<GameInfo>();
        gameList.add(new GameInfo("Hang Man", "행맨게임입니다\n컴퓨터가 생각하는 단어를\n맞춰보세요!", R.mipmap.ic_launcher, GameEnum.HANG_MAN));
        //todo 게임 추가

        ((GameListAdapter)binding.lv.getAdapter()).setGame(gameList);
        setView();
    }


    private void setView() {
        if(binding.lv.getAdapter() == null) {
            binding.lv.setAdapter(new GameListAdapter());
        }
        ((GameListAdapter)binding.lv.getAdapter()).notifyDataSetChanged();
    }
}