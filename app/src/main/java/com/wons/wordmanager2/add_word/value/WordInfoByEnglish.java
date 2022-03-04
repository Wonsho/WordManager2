package com.wons.wordmanager2.add_word.value;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WordInfoByEnglish {
    @PrimaryKey
    @NonNull
    public String word_english;
    public String word_korean_ILBAN;
    public String word_korean_MYUNGSA;
    public String word_korean_DONGSA;
    public String word_korean_BUSA;
    public String word_korean_JODONGSA;
    public String word_korean_HYNGYUNGSA;

    private String correctCount;
    private String testedCount;

    public WordInfoByEnglish(String word_english, String word_korean_ILBAN , String word_korean_DONGSA, String word_korean_BUSA, String word_korean_MYUNGSA, String word_korean_HYNGYUNGSA, String word_korean_JODONGSA) {
        this.word_korean_ILBAN = word_korean_ILBAN;
        this.word_korean_MYUNGSA = word_korean_MYUNGSA;
        this.word_korean_HYNGYUNGSA = word_korean_HYNGYUNGSA;
        this.word_korean_BUSA = word_korean_BUSA;
        this.word_korean_DONGSA = word_korean_DONGSA;
        this.word_korean_JODONGSA = word_korean_JODONGSA;
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
