package com.wons.wordmanager2.game_word.game_value;

public class GameInfo {
    public String gameName;
    public String gameInfo;
    public int gamePic;
    public GameEnum gameCode;

    public GameInfo(String gameName, String gameInfo, int gamePic, GameEnum gameCode) {
        this.gameName = gameName;
        this.gameInfo = gameInfo;
        this.gamePic = gamePic;
        this.gameCode = gameCode;
    }
}
