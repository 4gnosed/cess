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

    public static FileUrlVo upload(MultipartFile multiFile, HttpServletRequest request) {
        return upload(multiFile, request);
    }

    /**
     * 文件保存本地文件系统
     *
     * @param multiFile
     * @param request
     * @return
     */
    public static FileUrlVo upload(MultipartFile multiFile, HttpServletRequest request, String fileFolder) {
        String partPath = Constant.IMG_PATH;
        String filename = multiFile.getOriginalFilename();
        filename = UUID.randomUUID().toString() + filename;
        if (fileFolder == null || "".equals(fileFolder)) {
            fileFolder = Constant.FILE_FOLDER;
            partPath = Constant.PART_PATH;
        }
        File folder = new File(fileFolder);
        File file = new File(folder, filename);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            multiFile.transferTo(file);
            FileUrlVo fileUrlVo = new FileUrlVo();
            String ipPort = getIpPort(request);
            String filePath = partPath + file.getName();
            fileUrlVo.setIpPort(ipPort);
            fileUrlVo.setFilePath(filePath);
            return fileUrlVo;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getIpPort(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort();
    }
}
