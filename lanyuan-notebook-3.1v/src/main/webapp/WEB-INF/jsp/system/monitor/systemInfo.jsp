<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table class="table table-striped table-bordered table-hover" style="margin: 0px;">
	<tbody>
		<tr>
			<td class="left">ip地址</td>
			<td id="hostIp" class="left">${systemInfo.hostIp}</td>
		</tr>
		<tr>
			<td class="left">主机名</td>

			<td class="left" id="hostName">${systemInfo.hostName}</td>
		</tr>
		<tr>
			<td class="left">操作系统的名称</td>

			<td class="left" id="osName">${systemInfo.osName}</td>
		</tr>
		<tr>
			<td class="left">操作系统的构架</td>

			<td class="left" id="arch">${systemInfo.arch}</td>
		</tr>
		<tr>
			<td class="left">操作系统的版本</td>

			<td class="left" id="osVersion">${systemInfo.osVersion}</td>
		</tr>
		<tr>
			<td class="left">处理器个数</td>

			<td class="left" id="processors">${systemInfo.processors}</td>
		</tr>
		<tr>
			<td class="left">Java的运行环境版本</td>

			<td class="left" id="javaVersion">${systemInfo.javaVersion}</td>
		</tr>
		<tr>
			<td class="left">Java供应商的URL</td>

			<td class="left" id="javaUrl">${systemInfo.javaUrl}</td>
		</tr>
		<tr>
			<td class="left">Java的安装路径</td>

			<td class="left" id="javaHome">${systemInfo.javaHome}</td>
		</tr>
		<tr>
			<td class="left">临时文件路径</td>

			<td class="left" id="tmpdir">${systemInfo.tmpdir}</td>
		</tr>
	</tbody>
</table>