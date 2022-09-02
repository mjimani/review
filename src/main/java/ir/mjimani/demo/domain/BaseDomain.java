package ir.mjimani.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseDomain {

    public enum BFN {
        id, creatorId, creationDate
    }

    @Id
    protected String id;
    protected String creatorId;
    protected Long creationDate;

    public void init() {
        creationDate = new Date().getTime();
        creatorId = "USER_ID_FROM_TOKEN";
    }
}
