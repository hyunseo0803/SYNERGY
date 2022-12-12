package jina.hyunseo.synergy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "File")
public class Photo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(nullable = false)
    private String orgNm;  // 파일 원본명

    @Column(nullable = false)
    private String savedNm;

    private String filePath;  // 파일 저장 경로

    @Builder
    public Photo(Long id, String orgNm, String savedNm, String filePath){
        this.id=id;
        this.orgNm = orgNm;
        this.savedNm=savedNm;
        this.filePath = filePath;
    }

    // // Board 정보 저장
    // public void setPost(Post post){
    //     this.post = post;

	// // 게시글에 현재 파일이 존재하지 않는다면
    //     if(!post.getPhoto().contains(this))
    //         // 파일 추가
    //         post.getPhoto().add(this);
    // }


}
