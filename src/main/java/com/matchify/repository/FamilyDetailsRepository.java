package com.matchify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matchify.entity.FamilyDetails;

@Repository
public interface FamilyDetailsRepository extends JpaRepository<FamilyDetails, Integer> {

    //

}
