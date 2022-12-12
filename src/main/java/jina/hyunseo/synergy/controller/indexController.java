package jina.hyunseo.synergy.controller;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.sym.Name;
import com.fasterxml.jackson.core.sym.Name2;

import jina.hyunseo.synergy.domain.Photo;
import jina.hyunseo.synergy.domain.Post;
import jina.hyunseo.synergy.domain.User;
import jina.hyunseo.synergy.dto.SessionUser;
import jina.hyunseo.synergy.repository.PhotoRepository;
import jina.hyunseo.synergy.repository.PostRepository;
import jina.hyunseo.synergy.service.PhotoService;
import jina.hyunseo.synergy.service.PostService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class indexController{
    private final HttpSession httpSession;
    @Autowired
    private PhotoRepository photoRepository;

    //홈화면에서 로그인 기능 
    @GetMapping("/")
    public String index(Model model){
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null) {
            model.addAttribute("name", user.getName());
            model.addAttribute("picture", user.getPicture());
        }
        return "index";
    }


    @Autowired
    private PostService postService;

    @Autowired
    private PhotoService photoService;

    //전체 포폴 리스트 
    @GetMapping("/foliolist")
    public String list(Model model){
        List<Post> list=postService.getPostList();
        model.addAttribute("list",list);
        List<Photo> files = photoRepository.findAll();
        model.addAttribute("all",files);
        return "foliolist";
    }

    //사용자별 포폴 리스트
    @GetMapping("/mypost")
    public String mypost(Model model, String name){
        List<Post> mylist=postService.userPostlist(name);
        model.addAttribute("list",mylist);
        List<Photo> files = photoRepository.findAll();
        model.addAttribute("all",files);
        return "mypost";
    }



    //포폴 클릭시
    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Integer post_id, Model model){
        Post post=postService.getPost(post_id);
        model.addAttribute("post", post);
        List<Photo> files = photoRepository.findAll();
        model.addAttribute("all",files);
        return "portfolio";
    }
    //포폴 업로드 페이지
    @GetMapping("/post/write") //localhost:8090/post/write
    public String PostingForm(){

        return "posting";
    }
    //포폴 업로드 페이지_post&photo 저장 기능구현
    @PostMapping("/post/write")
    public String postWrite(Post post,  @RequestParam("files") List<MultipartFile> files)throws IOException{

        postService.write(post);
        for (MultipartFile multipartFile : files) {
            photoService.saveFile(multipartFile);
        }

        return "redirect:/";
    }

    //포폴 수정하는 화면 
    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Integer post_id, Model model){
        Post post=postService.getPost(post_id);
        model.addAttribute("post", post);
        return "updating";
    }
    //포폴 db업데이트(수정)
    @PutMapping("/post/edit/{no}")
    public String update(Post post){
        postService.write(post);
        return "redirect:/";
    }

    //포폴 삭제 기능 
    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Integer post_id){
        postService.deletePost(post_id);
        return "redirect:/";
    }

    //포폴 검색 기능 input keyword로 검색 
    @GetMapping("/post/search")
    public String search(Model model,String keyword){
        List<Post> searchList=postService.search(keyword);

        model.addAttribute("searchList", searchList);

        return "foliolist";
    }

    //이미지 불러오기 
    @GetMapping("/images/{fileId}")
    @ResponseBody
    public UrlResource downloadImage(@PathVariable("fileId") Long post_id, Model model) throws IOException{

        Photo file = photoRepository.findById(post_id).orElse(null);
        return new UrlResource("file:" + file.getFilePath());
    }
    
}
