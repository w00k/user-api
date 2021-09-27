package com.wook.user.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wook.user.model.db.UserDb;

@Repository
public interface UserRepository extends CrudRepository<UserDb, Long> {

	@Transactional
	@Modifying
	@Query(value = "update user_db u set u.last_login = :date_input where u.id = :id_input and u.token = :token_input", nativeQuery = true)
	int updateLastLogin(@Param("date_input") Date lasLogin, @Param("id_input") Long id,
			@Param("token_input") String token_input);

	@Transactional
	@Modifying
	@Query(value = "update user_db u set u.name = :name_input, u.email = :email_input, u.password = :password_input, is_active = :active_input,  update_date = :update_input where u.id = :id_input and u.token = :token_input", nativeQuery = true)
	int updateUser(@Param("name_input") String name, @Param("email_input") String email,
			@Param("password_input") String password, @Param("active_input") boolean active,
			@Param("update_input") Date updateDate, @Param("id_input") Long id, @Param("token_input") String token);
	
}
