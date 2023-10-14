package com.realman.becore.util.response;

import java.util.List;

public record PageImplResponse<T>(
    List<T> content,
    Integer total,
    Integer pageSize,
    Integer current
) {

}
