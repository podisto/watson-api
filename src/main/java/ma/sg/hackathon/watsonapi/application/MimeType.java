package ma.sg.hackathon.watsonapi.application;

import lombok.Getter;

import java.util.Arrays;

/**
 * Created by podisto on 26/03/2022.
 */
@Getter
public enum MimeType {

    FLAC("flac", "audio/flac"),
    MP3("mp3", "audio/mp3"),
    MPEG("mpeg", "audio/mpeg"),
    OGG("application/ogg", "audio/ogg"),
    WAV("wav", "audio/wav"),
    WEBM("webm", "audio/webm");

    private final String extension;
    private final String contentType;

    MimeType(String extension, String contentType) {
        this.extension = extension;
        this.contentType = contentType;
    }

    public static String getContentType(String extension) {
        return Arrays.stream(values())
                .filter(type -> type.extension.equalsIgnoreCase(extension))
                .map(MimeType::getContentType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown extension " + extension));
    }
}
