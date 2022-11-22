package com.star.entity;

import lombok.Data;

import java.util.Date;

/**
 * @创建人 ruansl
 * @创建时间 2022/10/15 0015
 * @描述
 **/
@Data
public class Video {

    //视频ID
    private Long id;
    //视频标题
    private String title;
    //云端视频资源
    private String videoSourceId;
    //原始文件名称
    private String videoOriginalName;
    //排序
    private Integer sort;
    //播放次数
    private Integer playCount;
    //是否可以免费试听
    private Integer isFree;
    //视频时长
    private Float duration;
    //状态：0未上传 1转码中  2正常
    private Integer status;
    //视频源文件大小（字节）
    private Long size;
    //乐观锁
    private Long version;
    //类型id
    private Long typeId;
    //用户id
    private Long userId;
    private Date createTime;
    private Date updateTime;

    private Type type;
    private User user;

}
