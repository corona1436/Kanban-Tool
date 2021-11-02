package com.kanban.tool.projectmanagementtool.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kanban.tool.projectmanagementtool.model.Backlog;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog,Long> {

    Backlog findByProjectIdentifier(String identifier);
}
