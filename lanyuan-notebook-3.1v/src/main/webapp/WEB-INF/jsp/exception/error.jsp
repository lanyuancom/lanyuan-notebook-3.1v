<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head><title>Exception!</title></head>
<body>
<% Exception e = (Exception)request.getAttribute("ex"); %>
<H2 style="color: blue;">未知错误: <%= e.getClass().getName()%></H2>
<hr />
<P>错误描述：</P>
<span style="color: red;">
<%= e.getMessage()%></span>
<P>错误信息：</P>
<span style="color: blue;">
<% e.printStackTrace(new java.io.PrintWriter(out)); %></span>
</body>
</html>