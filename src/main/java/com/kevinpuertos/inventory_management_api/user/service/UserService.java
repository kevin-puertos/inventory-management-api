package com.kevinpuertos.inventory_management_api.user.service;

import com.kevinpuertos.inventory_management_api.exceptions.ResourceAlreadyExistException;
import com.kevinpuertos.inventory_management_api.exceptions.ResourceNotFoundException;
import com.kevinpuertos.inventory_management_api.user.UserRole;
import com.kevinpuertos.inventory_management_api.user.dto.CreateUserDto;
import com.kevinpuertos.inventory_management_api.user.dto.UpdateUserDto;
import com.kevinpuertos.inventory_management_api.user.dto.ViewUserDto;
import com.kevinpuertos.inventory_management_api.user.entity.User;
import com.kevinpuertos.inventory_management_api.user.mapper.IUserMapper;
import com.kevinpuertos.inventory_management_api.user.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserService implements  IUserService{

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User findUserByName(String name) {
       User user = userRepository.findByUsername(name);
       if(user == null){
           throw new ResourceNotFoundException("User not found");
       }
       return user;
    }

    @Override
    public void createUser(CreateUserDto user) {
        User userAux = this.findUserByName(user.userName());
        if(userAux == null){
            userRepository.save(userMapper.createDtoToUser(user));
        }
        throw new ResourceNotFoundException("Username already exists");
    }

    @Override
    public ViewUserDto viewUser(Long id) {
        return userMapper.userToViewDto(findUserById(id));
    }

    @Override
    public List<ViewUserDto> viewAllUsers() {
        return userMapper.userListToViewDtoList(userRepository.findAll());
    }

    @Override
    public ViewUserDto updateUser(UpdateUserDto updateUser,Long id) {
        User user = this.findUserById(id);

        if(updateUser.userName() != null){
           User userAux = this.findUserByName(updateUser.userName());
           if(userAux != null){
            throw new ResourceAlreadyExistException("Product name already exist");
           }
           user.setUsername(updateUser.userName());
        }

        if(updateUser.userRole() != null){
            user.setRol(UserRole.valueOf(updateUser.userRole()));
        }

        if(updateUser.password() != null){
            user.setPassword(updateUser.password());
        }

        userRepository.save(user);

        return userMapper.userToViewDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = this.findUserById(id);
        userRepository.delete(user);
    }
}
