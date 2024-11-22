package com.ecolab.ecolab.repository;

import com.ecolab.ecolab.entity.UseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UseRepository extends JpaRepository<UseEntity, Long> {
}
