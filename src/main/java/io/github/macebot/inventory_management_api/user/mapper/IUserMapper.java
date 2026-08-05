package io.github.macebot.inventory_management_api.user.mapper;

import io.github.macebot.inventory_management_api.user.dto.CreateUserDto;
import io.github.macebot.inventory_management_api.user.dto.ViewUserDto;
import io.github.macebot.inventory_management_api.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    User createDtoToUser(CreateUserDto createUserDto);
    ViewUserDto userToViewDto(User user);
    List<ViewUserDto> userListToViewDtoList(List<User> users);

}
