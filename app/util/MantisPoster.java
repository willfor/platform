package util;

import static util.MantisConstant.ENCODE_UTF8;
import static util.MantisConstant.HANDLER;
import static util.MantisConstant.MANTIS_URL;
import static util.MantisConstant.REPORTER;
import static util.MantisConstant.REPRODUCIBILITY;
import static util.MantisConstant.SEVERITY;
import static util.MantisConstant.TREAT_IDEA;

import java.net.URLEncoder;

import play.Logger;
import enums.IssueType;
import enums.MantisMethodName;

public class MantisPoster {
	public static String postInsertToMantis(IssueType issueType, String detail,
			String versionCode) {
		return HttpUtil.sendPost(
				MANTIS_URL,
				constructPostParamToMantis(MantisMethodName.INSERT_NEW_BUG,
						issueType, detail, versionCode));
	}

	private static String constructPostParamToMantis(MantisMethodName menthod,
			IssueType issueType, String detail, String versionCode) {
		String timestamp = DateUtil.getCurrenttime();
		String token = MD5Util.stringToMD5("pp" + timestamp + "slQQHfUerV");
		try {
			String postParam = "method="
					+ URLEncoder.encode(menthod.getName(), ENCODE_UTF8)
					+ "&app_name=" + URLEncoder.encode("pp", ENCODE_UTF8)
					+ "&timestamp=" + URLEncoder.encode(timestamp, ENCODE_UTF8)
					+ "&token=" + URLEncoder.encode(token, ENCODE_UTF8)
					+ "&username=" + URLEncoder.encode(REPORTER, ENCODE_UTF8)
					+ "&" + getConstructedParam(issueType, detail, versionCode);
			return postParam;
		} catch (Exception e) {
			return null;
		}
	}

	private static String getConstructedParam(IssueType issueType,
			String detail, String versionCode) {
		try {
			String param = "project_id="
					+ URLEncoder.encode("844", ENCODE_UTF8)
					+ "&reporter="
					+ URLEncoder.encode(REPORTER, ENCODE_UTF8)
					+ "&handler="
					+ URLEncoder.encode(HANDLER, ENCODE_UTF8)
					+ "&category="
					+ URLEncoder.encode(issueType.getMantisCategory(),
							ENCODE_UTF8)
					+ "&reproducibility="
					+ URLEncoder.encode(REPRODUCIBILITY, ENCODE_UTF8)
					+ "&summary="
					+ URLEncoder.encode(issueType.getMantisCategory(),
							ENCODE_UTF8) + "&description="
					+ URLEncoder.encode(detail, ENCODE_UTF8) + "&version="
					+ URLEncoder.encode(versionCode, ENCODE_UTF8)
					+ "&treat_idea="
					+ URLEncoder.encode(TREAT_IDEA, ENCODE_UTF8) + "&severity="
					+ URLEncoder.encode(SEVERITY, ENCODE_UTF8);
			// + "&custom_field_10="
			// + URLEncoder.encode(arrsList.get(i)[1],
			// ENCODE_UTF8)
			return param;
		} catch (Exception e) {
			Logger.error("exception throws when construct parameter send to Mantis");
			return null;
		}
	}

}
