package com.example.todoapp.model.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.todoapp.model.entity.History;

public interface HistoryRepository extends JpaRepository<History, Long>{
    
	@Query(value="SELECT * FROM history h where h.id = :id ",nativeQuery=true)
	List<History> findHistory(@Param("id") long id);
	
	@Query(value="INSERT INTO history (id, todo_contents) " +
			"VALUES (?1, ?2)", nativeQuery=true)
	@Transactional
	@Modifying
	void persist (@Param("id") long id,
			      @Param("todoContents") String todoContents
			      );
	@Transactional
	List<History> deleteByTodoContents(String todoContents);		      
	
}
