package controllers.outer;

import models.PlatformDao;
import models.po.AndroidIssueDetail;
import play.Logger;
import play.mvc.Controller;
import util.MantisPoster;

import com.uc.jtest.assertion.JsonAssertion;

import enums.IssueType;

public class IssueReporter extends Controller {
	private static PlatformDao dao = new PlatformDao();
	
	
	public static void report() {
		String reportJsonStr = params.get("body");
		String type = JsonAssertion.getValue(reportJsonStr, "type");
		String detail = JsonAssertion.getValue(reportJsonStr, "detail");
		String versionCode = JsonAssertion.getValue(reportJsonStr, "versionCode");
		Logger.info("IssueReporter.report was triggered...");
		Logger.info("type:" + type + "detail:" + detail + "versionCode:" + versionCode);
		IssueType issueType = IssueType.find(type);
		String result = MantisPoster.postInsertToMantis(issueType,detail,versionCode);
		AndroidIssueDetail issueDetail = new AndroidIssueDetail();
		issueDetail = dao.saveIssue(issueDetail.issueType(issueType).issueDetail(detail).mantisId(JsonAssertion.getValue(result, "data")).issueFoundVersion(versionCode).status(JsonAssertion.getValue(result, "status")));
		Logger.info("save to mantis result:" + result);	
		renderJSON(result);
	}
	

}
