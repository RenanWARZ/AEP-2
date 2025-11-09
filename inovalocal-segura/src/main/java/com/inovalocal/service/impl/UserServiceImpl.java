package com.inovalocal.service.impl;

import com.inovalocal.model.User;
import com.inovalocal.repository.UserRepository;
import com.inovalocal.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User save(User user) { return repo.save(user); }

    @Override
    public List<User> findAll() { return repo.findAll(); }

    @Override
    public Optional<User> findById(Long id) { return repo.findById(id); }

    @Override
    public void delete(Long id) { repo.deleteById(id); }
}
