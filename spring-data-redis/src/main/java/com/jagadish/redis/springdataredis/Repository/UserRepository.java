package com.jagadish.redis.springdataredis.Repository;

import com.jagadish.redis.springdataredis.Model.User;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

//This will tell the Spring to say that this will be used as Orm
@Repository
public class UserRepository {

    private RedisTemplate<String, User> redisTemplate;
    private HashOperations hashOperations;

    public UserRepository(RedisTemplate<String, User> redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }


    public void save(User user) {
        hashOperations.put("USER", user.getId(), user);
    }


    public Map<String, User> findAll() {
        return hashOperations.entries("USER");
    }


    public User findById(String id) {
        return (User)hashOperations.get("USER", id);
    }


    public void update(User user) {
        save(user);
    }


    public void delete(String id) {
        hashOperations.delete("USER", id);
    }
}
