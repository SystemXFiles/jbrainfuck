package ru.systemxfiles.jbrainfuck.util;

import ru.systemxfiles.jbrainfuck.Opcode;
import ru.systemxfiles.jbrainfuck.Optimizer;
import ru.systemxfiles.jbrainfuck.Tokenizer;

import java.util.List;

/**
 * Created by System X - Files on 09.07.2014.
 */
public abstract class Constructor {
    protected List<Opcode> opcodes;

    public Constructor(List<Opcode> opcodes, boolean optimization) {
        this.opcodes = optimization ? Optimizer.optimize(opcodes) : opcodes;
    }

    public Constructor(String code, boolean optimization) {
        opcodes = optimization ? Optimizer.optimize(code) : Tokenizer.tokenize(code);
    }
}
