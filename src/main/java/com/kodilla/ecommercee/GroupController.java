package com.kodilla.ecommercee;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/group")
public class GroupController {

    @RequestMapping(method = RequestMethod.GET, value = "getGroups")
    public List<ProductGroupDto> getGroups() {
        return getTempList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getGroup")
    public ProductGroupDto getGroup(@RequestParam long groupId) {
        return getTempList().get((int) groupId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteGroup")
    public void deleteGroup(@RequestParam Long groupId) {

    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateGroup", consumes = APPLICATION_JSON_VALUE)
    public ProductGroupDto updateGroup(@RequestBody ProductGroupDto productGroupDto) {
        return productGroupDto;
    }

    @RequestMapping(method = RequestMethod.POST, value = "createGroup", consumes = APPLICATION_JSON_VALUE)
    public ProductGroupDto createGroup(@RequestBody ProductGroupDto productGroupDto) {
        return productGroupDto;
    }

    public List<ProductGroupDto> getTempList() {
        final List<ProductGroupDto> productGroupDtoList = IntStream.range(0, 5)
                .mapToObj(productGroup -> new ProductGroupDto((long) productGroup, "Product group:" + productGroup))
                .collect(Collectors.toList());
        return productGroupDtoList;
    }
}
