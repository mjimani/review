package ir.mjimani.demo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = Provider.ENTITY_NAME)
@NoArgsConstructor
public class Provider extends BaseDomain {

    @Transient
    public static final String ENTITY_NAME = "provider";

    public enum CN {
        name
    }

    private String name;

    public Provider(String providerId) {
        id = providerId;
    }
}
