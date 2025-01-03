package com.dimple.dimpleOJ.judge.codesandbox;

import com.dimple.dimpleOJ.judge.codesandbox.model.ExecuteCodeRequest;
import com.dimple.dimpleOJ.judge.codesandbox.model.ExecuteCodeResponse;

public interface CodeSandbox {


    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
