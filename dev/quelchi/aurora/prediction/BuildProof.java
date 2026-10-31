package dev.quelchi.aurora.prediction;

import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.prediction.PredictionHandshake;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.Iterator;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public final class BuildProof {
    private static final String[] a;
    private static final long b;

    private BuildProof() {
    }

    
    public static byte[][] fingerprint(Path path) throws IOException {
        Closeable closeable;
        MessageDigest messageDigest;
        block36: {
            Object object;
            boolean bl;
            block33: {
                InputStream inputStream;
                block34: {
                    bl = PredictionHandshake.a;
                    Path path2 = path;
                    if (!bl) {
                        if (path2 == null) return null;
                        path2 = path;
                    }
                    if (!Files.isRegularFile(path2, new LinkOption[0])) {
                        return null;
                    }
                    messageDigest = BuildProof.sha();
                    closeable = Files.newInputStream(path, new OpenOption[0]);
                    try {
                        int n;
                        object = new byte[(int)b];
                        while ((n = ((InputStream)closeable).read((byte[])object)) > 0) {
                            messageDigest.update((byte[])object, 0, n);
                            if (!bl) {
                                if (!bl) continue;
                            }
                            break block33;
                        }
                        inputStream = closeable;
                        if (bl) break block34;
                    }
                    catch (Throwable throwable) {
                        if (bl) throw throwable;
                        if (closeable == null) throw throwable;
                        try {
                            ((InputStream)closeable).close();
                            throw throwable;
                        }
                        catch (Throwable throwable2) {
                            throwable.addSuppressed(throwable2);
                        }
                        throw throwable;
                    }
                    if (inputStream == null) break block33;
                    inputStream = closeable;
                }
                inputStream.close();
            }
            closeable = new ByteArrayOutputStream();
            object = new JarFile(path.toFile(), false);
            try {
                JarEntry jarEntry;
                Iterator<JarEntry> iterator;
                block35: {
                    Iterator<JarEntry> iterator2;
                    ArrayList<JarEntry> arrayList = new ArrayList<JarEntry>();
                    iterator = Collections.list(((JarFile)object).entries()).iterator();
                    while (iterator.hasNext()) {
                        iterator2 = iterator;
                        if (!bl) {
                            jarEntry = iterator2.next();
                            boolean bl2 = jarEntry.isDirectory();
                            if (!bl && !bl2) {
                                bl2 = arrayList.add(jarEntry);
                            }
                            if (!bl) continue;
                        }
                        break block35;
                    }
                    arrayList.sort(BuildProof::lambda$fingerprint$0);
                    iterator2 = iterator = arrayList.iterator();
                }
                while (iterator.hasNext()) {
                    block37: {
                        jarEntry = iterator.next();
                        if (bl) break block36;
                        InputStream inputStream = ((JarFile)object).getInputStream(jarEntry);
                        try {
                            ((OutputStream)closeable).write(jarEntry.getName().getBytes(StandardCharsets.UTF_8));
                            ((ByteArrayOutputStream)closeable).write(0);
                            ((OutputStream)closeable).write(BuildProof.sha().digest(inputStream.readAllBytes()));
                            if (bl || inputStream == null) break block37;
                        }
                        catch (Throwable throwable) {
                            if (bl) throw throwable;
                            if (inputStream == null) throw throwable;
                            try {
                                inputStream.close();
                                throw throwable;
                            }
                            catch (Throwable throwable3) {
                                throwable.addSuppressed(throwable3);
                            }
                            throw throwable;
                        }
                        inputStream.close();
                    }
                    if (!bl) continue;
                    break;
                }
            }
            catch (Throwable throwable) {
                try {
                    ((ZipFile)object).close();
                    throw throwable;
                }
                catch (Throwable throwable4) {
                    throwable.addSuppressed(throwable4);
                }
                throw throwable;
            }
            ((ZipFile)object).close();
        }
        return new byte[][]{messageDigest.digest(), BuildProof.sha().digest(((ByteArrayOutputStream)closeable).toByteArray())};
    }

    
    public static byte[][] fingerprint(byte[] byArray) throws IOException {
        Closeable closeable;
        HashMap hashMap;
        ArrayList<String> arrayList;
        boolean bl;
        block13: {
            bl = PredictionHandshake.a;
            if (byArray == null) {
                return null;
            }
            arrayList = new ArrayList<String>();
            hashMap = new HashMap();
            closeable = new ZipInputStream(new ByteArrayInputStream(byArray));
            try {
                Object object;
                while ((object = ((ZipInputStream)closeable).getNextEntry()) != null) {
                    if (bl) break block13;
                    Object object2 = object;
                    if (!bl) {
                        if (((ZipEntry)object2).isDirectory()) continue;
                        arrayList.add(((ZipEntry)object).getName());
                        object2 = hashMap.put(((ZipEntry)object).getName(), BuildProof.sha().digest(((ZipInputStream)closeable).readAllBytes()));
                    }
                    if (!bl) continue;
                    break;
                }
            }
            catch (Throwable throwable) {
                try {
                    ((ZipInputStream)closeable).close();
                    throw throwable;
                }
                catch (Throwable throwable2) {
                    throwable.addSuppressed(throwable2);
                }
                throw throwable;
            }
            ((ZipInputStream)closeable).close();
        }
        arrayList.sort(String::compareTo);
        closeable = new ByteArrayOutputStream();
        for (String string : arrayList) {
            ((OutputStream)closeable).write(string.getBytes(StandardCharsets.UTF_8));
            ((ByteArrayOutputStream)closeable).write(0);
            ((OutputStream)closeable).write((byte[])hashMap.get(string));
            if (!bl) continue;
        }
        return new byte[][]{BuildProof.sha().digest(byArray), BuildProof.sha().digest(((ByteArrayOutputStream)closeable).toByteArray())};
    }

    public static byte[] answer(byte[] byArray, byte[] byArray2, byte[] byArray3) {
        MessageDigest messageDigest = BuildProof.sha();
        messageDigest.update(byArray);
        messageDigest.update(byArray2);
        messageDigest.update(byArray3);
        return messageDigest.digest();
    }

    private static MessageDigest sha() {
        try {
            return MessageDigest.getInstance(a[2]);
        }
        catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new IllegalStateException(noSuchAlgorithmException);
        }
    }

    public static void main(String[] stringArray) throws IOException {
        block7: {
            boolean bl;
            block9: {
                block8: {
                    byte[][] byArray = BuildProof.fingerprint(Path.of(stringArray[0], new String[0]));
                    boolean bl2 = PredictionHandshake.a;
                    if (byArray == null) {
                        throw new IOException(a[4] + stringArray[0]);
                    }
                    HexFormat hexFormat = HexFormat.of();
                    Files.writeString(Path.of(stringArray[1], new String[0]), (CharSequence)(a[3] + hexFormat.formatHex(byArray[0]) + a[1] + hexFormat.formatHex(byArray[1]) + a[0]), new OpenOption[0]);
                    if (!Module.a) break block7;
                    if (!bl2) break block8;
                    bl = false;
                    break block9;
                }
                bl = true;
            }
            PredictionHandshake.a = bl;
        }
    }

    private static int lambda$fingerprint$0(JarEntry jarEntry, JarEntry jarEntry2) {
        return jarEntry.getName().compareTo(jarEntry2.getName());
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var7 = new String[5];
                    var5_1 = 0;
                    var4_2 = "@k\\n\u000f@g\u0001F%v\u001c\u0010\"D\u0017b\"H@\u00071\u0003`Ir-^";
                    var6_3 = "@k\\n\u000f@g\u0001F%v\u001c\u0010\"D\u0017b\"H@\u00071\u0003`Ir-^".length();
                    var3_4 = 4;
                    var2_5 = -1;
lbl7:

                    while (true) {
                        v0 = ++var2_5;
                        v1 = var4_2.substring(v0, v0 + var3_4);
                        v2 = -1;
                        break block19;
                        break;
                    }
lbl12:

                    while (true) {
                        var7[var5_1++] = v3.intern();
                        if ((var2_5 += var3_4) < var6_3) {
                            var3_4 = var4_2.charAt(var2_5);
                            continue;
                        }
                        var4_2 = "\u0019k\u0003\u00065q\u0004\u0006i\u001bDb\u000b\f$UD!8\u0002\u00039\u001bD";
                        var6_3 = "\u0019k\u0003\u00065q\u0004\u0006i\u001bDb\u000b\f$UD!8\u0002\u00039\u001bD".length();
                        var3_4 = 12;
                        var2_5 = -1;
lbl21:

                        while (true) {
                            v4 = ++var2_5;
                            v1 = var4_2.substring(v4, v4 + var3_4);
                            v2 = 0;
                            break block19;
                            break;
                        }
                        break;
                    }
lbl26:

                    while (true) {
                        var7[var5_1++] = v3.intern();
                        if ((var2_5 += var3_4) < var6_3) {
                            var3_4 = var4_2.charAt(var2_5);
                            continue;
                        }
                        break block20;
                        break;
                    }
                }
                v5 = v1.toCharArray();
                v6 = v5;
                v7 = v5.length;
                var8_6 = 0;
                do {
                    v6 = v6;
                    v8 = var8_6;
                    v9 = v6[v8];
                    switch (var8_6 % 7) {
                        case 0: {
                            v10 = 98;
                            break;
                        }
                        case 1: {
                            v10 = 75;
                            break;
                        }
                        case 2: {
                            v10 = 33;
                            break;
                        }
                        case 3: {
                            v10 = 100;
                            break;
                        }
                        case 4: {
                            v10 = 64;
                            break;
                        }
                        case 5: {
                            v10 = 24;
                            break;
                        }
                        default: {
                            v10 = 104;
                        }
                    }
                    v6[v8] = (char)(v9 ^ v10);
                    ++var8_6;
lbl65:

                    v7 = v7;
                } while (v7 > var8_6);
                v3 = new String(v6);
                switch (v2) {
                    default: {
                        continue;
                    }
                    case 0:
lbl73:

                    continue;
                }
            }
            BuildProof.a = var7;
            break block21;
lbl77:

            while (true) {
                continue;
                break;
            }
        }
        var0_7 = 152529082279276756L;
        while (true)
        BuildProof.b = 3973417755002876116L ^ var0_7;
    }
}
