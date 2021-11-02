package com.kanban.tool.projectmanagementtool.repositories;

import org.springframework.data.repository.CrudRepository;

import com.kanban.tool.projectmanagementtool.model.User;

public interface UserRepository extends CrudRepository<User,Long> {

    User findByUsername(String username);
    User getById(Long id);

}
