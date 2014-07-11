Brainfuck interpreter and compiler for JVM
------------------------------------------

An interpreter and compiler Brainfuck under Java VM. It is written entirely in Java, Brainfuck source code can be interpreted in its VM or compiled into bytecode JVM, which optimizes and accelerates through JIT. If you are familiar with the projects JRuby, Jython, etc., then JBrainfuck is the same, only for Brainfuck.

### Features
+ Шnterpretation
+ Compiling under JVM, execution and saved in "class file".
+ JIT (acceleration to 8x)
+ Optimization (minimize multiple operations and replacement typical)
+ Translation Brainfuck code to Java and C++
+ Other options (replacement for the standard I/O and memory size)

### How to use?
	
	//Hello World!
	String code = 
		"++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.++++++" +
		"+..+++.>++.<<+++++++++++++++. >.+++.------.--------.>+.>.";

	Compiler compiler = new Compiler(code, true);
	VirtualMachine vm = compiler.compile();
	vm.run();

### What kind of API available?

1. [Java Compiler](https://github.com/SystemX-Files/jbrainfuck/blob/master/src/ru/systemxfiles/jbrainfuck/vm/java/Compiler.java) - class to compile BrainFuck on the JVM
2. [BF Compiler](https://github.com/SystemX-Files/jbrainfuck/blob/master/src/ru/systemxfiles/jbrainfuck/vm/bf/Compiler.java) - class to compile BrainFuck under its own VM
3. [VirtualMachine](https://github.com/SystemX-Files/jbrainfuck/blob/master/src/ru/systemxfiles/jbrainfuck/util/VirtualMachine.java) - class execution results compilation [Java Compiler](https://github.com/SystemX-Files/JBrainFuck/blob/master/src/ru/systemxfiles/jbrainfuck/vm/java/Compiler.java) or [BF Compiler](https://github.com/SystemX-Files/JBrainFuck/blob/master/src/ru/systemxfiles/jbrainfuck/vm/bf/Compiler.java)
4. [Settings](https://github.com/SystemX-Files/jbrainfuck/blob/master/src/ru/systemxfiles/jbrainfuck/Settings.java) - stores in itself global setting the memory size and I/O variables
5. [Translator](https://github.com/SystemX-Files/jbrainfuck/blob/master/src/ru/systemxfiles/jbrainfuck/util/Translator.java) - translator BrainFuck to Java and C++

---

Brainfuck интерпретатор и компилятор для JVM
--------------------------------------------

Это интерпретатор и компилятор языка Brainfuck под Java VM. Он полностью работает на Java, исходный код Brainfuck может быть проинтерпретирован в своей VM или же скомпилирован в байткод JVM, который подвергается оптимизациям и JIT. Если вы знакомы с проектами JRuby, Jython и т.д., то JBrainfuck это тоже самое, только для Brainfuck.

### Возможности
+ Интерпретация
+ Компиляция под JVM, выполнение и сохрание в class файл. 
+ JIT (до 8 раз быстрее обычной интерпретации)
+ Оптимизации (сворачивание множественных операций и замена типичных)
+ Трансляция кода Brainfuck на языки Java и C++
+ Другие опции (замена стандартного ввода/вывода и размер памяти)

### Как пользоваться? 
	
	//Hello World!
	String code = 
		"++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++.>+.++++++" +
		"+..+++.>++.<<+++++++++++++++. >.+++.------.--------.>+.>.";

	Compiler compiler = new Compiler(code, true);
	VirtualMachine vm = compiler.compile();
	vm.run();

### Какое API?

1. [Java Compiler](https://github.com/SystemX-Files/jbrainfuck/blob/master/src/ru/systemxfiles/jbrainfuck/vm/java/Compiler.java) - класс для компиляции BrainFuck под JVM
2. [BF Compiler](https://github.com/SystemX-Files/jbrainfuck/blob/master/src/ru/systemxfiles/jbrainfuck/vm/bf/Compiler.java) - класс для компиляции BrainFuck под обычную VM
3. [VirtualMachine](https://github.com/SystemX-Files/jbrainfuck/blob/master/src/ru/systemxfiles/jbrainfuck/util/VirtualMachine.java) - класс для запуска результата компиляции [Java Compiler](https://github.com/SystemX-Files/JBrainFuck/blob/master/src/ru/systemxfiles/jbrainfuck/vm/java/Compiler.java) или [BF Compiler](https://github.com/SystemX-Files/JBrainFuck/blob/master/src/ru/systemxfiles/jbrainfuck/vm/bf/Compiler.java)
4. [Settings](https://github.com/SystemX-Files/jbrainfuck/blob/master/src/ru/systemxfiles/jbrainfuck/Settings.java) - хранит в себе глобальные настройки размера памяти и переменные ввода/вывода
5. [Translator](https://github.com/SystemX-Files/jbrainfuck/blob/master/src/ru/systemxfiles/jbrainfuck/util/Translator.java) - транслятор BrainFuck на языки Java и С++
