package ir.mjimani.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResCountDto {
    public enum FN {
        count
    }

    private Long count = 0L;
}
