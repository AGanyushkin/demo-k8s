package fullstackblog.apigatewayservice;

import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/binary")
public class BinaryStorageController {
    private final BinarySerevice binarySerevice;

    @GetMapping
    public ResponseEntity<byte[]> getFile() throws ServerException, InsufficientDataException, ErrorResponseException,
            IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException,
            InternalException {
        return ResponseEntity.ok()
                .header("Content-Type", "image/png")
//                .header("Content-Disposition", "attachment; filename=\"filename.png\"")
                .body(binarySerevice.getFile());
    }
}
