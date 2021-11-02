package com.kanban.tool.projectmanagementtool.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kanban.tool.projectmanagementtool.model.ProjectTask;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask,Long> {

    List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);

    ProjectTask findByProjectSequence(String sequence);

}
