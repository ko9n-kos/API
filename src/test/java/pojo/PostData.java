package pojo;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostData {
    public static final String postsRequest = "/posts/";

    private Integer userId;
    private Integer id;
    private String title;
    private String body;
}