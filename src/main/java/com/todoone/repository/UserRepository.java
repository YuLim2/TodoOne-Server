package com.todoone.repository;

import com.todoone.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper
public interface UserRepository {
    @Insert("insert into user_table values(0, #{name}, #{email}, #{password}, #{birth}, #{gender}, #{allowNotification}, #{nickname})")
    void addUser(User user);

    // email 중복 검사
    @Select("select * from user_table where email = #{email}")
    Map<String, Object> findById(String email);

    // password 꺼내서 비교하려고
    @Select("select password from user_table where email = #{email}")
    String pwCheck(String email);
}