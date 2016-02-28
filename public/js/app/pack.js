
$(document).ready(function(){
  initElementsDefaulValue();
  //loadFirstPageMainTasks();
  loadAndRenderAutomationTestTypes();
  loadAndRenderAgooConfigures();
  bindViewReportAction();
  bindDownloadAction();
  bindPackSubmitAction();
  loadTotalCountAndInitPagination();
});



function initElementsDefaulValue(){
	$("#barCode_branch").val("master");
	$("#channel").val("PP_24");
    constructBranchSelectHtml();
    constructVersionNameSelectHtml();
    constructVersionCodeSelectHtml();
    constructPackBranchesSelectHtml();
    
}



function constructPackBranchesSelectHtml(){
  var ajaxUrl = "/ajax/PackController/listEnabledBranches";
  $.get(ajaxUrl).success(function(data){
   	branchList = "";
	for(i in data){
		tmp = data[i];
		if(i==0){
		branchList+="<option value=\""+tmp+"\" selected=\"selected\" onchange=\"filterPackTasksByBranchName(this.value)\">"+tmp+"</option>";
		}else{
		branchList+="<option value=\""+tmp+"\" onchange=\"filterPackTasksByBranchName(this.value)\">"+tmp+"</option>";
		}
	}
	$('#branch_select_span').append("<select id=\"branch_select\">" + branchList + "</select>");
	$("#branch_select").change(
		function filterPackTasksByBranchName(){
		var ajaxUrl = "/ajax/PackController/filterByBranchName?branchName="+this.value+"&from=1&batchCount=100";
		 $.get(ajaxUrl).success(function(data){
	 		 renderMainTasks(data);
	 	});
	}
	);
  }).error(function(e){
		alert(e);
  });
}

function filterPackTasksByBranchName(branchName){
	var ajaxUrl = "/ajax/PackController/filterByBranchName?branchName="+branchName+"&from=1&branchName=100";
	 $.get(ajaxUrl).success(function(data){
	 	 renderMainTasks(data);
	 });
}


function constructBranchSelectHtml(){
  var ajaxUrl = "/ajax/PackController/listEnabledBranches";
  $.get(ajaxUrl).success(function(data){
   	branchList = "";
	for(i in data){
		tmp = data[i];
		if(i==0){
		branchList+="<option value=\""+tmp+"\" selected=\"selected\">"+tmp+"</option>";
		}else{
		branchList+="<option value=\""+tmp+"\">"+tmp+"</option>";
		}
	}
	$('#depBranch').append("<select id=\"dep_branch\">" + branchList + "</select>");
	$('#commonBranch').append("<select id=\"common_branch\">" + branchList + "</select>");
  }).error(function(e){
		alert(e);
  });

}

function loadAndRenderAgooConfigures(){
		var ajaxUrl = "/ajax/PackController/listAgooConfigures";
		$.get(ajaxUrl).success(function(data){
	   	agooConfigList = "";
		for(i in data){
			tmp = data[i];
			if(i==0){
			agooConfigList+="<option value=\""+tmp.key+"_"+tmp.secret+"\" selected=\"selected\">"+tmp.description+"</option>";
			}else{
			agooConfigList+="<option value=\""+tmp.key+"_"+tmp.secret+"\">"+tmp.description+"</option>";
			}
		}
		$('#agoo_config_span').append("<select id=\"agoo_configure_select\">" + agooConfigList + "</select>");
	  }).error(function(e){
			alert(e);
	  });
}


function constructVersionNameSelectHtml(){
  var ajaxUrl = "/ajax/PackController/listEnabledVersionNames";
  $.get(ajaxUrl).success(function(data){
   	branchList = "";
	for(i in data){
		tmp = data[i];
		if(i==0){
		branchList+="<option value=\""+tmp+"\" selected=\"selected\">"+tmp+"</option>";
		}else{
		branchList+="<option value=\""+tmp+"\">"+tmp+"</option>";
		}
	}
	//$('#versionname_span').append("<select id=\"versionname\">" + branchList + "</select>");
  }).error(function(e){
		alert(e);
  });

}



function constructVersionCodeSelectHtml(){
  var ajaxUrl = "/ajax/PackController/listEnabledVersionCodes";
  $.get(ajaxUrl).success(function(data){
   	branchList = "";
	for(i in data){
		tmp = data[i];
		if(i==0){
		branchList+="<option value=\""+tmp+"\" selected=\"selected\">"+tmp+"</option>";
		}else{
		branchList+="<option value=\""+tmp+"\">"+tmp+"</option>";
		}
	}
	$('#versioncode_span').append("<select id=\"versioncode\">" + branchList + "</select>");
  }).error(function(e){
		alert(e);
  });
}


function loadMainTasks(fromCount,pageCount){
  var ajaxUrl = "/ajax/PackController/listMainTasks?from=" + fromCount + "&batchCount=" + pageCount;
  $.get(ajaxUrl).success(function(data){
   	renderMainTasks(data);
  }).error(function(e){
		alert(e);
  });
}

function jumpToReportDisplay(mainTaskId){
  var ajaxUrl = "/ajax/PackController/jumpToReportDisplayPage?mainTaskId=" +mainTaskId;
  $.get(ajaxUrl).success(function(data){
   	
  }).error(function(e){
		alert(e);
  });



}

function renderMainTasks(branchInfos){
    $('#pack_task_list_tbody').empty();
	branchList = "";
	for(i in branchInfos){
	  tmp = branchInfos[i];
		branchList += "<tr>";
		branchList +="<td>"+tmp.mainTaskId+"</td>";
		branchList += "<td> PP手机助手</td>";
		branchList += "<td>"+tmp.versionName+"</td>";
		branchList += "<td>"+tmp.versionCode+"</td>";
		branchList += "<td>branch:"+tmp.mainBranch+"     tag:"+refineValue(tmp.mainTag)+"</td>";
		branchList += "<td>"+tmp.ppserverUrl+"</td>";
    //打包状态
    if(tmp.status == 'success'){
      branchList += "<td><span class='label label-success' >"+tmp.statusDescription+"</span></td>";
    }else if (tmp.status == 'failed') {
      branchList += "<td><span class='label label-important' >"+tmp.statusDescription+"</span></td>";
    }else if(tmp.status=='processing'){
      branchList += "<td><span class='label label-info' >"+tmp.statusDescription+"</span></td>";
    }else{
      branchList += "<td><span class='label' >"+tmp.statusDescription+"</span></td>";
    };

		branchList += "<td>"
		for(j in tmp.taskStatusInfos){
			taskStatusInfo = tmp.taskStatusInfos[j];
			branchList +=taskStatusInfo.taskName+":"+taskStatusInfo.taskStatusForDisplay + " ( "+ taskStatusInfo.taskId +  " ) <br/>";
		}
		branchList += "</td>";
    
		branchList += "<td><div class='progress progress-success'><div class='bar' style='width: "+ tmp.statusFinishPercentage*100+"%;'></div></div></td>";
		branchList +="<td>"+tmp.buildType+"</td>";
		branchList +="<td>"+tmp.createTime+"</td>";
		if(tmp.status == 'waiting'){
			branchList +="<td>N.A</td>";
		}else{
			branchList +="<td><a href=\"http://pp.ucweb.local:9000/application/report_display?mainTaskId="+tmp.mainTaskId+"\">查看报告</a></td>";
		}
		
		if(tmp.url !=null && tmp.url !=''){
			branchList += "<td><a class='btn btn-info' onclick='showPackDetial(\""+tmp.packTaskId+"\")'>详情</a></td>";
		}else{
			branchList +="<td>N.A</td>";	
		}
		branchList +="</tr>";
	};
	$('#pack_task_list_tbody').append(branchList);
}

function refineValue(value){
	if(value ==null || value == 'undefined'){
		return "空";
	}
	return value;
}

function showOrHideQRCode(id,isShow){
   if(isShow){
	 $("#"+id).show();
   }else{
   	 $("#"+id).hide();
   }
}

function showPackDetial(packTaskId){
    var ajaxUrl = "/ajax/PackController/getPackDetail?packTaskId=" +packTaskId;
  	$.get(ajaxUrl).success(function(packInfo){
   	  $("#download_url").attr("href",packInfo.apkDebugReleaseUrl);
	  $("#debug_mapping").attr("href",packInfo.debugMappingUrl);
	  $("#release_mapping").attr("href",packInfo.releaseMappingUrl);
	  bindApplyReleaseMappingFile(packInfo.releaseMappingUrl,packInfo.versionName);
	  if(packInfo.releaseMD5 !="" && packInfo.releaseMD5 !=null && packInfo.releaseMD5 !="undefined"){
	  	document.getElementById('release_apk_md5').innerHTML = packInfo.releaseMD5;
	  }
	  if(packInfo.fileSize !="" && packInfo.fileSize !=null && packInfo.fileSize !="undefined"){
	  	document.getElementById('release_apk_filesize').innerHTML = packInfo.fileSize;
	  }
	  document.getElementById('apk_done_time').innerHTML = packInfo.doneTime + "  ( " + packInfo.doneTimestamp+ " )";
	  document.getElementById('d_branch_info').innerHTML = "分支: " + packInfo.mainBranch + " Tag: " + packInfo.mainTag;
	  document.getElementById('d_ppserver_url').innerHTML = packInfo.ppserverUrl;
	  $('#qrcode').empty();
	  $('#qrcode').qrcode(packInfo.apkDebugReleaseUrl);
	  $("#packModal").modal("toggle");
  	}).error(function(e){
		alert(e);
 	});
}

function bindApplyReleaseMappingFile(releaseMappingFilePath,versionName){
	$("#btn_apply_mapping").click(function(){
         var ajaxUrl = "/ajax/PackController/applyMapping?mappingPath=" + releaseMappingFilePath +"&versionName="+versionName;
         $.get(ajaxUrl).success(function(data){
             $("#btn_apply_mapping").val("已启用");
             alert("启用成功!");
			}).error(function(e){
			 alert(e);
		});
  });

}






function loadAndRenderAutomationTestTypes(){
	var ajaxUrl = "/ajax/PackController/listAutomationTestTypes";
  	$.get(ajaxUrl).success(function(data){
   	  branchList = "";
	  for(i in data){
			tmp = data[i];
			if(parseInt(tmp.typeId)<=0){
				continue;
			}
			branchList+="<label class=\"checkbox inline\">";
			branchList+="<input type=\"checkbox\" name=\"automation_test_type\"  value=\""+tmp.typeId+"\">"+tmp.typeName+"</input>";
			branchList+="</label>";
	  }
	 $('#automationTypes_span').append(branchList);
  	}).error(function(e){
		alert(e);
 	});

}


function bindViewReportAction(){
	
}

function bindDownloadAction(){
	
}

// 提交打包信息
function bindPackSubmitAction () {
  $("#pack_submit").click(function(){
    var packInfo = collectPackInfo();
    var packInfoJson = JSON.stringify(packInfo);
     var ajaxUrl = "/ajax/PackController/savePackInfo?packInfoJson=" + packInfoJson;
             $.get(ajaxUrl).success(function(data){
                alert("保存成功!");
                $("#btn-pack-updown").click();//创建打包成功后，收缩打包页面
             	loadTotalCountAndInitPagination();
				}).error(function(e){
					alert(e);
   			});
  });

}


// 收集打包表单信息
function collectPackInfo (){
  var packInfo = new Object();
  packInfo.depBranch = $("#dep_branch").val();
  packInfo.depTag = $("#dep_tag").val();
  packInfo.commonBranch = $("#common_branch").val();
  packInfo.commonTag = $("#common_tag").val();
  packInfo.barCodeBranch = $("#barCode_branch").val();
  packInfo.barCodeTag = $("#barCode_tag").val();
  packInfo.ppserverUrl = $('#ppserverUrl').val();
  packInfo.versionName = $("#versionname").val();
  packInfo.versionCode = $("#versioncode").val();
  packInfo.channel = $("#channel").val();
  packInfo.signature = $("#pack_sign").val();
  packInfo.proguardResourceFiles=$('input[name="proguard_resource_files"]').is(':checked');
  var selectedAutomationTypes =[];
  $('input[name="automation_test_type"]:checked').each(function(){
   selectedAutomationTypes.push($(this).val());
  });
  packInfo.testTypes=selectedAutomationTypes;
  packInfo.buildType = 'manual_build';
  packInfo.agooConfigure = $("#agoo_configure_select").val();
  return packInfo;
}




var pageSize=10;
var totalCount =0;
 function initPaginationBinding() {
	$("#Pagination").pagination(totalCount, {
		num_edge_entries: 1, //边缘页数
		num_display_entries: 1, //主体页数
		callback: pageselectCallback,
		items_per_page: pageSize, //每页显示1项
		prev_text: "前一页",
		next_text: "后一页"
	});
 }
 
function pageselectCallback(pageIndex){
	loadMainTasks(pageIndex*pageSize, pageSize);
}


function loadTotalCountAndInitPagination(){
  var ajaxUrl = "/ajax/PackController/getMainTaskTotalCount";
  $.get(ajaxUrl).success(function(data){
   	totalCount = parseInt(data);
   	initPaginationBinding();
  }).error(function(e){
	 alert(e);
  });
}

