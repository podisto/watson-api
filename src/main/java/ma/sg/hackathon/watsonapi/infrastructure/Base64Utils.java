package ma.sg.hackathon.watsonapi.infrastructure;

import java.util.Base64;

/**
 * Created by podisto on 27/03/2022.
 */
public class Base64Utils {

    public static byte[] toBase64(String encoded) {
        return Base64.getDecoder().decode(encoded);
    }

    public static String getContentTypeFromTag(String tag) {
        String[] split = tag.split(":");
        return split.length > 1 ? split[1] : split[0];
    }
}
