package com.realman.becore.dto.receptionist;

import lombok.Builder;

@Builder
public record Receptionist(
                Long receptionistId,
                Long accountId) {

}
