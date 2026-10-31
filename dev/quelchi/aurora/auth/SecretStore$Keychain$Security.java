package dev.quelchi.aurora.auth;

import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

interface SecretStore$Keychain$Security
extends Library {
    public int SecKeychainFindGenericPassword(Pointer var1, int var2, byte[] var3, int var4, byte[] var5, IntByReference var6, PointerByReference var7, Pointer var8);

    public int SecKeychainAddGenericPassword(Pointer var1, int var2, byte[] var3, int var4, byte[] var5, int var6, byte[] var7, Pointer var8);

    public int SecKeychainItemFreeContent(Pointer var1, Pointer var2);
}
