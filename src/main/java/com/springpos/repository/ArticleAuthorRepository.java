package com.springpos.repository;

import com.springpos.bean.ArticleAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleAuthorRepository extends JpaRepository<ArticleAuthor, Integer> {

}
