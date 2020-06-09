package cn.edu.cess.util;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.Vo.FileUrlVo;
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

    public static FileUrlVo uploadResume(MultipartFile multiFile, HttpServletRequest request) {
        return upload(multiFile, request, Constant.RESUME_PATH);
    }

    public static FileUrlVo uploadImg(MultipartFile multiFile, HttpServletRequest request) {
        return upload(multiFile, request, Constant.IMG_PATH);
    }

    public static FileUrlVo uploadAvatar(MultipartFile multiFile, HttpServletRequest request) {
        return upload(multiFile, request, Constant.AVATAR1_PATH);
    }

    /**
     * 文件保存本地文件系统
     *
     * @param multiFile
     * @param request
     * @return
     */
    public static FileUrlVo upload(MultipartFile multiFile, HttpServletRequest request, String partPath) {
        String filename = multiFile.getOriginalFilename();
        filename = UUID.randomUUID().toString() + filename;

        File folder = new File(Constant.FILE_FOLDER + partPath.substring(10));
        File file = new File(folder, filename);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            multiFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        FileUrlVo fileUrlVo = new FileUrlVo();
        String ipPort = getIpPort(request);
        String filePath = partPath + file.getName();
        fileUrlVo.setIpPort(ipPort);
        fileUrlVo.setFilePath(filePath);
        return fileUrlVo;
    }

    public static String getIpPort(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort();
    }
}
