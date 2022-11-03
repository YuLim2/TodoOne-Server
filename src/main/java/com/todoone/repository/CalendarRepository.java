package com.todoone.repository;

import com.todoone.domain.Calendar;
import com.todoone.domain.Todo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper

public interface CalendarRepository {
    @Insert("insert into calendar_table values(0, #{user_id}, #{couple_id}, #{date_start}, #{date_end}, #{title}, #{together})")
    void addCalendar(Calendar calendar);

    @Select("select * from calendar_table\n"+
            "where couple_id = #{couple_id}")
    List<Todo> getCalendarByCoupleId(int couple_id);

    @Delete("delete from todo_table where todo_id = #{todo_id}")
    void deleteCalendar(int calendar_id);
}
