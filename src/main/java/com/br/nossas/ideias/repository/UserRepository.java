package com.br.nossas.ideias.repository;

import com.br.nossas.ideias.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
    User findByPassword(String password);
}