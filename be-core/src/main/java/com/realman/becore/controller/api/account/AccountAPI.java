package com.realman.becore.controller.api.account;

import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Account")
@RequestMapping("/v1/account/{accountId}")
public interface AccountAPI {
        
}
