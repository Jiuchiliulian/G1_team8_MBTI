package com.diary.bace.handler;

import com.diary.bace.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;


/**
 *
 * @ControllerAdvice 配合 @ExceptionHandler 实现全局异常处理
 * ControllerAdvice本质上是一个Component
 * 这是定义的全局处理异常
 */
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {


    /**
     * 全局所有异常捕获
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value =Exception.class)
    public Result exceptionHandler(Exception e){
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.error("内部服务器错误！");
    }



    /**
     * 单独数据库唯一字段提取异常
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(DuplicateKeyException.class)
    public Result catchException(DuplicateKeyException e){
        //getCause()方法把被包装的原始异常提取出来
        SQLIntegrityConstraintViolationException cause = (SQLIntegrityConstraintViolationException) e.getCause();
        //getMessage()方法用于返回有关异常的详细描述性消息
        String message = cause.getMessage();
        //按照空格切割，获取当前的账户名
        String name = message.split(" ")[2];
        String errMsg ="字段值:"+name+"已存在!";
        log.error(errMsg);
        log.error(e.getMessage());
        return Result.error("字段值:"+name+"已存在! 请重新输入");
    }

    /**
     * 空值异常捕获
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result exceptionHandler(MethodArgumentNotValidException e) {
        String errMsg = e.getBindingResult().getFieldError().getDefaultMessage();
        log.error(e.getMessage());
        return Result.error(errMsg);
    }



    /**
     * 文件异常捕获
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(FileNotFoundException.class)
    public Result exceptionHandler(FileNotFoundException e) {
        String errMsg ="系统找不到指定的文件";
        log.error(e.getMessage());
        return Result.error(errMsg);
    }



    /**
     * 自定义异常捕获
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(MyException.class)
    public Result exceptionHandler(MyException e){
        log.error(e.getMessage());
        return Result.error(e.getMessage());
    }


}
