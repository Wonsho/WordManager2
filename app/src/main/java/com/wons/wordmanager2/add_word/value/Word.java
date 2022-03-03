package com.wons.wordmanager2.add_word.value;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Word {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int wordId;
    public String english;
    public String korean;
    public int listCode;
    private String correctCount;
    private String testedCount;

    public Word() {}

    public void setCorrectCount(String correctCount) {
        this.correctCount = correctCount;
    }

    public void setTestedCount(String testedCount) {
        this.testedCount = testedCount;
    }

    public Word(String english, String korean, int listCode) {
        correctCount = "0";
        testedCount = "0";
        this.english = english;
        this.korean = korean;
        this.listCode = listCode;
    }

    public int getPercentage_of_correct() {
        double correctTimes = Double.parseDouble(correctCount);
        double testedTimes = Double.parseDouble(testedCount);
        return ((int)(correctTimes/testedTimes * 100));
    }

    public void addCorrectCount() {
        correctCount = String.valueOf(Integer.parseInt(correctCount)+1);
    }

    public void addTestedCount() {
        testedCount = String.valueOf(Integer.parseInt(testedCount)+1);
    }

    public String getTestedCount() {
        return testedCount;
    }

    public String getCorrectCount() {
        return correctCount;
    }

}
