package com.realman.becore.service.avatar;

import org.springframework.stereotype.Service;

import com.realman.becore.controller.api.avatar.models.AvatarId;
import com.realman.becore.dto.avatar.Avatar;
import com.realman.becore.dto.avatar.AvatarMapper;
import com.realman.becore.error_handlers.exceptions.ResourceNotFoundException;
import com.realman.becore.repository.database.avatar.AvatarEntity;
import com.realman.becore.repository.database.avatar.AvatarRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AvatarCommandService {

    @NonNull
    private final AvatarRepository avatarRepository;

    @NonNull
    private final AvatarMapper avatarMapper;

    public void save(Avatar avatar, Long accountId) {
        AvatarEntity entity = avatarMapper.toEntity(avatar, accountId);
        avatarRepository.save(entity);
    }

    public void update(AvatarId avatarId, Avatar avatar) {
        AvatarEntity entity = avatarRepository
                .findById(avatarId.value()).orElseThrow(ResourceNotFoundException::new);
        avatarMapper.updateEntity(entity, avatar);
        avatarRepository.save(entity);
    }

    public void delete(AvatarId avatarId) {
        avatarRepository.deleteById(avatarId.value());
    }
}
