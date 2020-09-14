package com.kodilla.ecommercee.mapper;


import com.kodilla.ecommercee.domain.user.User;
import com.kodilla.ecommercee.domain.user.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getUsername(),
                userDto.getStatus(),
                userDto.getUserKey());
    }

    public UserDto mapToUserDto (final User user){
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getStatus(),
                user.getUserKey());
    }

    public List<UserDto> mapToUserDtoList(final List <User> users){
        return users.stream()
                .map ( u -> new UserDto(u.getId(),u.getUsername(), u.getStatus(), u.getUserKey()))
                .collect(Collectors.toList());

    }
}