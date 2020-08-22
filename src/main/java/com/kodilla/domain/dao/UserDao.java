package com.kodilla.domain.dao;

import com.kodilla.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository

public interface UserDao extends CrudRepository <User, Long> {
}
