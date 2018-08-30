package com.gohead.core.admin;

import com.gohead.core.common.Constants;
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
	@RequestMapping(value = "/datagrid", method = RequestMethod.POST)
	public Result list(
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "pageSize", required = false) String pageSize,
			Article article, HttpServletResponse response) throws Exception {
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
		JSONArray jsonArray = JSONArray.fromObject(articleList);
		return ResultGenerator.getSuccessResult(jsonArray, Integer.parseInt(page), total);
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
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Result save(@RequestBody Article article)
			throws Exception {
		int resultTotal = 0;

		article.setArticleCreateDate(DateUtil.getCurrentDateStr());

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
	@RequestMapping(value = "", method = RequestMethod.PUT)
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
	@RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
	@ResponseBody
	public Result delete(@PathVariable("ids") String ids) throws Exception {
		if (ids.length() > 20) {
			return ResultGenerator.genFailResult(Constants.DEFAULT_ERROR_MESSAGE);
		}
		String[] idsStr = ids.split(",");
		for (int i = 0; i < idsStr.length; i++) {
			articleService.deleteArticle(idsStr[i]);
		}

		log.info("request: article/delete , ids: " + ids);
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