package com.gohead.core.admin;

import com.gohead.core.common.Constants;
import com.gohead.core.common.CountResult;
import com.gohead.core.common.Result;
import com.gohead.core.common.ResultGenerator;
import com.gohead.core.entity.Article;
import com.gohead.core.entity.PageBean;
import com.gohead.core.service.ArticleService;
import com.gohead.core.util.DateUtil;
import com.gohead.core.util.ResponseUtil;
import com.gohead.core.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/articles")
public class ArticleController {
	@Resource
	private ArticleService articleService;
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ArticleController.class);// 日志文件

	/**
	 * 查找相应的数据集合
	 *
	 * @param page
	 * @param pageSize
	 * @param article
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/query/page/{page}/pageSize/{pageSize}", method = RequestMethod.GET)
	@ResponseBody
	public CountResult list(
			@PathVariable("page") String page,
			@PathVariable("pageSize") String pageSize,
			Article article, HttpServletResponse response, HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (page != null && pageSize != null) {
			PageBean pageBean = new PageBean(Integer.parseInt(page),
					Integer.parseInt(pageSize));
			map.put("start", pageBean.getStart());
			map.put("size", pageBean.getPageSize());
			map.put("userId", request.getHeader("userId"));
		}
		if (article != null) {
			map.put("articleTitle",
					StringUtil.formatLike(article.getArticleTitle()));
		}
		List<Article> articleList = articleService.findArticle(map);
		Integer total = articleService.getTotalArticle(map);
		return ResultGenerator.getSuccessResult(articleList, Integer.parseInt(page), total);
	}

	/**
	 * 查找相应的数据集合
	 *
	 * @param page
	 * @param pageSize
	 * @param article
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public Result list(
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "pageSize", required = false) String pageSize,
			Article article) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (page != null && pageSize != null) {
			PageBean pageBean = new PageBean(Integer.parseInt(page),
					Integer.parseInt(pageSize));
			map.put("start", pageBean.getStart());
			map.put("size", pageBean.getPageSize());
		}
		if (article != null) {
			map.put("articleTitle",
					StringUtil.formatLike(article.getArticleTitle()));
		}
		List<Article> articleList = articleService.findArticle(map);
		Integer total = articleService.getTotalArticle(map);

		Result result = ResultGenerator.genSuccessResult();
		Map data = new HashMap();
		data.put("rows", articleList);
		data.put("total", total);
		result.setData(data);
		return result;
	}

	/**
	 * 添加
	 *
	 * @param article
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Result save(@RequestBody Article article, HttpServletRequest request)
			throws Exception {
		int resultTotal = 0;

		int userId = Integer.valueOf(request.getHeader("userId"));
		article.setUserId(userId);
		article.setArticleCreateDate(DateUtil.getCurrentDateStr());
		article.setTop(0);

		resultTotal = articleService.addArticle(article);

		log.info("request: article/save , " + article.toString());

		if (resultTotal > 0) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("添加失败");
		}
	}

	/**
	 * 修改
	 *
	 * @param article
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	@ResponseBody
	public Result update(@RequestBody Article article)
			throws Exception {
		int resultTotal = 0;
		resultTotal = articleService.updateArticle(article);

		log.info("request: article/update , " + article.toString());

		if (resultTotal > 0) {
			return ResultGenerator.genSuccessResult();
		} else {
			return ResultGenerator.genFailResult("修改失败");
		}
	}

	/**
	 * 删除
	 *
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	@ResponseBody
	public Result delete(@RequestBody Article article) throws Exception {
		int userId = article.getId();
		if (userId > 0) {
			articleService.deleteArticle(String.valueOf(userId));
		} else {
			return ResultGenerator.genFailResult(Constants.DEFAULT_ERROR_MESSAGE);
		}

		return ResultGenerator.genSuccessResult();
	}

	/**
	 * 根据id查找
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Result findById(@PathVariable("id") String id) throws Exception {
		Article article = articleService.findById(id);
		Result result = ResultGenerator.genSuccessResult();
		result.setData(article);
		log.info("request: article/findById");
		return result;
	}
}