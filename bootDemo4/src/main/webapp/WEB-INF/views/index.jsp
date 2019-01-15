<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>bootDemo4</title>
</head>
<body>test ${hello}

<div style="text-align: center;margin-top: 50px">
	<form action="video/insert" method="post" enctype="multipart/form-data">
		<p><input type="file" name="fileName"/></p>
		<p><input type="submit" value="上传视频"/></p>
	</form>
	<%--<form action="chaxun" >--%>
		<%--<p><input type="submit" value="查询视频"/></p>--%>
	<%--</form>--%>
</div>
<script type="text/javascript">
	// window.location.href="http://192.168.88.154/sourceadmin/#/login";
</script>
</body>
</html>