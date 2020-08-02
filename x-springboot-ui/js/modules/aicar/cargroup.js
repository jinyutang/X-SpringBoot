$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'aicar/cargroup/list',
        datatype: "json",
        colModel: [			
			{ label: 'cargoupid', name: 'cargoupid', index: 'cargoupid', width: 50, key: true },
			{ label: '', name: 'createdate', index: 'createdate', width: 80 }, 			
			{ label: '', name: 'destination', index: 'destination', width: 80 }, 			
			{ label: '', name: 'name', index: 'name', width: 80 }, 			
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
		cargroup: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.cargroup = {};
		},
		update: function (event) {
			var cargoupid = getSelectedRow();
			if(cargoupid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(cargoupid)
		},
		saveOrUpdate: function (event) {
			var url = vm.cargroup.cargoupid == null ? "aicar/cargroup/save" : "aicar/cargroup/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.cargroup),
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
			var cargoupids = getSelectedRows();
			if(cargoupids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "aicar/cargroup/delete",
                    contentType: "application/json",
				    data: JSON.stringify(cargoupids),
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
		getInfo: function(cargoupid){
			$.get(baseURL + "aicar/cargroup/info/"+cargoupid, function(r){
                vm.cargroup = r.cargroup;
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