package dev.quelchi.aurora.util;

import dev.quelchi.aurora.util.KeyUtil;
import dev.quelchi.aurora.util.Names;
import net.minecraft.text.MutableText;
import net.minecraft.text.PlainTextContent;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;
import net.minecraft.text.TranslatableTextContent;

public final class TextReplacer {
    private TextReplacer() {
    }

    
    public static Text replace(Text text, String string, String string2) {
        MutableText mutableText;
        int n;
        block13: {
            Text text2;
            block11: {
                String string3;
                block12: {
                    n = KeyUtil.a;
                    text2 = text;
                    if (n != 0) return text2;
                    if (text2 == null) break block11;
                    string3 = string;
                    if (n != 0) break block12;
                    if (string3 == null) break block11;
                    string3 = string;
                }
                if (!string3.isEmpty()) break block13;
            }
            text2 = text;
            return text2;
        }
        MutableText mutableText2 = TextReplacer.rebuildContent(text, string, string2).method_10862(text.method_10866());
        for (Text text3 : text.method_10855()) {
            mutableText = mutableText2;
            if (n != 0) return mutableText;
            mutableText.method_10852(TextReplacer.replace(text3, string, string2));
            if (n == 0) continue;
        }
        mutableText = mutableText2;
        return mutableText;
    }

    
    private static MutableText rebuildContent(Text text, String string, String string2) {
        MutableText mutableText;
        String string3;
        TextContent textContent = text.method_10851();
        int n = KeyUtil.a;
        TextContent textContent2 = textContent;
        if (n == 0) {
            if (textContent2 instanceof PlainTextContent) {
                String string4;
                PlainTextContent plainTextContent = (PlainTextContent)textContent;
                String string5 = string4 = plainTextContent.comp_737();
                if (n != 0) return Text.method_43470((String)string5);
                if (!string5.contains(string)) return MutableText.method_43477((TextContent)textContent);
                string5 = Names.replaceWhole(string4, string, string2);
                return Text.method_43470((String)string5);
            }
            textContent2 = textContent;
        }
        if (n != 0) return MutableText.method_43477((TextContent)textContent2);
        if (!(textContent2 instanceof TranslatableTextContent)) {
            textContent2 = textContent;
            return MutableText.method_43477((TextContent)textContent2);
        }
        TranslatableTextContent translatableTextContent = (TranslatableTextContent)textContent;
        Object[] objectArray = translatableTextContent.method_11023();
        boolean bl = false;
        Object[] objectArray2 = new Object[objectArray.length];
        for (int i = 0; i < objectArray.length; ++i) {
            Object object;
            block24: {
                block25: {
                    Object object2;
                    block23: {
                        object = objectArray[i];
                        if (n != 0) return MutableText.method_43477((TextContent)textContent);
                        object2 = object;
                        if (n == 0) {
                            if (object2 instanceof Text) {
                                Text text2 = (Text)object;
                                objectArray2[i] = TextReplacer.replace(text2, string, string2);
                                bl = true;
                                if (n == 0) continue;
                            }
                            object2 = object;
                        }
                        if (n != 0) break block23;
                        if (!(object2 instanceof String)) break block24;
                        object2 = object;
                    }
                    String string6 = (String)object2;
                    if (n != 0) break block25;
                    if (!string6.contains(string)) break block24;
                    objectArray2[i] = Names.replaceWhole(string6, string, string2);
                    bl = true;
                }
                if (n == 0) continue;
            }
            objectArray2[i] = object;
            if (n == 0) continue;
        }
        if (!bl) return MutableText.method_43477((TextContent)textContent);
        String string7 = string3 = translatableTextContent.method_48323();
        if (n == 0) {
            if (string7 != null) {
                mutableText = Text.method_48322((String)translatableTextContent.method_11022(), (String)string3, (Object[])objectArray2);
                return mutableText;
            }
            string7 = translatableTextContent.method_11022();
        }
        mutableText = Text.method_43469((String)string7, (Object[])objectArray2);
        return mutableText;
    }
}
