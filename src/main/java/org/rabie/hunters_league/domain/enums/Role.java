package org.rabie.hunters_league.domain.enums;

import java.util.Set;

public enum Role {
    MEMBER(Set.of("CAN_PARTICIPATE", "CAN_VIEW_RANKINGS", "CAN_VIEW_COMPETITIONS")),
    JURY(Set.of("CAN_PARTICIPATE", "CAN_VIEW_RANKINGS", "CAN_VIEW_COMPETITIONS", "CAN_SCORE")),
    ADMIN(Set.of("CAN_PARTICIPATE", "CAN_VIEW_RANKINGS", "CAN_VIEW_COMPETITIONS", "CAN_SCORE", "CAN_MANAGE_COMPETITIONS", "CAN_MANAGE_USERS", "CAN_MANAGE_SPECIES", "CAN_MANAGE_SETTINGS"));

    private final Set<String> permissions;

    Role(Set<String> permissions) {
        this.permissions = permissions;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

}
