package com.example.todo.mapper;

import com.example.todo.entity.Todo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TodoMapper {

    @Select("SELECT * FROM todos ORDER BY created_at DESC")
    List<Todo> findAll();

    @Select("SELECT * FROM todos WHERE id = #{id}")
    Todo findById(Integer id);

    @Insert("INSERT INTO todos(title, completed) VALUES(#{title}, #{completed})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Todo todo);

    @Update("UPDATE todos SET title = #{title}, completed = #{completed} WHERE id = #{id}")
    int update(Todo todo);

    @Delete("DELETE FROM todos WHERE id = #{id}")
    int deleteById(Integer id);
}