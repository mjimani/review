package ir.mjimani.demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    protected Boolean success;
    private T data;
    protected List<String> messages;

    public Response<T> data(T data) {
        this.data = data;
        return this;
    }

    public Response<T> success(Boolean success) {
        this.success = success;
        return this;
    }

    public Response<T> message(List<String> messages) {
        this.messages = messages;
        this.success = false;
        return this;
    }

    public Response(T data) {
        success = true;
        this.data = data;
    }
}
