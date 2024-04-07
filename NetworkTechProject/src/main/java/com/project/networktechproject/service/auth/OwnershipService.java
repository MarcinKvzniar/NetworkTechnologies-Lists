package com.project.networktechproject.service.auth;

import com.project.networktechproject.commonTypes.UserRole;
import com.project.networktechproject.infrastructure.entity.AuthEntity;
import com.project.networktechproject.infrastructure.entity.LoanEntity;
import com.project.networktechproject.infrastructure.repository.AuthRepository;
import com.project.networktechproject.service.user.error.UserNotFound;
import org.springframework.security.core.Authentication;

public abstract class OwnershipService {
    protected final AuthRepository authRepository;

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

    public static AuthInfo getAuthInfo(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        UserRole role = null;
        var username = authentication.getName();
        var authorities = authentication.getAuthorities().stream().toList();
        if (!authorities.isEmpty()) {
            role = UserRole.valueOf(authorities.getFirst().getAuthority());
        }
        return new AuthInfo(role, username);
    }

    public boolean isOwnerOrAdmin(AuthInfo auth, Long userId) {
        if (userId == null || auth == null) {
            return false;
        }

        return auth.role() != null && auth.role().equals(UserRole.ROLE_ADMIN) || isOwner(auth.username(), userId);
    }

    public record AuthInfo(UserRole role, String username) {
    }
}
