package ru.systemxfiles.jbrainfuck;

/**
 * Created by System X - Files on 07.07.2014.
 */
public class Opcode{
    public enum Type{
        SHIFT,
        ADD,
        ZERO,
        OUT,
        IN,
        WHILE,
        END
    }

    public Type type = null;
    public int arg = 1;

    public Opcode(Type type, int arg) {
        this.type = type;
        this.arg = arg;
    }

    public Opcode(Type type) {
        this.type = type;
    }

    public Opcode clone(){
        return new Opcode(type, arg);
    }
}
