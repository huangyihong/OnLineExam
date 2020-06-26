package com.plg.shiro.controller.system;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.plg.shiro.entity.OmRole;
import com.plg.shiro.entity.OmUser;
import com.plg.shiro.entity.OmUserGroup;
import com.plg.shiro.service.IRoleService;
import com.plg.shiro.service.IUserGroupService;
import com.plg.shiro.service.IUserRoleService;
import com.plg.shiro.service.IUserService;
import com.plg.shiro.util.DateUtil;
import com.plg.shiro.util.DownloadUtils;
import com.plg.shiro.util.ExcelUtils;
import com.plg.shiro.util.Md5;
import com.plg.shiro.util.UUIDUtil;
import com.plg.shiro.util.dwz.AjaxObject;
import com.plg.shiro.util.dwz.LayuiPage;

/**
 * 用户模块
 *
 */
@Controller
@RequestMapping({ "/admin/system/omUser" })
public class UserController {
	
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource
	private IUserService service;
	
	@Resource
	private IUserGroupService groupService;
	
	@Resource
	private IRoleService roleService;
	
	@Resource
	private IUserRoleService userRoleService;
	
	private static final String USER_PATH = "admin/system/omUser/";
	
	/**
	 * 用户管理列表页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/list" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String list(HttpServletRequest request) {
		return USER_PATH+"list";
	}
	
	@RequestMapping(value = { "/studentList" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String studentList(HttpServletRequest request) {
		return USER_PATH+"studentList";
	}
	
	//获取列表数据
  	@RequestMapping("getList")
    @ResponseBody
  	public AjaxObject getList(HttpServletRequest request,LayuiPage page) throws Exception {
  	  	AjaxObject result = AjaxObject.newOk("success");
  			try {
  				List<OmUser> list = service.findPageList(request, page);
  				result.setData(list);
  				result.setTotal(page.getTotalCount());
  			} catch(Exception e) {
  				return AjaxObject.newError(e.getMessage());
  			}
  			return result;
  	}
  	
  	 //新增修改页面
  	@RequestMapping(value = { "/create" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
  	public String create(HttpServletRequest request) {
  		String userId = request.getParameter("userId");
  		OmUser bean = new OmUser();
		if(StringUtils.isNotBlank(userId)){
			bean = service.selectByPrimaryKey(userId);
    	}else {
    		bean.setUserType(request.getParameter("userType"));
    	}
		request.setAttribute("bean", bean);
    	request.setAttribute("fntype", request.getParameter("fntype"));
    	request.setAttribute("userType", request.getParameter("userType"));
    	List<OmUserGroup> groupList =groupService.selectAll();
    	request.setAttribute("groupList", groupList);
    	return USER_PATH+"create";
  	}

    //保存修改操作
    @RequestMapping("save")
    @ResponseBody
	public AjaxObject save(HttpServletRequest request,OmUser bean){
    	AjaxObject result = AjaxObject.newOk("success");
		try {
			String fntype = request.getParameter("fntype");
			if ("update".equals(fntype)) {
				bean.setUpdateTime(DateUtil.dateParse(new Date(),""));
				service.updateByPrimaryKeySelective(bean);
			}else{
				bean.setUserId(UUIDUtil.getUUID());
				bean.setCreateTime(DateUtil.dateParse(new Date(),""));
				bean.setPassword(Md5.getMD5ofStrByLowerCase(String.valueOf(bean.getPassword())));
				service.insert(bean);
			}
		} catch (Exception e) {
			logger.error("保存修改用户信息："  , e);
			return AjaxObject.newError(e.getMessage());
		}
		return result;
	}
    
    //批量删除操作
    @RequestMapping("del")
    @ResponseBody
	public AjaxObject del(HttpServletRequest request,String ids){
    	AjaxObject result = AjaxObject.newOk("success");
		try {
			service.deleteBatch(ids);
		} catch (Exception e) {
			logger.error("删除用户信息："  , e);
			return AjaxObject.newError(e.getMessage());
		}
		return result;
	}
    
    //用户名是否重复
    @RequestMapping(value = { "/isExistName" })
   	@ResponseBody
   	public AjaxObject isExistName(String userName,HttpServletRequest request){
    	OmUser bean= service.selectByUserName(userName);
    	boolean flag = false;
    	if(bean!=null){
    		flag = true;
    	}
   		return AjaxObject.newOk("操作成功",flag);
   	}
    
    /**
	 * 用户角色授权页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/assignRole" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String assignRole(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		request.setAttribute("userId", userId);
		return USER_PATH+"assignRole";
	}
	
	//获取所有用户
    @RequestMapping(value = { "/getAllUserList" })
   	@ResponseBody
   	public AjaxObject getAllRoleList(HttpServletRequest request){
    	List<OmUser> list = service.selectAll();
   		return AjaxObject.newOk("操作成功",list);
   	}
    
    /**
	 * 修改密码页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/changePassword" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String changePassword(HttpServletRequest request) {
		return USER_PATH+"changePassword";
	}
	
	//保存修改密码
    @RequestMapping(value = { "/saveChangePassword" })
   	@ResponseBody
   	public AjaxObject saveChangePassword(HttpServletRequest request, String password, String newPassword, String rPassword, String userId){
    	AjaxObject result = AjaxObject.newOk("success");
		try {
			Subject currentUser = SecurityUtils.getSubject();
			OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
			if (StringUtils.isNotBlank(userId)) {
				omUser= service.selectByPrimaryKey(userId);
			}
			//判断旧密码是否正确
			String oldPassword = Md5.getMD5ofStrByLowerCase(String.valueOf(password));
			if(!omUser.getPassword().equals(oldPassword)){
				return AjaxObject.newError("当前密码不正确！");
			}
			//更新密码
			omUser.setPassword(Md5.getMD5ofStrByLowerCase(String.valueOf(newPassword)));
			service.updateByPrimaryKeySelective(omUser);
		} catch (Exception e) {
			logger.error("保存修改用户密码信息："  , e);
			return AjaxObject.newError(e.getMessage());
		}
		return result;
   	}
    
    //重置密码
    @RequestMapping(value = { "/resetPassword" })
   	@ResponseBody
   	public AjaxObject resetPassword(HttpServletRequest request, String userId){
    	AjaxObject result = AjaxObject.newOk("success");
		try {
			if (StringUtils.isNotBlank(userId)) {
				OmUser omUser = service.selectByPrimaryKey(userId);
				//重置密码
				omUser.setPassword(Md5.getMD5ofStrByLowerCase(String.valueOf("123456")));
				service.updateByPrimaryKeySelective(omUser);
			}else{
				return AjaxObject.newError("参数错误！");
			}
		} catch (Exception e) {
			logger.error("重置用户密码信息："  , e);
			return AjaxObject.newError(e.getMessage());
		}
		return result;
   	}
    
    /**
	 * 个人中心页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/userInfo" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String userInfo(HttpServletRequest request) {
		Subject currentUser = SecurityUtils.getSubject();
		OmUser omUser = (OmUser) SecurityUtils.getSubject().getSession().getAttribute("om_user");
		request.setAttribute("bean", omUser);
		List<OmUserGroup> groupList =groupService.selectAll();
    	request.setAttribute("groupList", groupList);
		return USER_PATH+"userInfo";
	}
	
	//excel导入
	@RequestMapping(value = { "/importUser" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String toImportFile(HttpServletRequest request) {
		String userType = request.getParameter("userType");
		request.setAttribute("userType", userType);
	  	return USER_PATH+"importUser";
	}

    @RequestMapping("import")
    @ResponseBody
    public AjaxObject importFile(@RequestParam MultipartFile file,HttpServletRequest request, HttpServletResponse response) {
        AjaxObject result = AjaxObject.newOk("success");
        try {
        	// 判断文件名是否为空
    		if (file == null){
    			 return AjaxObject.newError("上传文件为空");
    		}
    		// 获取文件名
    		String name = file.getOriginalFilename();
    		// 判断文件大小、即名称
    		long size = file.getSize();
    		if (name == null || ("").equals(name) && size == 0){
    			return AjaxObject.newError("上传文件内容为空");
    		}
			String[] mappingFields = {"userId","unit","realName","userName","idType"};
    		List<OmUser> list = ExcelUtils.readSheet(OmUser.class, mappingFields, 0, 2, file.getInputStream());
    		if(list.size()==0){
    			return AjaxObject.newError("上传文件内容为空");
    		}
    		readExcel(request,file, result,list);
    		
        } catch(Exception e) {
            return AjaxObject.newError(e.getMessage());
        }
        return result;
    }

	private void readExcel(HttpServletRequest request, MultipartFile file, AjaxObject result,List<OmUser> list) throws Exception, IOException {
		String message = "";
		String userType = request.getParameter("userType");
		List<OmUser> addList = new ArrayList<OmUser>();
		for(int i=0;i<list.size();i++){
			OmUser bean=list.get(i);
			if(StringUtils.isBlank(bean.getUserName())){
				message +="第"+(i+2)+"行数据学号为空,忽略导入<br>";
				continue;
			}
			if(StringUtils.isBlank(bean.getRealName())){
				message +="第"+(i+2)+"行数据姓名为空,忽略导入<br>";
				continue;
			}
			OmUser oldUser = service.selectByUserName(bean.getUserName());
			if(oldUser!=null){
				message +="第"+(i+2)+"行数据["+bean.getUserName()+"]学号(用户名)已存在于库中,忽略导入<br>";
				continue;
			}
			OmUserGroup group= groupService.selectByGroupName(bean.getUnit());
			if(group==null){
				group = new OmUserGroup();
				group.setGroupId(UUIDUtil.getUUID());
				group.setGroupName(bean.getUnit());
				groupService.insert(group);
			}
			bean.setGroupId(group.getGroupId());
			bean.setGroupName(bean.getUnit());
			String idType = "";
			if("中士".equals(bean.getIdType())){
				idType = "1";
			}else if("列兵".equals(bean.getIdType())){
				idType = "2";
			}
			bean.setIdType(idType);
			bean.setUserType(userType);
			bean.setUserId(UUIDUtil.getUUID());
			bean.setStatus((byte)1);
			bean.setPassword(Md5.getMD5ofStrByLowerCase(String.valueOf("123456")));
			bean.setCreateTime(DateUtil.dateParse(new Date(),""));
			addList.add(bean);
			service.insert(bean);
			
			//分配角色
			String roleCode = "admin";
			if("1".equals(userType)) {//管理员
				roleCode = "admin";
			}else if("2".equals(userType)) {//教官 老师
				roleCode = "teacher_role";
			}else if("3".equals(userType)) {//学员
				roleCode = "student_role";
			}
			//根据角色编码获取角色id
			OmRole roleBean = roleService.selectByRoleCode(roleCode);
			if(roleBean==null) {
				List<OmRole> roleList = roleService.selectAll();
				if(roleList.size()>0) {
					roleBean = roleList.get(0);
				}
			}
			if(roleBean!=null) {
				userRoleService.saveUserRole(bean.getUserId(),roleBean.getRoleId());
			}
		}
		result.setMessage("成功导入：" +addList.size()+"条数据。<br>" +message);
	}

	/**
	 * 模板下载
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/downloadTemplate")
   	@SuppressWarnings("deprecation")
   	@ResponseBody
   	public void downloadTemplate(HttpServletRequest request,HttpServletResponse response)throws Exception {
		String filePath = "/template/学员信息模板.xls";
		String fileName = "学员信息模板.xls";
		InputStream in = null;  
        try {  
          in =  this.getClass().getResourceAsStream(filePath);  
          DownloadUtils.downloadExcel(response,in,fileName);
        } catch (FileNotFoundException e) {  
        }  
    }

}
