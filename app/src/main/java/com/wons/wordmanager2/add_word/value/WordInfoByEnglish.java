package com.wons.wordmanager2.add_word.value;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WordInfoByEnglish {
    @PrimaryKey
    @NonNull
    public String word_english;
    public String word_korean;
    private String correctCount;
    private String testedCount;

    public WordInfoByEnglish(String word_english, String word_korean) {
        this.word_english = word_english;

        if (word_korean.contains("형)") || word_korean.contains("명)") || word_korean.contains("부)")) {
            String[] arr1 = word_korean.split(",");
        } else {
            String[] arr2 = word_korean.split(",");
        }
        this.word_korean = word_korean;
        correctCount = "0";
        testedCount = "0";
    }
    public void setCorrectCount(String correctCount) {
        this.correctCount = correctCount;
    }

    public void setTestedCount(String testedCount) {
        this.testedCount = testedCount;
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