package com.sysadminanywhere.inventory.repository;

import com.sysadminanywhere.inventory.controller.dto.ComputerDto;
import com.sysadminanywhere.inventory.entity.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ComputerRepository extends JpaRepository<Computer, Long>, JpaSpecificationExecutor<Computer> {

    List<Computer> findAllByName(String cn);

    @Query("SELECT new com.sysadminanywhere.inventory.controller.dto.ComputerDto(c.id, c.name, c.dns) FROM Computer c JOIN c.installations i WHERE i.software.id = :softwareId and c.name LIKE :name")
    Page<ComputerDto> getComputersWithSoftware(@Param("softwareId") Long softwareId, @Param("name") String name, Pageable pageable);

}
