package jina.hyunseo.synergy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jina.hyunseo.synergy.domain.Post;
import jina.hyunseo.synergy.repository.PostRepository;

@Service
public class PostService {

@Autowired
private PostRepository postRepository;
public void write(Post post){

    postRepository.save(post);
}

@Transactional
public List<Post> search(String keyword) {
    List<Post> postList = postRepository.findByTitleContaining(keyword);
    return postList;
}
@Transactional
    public List<Post> getPostList(){
        List<Post> posts=postRepository.findAll();
        return posts;
    
    }
@Transactional
public void deletePost(Integer post_id){
    postRepository.deleteById(post_id);
}

@Transactional
public Post getPost(Integer post_id) {
    Optional<Post> postDetail=postRepository.findById(post_id);
    Post post=postDetail.get();
    return post;
}
}
    

