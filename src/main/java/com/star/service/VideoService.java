package com.star.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.star.entity.Video;
import com.star.queryvo.SearchBlog;
import com.star.queryvo.SearchVideoQo;
import com.star.queryvo.VideoQo;
import com.star.queryvo.VideoVo;

import java.util.List;

/**
 * @创建人 ruansl
 * @创建时间 2022/10/15 0015
 * @描述 视频业务层
 **/
public interface VideoService extends IService<Video> {

    //新增
    Boolean saveVideo(Video video);

    //查询所有视频
    List<VideoQo> getAllVideos();

    //删除视频
    void deleteVideo(Long id);

    //获取视频
    VideoVo getVideoById(Long id);

    //更新
    Boolean updateVideo(VideoVo videoVo);

    //搜素视频
    List<VideoQo> getVideoBySearch(SearchVideoQo searchVideoQo);
}
