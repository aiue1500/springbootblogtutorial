package com.example.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.Article;
import com.example.model.ArticleListForm;
import com.example.service.ArticleService;

@Controller
@RequestMapping("/article")
public class ArticleController {

  @Autowired
  ArticleService service;
  
  @Autowired
  MessageSource msg;

  // index　記事一覧画面
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView index(@ModelAttribute("searchFormModel") @Valid ArticleListForm articleListForm, BindingResult result,ModelAndView mav,Locale locale) {
    //検索機能のバリデーションチェック
    if(result.hasErrors()){
      mav = new ModelAndView();
      mav.addObject(articleListForm);
      mav.setViewName("article/index");
      return mav;
    }
    //データをセットし、表示させる
    List<Article> datalist = service.findArticles(articleListForm);
    mav.setViewName("article/index");
    mav.addObject("searchFormModel",articleListForm);
    mav.addObject("datalist", datalist);
    //検索した場合、検索結果アラートを表示する　検索していない場合、ここはスキップされる
    if(!articleListForm.isEmpty()){
      if(datalist.size() == 0){
        mav.addObject("search_result_alert",msg.getMessage("article.index.search_result.nothing", null,locale));
      }else{
        mav.addObject("search_result_alert",msg.getMessage("article.index.search_result.being",new String[]{String.valueOf(datalist.size())},locale)); 
      }
    }
    return mav;
  }

  // create
  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public ModelAndView create(@ModelAttribute("formModel") Article article, ModelAndView mav) {
    mav.setViewName("article/edit");
    mav.addObject("title", "新規作成");
    //ユーザIDを設定
    article.setUser_id(1);
    mav.addObject("formModel", article);
    return mav;
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public ModelAndView post_create(ModelAndView mav, @ModelAttribute("formModel") @Valid Article article,
      BindingResult result) {
    // バリデーションチェック
    if (result.hasErrors()) {
      // 入力情報を保持しつつ、作成画面に戻す
      mav = new ModelAndView();
      mav.addObject(article);
      mav.setViewName("article/edit");
      return mav;
    }

    // 日付の設定をしてデータベースに追加登録
    article.setCreated_at(new Date());
    article.setUpdated_at(new Date());
    service.create(article);
    return new ModelAndView("redirect:/article/");
  }

  // read
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ModelAndView show(@PathVariable("id") Integer id, ModelAndView mav) {
    mav.setViewName("/article/show");
    Article data = service.findOne(id);
    mav.addObject("data", data);
    return mav;
  }

  // update
  @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
  public ModelAndView edit(@PathVariable("id") Integer id, ModelAndView mav) {
    mav.setViewName("/article/edit");
    Article article = service.findOne(id);
    mav.addObject("title","記事編集");
    mav.addObject("formModel", article);
    return mav;
  }

  @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
  public ModelAndView post_edit(@ModelAttribute("formModel") Article article, BindingResult result, ModelAndView mav) {
    // 更新日付の更新
    article.setCreated_at(service.findOne(article.getId()).getCreated_at());
    article.setUpdated_at(new Date());
    service.update(article);
    return new ModelAndView("redirect:/article/" + article.getId());
  }

  // delete
  @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
  public ModelAndView delete(@PathVariable Integer id, RedirectAttributes attributes, ModelAndView mav) {
    service.delete(id);
    attributes.addFlashAttribute("deleteMessage", "delete ID:" + id);
    return new ModelAndView("redirect:/article/");
  }

}
