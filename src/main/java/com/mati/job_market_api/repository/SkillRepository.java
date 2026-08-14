package com.mati.job_market_api.repository;

import com.mati.job_market_api.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
}