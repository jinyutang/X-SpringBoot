$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'aicar/car/list',
        datatype: "json",
        colModel: [			
			{ label: 'carid', name: 'carid', index: 'carid', width: 50, key: true },
			{ label: '', name: 'createdate', index: 'createdate', width: 80 }, 			
			{ label: '', name: 'deviceDeviceid', index: 'device_deviceid', width: 80 }, 			
			{ label: '电子围栏功能', name: 'fence', index: 'fence', width: 80 }, 			
			{ label: '车牌号', name: 'number', index: 'number', width: 80 }, 			
			{ label: '车型', name: 'type', index: 'type', width: 80 }, 			
			{ label: '', name: 'userUserid', index: 'user_userid', width: 80 }			
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
		car: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.car = {};
		},
		update: function (event) {
			var carid = getSelectedRow();
			if(carid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(carid)
		},
		saveOrUpdate: function (event) {
			var url = vm.car.carid == null ? "aicar/car/save" : "aicar/car/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.car),
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
			var carids = getSelectedRows();
			if(carids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "aicar/car/delete",
                    contentType: "application/json",
				    data: JSON.stringify(carids),
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
		getInfo: function(carid){
			$.get(baseURL + "aicar/car/info/"+carid, function(r){
                vm.car = r.car;
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