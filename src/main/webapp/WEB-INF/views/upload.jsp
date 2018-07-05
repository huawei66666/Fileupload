<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/include/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <title>带进度条上传</title>
    <script src="${ctxStatic}/jquery-1.12.4.min.js"></script>
    <script src="${ctxStatic}/jquery.uploadify.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/uploadify.css" />

</head>
<body>
<h1>上传测试</h1>
<form>
    <div id="queue"></div>
    <input id="file_upload" name="file_upload" type="file" multiple="true">
</form>

<script type="text/javascript">
    $(function () {
        $("#file_upload").uploadify({
            height: 30,
            swf: '${ctxStatic}/uploadify.swf',
            uploader: '/uploadExcel',
            width: 120
        });
    });
</script>

</body>
</html>
