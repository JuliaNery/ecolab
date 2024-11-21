package com.ecolab.ecolab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UseRepository extends JpaRepository<UseRepository, Long> {
}
