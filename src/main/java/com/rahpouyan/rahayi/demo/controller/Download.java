package com.rahpouyan.rahayi.demo.controller;

import com.rahpouyan.rahayi.demo.Information;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;

@Controller
@CrossOrigin(origins = Information.frontAddress)
public class Download {

    @Value("${upload.address.image}")
    private String imageAddress;

    @Value("${upload.address.voice}")
    private String voiceAddress;

    @RequestMapping("/image/{fileName}")
    public void downloadImage(HttpServletResponse response, HttpServletRequest request,
                              @PathVariable("fileName") String fileName) throws IOException {

        File file = new File(imageAddress + "/" + fileName);
        if (file.exists()) {


            InputStream fis = new FileInputStream(file);

            response.setHeader("Accept-Ranges", "bytes");
            long length = (int) new File(file.toString()).length();
            long start = 0;

            if (request.getHeader("Range") != null) {
                response.setStatus(javax.servlet.http.HttpServletResponse.SC_PARTIAL_CONTENT);// 206
                start = Long.parseLong(request.getHeader("Range")
                        .replaceAll("bytes=", "").replaceAll("-", ""));
            }

            response.setHeader("Content-Length", new Long(length - start).toString());
            if (start != 0) {
                response.setHeader("Content-Range", "bytes "
                        + new Long(start).toString() + "-"
                        + new Long(length - 1).toString() + "/"
                        + new Long(length).toString());
            }

            response.setContentType("application/octet-stream");

            fis.skip(start);
            byte[] b = new byte[1024];
            int i;
            while ((i = fis.read(b)) != -1) {
                response.getOutputStream().write(b, 0, i);
                response.flushBuffer();
            }
            fis.close();

        }
    }


    @RequestMapping("/voice/{fileName}")
    public void downloadVoice(HttpServletResponse response, HttpServletRequest request,
                              @PathVariable("fileName") String fileName) throws IOException {

        File file = new File(voiceAddress + "/" + fileName);
        if (file.exists()) {


            InputStream fis = new FileInputStream(file);

            response.setHeader("Accept-Ranges", "bytes");
            long length = (int) new File(file.toString()).length();
            long start = 0;

            if (request.getHeader("Range") != null) {
                response.setStatus(javax.servlet.http.HttpServletResponse.SC_PARTIAL_CONTENT);// 206
                start = Long.parseLong(request.getHeader("Range")
                        .replaceAll("bytes=", "").replaceAll("-", ""));
            }

            response.setHeader("Content-Length", new Long(length - start).toString());
            if (start != 0) {
                response.setHeader("Content-Range", "bytes "
                        + new Long(start).toString() + "-"
                        + new Long(length - 1).toString() + "/"
                        + new Long(length).toString());
            }

            response.setContentType("application/octet-stream");

            fis.skip(start);
            byte[] b = new byte[1024];
            int i;
            while ((i = fis.read(b)) != -1) {
                response.getOutputStream().write(b, 0, i);
                response.flushBuffer();
            }
            fis.close();

        }
    }
}
