package org.teng.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.teng.dao.provider.JobDynaSqlProvider;
import org.teng.domain.Job;
import static org.teng.util.common.tengConstants.JOBTABLE;

import java.util.*;
/**
 * @author 滕乾安tengqianan@sina.com
 * @date 2018-01-16
 * @qq 1730451344
 * */
public interface JobDao {

	@Select("select * from "+JOBTABLE+" where ID = #{id}")
	Job selectById(int id);
	
	@Select("select * from "+JOBTABLE+"")
	List<Job> selectAllJob();
	
	//动态查询
	@SelectProvider(type = JobDynaSqlProvider.class,method = "selectWhitParam")
	List<Job> selectByPage(Map<String,Object> params);
	
	@SelectProvider(type = JobDynaSqlProvider.class,method = "count")
	Integer count(Map<String,Object> params);
	
	//根据ID删除部门
	@Delete("delete from "+JOBTABLE+" where id = #{id}")
	void deleteById(Integer id);
	
	//动态插入部门
	@SelectProvider(type = JobDynaSqlProvider.class,method = "insertJob")
	void save(Job job);
	
	//动态修改用户
	@SelectProvider(type = JobDynaSqlProvider.class,method = "updateJob")
	void update(Job job);
}