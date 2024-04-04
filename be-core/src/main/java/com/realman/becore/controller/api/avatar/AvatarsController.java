package com.realman.becore.controller.api.avatar;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;

import com.realman.becore.controller.api.avatar.models.AvatarModelMapper;
import com.realman.becore.controller.api.avatar.models.AvatarRequest;
import com.realman.becore.controller.api.avatar.models.AvatarResponse;
import com.realman.becore.dto.account.StaffId;
import com.realman.becore.dto.avatar.Avatar;
import com.realman.becore.service.avatar.AvatarUseCaseService;
import com.realman.becore.util.response.PageImplResponse;
import com.realman.becore.util.response.PageRequestCustom;

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
    public void save(List<AvatarRequest> avatarList, Long staffId) {
        List<Avatar> dtoList = avatarList.stream()
                .map(avatarModelMapper::toDto).toList();
        avatarUseCaseService.save(dtoList, new StaffId(staffId));
    }

    @Override
    public PageImplResponse<AvatarResponse> findAll(Long staffId, String sorter, Integer current, Integer pageSize) {
        PageRequestCustom pageRequestCustom = PageRequestCustom.of(pageSize, current, sorter);
        Page<Avatar> avatars = avatarUseCaseService.findAll(new StaffId(staffId), pageRequestCustom);
        Page<AvatarResponse> responses = avatars.map(a -> avatarModelMapper.toModel(a));
        return new PageImplResponse<>(
            responses.getContent(), 
            responses.getTotalElements(),
            responses.getTotalPages(),
            responses.getSize(),
            pageRequestCustom.current());
    }

}
