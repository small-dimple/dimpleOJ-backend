package com.dimple.dimpleOJ.judge.codesandbox;

import com.dimple.dimpleOJ.judge.codesandbox.impl.ExampleCodeSandboxImpl;
import com.dimple.dimpleOJ.judge.codesandbox.impl.RemoteCodeSandboxImpl;
import com.dimple.dimpleOJ.judge.codesandbox.impl.ThirdPartyCodeSandboxImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例工厂模式（根据输入的字符串，决定new 什么实例）
 */
public class CodeSandboxFactory {

    // 使用 Map 缓存每种类型的实例
    private final Map<String, CodeSandbox> codeSandboxCache = new ConcurrentHashMap<>();
    
    // 这里使用静态内部类，避免线程安全问题，因为静态内部类，只在初始化时调用一次，后续外部类实例化，不会影响内部类
    private static class Holder{
        //只进行一次实例化，返回给外部
        private static final CodeSandboxFactory Instance = new CodeSandboxFactory();
    }

    private CodeSandboxFactory() {
        //私有化构造方法，防止外部实例化工厂
    }
    public CodeSandbox getCodeSandbox(String type) {
        // 使用缓存：先检查是否已有实例，若无则创建
        return codeSandboxCache.computeIfAbsent(type, this::createCodeSandbox);
    }
    public static CodeSandboxFactory getInstance(){
        //获取这个实例
        return Holder.Instance;
    }

    // 根据类型创建具体的实例
    private CodeSandbox createCodeSandbox(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandboxImpl();
            case "remote":
                return new RemoteCodeSandboxImpl();
            case "thirdParty":
                return new ThirdPartyCodeSandboxImpl();
            default:
                // 默认返回 Example 实现
                return new ExampleCodeSandboxImpl();
        }


    }
}
