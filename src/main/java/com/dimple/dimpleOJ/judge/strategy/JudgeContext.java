package com.dimple.dimpleOJ.judge.strategy;

import com.dimple.dimpleOJ.model.dto.questionsubmit.JudgeInfo;
import com.dimple.dimpleOJ.model.entity.Question;
import lombok.Data;

import java.util.List;

/**
 * 判题上下文（用于定义在策略中传递的参数）
 */

@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private Question question;

}
