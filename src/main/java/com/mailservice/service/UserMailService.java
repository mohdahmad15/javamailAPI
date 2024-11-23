package com.mailservice.service;


import com.mailservice.entities.UserEntityCl;
import com.mailservice.exceptionHandler.UserServiceException;

import jakarta.validation.Valid;

public interface UserMailService {

	//User creation
	public UserEntityCl createUser(@Valid UserEntityCl entityCl) throws UserServiceException;
	
	//update user
	public UserEntityCl updateUser(Long id, @Valid UserEntityCl entityCl) throws UserServiceException;
	
	//user deletion
	public void deleteUser(Long id) throws UserServiceException;
}
