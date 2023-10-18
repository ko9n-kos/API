package pojo;

import lombok.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static commonLogic.PropertyReader.getProperties;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    public static final String usersRequest = "/users/";
    private Integer id;
    private String name;
    private String username;
    private String email;
    private AddressData address;
    private String phone;
    private String website;
    private CompanyData company;

    public static String getExpectedUser5() throws IOException {
        return FileUtils.readFileToString(new File(getProperties("config.properties", "expectedUser5Json")), StandardCharsets.UTF_8);
    }
}