package castlab.assignment.isoFileFormat.service;

import castlab.assignment.isoFileFormat.dto.Mp4Box;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

@Service
public class Mp4Service {
    public String analyzeMp4(String url) {
        String byteArray=null;
        try (InputStream inputStream = new URL(url).openStream()){
            byte[] bytes = inputStream.readAllBytes();
            byteArray = Arrays.toString(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return byteArray;
    }

    private Mp4Box convertToBox (byte[] bytes, int offset){

    }
}
