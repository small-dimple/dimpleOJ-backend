package com.dimple.dimpleOJ.model.dto.question;

import lombok.Data;

import java.io.Serializable;

@Data
public class JudgeConfig implements Serializable {

    /**
     * 时间限制 (ms)
     */
    private Long timeLimit;
    /**
     * 内存限制 (KB)
     */
    private Long memoryLimit;

    /**
     * 堆栈限制 (KB)
     */
    private Long stackLimit;




    private static final long serialVersionUID = 1L;
}
