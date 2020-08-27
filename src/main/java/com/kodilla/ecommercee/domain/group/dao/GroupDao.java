package com.kodilla.ecommercee.domain.group.dao;

import com.kodilla.ecommercee.domain.group.Group;
import com.kodilla.ecommercee.domain.order.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface GroupDao extends CrudRepository<Group, Long> {
}
