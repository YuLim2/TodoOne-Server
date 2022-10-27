package com.todoone.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Couple {
    private int couple_id;
    private int user_id1;
    private int user_id2;

    public Couple(int couple_id, int user_id1, int user_id2){
        this.couple_id = couple_id;
        this.user_id1 = user_id1;
        this.user_id2 = user_id2;
    }
}
