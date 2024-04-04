package com.realman.becore.controller.api.services.models;

import java.util.Objects;

public record BranchId(Long value) {
    public Boolean hasValueEmpty() {
        return Objects.isNull(value);
    }
}
