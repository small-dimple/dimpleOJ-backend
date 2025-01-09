package com.dimple.dimpleOJ.judge.codesandbox;

import com.dimple.dimpleOJ.judge.codesandbox.model.ExecuteCodeRequest;
import com.dimple.dimpleOJ.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SpringBootTest
class CodeSandboxTest {

    @Test
    void executeCode() {

        CodeSandboxFactory factory = CodeSandboxFactory.getInstance();

        // 获取 "example" 类型的实例
        CodeSandbox exampleSandbox1 = factory.getCodeSandbox("example");
        CodeSandbox exampleSandbox2 = factory.getCodeSandbox("example");

        // 获取 "remote" 类型的实例
        CodeSandbox remoteSandbox1 = factory.getCodeSandbox("remote");
        CodeSandbox remoteSandbox2 = factory.getCodeSandbox("remote");

        // 验证同一类型的实例是同一个
        System.out.println(exampleSandbox1 == exampleSandbox2); // true
        System.out.println(remoteSandbox1 == remoteSandbox2);   // true

        // 不同类型的实例是不同的
        System.out.println(exampleSandbox1 == remoteSandbox1);  // false
    }

    @Test
    void testCodeSandboxFactorySingleton() {
        CodeSandboxFactory instance1 = CodeSandboxFactory.getInstance();
        CodeSandboxFactory instance2 = CodeSandboxFactory.getInstance();

        // 判断是否是同一个实例
        System.out.println(instance1 == instance2 ? "CodeSandboxFactory 是单例" : "CodeSandboxFactory 不是单例");
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String type = scanner.next();
            CodeSandboxFactory codeSandboxFactory = CodeSandboxFactory.getInstance();
            CodeSandbox example = codeSandboxFactory.getCodeSandbox(type);
            String code = "int main() { }";
            String language = QuestionSubmitLanguageEnum.JAVA.getValue();
            List<String> inputList = Arrays.asList("1 2", "3 4");
            ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                    .code(code)
                    .language(language)
                    .inputList(inputList)
                    .build();

            example.executeCode(executeCodeRequest);
        }
    }

}