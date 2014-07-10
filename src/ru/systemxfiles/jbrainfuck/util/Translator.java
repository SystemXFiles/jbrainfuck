package ru.systemxfiles.jbrainfuck.util;

import ru.systemxfiles.jbrainfuck.Opcode;
import ru.systemxfiles.jbrainfuck.Settings;

import java.util.List;

/**
 * Created by System X - Files on 08.07.2014.
 */
public class Translator extends Constructor{
    public Translator(List<Opcode> opcodes, boolean optimization) {
        super(opcodes, optimization);
    }

    public Translator(String code, boolean optimization) {
        super(code, optimization);
    }

    private String repeat(char chr, int count){
        StringBuilder retValue = new StringBuilder();

        while(count --> 0)
            retValue.append(chr);

        return retValue.toString();
    }

    public String AsJava(String methodName){
        StringBuilder builder = new StringBuilder();

        int t = 0;

        builder
                .append(repeat('\t', t))
                .append("public static void ")
                .append(methodName)
                .append("() {\n");

        ++t;
        builder
                .append(repeat('\t', t))
                .append("char[] arr = new char[")
                .append(Settings.memorySize)
                .append("];\n")

                .append(repeat('\t', t))
                .append("int i = ")
                .append(Settings.memorySize / 2)
                .append(";\n\n");

        for (Opcode opcode : opcodes) {
            switch (opcode.type) {
                case SHIFT:
                    builder
                            .append(repeat('\t', t))
                            .append("i += ")
                            .append(opcode.arg)
                            .append(";\n");
                    break;
                case ADD:
                    builder
                            .append(repeat('\t', t))
                            .append("arr[i] += ")
                            .append(opcode.arg)
                            .append(";\n");
                    break;
                case ZERO:
                    builder
                            .append(repeat('\t', t))
                            .append("arr[i] = 0;\n");
                    break;
                case OUT:
                    if(opcode.arg == 1)
                        builder
                                .append(repeat('\t', t))
                                .append("System.out.print(arr[i]);\n");
                    else
                        builder
                                .append(repeat('\t', t))
                                .append("for(int j = 0; j < ")
                                .append(opcode.arg)
                                .append("; ++j)\n")

                                .append(repeat('\t', t + 1))
                                .append("System.out.print(arr[i]);\n");
                    break;
                case IN:
                    if(opcode.arg == 1)
                        builder
                                .append(repeat('\t', t))
                                .append("try {\n")

                                .append(repeat('\t', t + 1))
                                .append("arr[i] = (char)System.in.read();\n")

                                .append(repeat('\t', t))
                                .append("} catch (IOException ignored) {}\n");
                    else
                        builder
                                .append(repeat('\t', t))
                                .append("try {\n")

                                .append(repeat('\t', t + 1))
                                .append("for(int j = 0; j < ")
                                .append(opcode.arg)
                                .append("; ++j)\n")

                                .append(repeat('\t', t + 2))
                                .append("arr[i] = (char)System.in.read();\n")

                                .append(repeat('\t', t))
                                .append("} catch (IOException ignored) {}\n");
                    break;
                case WHILE:
                    builder
                            .append(repeat('\t', t))
                            .append("while(arr[i] != 0) {\n");

                    ++t;
                    break;
                case END:
                    --t;

                    builder
                            .append(repeat('\t', t))
                            .append("}\n");
                    break;
            }
        }

        --t;
        builder
                .append(repeat('\t', t))
                .append("}\n");

        return builder.toString();
    }

    public String AsCPP(String funcName){
        StringBuilder builder = new StringBuilder();

        int t = 0;

        builder
                .append(repeat('\t', t))
                .append("void ")
                .append(funcName)
                .append("() {\n");

        ++t;
        builder
                .append(repeat('\t', t))
                .append("char arr[")
                .append(Settings.memorySize)
                .append("] = {};\n")

                .append(repeat('\t', t))
                .append("size_t i = ")
                .append(Settings.memorySize / 2)
                .append(";\n\n");

        for (Opcode opcode : opcodes) {
            switch (opcode.type) {
                case SHIFT:
                    builder
                            .append(repeat('\t', t))
                            .append("i += ")
                            .append(opcode.arg)
                            .append(";\n");
                    break;
                case ADD:
                    builder
                            .append(repeat('\t', t))
                            .append("arr[i] += ")
                            .append(opcode.arg)
                            .append(";\n");
                    break;
                case ZERO:
                    builder
                            .append(repeat('\t', t))
                            .append("arr[i] = 0;\n");
                    break;
                case OUT:
                    if(opcode.arg == 1)
                        builder
                                .append(repeat('\t', t))
                                .append("putchar(arr[i]);\n");
                    else
                        builder
                                .append(repeat('\t', t))
                                .append("for(size_t j = 0; j < ")
                                .append(opcode.arg)
                                .append("; ++j)\n")

                                .append(repeat('\t', t + 1))
                                .append("putchar(arr[i]);\n");
                    break;
                case IN:
                    if(opcode.arg == 1)
                        builder
                                .append(repeat('\t', t))
                                .append("arr[i] = getchar();\n");
                    else
                        builder
                                .append(repeat('\t', t))
                                .append("for(size_t j = 0; j < ")
                                .append(opcode.arg)
                                .append("; ++j)\n")

                                .append(repeat('\t', t + 1))
                                .append("arr[i] = getchar();\n");
                    break;
                case WHILE:
                    builder
                            .append(repeat('\t', t))
                            .append("while(arr[i]) {\n");

                    ++t;
                    break;
                case END:
                    --t;

                    builder
                            .append(repeat('\t', t))
                            .append("}\n");
                    break;
            }
        }

        --t;
        builder
                .append(repeat('\t', t))
                .append("}\n");

        return builder.toString();
    }
}
