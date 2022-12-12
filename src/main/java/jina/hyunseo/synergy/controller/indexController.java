package jina.hyunseo.synergy.controller;

import java.io.IOException;
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

import jina.hyunseo.synergy.domain.Photo;
import jina.hyunseo.synergy.domain.Post;
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

    // @GetMapping("/")
    // public String index(Model model){
    //     SessionUser user = (SessionUser) httpSession.getAttribute("user");
    //     if(user != null) {
    //         model.addAttribute("name", user.getName());
    //         model.addAttribute("picture", user.getPicture());
    //     }
    //     return "index2";
    // }

    // @GetMapping("/home")
    // public String home(Model model){
        
    //     return "home";
    // }

    @Autowired
    private PostService postService;

    @Autowired
    private PhotoService photoService;

    @GetMapping("post/list")
    public String list(Model model){
        List<Post> list=postService.getPostList();
        model.addAttribute("postlist",list);
        return "list";
    }

    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Integer post_id, Model model){
        Post post=postService.getPost(post_id);
        model.addAttribute("post", post);
        return "detail";
    }

    @GetMapping("/post/write") //localhost:8090/post/write
    public String PostingForm(){

        return "index";
    }

    @PostMapping("/post/write")
    public String postWrite(Post post,  @RequestParam("files") List<MultipartFile> files)throws IOException{

        postService.write(post);
        for (MultipartFile multipartFile : files) {
            photoService.saveFile(multipartFile);
        }

        return "redirect:/view";
    }

    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Integer post_id, Model model){
        Post post=postService.getPost(post_id);
        model.addAttribute("post", post);
        return "update";
    }

    @PutMapping("/post/edit/{no}")
    public String update(Post post){
        postService.write(post);
        return "redirect:/";
    }

    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Integer post_id){
        postService.deletePost(post_id);
        return "redirect:/";
    }


    @GetMapping("/post/search")
    public String search(Model model,String keyword){
        List<Post> searchList=postService.search(keyword);

        model.addAttribute("searchList", searchList);

        return "post-search";
    }

    @GetMapping("/view")
    public String view(Model model) {

        List<Photo> files = photoRepository.findAll();
        model.addAttribute("all",files);
        return "view";
    }


    //   이미지 출력
    @GetMapping("/images/{fileId}")
    @ResponseBody
    public UrlResource downloadImage(@PathVariable("fileId") Long id, Model model) throws IOException{

        Photo file = photoRepository.findById(id).orElse(null);
        return new UrlResource("file:" + file.getFilePath());
    }
    
    @GetMapping("/post/star")
    public String review1(Integer temp){
        System.out.println(temp);
        return "star";
    }
    @PostMapping("/post/star")
    public String review(@RequestBody Integer temp){
        System.out.println(temp);
        return "redirect:/";
    }
}
