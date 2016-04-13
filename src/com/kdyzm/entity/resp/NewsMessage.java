package com.kdyzm.entity.resp;

import java.util.List;

import com.kdyzm.entity.resp.base.BaseResponseMessage;
/**
 * 图文消息
 * @author QCZhengrjA1
 *
 */
public class NewsMessage extends BaseResponseMessage{
	// 图文消息个数，限制为10条以内  
    private int ArticleCount;  
    // 多条图文消息信息，默认第一个item为大图  
    private List<Article> Articles;
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<Article> getArticles() {
		return Articles;
	}
	public void setArticles(List<Article> articles) {
		Articles = articles;
	}  
}
