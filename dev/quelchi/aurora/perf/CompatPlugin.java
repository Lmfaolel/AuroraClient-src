package dev.quelchi.aurora.perf;

import dev.quelchi.aurora.perf.Tweaks;
import java.util.List;
import java.util.Set;
import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.JumpInsnNode;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public final class CompatPlugin
implements IMixinConfigPlugin {
    private static final Logger LOG;
    private static final String CULLER;
    private static final String TARGET;
    private static final String VISIT;
    private static final String VISIT_DESC;
    private static final String[] a;

    public boolean shouldApplyMixin(String string, String string2) {
        boolean bl;
        block14: {
            block15: {
                String string3;
                int n;
                block10: {
                    block11: {
                        boolean bl2;
                        block12: {
                            block13: {
                                n = Tweaks.a;
                                string3 = string2;
                                if (n != 0) break block10;
                                if (!string3.endsWith(a[26])) break block11;
                                bl2 = FabricLoader.getInstance().isModLoaded(a[3]);
                                if (n != 0) break block12;
                                if (bl2) break block13;
                                bl2 = true;
                                break block12;
                            }
                            bl2 = false;
                        }
                        return bl2;
                    }
                    string3 = string;
                }
                bl = string3.equals(TARGET);
                if (n != 0) break block14;
                if (bl) break block15;
                return true;
            }
            bl = FabricLoader.getInstance().isModLoaded(a[4]);
        }
        return bl;
    }

    
    public void postApply(String string, ClassNode classNode, String string2, IMixinInfo iMixinInfo) {
        block95: {
            int n;
            block108: {
                int n2;
                String[] stringArray;
                AbstractInsnNode abstractInsnNode;
                MethodInsnNode methodInsnNode;
                AbstractInsnNode abstractInsnNode2;
                Object object;
                int n3;
                block94: {
                    int n4;
                    block107: {
                        int n5;
                        block92: {
                            block98: {
                                block91: {
                                    int n6;
                                    n3 = Tweaks.a;
                                    if (!string.equals(TARGET)) {
                                        return;
                                    }
                                    object = null;
                                    for (Object object2 : classNode.fields) {
                                        block97: {
                                            Object object3;
                                            String string3;
                                            String string4;
                                            block96: {
                                                string4 = ((FieldNode)object2).name;
                                                string3 = a[14];
                                                if (n3 != 0) break block96;
                                                n6 = string4.contains(string3);
                                                if (n3 != 0) break block91;
                                                if (n6 == 0) break block97;
                                                object3 = object2;
                                                if (n3 != 0) break block97;
                                                string4 = ((FieldNode)object3).desc;
                                                string3 = a[22];
                                            }
                                            if (string4.equals(string3)) {
                                                object3 = object = object2;
                                            }
                                        }
                                        if (n3 == 0) continue;
                                    }
                                    n6 = n5 = 0;
                                }
                                if (object == null) break block98;
                                block54: for (Object object4 : classNode.methods) {
                                    block55: while (true) {
                                        MethodNode methodNode = (MethodNode)object4;
                                        if (n3 != 0) break block92;
                                        Object object5 = methodNode;
                                        if (n3 == 0) {
                                            if (!object5.name.equals(a[18])) continue block54;
                                            object5 = methodNode;
                                        }
                                        Object object6 = object5.instructions.toArray();
                                        int n7 = ((AbstractInsnNode[])object6).length;
                                        int n8 = 0;
                                        while (n8 < n7) {
                                            block99: {
                                                block100: {
                                                    LabelNode labelNode;
                                                    AbstractInsnNode abstractInsnNode3;
                                                    MethodInsnNode methodInsnNode2;
                                                    block106: {
                                                        block93: {
                                                            String string5;
                                                            FieldNode fieldNode;
                                                            block101: {
                                                                fieldNode = object6[n8];
                                                                if (n3 != 0) break block99;
                                                                if (n3 != 0) continue block54;
                                                                if (!(fieldNode instanceof MethodInsnNode)) break block100;
                                                                methodInsnNode2 = (MethodInsnNode)fieldNode;
                                                                if (n3 != 0) break block99;
                                                                if (!methodInsnNode2.name.equals(a[27])) break block100;
                                                                string5 = methodInsnNode2.owner;
                                                                if (n3 != 0) break block101;
                                                                if (!string5.startsWith(a[21])) break block100;
                                                                string5 = methodInsnNode2.desc;
                                                            }
                                                            if (!string5.startsWith(a[17].substring(0, a[8].length() - 1))) break block100;
                                                            abstractInsnNode2 = null;
                                                            methodInsnNode = null;
                                                            abstractInsnNode = fieldNode.getNext();
                                                            int n9 = 0;
                                                            while (n9 < 24) {
                                                                block102: {
                                                                    AbstractInsnNode abstractInsnNode4;
                                                                    block103: {
                                                                        String string6;
                                                                        String string7;
                                                                        block104: {
                                                                            block105: {
                                                                                object4 = abstractInsnNode;
                                                                                if (n3 != 0) continue block55;
                                                                                if (object4 == null) break;
                                                                                abstractInsnNode3 = methodInsnNode;
                                                                                if (n3 != 0) break block93;
                                                                                if (abstractInsnNode3 != null) break;
                                                                                abstractInsnNode4 = abstractInsnNode;
                                                                                if (n3 != 0) break block102;
                                                                                if (!(abstractInsnNode4 instanceof MethodInsnNode)) break block103;
                                                                                labelNode = (MethodInsnNode)abstractInsnNode;
                                                                                string7 = labelNode.name;
                                                                                string6 = a[12];
                                                                                if (n3 != 0) break block104;
                                                                                if (!string7.contains(string6)) break block105;
                                                                                abstractInsnNode2 = labelNode;
                                                                                if (n3 == 0) break block103;
                                                                            }
                                                                            abstractInsnNode4 = labelNode;
                                                                            if (n3 != 0) break block102;
                                                                            string7 = abstractInsnNode4.owner;
                                                                            string6 = a[7];
                                                                        }
                                                                        if (!string7.equals(string6)) break block103;
                                                                        abstractInsnNode4 = labelNode;
                                                                        if (n3 != 0) break block102;
                                                                        if (!abstractInsnNode4.name.equals(a[20])) break block103;
                                                                        abstractInsnNode4 = labelNode;
                                                                        if (n3 != 0) break block102;
                                                                        if (abstractInsnNode4.desc.equals(a[8])) {
                                                                            methodInsnNode = labelNode;
                                                                        }
                                                                    }
                                                                    ++n9;
                                                                    abstractInsnNode4 = abstractInsnNode = abstractInsnNode.getNext();
                                                                }
                                                                if (n3 == 0) continue;
                                                            }
                                                            abstractInsnNode3 = abstractInsnNode2;
                                                        }
                                                        if (n3 != 0) break block106;
                                                        if (abstractInsnNode3 == null) break block100;
                                                        abstractInsnNode3 = methodInsnNode;
                                                    }
                                                    if (abstractInsnNode3 != null) {
                                                        LabelNode labelNode2 = new LabelNode();
                                                        labelNode = new LabelNode();
                                                        InsnList insnList = new InsnList();
                                                        stringArray = a;
                                                        insnList.add((AbstractInsnNode)new FieldInsnNode(178, stringArray[7], ((FieldNode)object).name, ((FieldNode)object).desc));
                                                        insnList.add((AbstractInsnNode)new MethodInsnNode(182, stringArray[0], stringArray[9], stringArray[6], false));
                                                        insnList.add((AbstractInsnNode)new JumpInsnNode(199, labelNode2));
                                                        insnList.add((AbstractInsnNode)new MethodInsnNode(184, stringArray[7], stringArray[25], stringArray[8], false));
                                                        insnList.add((AbstractInsnNode)new JumpInsnNode(167, labelNode));
                                                        insnList.add((AbstractInsnNode)labelNode2);
                                                        methodNode.instructions.insertBefore((AbstractInsnNode)methodInsnNode2, insnList);
                                                        methodNode.instructions.insert((AbstractInsnNode)methodInsnNode, (AbstractInsnNode)labelNode);
                                                        ++n5;
                                                    }
                                                }
                                                ++n8;
                                            }
                                            if (n3 == 0) continue;
                                        }
                                        break;
                                    }
                                    if (n3 == 0) continue;
                                }
                            }
                            if (n5 != true) break block92;
                            LOG.info(a[1]);
                            if (n3 == 0) break block107;
                        }
                        stringArray = a;
                        LOG.info(stringArray[24], (Object)n5);
                    }
                    n2 = 0;
                    for (Object object6 : classNode.fields) {
                        n4 = ((FieldNode)object6).desc.equals(a[28]);
                        if (n3 == 0) {
                            if (n4 != 0) {
                                ++n2;
                            }
                            if (n3 == 0) continue;
                        }
                        break block94;
                    }
                    n4 = n = 0;
                }
                if (n3 != 0) break block95;
                if (object == null || n2 != true) break block108;
                for (MethodNode methodNode : classNode.methods) {
                    AbstractInsnNode[] abstractInsnNodeArray = methodNode.instructions.toArray();
                    if (n3 != 0) break block95;
                    AbstractInsnNode[] abstractInsnNodeArray2 = abstractInsnNodeArray;
                    block60: while (true) {
                        int n10 = abstractInsnNodeArray2.length;
                        int n11 = 0;
                        while (n11 < n10) {
                            block109: {
                                block110: {
                                    block113: {
                                        int n12;
                                        block112: {
                                            MethodInsnNode methodInsnNode3;
                                            block111: {
                                                abstractInsnNodeArray2 = abstractInsnNodeArray;
                                                if (n3 != 0) continue block60;
                                                abstractInsnNode2 = abstractInsnNodeArray2[n11];
                                                if (n3 != 0) break block109;
                                                if (!(abstractInsnNode2 instanceof MethodInsnNode)) break block110;
                                                methodInsnNode3 = methodInsnNode = (MethodInsnNode)abstractInsnNode2;
                                                if (n3 != 0) break block111;
                                                if (!methodInsnNode3.owner.equals(a[29])) break block110;
                                                methodInsnNode3 = methodInsnNode;
                                            }
                                            String string8 = methodInsnNode3.name;
                                            int n13 = -1;
                                            n12 = string8.hashCode();
                                            if (n3 != 0) break block112;
                                            switch (n12) {
                                                case 102230: {
                                                    n12 = string8.equals(a[15]) ? 1 : 0;
                                                    if (n3 == 0) {
                                                        if (n12 == 0) break;
                                                        n13 = 0;
                                                        if (n3 == 0) break;
                                                    }
                                                    break block112;
                                                }
                                                case 113762: {
                                                    n12 = string8.equals(a[30]) ? 1 : 0;
                                                    if (n3 == 0) {
                                                        if (n12 == 0) break;
                                                        n13 = 1;
                                                        if (n3 == 0) break;
                                                    }
                                                    break block112;
                                                }
                                                case -934610812: {
                                                    n12 = string8.equals(a[13]) ? 1 : 0;
                                                    if (n3 != 0) break block112;
                                                    if (n12 == 0) break;
                                                    n13 = 2;
                                                }
                                            }
                                            n12 = n13;
                                        }
                                        switch (n12) {
                                            case 0: {
                                                String string9 = a[2];
                                                break;
                                            }
                                            case 1: {
                                                stringArray = a;
                                                String string9 = stringArray[16];
                                                break;
                                            }
                                            case 2: {
                                                stringArray = a;
                                                String string9 = stringArray[23];
                                                break;
                                            }
                                            default: {
                                                String string9 = abstractInsnNode = null;
                                            }
                                        }
                                        if (n3 != 0) break block113;
                                        if (abstractInsnNode == null) break block110;
                                        stringArray = a;
                                        methodNode.instructions.set((AbstractInsnNode)methodInsnNode, (AbstractInsnNode)new MethodInsnNode(184, stringArray[19], methodInsnNode.name, (String)abstractInsnNode, false));
                                    }
                                    ++n;
                                }
                                ++n11;
                            }
                            if (n3 == 0) continue;
                        }
                        break;
                    }
                    if (n3 == 0) continue;
                }
            }
            LOG.info(a[10], (Object)n);
        }
    }

    public void onLoad(String string) {
    }

    public String getRefMapperConfig() {
        return null;
    }

    public void acceptTargets(Set<String> set, Set<String> set2) {
    }

    public List<String> getMixins() {
        return null;
    }

    public void preApply(String string, ClassNode classNode, String string2, IMixinInfo iMixinInfo) {
    }

    
    static {
        block19: {
            block18: {
                var5 = new String[31];
                var3_1 = 0;
                var2_2 = "Rncb\u007fNkVh:W8PoYkYl3CfGyzgl\"C0\u0018|~j Ro\\/Fl4K\u007fU/P{$Pk\u001f|5s5P'Kjvw9Md\u0018Ngd#\u0002cV/ak5\u0002e[lyv#KeV/vv<NoJ+\u0010C\u007fb&C%Tn{d\u007fvbJjtg\u001cMiYc.*\u001cHkNn:o1Lm\u0017@wi5A~\u0003\tNzyh1LgWk\fK`qj%O']waq1HVja,3Cl^j|m5Oi\u0017bzg#\ryWk|v=\riTfpm$\rx]aqf\"\riPz{h\u007fMi[c`p9Md\u0017@v`<WyQ`{@%Nf]}\u0014\u0010&Yi1Tk\u0017ctm7\rEZep`$\u0019HVja,3Cl^j|m5Oi\u0017bzg#\ryWk|v=\riTfpm$\rx]aqf\"\riPz{h\u007fMi[c`p9Md\u0017@v`<WyQ`{@%Nf]}\u0082\u0010C{f$\riYisf9LoUl:n?Fy\u0017|zg9Wg\u0017lyj5L~\u0017zaj<\riWcyf3VcWaf,\u0007PcLjDv5Wo\u0003C{f$\riYisf9LoUl:n?Fy\u0017|zg9Wg\u0017lyj5L~\u0017}pm4Gx\u0017l}v>I%jj{g5PY]laj?L1qF<U\u0003_jaDyzgl\"C0\u0018\\zg9Wg\u0018Jmw\"C*[zyo5P*W}|d9L*U`cf4\u0002e^i5W8PoYkYl3Cf\u0018na#+_*Kfaf#\u0006yzgl\"C)Ycyl'grHn{g5FIAc|m4Pc[nyU5P~Qlto\u0004PkNjgp1N\u0006Jjxl&G\u001b}WEB\u001efO|PVZ\u001ckD|]\\@\u0011nUw]\\D\u0019l\u0003_ja,\u0010C\u007fb&C%Tn{d\u007fvbJjtg\u001cMiYc.O:C|Y yb>E%wm\u007ff3V1\u0011Y\u0082\u0010C{f$\riYisf9LoUl:n?Fy\u0017|zg9Wg\u0017lyj5L~\u0017zaj<\riWcyf3VcWaf,\u0007PcLjDv5Wo\u0003C{f$\riYisf9LoUl:n?Fy\u0017|zg9Wg\u0017lyj5L~\u0017}pm4Gx\u0017l}v>I%jj{g5PY]laj?L1qF<U\fH}z`5Qyizpv5\"\\jc,!WoTl}j\u007fC\u007fJ`gb\u007fRoJi:@%Nfw}|d9L\u000eNffj$loQh}a?Py%W}r,#ReVhps?UoJjq,1Qg\u0017|lm$JoLfv,1PmK \u0017tetu1\rfYar,\u0004Jx]nqO?AkT4\u001a\u0010C\u007fb&C%Tn{d\u007fvbJjtg\u001cMiYc.*\u0006Hyzgl\"C0\u0018\\zg9Wg\u0018Jmw\"C*[zyo5P*[nyopLeL/gf3MmVfff4\u0002\"Cr5n1ViPjf*|\u0002f]ia#1NeVj\u000eNffj$loQh}a?Py\u0016\u0016X|m4M}~zyo#Ax]j{N9ZcV\u0002Wi\u0017tetu1\rfYar,\u0004Jx]nqO?AkT4";
                var4_3 = "Rncb\u007fNkVh:W8PoYkYl3CfGyzgl\"C0\u0018|~j Ro\\/Fl4K\u007fU/P{$Pk\u001f|5s5P'Kjvw9Md\u0018Ngd#\u0002cV/ak5\u0002e[lyv#KeV/vv<NoJ+\u0010C\u007fb&C%Tn{d\u007fvbJjtg\u001cMiYc.*\u001cHkNn:o1Lm\u0017@wi5A~\u0003\tNzyh1LgWk\fK`qj%O']waq1HVja,3Cl^j|m5Oi\u0017bzg#\ryWk|v=\riTfpm$\rx]aqf\"\riPz{h\u007fMi[c`p9Md\u0017@v`<WyQ`{@%Nf]}\u0014\u0010&Yi1Tk\u0017ctm7\rEZep`$\u0019HVja,3Cl^j|m5Oi\u0017bzg#\ryWk|v=\riTfpm$\rx]aqf\"\riPz{h\u007fMi[c`p9Md\u0017@v`<WyQ`{@%Nf]}\u0082\u0010C{f$\riYisf9LoUl:n?Fy\u0017|zg9Wg\u0017lyj5L~\u0017zaj<\riWcyf3VcWaf,\u0007PcLjDv5Wo\u0003C{f$\riYisf9LoUl:n?Fy\u0017|zg9Wg\u0017lyj5L~\u0017}pm4Gx\u0017l}v>I%jj{g5PY]laj?L1qF<U\u0003_jaDyzgl\"C0\u0018\\zg9Wg\u0018Jmw\"C*[zyo5P*W}|d9L*U`cf4\u0002e^i5W8PoYkYl3Cf\u0018na#+_*Kfaf#\u0006yzgl\"C)Ycyl'grHn{g5FIAc|m4Pc[nyU5P~Qlto\u0004PkNjgp1N\u0006Jjxl&G\u001b}WEB\u001efO|PVZ\u001ckD|]\\@\u0011nUw]\\D\u0019l\u0003_ja,\u0010C\u007fb&C%Tn{d\u007fvbJjtg\u001cMiYc.O:C|Y yb>E%wm\u007ff3V1\u0011Y\u0082\u0010C{f$\riYisf9LoUl:n?Fy\u0017|zg9Wg\u0017lyj5L~\u0017zaj<\riWcyf3VcWaf,\u0007PcLjDv5Wo\u0003C{f$\riYisf9LoUl:n?Fy\u0017|zg9Wg\u0017lyj5L~\u0017}pm4Gx\u0017l}v>I%jj{g5PY]laj?L1qF<U\fH}z`5Qyizpv5\"\\jc,!WoTl}j\u007fC\u007fJ`gb\u007fRoJi:@%Nfw}|d9L\u000eNffj$loQh}a?Py%W}r,#ReVhps?UoJjq,1Qg\u0017|lm$JoLfv,1PmK \u0017tetu1\rfYar,\u0004Jx]nqO?AkT4\u001a\u0010C\u007fb&C%Tn{d\u007fvbJjtg\u001cMiYc.*\u0006Hyzgl\"C0\u0018\\zg9Wg\u0018Jmw\"C*[zyo5P*[nyopLeL/gf3MmVfff4\u0002\"Cr5n1ViPjf*|\u0002f]ia#1NeVj\u000eNffj$loQh}a?Py\u0016\u0016X|m4M}~zyo#Ax]j{N9ZcV\u0002Wi\u0017tetu1\rfYar,\u0004Jx]nqO?AkT4".length();
                var1_4 = 21;
                var0_5 = -1;
lbl7:

                while (true) {
                    v0 = ++var0_5;
                    v1 = var2_2.substring(v0, v0 + var1_4);
                    v2 = -1;
                    break block18;
                    break;
                }
lbl12:

                while (true) {
                    var5[var3_1++] = v3.intern();
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        continue;
                    }
                    var2_2 = "Rncb\u007fNkVh:W8PoYkYl3Cf\u0003Kja";
                    var4_3 = "Rncb\u007fNkVh:W8PoYkYl3Cf\u0003Kja".length();
                    var1_4 = 21;
                    var0_5 = -1;
lbl21:

                    while (true) {
                        v4 = ++var0_5;
                        v1 = var2_2.substring(v4, v4 + var1_4);
                        v2 = 0;
                        break block18;
                        break;
                    }
                    break;
                }
lbl26:

                while (true) {
                    var5[var3_1++] = v3.intern();
                    if ((var0_5 += var1_4) < var4_3) {
                        var1_4 = var2_2.charAt(var0_5);
                        continue;
                    }
                    break block19;
                    break;
                }
            }
            v5 = v1.toCharArray();
            v6 = v5;
            v7 = v5.length;
            var6_7 = 0;
            do {
                v6 = v6;
                v8 = var6_7;
                v9 = v6[v8];
                switch (var6_7 % 7) {
                    case 0: {
                        v10 = 56;
                        break;
                    }
                    case 1: {
                        v10 = 15;
                        break;
                    }
                    case 2: {
                        v10 = 21;
                        break;
                    }
                    case 3: {
                        v10 = 3;
                        break;
                    }
                    case 4: {
                        v10 = 80;
                        break;
                    }
                    case 5: {
                        v10 = 34;
                        break;
                    }
                    default: {
                        v10 = 10;
                    }
                }
                v6[v8] = (char)(v9 ^ v10);
                ++var6_7;
lbl65:

                v7 = v7;
            } while (v7 > var6_7);
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
        CompatPlugin.a = var5;
        CompatPlugin.CULLER = CompatPlugin.a[7];
        CompatPlugin.VISIT = CompatPlugin.a[25];
        CompatPlugin.VISIT_DESC = CompatPlugin.a[8];
        var0_6 = CompatPlugin.a;
        CompatPlugin.LOG = LoggerFactory.getLogger((String)var0_6[11]);
        CompatPlugin.TARGET = var0_6[5].replace('/', '.');
    }
}
