package com.ug369.backend.outerapi.security;

//import com.baidu.ueditor.ActionEnter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ug369.backend.bean.base.request.WebUser;
import com.ug369.backend.bean.exception.UserException;
import com.ug369.backend.outerapi.annotation.MemoryCache;
import com.ug369.backend.service.entity.mysql.User;
import com.ug369.backend.service.entity.mysql.UserRole;
import com.ug369.backend.service.service.RoleService;
import com.ug369.backend.service.service.UserService;
import com.ug369.backend.utils.TokenUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
	TokenUtils tokenUtils;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	ObjectMapper objectMapper;

	@Value("${ugms.static.file.path}")
	private String filePath;
	@Autowired
	MemoryCache memoryCache;

    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filter)
            throws AuthenticationException, IOException, ServletException {
		String token = request.getHeader("token");
		if (!request.getRequestURI().equals("/token")){
			if (StringUtils.isEmpty(token)){
				String cookiestr = request.getHeader("cookie");
				if(!StringUtils.isEmpty(cookiestr)){
					String[] cookies = cookiestr.split(";");
					for (String cookie : cookies){
						String[] pair = cookie.split("=");
						if (pair[0].trim().equals("token")){
							token = pair[1];
						}
					}
				}
			}
		}


//		if(request.getRequestURI().contains("controller")){
//
//			request.setCharacterEncoding( "utf-8" );
//			response.setHeader("Content-Type" , "text/html");
//			String action = request.getParameter("action");
//			//本地调试和服务调试切换
//			String rootPath = filePath;
////			String rootPath = "/E:/shanghaihuisheng/ug-mgmt/outer-api/target/classes";
//			System.out.println(rootPath);
//			String result = new ActionEnter( request, rootPath ).exec();
//
//			if( action!=null &&
//					(action.equals("listfile") || action.equals("listimage") ) ){
//				rootPath = rootPath.replace("\\", "/");
//				result = result.replaceAll(rootPath, "");
//			}
//			if (action.equals("uploadimage")){
//				memoryCache.put(token,result);
//			}
//			PrintWriter writer = response.getWriter();
//			writer.write( result );
//			System.out.println(result);
//			return;
//		}

//		if(request.getRequestURI().contains("uploadimage/result")){
//			request.setCharacterEncoding( "utf-8" );
//			response.setHeader("Content-Type" , "text/html");
//			String result = upload.get(request.getParameter("token"));
//			PrintWriter writer = response.getWriter();
//			writer.write( result );
//			return;
//		}

	    if (!request.getRequestURI().equals("/token")){

			if (!StringUtils.isEmpty(token) ) {
				try {
					Long userId = tokenUtils.validate(token);
					User user = userService.findById(userId);
					if (user != null) {
						UserRole role = userService.getRoleByUser(userId);
						if (role !=null && role.getRole()!=0) {
							WebUser webUser = new WebUser();
							webUser.setId(userId);
							webUser.setMobile(user.getMobile());
							webUser.setName(user.getName());
							webUser.setRole(role.getRole());
							webUser.setUsername(user.getUsername());
							webUser.setEmail(user.getEmail());
							webUser.setRolename(roleService.findById(role.getRole()).getName());
							webUser.setDepartment(user.getDepartment());
							SecurityContextHolder.getContext().setAuthentication(
									new UsernamePasswordAuthenticationToken(webUser, webUser, AuthorityUtils
											.createAuthorityList("ROLE_" + role.getId())));
						}
					}

				} catch (NumberFormatException | UserException e) {
					e.printStackTrace();
				}
			}else {

//				if (!request.getMethod().equals("OPTIONS")){
//					response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//					PrintWriter writer = response.getWriter();
//					writer.print(objectMapper
//							.writeValueAsString(new BasicResponse(UgmsStatus.AUTH_FAILED, "没有认证信息")));
//					writer.flush();
//					writer.close();
//					return;
//				}
			}
		}
		filter.doFilter(request, response);

    }
}
