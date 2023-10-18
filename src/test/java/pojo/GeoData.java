package pojo;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeoData {
    protected String lat;
    protected String lng;
}