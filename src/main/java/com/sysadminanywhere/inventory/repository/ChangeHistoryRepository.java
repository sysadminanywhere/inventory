package com.sysadminanywhere.inventory.repository;

import com.sysadminanywhere.inventory.entity.ChangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangeHistoryRepository extends JpaRepository<ChangeHistory, Long> {
}