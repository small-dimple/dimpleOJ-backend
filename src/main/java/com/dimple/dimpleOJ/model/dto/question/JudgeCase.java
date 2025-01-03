package com.dimple.dimpleOJ.model.dto.question;

import lombok.Data;

import java.io.Serializable;

@Data
public class JudgeCase implements Serializable {

    /**
     * 输入用例
     */
    private String input;
    /**
     * 输出用例
     */
    private String output;

    private static final long serialVersionUID = 1L;

}
