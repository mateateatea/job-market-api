package com.mati.job_market_api.repository;

import com.mati.job_market_api.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.mati.job_market_api.model.SkillStat;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Integer> {

    @Query(value = "SELECT s.skill_name AS skillName, COUNT(js.job_id) AS jobCount " +
            "FROM skills s " +
            "JOIN job_skills js ON s.skill_id = js.skill_id " +
            "GROUP BY s.skill_name " +
            "ORDER BY jobCount DESC", nativeQuery = true)
    List<SkillStat> findSkillStats();
}