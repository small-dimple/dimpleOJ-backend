package com.dimple.dimpleOJ.judge.codesandbox;

import com.dimple.dimpleOJ.judge.codesandbox.model.ExecuteCodeRequest;
import com.dimple.dimpleOJ.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CodeSandboxProxy implements CodeSandbox {

    //这里的参数必须传入
    private CodeSandbox codeSandbox;

    //构造函数，传入CodeSandbox对象
    public CodeSandboxProxy(CodeSandbox codeSandbox) {
        this.codeSandbox = codeSandbox;
    }

    /**
     * 静态代理，在原有基础上对原方法的增强，这里是加日志
     * @param executeCodeRequest
     * @return
     */
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("executeCodeRequest: {}", executeCodeRequest);
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        log.info("executeCodeResponse: {}", executeCodeResponse);
        return executeCodeResponse;
    }
}
