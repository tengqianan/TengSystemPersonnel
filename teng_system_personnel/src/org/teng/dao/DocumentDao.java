package org.teng.dao;

import java.util.*;
import static org.teng.util.common.tengConstants.DOCUMENTTABLE;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.teng.dao.provider.DocumentDynaSqlProvider;
import org.teng.domain.Document;

/**
 * @author 滕乾安tengqianan@sina.com
 * @date 2018-1-17
 * @qq 1730451344
 * */
public interface DocumentDao {

	//动态查询
	@SelectProvider(type = DocumentDynaSqlProvider.class,method= "selectWhitParam")
	@Results({
			@Result(id=true,column="id",property="id"),
			@Result(column="CREATE_DATE",property="createDate",javaType=java.util.Date.class),
			@Result(column="USER_ID",property="user",one=@One(select="org.teng.dao.UserDao.selectById",fetchType=FetchType.EAGER))
	})
	List<Document> selectByPage(Map<String,Object> params);
	@SelectProvider(type=DocumentDynaSqlProvider.class,method="count")
	Integer count(Map<String,Object>params);
	
	//动态插入
	@SelectProvider(type=DocumentDynaSqlProvider.class,method="insertDocument")
	void save(Document document);
	
	@Select("select * from"+DOCUMENTTABLE+" where ID=#{id}")
	Document selectById(int id);
	
	@Delete("delete * from"+DOCUMENTTABLE+"where id=#{id}")
	void deleteById(Integer id);
	
	//动态修改文档
	@SelectProvider(type=DocumentDynaSqlProvider.class,method="updateDocument")
	void update(Document document);
}
