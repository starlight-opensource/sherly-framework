package cn.starlightsoftware.sherly.wrapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 对 mybatisPlus lambdaQueryWrapper包装
 * @author 谷子毅
 */
public class SherlyLambdaQueryWrapper<T> extends LambdaQueryWrapper<T> {

    public SherlyLambdaQueryWrapper<T> likeIfExist(SFunction<T, ?> column, String value) {
        if (StringUtils.hasText(value)) {
            return (SherlyLambdaQueryWrapper<T>) super.like(column, value);
        }
        return this;
    }

    public SherlyLambdaQueryWrapper<T> inIfExist(SFunction<T, ?> column, Collection<?> collect) {
        if (!CollectionUtils.isEmpty(collect)) {
            return (SherlyLambdaQueryWrapper<T>) super.in(column, collect);
        }
        return this;
    }

    public SherlyLambdaQueryWrapper<T> eqIfExist(SFunction<T, ?> column, Object object) {
        if (object != null) {
            return (SherlyLambdaQueryWrapper<T>) super.eq(column, object);
        }
        return this;
    }

    public SherlyLambdaQueryWrapper<T> neIfExist(SFunction<T, ?> column, Object object) {
        if (object != null) {
            return (SherlyLambdaQueryWrapper<T>) super.ne(column, object);
        }
        return this;
    }

    public SherlyLambdaQueryWrapper<T> gtIfExist(SFunction<T, ?> column, Object object) {
        if (object != null) {
            return (SherlyLambdaQueryWrapper<T>) super.gt(column, object);
        }
        return this;
    }

    public SherlyLambdaQueryWrapper<T> geIfExist(SFunction<T, ?> column, Object object) {
        if (object != null) {
            return (SherlyLambdaQueryWrapper<T>) super.ge(column, object);
        }
        return this;
    }

    public SherlyLambdaQueryWrapper<T> ltIfExist(SFunction<T, ?> column, Object object) {
        if (object != null) {
            return (SherlyLambdaQueryWrapper<T>) super.lt(column, object);
        }
        return this;
    }

    public SherlyLambdaQueryWrapper<T> leIfExist(SFunction<T, ?> column, Object object) {
        if (object != null) {
            return (SherlyLambdaQueryWrapper<T>) super.le(column, object);
        }
        return this;
    }

    public SherlyLambdaQueryWrapper<T> betweenIfExist(SFunction<T, ?> column, Object[] object) {
        if (object == null) {
            return this;
        }
        if (object[0] != null && object[1] != null) {
            return (SherlyLambdaQueryWrapper<T>) super.between(column, object[0], object[1]);
        }
        if (object[0] != null) {
            return (SherlyLambdaQueryWrapper<T>) ge(column, object[0]);
        }
        if (object[1] != null) {
            return (SherlyLambdaQueryWrapper<T>) le(column, object[1]);
        }
        return this;
    }

    public SherlyLambdaQueryWrapper<T> betweenIfExist(SFunction<T, ?> column, Object object1, Object object2) {
        if (object1 != null && object2 != null) {
            return (SherlyLambdaQueryWrapper<T>) super.between(column, object1, object2);
        }
        if (object1 != null) {
            return (SherlyLambdaQueryWrapper<T>) ge(column, object1);
        }
        if (object2 != null) {
            return (SherlyLambdaQueryWrapper<T>) le(column, object2);
        }
        return this;
    }

    public SherlyLambdaQueryWrapper<T> select(Class<T> entityClass, String query) {
        if (StringUtils.hasText(query)) {
            List<String> list = Arrays.asList(query.split(","));
            return (SherlyLambdaQueryWrapper<T>) super.select(entityClass, i -> list.contains(i.getProperty()));
        }
        return this;
    }

    public SherlyLambdaQueryWrapper<T> or() {
        return (SherlyLambdaQueryWrapper<T>)super.or();
    }
}
