<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/include/taglib.jsp" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <title>带进度条上传</title>
    <link rel="stylesheet" type="text/css" href="${ctxStatic}/uploadify.css"/>
    <script src="${ctxStatic}/jquery-1.7.1.min.js"></script>
    <script src="${ctxStatic}/jquery.uploadify.min.js"></script>
    <style type="text/css">
        body {
            font: 13px Arial, Helvetica, Sans-serif;
        }
    </style>
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
            uploader: '/uploadFile',
            width: 120
        });
    });
</script>

</body>
</html>
