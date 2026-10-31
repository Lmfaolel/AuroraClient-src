package dev.quelchi.aurora.gui;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import dev.quelchi.aurora.gui.PackMix$Entry;
import dev.quelchi.aurora.gui.PackMix$Look;
import dev.quelchi.aurora.gui.PackMix$Version;
import dev.quelchi.aurora.gui.PackTextures$Sprite;
import dev.quelchi.aurora.gui.PreviewScene;
import dev.quelchi.aurora.util.Daemon;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.LambdaMetafactory;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.resource.InputSupplier;
import net.minecraft.resource.PackVersion;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.metadata.PackResourceMetadata;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Range;

public final class PackMix {
    public static final Map<String, List<PackMix$Entry>> CATEGORIES;
    public static final Map<String, String> picks;
    public static final Map<String, String> pickNames;
    public static final Map<String, float[]> tints;
    public static final Map<String, Float> hands;
    public static final Map<String, String> HAND_MODELS;
    public static String editingFile;
    private static Map<PackMix$Entry, List<PackMix$Version>> loaded;
    private static final Map<Identifier, int[]> originals;
    private static String loadedCategory;
    private static int generation;
    private static final String[] a;
    private static final long[] b;
    private static final Integer[] c;

    private PackMix() {
    }

    private static PackMix$Entry item(String string, String ... stringArray) {
        return new PackMix$Entry(string, PackMix$Look.FLAT, List.of(stringArray));
    }

    public static List<PackMix$Version> versions(PackMix$Entry packMix$Entry) {
        return loaded.getOrDefault(packMix$Entry, List.of());
    }

    public static boolean ready(String string) {
        return string.equals(loadedCategory);
    }

    private static List<ResourcePackProfile> packs() {
        ArrayList<ResourcePackProfile> arrayList = new ArrayList<ResourcePackProfile>();
        Iterator iterator = MinecraftClient.method_1551().method_1520().method_14441().iterator();
        int n = PreviewScene.a;
        while (iterator.hasNext()) {
            block5: {
                ResourcePackProfile resourcePackProfile = (ResourcePackProfile)iterator.next();
                boolean bl = resourcePackProfile.method_14463().startsWith(a[50]);
                if (n != 0 || !bl) break block5;
                bl = arrayList.add(resourcePackProfile);
            }
            if (n == 0) continue;
        }
        return arrayList;
    }

    public static void load(String string) {
        int n = ++generation;
        List<PackMix$Entry> list = CATEGORIES.get(string);
        List<ResourcePackProfile> list2 = PackMix.packs();
        Daemon.run(a[34], () -> PackMix.lambda$load$1(list, list2, n, string));
    }

    
    private static void upload(String var0, Map<PackMix$Entry, List<Map.Entry<ResourcePackProfile, Map<String, byte[]>>>> var1_1) {
        PackMix.free();
        var2_2 = PreviewScene.a;
        var3_3 = MinecraftClient.method_1551().method_1531();
        var4_4 = new LinkedHashMap<PackMix$Entry, List<PackMix$Version>>();
        var5_5 = 0;
        var6_6 = var1_1.entrySet().iterator();
        while (var6_6.hasNext()) {
            v0 /* !! */  = var6_6.next();
            if (var2_2 == 0) {
                block20: {
                    var7_7 = v0 /* !! */ ;
                    var8_8 = new ArrayList<PackMix$Version>();
                    for (Map.Entry var10_10 : (List)var7_7.getValue()) {
                        block23: {
                            block22: {
                                var11_11 = new HashMap<String, PackTextures$Sprite>();
                                v1 = ((Map)var10_10.getValue()).entrySet();
                                if (var2_2 != 0) break block20;
                                var12_12 = v1.iterator();
                                while (var12_12.hasNext()) {
                                    block21: {
                                        v1 = (Map.Entry)var12_12.next();
                                        if (var2_2 != 0) break block20;
                                        var13_13 = v1;
                                        try {
                                            var14_14 = NativeImage.method_49277((byte[])((byte[])var13_13.getValue()));
                                        }
                                        catch (Exception var15_16) {
                                            continue;
                                        }
                                        var19_20 = PackMix.a;
                                        var15_15 = Identifier.method_60655((String)var19_20[21], (String)(var19_20[97] + var5_5++));
                                        var16_17 = new int[var14_14.method_4307() * var14_14.method_4323()];
                                        for (var17_18 = 0; var17_18 < var16_17.length; ++var17_18) {
                                            v2 = var16_17;
                                            if (var2_2 == 0) {
                                                v2[var17_18] = var14_14.method_61940(var17_18 % var14_14.method_4307(), var17_18 / var14_14.method_4307());
                                                if (var2_2 == 0) continue;
                                                break;
                                            }
                                            break block21;
                                        }
                                        v2 = PackMix.originals.put(var15_15, var16_17);
                                    }
                                    var3_3.method_4616(var15_15, (AbstractTexture)new NativeImageBackedTexture((Supplier<String>)LambdaMetafactory.metafactory(null, null, null, ()Ljava/lang/Object;, toString(), ()Ljava/lang/String;)((Identifier)var15_15), var14_14));
                                    v4 = var14_14.method_4307();
                                    if (var2_2 == 0) {
                                        var17_18 = Math.min(v4, var14_14.method_4323());
                                        v4 = var14_14.method_4323() > var14_14.method_4307() ? 1 : 0;
                                    }
                                    var18_19 = v4;
                                    v6 = var11_11;
                                    v7 = (String)var13_13.getKey();
                                    v8 = v9;
                                    v10 = v9;
                                    v11 = var15_15;
                                    v12 = var18_19 != 0 ? var17_18 : var14_14.method_4307();
                                    v8(v11, v12, var18_19 != 0 ? var17_18 : var14_14.method_4323(), var14_14.method_4307(), var14_14.method_4323());
                                    v6.put(v7, v10);
                                    if (var2_2 == 0) continue;
                                }
                                v14 /* !! */  = var11_11;
                                if (var2_2 != 0) break block22;
                                if (v14 /* !! */ .isEmpty()) break block23;
                                v14 /* !! */  = var10_10.getKey();
                            }
                            var12_12 = (ResourcePackProfile)v14 /* !! */ ;
                            var8_8.add(new PackMix$Version(var12_12.method_14463(), var12_12.method_14457().getString(), var11_11));
                        }
                        if (var2_2 == 0) continue;
                    }
                    v1 = var4_4.put((PackMix$Entry)var7_7.getKey(), var8_8);
                }
                if (var2_2 == 0) continue;
            }
        }
        PackMix.loaded = var4_4;
        PackMix.loadedCategory = var0;
        for (Map.Entry<PackMix$Entry, List<Map.Entry<ResourcePackProfile, Map<String, byte[]>>>> v0 : var4_4.keySet()) lbl_neg_1000:

        {
            var7_7 = (PackMix$Entry)v0 /* !! */ ;
            PackMix.retint(var7_7.label());
            if (var2_2 == 0) continue;
        }
    }

    
    public static void retint(String var0) {
        var2_1 = MinecraftClient.method_1551().method_1531();
        var3_2 = PackMix.loaded.entrySet().iterator();
        var1_3 = PreviewScene.a;
        block16: while (var3_2.hasNext()) {
            var4_4 = var3_2.next();
            v0 = var4_4;
            if (var1_3 == 0) {
                v1 = v0.getKey();
            }
            block17: while (v1.label().equals(var0)) {
                v0 = PackMix.tints.get(var0);
lbl16:

                var5_5 = (float[])v0;
                for (Object v1 : var4_4.getValue()) {
                    block25: {
                        block24: {
                            if (var1_3 != 0) continue block17;
                            var7_7 = v1;
                            if (var5_5 == null) break block24;
                            v3 = var7_7.packId().equals(PackMix.picks.get(var0));
                            if (var1_3 != 0) break block25;
                            if (!v3) break block24;
                            v3 = true;
                            break block25;
                        }
                        v3 = false;
                    }
                    var8_8 = v3;
                    for (PackTextures$Sprite var10_10 : var7_7.sprites().values()) {
                        block27: {
                            block26: {
                                v1 = PackMix.originals;
                                if (var1_3 != 0) continue block17;
                                var11_11 = (int[])v1.get(var10_10.id());
                                var13_13 = var2_1.method_4619(var10_10.id());
                                v6 = var13_13;
                                if (var1_3 != 0) break block26;
                                if (!(v6 instanceof NativeImageBackedTexture)) continue;
                                v6 = var13_13;
                            }
                            var12_12 = (NativeImageBackedTexture)v6;
                            if (var11_11 == null) {
                                continue;
                            }
                            var13_13 = var12_12.method_4525();
                            v9 = var13_13;
                            if (var1_3 != 0) break block27;
                            if (v9 == null) {
                                continue;
                            }
                            v9 = var13_13;
                        }
                        var14_14 = v9.method_4307();
                        for (var15_15 = 0; var15_15 < var11_11.length; ++var15_15) {
                            block28: {
                                block29: {
                                    v1 = var13_13;
                                    if (var1_3 != 0) continue block17;
                                    v11 = var15_15 % var14_14;
                                    v12 = var15_15;
                                    v13 = var14_14;
                                    if (var1_3 != 0) break block28;
                                    v12 = v12 / v13;
                                    if (!var8_8) break block29;
                                    v13 = PackMix.tint(var11_11[var15_15], var5_5);
                                    break block28;
                                }
                                v13 = var11_11[var15_15];
                            }
                            v1.method_61941(v11, v12, v13);
                            if (var1_3 == 0) continue;
                        }
                        var12_12.method_4524();
                        if (var1_3 == 0) continue;
                    }
                    if (var1_3 == 0) continue;
                }
                if (var1_3 == 0) continue block16;
            }
        }
    }

    public static int tint(int n, float[] fArray) {
        int n2;
        block4: {
            block5: {
                int n3 = PreviewScene.a;
                n2 = n;
                if (n3 != 0) break block4;
                if (n2 >>> 24 != 0) break block5;
                return n;
            }
            n2 = n >> 16 & 0xFF;
        }
        float[] fArray2 = Color.RGBtoHSB(n2, n >> 8 & 0xFF, n & 0xFF, null);
        float f = fArray2[0] + fArray[0];
        float f2 = Math.min(1.0f, fArray2[1] * fArray[1]);
        return n & PackMix.a(26191, 1247101675235020949L) | Color.HSBtoRGB(f - (float)Math.floor(f), f2, fArray2[2]) & PackMix.a(9643, 2010423772041302896L);
    }

    
    public static void free() {
        TextureManager textureManager = MinecraftClient.method_1551().method_1531();
        Iterator<List<PackMix$Version>> iterator = loaded.values().iterator();
        int n = PreviewScene.a;
        while (iterator.hasNext()) {
            Object object = iterator.next();
            block1: while (true) {
                List<PackMix$Version> list = object;
                if (n != 0) return;
                Iterator<PackMix$Version> iterator2 = list.iterator();
                block2: while (true) {
                    Iterator<Record> iterator3 = iterator2;
                    block3: while (iterator3.hasNext()) {
                        object = iterator2.next();
                        if (n != 0) continue block1;
                        Object object2 = object;
                        Iterator<PackTextures$Sprite> iterator4 = ((PackMix$Version)object2).sprites().values().iterator();
                        while (iterator4.hasNext()) {
                            iterator3 = iterator4;
                            if (n != 0) continue block3;
                            PackTextures$Sprite packTextures$Sprite = (PackTextures$Sprite)iterator3.next();
                            textureManager.method_4615(packTextures$Sprite.id());
                            if (n == 0) continue;
                        }
                        if (n == 0) continue block2;
                    }
                    break block1;
                    break;
                }
                break;
            }
            if (n == 0) continue;
        }
        loaded = Map.of();
        loadedCategory = null;
        originals.clear();
    }

    public static void close() {
        ++generation;
        PackMix.free();
    }

    public static String fileName(String string) {
        return string.replaceAll(a[1], "").trim();
    }

    public static void export(String string, Formatting formatting, Consumer<String> consumer) {
        HashMap hashMap;
        Object object;
        Object object2;
        Object object32;
        HashMap<String, float[]> hashMap2;
        LinkedHashMap linkedHashMap;
        boolean bl;
        Path path;
        String string2;
        String[] stringArray;
        MinecraftClient minecraftClient;
        block40: {
            int n;
            block37: {
                boolean bl2;
                block36: {
                    Object object4;
                    block35: {
                        Formatting formatting2;
                        block33: {
                            block34: {
                                minecraftClient = MinecraftClient.method_1551();
                                n = PreviewScene.a;
                                formatting2 = formatting;
                                if (n != 0) break block33;
                                if (formatting2 != Formatting.field_1068) break block34;
                                object4 = "";
                                break block35;
                            }
                            formatting2 = formatting;
                        }
                        object4 = "\u00a7" + formatting2.method_36145();
                    }
                    String string3 = object4;
                    stringArray = a;
                    string2 = string3 + PackMix.fileName(string) + stringArray[26];
                    path = minecraftClient.method_1479().resolve(string2);
                    bl = string2.equals(editingFile);
                    bl2 = bl;
                    if (n != 0) break block36;
                    if (bl2) break block37;
                    bl2 = Files.exists(path, new LinkOption[0]);
                }
                if (bl2) {
                    consumer.accept(a[52]);
                    return;
                }
            }
            HashMap<String, ResourcePackProfile> hashMap22 = new HashMap<String, ResourcePackProfile>();
            for (ResourcePackProfile object62 : PackMix.packs()) {
                hashMap22.put(object62.method_14463(), object62);
                if (n == 0) continue;
            }
            linkedHashMap = new LinkedHashMap();
            hashMap2 = new HashMap<String, float[]>();
            block25: for (List<PackMix$Entry> list : CATEGORIES.values()) {
                block26: for (Object object32 : list) {
                    block38: {
                        if (n != 0) continue block25;
                        object2 = (ResourcePackProfile)hashMap22.get(picks.get(((PackMix$Entry)object32).label()));
                        if (n != 0) break block38;
                        if (object2 == null) {
                            continue;
                        }
                        linkedHashMap.computeIfAbsent(object2, PackMix::lambda$export$2).addAll(((PackMix$Entry)object32).files());
                    }
                    if ((object = tints.get(((PackMix$Entry)object32).label())) != null) {
                        for (String string3 : ((PackMix$Entry)object32).files()) {
                            hashMap2.put(string3, (float[])object.clone());
                            if (n != 0) continue block26;
                            if (n == 0) continue;
                        }
                    }
                    if (n == 0) continue;
                }
                if (n == 0) continue;
            }
            hashMap = new HashMap();
            for (Map.Entry<String, String> entry : HAND_MODELS.entrySet()) {
                block41: {
                    object32 = hands.get(entry.getKey());
                    if (n != 0) break block40;
                    if (object32 == null) break block41;
                    Object object5 = picks;
                    if (n != 0) break block41;
                    if (!object5.containsKey(entry.getKey())) break block41;
                    object5 = hashMap.put(entry.getValue(), PackMix.handModel(entry.getValue(), ((Float)object32).floatValue()));
                }
                if (n == 0) continue;
            }
            if (linkedHashMap.isEmpty()) {
                consumer.accept(a[18]);
                return;
            }
        }
        PackVersion packVersion = SharedConstants.method_16673().method_70592(ResourceType.field_14188);
        PackResourceMetadata packResourceMetadata = new PackResourceMetadata((Text)Text.method_43470((String)PackMix.fileName(string)).method_27692(formatting), new Range((Comparable)packVersion));
        object32 = new JsonObject();
        stringArray = a;
        object32.add(stringArray[64], (JsonElement)PackResourceMetadata.field_61155.comp_3437().encodeStart((DynamicOps)JsonOps.INSTANCE, (Object)packResourceMetadata).getOrThrow());
        object2 = new GsonBuilder().setPrettyPrinting().create().toJson((JsonElement)object32).getBytes(StandardCharsets.UTF_8);
        object = stringArray[85] + string2;
        Daemon.run(stringArray[22], () -> PackMix.lambda$export$5((byte[])object2, hashMap, linkedHashMap, hashMap2, minecraftClient, consumer, bl, path, string2, (String)object));
    }

    private static byte[] handModel(String string, float f) {
        String string2;
        float f2;
        float f3 = f2 = 0.68f * f;
        float f4 = f2;
        float f5 = f2;
        String[] stringArray = a;
        String string3 = string2 = stringArray[57] + f5 + stringArray[69] + f4 + stringArray[39] + f3 + stringArray[91];
        String string4 = string2;
        String string5 = string;
        return (stringArray[104] + string5 + stringArray[72] + string4 + stringArray[81] + string3 + stringArray[56]).getBytes(StandardCharsets.UTF_8);
    }

    private static byte[] tinted(byte[] byArray, float[] fArray) throws IOException {
        BufferedImage bufferedImage;
        int n;
        BufferedImage bufferedImage2;
        block9: {
            block10: {
                bufferedImage2 = ImageIO.read(new ByteArrayInputStream(byArray));
                n = PreviewScene.a;
                bufferedImage = bufferedImage2;
                if (n != 0) break block9;
                if (bufferedImage != null) break block10;
                return byArray;
            }
            bufferedImage = bufferedImage2;
        }
        int n2 = bufferedImage.getWidth();
        int n3 = bufferedImage2.getHeight();
        BufferedImage bufferedImage3 = new BufferedImage(n2, n3, 2);
        int n4 = 0;
        while (n4 < n3) {
            block11: {
                for (int i = 0; i < n2; ++i) {
                    bufferedImage3.setRGB(i, n4, PackMix.tint(bufferedImage2.getRGB(i, n4), fArray));
                    if (n == 0) {
                        if (n == 0) continue;
                        break;
                    }
                    break block11;
                }
                ++n4;
            }
            if (n == 0) continue;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write((RenderedImage)bufferedImage3, a[66], byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private static void put(ZipOutputStream zipOutputStream, String string, byte[] byArray) throws IOException {
        zipOutputStream.putNextEntry(new ZipEntry(string));
        zipOutputStream.write(byArray);
        zipOutputStream.closeEntry();
    }

    private static Identifier texture(String string) {
        String[] stringArray = a;
        return Identifier.method_60656((String)(stringArray[31] + string + stringArray[98]));
    }

    
    private static byte[] read(InputSupplier<InputStream> var0) {
        block17: {
            block16: {
                block15: {
                    block19: {
                        var1_1 = PreviewScene.a;
                        v0 /* !! */  = var0;
                        if (var1_1 != 0) // goto
                        if (v0 /* !! */  != null) break block15;
                        break block19;
                        catch (Throwable v1) {
                            throw v1;
                        }
                    }
                    return null;
                }
                v0 /* !! */  = var0.get();
lbl17:

                var2_2 = (InputStream)v0 /* !! */ ;
                try {
                    var3_4 = var2_2.readAllBytes();
                }
                catch (Throwable var3_5) {
                    try {
                        block18: {
                            if (var1_1 != 0 || var2_2 == null) break block18;
                            try {
                                var2_2.close();
                            }
                            catch (Throwable var4_6) {
                                var3_5.addSuppressed(var4_6);
                            }
                        }
                        throw var3_5;
                    }
                    catch (Exception var2_3) {
                        return null;
                    }
                }
                v4 = var2_2;
                if (var1_1 != 0) break block16;
                if (v4 == null) break block17;
                v4 = var2_2;
            }
            v4.close();
        }
        return var3_4;
    }

    
    private static void lambda$export$5(byte[] var0, Map var1_1, Map var2_2, Map var3_3, MinecraftClient var4_4, Consumer var5_5, boolean var6_6, Path var7_7, String var8_8, String var9_9) {
        block44: {
            block37: {
                var11_10 = null;
                var12_11 = new ByteArrayOutputStream();
                var10_12 = PreviewScene.a;
                try {
                    var13_13 = new ZipOutputStream(var12_11);
                    try {
                        var23_16 = PackMix.a;
                        PackMix.put((ZipOutputStream)var13_13, var23_16[0], var0);
                        var14_17 = var1_1.entrySet().iterator();
                        while (var14_17.hasNext()) {
                            v0 = var14_17.next();
                            if (var10_12 == 0) {
                                var15_19 = v0;
                                var23_16 = PackMix.a;
                                PackMix.put((ZipOutputStream)var13_13, var23_16[61] + (String)var15_19.getKey() + var23_16[70], (byte[])var15_19.getValue());
                                if (var10_12 == 0) continue;
                            }
                        }
                        var14_17 = var2_2.entrySet().iterator();
                        while (var14_17.hasNext()) {
                            block42: {
                                block41: {
                                    v0 = var15_19 = var14_17.next();
lbl21:

                                    if (var10_12 != 0) break block37;
                                    var16_21 = ((ResourcePackProfile)var15_19.getKey()).method_14458();
                                    try {
                                        v1 /* !! */  = var15_19.getValue();
                                        block32: while (true) {
                                            var17_22 = ((List)v1 /* !! */ ).iterator();
                                            while (true) {
                                                block39: {
                                                    block40: {
                                                        block38: {
                                                            if (!var17_22.hasNext()) break;
                                                            v1 /* !! */  = var17_22.next();
                                                            if (var10_12 != 0) continue block32;
                                                            var18_24 = (String)v1 /* !! */ ;
                                                            var19_26 = PackMix.read((InputSupplier<InputStream>)var16_21.method_14405(ResourceType.field_14188, PackMix.texture(var18_24)));
                                                            v3 /* !! */  = var19_26;
                                                            if (var10_12 != 0) break block38;
                                                            if (v3 /* !! */  == null) {
                                                                continue;
                                                            }
                                                            v3 /* !! */  = (byte[])var3_3.get(var18_24);
                                                        }
                                                        var20_27 = v3 /* !! */ ;
                                                        if (var10_12 != 0) break block39;
                                                        if (var20_27 == null) break block40;
                                                        var19_26 = PackMix.tinted(var19_26, var20_27);
                                                    }
                                                    var23_16 = PackMix.a;
                                                    PackMix.put((ZipOutputStream)var13_13, var23_16[53] + var18_24 + var23_16[46], var19_26);
                                                }
                                                var23_16 = PackMix.a;
                                                var21_28 = Identifier.method_60656((String)(var23_16[3] + var18_24 + var23_16[63]));
                                                var22_29 = PackMix.read((InputSupplier<InputStream>)var16_21.method_14405(ResourceType.field_14188, var21_28));
                                                if (var22_29 != null) {
                                                    PackMix.put((ZipOutputStream)var13_13, var23_16[87] + var18_24 + var23_16[88], var22_29);
                                                }
                                                if (var10_12 != 0) break;
                                            }
                                            break;
                                        }
                                    }
                                    catch (Throwable var17_23) {
                                        block43: {
                                            if (var10_12 != 0 || var16_21 == null) break block43;
                                            try {
                                                var16_21.close();
                                            }
                                            catch (Throwable var18_25) {
                                                var17_23.addSuppressed(var18_25);
                                            }
                                        }
                                        throw var17_23;
                                    }
                                    v8 = var16_21;
                                    if (var10_12 != 0) break block41;
                                    if (v8 == null) break block42;
                                    v8 = var16_21;
                                }
                                v8.close();
                            }
                            if (var10_12 == 0) continue;
                        }
                    }
                    catch (Throwable var14_18) {
                        try {
                            var13_13.close();
                        }
                        catch (Throwable var15_20) {
                            var14_18.addSuppressed(var15_20);
                        }
                        throw var14_18;
                    }
                    var13_13.close();
                }
                catch (Exception var13_14) {
                    var4_4.execute((Runnable)LambdaMetafactory.metafactory(null, null, null, ()V, lambda$export$3(java.util.function.Consumer ), ()V)((Consumer)var5_5));
                    return;
                }
            }
            try {
                block45: {
                    if (!var6_6) // goto
                    Files.write(var7_7, var12_11.toByteArray(), new OpenOption[]{StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE});
                    if (var10_12 == 0) break block44;
                    break block45;
                    catch (Throwable v10) {
                        throw v10;
                    }
                }
                Files.write(var7_7, var12_11.toByteArray(), new OpenOption[]{StandardOpenOption.CREATE_NEW});
            }
            catch (Exception var13_15) {
                v12 = var6_6 != false ? PackMix.a[14] : PackMix.a[19];
                var11_10 = v12;
            }
        }
        var13_13 = var11_10;
        var4_4.execute((Runnable)LambdaMetafactory.metafactory(null, null, null, ()V, lambda$export$4(java.lang.String java.lang.String boolean net.minecraft.client.MinecraftClient java.lang.String java.util.function.Consumer ), ()V)((String)var13_13, (String)var8_8, (boolean)var6_6, (MinecraftClient)var4_4, (String)var9_9, (Consumer)var5_5));
    }

    private static void lambda$export$4(String string, String string2, boolean bl, MinecraftClient minecraftClient, String string3, Consumer consumer) {
        block12: {
            block11: {
                MinecraftClient minecraftClient2;
                block13: {
                    String string4;
                    int n;
                    block10: {
                        n = PreviewScene.a;
                        string4 = string;
                        if (n != 0) break block10;
                        if (string4 != null) break block11;
                        string4 = string2;
                    }
                    editingFile = string4;
                    if (n != 0) break block12;
                    if (!bl) break block11;
                    minecraftClient2 = minecraftClient;
                    if (n != 0) break block13;
                    if (!minecraftClient2.method_1520().method_29210().contains(string3)) break block11;
                    minecraftClient2 = minecraftClient;
                }
                minecraftClient2.method_1521();
            }
            consumer.accept(string);
        }
    }

    private static void lambda$export$3(Consumer consumer) {
        consumer.accept(a[29]);
    }

    private static List lambda$export$2(ResourcePackProfile resourcePackProfile) {
        return new ArrayList();
    }

    
    private static void lambda$load$1(List list, List list2, int n, String string) {
        PackMix$Entry packMix$Entry;
        Iterator iterator;
        int n2;
        LinkedHashMap linkedHashMap;
        block24: {
            Iterator iterator2;
            linkedHashMap = new LinkedHashMap();
            n2 = PreviewScene.a;
            iterator = list.iterator();
            while (iterator.hasNext()) {
                iterator2 = iterator;
                if (n2 == 0) {
                    packMix$Entry = (PackMix$Entry)iterator2.next();
                    linkedHashMap.put(packMix$Entry, new ArrayList());
                    if (n2 == 0) continue;
                }
                break block24;
            }
            iterator2 = iterator = list2.iterator();
        }
        while (iterator.hasNext()) {
            block25: {
                packMix$Entry = (ResourcePackProfile)iterator.next();
                if (n2 != 0) return;
                if (n != generation) {
                    return;
                }
                try {
                    ResourcePack resourcePack;
                    block26: {
                        ResourcePack resourcePack2 = packMix$Entry.method_14458();
                        try {
                            Iterator iterator3 = list.iterator();
                            block16: while (true) {
                                Iterator<Object> iterator4 = iterator3;
                                block17: while (iterator4.hasNext()) {
                                    PackMix$Entry packMix$Entry2 = (PackMix$Entry)iterator3.next();
                                    LinkedHashMap<String, byte[]> linkedHashMap3 = new LinkedHashMap<String, byte[]>();
                                    if (n2 != 0) break block25;
                                    Iterator<String> iterator5 = packMix$Entry2.files().iterator();
                                    while (iterator5.hasNext()) {
                                        byte[] byArray2;
                                        iterator4 = iterator5;
                                        if (n2 != 0) continue block17;
                                        String string2 = (String)iterator4.next();
                                        byte[] byArray = byArray2 = PackMix.read((InputSupplier<InputStream>)resourcePack2.method_14405(ResourceType.field_14188, PackMix.texture(string2)));
                                        if (n2 == 0 && byArray != null) {
                                            byArray = linkedHashMap3.put(string2, byArray2);
                                        }
                                        if (n2 == 0) continue;
                                    }
                                    LinkedHashMap<String, Object> linkedHashMap2 = linkedHashMap3;
                                    if (n2 == 0) {
                                        if (linkedHashMap2.isEmpty()) continue block16;
                                        linkedHashMap2 = linkedHashMap.get(packMix$Entry2);
                                    }
                                    ((List)((Object)linkedHashMap2)).add(Map.entry(packMix$Entry, linkedHashMap3));
                                    if (n2 == 0) continue block16;
                                }
                                break;
                            }
                            resourcePack = resourcePack2;
                            if (n2 != 0) break block26;
                        }
                        catch (Throwable throwable) {
                            if (n2 != 0) throw throwable;
                            if (resourcePack2 == null) throw throwable;
                            try {
                                resourcePack2.close();
                                throw throwable;
                            }
                            catch (Throwable throwable2) {
                                throwable.addSuppressed(throwable2);
                            }
                            throw throwable;
                        }
                        if (resourcePack == null) break block25;
                        resourcePack = resourcePack2;
                    }
                    resourcePack.close();
                }
                catch (Exception exception) {
                }
            }
            if (n2 == 0) continue;
        }
        MinecraftClient.method_1551().execute(() -> PackMix.lambda$load$0(n, string, linkedHashMap));
    }

    private static void lambda$load$0(int n, String string, Map map) {
        if (n == generation) {
            PackMix.upload(string, map);
        }
    }

    
    static {
        block29: {
            block28: {
                block27: {
                    block26: {
                        var13 = new String[105];
                        var11_1 = 0;
                        var10_2 = "Z;n)\\\u001d\u001bG?y#\fq\u0006QmHZG\bf3>/\tc4{'\u001c\u0004\u0017X#\t^?u6\u0007\u0002\u001dYu\u0006B?a/\u0017\u0004\fi(t1\u0006\u0011\u0014\n3y'\u001f\u0007Z3n)\u0013\b\u001d\fH6b!\u0019_\u001eC(h\u001dC\u0018M/dm\u0011\u001f\u0016^;d,\u0017\u0002WM?c'\u0000\u0019\u001buo9\ty5x.R\u0016\u0011X?\u001bH6b!\u0019_\nO)}#\u0005\u001e'K4n*\u001d\u0002'H5y6\u001d\u001d\u0005~5y'\u001f\u001eO4y+\u0006\tWO4i\u001d\u0011\u0002\u0001Y.l.]\u0015\u0016N\u0005n0\u000b\u0003\fK6\u0004l3\u007f'7i5x.\u0016\u001e_^zz0\u001b\u0004\u001d\n5{'\u0000P\u0011^v-6\u0007\u0002\u0016\n.e'R\u0000\u0019I1--\u0014\u0016XK4ib\u0006\u0002\u0001\n;j#\u001b\u001e\tC.h/]\u001d\u0019I?\u0007N3l/\u001d\u001e\u001c\u0011H6b!\u0019_\u000bE/a\u001d\u0014\u0019\nO\u0005<\u0014~2b1\u0017P\bK9f1R\u0011\nOzj-\u001c\u0015\nd;`'R\u0004\u0019A?c\tm6b5\u0001\u0004\u0017D?\u0006K/\u007f-\u0000\u0011\u0012K/\u007f-\u0000\u0011UZ;n)_\u0015\u0000Z5\u007f6\fm5a&\u0017\u001eXK*}.\u0017\u0005H5b6\u0001\tD?y*\u0017\u0002\u0011^?\u0004\u0004 d2\u0019H6b!\u0019_\nO)}#\u0005\u001e'K4n*\u001d\u0002'Y3i'\u0012O4y+\u0006\tWY2d'\u001e\u0014'H;~'\ro\"}-\u0000\u0004XL;d.\u0017\u0014\bi(t1\u0006\u0011\u0014Y\t^?u6\u0007\u0002\u001dYu\u000ex?~2\u0013\u0007\u0016\n;c!\u001a\u001f\n\u0006k(`-\u0007\u0002\u000fK/\u007f-\u0000\u0011UZ;n)_\u001d\u0011R\bF?j%\u001b\u001e\u001fY\u0004\n)h6\u0005~5y'\u001f\u000fH6b!\u0019_\u001fF5z1\u0006\u001f\u0016O\u0002\u0006z\u0007y2d'\u001e\u0014\u000b\u000bo4i'\u0000P\bO;\u007f.\u0003K\"h\tm6b5\u0001\u0004\u0017D?\nI2h1\u0006\u0000\u0014K.h\u000eH6b!\u0019_\u0017H)d&\u001b\u0011\u0016\u0004\u0004*c%\u001aH6b!\u0019_\nO)}#\u0005\u001e'K4n*\u001d\u0002'Y3i'B\u0010^5y'\u001f/\u0017L\u0005x,\u0016\t\u0011D=\u0005Y-b0\u0016\u0005L3a']\u0005i2h1\u0006\nd;`'R\u0004\u0019A?c\u001aK)~'\u0006\u0003WG3c'\u0011\u0002\u0019L.\"6\u0017\b\f_(h1]\u0013C.h/]\u0017\u0014E-~6\u001d\u001e\u001du>x1\u0006\be8~+\u0016\u0019\u0019D\u0007 z-?x\rrHQz/0\u001d\u0004\u0019^3b,PJXqj!b_IH\u0006z?w/\\X\b.\u007f#\u001c\u0003\u0014K.d-\u001cRB\n\u0001<lCCT\ni#p^PI\u0004k>\u001f^PZY9l.\u0017RB\n\u0001\u000bo4ib\u0011\u0002\u0001Y.l.\u0004l3\u007f'\u0011H6b!\u0019_\u000bE/a\u001d\u0014\u0019\nO\u0005=\u001dK)~'\u0006\u0003WG3c'\u0011\u0002\u0019L.\"/\u001d\u0014\u001dF)\"+\u0006\u0015\u0015\u0005 M/dm\u0001\u0000\nC.h1]\u0018\rNue-\u0006\u0012\u0019X\u0005~'\u001e\u0015\u001b^3b,\u000b\u0004*c%\\\u001d\u001bG?y#\u0004Z;n)\u001cH6b!\u0019_\nO)}#\u0005\u001e'K4n*\u001d\u0002'^5}\u001d\u001d\u0016\u001e\u0003Z4j\u001cO4y+\u0006\tWY2d'\u001e\u0014'H;~'-\u001e\u0017Z;y6\u0017\u0002\u0016\u0017M/dm\u0011\u001f\u0016^;d,\u0017\u0002WC4{'\u001c\u0004\u0017X#\u0002\u0006z\u0005\u00040~-\u001c\u0019M/dm\u0011\u001f\u0016^;d,\u0017\u0002WY2x.\u0019\u0015\nu8b:1\bzpnxPX\b>d1\u0002\u001c\u0019Sx7b\tzX\nz-`\u0014\u0019\nY.}'\u0000\u0003\u0017D\u0005\u007f+\u0015\u0018\fB;c&PJX\fM5a&\u0017\u001e'K*}.\u0017\u0006b5y \u0013\u0002\fH6b!\u0019_\u001eC(h\u001dB\u0015C.h/]\u0004\u0017^?`\u001d\u001d\u0016'_4i;\u001b\u001e\u001f\u0011C.h/]\u0017\u0017F>h,-\u0011\bZ6h#O4y+\u0006\tWO+x+\u0002\u001d\u001dD.\"*\u0007\u001d\u0019D5d&-\u001c\u001dM=d,\u0015\u0003W\be8~+\u0016\u0019\u0019D\u001aO4y+\u0006\tWO+x+\u0002\u001d\u001dD.\"*\u0007\u001d\u0019D5d&]\u001e\u0006P-bRPZL3\u007f1\u0006\u0000\u001dX)b,-\u001c\u001dL.e#\u001c\u0014Z\u0010z\tc4{'\u001c\u0004\u0017X#\fm5a&\u0017\u001eXK*}.\u0017\u0006y2d'\u001e\u0014\u0005L3a']\u000em6b5\u0001\u0004\u0017D?-&\u0007\u0003\f\u001aK)~'\u0006\u0003WG3c'\u0011\u0002\u0019L.\"6\u0017\b\f_(h1]\u000b\u0004*c%\\\u001d\u001bG?y#\u0005z?l0\u001e\rm5a&\u0017\u001eXK*}.\u0017\u0003\u0003wzp\u0018H6b!\u0019_\nO)}#\u0005\u001e'K4n*\u001d\u0002'^5}\u0010C.h/]\u0015\u0016N?\u007f\u001d\u0002\u0015\u0019X6\u0004g;n'\u0010C.h/]\u0015\u0016N\u0005n0\u000b\u0003\fK6\u0005C.h/]\tZ;n)-\u001d\u0011Ru\u0004\u0004*c%\u000by2x.\u0019\u0015\n\n8b:\u0007k4n*\u001d\u0002\u000b\u0005C.h/]\u0005~5b.\u0001";
                        var12_3 = "Z;n)\\\u001d\u001bG?y#\fq\u0006QmHZG\bf3>/\tc4{'\u001c\u0004\u0017X#\t^?u6\u0007\u0002\u001dYu\u0006B?a/\u0017\u0004\fi(t1\u0006\u0011\u0014\n3y'\u001f\u0007Z3n)\u0013\b\u001d\fH6b!\u0019_\u001eC(h\u001dC\u0018M/dm\u0011\u001f\u0016^;d,\u0017\u0002WM?c'\u0000\u0019\u001buo9\ty5x.R\u0016\u0011X?\u001bH6b!\u0019_\nO)}#\u0005\u001e'K4n*\u001d\u0002'H5y6\u001d\u001d\u0005~5y'\u001f\u001eO4y+\u0006\tWO4i\u001d\u0011\u0002\u0001Y.l.]\u0015\u0016N\u0005n0\u000b\u0003\fK6\u0004l3\u007f'7i5x.\u0016\u001e_^zz0\u001b\u0004\u001d\n5{'\u0000P\u0011^v-6\u0007\u0002\u0016\n.e'R\u0000\u0019I1--\u0014\u0016XK4ib\u0006\u0002\u0001\n;j#\u001b\u001e\tC.h/]\u001d\u0019I?\u0007N3l/\u001d\u001e\u001c\u0011H6b!\u0019_\u000bE/a\u001d\u0014\u0019\nO\u0005<\u0014~2b1\u0017P\bK9f1R\u0011\nOzj-\u001c\u0015\nd;`'R\u0004\u0019A?c\tm6b5\u0001\u0004\u0017D?\u0006K/\u007f-\u0000\u0011\u0012K/\u007f-\u0000\u0011UZ;n)_\u0015\u0000Z5\u007f6\fm5a&\u0017\u001eXK*}.\u0017\u0005H5b6\u0001\tD?y*\u0017\u0002\u0011^?\u0004\u0004 d2\u0019H6b!\u0019_\nO)}#\u0005\u001e'K4n*\u001d\u0002'Y3i'\u0012O4y+\u0006\tWY2d'\u001e\u0014'H;~'\ro\"}-\u0000\u0004XL;d.\u0017\u0014\bi(t1\u0006\u0011\u0014Y\t^?u6\u0007\u0002\u001dYu\u000ex?~2\u0013\u0007\u0016\n;c!\u001a\u001f\n\u0006k(`-\u0007\u0002\u000fK/\u007f-\u0000\u0011UZ;n)_\u001d\u0011R\bF?j%\u001b\u001e\u001fY\u0004\n)h6\u0005~5y'\u001f\u000fH6b!\u0019_\u001fF5z1\u0006\u001f\u0016O\u0002\u0006z\u0007y2d'\u001e\u0014\u000b\u000bo4i'\u0000P\bO;\u007f.\u0003K\"h\tm6b5\u0001\u0004\u0017D?\nI2h1\u0006\u0000\u0014K.h\u000eH6b!\u0019_\u0017H)d&\u001b\u0011\u0016\u0004\u0004*c%\u001aH6b!\u0019_\nO)}#\u0005\u001e'K4n*\u001d\u0002'Y3i'B\u0010^5y'\u001f/\u0017L\u0005x,\u0016\t\u0011D=\u0005Y-b0\u0016\u0005L3a']\u0005i2h1\u0006\nd;`'R\u0004\u0019A?c\u001aK)~'\u0006\u0003WG3c'\u0011\u0002\u0019L.\"6\u0017\b\f_(h1]\u0013C.h/]\u0017\u0014E-~6\u001d\u001e\u001du>x1\u0006\be8~+\u0016\u0019\u0019D\u0007 z-?x\rrHQz/0\u001d\u0004\u0019^3b,PJXqj!b_IH\u0006z?w/\\X\b.\u007f#\u001c\u0003\u0014K.d-\u001cRB\n\u0001<lCCT\ni#p^PI\u0004k>\u001f^PZY9l.\u0017RB\n\u0001\u000bo4ib\u0011\u0002\u0001Y.l.\u0004l3\u007f'\u0011H6b!\u0019_\u000bE/a\u001d\u0014\u0019\nO\u0005=\u001dK)~'\u0006\u0003WG3c'\u0011\u0002\u0019L.\"/\u001d\u0014\u001dF)\"+\u0006\u0015\u0015\u0005 M/dm\u0001\u0000\nC.h1]\u0018\rNue-\u0006\u0012\u0019X\u0005~'\u001e\u0015\u001b^3b,\u000b\u0004*c%\\\u001d\u001bG?y#\u0004Z;n)\u001cH6b!\u0019_\nO)}#\u0005\u001e'K4n*\u001d\u0002'^5}\u001d\u001d\u0016\u001e\u0003Z4j\u001cO4y+\u0006\tWY2d'\u001e\u0014'H;~'-\u001e\u0017Z;y6\u0017\u0002\u0016\u0017M/dm\u0011\u001f\u0016^;d,\u0017\u0002WC4{'\u001c\u0004\u0017X#\u0002\u0006z\u0005\u00040~-\u001c\u0019M/dm\u0011\u001f\u0016^;d,\u0017\u0002WY2x.\u0019\u0015\nu8b:1\bzpnxPX\b>d1\u0002\u001c\u0019Sx7b\tzX\nz-`\u0014\u0019\nY.}'\u0000\u0003\u0017D\u0005\u007f+\u0015\u0018\fB;c&PJX\fM5a&\u0017\u001e'K*}.\u0017\u0006b5y \u0013\u0002\fH6b!\u0019_\u001eC(h\u001dB\u0015C.h/]\u0004\u0017^?`\u001d\u001d\u0016'_4i;\u001b\u001e\u001f\u0011C.h/]\u0017\u0017F>h,-\u0011\bZ6h#O4y+\u0006\tWO+x+\u0002\u001d\u001dD.\"*\u0007\u001d\u0019D5d&-\u001c\u001dM=d,\u0015\u0003W\be8~+\u0016\u0019\u0019D\u001aO4y+\u0006\tWO+x+\u0002\u001d\u001dD.\"*\u0007\u001d\u0019D5d&]\u001e\u0006P-bRPZL3\u007f1\u0006\u0000\u001dX)b,-\u001c\u001dL.e#\u001c\u0014Z\u0010z\tc4{'\u001c\u0004\u0017X#\fm5a&\u0017\u001eXK*}.\u0017\u0006y2d'\u001e\u0014\u0005L3a']\u000em6b5\u0001\u0004\u0017D?-&\u0007\u0003\f\u001aK)~'\u0006\u0003WG3c'\u0011\u0002\u0019L.\"6\u0017\b\f_(h1]\u000b\u0004*c%\\\u001d\u001bG?y#\u0005z?l0\u001e\rm5a&\u0017\u001eXK*}.\u0017\u0003\u0003wzp\u0018H6b!\u0019_\nO)}#\u0005\u001e'K4n*\u001d\u0002'^5}\u0010C.h/]\u0015\u0016N?\u007f\u001d\u0002\u0015\u0019X6\u0004g;n'\u0010C.h/]\u0015\u0016N\u0005n0\u000b\u0003\fK6\u0005C.h/]\tZ;n)-\u001d\u0011Ru\u0004\u0004*c%\u000by2x.\u0019\u0015\n\n8b:\u0007k4n*\u001d\u0002\u000b\u0005C.h/]\u0005~5b.\u0001".length();
                        var9_4 = 11;
                        var8_5 = -1;
lbl7:

                        while (true) {
                            v0 = ++var8_5;
                            v1 = var10_2.substring(v0, v0 + var9_4);
                            v2 = -1;
                            break block26;
                            break;
                        }
lbl12:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            var10_2 = "M/dm\u0001\u0000\nC.h1]\u0018\rNue-\u0006\u0012\u0019XTQP-bP\u0000\u0019X?c6PJX\b7d,\u0017\u0013\nK<yx\u001b\u0004\u001dGuj'\u001c\u0015\nK.h&P\\r\nz/6\u0017\b\f_(h1PJXQz/.\u0013\t\u001dXj/xRR\u0015C4h!\u0000\u0011\u001e^`d6\u0017\u001dW";
                            var12_3 = "M/dm\u0001\u0000\nC.h1]\u0018\rNue-\u0006\u0012\u0019XTQP-bP\u0000\u0019X?c6PJX\b7d,\u0017\u0013\nK<yx\u001b\u0004\u001dGuj'\u001c\u0015\nK.h&P\\r\nz/6\u0017\b\f_(h1PJXQz/.\u0013\t\u001dXj/xRR\u0015C4h!\u0000\u0011\u001e^`d6\u0017\u001dW".length();
                            var9_4 = 22;
                            var8_5 = -1;
lbl21:

                            while (true) {
                                v4 = ++var8_5;
                                v1 = var10_2.substring(v4, v4 + var9_4);
                                v2 = 0;
                                break block26;
                                break;
                            }
                            break;
                        }
lbl26:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            break block27;
                            break;
                        }
                    }
                    v5 = v1.toCharArray();
                    v6 = v5;
                    v7 = v5.length;
                    var14_7 = 0;
                    do {
                        v6 = v6;
                        v8 = var14_7;
                        v9 = v6[v8];
                        switch (var14_7 % 7) {
                            case 0: {
                                v10 = 42;
                                break;
                            }
                            case 1: {
                                v10 = 90;
                                break;
                            }
                            case 2: {
                                v10 = 13;
                                break;
                            }
                            case 3: {
                                v10 = 66;
                                break;
                            }
                            case 4: {
                                v10 = 114;
                                break;
                            }
                            case 5: {
                                v10 = 112;
                                break;
                            }
                            default: {
                                v10 = 120;
                            }
                        }
                        v6[v8] = (char)(v9 ^ v10);
                        ++var14_7;
lbl65:

                        v7 = v7;
                    } while (v7 > var14_7);
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
                PackMix.a = var13;
                var0_8 = 1323559541135213835L;
                var6_9 = new long[2];
                var3_10 = 0;
                var4_11 = "P\u0016+H<\u000b\u00a9\u0084\u00d8\u0081\u00b2\u0019\u00a0\\\u0015\u009e";
                var5_12 = "P\u0016+H<\u000b\u00a9\u0084\u00d8\u0081\u00b2\u0019\u00a0\\\u0015\u009e".length();
                var2_13 = 0;
                while (true) {
                    break block28;
                    break;
                }
lbl84:

                while (true) {
                    var6_9[v11] = (((long)var7_14[0] & 255L) << 56 | ((long)var7_14[1] & 255L) << 48 | ((long)var7_14[2] & 255L) << 40 | ((long)var7_14[3] & 255L) << 32 | ((long)var7_14[4] & 255L) << 24 | ((long)var7_14[5] & 255L) << 16 | ((long)var7_14[6] & 255L) << 8 | (long)var7_14[7] & 255L) ^ var0_8;
                    if (var2_13 < var5_12) continue;
                    break block29;
                    break;
                }
            }
            var7_14 = var4_11.substring(var2_13, var2_13 += 8).getBytes("ISO-8859-1");
            v11 = var3_10++;
            while (true)
        }
        PackMix.b = var6_9;
        PackMix.c = new Integer[2];
        PackMix.CATEGORIES = new LinkedHashMap<String, List<PackMix$Entry>>();
        var8_6 = PackMix.a;
        var15_15 = new ArrayList<String>(List.of(var8_6[65], var8_6[47], var8_6[92], var8_6[10]));
        for (var16_16 = 1; var16_16 <= 4; ++var16_16) {
            var15_15.add(PackMix.a[27] + var16_16);
        }
        var8_6 = PackMix.a;
        PackMix.CATEGORIES.put(var8_6[100], List.of(new PackMix$Entry(var8_6[32], PackMix$Look.BLOCK, var15_15)));
        PackMix.CATEGORIES.put(var8_6[43], List.of(new PackMix$Entry(var8_6[20], PackMix$Look.BLOCK, List.of(var8_6[38])), PackMix.item(var8_6[86], new String[]{var8_6[54]})));
        PackMix.CATEGORIES.put(var8_6[55], List.of(new PackMix$Entry(var8_6[79], PackMix$Look.BLOCK, List.of(var8_6[45]))));
        PackMix.CATEGORIES.put(var8_6[59], List.of(PackMix.item(var8_6[13], new String[]{var8_6[75], var8_6[7]}), PackMix.item(var8_6[9], new String[]{var8_6[60], var8_6[17]})));
        PackMix.CATEGORIES.put(var8_6[11], List.of(PackMix.item(var8_6[37], new String[]{var8_6[76]})));
        PackMix.CATEGORIES.put(var8_6[89], List.of(PackMix.item(var8_6[41], new String[]{var8_6[93]})));
        PackMix.CATEGORIES.put(var8_6[90], List.of(PackMix.item(var8_6[83], new String[]{var8_6[77]})));
        PackMix.CATEGORIES.put(var8_6[30], List.of(new PackMix$Entry(var8_6[58], PackMix$Look.CRYSTAL, List.of(var8_6[12])), PackMix.item(var8_6[5], new String[]{var8_6[95]})));
        PackMix.CATEGORIES.put(var8_6[40], List.of(new PackMix$Entry(var8_6[84], PackMix$Look.SHIELD, List.of(var8_6[67], var8_6[28]))));
        var16_17 = new ArrayList<PackMix$Entry>();
        var17_18 = new ArrayList<PackMix$Entry>();
        for (String var21_22 : new String[]{var8_6[25], var8_6[16]}) {
            var22_23 = var21_22.substring(0, 1).toUpperCase() + var21_22.substring(1);
            var23_24 = new ArrayList<String>();
            v13 = new String[4];
            var8_6 = PackMix.a;
            v13[0] = var8_6[4];
            v13[1] = var8_6[44];
            v13[2] = var8_6[35];
            v13[3] = var8_6[24];
            for (String var27_28 : v13) {
                var8_6 = PackMix.a;
                var23_24.add(var8_6[101] + var21_22 + "_" + var27_28);
            }
            var8_6 = PackMix.a;
            var23_24.add(var8_6[80] + var21_22);
            var23_24.add(var8_6[78] + var21_22);
            var16_17.add(new PackMix$Entry(var22_23 + var8_6[36], PackMix$Look.SET, var23_24));
            for (String var27_28 : new String[]{var8_6[49], var8_6[42], var8_6[6]}) {
                v14 = new String[1];
                var8_6 = PackMix.a;
                v14[0] = var8_6[96] + var21_22 + "_" + var27_28;
                var17_18.add(PackMix.item(var22_23 + " " + var27_28, v14));
            }
        }
        var8_6 = PackMix.a;
        var17_18.add(PackMix.item(var8_6[94], new String[]{var8_6[15]}));
        PackMix.CATEGORIES.put(var8_6[33], var16_17);
        PackMix.CATEGORIES.put(var8_6[102], var17_18);
        PackMix.CATEGORIES.put(var8_6[82], List.of(new PackMix$Entry(var8_6[2], PackMix$Look.GUI, List.of(var8_6[68])), PackMix.item(var8_6[74], new String[]{var8_6[103], var8_6[62]}), new PackMix$Entry(var8_6[51], PackMix$Look.GUI, List.of(var8_6[8])), new PackMix$Entry(var8_6[99], PackMix$Look.GUI, List.of(var8_6[71]))));
        PackMix.picks = new LinkedHashMap<String, String>();
        PackMix.pickNames = new HashMap<String, String>();
        PackMix.tints = new HashMap<String, float[]>();
        PackMix.hands = new HashMap<String, Float>();
        PackMix.HAND_MODELS = Map.of(var8_6[37], var8_6[48], var8_6[23], var8_6[73]);
        PackMix.loaded = Map.of();
        PackMix.originals = new HashMap<Identifier, int[]>();
    }

    private static int a(int n, long l) {
        int n2 = n ^ (int)(l & 0x7FFFL) ^ 0x76DB;
        if (c[n2] == null) {
            PackMix.c[n2] = (int)(b[n2] ^ l);
        }
        return c[n2];
    }
}
