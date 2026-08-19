package com.mati.job_market_api.service;

import com.mati.job_market_api.exception.ResourceNotFoundException;
import com.mati.job_market_api.model.Skill;
import com.mati.job_market_api.model.SkillStat;
import com.mati.job_market_api.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill getSkillById(Integer id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id " + id));
    }

    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public Skill updateSkill(Integer id, Skill skillDetails) {
        Skill skill = getSkillById(id);
        skill.setName(skillDetails.getName());
        return skillRepository.save(skill);
    }

    public Skill patchSkill(Integer id, Skill skillDetails) {
        Skill skill = getSkillById(id);
        if (skillDetails.getName() != null) {
            skill.setName(skillDetails.getName());
        }
        return skillRepository.save(skill);
    }

    public void deleteSkill(Integer id) {
        skillRepository.deleteById(id);
    }

    public List<SkillStat> getSkillStats() {
        return skillRepository.findSkillStats();
    }
}