package com.docker.controller;

import com.docker.annotation.Login;
import com.docker.entity.FileInfo;
import com.docker.service.FileService;
import com.docker.tasks.FileTree;
import com.docker.util.FileIOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * madkdown编辑器控制器
 * Created by CHEN on 2019/12/8.
 */

@Slf4j
@CrossOrigin
@Controller
public class MarkdownController {

    @Autowired
    FileService fileService;

    @Login
    @GetMapping("/mark/read")
    public String readonly(HashMap<String, Object> map, @RequestParam(value = "p", required = true) String p) {
        map = fileService.getContextString(map, p);
        return "mark/readonly.html";
    }


    @Login
    @GetMapping("/mark/edit")
    public String edit(HashMap<String, Object> map, @RequestParam(value = "p", required = true) String p) {
        map = fileService.getContextString(map, p);
        return "mark/edit.html";
    }

    @Login
    @ResponseBody
    @PostMapping("/mark/save")
    public boolean save(@RequestParam(value = "title", required = false) String title,
                        @RequestParam(value = "path", required = false) String path,
                        @RequestParam(value = "type", required = false) String type,
                        @RequestParam(value = "updateTime", required = false) long updateTime,
                        @RequestParam(value = "content", required = false) String content) {
        return fileService.saveFile(path,updateTime,content);
    }
}
