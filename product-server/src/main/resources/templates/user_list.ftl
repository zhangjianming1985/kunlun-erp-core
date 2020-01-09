<html>
	<head>
		<title>显示用户信息</title>
		<meta charset="utf-8"></meta>
	</head>
	<body>
		<table border="1" align="center" with="50%">
			<tr>
				<th>ID</th>
				<th>NAME</th>
			</tr>
			<#list list as user>
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
			</tr>
			</#list>
		</table>
	</body>
</html>