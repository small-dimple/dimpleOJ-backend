package com.dimple.dimpleOJ.judge.codesandbox.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 判题信息
 */
@Data
public class JudgeInfo implements Serializable {


    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 程序执行时间（KB）
     */
    private Long time;

    /**
     * 程序执行内存（KB）
     */
    private Long memory;

    private static final long serialVersionUID = 1L;
}
