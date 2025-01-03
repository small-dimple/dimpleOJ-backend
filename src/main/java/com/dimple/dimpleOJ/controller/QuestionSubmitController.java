package com.dimple.dimpleOJ.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dimple.dimpleOJ.annotation.AuthCheck;
import com.dimple.dimpleOJ.common.BaseResponse;
import com.dimple.dimpleOJ.common.ErrorCode;
import com.dimple.dimpleOJ.common.ResultUtils;
import com.dimple.dimpleOJ.constant.UserConstant;
import com.dimple.dimpleOJ.exception.BusinessException;
import com.dimple.dimpleOJ.model.dto.question.QuestionQueryRequest;
import com.dimple.dimpleOJ.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.dimple.dimpleOJ.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.dimple.dimpleOJ.model.entity.Question;
import com.dimple.dimpleOJ.model.entity.QuestionSubmit;
import com.dimple.dimpleOJ.model.entity.User;
import com.dimple.dimpleOJ.model.vo.QuestionSubmitVO;
import com.dimple.dimpleOJ.service.QuestionSubmitService;
import com.dimple.dimpleOJ.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 提交题目接口
 *
  * @author 程序员dimple
 */
@RestController
@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private UserService userService;

    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param request
     * @return 提交记录的 id
     */
    @PostMapping("/")
    public BaseResponse<Long> doQuestionSubmit(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
            HttpServletRequest request) {
        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 登录才能点赞
        final User loginUser = userService.getLoginUser(request);
        long questionSubmitId = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
        return ResultUtils.success(questionSubmitId);
    }

    /**
     * 分页获取题目提交列表（普通用户只能看到非答案、提交代码等公开信息）
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<QuestionSubmitVO>> listQuestionSubmitByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest , HttpServletRequest request) {
        long current = questionSubmitQueryRequest.getCurrent();
        long size = questionSubmitQueryRequest.getPageSize();
        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));


        User loginUser = userService.getLoginUser(request);
        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage , loginUser));
    }


}
