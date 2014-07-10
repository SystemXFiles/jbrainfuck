package ru.systemxfiles.jbrainfuck.vm.java;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;
import ru.systemxfiles.jbrainfuck.Opcode;
import ru.systemxfiles.jbrainfuck.Settings;
import ru.systemxfiles.jbrainfuck.util.ByteCodeLoader;
import ru.systemxfiles.jbrainfuck.util.VirtualMachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Stack;

/**
 * Created by System X - Files on 07.07.2014.
 */
public class Compiler extends ru.systemxfiles.jbrainfuck.util.Compiler implements Opcodes {
    public Compiler(List<Opcode> opcodes, boolean optimization) {
        super(opcodes, optimization);
    }

    public Compiler(String code, boolean optimization) {
        super(code, optimization);
    }

    private static int counter = 0;

    public VirtualMachine compile(int memorySize){

        Class<?> aClass = ByteCodeLoader.clazz.loadClass(
                toByteCode(
                        "BrainFuckJit$" + String.valueOf(counter++),
                        memorySize
                )
        );

        try {
            return (VirtualMachine)aClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public byte[] toByteCode(String className){
        return toByteCode(className, Settings.memorySize);
    }

    public byte[] toByteCode(String className, int memorySize){
        ClassNode cn = new ClassNode();

        cn.version = V1_8;
        cn.access = ACC_PUBLIC + ACC_SUPER;
        cn.name = className;
        cn.superName = VirtualMachine.class.getName().replace('.','/');

        {
            MethodNode mn = new MethodNode(ACC_PUBLIC, "<init>", "()V", null, null);
            InsnList il = mn.instructions;

            il.add(new VarInsnNode(ALOAD, 0));
            il.add(new MethodInsnNode(INVOKESPECIAL, cn.superName, "<init>", "()V", false));
            il.add(new InsnNode(RETURN));

            cn.methods.add(mn);
        }

        {
            Stack<LabelNode> lbls = new Stack<LabelNode>();
            MethodNode mn = new MethodNode(ACC_PUBLIC, "run", "()V", null, null);
            InsnList il = mn.instructions;

            il.add(new LdcInsnNode(memorySize));
            il.add(new IntInsnNode(NEWARRAY, T_CHAR));
            il.add(new VarInsnNode(ASTORE, 1));

            il.add(new LdcInsnNode(memorySize / 2));
            il.add(new VarInsnNode(ISTORE, 2));

            for (Opcode opcode : opcodes) {
                switch (opcode.type) {
                    case SHIFT:
                        il.add(new IincInsnNode(2, opcode.arg));
                        break;
                    case ADD:
                        il.add(new VarInsnNode(ALOAD, 1));
                        il.add(new VarInsnNode(ILOAD, 2));
                        il.add(new InsnNode(DUP2));
                        il.add(new InsnNode(CALOAD));
                        il.add(new LdcInsnNode(opcode.arg));
                        il.add(new InsnNode(IADD));
                        il.add(new InsnNode(CASTORE));

                        break;
                    case ZERO:
                        il.add(new VarInsnNode(ALOAD, 1));
                        il.add(new VarInsnNode(ILOAD, 2));
                        il.add(new InsnNode(ICONST_0));
                        il.add(new InsnNode(CASTORE));

                        break;
                    case OUT:
                        for (int i = 0; i < opcode.arg; ++i) {
                            il.add(new VarInsnNode(ALOAD, 0));
                            il.add(new FieldInsnNode(GETFIELD, cn.name, "out", "Ljava/io/PrintStream;"));
                            il.add(new VarInsnNode(ALOAD, 1));
                            il.add(new VarInsnNode(ILOAD, 2));
                            il.add(new InsnNode(CALOAD));
                            il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/io/PrintStream", "print", "(C)V", false));
                        }

                        break;
                    case IN:
                        for (int i = 0; i < opcode.arg; ++i) {
                            il.add(new VarInsnNode(ALOAD, 1));
                            il.add(new VarInsnNode(ILOAD, 2));
                            il.add(new VarInsnNode(ALOAD, 0));
                            il.add(new FieldInsnNode(GETSTATIC, cn.name, "in", "Ljava/io/InputStream;"));
                            il.add(new MethodInsnNode(INVOKEVIRTUAL, "java/io/InputStream", "read", "()I", false));
                            il.add(new InsnNode(CASTORE));
                        }

                        break;
                    case WHILE:
                        LabelNode
                                begin = new LabelNode(),
                                end = new LabelNode();

                        lbls.push(end);
                        lbls.push(begin);

                        il.add(begin);
                        il.add(new VarInsnNode(ALOAD, 1));
                        il.add(new VarInsnNode(ILOAD, 2));
                        il.add(new InsnNode(CALOAD));
                        il.add(new JumpInsnNode(IFEQ, end));
                        break;
                    case END:
                        il.add(new JumpInsnNode(GOTO, lbls.pop()));
                        il.add(lbls.pop());
                        break;
                }
            }

            il.add(new InsnNode(RETURN));
            cn.methods.add(mn);
        }

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cn.accept(cw);

        return cw.toByteArray();
    }

    public void saveToFile(String classFile) {
        saveToFile(new File(classFile));
    }

    public void saveToFile(File classFile) {
        saveToFile(classFile, Settings.memorySize);
    }

    public void saveToFile(String classFile, int memorySize) {
        saveToFile(new File(classFile), memorySize);
    }

    public void saveToFile(File classFile, int memorySize) {
        String className = classFile.getName();

        if(className.contains("."))
            className = className.substring(0, className.lastIndexOf('.'));

        try {
            FileOutputStream stream = new FileOutputStream(classFile);
            stream.write(toByteCode(className, memorySize));
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
