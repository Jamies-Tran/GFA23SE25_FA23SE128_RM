package com.realman.becore.util.response;

import java.util.List;

public record PageImplResponse<T>(
    List<T> content,
    Long totalElements,
    Integer totalPages,
    Integer pageSize,
    Integer current
) {

}
