package castlab.assignment.isoFileFormat.service;

import castlab.assignment.isoFileFormat.dto.Mp4Box;
import castlab.assignment.isoFileFormat.util.Constants;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@Service
public class Mp4Service {
    public Mp4Box analyzeMp4(String url) {
        Mp4Box initialBox = null;
        try (InputStream inputStream = new URL(url).openStream()) {
            byte[] bytes = inputStream.readAllBytes();
            initialBox = convertToBox(bytes, 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return initialBox;
    }

    private Mp4Box convertToBox(byte[] fileBytes, int offset) {
        if (offset < 0 || offset + 8 > fileBytes.length) {
            throw new IllegalArgumentException("Invalid offset or length for converting to Mp4Box.");
        }

        long boxSize = readInt(fileBytes, offset);
        String boxType = new String(fileBytes, offset + 4, 4, StandardCharsets.US_ASCII);

        System.out.println("Offset: " + offset + ", BoxSize: " + boxSize + ", BoxType: " + boxType);

        Mp4Box mp4Box = new Mp4Box(boxSize, boxType);

        if (Constants.MOOF.equals(boxType) || Constants.TRAF.equals(boxType)) {
            // Check if the box has sub-boxes
            int subBoxOffset = offset + 8;

            // Iterate through sub-boxes
            while (subBoxOffset < offset + boxSize && subBoxOffset + 8 < fileBytes.length && subBoxOffset > 0) {
                long subBoxSize = readInt(fileBytes, subBoxOffset);
                Mp4Box subBox = convertToBox(fileBytes, subBoxOffset);
                mp4Box.addSubBox(subBox);

                // Move to the next sub-box
                subBoxOffset += subBoxSize;
            }
        }

        return mp4Box;
    }

    private long readInt(byte[] bytes, int offset) {
        if (bytes == null || offset < 0 || offset + 4 > bytes.length) {
            return 0;
        }
        return ByteBuffer.wrap(bytes, offset, 4).getInt();
    }
}
