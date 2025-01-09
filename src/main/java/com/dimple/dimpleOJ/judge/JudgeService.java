package com.dimple.dimpleOJ.judge;


import com.dimple.dimpleOJ.judge.codesandbox.model.ExecuteCodeRequest;
import com.dimple.dimpleOJ.judge.codesandbox.model.ExecuteCodeResponse;
import com.dimple.dimpleOJ.model.entity.QuestionSubmit;
import com.dimple.dimpleOJ.model.vo.QuestionSubmitVO;
import org.springframework.stereotype.Service;

/**
 * 判题服务
 */
public interface JudgeService {


    /**
     * 判题
     * @param questionSubmitId
     * @return
     */
    QuestionSubmit doJudge(Long questionSubmitId);





}
