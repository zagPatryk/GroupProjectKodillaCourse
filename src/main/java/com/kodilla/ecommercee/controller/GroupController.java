package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.group.GroupDto;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/group")
public class GroupController {
	@Autowired
	private GroupService service;
	@Autowired
	private GroupMapper mapper;

	@RequestMapping(method = RequestMethod.GET, value = "getGroups")
	public List<GroupDto> getGroups() {
		return mapper.mapToListGroupDto(service.getAllGroups());
	}

	@RequestMapping(method = RequestMethod.GET, value = "getGroup")
	public GroupDto getGroup(@RequestParam long groupId) throws GroupNotFoundException {
		return mapper.mapToGroupDto(service.getGroup(groupId).orElseThrow(GroupNotFoundException::new));
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "deleteGroup")
	public boolean deleteGroup(@RequestParam Long groupId) {
		service.deleteGroup(groupId);
		return true;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "updateGroup", consumes = APPLICATION_JSON_VALUE)
	public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
		return mapper.mapToGroupDto(service.saveGroup(mapper.mapToGroup(groupDto)));
	}

	@RequestMapping(method = RequestMethod.POST, value = "createGroup", consumes = APPLICATION_JSON_VALUE)
	public void createGroup(@RequestBody GroupDto groupDto) {
		service.saveGroup(mapper.mapToGroup(groupDto));
	}
}
