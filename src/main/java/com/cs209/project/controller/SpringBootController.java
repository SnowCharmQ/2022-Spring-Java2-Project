package com.cs209.project.controller;

import com.cs209.project.entity.SpringBootIteration;
import com.cs209.project.service.ISpringBootService;
import com.cs209.project.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("springboot")
public class SpringBootController extends BaseController{
    @Autowired
    private ISpringBootService iSpringBootService;

    @RequestMapping("version")
    public JsonResult<List<SpringBootIteration>> getVersion(){
        List<SpringBootIteration> list = iSpringBootService.selectVersion();
        return new JsonResult<>(ok, list);
    }
}