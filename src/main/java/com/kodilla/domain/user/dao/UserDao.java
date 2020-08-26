package com.kodilla.domain.user.dao;

import com.kodilla.domain.user.UserDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository

public interface UserDao extends CrudRepository <UserDto, Long> {
}
