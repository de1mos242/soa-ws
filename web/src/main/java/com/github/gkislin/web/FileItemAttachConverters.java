package com.github.gkislin.web;

import com.github.gkislin.common.converter.Converter;
import com.github.gkislin.mail.MimeAttach;
import com.github.gkislin.mail.UrlAttach;
import org.apache.commons.fileupload.FileItem;

import javax.activation.DataHandler;
import java.io.File;
import java.net.URL;

/**
 * User: gkislin
 * Date: 16.09.13
 */
public class FileItemAttachConverters {
    public static final Converter<FileItem, UrlAttach> FILE_ITEM_URL_CONVERTER = new Converter<FileItem, UrlAttach>() {

        @Override
        public UrlAttach convert(FileItem fi) throws Exception {
            URL tempFileUrl = getTmpFile(fi);
            return new UrlAttach(fi.getName(), tempFileUrl.toString());
        }
    };

    public static final Converter<FileItem, MimeAttach> FILE_ITEM_MIME_CONVERTER = new Converter<FileItem, MimeAttach>() {

        @Override
        public MimeAttach convert(FileItem fi) throws Exception {
            URL tempFileUrl = getTmpFile(fi);
            return new MimeAttach(fi.getName(), new DataHandler(tempFileUrl));
        }
    };

    private static URL getTmpFile(FileItem fi) throws Exception {
        File tempFile = File.createTempFile("web_", null);
        fi.write(tempFile);
        return tempFile.toURI().toURL();
    }
}
