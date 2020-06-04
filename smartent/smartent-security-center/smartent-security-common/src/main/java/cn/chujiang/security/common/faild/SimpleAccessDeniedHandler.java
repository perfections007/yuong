package cn.chujiang.security.common.faild;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import cn.chujiang.commons.model.result.Result;
import cn.chujiang.commons.utils.ResponseUtils;

/**
 * 授权失败处理器
 * @author zhejun
 *
 */
@Component
public class SimpleAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
			 Result result  =new Result();
			 result.setDatas(request.getRequestURI());
			 result.setResp_code(401);
			 result.setResp_msg(accessDeniedException.getMessage());
	        ResponseUtils.responseJsonWriter(response, result);

	}

}
