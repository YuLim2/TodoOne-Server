package com.todoone.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper

public interface CoupleRepository {
    @Insert("insert into couple_table values(0, #{user_id1}, #{user_id2})")
    void addCouple(int user_id1, int user_id2);

    // couple code가 있는지 검사
    @Select("select * from couple_table where user_id1 = #{user_id} or user_id2 = #{user_id}")
    Map<String, Object> codeCheck(Integer user_id);

    // 코드를 통한 아이디 검색
    @Select("select user_id from user_table where code = #{code}")
    Integer findUserID(int code);

}