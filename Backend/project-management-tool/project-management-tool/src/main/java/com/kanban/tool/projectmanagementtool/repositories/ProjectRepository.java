package com.kanban.tool.projectmanagementtool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kanban.tool.projectmanagementtool.model.Project;


@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {

  //find project by id
  Project findByProjectIdentifier(String projectId);


  //Iterable function to find all projects in the database
  @Override
  Iterable<Project> findAll();

  Iterable<Project> findAllByProjectLeader(String username);

}
