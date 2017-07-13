package blog.geek.entity;

import java.util.List;

/**
 * 分页
 * @author yuanyang
 * @version 1.0
 */
public class Pager<T> {

    private int pageSize = 10;    //每一页的大小,默认为10
    private int pageIndex = 1;   //当前所在页面的位置,默认位置为第1页
    private int totalCount;   //一共有多少条记录
    private int totalPages; //共页数
    private List<T> result; //得到的结果

    public Pager() {
    }

    public Pager(int pageIndex, int pageSize, int totalCount) {
        this.pageIndex = pageIndex; //当前页
        this.pageSize = pageSize;   //页面显示数量
        this.totalCount = totalCount;//总条数
        totalPages = totalCount / pageSize + 1; //页面总数
    }

    //start getter and setter
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    //end getter and setter

    /**
     * 计算数据的第一条记录在数据表中的位置,序号从0开始
     * @return
     */
    public int getOffSet(){
        return (pageIndex - 1) * pageSize;
    }
}

