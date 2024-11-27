package cn.starlightsoftware.sherly.utils;

import cn.hutool.core.collection.CollUtil;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 参考 yudao-cloud的CollectionUtils
 * @author 谷子毅
 */
public class StreamUtil {

    public static <T, R> List<R> toList(Collection<T> from, Function<T, R> func) {
        if (CollUtil.isEmpty(from)) {
            return new ArrayList<>();
        }
        return from.stream().map(func).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static <T, R> Set<R> toList(Collection<T> from, Function<T, R> func, Predicate<T> filter) {
        if (CollUtil.isEmpty(from)) {
            return new HashSet<>();
        }
        return from.stream().filter(filter).map(func).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    public static <T, R> Set<R> toSet(Collection<T> from, Function<T, R> func) {
        if (CollUtil.isEmpty(from)) {
            return new HashSet<>();
        }
        return from.stream().map(func).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    public static <T, R> Set<R> toSet(Collection<T> from, Function<T, R> func, Predicate<T> filter) {
        if (CollUtil.isEmpty(from)) {
            return new HashSet<>();
        }
        return from.stream().filter(filter).map(func).filter(Objects::nonNull).collect(Collectors.toSet());
    }

}
