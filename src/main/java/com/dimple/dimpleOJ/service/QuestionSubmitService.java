package com.dimple.dimpleOJ.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dimple.dimpleOJ.model.dto.question.QuestionQueryRequest;
import com.dimple.dimpleOJ.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.dimple.dimpleOJ.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.dimple.dimpleOJ.model.entity.Question;
import com.dimple.dimpleOJ.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dimple.dimpleOJ.model.entity.User;
import com.dimple.dimpleOJ.model.vo.QuestionSubmitVO;
import com.dimple.dimpleOJ.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author Administrator
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2024-12-31 17:58:40
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);

    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

//    /**
//     * 从 ES 查询
//     *
//     * @param questionQueryRequest
//     * @return
//     */
//    Page<Question> searchFromEs(QuestionQueryRequest questionQueryRequest);

    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);
}
