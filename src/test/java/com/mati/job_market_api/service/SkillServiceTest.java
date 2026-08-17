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
}
