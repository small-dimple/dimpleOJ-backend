package com.dimple.dimpleOJ.judge.codesandbox.impl;

import com.dimple.dimpleOJ.judge.codesandbox.CodeSandbox;
import com.dimple.dimpleOJ.judge.codesandbox.model.ExecuteCodeRequest;
import com.dimple.dimpleOJ.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 调用第三方的代码沙箱（使用现成的代码沙箱）
 */
public class ThirdPartyCodeSandboxImpl implements CodeSandbox {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方代码沙箱");

        return null;
    }
}
