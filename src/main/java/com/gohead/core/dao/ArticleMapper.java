package com.gohead.core.dao;

import com.gohead.core.entity.Article;

import java.util.List;
import java.util.Map;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    /**
     * 返回相应的数据集合
     *
     * @param map
     * @return
     */
    public List<Article> findArticles(Map<String, Object> map);

    /**
     * 数据数目
     *
     * @param map
     * @return
     */
    public int getTotalArticles(Map<String, Object> map);

    /**
     * 添加文章
     *
     * @return
     */
    public int insertArticle(Article article);

    /**
     * 修改文章
     *
     * @return
     */
    public int updArticle(Article article);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public int delArticle(String id);

    /**
     * 根据id查找
     *
     * @param id
     * @return
     */
    public Article getArticleById(String id);
}