import play.*;
import play.jobs.*;
import play.test.*;
import models.*;

//annotation to tell Play that we want to run this job synchronously at application start-up.
@OnApplicationStart
public class Bootstrap extends Job {

	 public void doJob(){
		 //check if the database is empty
		 if(User.count()==0){
			 Fixtures.loadModels("initial-data.yml");
		 }
	 }
}
