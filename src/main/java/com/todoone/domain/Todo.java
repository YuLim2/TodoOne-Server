package com.todoone.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Todo {

    private int todo_id;
    private int user_id;
    private int couple_id;
    private String content;
    private String date;
    private int completeStatus;
    private int together;

    public Todo(int todo_id, int user_id, int couple_id, String content, String date, int completeStatus, int together) {
        this.todo_id = todo_id;
        this.user_id = user_id;
        this.couple_id = couple_id;
        this.content = content;
        this.date = date;
        this.completeStatus = completeStatus;
        this.together = together;
    }
}
