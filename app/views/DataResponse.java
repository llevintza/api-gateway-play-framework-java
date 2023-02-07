package views;

public class DataResponse<TData> extends Response {
    public TData data;

    public DataResponse(TData data){
        this.data = data;
    }
}
