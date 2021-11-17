package com.lance.modules.mnt.repository;

import com.lance.modules.mnt.domain.DeployHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeployHistoryRepository extends JpaRepository<DeployHistory, String>, JpaSpecificationExecutor<DeployHistory> {
}
