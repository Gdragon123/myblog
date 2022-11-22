package com.star.queryvo;

import com.star.entity.Type;
import lombok.Data;

import java.util.Date;

/**
 * @创建人 ruansl
 * @创建时间 2022/10/15 0015
 * @描述 查询视频列表
 **/
@Data
public class VideoQo {

    private Long id;
    private String title;
    private Date createTime;
    private Date updateTime;
    private Boolean recommend;
    private Boolean published;
    private Long typeId;
    private Type type;

}
