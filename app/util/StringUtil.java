package util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import models.po.AndroidSubTask;

public class StringUtil {

	public static <T extends Object> String convertListToString(List<T> srcList) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < srcList.size(); i++) {
			sb.append(srcList.get(i));
			if (i < srcList.size() - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	public static List<Integer> getAttributeList(List<AndroidSubTask> subTasks) {
		List<Integer> subTaskIds = new ArrayList<Integer>();
		for (AndroidSubTask subTask : subTasks) {
			if (!subTaskIds.contains(subTask.getSubTaskId())) {
				subTaskIds.add(subTask.getSubTaskId());
			}
		}
		return subTaskIds;
	}
	
	public static String getTrimedStr(String src){
		if(StringUtils.isNotEmpty(src)){
			return src.trim();
		}
		return src;
	}

}
