package com.realman.becore.service.avatar;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realman.becore.dto.account.StaffId;
import com.realman.becore.dto.avatar.Avatar;
import com.realman.becore.dto.avatar.AvatarId;
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
    public void save(List<Avatar> avatarList, StaffId staffId) {
        avatarCommandService.save(avatarList, staffId);
    }

    @Transactional
    public void update(AvatarId avatarId, Avatar avatar) {
        avatarCommandService.update(avatarId, avatar);
    }

    @Transactional
    public void delete(AvatarId avatarId) {
        avatarCommandService.delete(avatarId);
    }

    public Page<Avatar> findAll(StaffId staffId, PageRequestCustom pageRequestCustom) {
        return avatarQueryService.findAll(staffId, pageRequestCustom);
    }

    public Avatar findById(AvatarId avatarId) {
        return avatarQueryService.findById(avatarId);
    }
}
