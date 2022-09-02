package ir.mjimani.demo.domain;

import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = User.ENTITY_NAME)
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseDomain {

    @Transient
    public static final String ENTITY_NAME = "users";

    public enum CN {
        email, firstName, lastName
    }

    private String email;
    private String firstName;
    private String lastName;
}
