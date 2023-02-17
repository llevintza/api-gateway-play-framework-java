package views;

public class Response {
    private final String SUCCESS_STATUS = "ok";
    public String status;

    public Response() {
        this.status = SUCCESS_STATUS;
    }

    public Response(String status) {
        this.status = status;
    }
}
