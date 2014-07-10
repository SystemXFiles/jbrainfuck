package ru.systemxfiles.jbrainfuck.util;

import ru.systemxfiles.jbrainfuck.Settings;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * Created by System X - Files on 09.07.2014.
 */
public abstract class VirtualMachine implements Runnable{
    public PrintStream out = Settings.stdout;
    public InputStream in = Settings.stdin;
}
