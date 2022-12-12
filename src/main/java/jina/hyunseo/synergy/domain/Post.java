package jina.hyunseo.synergy.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import groovy.transform.builder.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Table(name = "Post")
public class Post extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer post_id;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = User.class)
    @JoinColumn(name = "user_name", updatable = false)
    @JsonBackReference
    private User name;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = Photo.class)
    @JoinColumn(name = "file_id", updatable = false)
    @JsonBackReference
    private Photo photo_id;

    private String git_url;
    private String title;
    private String content;
    

    @OneToMany(
        mappedBy = "post",
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
        orphanRemoval = true
    )
    private List<Photo> photo = new ArrayList<>();

    @Builder
    public Post(User name, Photo photo_id, String git_url, String title, String content) {
        this.name = name;
        this.photo_id= photo_id;
        this.title = title;
        this.git_url=git_url;
        this.content = content;
    }

    public void update(String title, String content, String git_url) {
        this.title = title;
        this.content = content;
        this.git_url= git_url;
    }

    // // Board에서 파일 처리 위함
    // public void addPhoto(Photo photo) {
    //     this.photo.add(photo);

	// // 게시글에 파일이 저장되어있지 않은 경우
    //     if(photo.getPost() != this)
    //         // 파일 저장
    //         photo.setPost(this);
    // }
}
