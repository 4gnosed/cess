package cn.edu.cess.util;

import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.Vo.FileUrlVo;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author Gnosed Lu
 * @Date 2020/1/14
 */
public class FileUploadUtil {

    private static final Logger log = LoggerFactory.getLogger(FileUploadUtil.class);

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

        log.info("开始保存文件>>>>>>filename:{}", filename);


        FileOutputStream write = null;
        InputStream read = null;
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            write = new FileOutputStream(file);
            read = multiFile.getInputStream();
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = read.read(buf)) != -1) {
                write.write(buf, 0, length);
            }
//            multiFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                write.close();
                read.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileUrlVo fileUrlVo = new FileUrlVo();
        String ipPort = getIpPort(request);
        String filePath = partPath + file.getName();
        fileUrlVo.setIpPort(ipPort);
        fileUrlVo.setFilePath(filePath);
        log.info("开始保存文件成功返回>>>>>>fileUrlVo：{}", JSON.toJSONString(fileUrlVo));

        return fileUrlVo;
    }


    public static String getIpPort(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort();
    }
}
