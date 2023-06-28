package fullstackblog.apigatewayservice;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Service
public class BinarySerevice {

    @Value("${minio.address}")
    private String minIOAddress;

    @Value("${minio.accessKey}")
    private String accessKey;

    @Value("${minio.secretKey}")
    private String secretKey;

    @Value("${minio.bucket}")
    private String bucket;

    @Value("${minio.objectName}")
    private String objectName;

    @Value("classpath:/test-file.png")
    private Resource resource;

    @PostConstruct
    public void init() {
        try {

            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint(minIOAddress)
                            .credentials(accessKey, secretKey)
                            .build();

            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())) {
                minioClient.makeBucket(
                        MakeBucketArgs
                                .builder()
                                .bucket(bucket)
                                .build()
                );
            }

            minioClient.putObject(PutObjectArgs
                    .builder()
                    .bucket(bucket)
                    .object(objectName)
                    .stream(resource.getInputStream(), -1, 10485760).build()
            );
        } catch (Exception e) {
            log.error("Can't connect to MinIO", e);
        }
    }

    public byte[] getFile() throws ServerException, InsufficientDataException, ErrorResponseException, IOException,
            NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException,
            InternalException {

        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(minIOAddress)
                        .credentials(accessKey, secretKey)
                        .build();

        InputStream stream =
                minioClient.getObject(GetObjectArgs
                        .builder()
                        .bucket(bucket)
                        .object(objectName)
                        .build());

        return stream.readAllBytes();
    }
}
