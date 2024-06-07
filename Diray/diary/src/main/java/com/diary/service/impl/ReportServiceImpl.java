package com.diary.service.impl;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diary.entity.Comment;
import com.diary.entity.Like;
import com.diary.entity.Report;
import com.diary.mapper.CommentMapper;
import com.diary.mapper.DiaryMapper;
import com.diary.mapper.LikeMapper;
import com.diary.mapper.ReportMapper;
import com.diary.service.CommentService;
import com.diary.service.DiaryService;
import com.diary.service.ReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.diary.vo.ReportVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>
 * 举报表 服务实现类
 * </p>
 *
 * @author diary
 * @since 2024-06-03
 */
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {

    @Autowired
    private DiaryMapper diaryMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private LikeMapper likeMapper;

    @Override
    public Report add(Report report) {
        report.setStatus(0);
        String uuid = getUUID();
        report.setReportId(uuid);
        this.save(report);
        return report;
    }

    @Override
    @Transactional
    public String updateReport(ReportVo reportVo) {
        Report report = this.getById(reportVo.getReportId());
        if (reportVo.getReason().equals("delete")){
            if (report.getReportType().equals("diary")) {
                likeMapper.delete(new LambdaQueryWrapper<Like>().eq(Like::getDiaryId,report.getTargetId()));
                commentMapper.delete(new LambdaQueryWrapper<Comment>().eq(Comment::getDiaryId,report.getTargetId()));
                diaryMapper.deleteById(report.getTargetId());
                report.setStatus(1);
                report.setAction(reportVo.getAction());
                report.setOperationReason(reportVo.getReason());
                this.updateById(report);
                return "举报内容处理成功，已删除日记。";
            }else {
                commentMapper.deleteById(report.getTargetId());
                report.setStatus(1);
                report.setAction(reportVo.getAction());
                report.setOperationReason(reportVo.getReason());
                this.updateById(report);
                return "举报内容处理成功，已删除评论。";
            }
        }
        report.setStatus(1);
        report.setAction(reportVo.getAction());
        report.setOperationReason(reportVo.getReason());
        this.updateById(report);
        return "内容处理成功";
    }


    /**
     * 生成UUID
     * @return
     */
    public String getUUID(){
        ThreadLocalRandom random = ThreadLocalRandom.current();
        //32位UUID
        return new UUID(random.nextLong(), random.nextLong()).toString().replace("-", "");
    }
}
