package com.bdth.bootDemo.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;

/**
 * @author weiming.zhu
 * @date 2019/1/7 11:20
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${imagesPath}")
    private String mImagesPath;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大KB,MB
        factory.setMaxFileSize("1024MB");
        //设置总上传数据总大小
        factory.setMaxRequestSize("1024MB");
        return factory.createMultipartConfig();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (mImagesPath.equals("") || mImagesPath.equals("${imagesPath}")) {
            String imagesPath = WebAppConfig.class.getClassLoader().getResource("").getPath();
            logger.info("====== 1.上传配置类imagesPath: " + imagesPath + " ======");
            if (imagesPath.indexOf(".jar") > 0) {
                imagesPath = imagesPath.substring(0, imagesPath.indexOf(".jar"));
            } else if (imagesPath.indexOf("classes") > 0) {
                imagesPath = "file:" + imagesPath.substring(0, imagesPath.indexOf("classes"));
            }
            imagesPath = imagesPath.substring(0, imagesPath.lastIndexOf("/")) + "/images/";
            mImagesPath = imagesPath;
        }
        logger.info("====== imagesPath: " + mImagesPath + " ======");
        registry.addResourceHandler("/images/**").addResourceLocations(mImagesPath);
        logger.info("====== 2.上传配置类mImagesPath: " + mImagesPath + " ======");
    }

}
