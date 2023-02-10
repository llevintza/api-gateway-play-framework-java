package controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import views.SuccessResponse;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class ServerHealthController extends Controller {

//    private final HttpExecutionContext executionContext;
    private static final Logger logger = LoggerFactory.getLogger(ServerHealthController.class);

    public ServerHealthController() {
//        this.executionContext = null;
    }
//    @Inject
//    public ServerHealthController(HttpExecutionContext executionContext){
//        this.executionContext = executionContext;
//    }

    public Result healthcheck() {
        logger.debug("calling server health-check end-point");
        final SuccessResponse response = new SuccessResponse();
        logger.debug("returning healthy response", response);

        return ok(Json.toJson(response));
    }
}
