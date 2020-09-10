package com.kodilla.ecommercee.domain.user.dao;

import com.kodilla.ecommercee.domain.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

    @Override
    Optional<User> findById(Long id);

    @Override
    User save (User user);

    @Override
    void deleteById(Long id);

}

