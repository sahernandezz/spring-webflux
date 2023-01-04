package com.spring.webflux.springwebflux.component;

import com.spring.webflux.springwebflux.exception.RecordNotFoundException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

import static java.nio.file.Files.readAllBytes;

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

    public String obtenerFoto(final String img) {
        File file = new File(this.path + img + "." + this.imgFormat);
        byte[] fileContent;
        try {
            fileContent = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            throw new RecordNotFoundException("No se pudo encontrar la imagen");
        }
        return Base64.encode(Unpooled.wrappedBuffer(fileContent)).toString();
    }
}
