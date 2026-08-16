package com.mati.job_market_api.controller;

import com.mati.job_market_api.model.Skill;
import com.mati.job_market_api.model.SkillStat;
import com.mati.job_market_api.service.SkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/stats")
    public List<SkillStat> getSkillStats() {
        return skillService.getSkillStats();
    }

    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }

    @GetMapping("/{id}")
    public Skill getSkillById(@PathVariable Integer id) {
        return skillService.getSkillById(id);
    }

    @PostMapping
    public Skill createSkill(@RequestBody Skill skill) {
        return skillService.createSkill(skill);
    }

    @PutMapping("/{id}")
    public Skill updateSkill(@PathVariable Integer id, @RequestBody Skill skillDetails) {
        return skillService.updateSkill(id, skillDetails);
    }

    @PatchMapping("/{id}")
    public Skill patchSkill(@PathVariable Integer id, @RequestBody Skill skillDetails) {
        return skillService.patchSkill(id, skillDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteSkill(@PathVariable Integer id) {
        skillService.deleteSkill(id);
    }
}