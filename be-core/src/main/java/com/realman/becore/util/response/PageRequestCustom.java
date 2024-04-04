package com.realman.becore.util.response;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import lombok.Builder;

@Builder
public record PageRequestCustom(PageRequest pageRequest) {
    public static PageRequestCustom of(Integer pageSize, Integer current) {
        PageRequest pageRequest = PageRequest.of((current - 1), pageSize);
        return PageRequestCustom.builder().pageRequest(pageRequest).build();
    }

    public static PageRequestCustom of(Integer pageSize, Integer current, String sorter) {
        PageRequest pageRequest = PageRequest.of((current - 1), pageSize, Sort.by(sorter));
        return PageRequestCustom.builder().pageRequest(pageRequest).build();
    }

    public Integer current() {
        return pageRequest.getPageNumber() + 1;
    }
}
