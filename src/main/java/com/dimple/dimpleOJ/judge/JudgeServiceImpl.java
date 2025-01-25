package com.dimple.dimpleOJ.judge;

import cn.hutool.json.JSONUtil;
import com.dimple.dimpleOJ.common.ErrorCode;
import com.dimple.dimpleOJ.exception.BusinessException;
import com.dimple.dimpleOJ.judge.codesandbox.CodeSandbox;
import com.dimple.dimpleOJ.judge.codesandbox.CodeSandboxFactory;
import com.dimple.dimpleOJ.judge.codesandbox.CodeSandboxProxy;
import com.dimple.dimpleOJ.judge.codesandbox.model.ExecuteCodeRequest;
import com.dimple.dimpleOJ.judge.codesandbox.model.ExecuteCodeResponse;
import com.dimple.dimpleOJ.judge.strategy.DefaultJudgeStrategy;
import com.dimple.dimpleOJ.judge.strategy.JudgeContext;
import com.dimple.dimpleOJ.judge.strategy.JudgeStrategy;
import com.dimple.dimpleOJ.model.dto.question.JudgeCase;
import com.dimple.dimpleOJ.judge.codesandbox.model.JudgeInfo;
import com.dimple.dimpleOJ.model.entity.Question;
import com.dimple.dimpleOJ.model.entity.QuestionSubmit;
import com.dimple.dimpleOJ.model.enums.QuestionSubmitStatusEnum;
import com.dimple.dimpleOJ.service.QuestionService;
import com.dimple.dimpleOJ.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


/**
 *
 */
@Service
public class JudgeServiceImpl implements JudgeService {

    @Value("${codesandbox.type:example}")
    private String type;

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;


    @Override
    public QuestionSubmit doJudge(Long questionSubmitId) {
        //1. 问题提交信息
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "问题提交信息不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        //问题信息
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "问题信息不存在");
        }
        //2. 设置题目提交状态,如果当前状态不是WAITING,则不提交判题
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目正在判题");
        }
        //3. 更改状态为判题中，防止重复执行
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean updateById = questionSubmitService.updateById(questionSubmitUpdate);
        if (!updateById) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "更新题目提交状态失败");
        }
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        //4. 执行代码沙箱，获取执行结果
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        String judgeCaseStr = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCaseStr, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        CodeSandboxFactory codeSandboxFactory = CodeSandboxFactory.getInstance();
        CodeSandbox codeSandbox = codeSandboxFactory.getCodeSandbox(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();

        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);

        List<String> outputList = executeCodeResponse.getOutputList();
        //5.执行判题逻辑，引入上下文和默认策略，使用默认判题策略

        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setQuestion(question);
        //使用策略实现类
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        JudgeInfo judgeInfo = judgeStrategy.doJudge(judgeContext);

        //6. 判题结束：修改数据库中的判题结果
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        //设置判题状态为成功
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
         updateById = questionSubmitService.updateById(questionSubmitUpdate);
        if (!updateById) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "更新题目成功状态失败");
        }

        return questionSubmitService.getById(questionSubmitId);
    }
}
