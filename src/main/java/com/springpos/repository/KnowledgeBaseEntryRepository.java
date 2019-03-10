package com.springpos.repository;

import com.springpos.bean.KnowledgeBaseEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 *
 */
@Repository
public interface KnowledgeBaseEntryRepository extends JpaRepository<KnowledgeBaseEntry, Integer> {

}
