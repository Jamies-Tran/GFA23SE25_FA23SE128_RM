package com.realman.becore.util.response;

import java.util.List;

public record ListResponse<T>(
        List<T> values) {

}
