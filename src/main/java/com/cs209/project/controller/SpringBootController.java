package com.cs209.project.controller;

import com.cs209.project.entity.IssueWord;
import com.cs209.project.entity.SpringBootIteration;
import com.cs209.project.entity.SpringBootQuestion;
import com.cs209.project.service.ISpringBootService;
import com.cs209.project.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("springboot")
public class SpringBootController extends BaseController {
    @Autowired
    private ISpringBootService iSpringBootService;

    @RequestMapping("springboot-version")
    public JsonResult<List<SpringBootIteration>> getVersion() {
        List<SpringBootIteration> list = iSpringBootService.selectVersion();
        return new JsonResult<>(ok, list);
    }

    @RequestMapping("springboot-question")
    public JsonResult<List<SpringBootQuestion>> getQuestion(String sorting, String key, String page) {
        List<SpringBootQuestion> list = iSpringBootService.selectSpringBootQuestion(sorting, key, page);
        if (list == null) return new JsonResult<>(220);
        else if (list.isEmpty()) return new JsonResult<>(220);
        return new JsonResult<>(ok, list);
    }

    @RequestMapping("springboot-select")
    public JsonResult<List<SpringBootQuestion>> getAllQuestion() {
        List<SpringBootQuestion> list = iSpringBootService.selectAllSpringBootQuestion();
        return new JsonResult<>(ok, list);
    }

    @RequestMapping("issue-NLP")
    public JsonResult<List<IssueWord>> getIssueWord() {
        List<IssueWord> list = iSpringBootService.selectIssueWord();
        return new JsonResult<>(ok, list);
    }
}
