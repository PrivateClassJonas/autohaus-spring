package com.accenture.remoterevolution.autohaus.Repos;

import com.accenture.remoterevolution.autohaus.Entities.Kfz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KfzRepository extends JpaRepository<Kfz, Long> {
    Optional<Kfz> findByGuid(String guid);
}
