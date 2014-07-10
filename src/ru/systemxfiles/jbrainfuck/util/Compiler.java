package ru.systemxfiles.jbrainfuck.util;

import ru.systemxfiles.jbrainfuck.Opcode;
import ru.systemxfiles.jbrainfuck.Settings;

import java.util.List;

/**
 * Created by System X - Files on 09.07.2014.
 */
public abstract class Compiler extends Constructor {
    public Compiler(List<Opcode> opcodes, boolean optimization) {
        super(opcodes, optimization);
    }

    public Compiler(String code, boolean optimization) {
        super(code, optimization);
    }

    public VirtualMachine compile() {
        return compile(Settings.memorySize);
    }

    public abstract VirtualMachine compile(int memorySize);
}
