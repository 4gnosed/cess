package cn.edu.cess.entity.Vo;

import cn.edu.cess.constant.Constant;
import lombok.Data;

/**
 * @Author Gnosed Lu
 * @Date 2020/4/21
 * @Description
 */
@Data
public class FileUrlVo {
    private String IpPort;
    private String FilePath;
    private String Path;
    private String fileName;

    public String getPath() {
        String path = getIpPort() + getFilePath();
        return path == null ? null : path;
    }

    public String getFileName() {
        return FilePath.substring(Constant.PART_PATH.length() + Constant.UUID_LENGTH);
    }
}
