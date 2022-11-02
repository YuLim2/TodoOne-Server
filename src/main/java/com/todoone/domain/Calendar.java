package com.todoone.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Calendar {

    private int calendar_id;
    private int user_id;
    private int couple_id;
    private String date_start;
    private String date_end;
    private String title;
    private int together;

    public Calendar(int calendar_id, int user_id, int couple_id, String date_start, String date_end, String title, int together) {
        this.calendar_id = calendar_id;
        this.user_id = user_id;
        this.couple_id = couple_id;
        this.date_start = date_start;
        this.date_end = date_end;
        this.title = title;
        this.together = together;
    }
}
