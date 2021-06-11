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
    private String ipPort;
    private String filePath;
    private String path;
    private String fileName;

    public String getPath() {
        if (getIpPort() == null || getFilePath() == null) {
            return null;
        } else {
            return getIpPort() + getFilePath();
        }
    }

    public String getFileName() {
        return filePath == null ? null : filePath.substring(filePath.lastIndexOf("/") + 1 + Constant.SNOWFLAKE_LENGTH);
    }

    public FileUrlVo() {
    }
}
