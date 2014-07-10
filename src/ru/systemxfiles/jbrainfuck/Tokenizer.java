package ru.systemxfiles.jbrainfuck;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by System X - Files on 07.07.2014.
 */
public abstract class Tokenizer{
    private static Opcode AsToken(char chr){
        switch (chr) {
            case '>': return new Opcode(Opcode.Type.SHIFT, +1);
            case '<': return new Opcode(Opcode.Type.SHIFT, -1);

            case '+': return new Opcode(Opcode.Type.ADD, +1);
            case '-': return new Opcode(Opcode.Type.ADD, -1);

            case '.': return new Opcode(Opcode.Type.OUT);
            case ',': return new Opcode(Opcode.Type.IN);
            case '[': return new Opcode(Opcode.Type.WHILE);
            case ']': return new Opcode(Opcode.Type.END);
        }

        return new Opcode(null);
    }

    public static List<Opcode> tokenize(String code) {
        List<Opcode> retValue = new ArrayList<Opcode>();
        List<Opcode> zero = new ArrayList<Opcode>();
        int pos = 0;

        while (pos < code.length()) {
            Opcode token = AsToken(code.charAt(pos++));

            switch (zero.size()) {
                case 0:
                    if(token.type == Opcode.Type.WHILE) {
                        zero.add(token);
                        continue;
                    }
                    break;
                case 1:
                    if(token.type == Opcode.Type.ADD) {
                        zero.add(token);
                        continue;
                    }
                    break;
                case 2:
                    if(token.type == Opcode.Type.END) {
                        retValue.add(new Opcode(Opcode.Type.ZERO));
                        zero.clear();
                        continue;
                    }
                    break;
            }

            retValue.addAll(zero);
            zero.clear();

            if(token.type != null)
                retValue.add(token);
        }

        return retValue;
    }
}
