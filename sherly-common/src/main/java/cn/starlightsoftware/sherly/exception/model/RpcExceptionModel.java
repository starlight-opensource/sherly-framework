package cn.starlightsoftware.sherly.exception.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 此对象为微服务间传递异常信息的载体，测试环境方便排查问题，生产环境过滤此对象不进行传输
 * @author 谷子毅
 * @date 2022/3/23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RpcExceptionModel {
    /** 异常描述 */
    private String message;

    /** 异常堆栈 */
    private List<String> errorStack;
}
