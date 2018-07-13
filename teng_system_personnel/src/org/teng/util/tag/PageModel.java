package org.teng.util.tag;

import org.teng.util.common.tengConstants;
/**简化分页功能，设计分页的jsp标签
 * @author 滕乾安tengqianan@sina.com
 * @date 2018-1-18
 * @qq 1730451344
 * */
public class PageModel {

	private int recordCount;//分页总数据条数
	private int pageIndex;//当前页面
	private int pageSize=tengConstants.PAGE_DEFAULT_SIZE=4;//没有分多少条数据
	private int totalSize;//总页数
	public int getRecordCount() {
		this.recordCount = this.recordCount <= 0?0:this.recordCount;
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getPageIndex() {
		this.pageIndex = this.pageIndex <= 0?1:this.pageIndex;
		//判断当前页面是否超过总页数：如果超过默认将最后页面作为当前页
		this.pageIndex = this.pageIndex >= this.getTotalSize()?this.getTotalSize():this.pageIndex;
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		this.pageSize = this.pageSize <= tengConstants.PAGE_DEFAULT_SIZE?tengConstants.PAGE_DEFAULT_SIZE:this.pageSize;
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalSize() {
		if(this.getRecordCount() <= 0 ) {
			totalSize = 0;
		}else {
			totalSize = (this.getRecordCount()-1)/this.getRecordCount()+1;
		}
		return totalSize;
	}
	public int getFirstLimitParam() {
		return (this.getPageIndex()-1)*this.getPageSize();
	}
	
}
