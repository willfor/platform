select@space@ s.test_type_id,t.type_name,t.request_url,s.sub_task_id 
@space@from android_main_task m, android_sub_task s,android_test_type t
@space@where m.main_task_id = s.main_task_id
@space@and s.test_type_id = t.type_id
@space@and m.package_task_id = @package_task_id@