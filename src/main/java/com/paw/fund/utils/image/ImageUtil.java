package com.paw.fund.utils.image;

import com.paw.fund.configuration.handler.exceptions.ServiceException;
import com.paw.fund.enums.EMimeType;
import org.apache.tika.Tika;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;

public class ImageUtil {
    private static Tika tika = new Tika();

    public static EMimeType findMimeType(String url) {
        try (InputStream is = new URI(url).toURL().openStream()) {
            String mimeType = tika.detect(is);
            return EMimeType.findByCode(mimeType);
        } catch (Exception exc) {
            throw new ServiceException();
        }
    }
}
