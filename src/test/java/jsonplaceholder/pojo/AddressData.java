package jsonplaceholder.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
