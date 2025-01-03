package com.dimple.dimpleOJ.judge.codesandbox.model;

import com.dimple.dimpleOJ.model.dto.questionsubmit.JudgeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeResponse {

    /**
     * 接口信息
     */
    private String message;
    /**
     * 输出信息
     */
    private List<String> outputList;
    /**
     * 状态
     */
    private String status;
    /**
     * 评测信息
     */
    private JudgeInfo judgeInfo;


}
