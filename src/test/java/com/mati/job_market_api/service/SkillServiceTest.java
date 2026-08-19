package com.mati.job_market_api.service;

import com.mati.job_market_api.model.Skill;
import com.mati.job_market_api.repository.SkillRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.mati.job_market_api.exception.ResourceNotFoundException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SkillServiceTest {

    @Mock
    private SkillRepository skillRepository;

    @InjectMocks
    private SkillService skillService;

    @Test
    void getSkillById_returnsSkill_whenSkillExists() {
        Skill skill = new Skill();
        skill.setId(1);
        skill.setName("DOCKER");

        when(skillRepository.findById(1)).thenReturn(Optional.of(skill));

        Skill result = skillService.getSkillById(1);

        assertEquals("DOCKER", result.getName());
    }

    @Test
    void getSkillById_throwsException_whenSkillDoesNotExist() {
        when(skillRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            skillService.getSkillById(99);
        });
    }

    @Test
    void createSkill_savesAndReturnsSkill() {
        Skill skill = new Skill();
        skill.setName("KUBERNETES");

        when(skillRepository.save(skill)).thenReturn(skill);

        Skill result = skillService.createSkill(skill);

        assertEquals("KUBERNETES", result.getName());
    }

    @Test
    void updateSkill_updatesName_whenSkillExists() {
        Skill existingSkill = new Skill();
        existingSkill.setId(1);
        existingSkill.setName("Java");

        Skill skillDetails = new Skill();
        skillDetails.setName("Python");

        when(skillRepository.findById(1)).thenReturn(Optional.of(existingSkill));
        when(skillRepository.save(existingSkill)).thenReturn(existingSkill);

        Skill result = skillService.updateSkill(1, skillDetails);

        assertEquals("Python", result.getName());
    }

    @Test
    void patchSkill_updatesName_whenNameProvided() {
        Skill existingSkill = new Skill();
        existingSkill.setId(1);
        existingSkill.setName("Java");

        Skill skillDetails = new Skill();
        skillDetails.setName("Python");

        when(skillRepository.findById(1)).thenReturn(Optional.of(existingSkill));
        when(skillRepository.save(existingSkill)).thenReturn(existingSkill);

        Skill result = skillService.patchSkill(1, skillDetails);

        assertEquals("Python", result.getName());
    }

    @Test
    void patchSkill_keepsOriginalName_whenNameIsNull() {
        Skill existingSkill = new Skill();
        existingSkill.setId(1);
        existingSkill.setName("Java");

        Skill skillDetails = new Skill();
        skillDetails.setName(null);

        when(skillRepository.findById(1)).thenReturn(Optional.of(existingSkill));
        when(skillRepository.save(existingSkill)).thenReturn(existingSkill);

        Skill result = skillService.patchSkill(1, skillDetails);

        assertEquals("Java", result.getName());
    }

    @Test
    void deleteSkill_callsRepositoryDeleteById() {
        skillService.deleteSkill(1);

        verify(skillRepository).deleteById(1);
    }
}
