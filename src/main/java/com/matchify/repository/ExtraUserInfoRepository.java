package com.matchify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matchify.entity.ExtraUserInfo;

@Repository
public interface ExtraUserInfoRepository extends JpaRepository<ExtraUserInfo, Integer> {

    
}
