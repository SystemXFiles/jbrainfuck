package ru.systemxfiles.jbrainfuck.util;

/**
 * Created by System X - Files on 09.07.2014.
 */
public class ByteCodeLoader extends ClassLoader {
    public static final ByteCodeLoader clazz = new ByteCodeLoader();

    public Class<?> loadClass(byte[] bytecode) {
        return defineClass(null, bytecode, 0, bytecode.length);
    }
}
