(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-d6b20d96"],{"20ba":function(n,t,e){},"25e8":function(n,t,e){"use strict";var o=e("20ba"),c=e.n(o);c.a},a2f0:function(n,t,e){"use strict";e.r(t);var o=function(){var n=this,t=n.$createElement,e=n._self._c||t;return e("div",{staticClass:"form"},[e("el-form",{ref:"connection",attrs:{"label-position":n.labelPosition,"label-width":"125px",model:n.connection,rules:n.rules}},[e("el-form-item",{attrs:{label:"服务器地址",prop:"ip"}},[e("el-input",{model:{value:n.connection.name,callback:function(t){n.$set(n.connection,"name",t)},expression:"connection.name"}})],1),e("el-form-item",{attrs:{label:"订阅主题",prop:"topic"}},[e("el-input",{model:{value:n.connection.pubNetAddr,callback:function(t){n.$set(n.connection,"pubNetAddr",t)},expression:"connection.pubNetAddr"}})],1),e("el-form-item",{staticStyle:{"text-align":"left"},attrs:{label:"消息质量",prop:"connection"}},[e("el-radio-group",{model:{value:n.connection.connectionQuality,callback:function(t){n.$set(n.connection,"connectionQuality",t)},expression:"connection.connectionQuality"}},[e("el-radio",{attrs:{label:"0"}},[n._v("不再次发送")]),e("el-radio",{attrs:{label:"1"}},[n._v("再次发送直到成功")]),e("el-radio",{attrs:{label:"2"}},[n._v("确保受到")])],1)],1),e("el-form-item",{staticStyle:{"text-align":"left","margin-top":"40px"}},[e("el-button",{attrs:{type:"primary"},on:{click:function(t){return n.saveConnectionInfo("connection")}}},[n._v("保存设置")]),e("el-button",{on:{click:n.back}},[n._v("返回上级")])],1)],1)],1)},c=[],i={name:"DeviceEdit",mounted:function(){},data:function(){return{labelPosition:"right",connection:{args:[{}],connectionQuality:"0"},rules:{}}},computed:{},methods:{back:function(){this.$router.back()},saveConnectionInfo:function(){console.log(this.connection)}}},l=i,a=(e("25e8"),e("2877")),r=Object(a["a"])(l,o,c,!1,null,"956c3e6c",null);t["default"]=r.exports}}]);
//# sourceMappingURL=chunk-d6b20d96.4223dc93.js.map