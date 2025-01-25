package com.dimple.dimpleOJ.judge.codesandbox.impl;

import com.dimple.dimpleOJ.judge.codesandbox.CodeSandbox;
import com.dimple.dimpleOJ.judge.codesandbox.model.ExecuteCodeRequest;
import com.dimple.dimpleOJ.judge.codesandbox.model.ExecuteCodeResponse;
import com.dimple.dimpleOJ.judge.codesandbox.model.JudgeInfo;
import com.dimple.dimpleOJ.model.enums.JudgeInfoMessageEnum;
import com.dimple.dimpleOJ.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

/**
 * 示例代码沙箱（跑通业务）
 */
public class ExampleCodeSandboxImpl implements CodeSandbox {


    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("示例代码沙箱");
        List<String> inputList = executeCodeRequest.getInputList();
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setTime(100L);
        judgeInfo.setMemory(100L);

        
        executeCodeResponse.setJudgeInfo(judgeInfo);



        return executeCodeResponse;
    }
}
