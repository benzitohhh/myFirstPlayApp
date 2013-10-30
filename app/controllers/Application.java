package controllers;

import play.libs.F.Function;
import play.libs.F.Function0;
import play.libs.F.Promise;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

    public static Result index() {
    	
    	  String user = session("connected");
    	  if(user != null) {
    	    return ok("Hello " + user);
    	  } else {
    		  session("connected", "bennn@gmail.com"); // drops play cookie
    		  return unauthorized("Oops, you are not connected. "
    		  		+ "But I dropped a new cookie for you...");
    	  }
    	  
    	  // session().clear(); // discards session
    }
    
    public static Promise<Result> promise() {

    	// Promise<Integer>
		Promise<Integer> promiseOfInt = Promise
				.promise(new Function0<Integer>() {
					public Integer apply() {
						return 1 + 2; // intensiveComputation();
					}
				});

		// Promise<Result>
		return promiseOfInt.map(
				// The callback
				new Function<Integer, Result>() {
					public Result apply(Integer i) {
						return ok("Got result: " + i);
					}
				});
	}

}
