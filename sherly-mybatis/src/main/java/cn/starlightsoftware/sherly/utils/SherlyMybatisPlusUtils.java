package cn.starlightsoftware.sherly.utils;

import cn.starlightsoftware.sherly.model.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author 谷子毅
 * @date 2023/3/16
 */
public class SherlyMybatisPlusUtils {

    public static <T> Page<T> getPage(PageQuery pageQuery) {
        return new Page<>(pageQuery.getCurrent(), pageQuery.getSize());
    }

}
