package com.dimple.dimpleOJ.common;

import com.dimple.dimpleOJ.constant.CommonConstant;
import lombok.Data;

/**
 * 分页请求
 *
  * @author 程序员dimple
 */
@Data
public class PageRequest {

    /**
     * 当前页号
     */
    private int current = 1;

    /**
     * 页面大小
     */
    private int pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = CommonConstant.SORT_ORDER_ASC;
}
