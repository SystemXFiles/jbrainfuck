package ru.systemxfiles.jbrainfuck;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by System X - Files on 07.07.2014.
 */
public abstract class Tokenizer{
    public static List<Opcode> tokenize(String code) {
        List<Opcode> retValue = new ArrayList<Opcode>();
        int pos = 0;

        while (pos < code.length()) {
            switch (code.charAt(pos++)) {
                case '>': retValue.add(new Opcode(Opcode.Type.SHIFT, +1)); break;
                case '<': retValue.add(new Opcode(Opcode.Type.SHIFT, -1)); break;

                case '+': retValue.add(new Opcode(Opcode.Type.ADD, +1)); break;
                case '-': retValue.add(new Opcode(Opcode.Type.ADD, -1)); break;

                case '.': retValue.add(new Opcode(Opcode.Type.OUT)); break;
                case ',': retValue.add(new Opcode(Opcode.Type.IN)); break;
                case '[':
                    char next = code.charAt(pos);

                    if((next == '+' || next == '-') && code.charAt(pos + 1) == ']') {
                        retValue.add(new Opcode(Opcode.Type.ZERO));
                        pos += 2;
                    } else
                        retValue.add(new Opcode(Opcode.Type.WHILE));
                    break;
                case ']': retValue.add(new Opcode(Opcode.Type.END)); break;
            }
        }

        return retValue;
    }
}
