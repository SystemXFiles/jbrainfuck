import ru.systemxfiles.jbrainfuck.util.VirtualMachine;
import ru.systemxfiles.jbrainfuck.vm.java.Compiler;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Test {
    public static void main(String[] args) {
        String code =
                ">+>+>+>+>++<[>[<+++>-\n" +
                " >>>>>\n" +
                " >+>+>+>+>++<[>[<+++>-\n" +
                "   >>>>>\n" +
                "   >+>+>+>+>++<[>[<+++>-\n" +
                "     >>>>>\n" +
                "     >+>+>+>+>++<[>[<+++>-\n" +
                "       >>>>>\n" +
                "       +++[->+++++<]>[-]<\n" +
                "       <<<<<\n" +
                "     ]<<]>[-]\n" +
                "     <<<<<\n" +
                "   ]<<]>[-]\n" +
                "   <<<<<\n" +
                " ]<<]>[-]\n" +
                " <<<<<\n" +
                "]<<]>.";

        Compiler compiler = new Compiler(code, true);
        VirtualMachine vm = compiler.compile();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        vm.out = new PrintStream(out);

        long t = System.currentTimeMillis();

        vm.run();

        System.out.printf("Time: %d ms.\n", System.currentTimeMillis() - t);
        System.out.printf("Result: %s\n", out.toString());
        System.out.printf("Result is correct? %s.\n", out.toString().equals("\u00CA") ? "Yes" : "No");
    }
}
