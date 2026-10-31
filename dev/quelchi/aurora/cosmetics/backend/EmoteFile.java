package dev.quelchi.aurora.cosmetics.backend;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.cosmetics.backend.CosmeticsSync;
import dev.quelchi.aurora.cosmetics.content.Emotes;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.HashMap;
import net.fabricmc.loader.api.FabricLoader;

public final class EmoteFile {
    private static Path path;
    private static long lastModified;
    private static final String[] a;

    private EmoteFile() {
    }

    
    public static void poll() {
        int n = CosmeticsSync.a;
        try {
            Path path;
            block15: {
                block16: {
                    path = EmoteFile.path;
                    if (n != 0) break block15;
                    if (path != null) break block16;
                    path = EmoteFile.path = FabricLoader.getInstance().getConfigDir().resolve(a[9]);
                    if (n != 0) break block15;
                    if (!Files.exists(path, new LinkOption[0])) {
                        EmoteFile.writeTemplate();
                    }
                }
                path = EmoteFile.path;
            }
            if (n == 0) {
                if (!Files.exists(path, new LinkOption[0])) {
                    return;
                }
                path = EmoteFile.path;
            }
            long l = Files.getLastModifiedTime(path, new LinkOption[0]).toMillis();
            if (n != 0) return;
            if (l == lastModified) {
                return;
            }
            lastModified = l;
            EmoteFile.load();
            return;
        }
        catch (Exception exception) {
            Aurora.LOG.debug(a[19], (Throwable)exception);
        }
    }

    
    private static void load() throws Exception {
        block39: {
            JsonObject jsonObject = JsonParser.parseString((String)Files.readString(path)).getAsJsonObject();
            HashMap<String, float[][]> hashMap = new HashMap<String, float[][]>();
            int n = CosmeticsSync.a;
            for (String string : jsonObject.keySet()) {
                block41: {
                    String[] stringArray;
                    block40: {
                        if (n != 0) break block39;
                        if (string.startsWith("_")) {
                            continue;
                        }
                        break block40;
                        catch (Exception exception) {
                            throw exception;
                        }
                    }
                    try {
                        Object object;
                        JsonArray jsonArray = jsonObject.getAsJsonArray(string);
                        float[][] fArray = new float[jsonArray.size()][7];
                        for (int i = 0; i < jsonArray.size(); ++i) {
                            float f;
                            block69: {
                                block56: {
                                    JsonObject jsonObject2;
                                    block55: {
                                        JsonObject jsonObject3;
                                        block68: {
                                            float f2;
                                            block54: {
                                                String string2;
                                                String string3;
                                                block52: {
                                                    block53: {
                                                        block67: {
                                                            String string4;
                                                            block66: {
                                                                block51: {
                                                                    JsonObject jsonObject4;
                                                                    block50: {
                                                                        block65: {
                                                                            float f3;
                                                                            block64: {
                                                                                block49: {
                                                                                    JsonObject jsonObject5;
                                                                                    block48: {
                                                                                        block63: {
                                                                                            float f4;
                                                                                            block62: {
                                                                                                block47: {
                                                                                                    JsonObject jsonObject6;
                                                                                                    block46: {
                                                                                                        block61: {
                                                                                                            float f5;
                                                                                                            block60: {
                                                                                                                block45: {
                                                                                                                    JsonObject jsonObject7;
                                                                                                                    block44: {
                                                                                                                        block59: {
                                                                                                                            float f6;
                                                                                                                            block58: {
                                                                                                                                block43: {
                                                                                                                                    String string5;
                                                                                                                                    JsonObject jsonObject8;
                                                                                                                                    block42: {
                                                                                                                                        block57: {
                                                                                                                                            object = jsonArray;
                                                                                                                                            if (n != 0) break block41;
                                                                                                                                            jsonObject3 = object.get(i).getAsJsonObject();
                                                                                                                                            float[] fArray2 = fArray[i];
                                                                                                                                            int n2 = 0;
                                                                                                                                            jsonObject8 = jsonObject3;
                                                                                                                                            string5 = a[11];
                                                                                                                                            if (n != 0) break block42;
                                                                                                                                            fArray2[n2] = jsonObject8.get(string5).getAsFloat();
                                                                                                                                            fArray2 = fArray[i];
                                                                                                                                            n2 = 1;
                                                                                                                                            if (!jsonObject3.has(a[0])) break block43;
                                                                                                                                            break block57;
                                                                                                                                            catch (Exception exception) {
                                                                                                                                                throw exception;
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        jsonObject8 = jsonObject3;
                                                                                                                                        string5 = a[13];
                                                                                                                                    }
                                                                                                                                    f6 = jsonObject8.get(string5).getAsFloat();
                                                                                                                                    break block58;
                                                                                                                                }
                                                                                                                                f6 = 0.0f;
                                                                                                                            }
                                                                                                                            fArray2[n2] = f6;
                                                                                                                            float[] fArray3 = fArray[i];
                                                                                                                            int n3 = 2;
                                                                                                                            jsonObject7 = jsonObject3;
                                                                                                                            if (n != 0) break block44;
                                                                                                                            if (!jsonObject7.has(a[1])) break block45;
                                                                                                                            break block59;
                                                                                                                            catch (Exception exception) {
                                                                                                                                throw exception;
                                                                                                                            }
                                                                                                                        }
                                                                                                                        jsonObject7 = jsonObject3.get(a[5]);
                                                                                                                    }
                                                                                                                    f5 = jsonObject7.getAsFloat();
                                                                                                                    break block60;
                                                                                                                }
                                                                                                                f5 = 0.0f;
                                                                                                            }
                                                                                                            fArray3[n3] = f5;
                                                                                                            float[] fArray4 = fArray[i];
                                                                                                            int n4 = 3;
                                                                                                            jsonObject6 = jsonObject3;
                                                                                                            if (n != 0) break block46;
                                                                                                            if (!jsonObject6.has(a[16])) break block47;
                                                                                                            break block61;
                                                                                                            catch (Exception exception) {
                                                                                                                throw exception;
                                                                                                            }
                                                                                                        }
                                                                                                        jsonObject6 = jsonObject3.get(a[17]);
                                                                                                    }
                                                                                                    f4 = jsonObject6.getAsFloat();
                                                                                                    break block62;
                                                                                                }
                                                                                                f4 = 0.0f;
                                                                                            }
                                                                                            fArray4[n4] = f4;
                                                                                            float[] fArray5 = fArray[i];
                                                                                            int n5 = 4;
                                                                                            jsonObject5 = jsonObject3;
                                                                                            if (n != 0) break block48;
                                                                                            if (!jsonObject5.has(a[4])) break block49;
                                                                                            break block63;
                                                                                            catch (Exception exception) {
                                                                                                throw exception;
                                                                                            }
                                                                                        }
                                                                                        jsonObject5 = jsonObject3.get(a[12]);
                                                                                    }
                                                                                    f3 = jsonObject5.getAsFloat();
                                                                                    break block64;
                                                                                }
                                                                                f3 = 0.0f;
                                                                            }
                                                                            fArray5[n5] = f3;
                                                                            jsonObject4 = jsonObject3;
                                                                            if (n != 0) break block50;
                                                                            if (!jsonObject4.has(a[6])) break block51;
                                                                            break block65;
                                                                            catch (Exception exception) {
                                                                                throw exception;
                                                                            }
                                                                        }
                                                                        jsonObject4 = jsonObject3.get(a[8]);
                                                                    }
                                                                    string4 = jsonObject4.getAsString();
                                                                    break block66;
                                                                }
                                                                string4 = "";
                                                            }
                                                            string3 = string4;
                                                            float[] fArray6 = fArray[i];
                                                            int n6 = 5;
                                                            string2 = a[14];
                                                            if (n != 0) break block52;
                                                            if (!string2.equals(string3)) break block53;
                                                            break block67;
                                                            catch (Exception exception) {
                                                                throw exception;
                                                            }
                                                        }
                                                        f2 = 1.0f;
                                                        break block54;
                                                    }
                                                    stringArray = a;
                                                    string2 = stringArray[7];
                                                }
                                                f2 = string2.equals(string3) ? 2.0f : 0.0f;
                                            }
                                            fArray6[n6] = f2;
                                            float[] fArray7 = fArray[i];
                                            int n7 = 6;
                                            jsonObject2 = jsonObject3;
                                            if (n != 0) break block55;
                                            if (!jsonObject2.has(a[3])) break block56;
                                            break block68;
                                            catch (Exception exception) {
                                                throw exception;
                                            }
                                        }
                                        jsonObject2 = jsonObject3.get(a[10]);
                                    }
                                    f = jsonObject2.getAsFloat();
                                    break block69;
                                }
                                f = 0.0f;
                            }
                            fArray7[n7] = f;
                            if (n == 0) continue;
                        }
                        object = hashMap.put(string, fArray);
                    }
                    catch (Exception exception) {
                        stringArray = a;
                        Aurora.LOG.warn(stringArray[18], (Object)string, (Object)exception.toString());
                    }
                }
                if (n == 0) continue;
            }
            Emotes.setLocalPoses(hashMap);
            Aurora.LOG.info(a[15], (Object)hashMap.size());
        }
    }

    private static void writeTemplate() throws Exception {
        String string = a[2];
        Files.writeString(path, (CharSequence)string, new OpenOption[0]);
    }

    
    static {
        block21: {
            block20: {
                block19: {
                    var9 = new String[20];
                    var7_1 = 0;
                    var6_2 = "\u0004<u\u0016\u0003\u00070v\u0514\u001dW&SWyd\u0003<b\u001e\u0010\u0004,F\u0006\fSU\u00066D\u0012p\u0016\u0007T\u007f\u00028&\u0012\u001b_6\u00030i\u0007\u0010\u0006f\t.cS\u0017_6\u000f9(S&G`\u0003}r\u001b\u0010\u0006p\u000f1cS\u0014HrF4rS\u0014Vf\n4c\u0000UOxF:g\u001e\u0010\n6\b2&\u0001\u0010Ub\u0007/r]W\n\u001cF}&SWg6\u00142qS\u001cU,F&u\u001f\u001aR:F?g\u0000\u0010\n6\u00070v_U@d\u0003,*S\u0005Nw\u00158*S\u0002G`\u0003q&\u0016\u0014Us\u001b\u007f*yU\u00066F\u007fp\u0012\u0019SsF`&[\u0017Ge\u0003}-S\u0014KfFw&\u0004\u0014PsN;t\u0016\u0004\u0006<F)&XUV~\u0007.cZ\\\u0006<F8g\u0000\u0010\u0004:l}&SU\u0004a\u0007+cIUI{\u000f)&\u0015\u001aT6\u00154h_U\u0001w\u0004.!S\u0013IdF<&\u0011\u001aSx\u00058*SRVy\u0015z&\u0015\u001aT6\u00125cS\u0005Ie\u000f)o\u0005\u0010\u0006~\u00071`S\u001aHz\u001fs$_\u007f\u00066F}$\u0016\u0014Us\\}u\u001d\u0014V;\u000f3&\u0001\u0014RsFu0S\u001cU6\u0000<u\u0007\\\n6\t0o\u0007U@y\u0014}h\u001c\u001bC8F\u001ch\u0014\u0019CeF<t\u0016UtW\"\u0014G=&\b4l}&.Y,6F\u007fY\u0000\u0019Ib\u0015\u007f<S\u000e,6F}&Q\u001dCw\u0002\u007f<SW\u00166\u00164r\u0010\u001d\u0006>M}j\u001c\u001aMeF9i\u0004\u001b\u000f:Fl&\n\u0014Q:Fo&\u0001\u001aJzDq\fSU\u00066D?i\u0017\f\u0004,F\u007f5S\u0005Ob\u00055&[^\u0006z\u0003<h\u0000U@y\u0014*g\u0001\u0011\u000f:Fi&\n\u0014Q:Fh&\u0001\u001aJzDq\fSU\u00066D/o\u0014\u001dRW\u00140$IU\u0004 F-o\u0007\u0016N6Np&\u0001\u0014Oe\u0003.&\u0015\u001aTa\u0007/bZY\u0006!F$g\u0004U\u000e;F4h\u0004\u0014TrOq&KUTy\n1&[^\u0006y\u0013)q\u0012\u0007B?Dq\fSU\u00066D1c\u0015\u0001gd\u000b\u007f<SW\u001f6\u00164r\u0010\u001d\n6Wm&\n\u0014Q6Nv&\u001a\u001bQw\u00149/_U\u0017'F/i\u001f\u0019\u0006>K}i\u0006\u0001Qw\u00149/QY,6F}&Q\u0007Oq\u000e)J\u0016\u0012\u0004,F\u007f7AUV\u007f\u0012>nS]\u000b6\u00002t\u0004\u0014TrOq&BF\u0006o\u0007**SD\u00126\u00142j\u001fU\u000e=F.v\u001f\u0014_eF2s\u0007\\\u0004:l}&SU\u0004z\u0003;r?\u0010A4\\}$B@\u0006f\u000f)e\u001bY\u0006'P}\u007f\u0012\u0002\n6Wj&\u0001\u001aJzFu+S\u0006Vz\u0007$uS\u001aSbO\u007f*yU\u00066F\u007fn\u001a\u0005`y\n9$IU\u0004'^q&XU@y\n9uS\u0001NsF*n\u001c\u0019C6\u00122t\u0000\u001a\u0006p\t/q\u0012\u0007B6\u0007)&\u0007\u001dC6\u000e4v\u0000W\n\u001cF}&SWU\u007f\b6$IU\u0004'_q&>:bS*}S=<rEF3i\u0007UTw\u00024g\u001d\u0006\n6M}b\u0001\u001aVeF)n\u0016UQ~\t1cS\u0017Ir\u001f\u007f*yU\u00066F\u007fm\u001d\u0010C4\\}$AE\n6M}u\u0002\u0000Gb\u0015}q\u001a\u0001N6\u00125cS\u0013Cs\u0012}u\u0007\u0014_\u007f\b:&\u0003\u0019Gx\u00128bQY,6F}&Q\u0010Jt\t*uQO\u00064Tl&\u0001\u001cA~\u0012q&AG\u0006z\u0003;r_U\u000b6\u00002j\u0017\u0006\u0006b\u000e8&\u0015\u001aTs\u0007/kS\u0000V4JW&SU\u00064\u00155o\u001d\u0006\u0004,F\u007f4@UT\u007f\u00015r_U\u0014\"F1c\u0015\u0001\n6M}u\u0004\u001cHq\u0015}r\u001b\u0010\u0006~\u00038jS\u0017Gu\r\u007f*yU\u00066F\u007fu\u0003\u001cHsDg&QG\u0013:Fv&\u0012\u0007E~\u0003.&\u0007\u001dC6\n2q\u0016\u0007\u0006t\u0007>mS\u001aSbDW&S\b\n\u001cF}$,\u0010^w\u000b-j\u0016*Ra\u0003/mQO\u0006Ml}&SU]6D.j\u001c\u0001\u0004,Fl>_U\u0004t\u0007.cQO\u0006&Hh&\u000eY,6F}&\bU\u0004e\n2rQO\u0006$Vq&Q\u0017Ge\u0003\u007f<SE\b%R}{_\u007f\u00066F}}SWUz\t)$IU\u0014#J}$\u0011\u0014UsDg&C[\u0017 J}$\u0012\u0018V4\\}6]D\u0011:F\u007f`\u0001\u0010W4\\}>S\b,6F\u0000\f\u000e\u007f\u0004\u0003<u\u0016\u0005\u00165g\u0000\u0010\u0003\u00070v\u0004\u0011<p\u0016\u0003\u00162u\u0004\u0011<p\u0016\u0012\u0007(t\u001c\u0007G;\u00030i\u0007\u0010U8\f.i\u001d\u0004\u0003<u\u0016\u0004\u00151i\u0007\u0005\u00165g\u0000\u0010\u0004\u0004<u\u0016\u0003\u0007?u;'(t\u001c\u0007G,F&{S\u0010Ky\u00128&\u001c\u0003Cd\u00144b\u0016]U?F1i\u0012\u0011CrF;t\u001c\u0018\u0006w\u0013/i\u0001\u0014\u000bs\u000b2r\u0016\u0006\b|\u00152h\u0004\u0000/c\u0002\u0004\u0000/c\u0002";
                    var8_3 = "\u0004<u\u0016\u0003\u00070v\u0514\u001dW&SWyd\u0003<b\u001e\u0010\u0004,F\u0006\fSU\u00066D\u0012p\u0016\u0007T\u007f\u00028&\u0012\u001b_6\u00030i\u0007\u0010\u0006f\t.cS\u0017_6\u000f9(S&G`\u0003}r\u001b\u0010\u0006p\u000f1cS\u0014HrF4rS\u0014Vf\n4c\u0000UOxF:g\u001e\u0010\n6\b2&\u0001\u0010Ub\u0007/r]W\n\u001cF}&SWg6\u00142qS\u001cU,F&u\u001f\u001aR:F?g\u0000\u0010\n6\u00070v_U@d\u0003,*S\u0005Nw\u00158*S\u0002G`\u0003q&\u0016\u0014Us\u001b\u007f*yU\u00066F\u007fp\u0012\u0019SsF`&[\u0017Ge\u0003}-S\u0014KfFw&\u0004\u0014PsN;t\u0016\u0004\u0006<F)&XUV~\u0007.cZ\\\u0006<F8g\u0000\u0010\u0004:l}&SU\u0004a\u0007+cIUI{\u000f)&\u0015\u001aT6\u00154h_U\u0001w\u0004.!S\u0013IdF<&\u0011\u001aSx\u00058*SRVy\u0015z&\u0015\u001aT6\u00125cS\u0005Ie\u000f)o\u0005\u0010\u0006~\u00071`S\u001aHz\u001fs$_\u007f\u00066F}$\u0016\u0014Us\\}u\u001d\u0014V;\u000f3&\u0001\u0014RsFu0S\u001cU6\u0000<u\u0007\\\n6\t0o\u0007U@y\u0014}h\u001c\u001bC8F\u001ch\u0014\u0019CeF<t\u0016UtW\"\u0014G=&\b4l}&.Y,6F\u007fY\u0000\u0019Ib\u0015\u007f<S\u000e,6F}&Q\u001dCw\u0002\u007f<SW\u00166\u00164r\u0010\u001d\u0006>M}j\u001c\u001aMeF9i\u0004\u001b\u000f:Fl&\n\u0014Q:Fo&\u0001\u001aJzDq\fSU\u00066D?i\u0017\f\u0004,F\u007f5S\u0005Ob\u00055&[^\u0006z\u0003<h\u0000U@y\u0014*g\u0001\u0011\u000f:Fi&\n\u0014Q:Fh&\u0001\u001aJzDq\fSU\u00066D/o\u0014\u001dRW\u00140$IU\u0004 F-o\u0007\u0016N6Np&\u0001\u0014Oe\u0003.&\u0015\u001aTa\u0007/bZY\u0006!F$g\u0004U\u000e;F4h\u0004\u0014TrOq&KUTy\n1&[^\u0006y\u0013)q\u0012\u0007B?Dq\fSU\u00066D1c\u0015\u0001gd\u000b\u007f<SW\u001f6\u00164r\u0010\u001d\n6Wm&\n\u0014Q6Nv&\u001a\u001bQw\u00149/_U\u0017'F/i\u001f\u0019\u0006>K}i\u0006\u0001Qw\u00149/QY,6F}&Q\u0007Oq\u000e)J\u0016\u0012\u0004,F\u007f7AUV\u007f\u0012>nS]\u000b6\u00002t\u0004\u0014TrOq&BF\u0006o\u0007**SD\u00126\u00142j\u001fU\u000e=F.v\u001f\u0014_eF2s\u0007\\\u0004:l}&SU\u0004z\u0003;r?\u0010A4\\}$B@\u0006f\u000f)e\u001bY\u0006'P}\u007f\u0012\u0002\n6Wj&\u0001\u001aJzFu+S\u0006Vz\u0007$uS\u001aSbO\u007f*yU\u00066F\u007fn\u001a\u0005`y\n9$IU\u0004'^q&XU@y\n9uS\u0001NsF*n\u001c\u0019C6\u00122t\u0000\u001a\u0006p\t/q\u0012\u0007B6\u0007)&\u0007\u001dC6\u000e4v\u0000W\n\u001cF}&SWU\u007f\b6$IU\u0004'_q&>:bS*}S=<rEF3i\u0007UTw\u00024g\u001d\u0006\n6M}b\u0001\u001aVeF)n\u0016UQ~\t1cS\u0017Ir\u001f\u007f*yU\u00066F\u007fm\u001d\u0010C4\\}$AE\n6M}u\u0002\u0000Gb\u0015}q\u001a\u0001N6\u00125cS\u0013Cs\u0012}u\u0007\u0014_\u007f\b:&\u0003\u0019Gx\u00128bQY,6F}&Q\u0010Jt\t*uQO\u00064Tl&\u0001\u001cA~\u0012q&AG\u0006z\u0003;r_U\u000b6\u00002j\u0017\u0006\u0006b\u000e8&\u0015\u001aTs\u0007/kS\u0000V4JW&SU\u00064\u00155o\u001d\u0006\u0004,F\u007f4@UT\u007f\u00015r_U\u0014\"F1c\u0015\u0001\n6M}u\u0004\u001cHq\u0015}r\u001b\u0010\u0006~\u00038jS\u0017Gu\r\u007f*yU\u00066F\u007fu\u0003\u001cHsDg&QG\u0013:Fv&\u0012\u0007E~\u0003.&\u0007\u001dC6\n2q\u0016\u0007\u0006t\u0007>mS\u001aSbDW&S\b\n\u001cF}$,\u0010^w\u000b-j\u0016*Ra\u0003/mQO\u0006Ml}&SU]6D.j\u001c\u0001\u0004,Fl>_U\u0004t\u0007.cQO\u0006&Hh&\u000eY,6F}&\bU\u0004e\n2rQO\u0006$Vq&Q\u0017Ge\u0003\u007f<SE\b%R}{_\u007f\u00066F}}SWUz\t)$IU\u0014#J}$\u0011\u0014UsDg&C[\u0017 J}$\u0012\u0018V4\\}6]D\u0011:F\u007f`\u0001\u0010W4\\}>S\b,6F\u0000\f\u000e\u007f\u0004\u0003<u\u0016\u0005\u00165g\u0000\u0010\u0003\u00070v\u0004\u0011<p\u0016\u0003\u00162u\u0004\u0011<p\u0016\u0012\u0007(t\u001c\u0007G;\u00030i\u0007\u0010U8\f.i\u001d\u0004\u0003<u\u0016\u0004\u00151i\u0007\u0005\u00165g\u0000\u0010\u0004\u0004<u\u0016\u0003\u0007?u;'(t\u001c\u0007G,F&{S\u0010Ky\u00128&\u001c\u0003Cd\u00144b\u0016]U?F1i\u0012\u0011CrF;t\u001c\u0018\u0006w\u0013/i\u0001\u0014\u000bs\u000b2r\u0016\u0006\b|\u00152h\u0004\u0000/c\u0002\u0004\u0000/c\u0002".length();
                    var5_4 = 4;
                    var4_5 = -1;
lbl7:

                    while (true) {
                        v0 = ++var4_5;
                        v1 = var6_2.substring(v0, v0 + var5_4);
                        v2 = -1;
                        break block19;
                        break;
                    }
lbl12:

                    while (true) {
                        var9[var7_1++] = v3.intern();
                        if ((var4_5 += var5_4) < var8_3) {
                            var5_4 = var6_2.charAt(var4_5);
                            continue;
                        }
                        var6_2 = "#0i\u0007\u0010\u00061\u001d !S\u001cH6\u0007(t\u001c\u0007G;\u00030i\u0007\u0010U8\f.i\u001dUU}\u000f-v\u0016\u0011\u001c6\u001d \u0016#0i\u0007\u0010\u0006p\u000f1cS\u0005Iz\n}`\u0012\u001cJs\u0002";
                        var8_3 = "#0i\u0007\u0010\u00061\u001d !S\u001cH6\u0007(t\u001c\u0007G;\u00030i\u0007\u0010U8\f.i\u001dUU}\u000f-v\u0016\u0011\u001c6\u001d \u0016#0i\u0007\u0010\u0006p\u000f1cS\u0005Iz\n}`\u0012\u001cJs\u0002".length();
                        var5_4 = 44;
                        var4_5 = -1;
lbl21:

                        while (true) {
                            v4 = ++var4_5;
                            v1 = var6_2.substring(v4, v4 + var5_4);
                            v2 = 0;
                            break block19;
                            break;
                        }
                        break;
                    }
lbl26:

                    while (true) {
                        var9[var7_1++] = v3.intern();
                        if ((var4_5 += var5_4) < var8_3) {
                            var5_4 = var6_2.charAt(var4_5);
                            continue;
                        }
                        break block20;
                        break;
                    }
                }
                v5 = v1.toCharArray();
                v6 = v5;
                v7 = v5.length;
                var10_6 = 0;
                do {
                    v6 = v6;
                    v8 = var10_6;
                    v9 = v6[v8];
                    switch (var10_6 % 7) {
                        case 0: {
                            v10 = 102;
                            break;
                        }
                        case 1: {
                            v10 = 93;
                            break;
                        }
                        case 2: {
                            v10 = 6;
                            break;
                        }
                        case 3: {
                            v10 = 115;
                            break;
                        }
                        case 4: {
                            v10 = 117;
                            break;
                        }
                        case 5: {
                            v10 = 38;
                            break;
                        }
                        default: {
                            v10 = 22;
                        }
                    }
                    v6[v8] = (char)(v9 ^ v10);
                    ++var10_6;
lbl65:

                    v7 = v7;
                } while (v7 > var10_6);
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
            EmoteFile.a = var9;
            break block21;
lbl77:

            while (true) {
                continue;
                break;
            }
        }
        var2_7 = 5548191551578343741L;
        while (true)
        var0_8 = -5548191551578343742L ^ var2_7;
        EmoteFile.lastModified = var0_8;
    }
}
