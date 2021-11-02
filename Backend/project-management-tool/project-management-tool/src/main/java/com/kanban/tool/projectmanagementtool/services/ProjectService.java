package com.kanban.tool.projectmanagementtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kanban.tool.projectmanagementtool.exceptions.ProjectIDException;
import com.kanban.tool.projectmanagementtool.exceptions.ProjectNotFoundException;
import com.kanban.tool.projectmanagementtool.model.Backlog;
import com.kanban.tool.projectmanagementtool.model.Project;
import com.kanban.tool.projectmanagementtool.model.User;
import com.kanban.tool.projectmanagementtool.repositories.BacklogRepository;
import com.kanban.tool.projectmanagementtool.repositories.ProjectRepository;
import com.kanban.tool.projectmanagementtool.repositories.UserRepository;


@Service
public class ProjectService {

  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private BacklogRepository backlogRepository;

  @Autowired
  private UserRepository userRepository;


  //saveOrUpdate project
  public Project saveOrUpdateProject(Project project, String username){

      if(project.getId() != null){
          Project existingProject = projectRepository.findByProjectIdentifier(project.getProjectIdentifier());
          if(existingProject !=null &&(!existingProject.getProjectLeader().equals(username))){
              throw new ProjectNotFoundException("Project not found in your account");
          }else if(existingProject == null){
              throw new ProjectNotFoundException("Project with ID: '"+project.getProjectIdentifier()+"' cannot be updated because it doesn't exist");
          }
      }

      //checking if project already exists
      try{

          User user = userRepository.findByUsername(username);
          project.setUser(user);
          project.setProjectLeader(user.getUsername());
          project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

          if(project.getId()==null){
              Backlog backlog = new Backlog();
              project.setBacklog(backlog);
              backlog.setProject(project);
              backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
          }

          if(project.getId()!=null){
              project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
          }

          return projectRepository.save(project);
      }catch (Exception e){
          throw new ProjectIDException("ProjectID " + project.getProjectIdentifier().toUpperCase() + " already exists");
      }
  }

  //finding project by projectId
  public Project findProjectByIdentifier(String projectId, String username){

      Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

      //if no project with projectID was found
      if(project==null){
          throw new ProjectIDException("ProjectID " + projectId + " doesn't exists");
      }

      if(!project.getProjectLeader().equals(username)){
          throw new ProjectNotFoundException("Project not found in your account");
      }


      return project;
  }

  //finding all projects
  public Iterable<Project> findAllProjects(String username){
      return projectRepository.findAllByProjectLeader(username);
  }

  //deleting project by projectId
  public void deleteProjectByIdentifier(String projectId, String username){

      //checking is project exists in the database
      projectRepository.delete(findProjectByIdentifier(projectId, username));
  }
}
