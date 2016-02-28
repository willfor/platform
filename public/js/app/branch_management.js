$(document).ready(function(){
    loadBranchInfos();
	bindSaveBranchButtonAction();
	loadAndRenderAutomationTestTypes();
});

function loadAndRenderAutomationTestTypes(){
	var ajaxUrl = "/ajax/PackController/listAutomationTestTypes";
  	$.get(ajaxUrl).success(function(data){
   	  branchList = "";
	  for(i in data){
			tmp = data[i];
			if(parseInt(tmp.typeId)<=0){
				continue;
			}
			branchList+="<input type=\"checkbox\" name=\"automation_test_type\"  value=\""+tmp.typeId+"\">"+tmp.typeName+"</input>";
	  }
	 $('#automationTypes_span').after(branchList);
  	}).error(function(e){
		alert(e);
 	});
}


function bindSaveBranchButtonAction(){
	$("#btn_save").click(function(){
		if($("#step2").hasClass("active")){
			 var branchInfo = collectBranchInfo();
			 if(branchInfo.branchName ==null || branchInfo.branchName == "" || trimStr(branchInfo.branchName)==""){
			 	alert("输入信息无效");
			 	return;
			 }
			 var ajaxUrl = "/ajax/PackController/saveBranchInfo?branchInfo=" + JSON.stringify(branchInfo);
             $.get(ajaxUrl).success(function(data){
             		loadBranchInfos();
				}).error(function(e){
					alert(e);
   				});
		};
	});
}

function trimStr(str){
	return str.replace(/(^\s*)|(\s*$)/g,"");
}


function changeBranchStatus(branchId,toStatus){
    	var ajaxUrl = "/ajax/PackController/updateBranchStatus?branchId=" + branchId + "&status=" + toStatus;
    	$.get(ajaxUrl).success(function(data){
   					loadBranchInfos();
        }).error(function(e){
					alert(e);
        });
}


function loadBranchInfos(){
   		$.ajax({
            url:'/ajax/PackController/listBranches',
            type:'get',
            success:function(data){
                renderBranchList(data);
            }
        });
}


function collectBranchInfo(){
		   var packInfo = new Object();
			packInfo.description = $("#branch_description").val();
			packInfo.branchName = $("#branch_name").val();
			packInfo.versionName = $("#versionname").val();
			packInfo.versionCode = $("#versioncode").val();
			packInfo.ppserverUrl = $('#ppserverUrl').val();
			packInfo.status = "enabled";
			var selectedAutomationTypes =[];
  			$('input[name="automation_test_type"]:checked').each(function(){
   				selectedAutomationTypes.push($(this).val());
  			});
  			packInfo.testTypes=selectedAutomationTypes;
			//packInfo.commonBranch=$("#branch").val();
			// packInfo.barCodeBranch=$("#inputBarCode").val();
		    //	packInfo.versionName=$("#versionname").val();
		    //	packInfo.versionCode=$("#versioncode").val();
		    //	packInfo.ppserverUrl=$('input[name="optionsRadios"][checked]').val();
		    
		    return packInfo;
			//return JSON.stringify(packInfo);
}



function renderBranchList(branchInfos){
    $('#branch_info_body').empty();
	branchList = "";
	for(i in branchInfos){
		tmp = branchInfos[i];
		branchList += "<tr>";
		branchList +="<td>"+tmp.description+"</td>";
		branchList += "<td>"+tmp.branchName+"</td>";
		branchList += "<td>"+tmp.versionName+"</td>";
		branchList += "<td>"+tmp.versionCode+"</td>";
		branchList += "<td>"+tmp.ppserverUrl+"</td>";
		branchList += "<td><span id=\"branch_status_"+tmp.branchId+"\">"+tmp.statusDescription+"</span></td>";
		if(tmp.status == 'enabled'){
			branchList +="<td><button class=\"btn btn-danger\" id=\"change_status_btn_"+tmp.branchId+"\"  onclick=\"changeBranchStatus("+tmp.branchId+",'disabled');\">停用</button></td>";
		}else if(tmp.status == 'disabled'){
			branchList +="<td><button class=\"btn btn-success\" id=\"change_status_btn_"+tmp.branchId+"\"  onclick=\"changeBranchStatus("+tmp.branchId+",'enabled');\">启用</button></td>";
		}
		branchList +="</tr>";
	};

	$('#branch_info_body').append(branchList);
// 	$('#branch_info_body').json2html(branchInfos, transforms.reportSheet);
// }

//   var transforms = {
// 			'reportSheet': [
// 				{"tag":"tr", "children":function(){return(json2html.transform(this,transforms.reportSheetGroup,{'events':true}));}}
// 	        ],
// 			'reportSheetGroup': [
// 						{"tag":"td class='wudm'", 'html':function(){return this.description;}},
// 						{"tag":"td", 'html':function(){return this.branchName;}},
// 						{"tag":"td", 'html':function(){return this.versionName;}},
// 						{"tag":"td", 'html':function(){return this.versionCode;}},
// 						{"tag":"td", 'html':function(){return this.status;}},
// 						{"tag":"td", 'html':function(){return this.ppserverUrl;}},
	       
//   ]};

}
