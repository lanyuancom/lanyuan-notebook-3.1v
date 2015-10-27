package com.lanyuan.controller.system;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lanyuan.annotation.SystemLog;
import com.lanyuan.controller.index.BaseController;
import com.lanyuan.entity.ButtomFormMap;
import com.lanyuan.entity.Params;
import com.lanyuan.entity.ResFormMap;
import com.lanyuan.entity.ResUserFormMap;
import com.lanyuan.entity.RoleFormMap;
import com.lanyuan.entity.RoleResFormMap;
import com.lanyuan.entity.UserGroupsFormMap;
import com.lanyuan.mapper.ResourcesMapper;
import com.lanyuan.mapper.RoleMapper;
import com.lanyuan.util.Common;
import com.lanyuan.util.TreeObject;
import com.lanyuan.util.TreeUtil;

/**
 * 
 * @author lanyuan 2014-11-19
 * @Email: mmm333zzz520@163.com
 * @version 3.0v
 */
@Controller
@RequestMapping("/resources/")
public class ResourcesController extends BaseController {
	@Inject
	private ResourcesMapper resourcesMapper;
	
	@Inject
	private RoleMapper roleMapper;
	/**
	 * @param model
	 *            存放返回界面的model
	 * @return
	 */
	@ResponseBody
	@RequestMapping("treelists")
	public ResFormMap findByPage(Model model) {
		ResFormMap resFormMap = getFormMap(ResFormMap.class);
		String order = " order by level asc";
		resFormMap.put("$orderby", order);
		List<ResFormMap> mps = resourcesMapper.findByNames(resFormMap);
		List<TreeObject> list = new ArrayList<TreeObject>();
		for (ResFormMap map : mps) {
			TreeObject ts = new TreeObject();
			Common.flushObject(ts, map);
			list.add(ts);
		}
		TreeUtil treeUtil = new TreeUtil();
		List<TreeObject> ns = treeUtil.getChildTreeObjects(list, 0);
		resFormMap = new ResFormMap();
		resFormMap.put("treelists", ns);
		return resFormMap;
	}

	@ResponseBody
	@RequestMapping("reslists")
	public List<TreeObject> reslists(Model model) throws Exception {
		ResFormMap resFormMap = getFormMap(ResFormMap.class);
		List<ResFormMap> mps = resourcesMapper.findByWhere(resFormMap);
		List<TreeObject> list = new ArrayList<TreeObject>();
		for (ResFormMap map : mps) {
			TreeObject ts = new TreeObject();
			Common.flushObject(ts, map);
			list.add(ts);
		}
		TreeUtil treeUtil = new TreeUtil();
		List<TreeObject> ns = treeUtil.getChildTreeObjects(list, 0, "　");
		return ns;
	}

	/**
	 * @param model
	 *            存放返回界面的model
	 * @return
	 */
	@RequestMapping("list")
	public String list(Model model) {
		model.addAttribute("res", findByRes());
		return Common.BACKGROUND_PATH + "/system/resources/list";
	}

	/**
	 * 跳转到修改界面
	 * 
	 * @param model
	 * @param resourcesId
	 *            修改菜单信息ID
	 * @return
	 */
	@RequestMapping("editUI")
	public String editUI(Model model) {
		String id = getPara("id");
		if(Common.isNotEmpty(id)){
			model.addAttribute("resources", resourcesMapper.findbyFrist("id", id, ResFormMap.class));
		}
		return Common.BACKGROUND_PATH + "/system/resources/edit";
	}

	/**
	 * 跳转到新增界面
	 * 
	 * @return
	 */
	@RequestMapping("addUI")
	public String addUI(Model model) {
		return Common.BACKGROUND_PATH + "/system/resources/add";
	}

	/**
	 * 权限分配页面
	 * 
	 * @author lanyuan Email：mmm333zzz520@163.com date：2014-4-14
	 * @param model
	 * @return
	 */
	@RequestMapping("permissions")
	public String permissions(Model model) {
		ResFormMap resFormMap = getFormMap(ResFormMap.class);
		List<ResFormMap> mps = resourcesMapper.findByWhere(resFormMap);
		List<TreeObject> list = new ArrayList<TreeObject>();
		for (ResFormMap map : mps) {
			TreeObject ts = new TreeObject();
			Common.flushObject(ts, map);
			list.add(ts);
		}
		TreeUtil treeUtil = new TreeUtil();
		List<TreeObject> ns = treeUtil.getChildTreeObjects(list, 0);
		model.addAttribute("permissions", ns);
		return Common.BACKGROUND_PATH + "/system/resources/permissions";
	}

	/**
	 * 添加菜单
	 * 
	 * @param resources
	 * @return Map
	 * @throws Exception
	 */
	@RequestMapping("addEntity")
	@ResponseBody
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="系统管理",methods="资源管理-新增资源")//凡需要处理业务逻辑的.都需要记录操作日志
	public String addEntity() throws Exception {
		ResFormMap resFormMap = getFormMap(ResFormMap.class);
		if("2".equals(resFormMap.get("type"))){
			resFormMap.put("description", Common.htmltoString(resFormMap.get("description")+""));
		}
		Object o = resFormMap.get("ishide");
		if(null==o){
			resFormMap.set("ishide", "0");
		}
		
		resourcesMapper.addEntity(resFormMap);
		return "success";
	}

	/**
	 * 更新菜单
	 * 
	 * @param model
	 * @param Map
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("editEntity")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="系统管理",methods="资源管理-修改资源")//凡需要处理业务逻辑的.都需要记录操作日志
	public String editEntity(Model model) throws Exception {
		ResFormMap resFormMap = getFormMap(ResFormMap.class);
		if("2".equals(resFormMap.get("type"))){
			resFormMap.put("description", Common.htmltoString(resFormMap.get("description")+""));
		}
		Object o = resFormMap.get("ishide");
		if(null==o){
			resFormMap.set("ishide", "0");
		}
		resourcesMapper.editEntity(resFormMap);
		return "success";
	}

	/**
	 * 根据ID删除菜单
	 * 
	 * @param model
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("deleteEntity")
	@SystemLog(module="系统管理",methods="资源管理-删除资源")//凡需要处理业务逻辑的.都需要记录操作日志
	public String deleteEntity(Model model) throws Exception {
		String[] ids = getParaValues("ids");
		for (String id : ids) {
			resourcesMapper.deleteByAttribute("id", id, ResFormMap.class);
		};
		return "success";
	}

	@RequestMapping("sortUpdate")
	@ResponseBody
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	public String sortUpdate(Params params) throws Exception {
		List<String> ids = params.getId();
		List<String> es = params.getRowId();
		List<ResFormMap> maps = new ArrayList<ResFormMap>();
		for (int i = 0; i < ids.size(); i++) {
			ResFormMap map = new ResFormMap();
			map.put("id", ids.get(i));
			map.put("level", es.get(i));
			maps.add(map);
		}
		resourcesMapper.updateSortOrder(maps);
		return "success";
	}

	/**
	 * 查找相关资源
	 * 2015年10月27日
	 * @author Ekko
	 */
	@ResponseBody
	@RequestMapping("findRes")
	public List<ResFormMap> findUserRes() {
		ResFormMap resFormMap = getFormMap(ResFormMap.class);
		List<ResFormMap> resQ = new ArrayList<ResFormMap>();
		// 根据不同的参数处理不同的查找
		// 如果是权限，则直接查找 return
		// 如果是用户，则先查找相关角色，查找结果组合成List return
		if(resFormMap.containsKey("roleId"))
		{
			resQ = resourcesMapper.findRes(resFormMap);
			return resQ;
		}
		else if(resFormMap.containsKey("userId"))
		{
			RoleFormMap roleFormMap = new RoleFormMap();
			roleFormMap.put("userId", resFormMap.get("userId"));
			List<RoleFormMap> roles = roleMapper.seletUserRole(roleFormMap);
			
			for(RoleFormMap role : roles)
			{
				resFormMap.clear();
				resFormMap.put("roleId", role.get("id"));
				List<ResFormMap> rs = resourcesMapper.findRes(resFormMap);
				for(ResFormMap res : rs)
				{
					resQ.add(res);
				}
			}
			return resQ;
		}
		return resQ;
	}
	@ResponseBody
	@RequestMapping("addUserRes")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="系统管理",methods="用户管理/组管理-修改权限")//凡需要处理业务逻辑的.都需要记录操作日志
	public String addUserRes() throws Exception {
		String userId = "";
		String u = getPara("userId");
		String g = getPara("roleId");
		if (null != u && !Common.isEmpty(u.toString())) {
			userId = u.toString();
		} else if (null != g && !Common.isEmpty(g.toString())) {
			List<UserGroupsFormMap> gs = resourcesMapper.findByAttribute("roleId", g.toString(), UserGroupsFormMap.class);
			for (UserGroupsFormMap ug : gs) {
				userId += ug.get("userId") + ",";
			}
		}
		userId = Common.trimComma(userId);
		String[] users = userId.split(",");
		for (String uid : users) {
			resourcesMapper.deleteByAttribute("userId", uid, ResUserFormMap.class);
			String[] s = getParaValues("resId[]");
			List<ResUserFormMap> resUserFormMaps = new ArrayList<ResUserFormMap>();
			for (String rid : s) {
				ResUserFormMap resUserFormMap = new ResUserFormMap();
				resUserFormMap.put("resId", rid);
				resUserFormMap.put("userId", uid);
				resUserFormMaps.add(resUserFormMap);
			
			}
			resourcesMapper.batchSave(resUserFormMaps);
		}
		return "success";
	}

	@ResponseBody
	@RequestMapping("findByButtom")
	public List<ButtomFormMap> findByButtom(){
		return resourcesMapper.findByWhere(new ButtomFormMap());
	}
	
	/**
	 * 验证菜单是否存在
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("isExist")
	@ResponseBody
	public boolean isExist(String name,String resKey) {
		ResFormMap resFormMap = getFormMap(ResFormMap.class);
		List<ResFormMap> r = resourcesMapper.findByNames(resFormMap);
		if (r.size()==0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @desc 角色分配权限 
	 * @author ekko 11:00:55
	 * @since 2015年10月27日
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("addRoleRes")
	@Transactional(readOnly=false)//需要事务操作必须加入此注解
	@SystemLog(module="系统管理",methods="角色管理/组管理-修改权限")//凡需要处理业务逻辑的.都需要记录操作日志
	public String addRoleRes() throws Exception {
		//角色对应多个资源id
		String roleId = getPara("roleId");
		String[] s = getParaValues("resId[]");
		List<RoleResFormMap> roleResFormMaps = new ArrayList<RoleResFormMap>();
		//角色关联多个资源id,先删除后保存
		resourcesMapper.deleteByAttribute("roleId", roleId, RoleResFormMap.class);
		for (String rid : s) {
			RoleResFormMap roleResFormMap = new RoleResFormMap();
			roleResFormMap.put("resId", rid);
			roleResFormMap.put("roleId", roleId);
			roleResFormMaps.add(roleResFormMap);
		}
		resourcesMapper.batchSave(roleResFormMaps);
		return "success";
	}
	
}
