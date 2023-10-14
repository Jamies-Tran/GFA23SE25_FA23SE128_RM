package com.realman.becore.util.response;

import java.util.Objects;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public record PageRequestCustom(Integer pageSize, Integer current, Sort sort) {
    public PageRequest pageRequest() {
        if (Objects.nonNull(sort)) {
            PageRequestCustom custom = PageRequestCustom.of(pageSize, current, sort);
            return PageRequest.of(custom.current, custom.pageSize, custom.sort);
        } else {
            PageRequestCustom custom = PageRequestCustom.of(pageSize, current);
            return PageRequest.of(custom.current, custom.pageSize);
        }

    }

    public static PageRequestCustom of(Integer pageSize, Integer current) {
        return new PageRequestCustom(pageSize, current, null);
    }

    public static PageRequestCustom of(Integer pageSize, Integer current, Sort sort) {
        return new PageRequestCustom(pageSize, current, sort);

    }
}
