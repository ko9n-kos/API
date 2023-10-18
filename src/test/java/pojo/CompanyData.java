package pojo;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyData {
    protected String name;
    protected String catchPhrase;
    protected String bs;
}