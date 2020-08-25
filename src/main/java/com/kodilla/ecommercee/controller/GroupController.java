package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.group.GroupDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/group")
public class GroupController {

	@RequestMapping(method = RequestMethod.GET, value = "getGroups")
	public List<GroupDto> getGroups() {
		return getTempList();
	}

	@RequestMapping(method = RequestMethod.GET, value = "getGroup")
	public GroupDto getGroup(@RequestParam long groupId) {
		return getTempList().get((int) groupId);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "deleteGroup")
	public boolean deleteGroup(@RequestParam Long groupId) {
		return true;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "updateGroup", consumes = APPLICATION_JSON_VALUE)
	public GroupDto updateGroup(@RequestBody GroupDto groupDto) {
		return groupDto;
	}

	@RequestMapping(method = RequestMethod.POST, value = "createGroup", consumes = APPLICATION_JSON_VALUE)
	public GroupDto createGroup(@RequestBody GroupDto groupDto) {
		return groupDto;
	}

	public List<GroupDto> getTempList() {
		return IntStream.range(0, 5)
				.mapToObj(productGroup -> new GroupDto((long) productGroup, "Product group:" + productGroup))
				.collect(Collectors.toList());
	}
}
