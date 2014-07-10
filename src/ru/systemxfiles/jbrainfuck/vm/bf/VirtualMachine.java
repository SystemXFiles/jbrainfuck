package ru.systemxfiles.jbrainfuck.vm.bf;

import ru.systemxfiles.jbrainfuck.Opcode;

import java.io.IOException;

public class VirtualMachine extends ru.systemxfiles.jbrainfuck.util.VirtualMachine {
    private Opcode[] opcodes;
    private int memorySize;

    public VirtualMachine(Opcode[] opcodes, int memorySize) {
        this.opcodes = opcodes;
        this.memorySize = memorySize;
    }

    @Override
    public void run() {
        char[] arr = new char[memorySize];

        for (int i = 0, n = opcodes.length, p = memorySize / 2; i < n; ++i) {
            Opcode opcode = opcodes[i];

            switch (opcode.type) {
                case SHIFT:
                    p += opcode.arg;
                    break;
                case ADD:
                    arr[p] += opcode.arg;
                    break;
                case ZERO:
                    arr[p] = 0;
                    break;
                case OUT:
                    for(int j = 0; j < opcode.arg; ++j)
                        out.print(arr[p]);
                    break;
                case IN:
                    try {
                        for(int j = 0; j < opcode.arg; ++j)
                            arr[p] = (char)in.read();
                    } catch (IOException ignored) {}
                    break;
                case WHILE:
                    if(arr[p] == 0)
                        i = opcode.arg;
                    break;
                case END:
                    if(arr[p] != 0)
                        i = opcode.arg;
                    break;
            }
        }
    }
}
