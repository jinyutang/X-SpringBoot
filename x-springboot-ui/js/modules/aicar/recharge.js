$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'aicar/recharge/list',
        datatype: "json",
        colModel: [			
			{ label: '', name: 'createdate', index: 'createdate', width: 80 }, 			
			{ label: '', name: 'data', index: 'data', width: 80 }, 			
			{ label: 'rechargeid', name: 'rechargeid', index: 'rechargeid', width: 50, key: true },
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
		recharge: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.recharge = {};
		},
		update: function (event) {
			var rechargeid = getSelectedRow();
			if(rechargeid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(rechargeid)
		},
		saveOrUpdate: function (event) {
			var url = vm.recharge.rechargeid == null ? "aicar/recharge/save" : "aicar/recharge/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.recharge),
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
			var rechargeids = getSelectedRows();
			if(rechargeids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "aicar/recharge/delete",
                    contentType: "application/json",
				    data: JSON.stringify(rechargeids),
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
		getInfo: function(rechargeid){
			$.get(baseURL + "aicar/recharge/info/"+rechargeid, function(r){
                vm.recharge = r.recharge;
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