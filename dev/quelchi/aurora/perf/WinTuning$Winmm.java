package dev.quelchi.aurora.perf;

import com.sun.jna.Library;

interface WinTuning$Winmm
extends Library {
    public int timeBeginPeriod(int var1);
}
