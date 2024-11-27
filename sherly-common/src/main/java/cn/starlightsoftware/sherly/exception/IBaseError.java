package cn.starlightsoftware.sherly.exception;

/**
 * @author 谷子毅
 */
public interface IBaseError {

    /**
     * 获取错误码
     * @return code
     */
    String getCode();

    /**
     * 获取错误详情
     * @return message
     */
    String getMessage();
}
