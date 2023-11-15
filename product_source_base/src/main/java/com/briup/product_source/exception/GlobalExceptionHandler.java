package com.briup.product_source.exception;

import com.briup.product_source.result.Result;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * @author Hlmove
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
//        System.out.println("e.getClass() = " + e.getClass());
        e.printStackTrace();
        Result result;
        if (e instanceof ServiceException) {
            result = Result.failure(((ServiceException) e).getResultCode());
            // DuplicateKeyException  SqLException
            // If DuplicateKeyException 数据库重复
            // if SQLIntegrityConstraintViolationException 未知约束异常
        } else if (e instanceof DuplicateKeyException) {
//            System.out.println("\"hello\" = " + "hello");
            return Result.failure(2, "数据库约束异常，操作失败！");
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException me = (MethodArgumentNotValidException) e;
            BindingResult bindingResult = me.getBindingResult();
            if (bindingResult.hasFieldErrors()) {
                String defaultMessage = bindingResult.getFieldErrors().get(0).getDefaultMessage();
                return Result.failure(2, defaultMessage);
            }
            return Result.failure(2, "参数异常！");
        } else {
            result = Result.failure(500, "服务器意外错误：" + e.getMessage());
        }
        return result;
    }
}
