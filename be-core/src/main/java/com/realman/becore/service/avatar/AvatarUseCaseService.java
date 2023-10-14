package com.realman.becore.service.avatar;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realman.becore.controller.api.account.models.AccountId;
import com.realman.becore.controller.api.avatar.models.AvatarId;
import com.realman.becore.dto.avatar.Avatar;
import com.realman.becore.util.response.PageRequestCustom;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvatarUseCaseService {
    @NonNull
    private final AvatarCommandService avatarCommandService;

    @NonNull
    private final AvatarQueryService avatarQueryService;

    @Transactional
    public void save(Avatar avatar, Long accountId) {
        avatarCommandService.save(avatar, accountId);
    }

    @Transactional
    public void update(AvatarId avatarId, Avatar avatar) {
        avatarCommandService.update(avatarId, avatar);
    }

    @Transactional
    public void delete(AvatarId avatarId) {
        avatarCommandService.delete(avatarId);
    }

    public Page<Avatar> findAll(AccountId accountId, PageRequestCustom pageRequestCustom) {
        return avatarQueryService.findAll(accountId, pageRequestCustom);
    }

    public Avatar findById(AvatarId avatarId) {
        return avatarQueryService.findById(avatarId);
    }
}
