package com.wook.user.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wook.user.model.db.PhoneDb;

@Repository
public interface PhoneRepository extends CrudRepository<PhoneDb, Long> {

	@Transactional
	@Modifying
	@Query(value = "delete from phone_db p  where p.user_id = :id_input ", nativeQuery = true)
	int deletePhoneByUserId(@Param("id_input") Long id);
	
}
