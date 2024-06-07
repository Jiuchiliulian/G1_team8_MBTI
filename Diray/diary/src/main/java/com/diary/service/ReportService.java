package com.diary.service;

import com.diary.entity.Report;
import com.baomidou.mybatisplus.extension.service.IService;
import com.diary.vo.ReportVo;

/**
 * <p>
 * 举报表 服务类
 * </p>
 *
 * @author diary
 * @since 2024-06-03
 */
public interface ReportService extends IService<Report> {

    Report add(Report report);

    String updateReport(ReportVo reportVo);
}
