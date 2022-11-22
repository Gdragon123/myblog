package com.star.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.star.entity.Video;
import com.star.queryvo.VideoQo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @创建人 ruansl
 * @创建时间 2022/10/15 0015
 * @描述
 **/
@Mapper
@Repository
public interface VideoDao extends BaseMapper<Video> {

    List<VideoQo> getAllVideoQuery();
}
