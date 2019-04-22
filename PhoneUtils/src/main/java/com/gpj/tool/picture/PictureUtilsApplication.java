package com.gpj.tool.picture;

import com.gpj.tool.picture.handle.PictureHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PictureUtilsApplication {
   
    
    public static void main(String[] args) {
        log.info("start.......");
        PictureHandler.createImg("F:/picture.jpg",0,0,578,712,0.15F);
        log.info("finish.......");
    }
}
