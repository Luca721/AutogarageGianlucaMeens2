package com.luca.AutogarageGianlucaMeens.Auto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AutoRepository extends JpaRepository<Auto, Long> {
}
