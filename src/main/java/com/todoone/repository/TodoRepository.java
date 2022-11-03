package com.todoone.repository;

import com.todoone.domain.Todo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper

public interface TodoRepository {
    @Insert("insert into todo_table values(0, #{user_id}, #{couple_id}, #{content}, #{date}, #{completeStatus}, #{together})")
    void addTodo(Todo todo);

    @Select("select * from todo_table\n"+
    "where couple_id = #{couple_id}")
    List<Todo> getTodoByCoupleId(int couple_id);

    @Delete("delete from todo_table where todo_id = #{todo_id}")
    void deleteTodo(int todo_id);

    @Select("select complete_status from todo_table where todo_id = #{todo_id}")
    Integer findCs(int todo_id);

    @Update("update todo_table set complete_status=#{complete_status} where todo_id = #{todo_id}")
    void updateCompleteStatus(int todo_id, int complete_status);
}
