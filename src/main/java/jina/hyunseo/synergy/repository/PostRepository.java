package jina.hyunseo.synergy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jina.hyunseo.synergy.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

    List<Post> findByTitleContaining(String keyword);



    
}
