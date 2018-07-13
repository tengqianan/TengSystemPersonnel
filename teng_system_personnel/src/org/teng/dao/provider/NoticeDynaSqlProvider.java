package org.teng.dao.provider;
import static org.teng.util.common.tengConstants.NOTICETABLE;

import java.util.*;

import org.apache.ibatis.jdbc.SQL;
import org.teng.domain.Notice;
/**
 * @author 滕乾安tengqianan@sina.com
 * @date 2018-01-16
 * @qq 1730451344
 * */
public class NoticeDynaSqlProvider {

	//分页动态查询
	public String selectWhitParam(Map<String,Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(NOTICETABLE);
				if(params.get("notice")!=null) {
					Notice notice = (Notice)params.get("notice");
					if(notice.getTitle()!=null && !notice.getTitle().equals("")) {
						WHERE("title LIKE CONCAT ('%',#{notice.title},'%')");
					}
					if(notice.getContent()!=null && !notice.getContent().equals("")) {
						WHERE("content LIKE CONCAT ('%',#{notice.content},'%')");
					}
				}
			}
		}.toString();
		if(params.get("pageModel")!=null) {
			sql += "limit #{pageModel.firstLimitPage},#{pageModel.pageSize}";
		}
		return sql;
	}
	//动态查询总数
	public String count(Map<String,Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(NOTICETABLE);
				if(params.get("notice")!=null) {
					Notice notice = (Notice)params.get("notice");
					if(notice.getTitle()!=null && !notice.getTitle().equals("")) {
						WHERE("title LIKE CONCAT ('%',#{notice.title},'%')");
					}
					if(notice.getContent()!=null && !notice.getContent().equals("")) {
						WHERE("content LIKE CONCAT ('%',#{notice.content},'%')");
					}
				}
			}
		}.toString();
	}
	//动态插入
	public String insertNotice(Notice notice) {
		return new SQL() {
			{
				INSERT_INTO(NOTICETABLE);
				if(notice.getTitle()!=null && !notice.getTitle().equals("")) {
					VALUES("title","#{title}");
				}
				if(notice.getContent()!=null && !notice.getContent().equals("")) {
					VALUES("content","#{content}");
				}
				if(notice.getUser()!=null && !notice.getUser().equals("")) {
					VALUES("user_id","#{user.id}");
				}
			}
			
		}.toString();
	}
	//动态更新
	public String updateNotice(Notice notice) {
		return new SQL() {
			{
				UPDATE(NOTICETABLE);
				if(notice.getTitle()!=null && !notice.getTitle().equals("")) {
					SET("title=#{title}");
				}
				if(notice.getContent()!=null && !notice.getContent().equals("")) {
					SET("content=#{content}");
				}
				if(notice.getUser()!=null && !notice.getUser().equals("")) {
					SET("user_id=#{user.id}");
				}
				WHERE("id = #{id}");
			}
		}.toString();
	}
}
