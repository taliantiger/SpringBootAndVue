webpackJsonp([4],{"+sH/":function(e,t,i){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a={data:function(){return{emps:[],keywords:"",fileUploadBtnText:"导入数据",beginDateScope:"",faangledoubleup:"fa-angle-double-up",faangledoubledown:"fa-angle-double-down",dialogTitle:"",multipleSelection:[],depTextColor:"#c0c4cc",nations:[],politics:[],positions:[],joblevels:[],totalCount:-1,currentPage:1,degrees:[{id:4,name:"大专"},{id:5,name:"本科"},{id:6,name:"硕士"},{id:7,name:"博士"},{id:3,name:"高中"},{id:2,name:"初中"},{id:1,name:"小学"},{id:8,name:"其他"}],deps:[],defaultProps:{label:"name",isLeaf:"leaf",children:"children"},dialogVisible:!1,tableLoading:!1,advanceSearchViewVisible:!1,showOrHidePop:!1,showOrHidePop2:!1,emp:{name:"",gender:"",birthday:"",idCard:"",wedlock:"",nationId:"",nativePlace:"",politicId:"",email:"",phone:"",address:"",departmentId:"",departmentName:"所属部门...",jobLevelId:"",posId:"",engageForm:"",tiptopDegree:"",specialty:"",school:"",beginDate:"",workState:"",workID:"",contractTerm:"",conversionTime:"",notWorkDate:"",beginContract:"",endContract:"",workAge:""},rules:{name:[{required:!0,message:"必填:姓名",trigger:"blur"}],gender:[{required:!0,message:"必填:性别",trigger:"blur"}],birthday:[{required:!0,message:"必填:出生日期",trigger:"blur"}],idCard:[{required:!0,message:"必填:身份证号码",trigger:"blur"},{pattern:/(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/,message:"身份证号码格式不正确",trigger:"blur"}],wedlock:[{required:!0,message:"必填:婚姻状况",trigger:"blur"}],nationId:[{required:!0,message:"必填:民族",trigger:"change"}],nativePlace:[{required:!0,message:"必填:籍贯",trigger:"blur"}],politicId:[{required:!0,message:"必填:政治面貌",trigger:"blur"}],email:[{required:!0,message:"必填:电子邮箱",trigger:"blur"},{type:"email",message:"邮箱格式不正确",trigger:"blur"}],phone:[{required:!0,message:"必填:电话号码",trigger:"blur"}],address:[{required:!0,message:"必填:联系地址",trigger:"blur"}],departmentId:[{required:!0,message:"必填:部门",trigger:"change"}],jobLevelId:[{required:!0,message:"必填:职称",trigger:"change"}],posId:[{required:!0,message:"必填:职位",trigger:"change"}],engageForm:[{required:!0,message:"必填:聘用形式",trigger:"blur"}],tiptopDegree:[{required:!0,message:"必填:最高学历",trigger:"change"}],specialty:[{required:!0,message:"必填:专业",trigger:"blur"}],workID:[{required:!0,message:"必填:工号",trigger:"blur"}],school:[{required:!0,message:"必填:毕业院校",trigger:"blur"}],beginDate:[{required:!0,message:"必填:入职日期",trigger:"blur"}],conversionTime:[{required:!0,message:"必填:转正日期",trigger:"blur"}],beginContract:[{required:!0,message:"必填:合同起始日期",trigger:"blur"}],endContract:[{required:!0,message:"必填:合同终止日期",trigger:"blur"}],workAge:[{required:!0,message:"必填:工龄",trigger:"blur"}]}}},mounted:function(){this.initData(),this.loadEmps()},methods:{fileUploadSuccess:function(e,t,i){e&&this.$message({type:e.status,message:e.msg}),this.loadEmps(),this.fileUploadBtnText="导入数据"},fileUploadError:function(e,t,i){this.$message({type:"error",message:"导入失败!"}),this.fileUploadBtnText="导入数据"},beforeFileUpload:function(e){this.fileUploadBtnText="正在导入"},exportEmps:function(){window.open("/employee/basic/exportEmp","_parent")},cancelSearch:function(){this.advanceSearchViewVisible=!1,this.emptyEmpData(),this.beginDateScope="",this.loadEmps()},showAdvanceSearchView:function(){this.advanceSearchViewVisible=!this.advanceSearchViewVisible,this.keywords="",this.advanceSearchViewVisible||(this.emptyEmpData(),this.beginDateScope="",this.loadEmps())},handleSelectionChange:function(e){this.multipleSelection=e},deleteManyEmps:function(){var e=this;this.$confirm("此操作将删除["+this.multipleSelection.length+"]条数据, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){for(var t="",i=0;i<e.multipleSelection.length;i++)t+=e.multipleSelection[i].id+",";e.doDelete(t)}).catch(function(){})},deleteEmp:function(e){var t=this;this.$confirm("此操作将永久删除["+e.name+"], 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){t.doDelete(e.id)}).catch(function(){})},doDelete:function(e){this.tableLoading=!0;var t=this;this.deleteRequest("/employee/basic/emp/"+e).then(function(e){if(t.tableLoading=!1,e&&200==e.status){e.data;_,t.loadEmps()}})},keywordsChange:function(e){""==e&&this.loadEmps()},searchEmp:function(){this.loadEmps()},currentChange:function(e){this.currentPage=e,this.loadEmps()},loadEmps:function(){var e=this,t=this;this.tableLoading=!0,this.getRequest("/employee/basic/emp?page="+this.currentPage+"&size=10&keywords="+this.keywords+"&politicId="+this.emp.politicId+"&nationId="+this.emp.nationId+"&posId="+this.emp.posId+"&jobLevelId="+this.emp.jobLevelId+"&engageForm="+this.emp.engageForm+"&departmentId="+this.emp.departmentId+"&beginDateScope="+this.beginDateScope).then(function(i){if(e.tableLoading=!1,i&&200==i.status){var a=i.data;t.emps=a.emps,t.totalCount=a.count}})},addEmp:function(e){var t=this,i=this;this.$refs[e].validate(function(e){if(!e)return!1;t.emp.id?(t.tableLoading=!0,t.putRequest("/employee/basic/emp",t.emp).then(function(e){if(i.tableLoading=!1,e&&200==e.status){e.data;i.dialogVisible=!1,i.emptyEmpData(),i.loadEmps()}})):(t.tableLoading=!0,t.postRequest("/employee/basic/emp",t.emp).then(function(e){if(i.tableLoading=!1,e&&200==e.status){e.data;_,i.dialogVisible=!1,i.emptyEmpData(),i.loadEmps()}}))})},cancelEidt:function(){this.dialogVisible=!1,this.emptyEmpData()},showDepTree:function(){this.showOrHidePop=!this.showOrHidePop},showDepTree2:function(){this.showOrHidePop2=!this.showOrHidePop2},handleNodeClick:function(e){this.emp.departmentName=e.name,this.emp.departmentId=e.id,this.showOrHidePop=!1,this.depTextColor="#606266"},handleNodeClick2:function(e){this.emp.departmentName=e.name,this.emp.departmentId=e.id,this.showOrHidePop2=!1,this.depTextColor="#606266"},initData:function(){var e=this;this.getRequest("/employee/basic/basicdata").then(function(t){if(t&&200==t.status){var i=t.data;e.nations=i.nations,e.politics=i.politics,e.deps=i.deps,e.positions=i.positions,e.joblevels=i.joblevels,e.emp.workID=i.workID}})},showEditEmpView:function(e){console.log(e),this.dialogTitle="编辑员工",this.emp=e,this.emp.birthday=this.formatDate(e.birthday),this.emp.conversionTime=this.formatDate(e.conversionTime),this.emp.beginContract=this.formatDate(e.beginContract),this.emp.endContract=this.formatDate(e.endContract),this.emp.beginDate=this.formatDate(e.beginDate),this.emp.nationId=e.nation.id,this.emp.politicId=e.politicsStatus.id,this.emp.departmentId=e.department.id,this.emp.departmentName=e.department.name,this.emp.jobLevelId=e.jobLevel.id,this.emp.posId=e.position.id,delete this.emp.workAge,delete this.emp.notWorkDate,this.dialogVisible=!0},showAddEmpView:function(){this.dialogTitle="添加员工",this.dialogVisible=!0;var e=this;this.getRequest("/employee/basic/maxWorkID").then(function(t){t&&200==t.status&&(e.emp.workID=t.data)})},emptyEmpData:function(){this.emp={name:"",gender:"",birthday:"",idCard:"",wedlock:"",nationId:"",nativePlace:"",politicId:"",email:"",phone:"",address:"",departmentId:"",departmentName:"所属部门...",jobLevelId:"",posId:"",engageForm:"",tiptopDegree:"",specialty:"",school:"",beginDate:"",workState:"",workID:"",contractTerm:"",conversionTime:"",notWorkDate:"",beginContract:"",endContract:"",workAge:""}}}},l={render:function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",[i("el-container",[i("el-header",{staticStyle:{padding:"0px",display:"flex","justify-content":"space-between","align-items":"center"}},[i("div",{staticStyle:{display:"inline"}},[i("el-input",{staticStyle:{width:"300px",margin:"0px",padding:"0px"},attrs:{placeholder:"通过员工名搜索员工,记得回车哦...",clearable:"",size:"mini",disabled:e.advanceSearchViewVisible,"prefix-icon":"el-icon-search"},on:{change:e.keywordsChange},nativeOn:{keyup:function(t){if(!("button"in t)&&e._k(t.keyCode,"enter",13,t.key))return null;e.searchEmp(t)}},model:{value:e.keywords,callback:function(t){e.keywords=t},expression:"keywords"}}),e._v(" "),i("el-button",{staticStyle:{"margin-left":"5px"},attrs:{type:"primary",size:"mini",icon:"el-icon-search"},on:{click:e.searchEmp}},[e._v("搜索\n          ")]),e._v(" "),i("el-button",{staticStyle:{"margin-left":"5px"},attrs:{slot:"reference",type:"primary",size:"mini"},on:{click:e.showAdvanceSearchView},slot:"reference"},[i("i",{staticClass:"fa fa-lg",class:[e.advanceSearchViewVisible?e.faangledoubleup:e.faangledoubledown],staticStyle:{"margin-right":"5px"}}),e._v("高级搜索\n          ")])],1),e._v(" "),i("div",{staticStyle:{"margin-left":"5px","margin-right":"20px",display:"inline"}},[i("el-upload",{staticStyle:{display:"inline"},attrs:{"show-file-list":!1,accept:"application/vnd.ms-excel",action:"/employee/basic/importEmp","on-success":e.fileUploadSuccess,"on-error":e.fileUploadError,disabled:"正在导入"==e.fileUploadBtnText,"before-upload":e.beforeFileUpload}},[i("el-button",{attrs:{size:"mini",type:"success",loading:"正在导入"==e.fileUploadBtnText}},[i("i",{staticClass:"fa fa-lg fa-level-up",staticStyle:{"margin-right":"5px"}}),e._v(e._s(e.fileUploadBtnText)+"\n            ")])],1),e._v(" "),i("el-button",{attrs:{type:"success",size:"mini"},on:{click:e.exportEmps}},[i("i",{staticClass:"fa fa-lg fa-level-down",staticStyle:{"margin-right":"5px"}}),e._v("导出数据\n          ")]),e._v(" "),i("el-button",{attrs:{type:"primary",size:"mini",icon:"el-icon-plus"},on:{click:e.showAddEmpView}},[e._v("\n            添加员工\n          ")])],1)]),e._v(" "),i("el-main",{staticStyle:{"padding-left":"0px","padding-top":"0px"}},[i("div",[i("transition",{attrs:{name:"slide-fade"}},[i("div",{directives:[{name:"show",rawName:"v-show",value:e.advanceSearchViewVisible,expression:"advanceSearchViewVisible"}],staticStyle:{"margin-bottom":"10px",border:"1px","border-radius":"5px","border-style":"solid",padding:"5px 0px 5px 0px","box-sizing":"border-box","border-color":"#20a0ff"}},[i("el-row",[i("el-col",{attrs:{span:5}},[e._v("\n                  政治面貌:\n                  "),i("el-select",{staticStyle:{width:"130px"},attrs:{size:"mini",placeholder:"政治面貌"},model:{value:e.emp.politicId,callback:function(t){e.$set(e.emp,"politicId",t)},expression:"emp.politicId"}},e._l(e.politics,function(e){return i("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1),e._v(" "),i("el-col",{attrs:{span:4}},[e._v("\n                  民族:\n                  "),i("el-select",{staticStyle:{width:"130px"},attrs:{size:"mini",placeholder:"请选择民族"},model:{value:e.emp.nationId,callback:function(t){e.$set(e.emp,"nationId",t)},expression:"emp.nationId"}},e._l(e.nations,function(e){return i("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1),e._v(" "),i("el-col",{attrs:{span:4}},[e._v("\n                  职位:\n                  "),i("el-select",{staticStyle:{width:"130px"},attrs:{size:"mini",placeholder:"请选择职位"},model:{value:e.emp.posId,callback:function(t){e.$set(e.emp,"posId",t)},expression:"emp.posId"}},e._l(e.positions,function(e){return i("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1),e._v(" "),i("el-col",{attrs:{span:4}},[e._v("\n                  职称:\n                  "),i("el-select",{staticStyle:{width:"130px"},attrs:{size:"mini",placeholder:"请选择职称"},model:{value:e.emp.jobLevelId,callback:function(t){e.$set(e.emp,"jobLevelId",t)},expression:"emp.jobLevelId"}},e._l(e.joblevels,function(e){return i("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1),e._v(" "),i("el-col",{attrs:{span:7}},[e._v("\n                  聘用形式:\n                  "),i("el-radio-group",{model:{value:e.emp.engageForm,callback:function(t){e.$set(e.emp,"engageForm",t)},expression:"emp.engageForm"}},[i("el-radio",{attrs:{label:"劳动合同"}},[e._v("劳动合同")]),e._v(" "),i("el-radio",{staticStyle:{"margin-left":"15px"},attrs:{label:"劳务合同"}},[e._v("劳务合同")])],1)],1)],1),e._v(" "),i("el-row",{staticStyle:{"margin-top":"10px"}},[i("el-col",{attrs:{span:5}},[e._v("\n                  所属部门:\n                  "),i("el-popover",{attrs:{placement:"right",title:"请选择部门",trigger:"manual"},model:{value:e.showOrHidePop2,callback:function(t){e.showOrHidePop2=t},expression:"showOrHidePop2"}},[i("el-tree",{attrs:{data:e.deps,"default-expand-all":!0,props:e.defaultProps,"expand-on-click-node":!1},on:{"node-click":e.handleNodeClick2}}),e._v(" "),i("div",{staticStyle:{width:"130px",height:"26px",display:"inline-flex","font-size":"13px",border:"1px","border-radius":"5px","border-style":"solid","padding-left":"13px","box-sizing":"border-box","border-color":"#dcdfe6",cursor:"pointer","align-items":"center"},style:{color:e.depTextColor},attrs:{slot:"reference"},on:{click:e.showDepTree2},slot:"reference"},[e._v(e._s(e.emp.departmentName)+"\n                    ")])],1)],1),e._v(" "),i("el-col",{attrs:{span:10}},[e._v("\n                  入职日期:\n                  "),i("el-date-picker",{attrs:{"unlink-panels":"",size:"mini",type:"daterange","value-format":"yyyy-MM-dd","range-separator":"至","start-placeholder":"开始日期","end-placeholder":"结束日期"},model:{value:e.beginDateScope,callback:function(t){e.beginDateScope=t},expression:"beginDateScope"}})],1),e._v(" "),i("el-col",{attrs:{span:5,offset:4}},[i("el-button",{attrs:{size:"mini"},on:{click:e.cancelSearch}},[e._v("取消")]),e._v(" "),i("el-button",{attrs:{icon:"el-icon-search",type:"primary",size:"mini"},on:{click:e.searchEmp}},[e._v("搜索")])],1)],1)],1)]),e._v(" "),i("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.tableLoading,expression:"tableLoading"}],staticStyle:{width:"100%"},attrs:{data:e.emps,border:"",stripe:"",size:"mini"},on:{"selection-change":e.handleSelectionChange}},[i("el-table-column",{attrs:{type:"selection",align:"left",width:"30"}}),e._v(" "),i("el-table-column",{attrs:{prop:"name",align:"left",fixed:"",label:"姓名",width:"90"}}),e._v(" "),i("el-table-column",{attrs:{prop:"workID",width:"85",align:"left",label:"工号"}}),e._v(" "),i("el-table-column",{attrs:{prop:"gender",label:"性别",width:"50"}}),e._v(" "),i("el-table-column",{attrs:{width:"85",align:"left",label:"出生日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(e._f("formatDate")(t.row.birthday)))]}}])}),e._v(" "),i("el-table-column",{attrs:{prop:"idCard",width:"150",align:"left",label:"身份证号码"}}),e._v(" "),i("el-table-column",{attrs:{prop:"wedlock",width:"70",label:"婚姻状况"}}),e._v(" "),i("el-table-column",{attrs:{width:"50",prop:"nation.name",label:"民族"}}),e._v(" "),i("el-table-column",{attrs:{prop:"nativePlace",width:"80",label:"籍贯"}}),e._v(" "),i("el-table-column",{attrs:{prop:"politicsStatus.name",label:"政治面貌"}}),e._v(" "),i("el-table-column",{attrs:{prop:"email",width:"180",align:"left",label:"电子邮件"}}),e._v(" "),i("el-table-column",{attrs:{prop:"phone",width:"100",label:"电话号码"}}),e._v(" "),i("el-table-column",{attrs:{prop:"address",width:"220",align:"left",label:"联系地址"}}),e._v(" "),i("el-table-column",{attrs:{prop:"department.name",align:"left",width:"100",label:"所属部门"}}),e._v(" "),i("el-table-column",{attrs:{width:"100",align:"left",prop:"position.name",label:"职位"}}),e._v(" "),i("el-table-column",{attrs:{prop:"jobLevel.name",width:"100",align:"left",label:"职称"}}),e._v(" "),i("el-table-column",{attrs:{prop:"engageForm",label:"聘用形式"}}),e._v(" "),i("el-table-column",{attrs:{width:"85",align:"left",label:"入职日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(e._f("formatDate")(t.row.beginDate)))]}}])}),e._v(" "),i("el-table-column",{attrs:{width:"85",align:"left",label:"转正日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(e._f("formatDate")(t.row.conversionTime)))]}}])}),e._v(" "),i("el-table-column",{attrs:{width:"95",align:"left",label:"合同起始日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(e._f("formatDate")(t.row.beginContract)))]}}])}),e._v(" "),i("el-table-column",{attrs:{width:"95",align:"left",label:"合同截至日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(e._f("formatDate")(t.row.endContract)))]}}])}),e._v(" "),i("el-table-column",{attrs:{align:"left",width:"70",label:"合同期限"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v(e._s(t.row.contractTerm)+"年")]}}])}),e._v(" "),i("el-table-column",{attrs:{align:"left",prop:"tiptopDegree",label:"最高学历"}}),e._v(" "),i("el-table-column",{attrs:{fixed:"right",label:"操作",width:"195"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("el-button",{staticStyle:{padding:"3px 4px 3px 4px",margin:"2px"},attrs:{size:"mini"},on:{click:function(i){e.showEditEmpView(t.row)}}},[e._v("编辑\n                ")]),e._v(" "),i("el-button",{staticStyle:{padding:"3px 4px 3px 4px",margin:"2px"},attrs:{type:"primary",size:"mini"}},[e._v("查看高级资料\n                ")]),e._v(" "),i("el-button",{staticStyle:{padding:"3px 4px 3px 4px",margin:"2px"},attrs:{type:"danger",size:"mini"},on:{click:function(i){e.deleteEmp(t.row)}}},[e._v("删除\n                ")])]}}])})],1),e._v(" "),i("div",{staticStyle:{display:"flex","justify-content":"space-between",margin:"2px"}},[e.emps.length>0?i("el-button",{attrs:{type:"danger",size:"mini",disabled:0==e.multipleSelection.length},on:{click:e.deleteManyEmps}},[e._v("批量删除\n            ")]):e._e(),e._v(" "),i("el-pagination",{attrs:{background:"","page-size":10,"current-page":e.currentPage,layout:"prev, pager, next",total:e.totalCount},on:{"current-change":e.currentChange}})],1)],1)])],1),e._v(" "),i("el-form",{ref:"addEmpForm",staticStyle:{margin:"0px",padding:"0px"},attrs:{model:e.emp,rules:e.rules}},[i("div",{staticStyle:{"text-align":"left"}},[i("el-dialog",{staticStyle:{padding:"0px"},attrs:{title:e.dialogTitle,"close-on-click-modal":!1,visible:e.dialogVisible,width:"77%"},on:{"update:visible":function(t){e.dialogVisible=t}}},[i("el-row",[i("el-col",{attrs:{span:6}},[i("div",[i("el-form-item",{attrs:{label:"姓名:",prop:"name"}},[i("el-input",{staticStyle:{width:"150px"},attrs:{"prefix-icon":"el-icon-edit",size:"mini",placeholder:"请输入员工姓名"},model:{value:e.emp.name,callback:function(t){e.$set(e.emp,"name",t)},expression:"emp.name"}})],1)],1)]),e._v(" "),i("el-col",{attrs:{span:5}},[i("div",[i("el-form-item",{attrs:{label:"性别:",prop:"gender"}},[i("el-radio-group",{model:{value:e.emp.gender,callback:function(t){e.$set(e.emp,"gender",t)},expression:"emp.gender"}},[i("el-radio",{attrs:{label:"男"}},[e._v("男")]),e._v(" "),i("el-radio",{staticStyle:{"margin-left":"15px"},attrs:{label:"女"}},[e._v("女")])],1)],1)],1)]),e._v(" "),i("el-col",{attrs:{span:6}},[i("div",[i("el-form-item",{attrs:{label:"出生日期:",prop:"birthday"}},[i("el-date-picker",{staticStyle:{width:"150px"},attrs:{size:"mini","value-format":"yyyy-MM-dd HH:mm:ss",type:"date",placeholder:"出生日期"},model:{value:e.emp.birthday,callback:function(t){e.$set(e.emp,"birthday",t)},expression:"emp.birthday"}})],1)],1)]),e._v(" "),i("el-col",{attrs:{span:7}},[i("div",[i("el-form-item",{attrs:{label:"政治面貌:",prop:"politicId"}},[i("el-select",{staticStyle:{width:"200px"},attrs:{size:"mini",placeholder:"政治面貌"},model:{value:e.emp.politicId,callback:function(t){e.$set(e.emp,"politicId",t)},expression:"emp.politicId"}},e._l(e.politics,function(e){return i("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1)],1)])],1),e._v(" "),i("el-row",[i("el-col",{attrs:{span:6}},[i("div",[i("el-form-item",{attrs:{label:"民族:",prop:"nationId"}},[i("el-select",{staticStyle:{width:"150px"},attrs:{size:"mini",placeholder:"请选择民族"},model:{value:e.emp.nationId,callback:function(t){e.$set(e.emp,"nationId",t)},expression:"emp.nationId"}},e._l(e.nations,function(e){return i("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1)],1)]),e._v(" "),i("el-col",{attrs:{span:5}},[i("div",[i("el-form-item",{attrs:{label:"籍贯:",prop:"nativePlace"}},[i("el-input",{staticStyle:{width:"120px"},attrs:{size:"mini",placeholder:"员工籍贯"},model:{value:e.emp.nativePlace,callback:function(t){e.$set(e.emp,"nativePlace",t)},expression:"emp.nativePlace"}})],1)],1)]),e._v(" "),i("el-col",{attrs:{span:6}},[i("div",[i("el-form-item",{attrs:{label:"电子邮箱:",prop:"email"}},[i("el-input",{staticStyle:{width:"150px"},attrs:{"prefix-icon":"el-icon-message",size:"mini",placeholder:"电子邮箱地址..."},model:{value:e.emp.email,callback:function(t){e.$set(e.emp,"email",t)},expression:"emp.email"}})],1)],1)]),e._v(" "),i("el-col",{attrs:{span:7}},[i("div",[i("el-form-item",{attrs:{label:"联系地址:",prop:"address"}},[i("el-input",{staticStyle:{width:"200px"},attrs:{"prefix-icon":"el-icon-edit",size:"mini",placeholder:"联系地址..."},model:{value:e.emp.address,callback:function(t){e.$set(e.emp,"address",t)},expression:"emp.address"}})],1)],1)])],1),e._v(" "),i("el-row",[i("el-col",{attrs:{span:6}},[i("div",[i("el-form-item",{attrs:{label:"职位:",prop:"posId"}},[i("el-select",{staticStyle:{width:"150px"},attrs:{size:"mini",placeholder:"请选择职位"},model:{value:e.emp.posId,callback:function(t){e.$set(e.emp,"posId",t)},expression:"emp.posId"}},e._l(e.positions,function(e){return i("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1)],1)]),e._v(" "),i("el-col",{attrs:{span:5}},[i("div",[i("el-form-item",{attrs:{label:"职称:",prop:"jobLevelId"}},[i("el-select",{staticStyle:{width:"120px"},attrs:{size:"mini",placeholder:"请选择职称"},model:{value:e.emp.jobLevelId,callback:function(t){e.$set(e.emp,"jobLevelId",t)},expression:"emp.jobLevelId"}},e._l(e.joblevels,function(e){return i("el-option",{key:e.id,attrs:{label:e.name,value:e.id}})}))],1)],1)]),e._v(" "),i("el-col",{attrs:{span:6}},[i("div",[i("el-form-item",{attrs:{label:"所属部门:",prop:"departmentId"}},[i("el-popover",{attrs:{placement:"right",title:"请选择部门",trigger:"manual"},model:{value:e.showOrHidePop,callback:function(t){e.showOrHidePop=t},expression:"showOrHidePop"}},[i("el-tree",{attrs:{data:e.deps,"default-expand-all":!0,props:e.defaultProps,"expand-on-click-node":!1},on:{"node-click":e.handleNodeClick}}),e._v(" "),i("div",{staticStyle:{width:"150px",height:"26px",display:"inline-flex","font-size":"13px",border:"1px","border-radius":"5px","border-style":"solid","padding-left":"13px","box-sizing":"border-box","border-color":"#dcdfe6",cursor:"pointer","align-items":"center"},style:{color:e.depTextColor},attrs:{slot:"reference"},on:{click:function(t){return"button"in t||!e._k(t.keyCode,"left",37,t.key)?"button"in t&&0!==t.button?null:void e.showDepTree(t):null}},slot:"reference"},[e._v(e._s(e.emp.departmentName)+"\n                    ")])],1)],1)],1)]),e._v(" "),i("el-col",{attrs:{span:7}},[i("div",[i("el-form-item",{attrs:{label:"电话号码:",prop:"phone"}},[i("el-input",{staticStyle:{width:"200px"},attrs:{"prefix-icon":"el-icon-phone",size:"mini",placeholder:"电话号码..."},model:{value:e.emp.phone,callback:function(t){e.$set(e.emp,"phone",t)},expression:"emp.phone"}})],1)],1)])],1),e._v(" "),i("el-row",[i("el-col",{attrs:{span:6}},[i("div",[i("el-form-item",{attrs:{label:"工号:",prop:"workID"}},[i("el-input",{staticStyle:{width:"150px"},attrs:{disabled:"",size:"mini",placeholder:"员工工号..."},model:{value:e.emp.workID,callback:function(t){e.$set(e.emp,"workID",t)},expression:"emp.workID"}})],1)],1)]),e._v(" "),i("el-col",{attrs:{span:5}},[i("div",[i("el-form-item",{attrs:{label:"学历:",prop:"tiptopDegree"}},[i("el-select",{staticStyle:{width:"120px"},attrs:{size:"mini",placeholder:"最高学历"},model:{value:e.emp.tiptopDegree,callback:function(t){e.$set(e.emp,"tiptopDegree",t)},expression:"emp.tiptopDegree"}},e._l(e.degrees,function(e){return i("el-option",{key:e.id,attrs:{label:e.name,value:e.name}})}))],1)],1)]),e._v(" "),i("el-col",{attrs:{span:6}},[i("div",[i("el-form-item",{attrs:{label:"毕业院校:",prop:"school"}},[i("el-input",{staticStyle:{width:"150px"},attrs:{"prefix-icon":"el-icon-edit",size:"mini",placeholder:"毕业院校名称"},model:{value:e.emp.school,callback:function(t){e.$set(e.emp,"school",t)},expression:"emp.school"}})],1)],1)]),e._v(" "),i("el-col",{attrs:{span:7}},[i("div",[i("el-form-item",{attrs:{label:"专业名称:",prop:"specialty"}},[i("el-input",{staticStyle:{width:"200px"},attrs:{"prefix-icon":"el-icon-edit",size:"mini",placeholder:"专业名称"},model:{value:e.emp.specialty,callback:function(t){e.$set(e.emp,"specialty",t)},expression:"emp.specialty"}})],1)],1)])],1),e._v(" "),i("el-row",[i("el-col",{attrs:{span:6}},[i("div",[i("el-form-item",{attrs:{label:"入职日期:",prop:"beginDate"}},[i("el-date-picker",{staticStyle:{width:"130px"},attrs:{size:"mini",type:"date","value-format":"yyyy-MM-dd HH:mm:ss",placeholder:"入职日期"},model:{value:e.emp.beginDate,callback:function(t){e.$set(e.emp,"beginDate",t)},expression:"emp.beginDate"}})],1)],1)]),e._v(" "),i("el-col",{attrs:{span:6}},[i("div",[i("el-form-item",{attrs:{label:"转正日期:",prop:"conversionTime"}},[i("el-date-picker",{staticStyle:{width:"130px"},attrs:{size:"mini","value-format":"yyyy-MM-dd HH:mm:ss",type:"date",placeholder:"转正日期"},model:{value:e.emp.conversionTime,callback:function(t){e.$set(e.emp,"conversionTime",t)},expression:"emp.conversionTime"}})],1)],1)]),e._v(" "),i("el-col",{attrs:{span:6}},[i("div",[i("el-form-item",{attrs:{label:"合同起始日期:",prop:"beginContract"}},[i("el-date-picker",{staticStyle:{width:"135px"},attrs:{size:"mini","value-format":"yyyy-MM-dd HH:mm:ss",type:"date",placeholder:"合同起始日期"},model:{value:e.emp.beginContract,callback:function(t){e.$set(e.emp,"beginContract",t)},expression:"emp.beginContract"}})],1)],1)]),e._v(" "),i("el-col",{attrs:{span:6}},[i("div",[i("el-form-item",{attrs:{label:"合同终止日期:",prop:"endContract"}},[i("el-date-picker",{staticStyle:{width:"135px"},attrs:{"value-format":"yyyy-MM-dd HH:mm:ss",size:"mini",type:"date",placeholder:"合同终止日期"},model:{value:e.emp.endContract,callback:function(t){e.$set(e.emp,"endContract",t)},expression:"emp.endContract"}})],1)],1)])],1),e._v(" "),i("el-row",[i("el-col",{attrs:{span:8}},[i("div",[i("el-form-item",{attrs:{label:"身份证号码:",prop:"idCard"}},[i("el-input",{staticStyle:{width:"180px"},attrs:{"prefix-icon":"el-icon-edit",size:"mini",placeholder:"请输入员工身份证号码..."},model:{value:e.emp.idCard,callback:function(t){e.$set(e.emp,"idCard",t)},expression:"emp.idCard"}})],1)],1)]),e._v(" "),i("el-col",{attrs:{span:8}},[i("div",[i("el-form-item",{attrs:{label:"聘用形式:",prop:"engageForm"}},[i("el-radio-group",{model:{value:e.emp.engageForm,callback:function(t){e.$set(e.emp,"engageForm",t)},expression:"emp.engageForm"}},[i("el-radio",{attrs:{label:"劳动合同"}},[e._v("劳动合同")]),e._v(" "),i("el-radio",{staticStyle:{"margin-left":"15px"},attrs:{label:"劳务合同"}},[e._v("劳务合同")])],1)],1)],1)]),e._v(" "),i("el-col",{attrs:{span:8}},[i("div",[i("el-form-item",{attrs:{label:"婚姻状况:",prop:"wedlock"}},[i("el-radio-group",{model:{value:e.emp.wedlock,callback:function(t){e.$set(e.emp,"wedlock",t)},expression:"emp.wedlock"}},[i("el-radio",{attrs:{label:"已婚"}},[e._v("已婚")]),e._v(" "),i("el-radio",{staticStyle:{"margin-left":"15px"},attrs:{label:"未婚"}},[e._v("未婚")]),e._v(" "),i("el-radio",{staticStyle:{"margin-left":"15px"},attrs:{label:"离异"}},[e._v("离异")])],1)],1)],1)])],1),e._v(" "),i("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[i("el-button",{attrs:{size:"mini"},on:{click:e.cancelEidt}},[e._v("取 消")]),e._v(" "),i("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(t){e.addEmp("addEmpForm")}}},[e._v("确 定")])],1)],1)],1)])],1)},staticRenderFns:[]};var n=i("VU/8")(a,l,!1,function(e){i("/vO1")},null,null);t.default=n.exports},"/vO1":function(e,t){},"8cST":function(e,t,i){var a={"./EmpAdv.vue":"ABUp","./EmpBasic.vue":"+sH/"};function l(e){return i(n(e))}function n(e){var t=a[e];if(!(t+1))throw new Error("Cannot find module '"+e+"'.");return t}l.keys=function(){return Object.keys(a)},l.resolve=n,e.exports=l,l.id="8cST"},ABUp:function(e,t,i){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a={render:function(){this.$createElement;this._self._c;return this._m(0)},staticRenderFns:[function(){var e=this.$createElement,t=this._self._c||e;return t("div",[t("h1",[this._v("高级资料")])])}]},l=i("VU/8")(null,a,!1,null,null,null);t.default=l.exports}});
//# sourceMappingURL=4.15cd45dfe423d82c0c2a.js.map