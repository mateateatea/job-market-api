package com.mati.job_market_api.controller;

import com.mati.job_market_api.model.Skill;
import com.mati.job_market_api.repository.SkillRepository;
import org.springframework.web.bind.annotation.*;
import com.mati.job_market_api.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/skills")
public class SkillController {

    private final SkillRepository skillRepository;

    public SkillController(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @GetMapping
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Skill> getSkillById(@PathVariable Integer id) {
        return skillRepository.findById(id);
    }

    @PostMapping
    public Skill createSkill(@RequestBody Skill skill) {
        return skillRepository.save(skill);
    }

    @PutMapping("/{id}")
    public Skill updateSkill(@PathVariable Integer id, @RequestBody Skill skillDetails) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id " + id));

        skill.setName(skillDetails.getName());

        return skillRepository.save(skill);
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable Integer id) {
        skillRepository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public Skill patchSkill(@PathVariable Integer id, @RequestBody Skill skillDetails) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id " + id));

        if (skillDetails.getName() != null) {
            skill.setName(skillDetails.getName());
        }

        return skillRepository.save(skill);
    }
}