package com.clubjuegos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clubjuegos.models.LenguageEntity;

public interface LenguageEntityRepository extends JpaRepository<LenguageEntity, Integer> {
	
	LenguageEntity findByKeyAndLocale(String key, String locale);

}
