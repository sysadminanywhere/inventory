package com.sysadminanywhere.inventory.repository;

import com.sysadminanywhere.inventory.entity.Software;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftwareRepository  extends JpaRepository<Software, Long>  {
}
