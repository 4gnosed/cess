package cn.edu.cess.controller.admin.department;


import cn.edu.cess.base.AbstractClass;
import cn.edu.cess.constant.Constant;
import cn.edu.cess.entity.Vo.FileUrlVo;
import cn.edu.cess.entity.admin.department.Notice;
import cn.edu.cess.entity.admin.department.NoticeEnterprise;
import cn.edu.cess.entity.admin.department.NoticePicture;
import cn.edu.cess.result.Result;
import cn.edu.cess.result.ResultFactory;
import cn.edu.cess.service.admin.department.INoticeEnterpriseService;
import cn.edu.cess.service.admin.department.INoticePictureService;
import cn.edu.cess.service.admin.department.INoticeService;
import cn.edu.cess.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Gnosed Lu
 * @since 2020-05-11
 */
@RestController
@RequestMapping("/api")
public class NoticeController extends AbstractClass {
    @Resource(name = "redisTemplate")
    RedisTemplate<String,List<Notice>> redisTemplate;
    @Autowired
    INoticeService iNoticeService;
    @Autowired
    INoticePictureService iNoticePictureService;
    @Autowired
    INoticeEnterpriseService iNoticeEnterpriseService;

    @PostMapping("/admin/notice/img")
    public Result uploadImg(@RequestParam(value = "img") MultipartFile multipartFile, HttpServletRequest request) {
        if (multipartFile == null) {
            return ResultFactory.buildFailResult("文件为空");
        }
        //保存图片
        FileUrlVo fileUrlVo = FileUploadUtil.uploadImg(multipartFile, request);
        //保存图片-公告记录
        NoticePicture noticePicture = iNoticePictureService.save(fileUrlVo);
        //返回公告id
        return ResultFactory.buildSuccessResult(noticePicture);
    }

    @PostMapping("/admin/notice")
    public Result publicNotice(@RequestBody Notice notice) {
        iNoticeService.fillData(notice);
        if (iNoticeService.saveNotice(notice)) {
            List<Notice> list = redisTemplate.opsForValue().get(Constant.ALL_NOTICE_KEY);
            list.add(notice);
            redisTemplate.opsForValue().set(Constant.ALL_NOTICE_KEY,list,1, TimeUnit.DAYS);
            return ResultFactory.buildSuccessResult("");
        } else {
            return ResultFactory.buildFailResult("");
        }
    }

    @PutMapping({"/admin/notice", "/notice"})
    public Result updateNotice(@RequestBody Notice notice) {
        if (iNoticeService.updateNotice(notice)) {
            List<Notice> list = redisTemplate.opsForValue().get(Constant.ALL_NOTICE_KEY);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId().equals(notice.getId())) {
                    list.set(i, notice);
                    break;
                }
            }
            redisTemplate.opsForValue().set(Constant.ALL_NOTICE_KEY, list, 1, TimeUnit.DAYS);
            return ResultFactory.buildSuccessResult("");
        } else {
            return ResultFactory.buildFailResult("");
        }
    }

    @GetMapping("/noticeNormal/byPage")
    public Result getNormalNoticeByPage(@RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "10") Integer size) {
        return ResultFactory.buildSuccessResult(iNoticeService.listByPage(page, size, Constant.NORMAL_NOTICE_TYPE));
    }

    @GetMapping("/noticeGuide/byPage")
    public Result getGuideNoticeByPage(@RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer size) {
        return ResultFactory.buildSuccessResult(iNoticeService.listByPage(page, size, Constant.GUIDE_NOTICE_TYPE));

    }

    @GetMapping("/noticePolicy/byPage")
    public Result getPolicyNoticeByPage(@RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "10") Integer size) {
        return ResultFactory.buildSuccessResult(iNoticeService.listByPage(page, size, Constant.POLICY_NOTICE_TYPE));
    }

    @GetMapping("/notice")
    public Result getNoticeById(@RequestParam String noticeId) {
        Notice notice = iNoticeService.getById(noticeId);
        NoticeEnterprise noticeEnterprise = iNoticeEnterpriseService.getById(noticeId);
        if (noticeEnterprise != null) {
            notice.setEid(noticeEnterprise.getEid());
        }
        return ResultFactory.buildSuccessResult(notice);
    }

    @GetMapping("/admin/notice")
    public Result getAllNotice() {
        List<Notice> list = redisTemplate.opsForValue().get(Constant.ALL_NOTICE_KEY);
        if(list==null||list.size()==0){
            list = iNoticeService.list();
            redisTemplate.opsForValue().set(Constant.ALL_NOTICE_KEY,list,1, TimeUnit.DAYS);
        }
        return ResultFactory.buildSuccessResult(list);
    }

    @DeleteMapping("/admin/notice")
    public Result deleteNotice(@RequestParam() String nid) {
        if (iNoticeService.removeById(nid)) {
            List<Notice> list = redisTemplate.opsForValue().get(Constant.ALL_NOTICE_KEY);
            Iterator<Notice> it = list.iterator();
            while (it.hasNext()) {
                if(it.next().getId().equals(nid)){
                    it.remove();
                    break;
                }
            }
            redisTemplate.opsForValue().set(Constant.ALL_NOTICE_KEY,list,1, TimeUnit.DAYS);
            return ResultFactory.buildSuccessResult("");
        } else {
            return ResultFactory.buildFailResult("");
        }
    }

    @DeleteMapping("/admin/notice/deletes")
    public Result deleteNotices(@RequestBody() List<Notice> notices) {
        if (iNoticeService.deleteNotices(notices)) {
            redisTemplate.delete(Constant.ALL_NOTICE_KEY);
            return ResultFactory.buildSuccessResult("");
        } else {
            return ResultFactory.buildFailResult("");
        }
    }
}
