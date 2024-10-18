package cn.starlightsoftware.sherly.mapper;

import cn.hutool.core.bean.BeanUtil;
import cn.starlightsoftware.sherly.model.PageQuery;
import cn.starlightsoftware.sherly.model.PageResult;
import cn.starlightsoftware.sherly.utils.SherlyMybatisPlusUtils;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.Db;

import java.util.Collection;
import java.util.List;

/**
 * 增强的 mapper 接口，方便 mapper 层的一些简单调用
 * @author 谷子毅
 * @date 2023/3/16
 */
public interface SherlyMapper<T> extends BaseMapper<T> {

    default <R> PageResult<R> selectPage(PageQuery pageQuery, Class<R> clazz, Wrapper<T> wrapper) {
        IPage<T> page = SherlyMybatisPlusUtils.getPage(pageQuery);
        selectPage(page, wrapper);
        List<R> result = BeanUtil.copyToList(page.getRecords(), clazz);
        return PageResult.build(result, page.getTotal());
    }

    default Page<T> selectPage(PageQuery pageQuery, Wrapper<T> wrapper) {
        Page<T> page = SherlyMybatisPlusUtils.getPage(pageQuery);
        return selectPage(page, wrapper);
    }

    default Long selectCount() {
        return selectCount(null);
    }

    default Long selectCount(SFunction<T, ?> field, Object value) {
        return selectCount(new LambdaQueryWrapper<T>().eq(field, value));
    }

    default T selectOne() {
        return selectList(null).get(0);
    }

    default T selectOne(SFunction<T, ?> field, Object value) {
        return selectOne(new LambdaQueryWrapper<T>().eq(field, value));
    }

    default List<T> selectList() {
        return selectList(null);
    }

    default <R> List<R> selectList(Class<R> clazz, Wrapper<T> wrapper) {
        List<T> list = selectList(wrapper);
        return BeanUtil.copyToList(list, clazz);
    }

    default List<T> selectList(SFunction<T, ?> field, Object value) {
        return selectList(new LambdaQueryWrapper<T>().eq(field, value));
    }

    default List<T> selectList(SFunction<T, ?> field, Collection<?> value) {
        return selectList(new LambdaQueryWrapper<T>().in(field, value));
    }

    default void insertBatch(Collection<T> entities) {
        Db.saveBatch(entities);
    }

    default int update(Wrapper<T> wrapper) {
        return update(null, wrapper);
    }

    default int delete(SFunction<T, ?> field, Object value) {
        return delete(new LambdaQueryWrapper<T>().eq(field, value));
    }

}
