<!DOCTYPE html>
<html lang="zh">
<head>
  <title>ares5k-generator</title>
  <link href="css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
  <div class="row">
    <#-- 标题栏 -->
    <div style="height:50px; margin-bottom: 30px; margin-top :10px; ">
        <h1><b>ares5k-generator</b></h1>
    </div>
    <form class="form-horizontal" id="form" role="form" action="/tables" method="post">
      <div style="margin-top:10px;">
        <#-- 提示错误信息 -->
        <#if error??>
          <div id="errorMessage" class="alert alert-danger">${error}</div>
        </#if>
        <#-- 提示成功信息 -->
        <div id="successMessage" class="alert alert-success" style="display: none">生成成功!</div>
        <fieldset>
          <#-- 数据源部分布局 start -->
          <legend><b>配置数据源</b></legend>
          <div class="form-group">
            <label class="col-sm-1 control-label" for="hostname">主机名:</label>
            <div class="col-sm-4">
              <input class="form-control" id="hostname" name="hostname" type="text" placeholder="192.168.x.xx" value="${(generatorVo.hostname)!''}"/>
            </div>
            <label class="col-sm-1 control-label" for="dbName">数据库名:</label>
            <div class="col-sm-4">
              <input class="form-control" id="dbName" name="dbName" type="text" placeholder="db_name" value="${(generatorVo.dbName)!''}"/>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-1 control-label" for="dbUser">用户名:</label>
            <div class="col-sm-4">
              <input class="form-control" id="dbUser" name="dbUser" type="text" placeholder="root" value="${(generatorVo.dbUser)!''}"/>
            </div>
            <label class="col-sm-1 control-label" for="dbPwd">密码:</label>
            <div class="col-sm-4">
              <input class="form-control" id="dbPwd" name="dbPwd" type="password" placeholder="123456" value="${(generatorVo.dbPwd)!''}"/>
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-1 control-label" for="port">端口:</label>
            <div class="col-sm-4">
              <input class="form-control" id="port" name="port" type="text" placeholder="3306" value="${(generatorVo.port)!''}"/>
            </div>
            <div class="col-sm-5 text-right">
              <button type="submit" class="btn btn-default">连 接</button>
              <#-- 结果集有数据是再展示 -->
              <#if (generatorVo.tableInfoList)??>
                <button type="button" class="btn btn-success btn-next" data-toggle="modal" data-target="#customModal">生 成</button>
              </#if>
            </div>
          </div>
          <#-- 数据源部分布局 end -->

          <#-- 结果集部分布局 start -->
          <#-- 结果集有数据是再展示 -->
          <#if (generatorVo.tableInfoList)??>
            <hr/>
            <table class="table table-hover">
              <caption>数据库中的表</caption>
              <thead>
              <tr>
                <th class="col-xs-1"><label><input id="baseTableCheckbox" type="checkbox"/></label></th>
                <th>表名</th>
                <th>备注</th>
              </tr>
              </thead>
              <tbody>
              <#-- 遍历所有表定义信息 -->
              <#list generatorVo.tableInfoList as table>
                <tr>
                  <td>
                    <label>
                      <input id="${table.tableName}" name="checkedTableNameList" type="checkbox" value="${table.tableName!''}"/>
                    </label>
                  </td>
                  <td>${table.tableName!''}</td>
                  <td>${table.tableComment!''}</td>
                  <#-- 将表格中数据提交回Vo的集合中, 用来下次反显 -->
                  <input id="tableInfoList[${table_index}].tableName" name="tableInfoList[${table_index}].tableName" type="hidden" value="${table.tableName!''}"/>
                  <input id="tableInfoList[${table_index}].tableComment" name="tableInfoList[${table_index}].tableComment" type="hidden" value="${table.tableComment!''}"/>
                </tr>
              </#list>
              </tbody>
            </table>
          </#if>
          <#-- 结果集部分布局 end -->
        </fieldset>
      </div>

      <#-- 个性化设置-模态部分布局 start -->
      <div class="modal fade" id="customModal" tabindex="-1" role="dialog" aria-labelledby="customModalLabel" aria-hidden="true">
        <div class="modal-dialog">
          <div class="modal-content">
            <#-- 模态头布局 start -->
            <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
              <h4 class="modal-title" id="customModalLabel">个性化设置</h4>
            </div>
            <#-- 模态头布局 end -->
            <#-- 模态体布局 start -->
            <div class="modal-body">
              <div class="form-horizontal">
                <div class="form-group">
                  <label class="col-sm-2 control-label" for="authorName">作者:</label>
                  <div class="col-sm-9">
                    <input class="form-control" id="authorName" name="authorName" type="text" placeholder="选填项, 例: ares5k" value="${(generatorVo.authorName)!''}"/>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label" for="packageName">包名:</label>
                  <div class="col-sm-9">
                    <input class="form-control" id="packageName" name="packageName" type="text" placeholder="com.ares5k" value="${(generatorVo.packageName)!''}"/>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label" for="ignorePrefix">忽略前缀:</label>
                  <div class="col-sm-9">
                    <input class="form-control" id="ignorePrefix" name="ignorePrefix" type="text" placeholder="选填项, 例: tb_" value="${(generatorVo.ignorePrefix)!''}"/>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">插件:</label>
                  <div class="col-sm-9">
                    <div class="col-sm-3">
                      <label class="checkbox-inline">
                        <input id="lombok" name="lombok" type="checkbox" value="lombok"
                          <#if (generatorVo.lombok)?? && generatorVo.lombok == "lombok"> checked</#if>
                        />lombok</label>
                    </div>
                    <div class="col-sm-4">
                      <label class="checkbox-inline">
                        <input id="mybatisPlus" name="mybatisPlus" type="checkbox" value="mybatis-plus"
                          <#if (generatorVo.mybatisPlus)?? && generatorVo.mybatisPlus == "mybatis-plus"> checked</#if>
                        />mybatis-plus</label>
                    </div>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">类型:</label>
                  <div class="col-sm-9">
                    <div class="col-sm-3">
                      <label class="checkbox-inline">
                        <input id="controller" name="typeList" type="checkbox" value="controller"
                          <#if (generatorVo.typeList)??>
                            <#list generatorVo.typeList as types>
                              <#if types == "controller"> checked</#if>
                            </#list>
                          </#if>
                        />controller</label>
                    </div>
                    <div class="col-sm-3">
                      <label class="checkbox-inline">
                        <input id="service" name="typeList" type="checkbox" value="service"
                           <#if (generatorVo.typeList)??>
                            <#list generatorVo.typeList as types>
                              <#if types == "service"> checked</#if>
                            </#list>
                           </#if>
                        />service</label>
                    </div>
                    <div class="col-sm-3">
                      <label class="checkbox-inline">
                        <input id="mapper" name="typeList" type="checkbox" value="mapper"
                          <#if (generatorVo.typeList)??>
                            <#list generatorVo.typeList as types>
                              <#if types == "service"> checked</#if>
                            </#list>
                          </#if>
                        />mapper</label>
                    </div>
                    <div class="col-sm-3">
                      <label class="checkbox-inline">
                        <input id="entity" name="typeList" type="checkbox" value="entity"
                           <#if (generatorVo.typeList)??>
                            <#list generatorVo.typeList as types>
                              <#if types == "entity"> checked</#if>
                            </#list>
                          </#if>
                        />entity</label>
                    </div>
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label">包划分:</label>
                  <div class="col-sm-10">
                    <div class="col-sm-5">
                      <label class="checkbox-inline" data-toggle="tooltip" data-html="true"
                             title="<p align='left'>解释: <br/>功能/模块/文件名.java <br/>example: <br/>controller/user/UserController.java <br/> entity/user/User.java</p>">
                        <input name="pathPattern" type="checkbox" value="ability-and-module"
                          <#if (generatorVo.pathPattern)?? && generatorVo.pathPattern == "ability-and-module"> checked</#if>
                        />以功能、模块</label>
                    </div>
                    <div class="col-sm-3">
                      <label class="checkbox-inline" data-toggle="tooltip" data-html="true"
                             title="<p align='left'>解释: <br/>模块/功能/文件名.java <br/>example: <br/>user/controller/UserController.java <br/>user/entity/User.java</p>">
                        <input name="pathPattern" type="checkbox" value="module"
                          <#if (generatorVo.pathPattern)?? && generatorVo.pathPattern == "module"> checked</#if>
                        />以模块</label>
                    </div>
                    <div class="col-sm-3">
                      <label class="checkbox-inline" data-toggle="tooltip" data-html="true"
                             title="<p align='left'>解释: <br/>功能/文件名.java <br/>example: <br/>controller/UserController.java <br/>entity/User.java</p>">
                        <input name="pathPattern" type="checkbox" value="ability"
                          <#if !(generatorVo.pathPattern)??> checked</#if>
                          <#if (generatorVo.pathPattern)?? && generatorVo.pathPattern == "ability"> checked</#if>
                        />以功能</label>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <#-- 模态体布局 end -->
            <#-- 模态底布局 start -->
            <div class="modal-footer">
              <button id="download" type="button" class="btn btn-primary">选好了</button>
            </div>
            <#-- 模态底布局 end -->
          </div>
        </div>
      </div>
      <#-- 个性化设置-模态部分布局 end -->
    </form>
  </div>
</div>
</body>
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<#-- ares5k-generator 画面用 js -->
<script>
  <#-- 回显时, 选中表名前的复选框 -->
  <#if (generatorVo.checkedTableNameList)??>
    <#list generatorVo.checkedTableNameList as tableName>
      $("#${tableName}").prop("checked", "true");
    </#list>
  </#if>
  <#-- 包换分的鼠标悬停提示 -->
  $('[data-toggle="tooltip"]').tooltip()
  <#-- 表名全选/反选 -->
  $("#baseTableCheckbox").click(() => {
    const isCheck = $("#baseTableCheckbox").is(":checked")
    $("input[name='checkedTableNameList']").prop("checked", isCheck)
  });
  <#-- 生成 -->
  $('#download').click(() => {
    const form = $("#form")
    form.attr("action", "/download")
    form.submit();
    form.attr("action", "/tables")
    $("#customModal").modal("hide")
    $("#successMessage").show()
    $("#errorMessage").hide()
  })
  <#-- 复选框模拟单选 -->
  $("input[name='pathPattern']").on('click', function () {
    $("input[name='pathPattern']").prop("checked", false);
    $(this).prop("checked", true);
  });
</script>
</html>