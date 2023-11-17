package castlab.assignment.isoFileFormat.service;

import castlab.assignment.isoFileFormat.dto.Mp4Box;
import castlab.assignment.isoFileFormat.dto.WebErrorDTO;
import castlab.assignment.isoFileFormat.exception.WebErrorException;
import castlab.assignment.isoFileFormat.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * The type Mp 4 service.
 */
@Service
public class Mp4Service {

    /**
     * The constant logger.
     */
    public static final Logger logger = LoggerFactory.getLogger(Mp4Service.class);
    /**
     * Analyze mp 4 mp 4 box.
     *
     * @param url the url
     * @return the mp 4 box
     */
    public Mp4Box analyzeMp4(String url) {
        logger.info("Mp4Service.analyzeMp4 - url : {}",url);
        Mp4Box initialBox = null;
        try (InputStream inputStream = new URL(url).openStream()) {
            byte[] bytes = inputStream.readAllBytes();
            initialBox = convertToBox(bytes, 0);
        } catch (IOException e) {
            logger.error("Mp4Service.analyzeMp4 - IOException",e);
            throw new WebErrorException(new WebErrorDTO(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), Constants.ErrorCode.ERRORCODE_CL_02));
        }
        return transformResponsePayload(initialBox);
    }

    /**
     * Convert to box mp 4 box.
     *
     * @param fileBytes the file bytes
     * @param offset    the offset
     * @return the mp 4 box
     */
    private Mp4Box convertToBox(byte[] fileBytes, int offset) {
        logger.info("Mp4Service.convertToBox - fileBytes : {}, offset : {}",fileBytes, offset);
        if (offset < 0 || offset + 8 > fileBytes.length) {
            logger.error("Mp4Service.convertToBox - WebErrorException");
            throw new WebErrorException(new WebErrorDTO(Constants.ErrorMessage.INVALID_OFFSET, HttpStatus.INTERNAL_SERVER_ERROR.value(), Constants.ErrorCode.ERRORCODE_CL_01));
        }

        long boxSize = read(fileBytes, offset);
        String boxType = new String(fileBytes, offset + 4, 4, StandardCharsets.US_ASCII);

        System.out.println("Offset: " + offset + ", BoxSize: " + boxSize + ", BoxType: " + boxType);

        Mp4Box mp4Box = new Mp4Box(boxSize, boxType);

        if (Constants.MOOF.equals(boxType) || Constants.TRAF.equals(boxType)) {
            // Check if the box has sub-boxes
            int subBoxOffset = offset + 8;

            // Iterate through sub-boxes
            while (subBoxOffset < offset + boxSize && subBoxOffset + 8 < fileBytes.length && subBoxOffset > 0) {
                long subBoxSize = read(fileBytes, subBoxOffset);
                Mp4Box subBox = convertToBox(fileBytes, subBoxOffset);
                mp4Box.addSubBox(subBox);

                // Move to the next sub-box
                subBoxOffset += subBoxSize;
            }
        }

        return mp4Box;
    }

    /**
     * Read long.
     *
     * @param bytes  the bytes
     * @param offset the offset
     * @return the long
     */
    private long read(byte[] bytes, int offset) {
        logger.info("Mp4Service.convertToBox - bytes : {}, offset : {}",bytes, offset);
        if (bytes == null || offset < 0 || offset + 4 > bytes.length) {
            return 0;
        }
        return ByteBuffer.wrap(bytes, offset, 4).getInt();
    }

    /**
     * Transform response payload mp 4 box.
     *
     * @param initialBox the initial box
     * @return the mp 4 box
     */
    private static Mp4Box transformResponsePayload(Mp4Box initialBox) {
        logger.info("Mp4Service.convertToBox - initialBox : {}",initialBox);
        Mp4Box mp4Box = new Mp4Box(initialBox.getSize(), initialBox.getType());
        mp4Box.setSubBoxes(initialBox.getSubBoxes());
        return mp4Box;
    }
}
