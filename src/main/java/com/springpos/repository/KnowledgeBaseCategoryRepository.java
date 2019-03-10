package com.springpos.repository;

import com.springpos.bean.KnowledgeBaseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
public interface KnowledgeBaseCategoryRepository extends JpaRepository<KnowledgeBaseCategory, Integer> {

}
