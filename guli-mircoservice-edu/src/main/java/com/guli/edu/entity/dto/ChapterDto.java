package com.guli.edu.entity.dto;

import com.guli.edu.entity.Video;
import lombok.Data;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

/**
 * @author Jason
 * @create 2019-12-17-9:45
 */

@Data
public class ChapterDto {

    private String id;

    private String title;

    private List<VideoDto> children;
}
