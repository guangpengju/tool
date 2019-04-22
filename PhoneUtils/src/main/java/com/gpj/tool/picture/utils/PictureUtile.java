package com.gpj.tool.picture.utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by GPJ on 2019/4/22.
 */
public class PictureUtile {
    /**
     * 裁剪图片
     */
    public static BufferedImage imgCut(BufferedImage imgBuffer, int x, int y, int width, int height) throws IOException {
        ImageFilter cropFilter = new CropImageFilter(x, y, width, height);
        
        Image img = Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(imgBuffer.getSource(), cropFilter));
        BufferedImage resultImgBuffer = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);

        Graphics graphics = resultImgBuffer.getGraphics();
        graphics.drawImage(img, 0, 0, null); // 绘制小图
        graphics.dispose();
        
        return resultImgBuffer;
    }

    /**
     * 缩放图片(压缩图片质量，不改变图片尺寸)
     */
    public static void imageResize(BufferedImage bufferedImage, FileOutputStream out, float quality) throws IOException {
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bufferedImage);
        param.setQuality(quality, true);
        encoder.setJPEGEncodeParam(param);
        encoder.encode(bufferedImage);
    }

    /**
     * 等比例缩放图片
     */
    public static BufferedImage imgZoom(BufferedImage bufferedImage, int width, int height){
        Image image = Toolkit.getDefaultToolkit().createImage(bufferedImage.getSource());

        BufferedImage resultImgBuffer = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resultImgBuffer.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return resultImgBuffer;
    }

    /**
     * 等比例缩放图片
     */
    public static BufferedImage imgZoom(BufferedImage bufferedImage, int imageRatio){
        Image image = Toolkit.getDefaultToolkit().createImage(bufferedImage.getSource());

        int width = image.getWidth(null) * imageRatio;
        int height = image.getHeight(null) * imageRatio;

        BufferedImage resultImgBuffer = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = resultImgBuffer.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return resultImgBuffer;
    }
}
