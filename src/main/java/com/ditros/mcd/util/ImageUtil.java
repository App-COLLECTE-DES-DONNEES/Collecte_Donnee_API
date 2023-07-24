package com.ditros.mcd.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageUtil {
    public static byte[] getImageFromDisk(String path) throws IOException {
        BufferedImage img;
        System.out.println("Path=====" + path);
        try{
            img = ImageIO.read(new File(path));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "png", baos);
            System.out.println("Image =========== " + baos.toByteArray());
            return baos.toByteArray();
        }
        catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
