package com.springpos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springpos.bean.ArticleAuthor;
import com.springpos.service.MainService;
import java.util.ArrayList;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.springpos.service.ArticleAuthorService;

@Controller
public class ArticleAuthorController {

    private ArticleAuthorService articleAuthorService;

    private MainService mainService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleAuthorController.class);

    ArrayList<ArticleAuthor> articleAuthorList = new ArrayList();

    @Autowired
    public void setArticleAuthorService(ArticleAuthorService articleAuthorService) {
        this.articleAuthorService = articleAuthorService;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("articleAuthor/new")
    public String articleAuthorPage(Model model) {
        if (mainService.getLoggedIn() == null) {
            return "redirect:/";
        }
        model.addAttribute("articleAuthor", new ArticleAuthor());
        mainService.setInstitution(model);
        return "articleAuthor";
    }

    @GetMapping("/articleAuthors")
    public ModelAndView showCategories(ArticleAuthor articleAuthor) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("articleAuthors");
        if (mainService.getLoggedIn() == null) {
            mv.setViewName("redirect:/");
        } else {
            mv.addObject("articleAuthor", new ArticleAuthor());
            mainService.setInstitution(mv);
            articleAuthorList.clear();
            articleAuthorList = (ArrayList<ArticleAuthor>) this.articleAuthorService.findAll();
            mv.addObject("articleAuthors", this.articleAuthorService.findAll());
        }
        return mv;
    }

    @PostMapping(value = "articleAuthor")
    public String save(@Valid ArticleAuthor articleAuthor, BindingResult result, Model model) {
        mainService.setInstitution(model);
        if (result.hasErrors()) {
            model.addAttribute("addMessage", result.toString());

            model.addAttribute("articleAuthor", new ArticleAuthor());
            return "articleAuthor";
        }
        articleAuthorService.save(articleAuthor);
        model.addAttribute("articleAuthor", new ArticleAuthor());
        model.addAttribute("addMessage", " ArticleAuthor Added Successfull ");
        return "articleAuthor";

    }

    @RequestMapping("/updateArticleAuthor/{id}")
    public String updateArticleAuthor(@PathVariable("id") int id, @Valid ArticleAuthor articleAuthor,
            BindingResult result, Model model) {
        mainService.setInstitution(model);
        model.addAttribute("articleAuthor", articleAuthor);
        this.articleAuthorService.update(articleAuthor);
        model.addAttribute("addMessage", "Update Successful !");
        model.addAttribute("articleAuthors", this.articleAuthorService.findAll());
        return "articleAuthors";
    }

    @GetMapping("/removeArticleAuthor/{id}")
    public String deleteArticleAuthor(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        ArticleAuthor articleAuthor = this.articleAuthorService.find(id);
        if (articleAuthor == null) {
            model.addAttribute("addMessage", "Invalid articleAuthor Id:" + id);
            model.addAttribute("articleAuthors", this.articleAuthorService.findAll());
            return "articleAuthors";
        }
        this.articleAuthorService.delete(articleAuthor);
        model.addAttribute("addMessage", "Deleted Successfully ! ");
        model.addAttribute("articleAuthors", this.articleAuthorService.findAll());
        return "articleAuthors";
    }

    @GetMapping("/editArticleAuthor/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        mainService.setInstitution(model);
        ArticleAuthor articleAuthor = this.articleAuthorService.find(id);
        if (articleAuthor == null) {
            model.addAttribute("addMessage", "Invalid articleAuthor Id:" + id);
            model.addAttribute("articleAuthors", this.articleAuthorService.findAll());
            return "articleAuthors";
        }
        model.addAttribute("articleAuthor", articleAuthor);
        return "updateArticleAuthor";
    }

 

}
