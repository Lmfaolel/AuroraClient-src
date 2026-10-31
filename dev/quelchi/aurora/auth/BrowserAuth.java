package dev.quelchi.aurora.auth;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.auth.MsAuth;
import dev.quelchi.aurora.auth.MsAuth$Account;
import dev.quelchi.aurora.auth.MsAuth$AuthException;
import dev.quelchi.aurora.module.Module;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Util;

public final class BrowserAuth {
    private static final String BAKED = "";
    private static final String FILE;
    private static String clientId;
    private static final String AUTH_URL;
    private static final String TOKEN_URL;
    private static final String SCOPE;
    private static final long TIMEOUT_SECONDS;
    private static final String[] a;
    private static final long[] b;
    private static final Long[] c;

    private BrowserAuth() {
    }

    
    public static synchronized String clientId() {
        String string;
        block16: {
            block15: {
                int n = MsAuth.a;
                String string2 = clientId;
                if (n == 0) {
                    if (string2 != null) {
                        return clientId;
                    }
                    string2 = BAKED;
                }
                clientId = string2;
                try {
                    Path path2;
                    String[] stringArray = a;
                    Path path = path2 = FabricLoader.getInstance().getConfigDir().resolve(stringArray[32]);
                    if (n == 0) {
                        if (!Files.isRegularFile(path, new LinkOption[0])) break block15;
                        path = path2;
                    }
                    Iterator<String> iterator = Files.readAllLines(path, StandardCharsets.UTF_8).iterator();
                    while (iterator.hasNext()) {
                        String string4;
                        String string5;
                        string = string5 = iterator.next();
                        if (n != 0) break block16;
                        String string3 = string4 = string.trim();
                        if (n == 0) {
                            if (string3.isEmpty()) continue;
                            string3 = string4;
                        }
                        if (n == 0) {
                            if (string3.startsWith("#")) continue;
                            string3 = string4;
                        }
                        clientId = string3;
                        break;
                    }
                }
                catch (Exception exception) {
                    String[] stringArray = a;
                    Aurora.LOG.warn(stringArray[38], (Object)stringArray[12], (Object)exception);
                }
            }
            string = clientId;
        }
        return string;
    }

    public static boolean configured() {
        boolean bl;
        block2: {
            block3: {
                int n = MsAuth.a;
                bl = BrowserAuth.clientId().isBlank();
                if (n != 0) break block2;
                if (bl) break block3;
                bl = true;
                break block2;
            }
            bl = false;
        }
        return bl;
    }

    
    public static MsAuth$Account signIn(Consumer<String> consumer) throws Exception {
        MsAuth$Account msAuth$Account;
        block27: {
            boolean bl;
            block29: {
                block28: {
                    block34: {
                        MsAuth$Account msAuth$Account2;
                        String string = BrowserAuth.clientId();
                        int n = MsAuth.a;
                        if (string.isBlank()) {
                            throw new MsAuth$AuthException(a[37]);
                        }
                        String[] stringArray = a;
                        HttpServer httpServer = HttpServer.create(new InetSocketAddress(stringArray[31], 0), 0);
                        int n2 = httpServer.getAddress().getPort();
                        String string2 = stringArray[8] + n2;
                        String string3 = BrowserAuth.random();
                        String string4 = BrowserAuth.random();
                        String string5 = Base64.getUrlEncoder().withoutPadding().encodeToString(MessageDigest.getInstance(stringArray[0]).digest(string4.getBytes(StandardCharsets.US_ASCII)));
                        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(1);
                        httpServer.createContext("/", arg_0 -> BrowserAuth.lambda$signIn$0(string3, arrayBlockingQueue, arg_0));
                        httpServer.setExecutor(null);
                        httpServer.start();
                        try {
                            String string6;
                            String string7;
                            block33: {
                                block26: {
                                    JsonObject jsonObject;
                                    block25: {
                                        JsonObject jsonObject2;
                                        block32: {
                                            String string8;
                                            String string9;
                                            block23: {
                                                block24: {
                                                    block31: {
                                                        block21: {
                                                            block22: {
                                                                block30: {
                                                                    String string10 = BrowserAuth.enc(string5);
                                                                    String string11 = BrowserAuth.enc(string3);
                                                                    String string12 = BrowserAuth.enc(stringArray[15]);
                                                                    String string13 = BrowserAuth.enc(string2);
                                                                    String string14 = BrowserAuth.enc(string);
                                                                    String string15 = stringArray[30] + string14 + stringArray[22] + string13 + stringArray[6] + string12 + stringArray[29] + string11 + stringArray[26] + string10 + stringArray[28];
                                                                    consumer.accept(stringArray[39]);
                                                                    Util.method_668().method_673(URI.create(string15));
                                                                    string9 = (String)arrayBlockingQueue.poll(BrowserAuth.a(23526, 3870964426477516365L), TimeUnit.SECONDS);
                                                                    string8 = string9;
                                                                    if (n != 0) break block21;
                                                                    if (string8 != null) break block22;
                                                                    break block30;
                                                                    catch (Exception exception) {
                                                                        throw exception;
                                                                    }
                                                                }
                                                                throw new MsAuth$AuthException(a[43]);
                                                            }
                                                            string8 = string9;
                                                        }
                                                        if (n != 0) break block23;
                                                        if (!string8.startsWith("!")) break block24;
                                                        break block31;
                                                        catch (Exception exception) {
                                                            throw exception;
                                                        }
                                                    }
                                                    throw new MsAuth$AuthException(BrowserAuth.trim(string9.substring(1)));
                                                }
                                                stringArray = a;
                                                consumer.accept(stringArray[1]);
                                                string8 = stringArray[20];
                                            }
                                            String[] stringArray2 = new String[12];
                                            stringArray = a;
                                            stringArray2[0] = stringArray[16];
                                            stringArray2[1] = string;
                                            stringArray2[2] = stringArray[5];
                                            stringArray2[3] = stringArray[34];
                                            stringArray2[4] = stringArray[24];
                                            stringArray2[5] = string9;
                                            stringArray2[6] = stringArray[18];
                                            stringArray2[7] = string2;
                                            stringArray2[8] = stringArray[21];
                                            stringArray2[9] = string4;
                                            stringArray2[10] = stringArray[40];
                                            stringArray2[11] = stringArray[33];
                                            jsonObject2 = MsAuth.postFormChecked(string8, MsAuth.form(stringArray2));
                                            string7 = jsonObject2.get(stringArray[7]).getAsString();
                                            jsonObject = jsonObject2;
                                            if (n != 0) break block25;
                                            if (!jsonObject.has(stringArray[4])) break block26;
                                            break block32;
                                            catch (Exception exception) {
                                                throw exception;
                                            }
                                        }
                                        jsonObject = jsonObject2.get(a[14]);
                                    }
                                    string6 = jsonObject.getAsString();
                                    break block33;
                                }
                                string6 = null;
                            }
                            msAuth$Account2 = MsAuth.finishFrom(string7, string6);
                        }
                        catch (Throwable throwable) {
                            httpServer.stop(0);
                            throw throwable;
                        }
                        httpServer.stop(0);
                        msAuth$Account = msAuth$Account2;
                        if (n == 0) break block27;
                        if (!Module.a) break block28;
                        break block34;
                        catch (Exception exception) {
                            throw exception;
                        }
                    }
                    bl = false;
                    break block29;
                }
                bl = true;
            }
            Module.a = bl;
        }
        return msAuth$Account;
    }

    private static String param(String string, String string2) {
        String string3;
        int n;
        block10: {
            block11: {
                n = MsAuth.a;
                string3 = string;
                if (n != 0) break block10;
                if (string3 != null) break block11;
                return null;
            }
            string3 = string;
        }
        for (String string4 : string3.split("&")) {
            int n2;
            String string5;
            block13: {
                int n3;
                int n4;
                block12: {
                    n4 = string4.indexOf(61);
                    n3 = n4;
                    if (n != 0) break block12;
                    if (n3 <= 0) {
                        continue;
                    }
                    string5 = string4;
                    n2 = 0;
                    if (n != 0) break block13;
                    n3 = string5.substring(n2, n4).equals(string2) ? 1 : 0;
                }
                if (n3 == 0) continue;
                string5 = string4;
                n2 = n4 + 1;
            }
            return URLDecoder.decode(string5.substring(n2), StandardCharsets.UTF_8);
        }
        return null;
    }

    private static String trim(String string) {
        Object object;
        block8: {
            int n;
            block6: {
                int n2;
                block7: {
                    int n3 = string.indexOf(10);
                    n2 = MsAuth.a;
                    n = n3;
                    if (n2 != 0) break block6;
                    if (n <= 0) break block7;
                    string = string.substring(0, n3);
                }
                object = string;
                if (n2 != 0) break block8;
                n = ((String)object).length();
            }
            object = n > 90 ? string.substring(0, 90) + "\u2026" : string;
        }
        return object;
    }

    private static String random() {
        byte[] byArray = new byte[32];
        new SecureRandom().nextBytes(byArray);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(byArray);
    }

    private static String enc(String string) {
        return URLEncoder.encode(string, StandardCharsets.UTF_8);
    }

    private static String page(boolean bl) {
        String string;
        String string2;
        String[] stringArray;
        block7: {
            block6: {
                String string3;
                block5: {
                    block4: {
                        if (!bl) break block4;
                        string3 = a[11];
                        break block5;
                    }
                    stringArray = a;
                    string3 = stringArray[9];
                }
                string2 = string3;
                if (!bl) break block6;
                string = a[3];
                break block7;
            }
            stringArray = a;
            string = stringArray[25];
        }
        String string4 = string;
        stringArray = a;
        return stringArray[17] + string2 + stringArray[2] + string4 + stringArray[23];
    }

    
    private static void lambda$signIn$0(String string, ArrayBlockingQueue arrayBlockingQueue, HttpExchange httpExchange) throws IOException {
        String string3;
        String string4;
        int n;
        block21: {
            OutputStream outputStream;
            block20: {
                String string2;
                String[] stringArray;
                block23: {
                    block22: {
                        String string6 = httpExchange.getRequestURI().getQuery();
                        stringArray = a;
                        boolean bl = string.equals(BrowserAuth.param(string6, stringArray[41]));
                        n = MsAuth.a;
                        string4 = bl ? BrowserAuth.param(string6, stringArray[27]) : null;
                        String string5 = string3 = bl ? BrowserAuth.param(string6, a[35]) : null;
                        if (!bl) break block22;
                        string2 = string3;
                        if (n != 0) break block23;
                        if (string2 == null) {
                            stringArray = a;
                            string3 = BrowserAuth.param(string6, stringArray[10]);
                        }
                    }
                    string2 = string4;
                }
                byte[] byArray = BrowserAuth.page(string2 != null).getBytes(StandardCharsets.UTF_8);
                stringArray = a;
                httpExchange.getResponseHeaders().add(stringArray[19], stringArray[36]);
                httpExchange.sendResponseHeaders(200, byArray.length);
                OutputStream outputStream2 = httpExchange.getResponseBody();
                try {
                    outputStream = outputStream2;
                    if (n != 0) break block20;
                    outputStream.write(byArray);
                    if (outputStream2 == null) break block21;
                    outputStream = outputStream2;
                }
                catch (Throwable throwable) {
                    if (n != 0) throw throwable;
                    if (outputStream2 == null) throw throwable;
                    try {
                        outputStream2.close();
                        throw throwable;
                    }
                    catch (Throwable throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                    throw throwable;
                }
            }
            outputStream.close();
        }
        String string6 = string4;
        if (n == 0) {
            if (string6 != null) {
                arrayBlockingQueue.offer(string4);
                if (n == 0) return;
            }
            string6 = string3;
        }
        if (string6 == null) return;
        arrayBlockingQueue.offer("!" + string3);
    }

    
    static {
        block23: {
            block22: {
                block21: {
                    block20: {
                        var13 = new String[44];
                        var11_1 = 0;
                        var10_2 = "?C#\\XQ&\u000b?b\u0005\u001f\u0003\nwLb\f\u2057$P$\n@TX`Lx\u0016\b\u0006\u0001-Nd\u0012\u0010\t\rd\u00151LGQ\tq\u001el\u000b\u001fPT2R05d\u0017Q\t\u0005~Lh\u000e\u001e\u0019\u00010\u0018c\u000b\u0002J\u0010q\u000e+\u0003\u001f\u000eDw\u0003+\u0000\u0010\t\u000f0\u0018dB<\u0003\nu\u000fy\u0003\u0017\u001eJ\r\u001en\u0004\u0003\u000f\u0017x3\u007f\r\u001a\u000f\n\n\u000by\u0003\u001f\u001e;d\u0015{\u0007\u0007Jx\u0001\u001e\u001a\u0001-\f\rh\u0001\u0014\u0019\u0017O\u0018d\t\u0014\u0004\u0011\u0004\u007f\u0016\u0001PK?\u0000d\u0001\u0010\u0006\f\u007f\u001f\u007fX\u000e?b\u0005\u001fG\r~Lm\u0003\u0018\u0006\u0001t\u0005\ty\u0010\u001e\u0018\t?b\u0005\u001f\u000f\u00000\u0005e\u0010\r~\u0010\u001e\u0018\u0005O\rq\u0017\u0003\u000fJd\u0014\u007f=\u0004\u007f\u0016\u0001\u0019^?Cg\r\u0016\u0003\n>\u0001b\u0001\u0003\u0005\u0017\u007f\n\u007f\r\u001f\u0006\r~\t%\u0001\u001e\u0007Ks\u0003e\u0011\u0004\u0007\u0001b\u001f$\r\u0010\u001f\u0010x^$\u0014CDT?\u0018d\t\u0014\u0004\r\u001en\u0004\u0003\u000f\u0017x3\u007f\r\u001a\u000f\n\u001e4i\r\t&\rf\t%\u0011\u0018\r\ny\u0002+\r\u0017\f\by\u0002n=\u0010\t\u0007u\u001fx\t\u000fg\u000b\u0014\u0004\u0010O\u0005o\u0106P*\u0006\u001e\t\u0010i\u001cnB\u0019\u001e\t|R7\u000f\u0014\u001e\u00050\u000fc\u0003\u0003\u0019\u0001dQ~\u0016\u0017G\\.P\u007f\u000b\u0005\u0006\u0001.-~\u0010\u001e\u0018\u0005,C\u007f\u000b\u0005\u0006\u0001.Pi\r\u0015\u0013Dc\u0018r\u000e\u0014WF}\ry\u0005\u0018\u0004^ Wo\u000b\u0002\u001a\bq\u00151\u0005\u0003\u0003\u0000+\u001cg\u0003\u0012\u000fIy\u0018n\u000f\u0002P\u0007u\u0002\u007f\u0007\u0003Q\fu\u0005l\n\u0005PU \\}\nJ\b\u0005s\u0007l\u0010\u001e\u001f\ntV(R\u0010Z\u0007!X0\u0001\u001e\u0006\u000bbV(\u0007I\u000fRv\\0\u0004\u001e\u0004\u0010*]=\u0012\tJ\u0017i\u001f\u007f\u0007\u001cG\u0011y@x\u0003\u001f\u0019Ic\ty\u000b\u0017HZ,\bb\u0014Q\u0019\u0010i\u0000n_S\u001e\u0001h\u0018&\u0003\u001d\u0003\u0003~Vh\u0007\u001f\u001e\u0001bN5^\u0019[Dc\u0018r\u000e\u0014WFv\u0003e\u0016\\\u001d\u0001y\u000bc\u0016K\\T Wf\u0003\u0003\r\r~V;BAJ\\`\u0014)\\\f\u001en\u0006\u0018\u0018\u0001s\u0018T\u0017\u0003\u0003\f/d\f\u0005\u000f\ndA_\u001b\u0001\u000f=\u0004\u007f\u0016\u0001\u0019^?Cg\r\u0016\u0003\n>\u0001b\u0001\u0003\u0005\u0017\u007f\n\u007f\r\u001f\u0006\r~\t%\u0001\u001e\u0007Ks\u0003e\u0011\u0004\u0007\u0001b\u001f$\r\u0010\u001f\u0010x^$\u0014CDT?\u0018d\t\u0014\u0004\r\u000fd\u0006\u00145\u0012u\u001eb\u0004\u0018\u000f\u0016!Jy\u0007\u0002\u001a\u000b~\u001fn=\u0005\u0013\u0014uQh\r\u0015\u000fBb\to\u000b\u0003\u000f\u0007d3~\u0010\u0018W\nP$\u0012OVKt\u0005}\\\u0004\u000fd\u0006\u0014#+dB\u0013\u000b\u0007{L\u007f\rQ'\r~\th\u0010\u0010\f\u00100\re\u0006Q\u001e\u0016iLj\u0005\u0010\u0003\n>\u0010Jh\r\u0015\u000f;s\u0004j\u000e\u001d\u000f\nw\t6\u0004\u000fd\u0006\u00141Jh\r\u0015\u000f;s\u0004j\u000e\u001d\u000f\nw\tT\u000f\u0014\u001e\f\u007f\b61C_R6\u001cy\r\u001c\u001a\u0010-\u001fn\u000e\u0014\t\u0010O\rh\u0001\u001e\u001f\nd\u0007Jx\u0016\u0010\u001e\u0001-L\u0004\u007f\u0016\u0001\u0019^?Cg\r\u0016\u0003\n>\u0001b\u0001\u0003\u0005\u0017\u007f\n\u007f\r\u001f\u0006\r~\t%\u0001\u001e\u0007Ks\u0003e\u0011\u0004\u0007\u0001b\u001f$\r\u0010\u001f\u0010x^$\u0014CDT?\r~\u0016\u0019\u0005\u0016y\u0016n]\u0012\u0006\ru\u0002\u007f=\u0018\u000eY\t]9U_ZJ B:\u0010\r~\u0010\u001e\u0018\u0005O\rq\u0017\u0003\u000fJd\u0014\u007f\u001e4i\r\t&\rf\t%\u0011\u0018\r\ny\u0002+\r\u0017\f\by\u0002n=\u0010\t\u0007u\u001fx\u0012\r~\u0016\u0019\u0005\u0016y\u0016j\u0016\u0018\u0005\nO\u000fd\u0006\u0014\u0011\ty\u0010\u001e\u0018;t\tx\u0001\u0003\u0003\u0014d\u0005d\f\u0018\u0018n\u001a\u0005E\fd\u0001gYQ\t\fq\u001ex\u0007\u0005W\u0011d\n&Z\u001e.y\r\u0006\u0019\u0001bLx\u000b\u0016\u0004Iy\u0002+\u000b\u0002J\n\u007f\u0018+\u0011\u0014\u001eDe\u001c%\u0011/d\u0017\u001d\u000eD~\u0003\u007fB\u0003\u000f\u0005tLp\u001f\u0018?b\u0005\u001fJ\r~Lb\fQ\u0013\u000be\u001e+\u0000\u0003\u0005\u0013c\ty\u2044\u0005\u001fh\r\u0001\u000f\u0005\u001f\u007f\u0003\u0005\u000f";
                        var12_3 = "?C#\\XQ&\u000b?b\u0005\u001f\u0003\nwLb\f\u2057$P$\n@TX`Lx\u0016\b\u0006\u0001-Nd\u0012\u0010\t\rd\u00151LGQ\tq\u001el\u000b\u001fPT2R05d\u0017Q\t\u0005~Lh\u000e\u001e\u0019\u00010\u0018c\u000b\u0002J\u0010q\u000e+\u0003\u001f\u000eDw\u0003+\u0000\u0010\t\u000f0\u0018dB<\u0003\nu\u000fy\u0003\u0017\u001eJ\r\u001en\u0004\u0003\u000f\u0017x3\u007f\r\u001a\u000f\n\n\u000by\u0003\u001f\u001e;d\u0015{\u0007\u0007Jx\u0001\u001e\u001a\u0001-\f\rh\u0001\u0014\u0019\u0017O\u0018d\t\u0014\u0004\u0011\u0004\u007f\u0016\u0001PK?\u0000d\u0001\u0010\u0006\f\u007f\u001f\u007fX\u000e?b\u0005\u001fG\r~Lm\u0003\u0018\u0006\u0001t\u0005\ty\u0010\u001e\u0018\t?b\u0005\u001f\u000f\u00000\u0005e\u0010\r~\u0010\u001e\u0018\u0005O\rq\u0017\u0003\u000fJd\u0014\u007f=\u0004\u007f\u0016\u0001\u0019^?Cg\r\u0016\u0003\n>\u0001b\u0001\u0003\u0005\u0017\u007f\n\u007f\r\u001f\u0006\r~\t%\u0001\u001e\u0007Ks\u0003e\u0011\u0004\u0007\u0001b\u001f$\r\u0010\u001f\u0010x^$\u0014CDT?\u0018d\t\u0014\u0004\r\u001en\u0004\u0003\u000f\u0017x3\u007f\r\u001a\u000f\n\u001e4i\r\t&\rf\t%\u0011\u0018\r\ny\u0002+\r\u0017\f\by\u0002n=\u0010\t\u0007u\u001fx\t\u000fg\u000b\u0014\u0004\u0010O\u0005o\u0106P*\u0006\u001e\t\u0010i\u001cnB\u0019\u001e\t|R7\u000f\u0014\u001e\u00050\u000fc\u0003\u0003\u0019\u0001dQ~\u0016\u0017G\\.P\u007f\u000b\u0005\u0006\u0001.-~\u0010\u001e\u0018\u0005,C\u007f\u000b\u0005\u0006\u0001.Pi\r\u0015\u0013Dc\u0018r\u000e\u0014WF}\ry\u0005\u0018\u0004^ Wo\u000b\u0002\u001a\bq\u00151\u0005\u0003\u0003\u0000+\u001cg\u0003\u0012\u000fIy\u0018n\u000f\u0002P\u0007u\u0002\u007f\u0007\u0003Q\fu\u0005l\n\u0005PU \\}\nJ\b\u0005s\u0007l\u0010\u001e\u001f\ntV(R\u0010Z\u0007!X0\u0001\u001e\u0006\u000bbV(\u0007I\u000fRv\\0\u0004\u001e\u0004\u0010*]=\u0012\tJ\u0017i\u001f\u007f\u0007\u001cG\u0011y@x\u0003\u001f\u0019Ic\ty\u000b\u0017HZ,\bb\u0014Q\u0019\u0010i\u0000n_S\u001e\u0001h\u0018&\u0003\u001d\u0003\u0003~Vh\u0007\u001f\u001e\u0001bN5^\u0019[Dc\u0018r\u000e\u0014WFv\u0003e\u0016\\\u001d\u0001y\u000bc\u0016K\\T Wf\u0003\u0003\r\r~V;BAJ\\`\u0014)\\\f\u001en\u0006\u0018\u0018\u0001s\u0018T\u0017\u0003\u0003\f/d\f\u0005\u000f\ndA_\u001b\u0001\u000f=\u0004\u007f\u0016\u0001\u0019^?Cg\r\u0016\u0003\n>\u0001b\u0001\u0003\u0005\u0017\u007f\n\u007f\r\u001f\u0006\r~\t%\u0001\u001e\u0007Ks\u0003e\u0011\u0004\u0007\u0001b\u001f$\r\u0010\u001f\u0010x^$\u0014CDT?\u0018d\t\u0014\u0004\r\u000fd\u0006\u00145\u0012u\u001eb\u0004\u0018\u000f\u0016!Jy\u0007\u0002\u001a\u000b~\u001fn=\u0005\u0013\u0014uQh\r\u0015\u000fBb\to\u000b\u0003\u000f\u0007d3~\u0010\u0018W\nP$\u0012OVKt\u0005}\\\u0004\u000fd\u0006\u0014#+dB\u0013\u000b\u0007{L\u007f\rQ'\r~\th\u0010\u0010\f\u00100\re\u0006Q\u001e\u0016iLj\u0005\u0010\u0003\n>\u0010Jh\r\u0015\u000f;s\u0004j\u000e\u001d\u000f\nw\t6\u0004\u000fd\u0006\u00141Jh\r\u0015\u000f;s\u0004j\u000e\u001d\u000f\nw\tT\u000f\u0014\u001e\f\u007f\b61C_R6\u001cy\r\u001c\u001a\u0010-\u001fn\u000e\u0014\t\u0010O\rh\u0001\u001e\u001f\nd\u0007Jx\u0016\u0010\u001e\u0001-L\u0004\u007f\u0016\u0001\u0019^?Cg\r\u0016\u0003\n>\u0001b\u0001\u0003\u0005\u0017\u007f\n\u007f\r\u001f\u0006\r~\t%\u0001\u001e\u0007Ks\u0003e\u0011\u0004\u0007\u0001b\u001f$\r\u0010\u001f\u0010x^$\u0014CDT?\r~\u0016\u0019\u0005\u0016y\u0016n]\u0012\u0006\ru\u0002\u007f=\u0018\u000eY\t]9U_ZJ B:\u0010\r~\u0010\u001e\u0018\u0005O\rq\u0017\u0003\u000fJd\u0014\u007f\u001e4i\r\t&\rf\t%\u0011\u0018\r\ny\u0002+\r\u0017\f\by\u0002n=\u0010\t\u0007u\u001fx\u0012\r~\u0016\u0019\u0005\u0016y\u0016j\u0016\u0018\u0005\nO\u000fd\u0006\u0014\u0011\ty\u0010\u001e\u0018;t\tx\u0001\u0003\u0003\u0014d\u0005d\f\u0018\u0018n\u001a\u0005E\fd\u0001gYQ\t\fq\u001ex\u0007\u0005W\u0011d\n&Z\u001e.y\r\u0006\u0019\u0001bLx\u000b\u0016\u0004Iy\u0002+\u000b\u0002J\n\u007f\u0018+\u0011\u0014\u001eDe\u001c%\u0011/d\u0017\u001d\u000eD~\u0003\u007fB\u0003\u000f\u0005tLp\u001f\u0018?b\u0005\u001fJ\r~Lb\fQ\u0013\u000be\u001e+\u0000\u0003\u0005\u0013c\ty\u2044\u0005\u001fh\r\u0001\u000f\u0005\u001f\u007f\u0003\u0005\u000f".length();
                        var9_4 = 7;
                        var8_5 = -1;
lbl7:

                        while (true) {
                            v0 = ++var8_5;
                            v1 = var10_2.substring(v0, v0 + var9_4);
                            v2 = -1;
                            break block20;
                            break;
                        }
lbl12:

                        while (true) {
                            var13[var11_1++] = v3.intern();
                            if ((var8_5 += var9_4) < var12_3) {
                                var9_4 = var10_2.charAt(var8_5);
                                continue;
                            }
                            var10_2 = "\u0004\u007f\u0016\u0001\u0019^?Cg\r\u0016\u0003\n>\u0001b\u0001\u0003\u0005\u0017\u007f\n\u007f\r\u001f\u0006\r~\t%\u0001\u001e\u0007Ks\u0003e\u0011\u0004\u0007\u0001b\u001f$\r\u0010\u001f\u0010x^$\u0014CDT?\r~\u0016\u0019\u0005\u0016y\u0016n\u0012?b\u0005\u001fG\r~L\u007f\u000b\u001c\u000f\u00000\u0003~\u0016_";
                            var12_3 = "\u0004\u007f\u0016\u0001\u0019^?Cg\r\u0016\u0003\n>\u0001b\u0001\u0003\u0005\u0017\u007f\n\u007f\r\u001f\u0006\r~\t%\u0001\u001e\u0007Ks\u0003e\u0011\u0004\u0007\u0001b\u001f$\r\u0010\u001f\u0010x^$\u0014CDT?\r~\u0016\u0019\u0005\u0016y\u0016n\u0012?b\u0005\u001fG\r~L\u007f\u000b\u001c\u000f\u00000\u0003~\u0016_".length();
                            var9_4 = 65;
                            var8_5 = -1;
lbl21:

                            while (true) {
                                v4 = ++var8_5;
                                v1 = var10_2.substring(v4, v4 + var9_4);
                                v2 = 0;
                                break block20;
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
                            break block21;
                            break;
                        }
                    }
                    v5 = v1.toCharArray();
                    v6 = v5;
                    v7 = v5.length;
                    var14_6 = 0;
                    do {
                        v6 = v6;
                        v8 = var14_6;
                        v9 = v6[v8];
                        switch (var14_6 % 7) {
                            case 0: {
                                v10 = 108;
                                break;
                            }
                            case 1: {
                                v10 = 11;
                                break;
                            }
                            case 2: {
                                v10 = 98;
                                break;
                            }
                            case 3: {
                                v10 = 113;
                                break;
                            }
                            case 4: {
                                v10 = 106;
                                break;
                            }
                            case 5: {
                                v10 = 100;
                                break;
                            }
                            default: {
                                v10 = 16;
                            }
                        }
                        v6[v8] = (char)(v9 ^ v10);
                        ++var14_6;
lbl65:

                        v7 = v7;
                    } while (v7 > var14_6);
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
                BrowserAuth.a = var13;
                BrowserAuth.TOKEN_URL = BrowserAuth.a[13];
                BrowserAuth.SCOPE = BrowserAuth.a[33];
                BrowserAuth.FILE = BrowserAuth.a[12];
                BrowserAuth.AUTH_URL = BrowserAuth.a[42];
                var0_7 = 2218068574355287151L;
                var6_8 = new long[2];
                var3_9 = 0;
                var4_10 = "_\u00e2\u00f9f\u00ed\u00a9\u009el+pD?/\u00ba\u00db\u000e";
                var5_11 = "_\u00e2\u00f9f\u00ed\u00a9\u009el+pD?/\u00ba\u00db\u000e".length();
                var2_12 = 0;
                while (true) {
                    break block22;
                    break;
                }
lbl89:

                while (true) {
                    var6_8[v11] = (((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L) ^ var0_7;
                    if (var2_12 < var5_11) continue;
                    break block23;
                    break;
                }
            }
            var7_13 = var4_10.substring(var2_12, var2_12 += 8).getBytes("ISO-8859-1");
            v11 = var3_9++;
            while (true)
        }
        BrowserAuth.b = var6_8;
        BrowserAuth.c = new Long[2];
        BrowserAuth.TIMEOUT_SECONDS = BrowserAuth.a(7813, 4695795338887230255L);
    }

    private static long a(int n, long l) {
        int n2 = (n ^ (int)l ^ 0x6DAA) & Short.MAX_VALUE;
        if (c[n2] == null) {
            BrowserAuth.c[n2] = b[n2] ^ l;
        }
        return c[n2];
    }
}
