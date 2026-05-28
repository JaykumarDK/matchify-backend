package com.matchify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matchify.entity.PersonalInfo;

@Repository
public interface PersonalInfoRepository extends JpaRepository<PersonalInfo, Integer> {
	


}
