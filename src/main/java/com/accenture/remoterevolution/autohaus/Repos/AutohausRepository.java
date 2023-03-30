package com.accenture.remoterevolution.autohaus.Repos;

import com.accenture.remoterevolution.autohaus.Entities.Autohaus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutohausRepository extends JpaRepository<Autohaus, Long> {
    Optional<Autohaus> findByGuid(String guid);
}
