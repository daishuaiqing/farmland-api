package com.daishuaiqing.farmland.query;

public class PageParam {

    private Integer page = 1;
    private Integer size = 20;
    private Integer start;

    public PageParam(){

    }

    public PageParam(Integer page){
        this.page=page;
    }

    public PageParam(Integer page, Integer pageSize){
        this.page=page;
        this.size=pageSize;
    }

    public Integer getStart() {
        return (page-1)*size;
    }

    public Integer getPage() {
        return page-1;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
