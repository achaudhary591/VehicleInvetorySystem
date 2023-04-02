package com.lld.automobile.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lld.automobile.db.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
