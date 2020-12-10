package com.tangyu.springboottraining.controller;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Objects;

@Controller
//@RestController
public class FileDownloadController implements ApplicationContextAware {

    ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

    @RequestMapping("/download")
    public String testDownload(HttpServletResponse response, HttpServletRequest request) {

        //待下载文件名
        String fileName = "mingw-get-setup.exe";
        //设置为png格式的文件
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        byte[] buff = new byte[1024];
        Resource resource = null;
        try {

            //获取资源文件
            resource = applicationContext.getResource("classpath:" + fileName);
        } catch (Exception e) {
            //自定义业务异常
//            throw new BusinessException(201, "导出模板失败");
            e.printStackTrace();
        }


        if (Objects.nonNull(resource)) {
            try (InputStream inputStream = resource.getInputStream();
                 ServletOutputStream servletOutputStream = response.getOutputStream()) {
                //把资源文件的二进制流数据copy到response的输出流中
                IOUtils.copy(inputStream, servletOutputStream);
                //清除flush所有的缓冲区中已设置的响应信息至客户端
                response.flushBuffer();
            } catch (Exception e) {
                //错误日志记录
//                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }

//        //创建缓冲输入流
//        BufferedInputStream bis = null;
//        OutputStream outputStream = null;
//        try {
//            outputStream = response.getOutputStream();
//
//            File file = new File(fileName);
//            //这个路径为待下载文件的路径
//            bis = new BufferedInputStream(new FileInputStream(file));
//            response.setContentLengthLong(file.length());
//            int read = bis.read(buff);
//
//            //通过while循环写入到指定了的文件夹中
//            while (read != -1) {
//                outputStream.write(buff, 0, buff.length);
//                outputStream.flush();
//                read = bis.read(buff);
//            }
////            request.getRequestDispatcher("/home").forward(request, response);
////            response.sendRedirect();
//        } catch (IOException e) {
//            e.printStackTrace();
//            //出现异常返回给页面失败的信息
////            model.addObject("result", "下载失败");
//        } finally {
//            if (bis != null) {
//                try {
//                    bis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (outputStream != null) {
//                try {
//                    outputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }

        //成功后返回成功信息
//        model.addObject("result", "下载成功");
        return null;
    }
}
