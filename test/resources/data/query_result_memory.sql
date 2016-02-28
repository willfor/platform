select 
 mt.main_task_id,
 mt.package_task_id,
 mt.create_time AS mainTask_createTime,
 mt.end_time AS mainTask_endTime,
 st.sub_task_id,
 st.url AS report_url,
 st.result,
 st.creatTime AS subTask_createTime,
 st.endTime AS subTask_endTime,
 st.status AS subTask_status,
 tc.case_desc,
 m.memory,
 m.process,
 pt.ppserver,
 pt.channel,
 pt.url AS apk_download_url,
 pt.creatTime AS pt_creatTime,
 pt.doneTime AS pt_endTime,
 pt.status AS pt_status,
 pt.apkname,
 pt.sign,
 pt.typestatus,
 t.type_name,
 d.device_brand
from android_main_task mt, android_sub_task st,android_test_memory m,android_test_case tc,packtask pt,android_test_type t,android_device_info d
WHERE mt.main_task_id = st.main_task_id
AND mt.main_task_id = @main_task_id@
AND st.sub_task_id = m.sub_task_id
AND m.test_case_id = tc.test_case_id
AND  mt.package_task_id = pt.taskId
AND st.test_type_id = t.type_id
AND st.device_id = d.device_id