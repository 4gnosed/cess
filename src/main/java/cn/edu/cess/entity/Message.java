package cn.edu.cess.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-04-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 发件人姓名
     */
    @TableField("sender")
    private String sender;

    /**
     * 收件人姓名
     */
    @TableField("receiver")
    private String receiver;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 发送时间
     */
    @TableField("send_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date sendTime;

    /**
     * 阅读状态：0未阅读，1已阅读
     */
    @TableField("status")
    private Boolean status;

    /**
     * 表示这封信是对id值为response_id的信息进行回信
     */
    @TableField("response_id")
    private Integer responseId;

    /**
     * 发件人用户id
     */
    @TableField("sender_uid")
    private Integer senderUid;
    /**
     * 收件人用户id
     */
    @TableField("receiver_uid")
    private Integer receiverUid;
}
