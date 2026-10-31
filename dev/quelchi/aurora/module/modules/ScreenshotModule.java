package dev.quelchi.aurora.module.modules;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.module.Category;
import dev.quelchi.aurora.module.Module;
import dev.quelchi.aurora.module.modules.AmbienceModule;
import dev.quelchi.aurora.module.modules.ScreenshotModule$1;
import dev.quelchi.aurora.module.modules.ScreenshotModule$Preview;
import dev.quelchi.aurora.module.setting.BooleanSetting;
import java.awt.Toolkit;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import javax.imageio.ImageIO;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.toast.Toast;
import net.minecraft.client.util.ScreenshotRecorder;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Formatting;
import net.minecraft.util.Util;

public final class ScreenshotModule
extends Module {
    private static ScreenshotModule instance;
    private final BooleanSetting autoCopy;
    private final BooleanSetting chat;
    private Path lastShot;
    public static volatile boolean capturing;
    private static final String[] b;
    private static final long d;

    public ScreenshotModule() {
        String[] stringArray = b;
        super(stringArray[42], stringArray[19], Category.MISC, true);
        this.autoCopy = new BooleanSetting(stringArray[30], false);
        this.chat = new BooleanSetting(stringArray[27], true);
        instance = this;
        this.addSettings(this.autoCopy, this.chat);
    }

    public static ScreenshotModule get() {
        return instance;
    }

    
    public void capture() {
        boolean bl = AmbienceModule.b;
        MinecraftClient minecraftClient = MinecraftClient.method_1551();
        if (bl) return;
        if (minecraftClient.method_1522() == null) {
            return;
        }
        capturing = true;
        try {
            ScreenshotRecorder.method_1659((File)minecraftClient.field_1697, (Framebuffer)minecraftClient.method_1522(), arg_0 -> this.lambda$capture$2(minecraftClient, arg_0));
            return;
        }
        finally {
            capturing = false;
        }
    }

    private void announce(MinecraftClient minecraftClient) {
        String string;
        block17: {
            ScreenshotModule screenshotModule;
            block15: {
                block16: {
                    boolean bl;
                    block14: {
                        ScreenshotModule screenshotModule2;
                        block12: {
                            block13: {
                                bl = AmbienceModule.b;
                                screenshotModule2 = this;
                                if (bl) break block12;
                                if (screenshotModule2.lastShot == null) break block13;
                                minecraftClient.method_1566().method_1999((Toast)new ScreenshotModule$Preview(this.lastShot));
                            }
                            screenshotModule2 = this;
                        }
                        if (screenshotModule2.chat.getValue() && minecraftClient.field_1705 != null) break block14;
                        return;
                    }
                    screenshotModule = this;
                    if (bl) break block15;
                    if (screenshotModule.lastShot != null) break block16;
                    string = b[44];
                    break block17;
                }
                screenshotModule = this;
            }
            string = screenshotModule.lastShot.getFileName().toString();
        }
        String string2 = string;
        String[] stringArray = b;
        MutableText mutableText = Text.method_43470((String)stringArray[16]).method_27692(Formatting.field_1080).method_10852((Text)Text.method_43470((String)string2).method_27692(Formatting.field_1068)).method_10852((Text)Text.method_43470((String)stringArray[29])).method_10852((Text)this.action(stringArray[22], Formatting.field_1076, stringArray[28], stringArray[35])).method_10852((Text)Text.method_43470((String)" ")).method_10852((Text)this.open(stringArray[0])).method_10852((Text)Text.method_43470((String)" ")).method_10852((Text)this.action(stringArray[8], Formatting.field_1061, stringArray[9], stringArray[20] + string2));
        minecraftClient.field_1705.method_1743().method_1812((Text)mutableText);
    }

    
    public String delete(String var1_1) {
        block15: {
            block17: {
                block14: {
                    block16: {
                        var6_2 = ScreenshotModule.b;
                        var3_3 = MinecraftClient.method_1551().field_1697.toPath().resolve(var6_2[31]);
                        var4_4 = var3_3.resolve(var1_1).normalize();
                        var2_5 = AmbienceModule.b;
                        v0 = var4_4.getParent().equals(var3_3.normalize());
                        if (var2_5) break block14;
                        if (!v0) // goto
                        break block16;
                        catch (Exception v1) {
                            throw v1;
                        }
                    }
                    v0 = var1_1.endsWith(ScreenshotModule.b[41]);
                }
                if (var2_5) // goto
                if (v0) break block15;
                break block17;
                catch (Exception v3) {
                    throw v3;
                }
            }
            return ScreenshotModule.b[32];
        }
        try {
            v0 = Files.deleteIfExists(var4_4);
lbl33:

            if (!v0) {
                return ScreenshotModule.b[45];
            }
        }
        catch (Exception var5_6) {
            return var5_6.getClass().getSimpleName();
        }
        if (var4_4.equals(this.lastShot)) {
            this.lastShot = null;
        }
        return null;
    }

    private MutableText action(String string, Formatting formatting, String string2, String string3) {
        return Text.method_43470((String)string).method_10862(Style.field_24360.method_10977(formatting).method_10949((HoverEvent)new HoverEvent.ShowText((Text)Text.method_43470((String)string2))).method_10958((ClickEvent)new ClickEvent.RunCommand(string3)));
    }

    private MutableText open(String string) {
        Style style = Style.field_24360.method_10977(Formatting.field_1075).method_10949((HoverEvent)new HoverEvent.ShowText((Text)Text.method_43470((String)b[4])));
        if (this.lastShot != null) {
            style = style.method_10958((ClickEvent)new ClickEvent.OpenFile(this.lastShot.toFile().getAbsolutePath()));
        }
        return Text.method_43470((String)string).method_10862(style);
    }

    public boolean copyLast() {
        boolean bl = this.copyLastReason() == null;
        return bl;
    }

    
    public String copyLastReason() {
        block35: {
            block45: {
                block44: {
                    block33: {
                        block34: {
                            block32: {
                                block41: {
                                    block43: {
                                        block42: {
                                            var1_1 = AmbienceModule.b;
                                            var2_2 = this.lastShot;
                                            if (var1_1) break block32;
                                            if (var2_2 == null) break block41;
                                            break block42;
                                            catch (Throwable v0) {
                                                throw v0;
                                            }
                                        }
                                        v1 = var2_2;
                                        if (var1_1) break block33;
                                        break block43;
                                        catch (Throwable v2) {
                                            throw v2;
                                        }
                                    }
                                    if (Files.isRegularFile(v1, new LinkOption[0])) break block34;
                                }
                                var6_3 = ScreenshotModule.b;
                                var2_2 = ScreenshotModule.newestScreenshot(MinecraftClient.method_1551().field_1697.toPath().resolve(var6_3[1]));
                            }
                            this.lastShot = var2_2;
                        }
                        v1 = var2_2;
                    }
                    if (v1 == null) {
                        return ScreenshotModule.b[18];
                    }
                    if (Util.method_668() != Util.OperatingSystem.field_1137) break block35;
                    v5 = var2_2;
                    if (var1_1) // goto
                    break block44;
                    catch (Throwable v6) {
                        throw v6;
                    }
                }
                if (!ScreenshotModule.copyViaMac(v5)) break block35;
                break block45;
                catch (Throwable v7) {
                    throw v7;
                }
            }
            return null;
        }
        try {
            block36: {
                block46: {
                    v5 = var2_2;
lbl57:

                    if ((var3_4 = ImageIO.read(v5.toFile())) == null) {
                        var6_3 = ScreenshotModule.b;
                        return var6_3[15] + String.valueOf(var2_2.getFileName());
                    }
                    var4_6 = new ScreenshotModule$1(this, var3_4);
                    if (!Boolean.getBoolean(ScreenshotModule.b[33])) break block36;
                    if (Util.method_668() == Util.OperatingSystem.field_1137) break block36;
                    break block46;
                    catch (Throwable v9) {
                        throw v9;
                    }
                }
                System.setProperty(ScreenshotModule.b[43], ScreenshotModule.b[47]);
            }
            var5_8 = Toolkit.getDefaultToolkit().getSystemClipboard();
            var5_8.setContents(var4_6, null);
            return null;
        }
        catch (Throwable var3_5) {
            block37: {
                block47: {
                    if (var1_1) // goto
                    Aurora.LOG.warn(ScreenshotModule.b[12], var3_5);
                    if (!ScreenshotModule.copyViaWindows(var2_2)) break block37;
                    break block47;
                    catch (Throwable v11) {
                        throw v11;
                    }
                }
                return null;
            }
            try {
                MinecraftClient.method_1551().field_1774.method_1455(var2_2.toAbsolutePath().toString());
lbl94:

                var6_3 = ScreenshotModule.b;
                return var6_3[40];
            }
            catch (Throwable var4_7) {
                block40: {
                    block38: {
                        block39: {
                            v13 = var3_5.getClass().getSimpleName();
                            v14 = var3_5;
                            if (var1_1) break block38;
                            if (v14.getMessage() != null) break block39;
                            v16 = "";
                            break block40;
                        }
                        v14 = var3_5;
                    }
                    v16 = ScreenshotModule.b[37] + v14.getMessage();
                }
                return v13 + (String)v16;
            }
        }
    }

    
    private static boolean copyViaWindows(Path var0) {
        block6: {
            block7: {
                var1_1 = AmbienceModule.b;
                v0 = System.getProperty(ScreenshotModule.b[34], "").toLowerCase();
                if (var1_1) // goto
                if (v0.contains(ScreenshotModule.b[6])) break block6;
                break block7;
                catch (Exception v1) {
                    throw v1;
                }
            }
            return false;
        }
        try {
            var5_2 = ScreenshotModule.b;
            v0 = var0.toAbsolutePath().toString().replace("'", var5_2[26]);
lbl18:

            var2_3 = v0;
            var5_2 = ScreenshotModule.b;
            var3_5 = var5_2[24] + var2_3 + var5_2[13];
            var4_6 = new ProcessBuilder(new String[]{var5_2[38], var5_2[36], var5_2[7], var5_2[17], var5_2[11], var3_5}).redirectErrorStream(true).redirectOutput(ProcessBuilder.Redirect.DISCARD).start();
            return ScreenshotModule.finished(var4_6);
        }
        catch (Exception var2_4) {
            Aurora.LOG.warn(ScreenshotModule.b[25], (Throwable)var2_4);
            return false;
        }
    }

    private static boolean copyViaMac(Path path) {
        try {
            String[] stringArray = new String[8];
            String[] stringArray2 = b;
            stringArray[0] = stringArray2[14];
            stringArray[1] = stringArray2[3];
            stringArray[2] = stringArray2[21];
            stringArray[3] = stringArray2[39];
            stringArray[4] = stringArray2[23];
            stringArray[5] = stringArray2[39];
            stringArray[6] = stringArray2[10];
            stringArray[7] = path.toAbsolutePath().toString();
            Process process = new ProcessBuilder(stringArray).redirectErrorStream(true).redirectOutput(ProcessBuilder.Redirect.DISCARD).start();
            return ScreenshotModule.finished(process);
        }
        catch (Exception exception) {
            Aurora.LOG.warn(b[46], (Throwable)exception);
            return false;
        }
    }

    private static boolean finished(Process process) throws InterruptedException {
        block6: {
            Process process2;
            block7: {
                boolean bl;
                block8: {
                    block9: {
                        boolean bl2 = AmbienceModule.b;
                        process2 = process;
                        if (bl2) break block6;
                        if (!process2.waitFor(d, TimeUnit.SECONDS)) break block7;
                        bl = process.exitValue();
                        if (bl2) break block8;
                        if (bl) break block9;
                        bl = true;
                        break block8;
                    }
                    bl = false;
                }
                return bl;
            }
            process2 = process.destroyForcibly();
        }
        return false;
    }

    private static Path newestScreenshot(Path path) {
        Path path2;
        block12: {
            Stream<Path> stream;
            block11: {
                boolean bl = AmbienceModule.b;
                Stream<Path> stream2 = Files.list(path);
                try {
                    path2 = stream2.filter(ScreenshotModule::lambda$newestScreenshot$3).max(Comparator.comparingLong(ScreenshotModule::lambda$newestScreenshot$4)).orElse(null);
                }
                catch (Throwable throwable) {
                    try {
                        block13: {
                            if (bl || stream2 == null) break block13;
                            try {
                                stream2.close();
                            }
                            catch (Throwable throwable2) {
                                throwable.addSuppressed(throwable2);
                            }
                        }
                        throw throwable;
                    }
                    catch (Exception exception) {
                        return null;
                    }
                }
                stream = stream2;
                if (bl) break block11;
                if (stream == null) break block12;
                stream = stream2;
            }
            stream.close();
        }
        return path2;
    }

    private static long lambda$newestScreenshot$4(Path path) {
        return path.toFile().lastModified();
    }

    private static boolean lambda$newestScreenshot$3(Path path) {
        return path.toString().endsWith(b[5]);
    }

    
    private void lambda$capture$2(MinecraftClient minecraftClient, Text text) {
        block15: {
            block13: {
                boolean bl;
                block14: {
                    block11: {
                        block12: {
                            bl = AmbienceModule.b;
                            TextContent textContent = text.method_10851();
                            if (bl) break block11;
                            if (!(textContent instanceof TranslatableTextContent)) break block12;
                            TranslatableTextContent translatableTextContent = (TranslatableTextContent)textContent;
                            if (bl) break block13;
                            if (b[2].equals(translatableTextContent.method_11022())) break block14;
                        }
                        minecraftClient.execute(() -> ScreenshotModule.lambda$capture$0(minecraftClient, text));
                    }
                    return;
                }
                this.lastShot = ScreenshotModule.newestScreenshot(minecraftClient.field_1697.toPath().resolve(b[31]));
                if (bl) break block15;
            }
            if (this.autoCopy.getValue()) {
                minecraftClient.execute(this::copyLast);
            }
            minecraftClient.execute(() -> this.lambda$capture$1(minecraftClient));
        }
    }

    private void lambda$capture$1(MinecraftClient minecraftClient) {
        this.announce(minecraftClient);
    }

    private static void lambda$capture$0(MinecraftClient minecraftClient, Text text) {
        block5: {
            MinecraftClient minecraftClient2;
            block4: {
                boolean bl = AmbienceModule.b;
                minecraftClient2 = minecraftClient;
                if (bl) break block4;
                if (minecraftClient2.field_1705 == null) break block5;
                minecraftClient2 = minecraftClient;
            }
            minecraftClient2.field_1705.method_1743().method_1812(text);
        }
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var7 = new String[48];
                    var5_1 = 0;
                    var4_2 = "%p\\\t7\"\u000b\r\\^\t<\u0011%\u0016PX\u001f\u0012\r\\^\t<\u0011%\u0016PXB*\n5\u001dZ_\u001f\u0002SZ\u00131OI\u0002y\u000b>\u001b\u001f_\u000f+\u001a3\u0010LD\u0003-\u0004POB\u000b\u0003\tVB\nSqC<+\u00100\u0017SI\b%{I\u0000<\u000b3#\u0016:Z@\t-\u001av\nWE\u001fy\f5\fZI\u0002*\u00179\n\u0007\u001bQHL+\n8\bS|C\u00014\u001e8\u001a\u0016-\\^\t<\u0011%\u0016PXL:\u0010&\u0007\u001fJ\r0\u00133\u001a@Y\u0016\u0017L\u0002,/\rKI\u0001w(?\u0010[C\u001b*Q\u0010\u0011MA\u001fw<:\u0017ON\u00038\r2#\u0005\u0016?<\u000b\u001f\u0013^K\tq[?W\u0004\fH0Q\u0012\u0017L\\\u0003*\u001a~W\t\u0011LM\u001f:\r?\u000eK\u000f\u001dPY\u0000=_8\u0011K\f\u001e<\u001e2^\u0006-^Z\t=_\u000fSqC\u0002\u0010\u0011\"\u001bMM\u000f-\u0016 \u001b#\u0010P\f\u001f:\r3\u001bQ_\u00046\u000bv\u0018PY\u0002=_?\u0010\u001f_\u000f+\u001a3\u0010LD\u0003-\fy$.MI\u001a0\u001a!R\u001fO\u0003)\u0006v\u001fQHL=\u001a:\u001bKIL*\u001c$\u001bZB\u001f1\u0010\"\r\r\u001fJ^\u0003+\u001e2\u001bSI\u0018<_\u000b\u0011Q\f\u001e,\u0011v\u001fMK\u001a\u0006%|C\u001c \"I\rZXL-\u00173^\\@\u0005)\u001d9\u001fMHL-\u0010vVMI\r=_~.p\u007f%\u0001_0\u0017SILq\u0016\"\u001bR\f]y\u00100^^^\u000b/V\u007f^^_L\u00f2\u001c:\u001fL_L\t1\u0011\u0018\u0084\u0005a?[HA\r\u0006&\u001b\u001f\u0001-*\f3\u0013]@\u0015\u0017\u001e;\u001b\u001f\u007f\u0015*\u000b3\u0013\u0011{\u00057\u001b9\tL\u0002*6\r;\r\u0013\u007f\u0015*\u000b3\u0013\u0011h\u001e8\b?\u0010X\u0017L}\u0016k%lU\u001f-\u001a;P{^\r.\u00168\u0019\u0011e\u00018\u00183#\u0005\u0016*+\u0010;8V@\tqX\u001d)VB\b6\b%^\\@\u0005)\u001d9\u001fMHL:\u0010&\u0007\u001fJ\r0\u00133\u001a\u0002Y\u0018\f=WM\u0018y\u00123\rLM\u000b< =P\\\u0015y\u000b>\u001b\u001fE\u00018\u00183^KCL \u0010#\f\u001fO\u00000\u000f4\u0011^^\b\u0002^\u001f\t?JX\u0003y\u001c9\u000eF\u000b\r\\^\t<\u0011%\u0016PX\u001f\u0010\u0010PXL8_%\u001dMI\t7\f>\u0011K\u0011\u0014^Z\rw\u001e!\n\u0011D\t8\u001b:\u001bL_\u0007\u0011L\u0002\u00028\u00123\n\u001fJ^\u0003+\u001e5\u0011OU\u0004SLX\r\u0002D\u001f\u000e\u000eP[\t+\f>\u001bS@B<\u00073\u0002SZB\u001dSE\u001c;\u00107\f[\f\u00197\u001e \u001fV@\r;\u00133^\u0017m;\r_>\u001b^H\u0000<\f%W\u0004\f\u000f6\u000f?\u001b[\f\u00181\u001av\u0018V@\ty\u000f7\nW\f\u00057\f\"\u001b^H\u0004POB\u000b\n-\\^\t<\u0011%\u0016PX\u0011\u0014^Z\rw\u001e!\n\u0011D\t8\u001b:\u001bL_\n\r\\^\t<\u0011%\u0016PX\f\u001fS^\t8\u001b/^XC\u0002<";
                    var6_3 = "%p\\\t7\"\u000b\r\\^\t<\u0011%\u0016PX\u001f\u0012\r\\^\t<\u0011%\u0016PXB*\n5\u001dZ_\u001f\u0002SZ\u00131OI\u0002y\u000b>\u001b\u001f_\u000f+\u001a3\u0010LD\u0003-\u0004POB\u000b\u0003\tVB\nSqC<+\u00100\u0017SI\b%{I\u0000<\u000b3#\u0016:Z@\t-\u001av\nWE\u001fy\f5\fZI\u0002*\u00179\n\u0007\u001bQHL+\n8\bS|C\u00014\u001e8\u001a\u0016-\\^\t<\u0011%\u0016PXL:\u0010&\u0007\u001fJ\r0\u00133\u001a@Y\u0016\u0017L\u0002,/\rKI\u0001w(?\u0010[C\u001b*Q\u0010\u0011MA\u001fw<:\u0017ON\u00038\r2#\u0005\u0016?<\u000b\u001f\u0013^K\tq[?W\u0004\fH0Q\u0012\u0017L\\\u0003*\u001a~W\t\u0011LM\u001f:\r?\u000eK\u000f\u001dPY\u0000=_8\u0011K\f\u001e<\u001e2^\u0006-^Z\t=_\u000fSqC\u0002\u0010\u0011\"\u001bMM\u000f-\u0016 \u001b#\u0010P\f\u001f:\r3\u001bQ_\u00046\u000bv\u0018PY\u0002=_?\u0010\u001f_\u000f+\u001a3\u0010LD\u0003-\fy$.MI\u001a0\u001a!R\u001fO\u0003)\u0006v\u001fQHL=\u001a:\u001bKIL*\u001c$\u001bZB\u001f1\u0010\"\r\r\u001fJ^\u0003+\u001e2\u001bSI\u0018<_\u000b\u0011Q\f\u001e,\u0011v\u001fMK\u001a\u0006%|C\u001c \"I\rZXL-\u00173^\\@\u0005)\u001d9\u001fMHL-\u0010vVMI\r=_~.p\u007f%\u0001_0\u0017SILq\u0016\"\u001bR\f]y\u00100^^^\u000b/V\u007f^^_L\u00f2\u001c:\u001fL_L\t1\u0011\u0018\u0084\u0005a?[HA\r\u0006&\u001b\u001f\u0001-*\f3\u0013]@\u0015\u0017\u001e;\u001b\u001f\u007f\u0015*\u000b3\u0013\u0011{\u00057\u001b9\tL\u0002*6\r;\r\u0013\u007f\u0015*\u000b3\u0013\u0011h\u001e8\b?\u0010X\u0017L}\u0016k%lU\u001f-\u001a;P{^\r.\u00168\u0019\u0011e\u00018\u00183#\u0005\u0016*+\u0010;8V@\tqX\u001d)VB\b6\b%^\\@\u0005)\u001d9\u001fMHL:\u0010&\u0007\u001fJ\r0\u00133\u001a\u0002Y\u0018\f=WM\u0018y\u00123\rLM\u000b< =P\\\u0015y\u000b>\u001b\u001fE\u00018\u00183^KCL \u0010#\f\u001fO\u00000\u000f4\u0011^^\b\u0002^\u001f\t?JX\u0003y\u001c9\u000eF\u000b\r\\^\t<\u0011%\u0016PX\u001f\u0010\u0010PXL8_%\u001dMI\t7\f>\u0011K\u0011\u0014^Z\rw\u001e!\n\u0011D\t8\u001b:\u001bL_\u0007\u0011L\u0002\u00028\u00123\n\u001fJ^\u0003+\u001e5\u0011OU\u0004SLX\r\u0002D\u001f\u000e\u000eP[\t+\f>\u001bS@B<\u00073\u0002SZB\u001dSE\u001c;\u00107\f[\f\u00197\u001e \u001fV@\r;\u00133^\u0017m;\r_>\u001b^H\u0000<\f%W\u0004\f\u000f6\u000f?\u001b[\f\u00181\u001av\u0018V@\ty\u000f7\nW\f\u00057\f\"\u001b^H\u0004POB\u000b\n-\\^\t<\u0011%\u0016PX\u0011\u0014^Z\rw\u001e!\n\u0011D\t8\u001b:\u001bL_\n\r\\^\t<\u0011%\u0016PX\f\u001fS^\t8\u001b/^XC\u0002<".length();
                    var3_4 = 6;
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
                        var4_2 = "\u0013^O#\n_5\u0012V\\\u000e6\u001e$\u001a\u001fO\u0003)\u0006v\u0018^E\u0000<\u001b\u0005\u0018^@\u001f<";
                        var6_3 = "\u0013^O#\n_5\u0012V\\\u000e6\u001e$\u001a\u001fO\u0003)\u0006v\u0018^E\u0000<\u001b\u0005\u0018^@\u001f<".length();
                        var3_4 = 27;
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
                            v10 = 126;
                            break;
                        }
                        case 1: {
                            v10 = 63;
                            break;
                        }
                        case 2: {
                            v10 = 44;
                            break;
                        }
                        case 3: {
                            v10 = 108;
                            break;
                        }
                        case 4: {
                            v10 = 89;
                            break;
                        }
                        case 5: {
                            v10 = 127;
                            break;
                        }
                        default: {
                            v10 = 86;
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
            ScreenshotModule.b = var7;
            break block21;
lbl77:

            while (true) {
                continue;
                break;
            }
        }
        var0_7 = 5665092111313207294L;
        while (true)
        ScreenshotModule.d = 5665092111313207288L ^ var0_7;
    }
}
