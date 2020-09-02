package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.group.Group;
import com.kodilla.ecommercee.domain.group.GroupDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupMapper {
    public Group mapToGroup(final GroupDto groupDto) {
        return new Group(
                groupDto.idProductGroup,
                groupDto.nameProductGroup,
                new ArrayList<>());
    }

    public GroupDto mapToGroupDto(final Group group) {
        return new GroupDto(
                group.getId(),
                group.getName());
    }

    public List<GroupDto> mapToListGroupDto(final List<Group> groupList) {
        return groupList.stream()
                .map(g -> new GroupDto(g.getId(), g.getName()))
                .collect(Collectors.toList());
    }
}
