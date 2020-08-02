$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'aicar/device/list',
        datatype: "json",
        colModel: [			
			{ label: '', name: 'createdate', index: 'createdate', width: 80 }, 			
			{ label: '截止日期', name: 'deadline', index: 'deadline', width: 80 }, 			
			{ label: 'deviceid', name: 'deviceid', index: 'deviceid', width: 50, key: true },
			{ label: '启用日期', name: 'enabledate', index: 'enabledate', width: 80 }, 			
			{ label: '', name: 'mobile', index: 'mobile', width: 80 }, 			
			{ label: '', name: 'model', index: 'model', width: 80 }, 			
			{ label: '', name: 'modelid', index: 'modelid', width: 80 }, 			
			{ label: '', name: 'qrcode', index: 'qrcode', width: 80 }, 			
			{ label: '', name: 'sn', index: 'sn', width: 80 }, 			
			{ label: 'VIP状态', name: 'vipstatus', index: 'vipstatus', width: 80 }, 			
			{ label: 'VIP类型', name: 'viptype', index: 'viptype', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#app',
	data:{
		showList: true,
		title: null,
		device: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.device = {};
		},
		update: function (event) {
			var deviceid = getSelectedRow();
			if(deviceid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(deviceid)
		},
		saveOrUpdate: function (event) {
			var url = vm.device.deviceid == null ? "aicar/device/save" : "aicar/device/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.device),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var deviceids = getSelectedRows();
			if(deviceids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "aicar/device/delete",
                    contentType: "application/json",
				    data: JSON.stringify(deviceids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(deviceid){
			$.get(baseURL + "aicar/device/info/"+deviceid, function(r){
                vm.device = r.device;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});