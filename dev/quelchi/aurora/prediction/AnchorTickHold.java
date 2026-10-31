package dev.quelchi.aurora.prediction;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.prediction.AnchorTickHold$Held;
import dev.quelchi.aurora.prediction.Prediction;
import dev.quelchi.aurora.prediction.PredictionHandshake;
import dev.quelchi.aurora.prediction.ac.InputAudit;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;

public final class AnchorTickHold {
    private static AnchorTickHold$Held held;
    private static int tick;
    private static int lastAnchorTick;
    private static boolean replaying;
    private static final String a;

    private AnchorTickHold() {
    }

    
    public static boolean hold(ClientPlayerEntity clientPlayerEntity, Hand hand, BlockHitResult blockHitResult) {
        block23: {
            AnchorTickHold$Held anchorTickHold$Held;
            boolean bl;
            block22: {
                bl = PredictionHandshake.a;
                boolean bl2 = replaying;
                if (bl) return bl2;
                if (bl2) return false;
                bl2 = Prediction.active();
                if (bl) return bl2;
                if (!bl2) return false;
                if (clientPlayerEntity == null) return false;
                if (blockHitResult == null) {
                    return false;
                }
                boolean bl3 = AnchorTickHold.anchorClick(clientPlayerEntity, hand, blockHitResult);
                if (bl) return bl3;
                if (!bl3) {
                    return false;
                }
                anchorTickHold$Held = held;
                if (!bl) {
                    if (anchorTickHold$Held == null) {
                        int n = lastAnchorTick;
                        if (bl) return n != 0;
                        if (n != tick) {
                            lastAnchorTick = tick;
                            return 0 != 0;
                        }
                    }
                    anchorTickHold$Held = held;
                }
                if (bl) break block22;
                if (anchorTickHold$Held != null) break block23;
                anchorTickHold$Held = new AnchorTickHold$Held(hand, blockHitResult, clientPlayerEntity.method_31548().method_67532(), InputAudit.inVanilla());
            }
            held = anchorTickHold$Held;
            if (!bl) return true;
        }
        Aurora.LOG.info(a, (Object)blockHitResult.method_17777().method_23854());
        return true;
    }

    private static boolean anchorClick(ClientPlayerEntity clientPlayerEntity, Hand hand, BlockHitResult blockHitResult) {
        boolean bl;
        block6: {
            block8: {
                block7: {
                    boolean bl2 = PredictionHandshake.a;
                    bl = clientPlayerEntity.method_73183().method_8320(blockHitResult.method_17777()).method_27852(Blocks.field_23152);
                    if (bl2) break block6;
                    if (bl) break block7;
                    bl = clientPlayerEntity.method_5998(hand).method_31574(Items.field_23141);
                    if (bl2) break block6;
                    if (!bl) break block8;
                }
                bl = true;
                break block6;
            }
            bl = false;
        }
        return bl;
    }

    
    public static void tick(MinecraftClient minecraftClient) {
        boolean bl;
        int n;
        PlayerInventory playerInventory;
        block20: {
            ++tick;
            AnchorTickHold$Held anchorTickHold$Held = held;
            held = null;
            boolean bl2 = PredictionHandshake.a;
            if (anchorTickHold$Held == null) return;
            MinecraftClient minecraftClient2 = minecraftClient;
            if (!bl2) {
                if (minecraftClient2.field_1724 == null) return;
                minecraftClient2 = minecraftClient;
            }
            if (minecraftClient2.field_1761 == null) return;
            int n2 = Prediction.active();
            if (!bl2) {
                if (n2 == 0) {
                    return;
                }
                n2 = tick;
            }
            lastAnchorTick = n2;
            playerInventory = minecraftClient.field_1724.method_31548();
            n = playerInventory.method_67532();
            playerInventory.method_61496(anchorTickHold$Held.slot());
            replaying = true;
            if (anchorTickHold$Held.vanilla()) {
                InputAudit.enterReplay();
            }
            try {
                ActionResult actionResult = minecraftClient.field_1761.method_2896(minecraftClient.field_1724, anchorTickHold$Held.hand(), anchorTickHold$Held.hit());
                bl = actionResult instanceof ActionResult.Success;
                if (!bl2) {
                    ActionResult.Success success;
                    if (bl && (success = (ActionResult.Success)actionResult).comp_2909() == ActionResult.SwingSource.field_52427) {
                        minecraftClient.field_1724.method_6104(anchorTickHold$Held.hand());
                    }
                    bl = anchorTickHold$Held.vanilla();
                }
                if (bl2) break block20;
            }
            catch (Throwable throwable) {
                if (bl2) throw throwable;
                if (anchorTickHold$Held.vanilla()) {
                    InputAudit.exitReplay();
                }
                replaying = false;
                playerInventory.method_61496(n);
                throw throwable;
            }
            if (bl) {
                InputAudit.exitReplay();
            }
            bl = false;
        }
        replaying = bl;
        playerInventory.method_61496(n);
    }

    
    static {
        char[] cArray;
        block11: {
            int n;
            int n2;
            block10: {
                char[] cArray2 = "\u0017\u0002= fy[>\t($^=\u001f-F,<R+[l\u000767S6Ml\u00054=X2\u001f%\bx;U<\u001f8\u000f;?\u001b8Kl\u001d%".toCharArray();
                cArray = cArray2;
                n2 = cArray2.length;
                n = 0;
                if (!true) break block10;
                n2 = n2;
                if (n2 <= n) break block11;
            }
            do {
                cArray = cArray;
                int n3 = n;
                char c = cArray[n3];
                cArray[n3] = (char)(c ^ (switch (n % 7) {
                    case 0 -> 76;
                    case 1 -> 102;
                    case 2 -> 88;
                    case 3 -> 84;
                    case 4 -> 59;
                    case 5 -> 89;
                    default -> 63;
                }));
                ++n;
                n2 = n2;
            } while (n2 > n);
        }
        a = new String(cArray).intern();
        lastAnchorTick = -1;
    }
}
