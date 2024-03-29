package com.project.networktechproject.service.auth;

import com.project.networktechproject.infrastructure.entity.AuthEntity;
import com.project.networktechproject.infrastructure.repository.AuthRepository;
import com.project.networktechproject.service.user.error.UserNotFound;

public abstract class OwnershipService {
    private final AuthRepository authRepository;

    public OwnershipService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public boolean isOwner(String username, Long userId) {
        if (userId == null || username == null) {
            return false;
        }

        AuthEntity auth = authRepository
                .findByUsername(username)
                .orElseThrow(() -> UserNotFound.createWithUsername(username));

        return userId == auth.getUser().getId();
    }
}
