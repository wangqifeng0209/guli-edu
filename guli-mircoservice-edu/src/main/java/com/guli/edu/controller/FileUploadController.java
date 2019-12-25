package com.guli.edu.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.guli.edu.common.R;
import com.guli.edu.handler.ConstantPropertiesUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author Jason
 * @create 2019-12-12-19:39
 */

@Api("文件上传的接口")
@RestController
@RequestMapping("/edu")
@CrossOrigin
public class FileUploadController {




    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public R upload(MultipartFile file) {

        //优化：对于固定的值，我们一般从写到配置文件中，然后读取配置文件
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = ConstantPropertiesUtils.ENDPOINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = ConstantPropertiesUtils.KEYID;
        String accessKeySecret = ConstantPropertiesUtils.KEYSECRET;
        String bucketName = ConstantPropertiesUtils.BUCKETNAME;


        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

//            System.out.println(filename);
            InputStream inputStream = file.getInputStream();



            //构建日期路径：avatar/2019/02/26/文件名
            String filePath = new DateTime().toString("yyyy/MM/dd/");

            //文件名：uuid.扩展名
            String original = file.getOriginalFilename();

            String uuid = UUID.randomUUID().toString();

            String fileType = original.substring(original.lastIndexOf("."));

            String fileName = filePath + uuid + fileType;
//            String fileUrl = fileHost + "/" + filePath + "/" + newName;

            // 上传文件流。
            ossClient.putObject(bucketName, fileName, inputStream);
            String imgUrl = "http://" + bucketName + "." + endpoint + "/" + fileName;

            // 关闭OSSClient。
            ossClient.shutdown();
            return R.ok().data("imgUrl", imgUrl);


        }catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }


    }
}
