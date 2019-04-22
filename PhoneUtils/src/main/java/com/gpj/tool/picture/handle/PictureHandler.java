package com.gpj.tool.picture.handle;

import com.gpj.tool.picture.utils.PictureUtile;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import javax.imageio.ImageIO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class PictureHandler {
    private BufferedImage bufferedImage;
    
    public PictureHandler(BufferedImage bufferedImage){
        this.bufferedImage = bufferedImage;
    }
    
    public static PictureHandler readImage(String path){
        BufferedImage bufferedImage = null;
        try{
            bufferedImage = ImageIO.read(new File(path));
        }catch(IOException e){
            log.error("图片读入失败:{}",e);
        }
        return new PictureHandler(bufferedImage);
    }
    
    public void writeImage(String path) {
        try{
            ImageIO.write(this.bufferedImage,"JPEG", new File(path));
        }catch(IOException e){
            log.error("图片写出失败:{}",e);
        }
    }

    public void writeWithImageResize(String path, float quality) {
        try{
            FileOutputStream out = new FileOutputStream(new File(path));
            PictureUtile.imageResize(this.bufferedImage, out, quality);
            ImageIO.write(this.bufferedImage,"JPEG", out);
        }catch(FileNotFoundException e){
            log.error("【{}】文件未找到:{}",path,e);
        }catch(IOException e){
            log.error("图片写出失败:{}",e);
        }
    }
    
    public PictureHandler cutImage(int x, int y, int width, int height){
        try{
            this.bufferedImage = PictureUtile.imgCut(this.bufferedImage,x, y, width, height);
        }catch(IOException e){
            log.error("切图失败:{}",e);
        }
        return this;
    }

    public PictureHandler zoomImage(int imageRatio){
        this.bufferedImage = PictureUtile.imgZoom(this.bufferedImage,imageRatio);
        return this;
    }

    public PictureHandler zoomImage(int width, int height){
        this.bufferedImage = PictureUtile.imgZoom(this.bufferedImage,width,height);
        return this;
    }
    
    public static void createImg(String path, int x, int y, int width, int height, float quality) {
        String dis = path.substring(0,path.lastIndexOf("/"));
        String name = path.substring(path.lastIndexOf("/") + 1,path.length());
        log.info("图片路径为:{}",dis);
        log.info("图片名称为:{}",name);
        String[] names = name.split("\\.");
        log.info("图片名称为:{}",names.length);
        String resultPath = dis + "/" + names[0] + "_" + UUID.randomUUID().toString() + "_result." + names[1];
        log.info("输出图片路径为:{}",resultPath);

        PictureHandler handler = PictureHandler.readImage(path);
        handler.cutImage(x,y,width,height).zoomImage(358,441).writeWithImageResize(resultPath,quality);
    }
}
