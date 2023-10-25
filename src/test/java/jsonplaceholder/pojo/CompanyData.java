package jsonplaceholder.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyData {
    protected String name;
    protected String catchPhrase;
    protected String bs;
}
