<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<div class="col-md-12">
		<div class="alert alert-warning alert-block">
		<table>
		<tr>
		<td align="center" colspan="2"><h1>蓝缘开源系统</h1></td>
		</tr>
		<tr>
		<td><div class="topBtn" style="font-size: 15px;">
				
		          	<div style="padding-left: 20px;color: red;">	
		          	 1.0版和2.0版的源码已经开源<br>
		          	 1.0版本：http://blog.csdn.net/mmm333zzz/article/details/16863543<br>
　　　				 2.0版本：http://blog.csdn.net/mmm333zzz/article/details/37773589
		          	</div><br>
								关于3.0新版本的说明：
								<div style="padding-left: 20px;">
								一大亮点：<div style="padding-left: 20px;color: red;">采用最新的技术流行框架：springMVC4.1.4+shiro1.2.3+spring4.x+Mybaits3.2.8+Ajax+html5<br>
								</div>
								spring4.x的新特性请看：
								<div style="padding-left: 20px;color: blue;">
								http://jinnianshilongnian.iteye.com/blog/1989381
								</div>
								</div>
								<div style="padding-left: 20px;">
									说明：
									<div style="padding-left: 20px;">
									这个版本主要是对原有的蓝缘系统更换UI界面,功能上基本一致,
									但此还在开发当中..... 关于以前版本,不再维护,致力于新版本的开发和维护..
									</div>
									优化：
									<div style="padding-left: 20px;color: blue;padding-top: 10px;">
									封装好baseSerive,baseSeriveImpl,baseMapper..服务层，持久层统一调用,大大减少代码开发时间.
									</div>
									<div style="padding-left: 20px;color: blue;padding-top: 10px;">
									spring4.x的强类型注解，泛型限定式依赖注入
									</div>
									<div style="padding-left: 20px;padding-top: 10px;">
									用mapper来代替dao,由mybaits自动管理各事务的操作,大大减少代码开发时间.
									</div>
									<div style="padding-left: 20px;color: red;padding-top: 10px;">
									3.0版本不再使用spring Security3权限安全机制,采用了shiro权限机制,
									为何愿意使用Apache Shiro？请看：http://www.infoq.com/cn/articles/apache-shiro
									</div>
									技术要点：
									<div style="padding-left: 20px;">
									1：此版本采用ajax+js分页,表格lyGrid分页插件是群主自己写的,有点模仿ligerui的分页实现
									<br>2：列表的表头固定,兼容IE,firefox,google,360的浏览器,其他暂没有测试.<br>
									3：表格排序功能<br>
									4：弹窗采用贤心的插件，网址：http://sentsin.com/jquery/layer/<br>
									5：加入druid技术,对sql语句的监控.<br>
									6：自定义注解导出excel<br>
									7：<font color="blue">使用了ehcache缓存机制</font><br>
									8：<font color="blue">新增支持oracle分页实现</font><br>
									9：<font color="blue">新增支持SQLserver2008分页实现</font><br>
									10：<font color="blue">解决分页参数没法传到后台的问题</font><br>
									11：<font color="blue">异常统一处理</font><br>
									12：<font color="blue">使用spring Security3权限安全机制,采用了shiro权限机制</font><br>
									13：<font color="blue">封装好baseSerive,baseSeriveImpl,baseMapper..服务层，持久层统一调用</font><br>
									14：........<br>
								</div></div></div>
		</td>
		<td style="width: 290px;"><div
				style="PADDING-RIGHT: 0px; PADDING-LEFT: 0px; PADDING-BOTTOM: 0px; PADDING-TOP: 0px; WIDTH: 100%; HEIGHT: 148px; border: 1px solid #cacaca; background: #FFFFFF">
				<div
					style="WIDTH: 100%; clear: both; height: 31px; background-image: url(http://www.tianqi.com/static/images/code/bg_13.jpg); background-repeat: repeat-x; border-bottom: 1px solid #cacaca;">
					<div
						style="float: left; height: 31px; color: #9e0905; font-weight: bold; line-height: 31px; margin-left: 20px; font-size: 14px;">城市天气预报</div>

				</div>
				<iframe width="400" scrolling="no" height="120" frameborder="0"
					allowtransparency="true"
					src="http://i.tianqi.com/index.php?c=code&id=19&bgc=%23FFFFFF&bdc=%23&icon=1&temp=1&num=2"></iframe>
			</div></td>
		</tr>
		</table>
		</div>
	</div>
	<div class="col-md-6">
		<div class="alert alert-danger">
			<button type="button" class="close" data-dismiss="alert">×</button>
								<div style="font-size: 17px;">
							         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 蓝缘有一个自己的网站空间,所有在学习和使用蓝缘系统的同志们捐助1元或2元就行(要求不过分吧!街边吃顿都10元以上啊.....),
							       以下是蓝缘系统捐助的支付页面，由于空间网站需求收费，故此征集大家为蓝缘系统做一点贡献，有了你的支持，蓝缘系统将做得更好，服务开源，献身开源！再次致谢 ！
							     </div><img alt="蓝缘系统的支付连接" src="${pageContext.request.contextPath}/images/zhifubao.png" title="蓝缘系统的支付连接"> <span title="蓝缘系统的支付连接" style="font-size: 20px;color: blue;" >支付宝账号是:mmm333zzz520@163.com</span><br> <span title="蓝缘系统的支付连接" style="font-size: 20px;color: blue;" >支付地址: 支付地址已经关闭,亲可以直接转到支付宝账号上，记得备注哦！</span>
								</div>
	</div>
	<div class="col-md-6">
		<div class="alert alert-success">
			<button type="button" class="close" data-dismiss="alert">×</button>
			<table style="width: 100%;">
				<tr>
					<td style="font-size: 17px; color: blue; width: 150px;"
						valign="top">感谢捐助:</td>
					<td align="left" style="font-size: 14px; color: blue;">
						以下是蓝缘系统捐助的支付页面，由于空间网站需求收费，故此征集大家为蓝缘系统做一点贡献，有了你的支持，蓝缘系统将做得更好，服务开源，献身开源！再次致谢
						！</td>
				</tr>
				<tr>
					<td>支付宝账号是:</td>
					<td style="font-size: 17px; color: blue;">mmm333zzz520@163.com</td>
				</tr>
				<tr>
					<td width="116"><img alt="蓝缘系统的支付连接"
						src="${pageContext.request.contextPath}/images/zhifubao.png"></td>
					<td><span title="蓝缘系统的支付连接"
						style="font-size: 17px; color: blue;">支付地址已经关闭,亲可以直接转到支付宝账号上，<span style="font-size: 17px; color: red;">捐助请备注哦！谢谢！</span></span></td>
					
				</tr>
				<tr>
					<td>技术支持:</td>
					<td style="font-size: 17px; color: blue;">QQ:421828229</td>
				</tr>
				<tr>
					<td>QQ交流群:</td>
					<td style="font-size: 17px; color: blue;">[官] ①90993106(满) [官] ②96511102(满) [官] ③464063178(满) [官] ④387039123 (加群请说明官网账号!)</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="col-md-12">
		<div class="alert alert-info">
			<button type="button" class="close" data-dismiss="alert">×</button>
			<div style="font-size: 14px; text-align: left; padding: 5px;">
				<span style="font-weight: 800; font-size: 18px;">感谢捐助者:&nbsp;&nbsp;&nbsp;<span
					style="font-size: 12px;">(排名不分先后.可能有些忘记了写上,可以联系我,我补上,非常感谢各位)</span></span><br>
				<table width="100%">
						<tr>
							<td>沧海一粟&nbsp;&nbsp;&nbsp;200元</td>
							<td>apache&nbsp;&nbsp;&nbsp;5元</td>
							<td>Alias&nbsp;&nbsp;&nbsp;5元</td>
							<td>张X名&nbsp;&nbsp;&nbsp;4元</td>
							<td>Could&nbsp;&nbsp;&nbsp;10元</td>
							<td>小滴棍&nbsp;&nbsp;&nbsp;50元</td>
							<td>上海lee&nbsp;&nbsp;&nbsp;100元</td>
							<td>逗留&nbsp;&nbsp;&nbsp;5元</td>
						</tr>
						<tr>
							<td>明月不懂&nbsp;&nbsp;&nbsp;20元</td>
							<td>翱翔-北京&nbsp;&nbsp;&nbsp;50元</td>
							<td>老周&nbsp;&nbsp;&nbsp;50元</td>
							<td>天涯无痕&nbsp;&nbsp;&nbsp;50元</td>
							<td>其实我很Z&nbsp;&nbsp;&nbsp;4元</td>
							<td>小胖&nbsp;&nbsp;&nbsp;50元</td>
							<td>南大西洋&nbsp;&nbsp;&nbsp;10元</td>
							<td>回忆&nbsp;&nbsp;&nbsp;20元</td>
						</tr>
						<tr>
							<td>王懿&nbsp;&nbsp;&nbsp;50元</td>
							<td>初来乍到&nbsp;&nbsp;&nbsp;150元</td>
							<td>久成&nbsp;&nbsp;&nbsp;5元</td>
							<td>修成&nbsp;&nbsp;&nbsp;30元</td>
							<td>何银&nbsp;&nbsp;&nbsp;5元</td>
							<td>小张&nbsp;&nbsp;&nbsp;6元</td>
							<td>家铭&nbsp;&nbsp;&nbsp;1元</td>
							<td>LemonTree&nbsp;&nbsp;&nbsp;66元</td>
						</tr>

						<tr>
							<td>风过&nbsp;&nbsp;&nbsp;30元</td>
							<td>邓X剑&nbsp;&nbsp;&nbsp;3元</td>
							<td>芳X光&nbsp;&nbsp;&nbsp;5元</td>
							<td>金燕&nbsp;&nbsp;&nbsp;11元</td>
							<td>李灿&nbsp;&nbsp;&nbsp;11.42元</td>
							<td>徐帅&nbsp;&nbsp;&nbsp;35元</td>
							<td>飞翔的度人&nbsp;&nbsp;&nbsp;10元</td>
							<td>南大西洋&nbsp;&nbsp;&nbsp;10元</td>
						</tr>
						<tr>
							<td>孟鹏&nbsp;&nbsp;&nbsp;60元</td>
							<td>张飞写代码&nbsp;&nbsp;&nbsp;20元</td>
							<td>李X明&nbsp;&nbsp;&nbsp;5元</td>
							<td>王X明&nbsp;&nbsp;&nbsp;100元</td>
							<td>wyx065747&nbsp;&nbsp;&nbsp;50元</td>
							<td>徐X影&nbsp;&nbsp;&nbsp;50元</td>
							<td>大山&nbsp;&nbsp;&nbsp;20元</td>
							<td>LemonTree&nbsp;&nbsp;&nbsp;66元</td>
						</tr>
						<tr>
							<td>方超&nbsp;&nbsp;&nbsp;5元</td>
							<td>刘振兴&nbsp;&nbsp;&nbsp;30元</td>
							<td>沈新权&nbsp;&nbsp;&nbsp;10元</td>
							<td>蒋龙&nbsp;&nbsp;&nbsp;20元</td>
							<td>赵俊&nbsp;&nbsp;&nbsp;100元</td>
							<td>王松&nbsp;&nbsp;&nbsp;100元</td>
							<td>王书青&nbsp;&nbsp;&nbsp;50元</td>
							<td>唐鹏涛&nbsp;&nbsp;&nbsp;50元</td>
						</tr>
						<tr>
							<td>陈勇&nbsp;&nbsp;&nbsp;15元</td>
							<td>陈炼&nbsp;&nbsp;&nbsp;5元</td>
							<td>王文生&nbsp;&nbsp;&nbsp;5元</td>
							<td>梁志均&nbsp;&nbsp;&nbsp;33元</td>
							<td>潘永辉&nbsp;&nbsp;&nbsp;10元</td>
							<td>魏学鹏&nbsp;&nbsp;&nbsp;9元</td>
							<td>寇东旭&nbsp;&nbsp;&nbsp;140元</td>
							<td>Exception&nbsp;&nbsp;&nbsp;10元</td>
						</tr>
						<tr>
							<td>曾红&nbsp;&nbsp;&nbsp;2元</td>
							<td>王大忠&nbsp;&nbsp;&nbsp;100元</td>
							<td>齐铁鑫&nbsp;&nbsp;&nbsp;10元</td>
							<td>石路&nbsp;&nbsp;&nbsp;5元</td>
							<td>李惊宇&nbsp;&nbsp;&nbsp;30元</td>
							<td>杜建国&nbsp;&nbsp;&nbsp;5元</td>
							<td>徐健&nbsp;&nbsp;&nbsp;7元</td>
							<td>刘宇洋&nbsp;&nbsp;&nbsp;5元</td>
						</tr>
						<tr>
							<td>武国君&nbsp;&nbsp;&nbsp;100元</td>
							<td>吉广志&nbsp;&nbsp;&nbsp;5元</td>
							<td>孙淑光&nbsp;&nbsp;&nbsp;20元</td>
							<td>玩卟亓&nbsp;&nbsp;&nbsp;10元</td>
							<td>林茂盛&nbsp;&nbsp;&nbsp;20元</td>
							<td>李鹏&nbsp;&nbsp;&nbsp;30元</td>
							<td>李刚&nbsp;&nbsp;&nbsp;50元</td>
							<td>梅晓访&nbsp;&nbsp;&nbsp;60元</td>
						</tr>
						<tr>
							<td>徐章凡&nbsp;&nbsp;&nbsp;40元</td>
							<td>李文轩&nbsp;&nbsp;&nbsp;50元</td>
							<td>贾瑞霞&nbsp;&nbsp;&nbsp;5元</td>
							<td>佟新宇&nbsp;&nbsp;&nbsp;5元</td>
							<td>杨伟杰&nbsp;&nbsp;&nbsp;15元</td>
							<td>杨伟杰&nbsp;&nbsp;&nbsp;2元</td>
							<td>xiaolong&nbsp;&nbsp;&nbsp;20元</td>
							<td>范磊&nbsp;&nbsp;&nbsp;100元</td>
						</tr>
						
						<tr>
							<td>汤峥&nbsp;&nbsp;&nbsp;5元</td>
							<td>李志超&nbsp;&nbsp;&nbsp;100元</td>
							<td>杨彬&nbsp;&nbsp;&nbsp;10元</td>
							<td>赵文青&nbsp;&nbsp;&nbsp;10元</td>
							<td>张志敏&nbsp;&nbsp;&nbsp;100元</td>
							<td>幸儿&nbsp;&nbsp;&nbsp;30元</td>
							<td>王佳&nbsp;&nbsp;&nbsp;20元</td>
							<td>李涛&nbsp;&nbsp;&nbsp;30元</td>
						</tr>
						<tr>
							<td>李志杰&nbsp;&nbsp;&nbsp;10元</td>
							<td>左志豪&nbsp;&nbsp;&nbsp;8元</td>
							<td>肖叮&nbsp;&nbsp;&nbsp;50元</td>
							<td>黄光华&nbsp;&nbsp;&nbsp;18元</td>
							<td>冯博&nbsp;&nbsp;&nbsp;10元</td>
							<td>林国军&nbsp;&nbsp;&nbsp;5元</td>
							<td>黄文祥&nbsp;&nbsp;&nbsp;10元</td>
							<td>封狮&nbsp;&nbsp;&nbsp;50元</td>
						</tr>
						<tr>
							<td>张新宜&nbsp;&nbsp;&nbsp;50元</td>
							<td>吴鹏&nbsp;&nbsp;&nbsp;10元</td>
							<td>游龙龙&nbsp;&nbsp;&nbsp;10元</td>
							<td>王海&nbsp;&nbsp;&nbsp;20元</td>
							<td>陈路宽&nbsp;&nbsp;&nbsp;10元</td>
							<td>朱网&nbsp;&nbsp;&nbsp;50元</td>
							<td>曹晓彤&nbsp;&nbsp;&nbsp;10元</td>
							<td>胡进辉&nbsp;&nbsp;&nbsp;5元</td>
						</tr>
						<tr>
							<td>杨广州&nbsp;&nbsp;&nbsp;88.88元</td>
							<td>王荣生&nbsp;&nbsp;&nbsp;100元</td>
							<td>周广文&nbsp;&nbsp;&nbsp;5元</td>
							<td>葛长城&nbsp;&nbsp;&nbsp;5元</td>
							<td>孙昊&nbsp;&nbsp;&nbsp;50元</td>
							<td>罗伟浩&nbsp;&nbsp;&nbsp;18元</td>
							<td>陈晗琪&nbsp;&nbsp;&nbsp;20元</td>
							<td>你的样子&nbsp;&nbsp;&nbsp;20元</td>
						</tr>
						<tr>
							<td>梁华锋&nbsp;&nbsp;&nbsp;5元</td>
							<td>付尚军&nbsp;&nbsp;&nbsp;30元</td>
							<td>高超&nbsp;&nbsp;&nbsp;20元</td>
							<td>冯昊&nbsp;&nbsp;&nbsp;10元</td>
							<td>卓银川&nbsp;&nbsp;&nbsp;10元</td>
							<td>胡彪&nbsp;&nbsp;&nbsp;50元</td>
							<td>李俊杰&nbsp;&nbsp;&nbsp;10元</td>
							<td>高敏&nbsp;&nbsp;&nbsp;15元</td>
						</tr>
						
						<tr>
							<td>时文娟&nbsp;&nbsp;&nbsp;20元</td>
							<td>许正亮&nbsp;&nbsp;&nbsp;20元</td>
							<td>俞峰&nbsp;&nbsp;&nbsp;5元</td>
							<td>李慎云&nbsp;&nbsp;&nbsp;20元</td>
							<td>唐敏&nbsp;&nbsp;&nbsp;50元</td>
							<td>张志刚&nbsp;&nbsp;&nbsp;50元</td>
							<td>郑中平&nbsp;&nbsp;&nbsp;1元</td>
							<td>要坚强&nbsp;&nbsp;&nbsp;10元</td>
						</tr>
						
						<tr>
							<td>李炜&nbsp;&nbsp;&nbsp;5元</td>
							<td>赵增兵&nbsp;&nbsp;&nbsp;5元</td>
							<td>志福&nbsp;&nbsp;&nbsp;1元</td>
							<td>陈辉&nbsp;&nbsp;&nbsp;15元</td>
							<td>Robin&nbsp;&nbsp;&nbsp;50元</td>
							<td>建民&nbsp;&nbsp;&nbsp;20元</td>
							<td>明日璀璨&nbsp;&nbsp;&nbsp;15元</td>
							<td>龚稽心&nbsp;&nbsp;&nbsp;30元</td>
						</tr>
						
						<tr>
							<td>Jy&nbsp;&nbsp;&nbsp;18元</td>
							<td>lixuegang&nbsp;&nbsp;&nbsp;5元</td>
							<td>余云霞&nbsp;&nbsp;&nbsp;30元</td>
							<td>陌上花开&nbsp;&nbsp;&nbsp;15元</td>
							<td>商阳剑&nbsp;&nbsp;&nbsp;10元</td>
							<td>杜海&nbsp;&nbsp;&nbsp;50元</td>
							<td>杨瑜栋&nbsp;&nbsp;&nbsp;50元</td>
							<td>海俊&nbsp;&nbsp;&nbsp;50元</td>
						</tr>
						
						<tr>
							<td>小吕布&nbsp;&nbsp;&nbsp;5元</td>
							<td>鹏飞&nbsp;&nbsp;&nbsp;5元</td>
							<td>商阳剑&nbsp;&nbsp;&nbsp;10元</td>
							<td>辉灿&nbsp;&nbsp;&nbsp;5元</td>
							<td>镇伟&nbsp;&nbsp;&nbsp;10元</td>
							<td>宗亮&nbsp;&nbsp;&nbsp;5元</td>
							<td>Darker&nbsp;&nbsp;&nbsp;15元</td>
							<td>开健&nbsp;&nbsp;&nbsp;10元</td>
						</tr>
						
						<tr>
							<td>星&nbsp;&nbsp;&nbsp;15元</td>
							<td>跃飞&nbsp;&nbsp;&nbsp;10元</td>
							<td>占平&nbsp;&nbsp;&nbsp;10元</td>
							<td>长伟&nbsp;&nbsp;&nbsp;1元</td>
							<td>刀疤薛&nbsp;&nbsp;&nbsp;2元</td>
							<td>F&nbsp;&nbsp;&nbsp;10元</td>
							<td>伟炬&nbsp;&nbsp;&nbsp;20元</td>
							<td>耀山&nbsp;&nbsp;&nbsp;5元</td>
						</tr>
						
						<tr>
							<td>冯根&nbsp;&nbsp;&nbsp;4.65元</td>
							<td>亚峰&nbsp;&nbsp;&nbsp;50元</td>
							<td>凡&nbsp;&nbsp;&nbsp;10元</td>
							<td>玉兵&nbsp;&nbsp;&nbsp;10元</td>
							<td>鹏&nbsp;&nbsp;&nbsp;20元</td>
							<td>志超&nbsp;&nbsp;&nbsp;5元</td>
							<td>球球她爸&nbsp;&nbsp;&nbsp;17元</td>
							<td>米家大哥&nbsp;&nbsp;&nbsp;50元</td>
						</tr>
						<tr>
							<td>soul13631&nbsp;&nbsp;&nbsp;20元</td>
							<td>八零&nbsp;&nbsp;&nbsp;100元</td>
							<td>*文正&nbsp;&nbsp;&nbsp;50元</td>
							<td>小波&nbsp;&nbsp;&nbsp;5元</td>
							<td>gecc&nbsp;&nbsp;&nbsp;36元</td>
							<td>南瓜王子&nbsp;&nbsp;&nbsp;20元</td>
							<td>和风&nbsp;&nbsp;&nbsp;10元</td>
							<td>狼King&nbsp;&nbsp;&nbsp;20元</td>
						</tr>
						<tr>
							<td>lufy&nbsp;&nbsp;&nbsp;10元</td>
							<td>俊潮&nbsp;&nbsp;&nbsp;20元</td>
							<td>dayton&nbsp;&nbsp;&nbsp;10元</td>
							<td>孙浩&nbsp;&nbsp;&nbsp;10元</td>
							<td>静&nbsp;&nbsp;&nbsp;5元</td>
							<td>海涛&nbsp;&nbsp;&nbsp;10元</td>
							<td>睿&nbsp;&nbsp;&nbsp;100元</td>
							<td>newbalance&nbsp;&nbsp;&nbsp;2元</td>
						</tr>
						<tr>
							<td>志红&nbsp;&nbsp;&nbsp;10元</td>
							<td>aha**@163.com&nbsp;&nbsp;&nbsp;5元</td>
							<td>高国清&nbsp;&nbsp;&nbsp;10元</td>
							<td>庶衍&nbsp;&nbsp;&nbsp;10元</td>
							<td>青&nbsp;&nbsp;&nbsp;15元</td>
							<td>W.J.&nbsp;&nbsp;&nbsp;50元</td>
							<td>贺&nbsp;&nbsp;&nbsp;21元</td>
							<td>飞&nbsp;&nbsp;&nbsp;10元</td>
						</tr>
						<tr>
							<td>敬南&nbsp;&nbsp;&nbsp;2元</td>
							<td>简简单单&nbsp;&nbsp;&nbsp;1.29元</td>
							<td>喻小琦&nbsp;&nbsp;&nbsp;5元</td>
							<td>南大西洋&nbsp;&nbsp;&nbsp;15元</td>
							<td>小丢丢&nbsp;&nbsp;&nbsp;10元</td>
							<td>浚&nbsp;&nbsp;&nbsp;5元</td>
							<td>&nbsp;&nbsp;&nbsp;</td>
							<td>&nbsp;&nbsp;&nbsp;</td>
						</tr>
					</table>

			</div>
		</div>
	</div>
