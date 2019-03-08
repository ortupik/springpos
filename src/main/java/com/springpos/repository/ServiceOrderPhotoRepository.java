package com.springpos.repository;

import com.springpos.bean.ServiceOrderPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *

 */
@Repository
public interface ServiceOrderPhotoRepository extends JpaRepository<ServiceOrderPhoto, Integer>{
      
}
