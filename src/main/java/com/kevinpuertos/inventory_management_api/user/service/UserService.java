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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements  IUserService{

    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;
    private final IUserMapper userMapper;

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User findUserByName(String name) {
       User user = userRepository.findByUsername(name)
               .orElseThrow(
                       () -> new ResourceNotFoundException("User not found")
                       );

       return user;
    }

    @Override
    public void createUser(CreateUserDto user) {

        if(! userRepository.findByUsername(user.username()).isPresent()){
            User user1 = userMapper.createDtoToUser(user);
            user1.setPassword(passwordEncoder.encode(user.password()));
            userRepository.save(user1);
            return;
        }
        throw new ResourceAlreadyExistException("Username already exists");
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

        if(updateUser.username() != null){
           User userAux = this.findUserByName(updateUser.username());
           if(userAux != null){
            throw new ResourceAlreadyExistException("Product name already exist");
           }
           user.setUsername(updateUser.username());
        }

        if(updateUser.rol() != null){
            user.setRol(UserRole.valueOf(updateUser.rol()));
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
