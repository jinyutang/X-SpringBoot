$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'aicar/carfaultcodes/list',
        datatype: "json",
        colModel: [			
			{ label: '', name: 'carfaultcode', index: 'carfaultcode', width: 80 }, 			
			{ label: 'carfaultcodesid', name: 'carfaultcodesid', index: 'carfaultcodesid', width: 50, key: true },
			{ label: '', name: 'commentate', index: 'commentate', width: 80 }			
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
		carfaultcodes: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.carfaultcodes = {};
		},
		update: function (event) {
			var carfaultcodesid = getSelectedRow();
			if(carfaultcodesid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(carfaultcodesid)
		},
		saveOrUpdate: function (event) {
			var url = vm.carfaultcodes.carfaultcodesid == null ? "aicar/carfaultcodes/save" : "aicar/carfaultcodes/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.carfaultcodes),
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
			var carfaultcodesids = getSelectedRows();
			if(carfaultcodesids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "aicar/carfaultcodes/delete",
                    contentType: "application/json",
				    data: JSON.stringify(carfaultcodesids),
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
		getInfo: function(carfaultcodesid){
			$.get(baseURL + "aicar/carfaultcodes/info/"+carfaultcodesid, function(r){
                vm.carfaultcodes = r.carfaultcodes;
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