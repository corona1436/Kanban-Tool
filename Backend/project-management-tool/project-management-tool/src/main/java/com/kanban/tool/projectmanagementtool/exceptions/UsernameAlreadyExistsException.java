package com.kanban.tool.projectmanagementtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 3232188963949966773L;

	public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
