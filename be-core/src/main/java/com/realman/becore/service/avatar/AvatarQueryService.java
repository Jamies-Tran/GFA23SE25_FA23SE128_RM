package com.realman.becore.service.avatar;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.realman.becore.controller.api.account.models.AccountId;
import com.realman.becore.controller.api.avatar.models.AvatarId;
import com.realman.becore.dto.avatar.Avatar;
import com.realman.becore.dto.avatar.AvatarMapper;
import com.realman.becore.error_handlers.exceptions.ResourceNotFoundException;
import com.realman.becore.repository.database.avatar.AvatarEntity;
import com.realman.becore.repository.database.avatar.AvatarRepository;
import com.realman.becore.util.response.PageRequestCustom;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvatarQueryService {
    @NonNull
    private final AvatarRepository avatarRepository;

    @NonNull
    private final AvatarMapper avatarMapper;

    public Page<Avatar> findAll(AccountId accountId, PageRequestCustom pageRequestCustom) {
        Page<AvatarEntity> entities = avatarRepository
                .findAll(accountId.value(), pageRequestCustom.pageRequest());
        return entities.map(entity -> avatarMapper.toDto(entity));
    }

    public Avatar findById(AvatarId avatarId) {
        AvatarEntity entity = avatarRepository
                .findById(avatarId.value()).orElseThrow(ResourceNotFoundException::new);
        return avatarMapper.toDto(entity);
    }
}
