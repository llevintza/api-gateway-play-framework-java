package controllers;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.DataResponse;
import views.Person;

import java.util.ArrayList;
import java.util.Arrays;

public class DataController extends Controller {
    public DataController() {

    }

    public Result getDummyData() {
        //todo: move this into a repository and create a mocked injected repo
        ArrayList<Person> people = new ArrayList<Person>(
                Arrays.asList(
                        new Person("john", "doe"),
                        new Person("Tom", "Jackson"),
                        new Person("Harry", "Davidson")
                )
        );
        DataResponse<ArrayList<Person>> result = new DataResponse<>(people);
        return ok(Json.toJson(result));
    }
}
