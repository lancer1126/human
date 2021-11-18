package com.lance.repository;

import com.lance.domain.QiniuContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface QiniuContentRepository extends JpaRepository<QiniuContent,Long>, JpaSpecificationExecutor<QiniuContent> {
}
