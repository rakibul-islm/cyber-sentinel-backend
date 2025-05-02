package com.bd.cybersentinel.repository;
import com.bd.cybersentinel.entity.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends ServiceRepository<User> {
	User findByUsername(String username);
	User findByEmail(String email);
}
