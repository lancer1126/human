package com.lance.modules.mnt.repository;

import com.lance.modules.mnt.domain.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AppRepository extends JpaRepository<App, Long>, JpaSpecificationExecutor<App> {
}

