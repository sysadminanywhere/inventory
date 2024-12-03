package com.sysadminanywhere.inventory.controller;

import com.sysadminanywhere.inventory.controller.dto.ComputerDto;
import com.sysadminanywhere.inventory.controller.dto.SoftwareCount;
import com.sysadminanywhere.inventory.controller.dto.SoftwareDto;
import com.sysadminanywhere.inventory.controller.dto.SoftwareOnComputer;
import com.sysadminanywhere.inventory.mapper.ComputerMapper;
import com.sysadminanywhere.inventory.mapper.SoftwareMapper;
import com.sysadminanywhere.inventory.repository.ComputerRepository;
import com.sysadminanywhere.inventory.repository.SoftwareRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final ComputerRepository computerRepository;
    private final SoftwareRepository softwareRepository;

    private final ComputerMapper computerMapper;
    private final SoftwareMapper softwareMapper;

    public InventoryController(ComputerRepository computerRepository,
                               SoftwareRepository softwareRepository,
                               ComputerMapper computerMapper,
                               SoftwareMapper softwareMapper) {
        this.computerRepository = computerRepository;
        this.softwareRepository = softwareRepository;
        this.computerMapper = computerMapper;
        this.softwareMapper = softwareMapper;
    }

    @GetMapping("/computers")
    public ResponseEntity<List<ComputerDto>> getComputers() {
        return ResponseEntity.ok(computerMapper.toDtoList(computerRepository.findAll()));
    }

    @GetMapping("/computers/{computerId}")
    public ResponseEntity<List<SoftwareOnComputer>> getSoftwareOnComputer(@PathVariable Long computerId) {
        return ResponseEntity.ok(softwareRepository.getSoftwareOnComputer(computerId));
    }

    @GetMapping("/software")
    public ResponseEntity<List<SoftwareDto>> getSoftware() {
        return ResponseEntity.ok(softwareMapper.toDtoList(softwareRepository.findAll()));
    }

    @GetMapping("/software/count")
    public ResponseEntity<List<SoftwareCount>> getSoftwareCount() {
        return ResponseEntity.ok(softwareRepository.getSoftwareInstallationCount());
    }

    @GetMapping("/software/{softwareId}")
    public ResponseEntity<List<ComputerDto>> getComputersWithSoftware(@PathVariable Long softwareId) {
        return ResponseEntity.ok(computerRepository.getComputersWithSoftware(softwareId));
    }

}