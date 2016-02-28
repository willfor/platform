package job;

import java.util.List;

import models.PlatformDao;
import models.po.AndroidMainTask;
import play.jobs.Every;
import play.jobs.Job;
import controllers.convertor.TaskConvertor;

@Every("10s")
public class TaskJob extends Job {
	private static PlatformDao dao = new PlatformDao();

	public void doJob() {
		System.out.println("Job started to scan....");
		dao.updatePackTasksExecutedLongTimeToFailed();
		dao.updateSubTasksExecutedLongTimeToFailed();
		dao.updateMainTaskStatus();
	}

	

}
