package blog.geek.utils;

import blog.geek.exception.OverSizeException;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * 文件处理工具类
 * @author yuanyang
 * @version 1.0
 */
@Service
public class FileUtil {
    private String message;     //信息

    private final String realDir = "E:"+File.separator+"picture" + File.separator + "blog";
    //private final String realDir = "/srv/www/blog";
    private final String virtualDir = File.separator + "file"; //虚拟磁盘目录
    private String extension;   //文件后缀

    private String fileName;    //文件名
    private String className;   //类名
    private long timeStamp;     //时间戳

    private String realPath;    //真实路径
    private String virtualPath; //虚拟路径

    /**
     * 将文件存入磁盘中
     * @param file      文件
     * @param clazzName 全类名
     * @param id        主码
     * @return
     */
    public boolean saveImage(MultipartFile file, String clazzName, String id) {
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        if (file.getSize() > 209715200) {
//            throw new OverSizeException("文件过大,只能上传小于200M的文件!");
        } else {
            timeStamp = System.currentTimeMillis();
            className = clazzName;
            fileName = file.getOriginalFilename();
            extension = fileName.substring(fileName.lastIndexOf(".") + 1);

            createFilePath(id);

            File file1 = new File(getRealPath());   //创建文件
            try {
                fileOutputStream = new FileOutputStream(file1);
                bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

                bufferedOutputStream.write(file.getBytes());
                if (file1.exists()) {
                    return true;
                } else
                    return false;
            } catch (FileNotFoundException e) {
                e.getMessage();
            } catch (IOException e) {
                e.getMessage();
            } finally {
                if (bufferedOutputStream != null){
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e) {
                        e.getMessage();
                    }
                }
                if (fileOutputStream != null){
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        e.getMessage();
                    }
                }
            }
        }
        return false;
    }
    /**
     * 更新图片
     * @param image
     * @param oldImagePath
     * @param clazzName
     * @param id
     * @return
     */
    public boolean updateImage(MultipartFile image, String oldImagePath, String clazzName, String id){
        //先删除原图片
        deleteImage(oldImagePath);
        //存新图片
        boolean flag = saveImage(image,clazzName,id);
        return flag;
    }

    /**
     * 删除图片
     * @param imagePath
     */
    public void deleteImage(String imagePath){
        if (ValidateUtil.notEmpty(imagePath)){
            File file = new File(imagePath.replace(virtualDir,realDir));
            if (file.exists()) {
                try {
                    FileUtils.forceDelete(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 批量删除图片
     * @param imagePaths
     */
    public void deleteImages(List<String> imagePaths){
        for (String imagePath:imagePaths){
            deleteImage(imagePath);
        }
    }

    /**
     * 创建文件路径
     * @param id
     */
    public void createFilePath(String id) {
        realPath = realDir + File.separator + className + "_" + timeStamp + "_" + id + "." + extension.toLowerCase();
        virtualPath = virtualDir + File.separator + className + "_" + timeStamp + "_" + id + "." + extension.toLowerCase();
    }

    /**
     * 返回文件的虚拟路径
     * @return
     */
    public String getVirtualPath() {
        return virtualPath;
    }

    /**
     * 返回文件的真实路径
     * @return
     */
    public String getRealPath() {
        return realPath;
    }
}
