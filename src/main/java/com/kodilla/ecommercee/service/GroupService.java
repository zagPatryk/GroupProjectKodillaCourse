package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.group.Group;
import com.kodilla.ecommercee.domain.group.dao.GroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    @Autowired
    private GroupDao groupDao;

    public List<Group> getAllGroups() {
        return groupDao.findAll();
    }

    public Optional<Group> getGroup(final long id) {
        return groupDao.findById(id);
    }

    public Group saveGroup(final Group group) {
        return groupDao.save(group);
    }

    public void deleteGroup(long id) {
        groupDao.deleteById(id);
    }
}
