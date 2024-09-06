package sumit.app.notifications;

import android.util.Log;
import com.google.auth.oauth2.GoogleCredentials;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class AccessToken {
    private static final String firebaseMessagingScope =
            "https://www.googleapis.com/auth/firebase.messaging";

    public String getAccessToken() {
        try {
            String jsonString = "{\n" +
                    "  \"type\": \"service_account\",\n" +
                    "  \"project_id\": \"fcm-test-43a71\",\n" +
                    "  \"private_key_id\": \"bda6008d68698bf8380e3b324c6b9e1353a0b19f\",\n" +
                    "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC0ZghCLlV4sVVH\\nqCYZxe/X9TyqkNBla5mSu2I63P/BYtM5nXkLC/seYv/DSEnN2iqJzrdHM/eLMVMF\\nc7u97e4xyeLXA8rfK3s6v9lRHLB8y2BprXIIFPZhSHpKAMVlP0EABxGPChaGoJNa\\nbg4ADafBAPIvb21wKK3Ea/6o3Snm/ej3CmmbxSXiZ0K6ZPKsfK5YO7sSliccHm3Z\\niAhJ4Xx4ka3ou9H+o58misiuSyJuwxxEhLIftEmj0Xn1NUWJOlBFAPNJa9AjOA7H\\nFUlY3CgK2SbDd5e5JmdIimgEu9MHDJ7A/elTl5HeoyI9qwLPpNOxzKutSmtGTHrz\\n3NwaKqQHAgMBAAECggEAPuBzT3Hr25Hfmga7JO+PLdPtzbuEz1zGHdUPI1olEIf7\\n/FBtQJLv94LlB3E6PDQm8Hpq+jfcN5k9CSDyFnyIMbVeybeqLcfvfyYgPjvELfML\\nCzpnjPaVe4nyCbokEyqNYs+T6KnI+SqdwX6rhYw85LIKN8tcNyQOkPBdRgk0Eizm\\nr3o6lHz1sPdyKjRCcc04QmbHVt4dbs4DqSFIv8BWmTO+x43TMpwENHjdaQNTHqqh\\nw/lsk5vP1MMsauBoX+U5PXYve5zD5sgLE56Ju9xxYgivEFjl/KJC5FVuzMoYElYp\\n0UVRwpt22Dwp5xfDfdXMke/eX0wyRD0HvDDSbDn2CQKBgQDmCCmu/1dALhC+Nf77\\nqSgfk8Z6K99Kip+XRpyaTul2Gm9v7Pqs1YEjarb5pcJqJLEaJfzc/mpJONLsnu04\\ngo1U6e09G/4xj8eFr7sdso7SlKAWmOx5v4N5o+K19El1WvMBWcX4BqBfBLqsL8J+\\nqKygM/Rw5ilwSNc+bgqzHbGmmQKBgQDIw3wPT/7xtrk6oGZ5IK+IAhSMt1YieVml\\nxUecKiwpkDtKVgKkh/B+7Bn9SWd46EBSnRdVlKyAkMvN0SXQ1DP81UItXjoiLPvy\\n5XkwIjTpILvZjxEPwitY7x4WMqnXzxM1Tlknmxl8z41yM6hktZmdFT49Tw3pI0SE\\n4//0+/9jnwKBgQDYzYi77t0FGGWfYLTZ7Cxs1pKSQxdI4i5YHa0rooRXU8MD8o6w\\npun3v3SFBr2d3Mcupe8VOpRiPrRNX9ylDia2iWOX3PJPM/wQ+/eXndsOjhX0Nl1q\\nwh3gX54lcABsIzQ1aa80fMpZw9NMxhbB2agZtd4FKmiT85Mqu/xJpQrywQKBgQCF\\nX2jBe3Fw/uTbD8hD2/UAs/EW8o0kZd2qhP7nvhSMoCbjYXRr/WtFxnAVlnhJGHHy\\n8C2aSyS2XHsohjNLIk5PPxWxshGItr3CRbRWx8LCxV5GUAcTovjNIj2onYu7tPOb\\nzCrqZt1tzyy2m57ZGNOuSXiJnkUthqJ0bkuSYv7tHQKBgDhAmCwNgMz6ENpl7+ez\\nCyOx4ESLkKjW2KOLF8+LWb4ZFaCpCno+GK0vzQnbzXNrW0eVQpHJfifSNvnJuXl1\\nhTu+01h023iY1BZIjIamxF3dJnHJpmdqVaxa06P7cdW0FOtQoU+TZ560Q+AT1tYA\\nzX4t1rOv/0QmUK8BMuUHbrex\\n-----END PRIVATE KEY-----\\n\",\n" +
                    "  \"client_email\": \"firebase-adminsdk-5e7b3@fcm-test-43a71.iam.gserviceaccount.com\",\n" +
                    "  \"client_id\": \"113078598198895321027\",\n" +
                    "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                    "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                    "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                    "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-5e7b3%40fcm-test-43a71.iam.gserviceaccount.com\",\n" +
                    "  \"universe_domain\": \"googleapis.com\"\n" +
                    "}";
            InputStream stream = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
            GoogleCredentials googleCredentials = GoogleCredentials.fromStream(stream).createScoped(firebaseMessagingScope);
            googleCredentials.refresh();
            return googleCredentials.getAccessToken().getTokenValue();
        } catch (Exception e) {
            Log.d("asdf", "getAccessToken: " + e);
            return null;
        }
    }
}