package com.realman.becore.util;

import java.util.List;

import lombok.Builder;

@Builder
public record CustomPagination<T>(List<T> contents) {
    public List<T> of(Integer current, Integer pageSize) {
        Integer fromIndex = current * pageSize;
        Integer toIndex = Math.min((current + 1) * pageSize, contents.size());
        return contents.subList(fromIndex, toIndex);
    }
}
