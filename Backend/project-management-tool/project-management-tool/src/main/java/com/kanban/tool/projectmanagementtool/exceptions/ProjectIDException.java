package com.kanban.tool.projectmanagementtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//ProjectID Exception class to throw exceptions related to ProjectID
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectIDException extends RuntimeException{

	private static final long serialVersionUID = -5098750332311905654L;

	public ProjectIDException(String message) {
        super(message);
    }
}
