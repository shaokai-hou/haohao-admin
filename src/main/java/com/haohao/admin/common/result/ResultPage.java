package com.haohao.admin.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultPage<T> {

    /**
     * 分页数据
     */
    private List<T> data;
    /**
     * 总页数
     */
    private Long total;
}
