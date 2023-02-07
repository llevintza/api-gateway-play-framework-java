package controllers;

import views.SuccessResponse;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class ServerHealthController extends Controller {

//    private final HttpExecutionContext executionContext;

    public ServerHealthController() {
//        this.executionContext = null;
    }
//    @Inject
//    public ServerHealthController(HttpExecutionContext executionContext){
//        this.executionContext = executionContext;
//    }

    public Result healthcheck() {
        final SuccessResponse response = new SuccessResponse();
        return ok(Json.toJson(response));
    }
}
