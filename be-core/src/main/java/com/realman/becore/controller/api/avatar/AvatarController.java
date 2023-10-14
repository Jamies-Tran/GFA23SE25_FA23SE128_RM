package com.realman.becore.controller.api.avatar;

import org.springframework.web.bind.annotation.RestController;

import com.realman.becore.controller.api.avatar.models.AvatarId;
import com.realman.becore.controller.api.avatar.models.AvatarModelMapper;
import com.realman.becore.controller.api.avatar.models.AvatarRequest;
import com.realman.becore.controller.api.avatar.models.AvatarResponse;
import com.realman.becore.dto.avatar.Avatar;
import com.realman.becore.service.avatar.AvatarUseCaseService;
import com.realman.becore.util.response.ValueResponse;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AvatarController implements AvatarAPI{

    @NonNull
    private final AvatarUseCaseService avatarUseCaseService;

    @NonNull
    private final AvatarModelMapper avatarModelMapper;

    @Override
    public void update(Long avatarId, AvatarRequest avatar) {
        Avatar dto = avatarModelMapper.toDto(avatar);
        avatarUseCaseService.update(new AvatarId(avatarId), dto);
    }

    @Override
    public void delete(Long avatarId) {
        avatarUseCaseService.delete(new AvatarId(avatarId));
    }

    @Override
    public ValueResponse<AvatarResponse> findById(Long avatarId) {
        Avatar avatar = avatarUseCaseService.findById(new AvatarId(avatarId));
        AvatarResponse response = avatarModelMapper.toModel(avatar);
        return new ValueResponse<AvatarResponse>(response);
    }
    
}
