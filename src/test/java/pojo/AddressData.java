package pojo;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressData {
    protected String street;
    protected String suite;
    protected String city;
    protected String zipcode;
    protected GeoData geo;
}