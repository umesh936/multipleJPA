package com.umesh.learning.multipleJPA.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.umesh.learning.multipleJPA.model.user.User;

@Repository
@Transactional(value="userTransactionManager")
public interface UserRepository extends JpaRepository<User, Integer> {
	
	List<User> findByEmailAndPassword(String email, String password);
}
