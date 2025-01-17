package com.example.DynamicEmployeeManagementSystem.service;

import com.example.DynamicEmployeeManagementSystem.error.AlreadyRegistered;
import com.example.DynamicEmployeeManagementSystem.model.User;
import com.example.DynamicEmployeeManagementSystem.repository.UserRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisTemplate<String, Object> redisTemplate;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RedisTemplate<String, Object> redisTemplate) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.redisTemplate = redisTemplate;
    }

    public User registerUser(User user) {

        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new AlreadyRegistered("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // System.out.println(user);
        Duration ttl = Duration.ofMinutes(5);
        redisTemplate.opsForValue().set(user.getUsername(), user, ttl);
        return userRepository.save(user);
    }

    public User login(String userName) {

        User user = (User) redisTemplate.opsForValue().get(userName);

        if (user != null) {
            System.out.println("User retrieved from Redis cache");
            //  redisTemplate.delete(userName);
            return user;
        }

        // If not in cache, query the database
        user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new NullPointerException("User not found");
        }

        // Cache the user in Redis
        Duration ttl = Duration.ofMinutes(5);
        redisTemplate.opsForValue().set(userName, user, ttl);

        System.out.println("User retrieved from database and cached in Redis");
        return user;
    }
}
