$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'aicar/cargroupmember/list',
        datatype: "json",
        colModel: [			
			{ label: '', name: 'cargroupCargoupid', index: 'cargroup_cargoupid', width: 80 }, 			
			{ label: 'cargroupmemberid', name: 'cargroupmemberid', index: 'cargroupmemberid', width: 50, key: true },
			{ label: '', name: 'createdate', index: 'createdate', width: 80 }, 			
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
		cargroupmember: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.cargroupmember = {};
		},
		update: function (event) {
			var cargroupmemberid = getSelectedRow();
			if(cargroupmemberid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(cargroupmemberid)
		},
		saveOrUpdate: function (event) {
			var url = vm.cargroupmember.cargroupmemberid == null ? "aicar/cargroupmember/save" : "aicar/cargroupmember/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.cargroupmember),
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
			var cargroupmemberids = getSelectedRows();
			if(cargroupmemberids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "aicar/cargroupmember/delete",
                    contentType: "application/json",
				    data: JSON.stringify(cargroupmemberids),
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
		getInfo: function(cargroupmemberid){
			$.get(baseURL + "aicar/cargroupmember/info/"+cargroupmemberid, function(r){
                vm.cargroupmember = r.cargroupmember;
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