package com.realman.becore.controller.api.avatar;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.realman.becore.controller.api.avatar.models.AvatarRequest;
import com.realman.becore.controller.api.avatar.models.AvatarResponse;
import com.realman.becore.util.response.ValueResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Avatar", description = "manage avatar API")
@RequestMapping("/v1/avatar/{avatarId}")
public interface AvatarAPI {

    @PutMapping
    void update(@PathVariable Long avatarId ,@RequestBody @Valid AvatarRequest avatar);

    @DeleteMapping
    void delete(@PathVariable Long avatarId);

    @GetMapping
    ValueResponse<AvatarResponse> findById(@PathVariable Long avatarId);
}
