package cn.chujiang.commons.utils;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.chujiang.commons.model.result.Result;


public class ResponseUtils {
	 private ResponseUtils() {
	    }

	    public static void responseJsonWriter(HttpServletResponse response, Result result) throws IOException {
	        response.setStatus(HttpServletResponse.SC_OK);
	        response.setCharacterEncoding("utf-8");
	        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
	        ObjectMapper objectMapper = new ObjectMapper();
	        String resBody = objectMapper.writeValueAsString(result);
	        ServletOutputStream printWriter = response.getOutputStream();
	        printWriter.print(resBody);
	        printWriter.flush();
	        printWriter.close();
	    }
}
