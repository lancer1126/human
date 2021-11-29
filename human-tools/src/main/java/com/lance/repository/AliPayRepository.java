package com.lance.repository;

import com.lance.domain.AlipayConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AliPayRepository extends JpaRepository<AlipayConfig, Long> {
}
