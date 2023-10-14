package com.realman.becore.controller.api.avatar;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RestController;

import com.realman.becore.controller.api.account.models.AccountId;
import com.realman.becore.controller.api.avatar.models.AvatarModelMapper;
import com.realman.becore.controller.api.avatar.models.AvatarRequest;
import com.realman.becore.controller.api.avatar.models.AvatarResponse;
import com.realman.becore.dto.avatar.Avatar;
import com.realman.becore.service.avatar.AvatarUseCaseService;
import com.realman.becore.util.response.PageImplResponse;
import com.realman.becore.util.response.PageRequestCustom;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AvatarsController implements AvatarsAPI {

    @NonNull
    private final AvatarUseCaseService avatarUseCaseService;

    @NonNull
    private final AvatarModelMapper avatarModelMapper;

    @Override
    public void save(AvatarRequest avatar, Long accountId) {
        Avatar dto = avatarModelMapper.toDto(avatar);
        avatarUseCaseService.save(dto, accountId);
    }

    @Override
    public PageImplResponse<AvatarResponse> findAll(Long accountId, String sorter, Integer current, Integer pageSize) {
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(pageSize, current, Sort.by(sorter));
        Page<Avatar> avatars = avatarUseCaseService.findAll(new AccountId(accountId), pageRequestCustom);
        Page<AvatarResponse> responses = avatars.map(a -> avatarModelMapper.toModel(a));
        return new PageImplResponse<>(responses.getContent(), responses.getTotalPages(), responses.getSize(),
                pageRequestCustom.current());
    }

}
