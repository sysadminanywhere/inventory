package com.sysadminanywhere.inventory.repository;

import com.sysadminanywhere.inventory.entity.Software;
import com.sysadminanywhere.inventory.controller.dto.SoftwareCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SoftwareRepository  extends JpaRepository<Software, Long>  {

    List<Software> findByNameAndVendorAndVersion(String name, String vendor, String version);

    // select s."name", count(*) from software s inner join installations i on s.id = i.software_id group by s."name"
    @Query("select new com.sysadminanywhere.inventory.controller.dto.SoftwareCount(s.name, s.vendor, s.version, count(i)) from Software s JOIN s.installations i GROUP BY s.name, s.vendor, s.version")
    List<SoftwareCount> getSoftwareInstallationCount();

}
