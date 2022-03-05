package com.wons.wordmanager2.setting;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Setting {
    @PrimaryKey
    @NonNull
    public int settingCode;
    public int settingValue;

    public Setting(int settingCode, int settingValue) {
        this.settingCode = settingCode;
        this.settingValue =settingValue;
    }
}
