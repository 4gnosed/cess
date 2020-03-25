package cn.edu.cess.util;

import cn.edu.cess.constant.Constant;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author Gnosed Lu
 * @Date 2020/1/14
 */
public class FileUploadUtil {
    public static String upload(MultipartFile multiFile, HttpServletRequest request) {
        String filename = multiFile.getOriginalFilename();
        filename = UUID.randomUUID().toString() + filename.substring(filename.length() - 4);
        File imgfolder = new File(Constant.IMG_FOLDER);
        File file = new File(imgfolder, filename);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            multiFile.transferTo(file);
            String imgURL = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + "/api/file/" + file.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
