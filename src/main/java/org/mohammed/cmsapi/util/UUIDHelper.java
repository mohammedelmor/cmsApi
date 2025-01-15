package org.mohammed.cmsapi.util;

import java.util.UUID;

public class UUIDHelper {
    private UUIDHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static UUID noHythenUUIDStringToUUID(String uuidString) {
        String uuidWithHyphens = uuidString.replaceFirst(
                "([0-9a-fA-F]{8})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]{4})([0-9a-fA-F]{12})",
                "$1-$2-$3-$4-$5"
        );
        return UUID.fromString(uuidWithHyphens);
    }
}
