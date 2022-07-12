package com.example.todoapp.model.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.todoapp.model.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{
	
	@Query(value="SELECT * FROM contents c where c.todo_contents = :todoContents ",nativeQuery=true)
	List<Todo> findContents(@Param("todoContents") String todoContents);
	@Transactional
	List<Todo> deleteByTodoContents(String todoContents);
}
