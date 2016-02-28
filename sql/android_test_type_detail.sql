/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.5.34-log 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `android_test_type_detail` (
	`typeId` int (11),
	`requestUrl` varchar (765),
	`type_detail_id` int (11)
); 
insert into `android_test_type_detail` (`typeId`, `requestUrl`, `type_detail_id`) values('-2','curl -X POST \"http://100.84.43.15:8080/jenkins/job/pp_pack_test_lyy_auto_build/buildWithParameters?token=dda5b27de1df20eedd4df9a7cd347fcd&pack_task_id=$param$\" --user pp_android:PK2dy65N\\(ZJDxE4R','1');
insert into `android_test_type_detail` (`typeId`, `requestUrl`, `type_detail_id`) values('-1','curl  http://100.84.43.15:9000/callback/TaskController/triggerJob?packTaskId=$param$','2');
insert into `android_test_type_detail` (`typeId`, `requestUrl`, `type_detail_id`) values('0','curl -X POST \"http://100.84.43.15:8080/jenkins/job/pp_ci_pack/buildWithParameters?token=dda5b27de1df20eedd4df9a7cd347fcd&pack_task_id=$param$\" --user pp_android:PK2dy65N\\(ZJDxE4R','3');
insert into `android_test_type_detail` (`typeId`, `requestUrl`, `type_detail_id`) values('1','curl -X POST \"http://100.84.43.15:8080/jenkins/job/pp_ci_test_cpu/buildWithParameters?token=dda5b27de1df20eedd4df9a7cd347fcd&sub_task_id=$param$\" --user pp_android:PK2dy65N\\(ZJDxE4R','4');
insert into `android_test_type_detail` (`typeId`, `requestUrl`, `type_detail_id`) values('2','curl -X POST \"http://100.84.43.15:8080/jenkins/job/pp_ci_test_memory1/buildWithParameters?token=dda5b27de1df20eedd4df9a7cd347fcd&sub_task_id=$param$\" --user pp_android:PK2dy65N\\(ZJDxE4R','5');
insert into `android_test_type_detail` (`typeId`, `requestUrl`, `type_detail_id`) values('3','curl -X POST \"http://100.84.43.15:8080/jenkins/job/pp_ci_test_js/buildWithParameters?token=dda5b27de1df20eedd4df9a7cd347fcd&sub_task_id=$param$\" --user pp_android:PK2dy65N\\(ZJDxE4R','6');
insert into `android_test_type_detail` (`typeId`, `requestUrl`, `type_detail_id`) values('4','curl -X POST \"http://100.84.43.15:8080/jenkins/job/pp_ci_test_download/buildWithParameters?token=dda5b27de1df20eedd4df9a7cd347fcd&sub_task_id=$param$\" --user pp_android:PK2dy65N\\(ZJDxE4R','7');
insert into `android_test_type_detail` (`typeId`, `requestUrl`, `type_detail_id`) values('5','curl -X POST \"http://100.84.43.15:8080/jenkins/job/pp_ci_test_ui/buildWithParameters?token=dda5b27de1df20eedd4df9a7cd347fcd&sub_task_id=$param$\" --user pp_android:PK2dy65N\\(ZJDxE4R','8');
insert into `android_test_type_detail` (`typeId`, `requestUrl`, `type_detail_id`) values('6','curl -X POST \"http://100.84.35.78:4456/jenkins/job/pp_ci_test_monkey/buildWithParameters??token=9abf8bea99873a62f74de6ba8922966d&pp_download_url=$package_url$&sub_task_id=$sub_task_id$\" --user pp_android:PK2dy65N\\(ZJDxE4R','9');
