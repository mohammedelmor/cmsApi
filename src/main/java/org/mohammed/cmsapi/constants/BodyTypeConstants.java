package org.mohammed.cmsapi.constants;

public class BodyTypeConstants {
    private BodyTypeConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String name = "body type";

    public static final String BUCKET_NAME = "bodytype";

    // Logger Constants
    public static final String NOT_EXIST = String.format("This %s does not exist!", name);
    public static final String ALREADY_EXISTS = String.format("This %s already exists!", name);
}
