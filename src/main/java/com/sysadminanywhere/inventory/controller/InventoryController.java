package com.sysadminanywhere.inventory.controller;

import com.sysadminanywhere.inventory.controller.dto.ComputerDto;
import com.sysadminanywhere.inventory.controller.dto.SoftwareCount;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/inventory/software")
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

    @GetMapping("/computers/{computerId}")
    public ResponseEntity<Page<SoftwareOnComputer>> getSoftwareOnComputer(@PathVariable Long computerId, Pageable pageable) {
        return ResponseEntity.ok(softwareRepository.getSoftwareOnComputer(computerId, pageable));
    }

    @GetMapping("/count")
    public ResponseEntity<Page<SoftwareCount>> getSoftwareCount(Pageable pageable) {
        return ResponseEntity.ok(softwareRepository.getSoftwareInstallationCount(pageable));
    }

    @GetMapping("/{softwareId}")
    public ResponseEntity<Page<ComputerDto>> getComputersWithSoftware(@PathVariable Long softwareId, Pageable pageable) {
        return ResponseEntity.ok(computerRepository.getComputersWithSoftware(softwareId, pageable));
    }

}