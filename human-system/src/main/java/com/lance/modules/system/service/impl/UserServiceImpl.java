package com.lance.modules.system.service.impl;

import com.lance.exception.EntityNotFoundException;
import com.lance.modules.system.domain.User;
import com.lance.modules.system.repository.UserRepository;
import com.lance.modules.system.service.UserService;
import com.lance.modules.system.service.dto.UserDto;
import com.lance.modules.system.service.dto.UserQueryCriteria;
import com.lance.modules.system.service.mapstruct.UserMapper;
import com.lance.utils.PageUtil;
import com.lance.utils.QueryHelp;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Object queryAll(UserQueryCriteria criteria, Pageable pageable) {
        Page<User> page = userRepository.findAll((root, criteriaQuery, criteriaBuilder) ->
                QueryHelp.getPredicate(root, criteria, criteriaBuilder), pageable);
        return PageUtil.toPage(page.map(userMapper::toDto));
    }
}
