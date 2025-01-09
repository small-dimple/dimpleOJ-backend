package com.dimple.dimpleOJ.judge.strategy;

import com.dimple.dimpleOJ.model.dto.questionsubmit.JudgeInfo;

public interface JudgeStrategy {

    /**
     * 执行判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext);
}
