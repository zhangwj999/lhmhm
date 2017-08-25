<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html>
<head>
    <title>新业务登记</title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="<%=path%>/plug-in/bootstrap3/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=path%>/plug-in/bootstrap3/fontawesome/css/font-awesome.min.css">
    <script type="text/javascript">


    </script>
</head>
<body>

<div class="container">
    <div class="row text-center">
        <h2>理财登记页面</h2>
    </div>

    <hr/>
    <div class="row-fluid">
        <!--身份证信息-->
        <div id="personInfoDiv" class="col-sm-6">
            <form id="custForm">
                <table class="table table-bordered" width="98%" style="left:30px" cellpadding="2" cellspacing="0"
                       border="1" bordercolor="#000000">
                    <tbody>
                    <tr>
                        <td>姓名</td>
                        <td><input type="text" class="form-control" name="custName" id="custName"></td>
                        <td colspan="2" rowspan="4"><img
                                src="C:\Users\gang\Desktop\WEB3.0控件开发包V1.0.7_20150326\2015-10-10\192.168.1.112_1_1444432747961.jpg"
                                width="150" height="200" name="PhotoDisplay"/></td>
                    </tr>
                    <tr>
                        <td>性别</td>
                        <td>
                            <select id="sex" name="sex" class="form-control">
                                <option value="1">男</option>
                                <option value="0">女</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <select id="passType" name="passType" class="form-control">
                                <option value="1">身份证</option>
                                <option value="2">护照</option>
                                <option value="3">军官（士兵）证</option>
                            </select>
                        </td>
                        <td><input type="text" class="form-control" name="passId" id="passId" onchange="checkPassId()"></td>
                    </tr>
                    <tr>
                        <td>年龄</td>
                        <td><input type="text" class="form-control" name="age" id="age"></td>
                    </tr>
                    <tr>
                        <td>家庭地址</td>
                        <td colspan="2"><input type="text" class="form-control" name="custAddress" id="custAddress">
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
            <button class="btn btn-primary" onclick="Idcard();"><i class="fa fa-comment"></i>读取身份证信息</button>
            <button class="btn btn-warning" onclick="onclickStart();"><i class="fa fa-warning"></i>理财风险告知</button>

        </div>
        <!--理财视频区域-->
        <div id="videoDiv" class="col-sm-6">
            <div id="divPlugin" style="z-index: 10"></div>
	  <%--<button class="btn btn-primary" onclick="osdoverlays();"><i class="fa fa-video-camera"></i>动态叠加信息</button>--%>
            <%--<button class="btn btn-primary" onclick="realplay();"><i class="fa fa-video-camera"></i>开始预览</button>--%>
            <%--<button class="btn btn-primary" onclick="clickStopRealPlay();"><i class="fa fa-stop"></i>停止预览</button>--%>
            <button class="btn btn-primary" onclick="clickCapturePic();"><i class="fa fa-paragraph"></i>抓图</button>
            <%--<button class="btn btn-primary" onclick="controlRecord('192.168.1.112','1','1');"><i--%>
                    <%--class="fa fa-video-camera"></i>开始录像--%>
            <%--</button>--%>
            <%--<button class="btn btn-primary" onclick="controlRecordStop('192.168.1.112','1','1');"><i--%>
                    <%--class="fa fa-stop"></i>停止录像--%>
            <%--</button>--%>
            <button class="btn btn-primary" onclick="startPlayback();"><i class="fa fa-reply "></i>录像回放</button>
        </div>
        <!--理财视频区域 结束-->
    </div>
    <hr/>
    <div class="row text-center">
        <h2></h2>
    </div>
    <hr/>
    <!--业务办理 开始-->
    <div class="row">
        <form id="billForm">
            <!---隐藏数据区域-->
            <input name="beginTime" type="hidden">
            <input name="endTime" type="hidden">
            <input name="CapturePath" type="hidden">
            <!--获取NVR信息-->
            <input name="ip" type="hidden">
            <input name="port" type="hidden">
            <input name="username" type="hidden">
            <input name="password" type="hidden">
            <%--//主通道--%>
            <input id="channels" name="channels" type="hidden">

            <table class="table table-bordered" width="98%" style="left:30px" cellpadding="2" cellspacing="0" border="1"
                   bordercolor="#000000">
                <tbody>
                <tr>
                    <td>业务流水号</td>
                    <td>${billId} <input type="hidden" name="billId" id="billId" value=${billId}></td>
                    <td>理财室</td>
                    <td><select class="form-control" name="officeId">
                        <c:forEach var="office" items="${officeList}">
                            <option value="${office.officeId}">${office.officeeName}</option>
                        </c:forEach>

                    </select></td>
                </tr>
                <tr>
                    <td>业务类型</td>
                    <td>
                        <select class="form-control" name="billType" onchange="getDueDateByBussType(this)">
                            <c:forEach var="bussT" items="${bussytpe}">
                                <option value="${bussT.busstypeId}">${bussT.busstypeName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>签约状态</td>
                    <td><select class="form-control" name="signStatus">
                        <option>是</option>
                        <option>否</option>
                    </select></td>
                </tr>
                <tr>
                    <td>到期日期</td>
                    <td><input type="text" class="form-control" readonly="readonly" name="dueDate" id="dueDate"></td>
                    <td>备注</td>
                    <td><input type="text" class="form-control" name="note" id="note"></td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
    <div class="row">
        <button class="btn btn-primary" onclick="addNew();"><i class="fa fa-plus"></i>新增单据</button>
        <form id="productForm">
            <table id="billTable" class="table table-bordered" width="98%" style="left:30px" cellpadding="2"
                   cellspacing="0" border="1" bordercolor="#000000">
                <thead>
                <th>产品名称</th>
                <th>单据编号</th>
                <th>银行卡号</th>
                <th>份数</th>
                <th>单价</th>
                <th>金额</th>
                <th>备注</th>
                <th>操作</th>
                </thead>
                <tbody>

                </tbody>
            </table>
        </form>
    </div>
    <br/>
    <div id="row">
        <button class="btn btn-primary bottom-right" onclick="save();"><i class="fa fa-save"></i>保存订单</button>
    </div>
    <div style="width:100%;height:200px;"></div>
    <!--业务办理 结束-->
</div>
<br/>
<div id="myModal" class="modal fade" style="z-index:9999;">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">风险提示：</h4>
      </div>
      <div class="modal-body">
        为了确保我们的服务质量，接下来的理财业务的办理会录音录像，
        请您谅解。理财资金管理运用过程中，可能会面临多种风险因素，包括但不限于政策风险、延期风险、流动性风险、再投资风险、
        信息传递风险、理财产品不成立风险和不可抗力风险。相关风险因素可能导致您的本金及收益全部或部分损失。
        如果您对此已经了解，我们现在开始办理业务。
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal" onclick="onclickSecond()">确认</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
</body>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=path%>/plug-in/jquery/jquery-2.0.0.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=path%>/plug-in/bootstrap3/js/bootstrap.min.js"></script>
<script src="<%=basePath%>/webpage/com/lhmh/FinancialRegistration/codebase/webVideoCtrl.js"></script>
<script src="<%=basePath%>/webpage/com/lhmh/FinancialRegistration/nvr.js"></script>
<script>
	function setPrice(obj){
		 var productId = $(obj).val();
		 $.ajax({ //一个Ajax过程 
	            type : "post", //以post方式与后台沟通
	            async : false,
	            url : 'billMainController.do?getPriceByProductId',
	            data: {
	            	productId: productId
	            },
	            success : function(price) {
	          	 	if(price != null && !isNaN(price) ){
	          	 		price = parseFloat(price);
	          	 		price = price.toFixed(2);
	          	 		$(obj).parents("tr").find("input[name*='price']").val(price);
	          	 	}else{
	          	 		$(obj).parents("tr").find("input[name*='price']").val("0.00");
	          	 	}
	            }
	          });
		 
	}
	function getDueDateByBussType(obj){
		 var busstype = $(obj).val();
		 $.ajax({ //一个Ajax过程 
	            type : "post", //以post方式与后台沟通
	            async : false,
	            url : 'billMainController.do?getDueDateByBussType',
	            data: {
	            	busstype: busstype
	            },
	            success : function(dueDate) {
	          	 	if(dueDate != null){
	          	 		$("#dueDate").val(dueDate);
	          	 	}
	            }
	          });
		 
	}
	
	$(function(){
		var obj = $("select[name='billType']");
		getDueDateByBussType(obj);
	});
    function getProduct(){
        var busstype=$("select[name=billType]").val();
        var jsonobj = "";
        $.ajax({ //一个Ajax过程
            type: "post", //以post方式与后台沟通
            async: false,
            data: {
                busstype: busstype
            },
            url: 'billMainController.do?getPrdouct',
            dataType: 'json',//
            success: function (json) {
                jsonobj=json;
            }
        });
        return jsonobj;
    }
    //新增 单据
    function addNew() {
        //抓图操作
        clickCapturePicWithSlience();

        var json = getProduct();
        html = "";
        html += "<tr>";
        html += "<td><select name='productId' class='form-control' onchange='setPrice(this)'>" ;
        for(i=0;i<json.length;i++){
            html += "<option value='"+json[i].productId+"'>"+json[i].productName+"</option>";
        }
        html += "<td><input type='text' class='form-control' name='billNum'></td>";
        html += "<td><input type='text' class='form-control' name='puhCard'></td>";
        html += "<td><input type='text' class='form-control' name='qty' onblur='setAmt(this)' onchange='checkFloatOnly(this)'></td>";
        html += "<td><input type='text' class='form-control' name='price' onblur='setAmt(this)' onchange='checkFloatOnly(this)'></td>";
        html += "<td><input type='text' class='form-control' name='amt' onchange='checkFloatOnly(this)'></td>";
        html += "<td><input type='text' class='form-control' name='note'></td>";
        html += "<td><button  class='btn btn-danger' onclick='deleteTeamMete(this);' ><i class='fa fa-remove'  ></i>删除</button></td>";
        html += "</tr>";
        $("#billTable tbody").append(html);
        
        $tr = $("#billTable tbody").find("tr:last");
        setPrice($tr.find("select[name*='productId']"));
    }
    
 	function checkFloatOnly(obj){
		var reg = /(([\d]{1,12})(\.[\d]{1,2})?)/g;
		var val = $(obj).val();
		var rlt = reg.exec(val);
		if(rlt!=null && rlt.length>0){
			$(obj).val(rlt[0]);
		}else{
			$(obj).val('');
		}
 	}
 	
    function setAmt(obj){
    	
    	var qty = $(obj).parents("tr").find("input[name*='qty']").val();
    	var price = $(obj).parents("tr").find("input[name*='price']").val();
    	
		 if(qty == null || qty == ''){
			 $(obj).parents("tr").find("input[name*='amt']").val("0.00");
		 }else if(price == null || price==''){
			 $(obj).parents("tr").find("input[name*='amt']").val("0.00");
		 }else{
			 var amtVal = parseFloat(qty)*parseFloat(price);
			 amtVal = amtVal+"";
			 var pointInx = amtVal.indexOf(".");
			 if( pointInx !=-1 && amtVal.length>(pointInx+3) ){
				 amtVal = amtVal.substring(0,pointInx+3);
			 }
			 $(obj).parents("tr").find("input[name*='amt']").attr("value",amtVal);
		 }
		 
    }
    
    function checkPassId() 

    {   
    	var passType = $("#passType").val();
    	if(passType != '1'){
    		return true;
    	}
    	
		var num = $("#passId").val();
        num = num.toUpperCase(); 

        //身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X。   

        if (!(/(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(num))){ 
             alert('输入的身份证号长度不对，或者号码不符合规定！\n15位号码应全为数字，18位号码末位可以为数字或X。'); 
             return false; 
        }

	    //校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。 
	    //下面分别分析出生日期和校验位 

		var len, re; 
		
		len = num.length; 
		
		if (len == 15) 
		
		{ 
		
		re = new RegExp(/^(\d{6})(\d{2})(\d{2})(\d{2})(\d{3})$/); 
		
		var arrSplit = num.match(re); 
		
		//检查生日日期是否正确 
		
		var dtmBirth = new Date('19' + arrSplit[2] + '/' + arrSplit[3] + '/' + arrSplit[4]); 
		
		var bGoodDay; 
		
		bGoodDay = (dtmBirth.getYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4])); 
		
		if (!bGoodDay) 
		
		{ 
		
		          alert('输入的身份证号里出生日期不对！');   
		
		           return false; 
		
		} 
		
		else 
		
		{ 
		
		//将15位身份证转成18位 
		
		//校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。 
		
		          var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); 
		
		           var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'); 
		
		           var nTemp = 0, i;   
		
		            num = num.substr(0, 6) + '19' + num.substr(6, num.length - 6); 
		
		           for(i = 0; i < 17; i ++) 
		
		          { 
		
		                nTemp += num.substr(i, 1) * arrInt[i]; 
		
		           } 
		
		           num += arrCh[nTemp % 11];   
		
		            return num;   
		
		}   
		
		} 
		
		if (len == 18) 
		
		{ 
		
		re = new RegExp(/^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/); 
		
		var arrSplit = num.match(re); 
		
		//检查生日日期是否正确 
		
		var dtmBirth = new Date(arrSplit[2] + "/" + arrSplit[3] + "/" + arrSplit[4]); 
		
		var bGoodDay; 
		
		bGoodDay = (dtmBirth.getFullYear() == Number(arrSplit[2])) && ((dtmBirth.getMonth() + 1) == Number(arrSplit[3])) && (dtmBirth.getDate() == Number(arrSplit[4])); 
		
		if (!bGoodDay) 
		
		{ 
		
		alert(dtmBirth.getYear()); 
		
		alert(arrSplit[2]); 
		
		alert('输入的身份证号里出生日期不对！'); 
		
		return false; 
		
		}
		
		else 
		
		{ 
		
		//检验18位身份证的校验码是否正确。 
		
		//校验位按照ISO 7064:1983.MOD 11-2的规定生成，X可以认为是数字10。 
		
		var valnum; 
		
		var arrInt = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); 
		
		var arrCh = new Array('1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'); 
		
		var nTemp = 0, i; 
		
		for(i = 0; i < 17; i ++) 
		
		{ 
		
		nTemp += num.substr(i, 1) * arrInt[i]; 
		
		} 
		
		valnum = arrCh[nTemp % 11]; 
		
		if (valnum != num.substr(17, 1)) 
		
		{ 
		
		alert('18位身份证的校验码不正确！应该为：' + valnum); 
		
		return false; 
		
		} 
		
		return num;
		
		}
		
		}
		
		return false; 
		
	}   

    //删除单据
    function deleteTeamMete(isonObj) {
        $(isonObj).parent().parent().remove();
    }
    //读取身份证信息
    function Idcard() {
        var result;
        var cardsn;
        var IdrControl1;
        try {
            IdrControl1 = new ActiveXObject("IDRCONTROL.IdrControlCtrl.1");
//	    alert("已安装");
        } catch (e) {
            alert("控件未安装");
        }
        result = IdrControl1.ReadCard("1001", "");
        if (result == 1) {
            //姓名
            $("#custName").val(IdrControl1.GetName());

            //证件类型
            $("select[name='passType']").find("option").removeAttr("selected");
            $("select[name='passType']").find("option[value='1']").attr("selected", true);

            //身份证号码
            cardsn = IdrControl1.GetCode();
            $("#passId").val(cardsn);

            //计算年龄
            var birYear = IdrControl1.GetBirthYear();
            var currYear = new Date().getFullYear();
            $("#age").val(parseInt(currYear) - parseInt(birYear));

            //性别
            var sex = IdrControl1.GetSex();
            sex = (sex == '男' ? '0' : '1');
            var html = "";
            if(sex == '0'){
            	html += '<option value="1" selected="selected">男</option><option value="0">女</option>';
            }else{
            	html += '<option value="1">男</option><option value="0" selected="selected">女</option>';
            }
            $("select[name='sex']").html(html);

            //身份证照片
            var imgData = IdrControl1.GetJPGPhotobuf();
            document.all['PhotoDisplay'].src = 'data:image/jpeg;base64,' + imgData;


            //	$("#fileData").val(imgData);

// 			$("#formobj").ajaxSubmit({
// 				url:"billMainController.do?saveCustImg",
// 				type:"post",
// 				async:false,
// 				success:function(uploadDone){
// 					if(uploadDone=="0"){
// 					   alert("身份证图片上传失败！");
// 					}
// 				}
// 			});
        } else {
            if (result == -1)
                alert("端口初始化失败！");
            if (result == -2)
                alert("请重新将卡片放到读卡器上！");
            if (result == -3)
                alert("读取数据失败！");
            if (result == -4)
                alert("生成照片文件失败，请检查设定路径和磁盘空间！");
        }
    }
    //保存订单
    function save() {
    	 //抓图操作
        clickCapturePicWithSlience();
        var szTime = WebVideoCtrl.I_GetOSDTime();
        $("input[name='endTime']").val(szTime);
    	 
    	if(!checkPassId){
    		return false;
    	}
        //保存客户信息
        var custInfo = $("#custForm").serializeObject();
        //console.log("custInfo=" + JSON.stringify(custInfo));
        //保存订单信息
        var billInfo = $("#billForm").serializeObject();
        //console.log("billInfo=" + JSON.stringify(billInfo));
        //产品信息
        var o = [];
        $("#productForm").find('tr').each(function () {
            var $this = $(this);
            var $elements = $this.find('input, textarea, select')
            if ($elements.size() > 0) {
                var serialized = $elements.serialize();
                var item = $.toDictionary(serialized);
                o.push(item);
            }
        });
      //  console.log("productForm=" + JSON.stringify(o));
        //结束预览
        clickStopRealPlay();
//console.log(JSON.stringify(billInfo));
        //结束录像
       if( controlRecordStop('192.168.1.112','1','1')==1){
     	   //保存订单
           $.ajax({ //一个Ajax过程
               type: "post", //以post方式与后台沟通
               async: false,
               data: {
                   custInfo: JSON.stringify(custInfo),
                   billInfo: JSON.stringify(billInfo),
                   productForm: JSON.stringify(o)
               },
               url: 'billMainController.do?saveBill',
               dataType: 'json',//
               success: function (json) {
                   if(json.msg=="ok"){

                       window.location="businessAuditingController.do?fromSure&billId="+$("#billId").val();
                   }
               }
           }); 
       };
       


    }
    (function ($) {
        $.extend({
            toDictionary: function (query) {
                var parms = {};
                var items = query.split("&"); // split
                for (var i = 0; i < items.length; i++) {
                    var values = items[i].split("=");
                    var key = decodeURIComponent(values.shift());
                    var value = values.join("=")
                    parms[key] = decodeURIComponent(value);
                }
                return (parms);
            }
        })
    })(jQuery);
    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
    /**********************以下是视频信息**************************/
    var termainObj = null;
    function getNvrInfo() {
        //获取理财室id
        var officeId = $("select[name=officeId]").val();
        // alert(officeId);
        //存储理财信息
        //保存订单
        $.ajax({ //一个Ajax过程
            type: "post", //以post方式与后台沟通
            async: false,
            data: {officeId: officeId},
            url: 'billMainController.do?queryNvrByOfficeId',
            dataType: 'json',//
            success: function (json) {
                //赋值
                $("input[name=ip]").val(json.attributes.TERMINAL_IP);
                $("input[name=port]").val(json.attributes.TERMINAL_PORT);
                $("input[name=username]").val(json.attributes.USER_NAME);
                $("input[name=password]").val(json.attributes.USER_PASSWORD);
                termainObj = json.attributes.channelmap;
            }
        });
        var mainChannelId = 01;
        for (i = 0; i < termainObj.length; i++) {
            var obj = termainObj[i];
            //只预览主通道
            if ("0" == obj[1]) {
                mainChannelId = obj[0];
            }
        }
        $("#channels").val(mainChannelId);
    }
    /**
     *实时预览功能
     */
    function realplay() {
        //先获取nvr信息
        getNvrInfo();
        var ip = $("input[name=ip]").val();
        var port = $("input[name=port]").val();
        var username = $("input[name=username]").val();
        var password = $("input[name=password]").val();
        var chanenelId = $("#channels").val();
        var iRet = WebVideoCtrl.I_Login(ip, 1, port, username, password, {
            success: function (xmlDoc) {
                clickStartRealPlay(ip,chanenelId, '1');
               

            },
            error: function (xmlDoc) {
                alert(" 登录失败！" + xmlDoc);

            }
        });

        if (-1 == iRet) {
            clickStartRealPlay(ip,chanenelId, '1');
        }
    }
    // 抓图
    function clickCapturePic() {
        var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
                szInfo = "";

        if (oWndInfo != null) {
            var szChannelID = $("#channels").val(),
                    szPicName = oWndInfo.szIP + "_" + szChannelID + "_" + new Date().getTime(),
                    iRet = WebVideoCtrl.I_CapturePic(szPicName);
            if (0 == iRet) {
                szInfo = "抓图成功！";
                var xmlDoc = WebVideoCtrl.I_GetLocalCfg();
                var capturePath = $(xmlDoc).find("CapturePath").eq(0).text();
                //   alert(capturePath);
                $(window.parent.document).find("input[name='CapturePath']").val(capturePath);
                alert(oWndInfo.szIP + " " + szInfo);
            } else {
                szInfo = "抓图失败！";
            }

        }

    }
    function clickCapturePicWithSlience() {
        var oWndInfo = WebVideoCtrl.I_GetWindowStatus(g_iWndIndex),
                szInfo = "";

        if (oWndInfo != null) {
            var szChannelID = $("#channels").val(),
                    szPicName = oWndInfo.szIP + "_" + szChannelID + "_" + new Date().getTime(),
                    iRet = WebVideoCtrl.I_CapturePic(szPicName);
            if (0 == iRet) {
                szInfo = "抓图成功！";
                var xmlDoc = WebVideoCtrl.I_GetLocalCfg();
                var capturePath = $(xmlDoc).find("CapturePath").eq(0).text();
                //   alert(capturePath);
                $(window.parent.document).find("input[name='CapturePath']").val(capturePath);
              //  alert(oWndInfo.szIP + " " + szInfo);
            } else {
                szInfo = "抓图失败！";
            }

        }

    }
    /**
     * 手工录像功能
     * @param ip
     * @param chanenelId
     * @param officeId
     */
    function controlRecord(ip, chanenelId, officeId) {

        var ip = $("input[name=ip]").val();
        var port = $("input[name=port]").val();
        var username = $("input[name=username]").val();
        var password = $("input[name=password]").val();
        var chanenelId = $("#channels").val();
        //全部按照高清码流走
        chanenelId=chanenelId+"01";
       //获取理财室id
        var officeId = $("select[name=officeId]").val();
        //实际录像
        $.ajax({ //一个Ajax过程
            type: "post", //以post方式与后台沟通
            async: false,
            data: {ip: ip, officeId: officeId, chanenelId: chanenelId},
            url: 'billMainController.do?clickStartRecord',
            dataType: 'json',//
            success: function (json) {
              // alert("" + json.msg);
               if(json.msg=="ok"){
            	//alert("理财录像开始：请先阅读如下风险提示："); 
                 
                  // alert("为了确保我们的服务质量，接下来的理财业务的办理会录音录像，请您谅解。理财资金管理运用过程中，可能会面临多种风险因素，包括但不限于政策风险、延期风险、流动性风险、再投资风险、信息传递风险、理财产品不成立风险和不可抗力风险。相关风险因素可能导致您的本金及收益全部或部分损失。如果您对此已经了解，我们现在开始办理业务");
                 
               }
                //JSON.stringify(json);
                //存储开始时间
            }
        });
    }
    /**
     * 手工录像功能--结束
     * @param ip
     * @param chanenelId
     * @param officeId
     */
    function controlRecordStop(ip, chanenelId, officeId) {
        //存储结束时间

        var ip = $("input[name=ip]").val();
        var port = $("input[name=port]").val();
        var username = $("input[name=username]").val();
        var password = $("input[name=password]").val();
        var chanenelId = $("#channels").val();
        //全部按照高清码流走
        chanenelId=chanenelId+"01";
        //获取理财室id
        var officeId = $("select[name=officeId]").val();
        var beginTime = $("#beginTime").val();
        //  alert(beginTime);
        if (beginTime == '') {
            alert("请先点击开始录像！");
        } else {
            $.ajax({ //一个Ajax过程
                type: "post", //以post方式与后台沟通
                async: false,
                data: {ip: ip, officeId: officeId, chanenelId: chanenelId},
                url: 'billMainController.do?clickStopRecord',
                dataType: 'json',//
                success: function (json) {
                    //alert("" + json.msg);
                    var szTime = WebVideoCtrl.I_GetOSDTime();
                    $("input[name='endTime']").val(szTime);
                    return 1;
                }
            });
        }
	return 1;

    }
    /**
     *录像回放
     */
    function startPlayback() {
        var ip = $("input[name=ip]").val();
        var chanenelId = $("#channels").val();
        var beginTime =  $("input[name='beginTime']").val();
        var endTime = $("input[name='endTime']").val()
        if (beginTime != null && endTime != null) {

            clickStartPlayback(ip, chanenelId, beginTime, endTime);
        } else {
            alert("没有可以回放的录像！");
        }


    }
    /**
     *osd叠加信息
     */
     function osdoverlays(){
    	  var ip = $("input[name=ip]").val();
           var port = $("input[name=port]").val();
           var username = $("input[name=username]").val();
           var password = $("input[name=password]").val();
           var chanenelId = $("#channels").val();
           var overlays="";
    	 $.ajax({ //一个Ajax过程
             type: "post", //以post方式与后台沟通
             async: false,
             data: {ip: ip, port: port, chanenelId: chanenelId,username:username,password:password,overlaysText:overlays},
             url: 'billMainController.do?osdoverlays',
             dataType: 'json',//
             success: function (json) {
                 alert("" + json.msg);
                 var szTime = WebVideoCtrl.I_GetOSDTime();
                 $("input[name='endTime']").val(szTime);
             //    $("input[name='endTime']").val(szTime);
             }
         });
    }
    /**
    *1、风险告知书
     * 2、预览
     * 3、开始录像
     */
    function onclickStart(){
    	$('#divPlugin').hide();
         $('#myModal').modal();
	}
    function onclickSecond(){
    	$('#divPlugin').show();
    	  //预览
        realplay();

		//开始录像
		controlRecord('192.168.1.112', '1', '1');
		//抓图操作
		clickCapturePicWithSlience();
    }
</script>
</html>