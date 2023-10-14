package com.realman.becore.controller.api.avatar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.realman.becore.controller.api.avatar.models.AvatarRequest;
import com.realman.becore.controller.api.avatar.models.AvatarResponse;
import com.realman.becore.util.response.PageImplResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Tag(name = "Avatar", description = "manage avatar API")
@RequestMapping("/v1/avatars")
public interface AvatarsAPI {

    @PostMapping
    void save(@RequestBody @Valid AvatarRequest avatar, Long accountId);

    @GetMapping
    PageImplResponse<AvatarResponse> findAll(
            @RequestParam(required = true, value = "accountId") Long accountId,
            @RequestParam(required = false, value = "sorter", defaultValue = "createdAt") String sorter,
            @RequestParam(required = false, value = "currentPage", defaultValue = "1") @Min(1) Integer current,
            @RequestParam(required = false, value = "pageSize", defaultValue = "20") Integer pageSize);
}
