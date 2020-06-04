package cn.chujiang.security.common.faild;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import cn.chujiang.commons.model.result.Result;
import cn.chujiang.commons.utils.ResponseUtils;

/**
 * 认证失败处理器
 * @author zhejun
 *
 */
@Component
public class SimpleAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		Result result = new Result();
		result.setResp_code(HttpStatus.BAD_REQUEST.value());
		result.setResp_msg(authException.getMessage());
		ResponseUtils.responseJsonWriter(response, result);
	}

}
