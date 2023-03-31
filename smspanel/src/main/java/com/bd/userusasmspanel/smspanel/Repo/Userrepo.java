package com.bd.userusasmspanel.smspanel.Repo;

import com.bd.userusasmspanel.smspanel.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Userrepo extends JpaRepository<User,Integer> {
    @Query("select u from User u where u.email=:email")
    public User getSmspanelByEmail(@Param("email")String email);
}
