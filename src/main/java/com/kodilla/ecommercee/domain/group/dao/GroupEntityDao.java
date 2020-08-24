package com.kodilla.ecommercee.domain.group.dao;

import com.kodilla.ecommercee.domain.group.Group;
import org.springframework.data.repository.CrudRepository;

public interface GroupDao extends CrudRepository<Group, Long> {
}
