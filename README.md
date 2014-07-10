Brainfuck интерпретатор и компилятор для JVM
--------------------------------------------

Это компилятор и интерпретатор языка Brainfuck под Java VM. Он полностью работает на Java, исходный код Brainfuck может быть проинтерпретирован в своей VM или же скомпилирован в байткод JVM, который подвергается оптимизациям и JIT. Если вы знакомы с проектами JRuby, Jython и т.д., то JBrainfuck это тоже самое, только для Brainfuck.

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

1. [Java Compiler](https://github.com/SystemX-Files/jbrainfuck/blob/master/src/ru/systemxfiles/jbrainfuck/vm/java/Compiler.java) - класс для компиляции BrainFuck под JVM.
2. [BF Compiler](https://github.com/SystemX-Files/jbrainfuck/blob/master/src/ru/systemxfiles/jbrainfuck/vm/bf/Compiler.java) - класс для компиляции BrainFuck под обычную VM.
3. [VirtualMachine](https://github.com/SystemX-Files/jbrainfuck/blob/master/src/ru/systemxfiles/jbrainfuck/util/VirtualMachine.java) - класс для запуска результата компиляции [Java Compiler](https://github.com/SystemX-Files/JBrainFuck/blob/master/src/ru/systemxfiles/jbrainfuck/vm/java/Compiler.java) или [BF Compiler](https://github.com/SystemX-Files/JBrainFuck/blob/master/src/ru/systemxfiles/jbrainfuck/vm/bf/Compiler.java).
4. [Settings](https://github.com/SystemX-Files/jbrainfuck/blob/master/src/ru/systemxfiles/jbrainfuck/Settings.java) - хранит в себе глобальные настройки размера памяти и переменные ввода/вывода.
5. [Translator](https://github.com/SystemX-Files/jbrainfuck/blob/master/src/ru/systemxfiles/jbrainfuck/util/Translator.java) - транслятор BrainFuck на языки Java и С++
