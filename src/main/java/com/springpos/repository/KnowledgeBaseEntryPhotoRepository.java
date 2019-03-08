package com.springpos.repository;

import com.springpos.bean.KnowledgeBaseEntryPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *

 */
@Repository
public interface KnowledgeBaseEntryPhotoRepository  extends JpaRepository<KnowledgeBaseEntryPhoto, Integer>{
   
}
