package com.demo.jfinal.result;



import com.jfinal.plugin.activerecord.Page;
import lombok.Data;


/**
 * PageResponse :返回分页的所有相关信息和数据
 *
 * @author zhangxiaoxiang
 * @date 2019/9/15
 */

@Data
public class PageResponse<T> {


    /**
     * 查询结果(分页数据)
     */
    private T pageDataList;



}
