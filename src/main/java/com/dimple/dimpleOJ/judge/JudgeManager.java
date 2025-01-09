package com.dimple.dimpleOJ.judge;

import com.dimple.dimpleOJ.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 将选择策略方法抽象出来，根据值选择什么策略实现类
 */
@Service
public class JudgeManager {

    QuestionSubmit doJudge(Long questionSubmitId){

        //这里可以执行选择策略方式
        return new QuestionSubmit();
    }


}
