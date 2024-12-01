package com.sysadminanywhere.inventory.repository;

import com.sysadminanywhere.inventory.entity.Installation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallationRepository  extends JpaRepository<Installation, Long>  {
}
