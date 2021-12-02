package com.example.gradebookbackend.repositories;


import com.example.gradebookbackend.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username=:username")
    public User getUserByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u WHERE u.group_id = :group_id")
    List<User> findByGroup_id(@Param("group_id") Integer group_id);

    @Query("SELECT u FROM User u WHERE u.group_id <> 1 ORDER BY u.surname")
    List<User> findStudents();
}
