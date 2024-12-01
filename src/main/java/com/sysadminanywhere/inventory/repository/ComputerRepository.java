package com.sysadminanywhere.inventory.repository;

import com.sysadminanywhere.inventory.entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerRepository extends JpaRepository<Computer, Long> {
}
