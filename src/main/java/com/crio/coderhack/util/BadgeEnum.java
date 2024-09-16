package com.crio.coderhack.util;

public enum BadgeEnum {
    CODE_NINJA("Code Ninja"),
    CODE_CHAMP("Code Champ"),
    CODE_MASTER("Code Master");

    private final String badgeName;

    BadgeEnum(String badgeName) {
        this.badgeName = badgeName;
    }

    public String getBadgeName() {
        return badgeName;
    }

    public static BadgeEnum fromBadgeName(String badgeName) {
        for (BadgeEnum badge : BadgeEnum.values()) {
            if (badge.badgeName.equals(badgeName)) {
                return badge;
            }
        }
        throw new IllegalArgumentException("Unknown badge name: " + badgeName);
    }
}
