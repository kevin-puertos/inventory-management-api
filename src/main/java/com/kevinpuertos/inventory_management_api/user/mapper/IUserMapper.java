package com.kevinpuertos.inventory_management_api.user.mapper;

import com.kevinpuertos.inventory_management_api.user.dto.CreateUserDto;
import com.kevinpuertos.inventory_management_api.user.dto.ViewUserDto;
import com.kevinpuertos.inventory_management_api.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    User createDtoToUser(CreateUserDto createUserDto);
    ViewUserDto userToViewDto(User user);
    List<ViewUserDto> userListToViewDtoList(List<User> users);

}
