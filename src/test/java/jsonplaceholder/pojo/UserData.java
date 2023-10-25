package jsonplaceholder.pojo;

import com.google.gson.Gson;
import lombok.*;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static framework.PropertyReader.getProperties;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    public static Gson gson = new Gson();
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

    public static void checkUserFromList(List<UserData> users) throws IOException {
        UserData userWithId5 = users.stream().filter(x -> x.getId().equals(5)).findFirst().orElse(null);
        Assert.assertEquals(gson.toJson(userWithId5), getExpectedUser5());
    }

    public static void checkUser(UserData user) throws IOException {
        String actualUser = gson.toJson(user);
        Assert.assertEquals(actualUser, getExpectedUser5());
    }
}