package org.mohammed.cmsapi.constants;

public class MuscleBalanceConstants {
    private MuscleBalanceConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String name = "muscle balance";

    public static final String BUCKET_NAME = "musclebalance";

    // Logger Constants
    public static final String NOT_EXIST = String.format("This %s does not exist!", name);
    public static final String ALREADY_EXISTS = String.format("This %s already exists!", name);
}
