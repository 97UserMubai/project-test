package com.boot.utils;

import com.boot.dto.PageVo;

import java.util.List;

/**
 * @author wangbaitao
 * @version 1.0.0
 * @Date 2020/9/4 10:20
 * <h>分页查询工具类</h>
 */
public class PageHelper {
    public static PageVo getPageVo(List<?> list, int pageNo, int pageSize) {
        PageVo pageVo = new PageVo();
        pageVo.setTotalElements((long) list.size());
        pageVo.setPageNumber(pageNo);
        pageVo.setPageSize(pageSize);
        //计算总共有几页
        //100  % 10 == 0 , 100/10 = 10
        //101 % 10 = 1 ,101/10 = 10 + 1 = 11，整型数据取整
        if (list.size() % pageSize == 0) {
            pageVo.setTotalPages(list.size() / pageSize);
        } else {
            pageVo.setTotalPages(list.size() / pageSize + 1);
        }
        //计算集合截取的起始位和结束位，pageNo从0开始，所以在调用该方法前需要将vue得到的参数自行-1
        if (pageNo < 0 || pageNo > pageVo.getTotalPages() - 1) {
            return null;
        }
        if (pageNo == 0) {
            pageVo.setPageList(list.subList(0, pageSize));
        } else if (pageNo == pageVo.getTotalPages() - 1) {
            //末页
            pageVo.setPageList(list.subList(pageNo * pageSize, list.size()));
        } else {
            pageVo.setPageList(list.subList(pageNo * pageSize, pageNo * pageSize + pageSize));
        }
        return pageVo;
    }
}
