package com.jjcompany.gatewayapi.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmailAndPassword(String email, String password);

}
