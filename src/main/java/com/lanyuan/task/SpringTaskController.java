package com.lanyuan.task;

import java.util.Properties;

import javax.inject.Inject;

import org.hyperic.sigar.Sigar;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.lanyuan.entity.ServerInfoFormMap;
import com.lanyuan.mapper.ServerInfoMapper;
import com.lanyuan.util.Common;
import com.lanyuan.util.EmailUtils;
import com.lanyuan.util.PropertiesUtils;
import com.lanyuan.util.SystemInfo;

/**
 * Spring调度，指定时间执行
 * 利用了spring中使用注解的方式来进行任务调度。 
 * @author lanyuan
 * 2015-05-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Component
@Lazy(false)
public class SpringTaskController {
	@Inject
	private ServerInfoMapper serverInfoMapper;

	public static void main(String[] args) {
		SpringTaskController action = new SpringTaskController();
		try {
			action.task();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 与用户设置的使用率比较 spirng 调度
	 * 
	 * @throws Exception
	 */
	@Scheduled(cron = "1 * *  * * ? ")
	public void task() throws Exception {
		ServerInfoFormMap usage = SystemInfo.usage(new Sigar());
		String cpuUsage = usage.get("cpuUsage")+"";// CPU使用率
		String serverUsage = usage.get("ramUsage")+"";// 系统内存使用率
		String JvmUsage = usage.get("jvmUsage")+"";// 计算ＪＶＭ内存使用率
		Properties prop = PropertiesUtils.getProperties();
		String cpu = prop.getProperty("cpu");
		String jvm = prop.getProperty("jvm");
		String ram = prop.getProperty("ram");
		String email = prop.getProperty("toEmail");
		// 当系统消耗内存大于或等于用户设定的内存时，发送邮件
		String cpubool = "";
		String jvmbool = "";
		String rambool = "";
		String mark = "<font color='red'>";
		if (Double.parseDouble(cpuUsage) > Double.parseDouble(cpu)) {
			cpubool = "style=\"color: red;font-weight: 600;\"";
			mark += "CPU当前：" + cpuUsage + "%,超出预设值  " + cpu + "%<br>";
		}
		if (Double.parseDouble(JvmUsage) > Double.parseDouble(jvm)) {
			jvmbool = "style=\"color: red;font-weight: 600;\"";
			mark += "JVM当前：" + JvmUsage + "%,超出预设值 " + jvm + "%<br>";
		}
		if (Double.parseDouble(serverUsage) > Double.parseDouble(ram)) {
			rambool = "style=\"color: red;font-weight: 600;\"";
			mark += "内存当前：" + serverUsage + "%,超出预设值  " + ram + "%";
		}
		mark += "</font>";
		// 邮件内容

		String title = "服务器预警提示 - "+Common.fromDateH();
		String centent = "当前时间是：" + Common.fromDateH() + "<br/><br/>" + "<style type=\"text/css\">" + ".common-table{" + "-moz-user-select: none;" + "width:100%;" + "border:0;" + "table-layout : fixed;" + "border-top:1px solid #dedfe1;" + "border-right:1px solid #dedfe1;" + "}" +

		"/*header*/" + ".common-table thead td,.common-table thead th{" + "    height:23px;" + "   background-color:#e4e8ea;" + "   text-align:center;" + "   border-left:1px solid #dedfe1;" + "}" +

		".common-table thead th, .common-table tbody th{" + "padding-left:7px;" + "padding-right:7px;" + "width:15px;" + "text-align:center;" + "}" +

		".common-table tbody td,  .common-table tbody th{" + "    height:25px!important;" + "border-bottom:1px solid #dedfe1;" + "border-left:1px solid #dedfe1;" + "cursor:default;" + "word-break: break-all;" + "-moz-outline-style: none;" + "_padding-right:7px;" + "text-align:center;" + "}</style>"
				+ "<table class=\"common-table\">" + "<thead>" + "<tr>" + "<td width=\"100\">名称</td>" + "<td width=\"100\">参数</td>" + "<td width=\"275\">预警设置</td>" + "</tr>" + "</thead>" + "<tbody id=\"tbody\">" + "<tr " + cpubool + "><td>CPU</td><td style=\"text-align: left;\">当前使用率：" + cpuUsage
				+ "%</td><td>使用率超出  " + cpu + "%,,发送邮箱提示 </td></tr>" + "<tr " + rambool + "><td>服务器内存</td><td style=\"text-align: left;\">当前使用率：" + serverUsage + "%</td><td>使用率超出  " + ram + "%,发送邮箱提示 </td></tr>" + "<tr " + jvmbool + "><td>JVM内存</td><td style=\"text-align: left;\">当前使用率：" + JvmUsage
				+ "%</td><td>使用率超出  " + jvm + "%,,发送邮箱提示 </td></tr>" + "</tbody>" + "</table>";
		mark = mark.replaceAll("'","\"");
		if (!Common.isEmpty(cpubool) || !Common.isEmpty(jvmbool) || !Common.isEmpty(rambool)) {
			try {
				EmailUtils.sendMail(prop.getProperty("fromEmail"), email, prop.getProperty("emailName"), prop.getProperty("emailPassword"), title, centent);
				// 保存预警信息
				usage.put("setCpuUsage", cpu);
				usage.put("setJvmUsage", jvm);
				usage.put("setRamUsage", ram);
				usage.put("email", email);
				usage.put("mark", mark);
				serverInfoMapper.addEntity(usage);
				System.err.println("发送邮件！");
			} catch (Exception e) {
				System.err.println("发送邮件失败！");
			}
		}
	}
}