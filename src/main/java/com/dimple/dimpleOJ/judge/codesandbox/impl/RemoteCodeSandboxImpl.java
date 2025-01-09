package com.dimple.dimpleOJ.judge.codesandbox.impl;

import com.dimple.dimpleOJ.judge.codesandbox.CodeSandbox;
import com.dimple.dimpleOJ.judge.codesandbox.model.ExecuteCodeRequest;
import com.dimple.dimpleOJ.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 自己实现的代码沙箱
 */
public class RemoteCodeSandboxImpl implements CodeSandbox {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");


        return null;
    }
}
