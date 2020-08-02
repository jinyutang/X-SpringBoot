$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'aicar/sysuser/list',
        datatype: "json",
        colModel: [			
			{ label: 'userId', name: 'userId', index: 'user_id', width: 50, key: true },
			{ label: '', name: 'username', index: 'username', width: 80 }, 			
			{ label: '', name: 'password', index: 'password', width: 80 }, 			
			{ label: '', name: 'salt', index: 'salt', width: 80 }, 			
			{ label: '', name: 'email', index: 'email', width: 80 }, 			
			{ label: '', name: 'mobile', index: 'mobile', width: 80 }, 			
			{ label: '', name: 'status', index: 'status', width: 80 }, 			
			{ label: '', name: 'createUserId', index: 'create_user_id', width: 80 }, 			
			{ label: '创建日期', name: 'createTime', index: 'create_time', width: 80 }, 			
			{ label: '', name: 'openid', index: 'openid', width: 80 }, 			
			{ label: '', name: 'unionid', index: 'unionid', width: 80 }, 			
			{ label: '', name: 'nickname', index: 'nickname', width: 80 }, 			
			{ label: '头像', name: 'avatar', index: 'avatar', width: 80 }, 			
			{ label: '性别', name: 'gender', index: 'gender', width: 80 }, 			
			{ label: '', name: 'delete', index: 'delete', width: 80 }			
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
		sysUser: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.sysUser = {};
		},
		update: function (event) {
			var userId = getSelectedRow();
			if(userId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(userId)
		},
		saveOrUpdate: function (event) {
			var url = vm.sysUser.userId == null ? "aicar/sysuser/save" : "aicar/sysuser/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.sysUser),
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
			var userIds = getSelectedRows();
			if(userIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "aicar/sysuser/delete",
                    contentType: "application/json",
				    data: JSON.stringify(userIds),
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
		getInfo: function(userId){
			$.get(baseURL + "aicar/sysuser/info/"+userId, function(r){
                vm.sysUser = r.sysUser;
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