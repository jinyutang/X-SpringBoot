$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'aicar/carfaultcodelog/list',
        datatype: "json",
        colModel: [			
			{ label: '', name: 'carCarid', index: 'car_carid', width: 80 }, 			
			{ label: 'carfaultcodelog', name: 'carfaultcodelog', index: 'carfaultcodelog', width: 50, key: true },
			{ label: '', name: 'carfaultcodesCarfaultcodesid', index: 'carfaultcodes_carfaultcodesid', width: 80 }, 			
			{ label: '', name: 'carid', index: 'carid', width: 80 }, 			
			{ label: '', name: 'createdate', index: 'createdate', width: 80 }			
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
		carfaultcodelog: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.carfaultcodelog = {};
		},
		update: function (event) {
			var carfaultcodelog = getSelectedRow();
			if(carfaultcodelog == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(carfaultcodelog)
		},
		saveOrUpdate: function (event) {
			var url = vm.carfaultcodelog.carfaultcodelog == null ? "aicar/carfaultcodelog/save" : "aicar/carfaultcodelog/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.carfaultcodelog),
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
			var carfaultcodelogs = getSelectedRows();
			if(carfaultcodelogs == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "aicar/carfaultcodelog/delete",
                    contentType: "application/json",
				    data: JSON.stringify(carfaultcodelogs),
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
		getInfo: function(carfaultcodelog){
			$.get(baseURL + "aicar/carfaultcodelog/info/"+carfaultcodelog, function(r){
                vm.carfaultcodelog = r.carfaultcodelog;
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