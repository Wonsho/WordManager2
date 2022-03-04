package com.wons.wordmanager2.add_word.adapter;

public enum WordInfoEnum {
    ILBAN("일반"),
    MYUNGSA("명사"),
    DONGSA("동사"),
    JODONGSA("조동사"),
    HYUNGYOUNGSA("형용사"),
    BUSA("부사");

    public String wordInfoKorean;
    WordInfoEnum(String str) {
        this.wordInfoKorean = str;
    }
}
