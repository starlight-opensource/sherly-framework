package cn.starlightsoftware.sherly.model;

import cn.starlightsoftware.sherly.exception.BizException;
import cn.starlightsoftware.sherly.exception.IBaseError;
import cn.starlightsoftware.sherly.exception.model.RpcExceptionModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static cn.starlightsoftware.sherly.exception.enums.GlobalErrorCodeEnum.SUCCESS;
import static cn.starlightsoftware.sherly.exception.enums.GlobalErrorCodeEnum.UNKNOWN;


/**
 * @author 谷子毅
 * @date 2023/3/16
 */
@Getter
@NoArgsConstructor
public class Result<T> {

    private String code;

    private String message;

    private RpcExceptionModel exceptionMessage;

    private Long timestamp;

    private T data;

    public Result(T data) {
        this.code = SUCCESS.getCode();
        this.message = SUCCESS.getMessage();
        this.timestamp = System.currentTimeMillis();
        this.data = data;
    }

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public Result(String code, String message, RpcExceptionModel exceptionMessage) {
        this.code = code;
        this.message = message;
        this.exceptionMessage = exceptionMessage;
        this.timestamp = System.currentTimeMillis();
    }

    public Result(String code, String message, RpcExceptionModel exceptionMessage, T data) {
        this.code = code;
        this.message = message;
        this.exceptionMessage = exceptionMessage;
        this.timestamp = System.currentTimeMillis();
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    public static Result<?> success() {
        return Result.success(null);
    }

    /**
     * 处理异常枚举
     *
     * @param error
     * @return
     */
    public static Result<?> error(IBaseError error) {
        return new Result<>(error.getCode(), error.getMessage());
    }

    /**
     * 不返回具体的异常详情
     *
     * @return
     */
    public static Result<?> error(String message) {
        return new Result<>(UNKNOWN.getCode(), String.format(UNKNOWN.getMessage(), message));
    }

    /**
     * 处理预编译的异常枚举
     *
     * @param error
     * @param message
     * @return
     */
    public static Result<?> error(IBaseError error, String message) {
        return new Result<>(error.getCode(), String.format(error.getMessage(), message));
    }

    /**
     * 处理未知异常
     *
     * @param e
     * @return
     */
    public static Result<?> error(RpcExceptionModel e) {
        return new Result<>(UNKNOWN.getCode(), String.format(UNKNOWN.getMessage(), e.getMessage()), e);
    }

    /**
     * 处理业务异常
     *
     * @param e
     * @return
     */
    public static Result<?> error(BizException e) {
        return new Result<>(e.getCode(), e.getMessage(), e.getRpcExceptionModel());
    }

    public static <T> Result<?> error(BizException e, T data) {
        return new Result<>(e.getCode(), e.getMessage(), e.getRpcExceptionModel(), data);
    }

    public void checkError() {
        if (code.equals(SUCCESS.getCode())) {
            return;
        }
        throw new BizException(code, message, exceptionMessage);
    }

    // 检查是否为异常状态码
    public boolean checkErrorCode() {
        if (code.equals(SUCCESS.getCode())) {
            return false;
        }
        return true;
    }

    @JsonIgnore
    public T getCheckedData() {
        this.checkError();
        return data;
    }
}
