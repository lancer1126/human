package com.lance.modules.system.service.impl;

import com.lance.exception.EntityNotFoundException;
import com.lance.modules.system.domain.User;
import com.lance.modules.system.repository.UserRepository;
import com.lance.modules.system.service.UserService;
import com.lance.modules.system.service.dto.UserDto;
import com.lance.modules.system.service.mapstruct.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto findByName(String userName) {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new EntityNotFoundException(User.class, "name", userName);
        } else {
            return userMapper.toDto(user);
        }
    }
}
