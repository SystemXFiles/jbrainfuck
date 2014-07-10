package ru.systemxfiles.jbrainfuck.vm.bf;

import ru.systemxfiles.jbrainfuck.Opcode;
import ru.systemxfiles.jbrainfuck.util.VirtualMachine;

import java.util.List;
import java.util.Stack;

/**
 * Created by System X - Files on 07.07.2014.
 */
public class Compiler extends ru.systemxfiles.jbrainfuck.util.Compiler{
    public Compiler(List<Opcode> opcodes, boolean optimization) {
        super(opcodes, optimization);
    }

    public Compiler(String code, boolean optimization) {
        super(code, optimization);
    }

    @Override
    public VirtualMachine compile(int memorySize) {
        Stack<Integer> pos = new Stack<Integer>();
        Opcode[] retValue = opcodes.toArray(new Opcode[opcodes.size()]);

        for (int i = 0, n = retValue.length; i < n; ++i) {
            switch (retValue[i].type) {
                case WHILE:
                    pos.push(i);
                    break;

                case END:
                    int p = pos.pop();

                    retValue[p].arg = i;
                    retValue[i].arg = p;

                    break;
            }
        }

        return new ru.systemxfiles.jbrainfuck.vm.bf.VirtualMachine(retValue, memorySize);
    }
}
