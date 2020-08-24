package com.kodilla.ecommercee.domain.group.dao;

import com.kodilla.ecommercee.domain.group.GroupEntity;
import org.springframework.data.repository.CrudRepository;

public interface GroupEntityDao extends CrudRepository<GroupEntity, Long> {
}
