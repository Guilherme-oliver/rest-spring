package com.oliveira.erudio.repositories;

import com.oliveira.erudio.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User WHERE u.userName =:userName")
    User findByUsername(@Param("userName") String userName);

}