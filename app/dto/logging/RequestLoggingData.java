package dto.logging;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class RequestLoggingData implements Serializable {
    @JsonProperty("method")
    public String method;

    @JsonProperty("uri")
    private String uri;

    @JsonProperty("status")
    private int status;

    @JsonProperty("duration")
    private long duration;


    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
