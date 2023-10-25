package jsonplaceholder.pojo;

import lombok.*;
import org.testng.Assert;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostData {
    private Integer userId;
    private Integer id;
    private String title;
    private String body;

    public static void checkIdSorting(List<PostData> posts) {
        List<Integer> ids = posts.stream().map(PostData::getId).toList();
        List<Integer> sortedIds = ids.stream().sorted().toList();
        Assert.assertEquals(ids, sortedIds);
    }


}