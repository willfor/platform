package controllers.callback;

import play.mvc.Controller;
import controllers.BuildTaskTrigger;

public class TaskController extends Controller {
	private static BuildTaskTrigger taskTrigger = new BuildTaskTrigger();

	/**
	 * 
	 * will be call back when pack task finished
	 * 
	 * */
	public static void triggerJob(int packTaskId) {
		taskTrigger.triggerAutomationJobs(packTaskId);
	}

}
