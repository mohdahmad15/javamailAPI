package com.mailservice.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mailservice.entities.UserEntityCl;
import com.mailservice.exceptionHandler.UserServiceException;
import com.mailservice.repositories.UserClRepository;
import com.mailservice.service.MailService;
import com.mailservice.service.UserMailService;

@Service
public class UserMailServiceImpl implements UserMailService {

    @Autowired
    private UserClRepository clRepository;

    @Autowired
    private MailService mailService;

    @Override
    public UserEntityCl createUser(UserEntityCl entityCl) {
        try {
            if (entityCl.getName() == null || entityCl.getName().isEmpty()) {
                throw new UserServiceException("Name cannot be empty.");
            }

            if (clRepository.existsByEmail(entityCl.getEmail())) {
                throw new UserServiceException("A user with this email already exists.");
            }

            UserEntityCl createdUser = clRepository.save(entityCl);

            mailService.sendEmail("ahmad436334@gmail.com", "New user created",
                    "A new user is created with Id: " + createdUser.getId() + " and name: " + createdUser.getName());

            return createdUser;
        } catch (Exception e) {
            throw new UserServiceException("Error occurred while creating user: " + e.getMessage(), e);
        }
    }

    @Override
    public UserEntityCl updateUser(Long id, UserEntityCl entityCl) {
        try {
            if (id == null || !clRepository.existsById(id)) {
                throw new UserServiceException("User with ID " + id + " does not exist.");
            }

            entityCl.setId(id);
            UserEntityCl updatedUser = clRepository.save(entityCl);

            mailService.sendEmail("ahmad436334@gmail.com", "User updated",
                    "User with Id: " + updatedUser.getId() + " has been updated.");

            return updatedUser;
        } catch (Exception e) {
            throw new UserServiceException("Error occurred while updating user: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteUser(Long id) {
        try {
            if (id == null || !clRepository.existsById(id)) {
                throw new UserServiceException("User with ID " + id + " does not exist.");
            }

            clRepository.deleteById(id);

            mailService.sendEmail("ahmad436334@gmail.com", "User deleted",
                    "User with ID: " + id + " has been deleted.");
        } catch (Exception e) {
            throw new UserServiceException("Error occurred while deleting user: " + e.getMessage(), e);
        }
    }
}
