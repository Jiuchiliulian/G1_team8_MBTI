package com.diary.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.diary.bace.vo.Result;
import com.diary.entity.Report;
import com.diary.service.ReportService;
import com.diary.vo.ReportVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 举报表 前端控制器
 * </p>
 *
 * @author diary
 * @since 2024-06-03
 */
@RestController
@RequestMapping("/report")
@Api(tags = "举报配置")
@ApiModel(value = "举报配置接口")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/add")
    @ApiOperation(value = "举报日记或评论")
    public Result<Report> add(@RequestBody Report report){
        return Result.ok(reportService.add(report));
    }

    @GetMapping("/reports")
    @ApiOperation(value = "获取待审核列表")
    public Result<List<Report>> reports(){
        return Result.ok(reportService.list(new LambdaQueryWrapper<Report>().eq(Report::getStatus,0)));
    }

    @GetMapping("/update")
    @ApiOperation(value = "处理举报内容")
    public Result<String> update(ReportVo reportVo){
        String mes = reportService.updateReport(reportVo);
        return Result.ok(mes);
    }

}

