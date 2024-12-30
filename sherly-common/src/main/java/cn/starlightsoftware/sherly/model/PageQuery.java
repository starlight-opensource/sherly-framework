package cn.starlightsoftware.sherly.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页请求对象，所有分页请求DTO都需继承此类
 * @author 谷子毅
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageQuery {
    /** 当前页 */
    @Schema(description = "当前页")
    private Integer current = 0;

    /** 页大小 */
    @Schema(description = "页大小")
    private Integer size = 10;
}
