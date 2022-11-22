package com.star.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.star.dao.VideoDao;
import com.star.entity.Video;
import com.star.queryvo.SearchBlog;
import com.star.queryvo.SearchVideoQo;
import com.star.queryvo.VideoQo;
import com.star.queryvo.VideoVo;
import com.star.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @创建人 ruansl
 * @创建时间 2022/10/15 0015
 * @描述
 **/
@Service
public class VideoServiceImpl extends ServiceImpl<VideoDao, Video> implements VideoService {

    @Autowired
    private VideoDao videoDao;

    @Override
    public Boolean saveVideo(Video video) {
        Date now = new Date();
        video.setCreateTime(now);
        video.setUpdateTime(now);
        return this.save(video);
    }

    @Override
    public List<VideoQo> getAllVideos() {
        return videoDao.getAllVideoQuery();
    }

    @Override
    public void deleteVideo(Long id) {
        videoDao.deleteById(id);
    }

    @Override
    public VideoVo getVideoById(Long id) {
        if (Objects.nonNull(id)) {
            Video video = videoDao.selectById(id);
            if (Objects.nonNull(video)) {
                VideoVo videoVo = new VideoVo();
                BeanUtils.copyProperties(video, videoVo);
                return videoVo;
            }
        }
        return null;
    }

    @Override
    public Boolean updateVideo(VideoVo videoVo) {
        Video video = new Video();
        BeanUtils.copyProperties(videoVo, video);
        Integer count = this.baseMapper.updateById(video);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<VideoQo> getVideoBySearch(SearchVideoQo searchVideoQo) {
        List<VideoQo> videoQoList = new ArrayList<>();
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("title", searchVideoQo.getTitle());
        wrapper.eq("type_id", searchVideoQo.getTypeId());
        List<Video> videoList = this.baseMapper.selectList(wrapper);
        if (!CollectionUtils.isEmpty(videoList)) {
            videoList.stream().forEach(x -> {
                VideoQo videoQo = new VideoQo();
                BeanUtils.copyProperties(x, videoQo);
                videoQoList.add(videoQo);
            });
        }
        return videoQoList;
    }
}
