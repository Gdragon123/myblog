package com.star.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.star.entity.Blog;
import com.star.entity.Type;
import com.star.entity.User;
import com.star.entity.Video;
import com.star.queryvo.*;
import com.star.service.TypeService;
import com.star.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * @创建人 ruansl
 * @创建时间 2022/10/15 0015
 * @描述
 **/
@Controller
@RequestMapping("/admin")
public class VideoController {

    @Autowired
    private VideoService videoService;
    @Autowired
    private TypeService typeService;

    //跳转视频新增页面
    @GetMapping("/videos/input")
    public String input(Model model) {
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("blog", new Blog());
        return "admin/videos-input";
    }

    //博客新增
    @PostMapping("/videos")
    public String post(Video video, RedirectAttributes attributes, HttpSession session){
        //新增的时候需要传递blog对象，blog对象需要有user
        video.setUser((User) session.getAttribute("user"));
        //设置blog的type
        video.setType(typeService.getType(video.getType().getId()));
        //设置blog中typeId属性
        video.setTypeId(video.getType().getId());
        //设置用户id
        video.setUserId(video.getUser().getId());

        Boolean result = videoService.saveVideo(video);
        if(result){
            attributes.addFlashAttribute("message", "新增成功");
        }else {
            attributes.addFlashAttribute("message", "新增失败");
        }
        return "redirect:/admin/videos";
    }

    //视频列表
    @RequestMapping("/videos")
    public String videos(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){

        //按照排序字段 倒序 排序
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum,10,orderBy);
        List<VideoQo> list = videoService.getAllVideos();
        PageInfo<VideoQo> pageInfo = new PageInfo<VideoQo>(list);
        model.addAttribute("types",typeService.getByType(2));
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs";

    }

    //删除视频
    @GetMapping("/videos/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        videoService.deleteVideo(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }

    //跳转编辑修改视频
    @GetMapping("/videos/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        VideoVo videoVo = videoService.getVideoById(id);
        List<Type> allType = typeService.getAllType();
        model.addAttribute("video", videoVo);
        model.addAttribute("types", allType);
        return "admin/videos-input";
    }

    //编辑修改视频
    @PostMapping("/videos/{id}")
    public String editPost(@Valid VideoVo videoVo, RedirectAttributes attributes) {
        Boolean result = videoService.updateVideo(videoVo);
        if(result){
            attributes.addFlashAttribute("message", "修改成功");
        }else {
            attributes.addFlashAttribute("message", "修改失败");
        }
        return "redirect:/admin/videos";
    }

    //搜索博客管理列表
    @PostMapping("/videos/search")
    public String search(SearchVideoQo searchVideoQo, Model model,
                         @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        List<VideoQo> videoBySearch = videoService.getVideoBySearch(searchVideoQo);
        PageHelper.startPage(pageNum, 10);
        PageInfo<VideoQo> pageInfo = new PageInfo<>(videoBySearch);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/videos :: videoList";
    }

}
