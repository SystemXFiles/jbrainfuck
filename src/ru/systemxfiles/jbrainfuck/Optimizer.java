package ru.systemxfiles.jbrainfuck;

import java.util.List;
import java.util.Stack;

/**
 * Created by System X - Files on 07.07.2014.
 */
public abstract class Optimizer {
    public static List<Opcode> optimize(String code) {
        return optimize(Tokenizer.tokenize(code));
    }

    public static List<Opcode> optimize(List<Opcode> tokens) {
        Stack<Opcode> retValue = new Stack<Opcode>();

        for (Opcode token : tokens) {
            switch (token.type){
                case SHIFT:
                case ADD:
                case OUT:
                case IN:
                case ZERO:
                    if(retValue.size() == 0) {
                        retValue.push(token.clone());
                        continue;
                    }

                    if(retValue.peek().type != token.type) {
                        if(retValue.peek().arg == 0)
                            retValue.pop();

                        if(retValue.peek().type == Opcode.Type.ZERO)
                            retValue.peek().arg = 1;

                        retValue.push(token.clone());
                        continue;
                    }

                    retValue.peek().arg += token.arg;
                    break;

                case WHILE:
                case END:
                    retValue.add(token.clone());
                    break;
            }
        }

        return retValue;
    }
}
