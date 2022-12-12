package com.spring.webflux.springwebflux.component;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class ImgComponent {

    @Value("${config.uploads.path}")
    private String path;

    @Value("${img.default}")
    private String defaultImg;

    @Value("${img.format}")
    private String imgFormat;

    public String save(final MultipartFile file) {
        String result;
        String formatName = setNameFile(file.getName());
        try {
            file.transferTo(new File(path + formatName + "." + imgFormat));
            result = formatName;
        } catch (IOException e) {
            result = defaultImg;
        }
        return result;
    }

    public String defaultImg() {
        return path + defaultImg;
    }

    private String setNameFile(final String name) {
        return UUID.randomUUID() + "-" + name
                .replace(" ", "")
                .replace(":", "")
                .replace("\\", "");
    }

    @SneakyThrows
    public File obtenerFoto(final String img) {
        return new File(path + img + "." + imgFormat);
    }
}
