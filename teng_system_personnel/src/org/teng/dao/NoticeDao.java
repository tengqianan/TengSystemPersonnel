package org.teng.dao;
import static org.teng.util.common.tengConstants.NOTICETABLE;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.FetchType;
import org.teng.dao.provider.NoticeDynaSqlProvider;
import org.teng.domain.Notice;


/**
 * @author 滕乾安tengqianan@sina.com
 * @date 2018-01-16
 * @qq 1730451344
 * */
public interface NoticeDao {

	//动态查询
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="selectWhitParam")
	@Results({@Result(id=true,column="id",property="id"),
		@Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
		@Result(column="USER_ID",property="user",
		one=@One(select="org.teng.dao.UserDao.selectById",fetchType=FetchType.EAGER))})
	List<Notice> selectByPage(Map<String,Object> param);
	
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object> params);
	
	@Select("select * from "+NOTICETABLE+" where id=#{id}")
	Notice selectById(int id);
	
	//根据ID删除公告
	@Delete("delete from "+NOTICETABLE+" where id=#{id}")
	void deleteById(Integer id);
	
	//动态插入公告
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="insertNotice")
	void save(Notice notice);
	
	//动态修改公告
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="updateNotice")
	void update(Notice notice);
}
