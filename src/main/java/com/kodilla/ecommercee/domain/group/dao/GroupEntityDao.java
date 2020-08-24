package com.kodilla.ecommercee.domain.group.dao;

import com.kodilla.ecommercee.domain.group.GroupEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface GroupEntityDao extends CrudRepository<GroupEntity, Long> {
}
