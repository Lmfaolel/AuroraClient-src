package dev.quelchi.aurora.perf;

import com.sun.jna.Library;
import com.sun.jna.Pointer;

interface WinTuning$Kernel32
extends Library {
    public Pointer GetCurrentThread();

    public Pointer GetCurrentProcess();

    public boolean SetThreadPriority(Pointer var1, int var2);

    public boolean SetProcessInformation(Pointer var1, int var2, Pointer var3, int var4);
}
