package dev.quelchi.aurora.optimize;

import dev.quelchi.aurora.Aurora;
import dev.quelchi.aurora.module.modules.OptimizersModule;
import dev.quelchi.aurora.optimize.GhostAnchor$Ghost;
import dev.quelchi.aurora.optimize.ProjectileOptimizer;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.RespawnAnchorBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public final class GhostAnchor {
    private static Block block;
    private static final Map<BlockPos, GhostAnchor$Ghost> GHOSTS;
    private static final int LIFETIME;
    private static final int MIN_PING;
    private static int ticks;
    private static WeakReference<ClientWorld> lastWorld;
    private static final String[] a;

    private GhostAnchor() {
    }

    public static void register() {
        try {
            String[] stringArray = a;
            RegistryKey registryKey = RegistryKey.method_29179((RegistryKey)RegistryKeys.field_41254, (Identifier)Identifier.method_60655((String)stringArray[1], (String)stringArray[0]));
            Block block = new Block(AbstractBlock.Settings.method_9637().method_31710(MapColor.field_16008).method_22488().method_9632(-1.0f).method_42327().method_51371().method_9626(BlockSoundGroup.field_27884).method_63500(registryKey));
            GhostAnchor.block = (Block)Registry.method_39197((Registry)Registries.field_41175, (RegistryKey)registryKey, (Object)block);
            BlockRenderLayerMap.putBlock((Block)GhostAnchor.block, (BlockRenderLayer)BlockRenderLayer.field_60926);
        }
        catch (Throwable throwable) {
            block = null;
            Aurora.LOG.warn(a[2], throwable);
            return;
        }
        UseBlockCallback.EVENT.register(GhostAnchor::onUseBlock);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static ActionResult onUseBlock(PlayerEntity playerEntity, World world, Hand hand, BlockHitResult blockHitResult) {
        Object object;
        int n;
        BlockState blockState;
        BlockPos blockPos;
        block52: {
            MinecraftClient minecraftClient;
            boolean bl;
            block53: {
                Item item;
                ItemStack itemStack;
                block51: {
                    PlayerEntity playerEntity2;
                    block50: {
                        int n2;
                        block48: {
                            block49: {
                                boolean bl2;
                                block46: {
                                    block47: {
                                        OptimizersModule optimizersModule;
                                        block45: {
                                            block44: {
                                                bl = ProjectileOptimizer.a;
                                                if (block != null && blockHitResult != null) break block44;
                                                return ActionResult.field_5811;
                                            }
                                            OptimizersModule optimizersModule2 = OptimizersModule.get();
                                            optimizersModule = optimizersModule2;
                                            if (bl) break block45;
                                            if (optimizersModule == null) return ActionResult.field_5811;
                                            optimizersModule = optimizersModule2;
                                        }
                                        bl2 = optimizersModule.heroAnchors();
                                        if (bl) break block46;
                                        if (bl2) break block47;
                                        return ActionResult.field_5811;
                                    }
                                    bl2 = world.method_8608();
                                }
                                if (!bl2) {
                                    return ActionResult.field_5811;
                                }
                                blockPos = blockHitResult.method_17777();
                                blockState = world.method_8320(blockPos);
                                if (!blockState.method_27852(Blocks.field_23152)) {
                                    return ActionResult.field_5811;
                                }
                                minecraftClient = MinecraftClient.method_1551();
                                if (minecraftClient.method_1542()) {
                                    return ActionResult.field_5811;
                                }
                                if (world.method_27983() == World.field_25180) {
                                    return ActionResult.field_5811;
                                }
                                n2 = (Integer)blockState.method_11654((Property)RespawnAnchorBlock.field_23153);
                                if (bl) break block48;
                                if (n2 >= 1) break block49;
                                return ActionResult.field_5811;
                            }
                            playerEntity2 = playerEntity;
                            if (bl) break block50;
                            n2 = playerEntity2.method_5715() ? 1 : 0;
                        }
                        if (n2 != 0) {
                            return ActionResult.field_5811;
                        }
                        playerEntity2 = playerEntity;
                    }
                    itemStack = playerEntity2.method_5998(hand);
                    item = Items.field_8801;
                    if (bl) break block51;
                    if (itemStack.method_31574(item)) return ActionResult.field_5811;
                    itemStack = playerEntity.method_6079();
                    item = Items.field_8801;
                }
                n = itemStack.method_31574(item);
                if (bl) break block52;
                if (n == 0) break block53;
                return ActionResult.field_5811;
            }
            object = minecraftClient;
            if (bl) return ActionResult.field_5812;
            n = GhostAnchor.ping(object);
        }
        if (n <= 25) {
            return ActionResult.field_5811;
        }
        world.method_8652(blockPos, block.method_9564(), 3);
        object = GHOSTS.put(blockPos.method_10062(), new GhostAnchor$Ghost(blockState, ticks));
        return ActionResult.field_5812;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static int ping(MinecraftClient minecraftClient) {
        PlayerListEntry playerListEntry;
        PlayerListEntry playerListEntry2;
        block18: {
            boolean bl;
            block17: {
                MinecraftClient minecraftClient2;
                block15: {
                    block16: {
                        block14: {
                            bl = ProjectileOptimizer.a;
                            minecraftClient2 = minecraftClient;
                            if (bl) break block14;
                            if (minecraftClient2.method_1562() == null) return 26;
                            minecraftClient2 = minecraftClient;
                        }
                        if (bl) break block15;
                        if (minecraftClient2.field_1724 != null) break block16;
                        return 26;
                    }
                    minecraftClient2 = minecraftClient;
                }
                playerListEntry2 = minecraftClient2.method_1562().method_2871(minecraftClient.field_1724.method_5667());
                playerListEntry = playerListEntry2;
                if (bl) break block17;
                if (playerListEntry == null) return 26;
                playerListEntry = playerListEntry2;
            }
            if (bl) return playerListEntry.method_2959();
            if (playerListEntry.method_2959() > 0) break block18;
            return 26;
        }
        playerListEntry = playerListEntry2;
        return playerListEntry.method_2959();
    }

    public static void tick() {
        Map<BlockPos, GhostAnchor$Ghost> map;
        ClientWorld clientWorld;
        boolean bl;
        block28: {
            block29: {
                block27: {
                    block25: {
                        block26: {
                            MinecraftClient minecraftClient = MinecraftClient.method_1551();
                            bl = ProjectileOptimizer.a;
                            clientWorld = minecraftClient.field_1687;
                            if (bl) break block25;
                            if (clientWorld == lastWorld.get()) break block26;
                            GHOSTS.clear();
                            lastWorld = new WeakReference<ClientWorld>(clientWorld);
                            return;
                        }
                        ++ticks;
                    }
                    if (block == null || clientWorld == null) break block27;
                    map = GHOSTS;
                    if (bl) break block28;
                    if (!map.isEmpty()) break block29;
                }
                return;
            }
            map = GHOSTS;
        }
        Iterator<Map.Entry<BlockPos, GhostAnchor$Ghost>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            block35: {
                block34: {
                    int n;
                    int n2;
                    BlockPos blockPos;
                    Map.Entry<BlockPos, GhostAnchor$Ghost> entry;
                    block32: {
                        block33: {
                            block30: {
                                block31: {
                                    entry = iterator.next();
                                    blockPos = entry.getKey();
                                    if (bl) break block30;
                                    if (!clientWorld.method_22340(blockPos)) break block31;
                                    n2 = clientWorld.method_8320(blockPos).method_27852(block);
                                    if (bl) break block32;
                                    if (n2 != 0) break block33;
                                }
                                iterator.remove();
                            }
                            if (!bl) continue;
                        }
                        n = ticks;
                        if (bl) break block34;
                        n2 = n - entry.getValue().bornTick();
                    }
                    if (n2 <= 40) break block35;
                    n = clientWorld.method_8652(blockPos, entry.getValue().before(), 3) ? 1 : 0;
                }
                iterator.remove();
            }
            if (!bl) continue;
        }
    }

    public static void restoreAll() {
        block16: {
            block17: {
                boolean bl = ProjectileOptimizer.a;
                ClientWorld clientWorld = MinecraftClient.method_1551().field_1687;
                if (bl) break block16;
                if (clientWorld == null) break block17;
                if (block == null) break block17;
                for (BlockPos blockPos : GHOSTS.entrySet()) {
                    block21: {
                        BlockPos blockPos2;
                        ClientWorld clientWorld2;
                        block20: {
                            Object object;
                            block18: {
                                if (bl) break block16;
                                clientWorld2 = clientWorld;
                                object = blockPos.getKey();
                                if (bl) break block18;
                                if (!clientWorld2.method_22340(object)) {
                                    continue;
                                }
                                clientWorld2 = clientWorld;
                                blockPos2 = blockPos;
                                if (bl) break block20;
                                object = blockPos2.getKey();
                            }
                            if (!clientWorld2.method_8320(object).method_27852(block)) break block21;
                            clientWorld2 = clientWorld;
                            blockPos2 = blockPos.getKey();
                        }
                        clientWorld2.method_8652(blockPos2, ((GhostAnchor$Ghost)blockPos.getValue()).before(), 3);
                    }
                    if (!bl) continue;
                }
            }
            GHOSTS.clear();
        }
    }

    
    static {
        block16: {
            block15: {
                block14: {
                    var13 = new String[3];
                    var11_1 = 0;
                    var10_2 = "_\b;<@f}V\u0003< F\u0006Y\u0015& FX*\u007f\b;<@\u0019}V\u0003< F\u0019~T\u000f7$\u0014ZsM\f0oZVh\u0018\u00021oF\\{Q\u0013 *F\\x";
                    var12_3 = "_\b;<@f}V\u0003< F\u0006Y\u0015& FX*\u007f\b;<@\u0019}V\u0003< F\u0019~T\u000f7$\u0014ZsM\f0oZVh\u0018\u00021oF\\{Q\u0013 *F\\x".length();
                    var9_4 = 12;
                    var8_5 = -1;
lbl7:

                    while (true) {
                        continue;
                        break;
                    }
lbl9:

                    while (true) {
                        var13[var11_1++] = new String(v0).intern();
                        if ((var8_5 += var9_4) < var12_3) {
                            var9_4 = var10_2.charAt(var8_5);
                            continue;
                        }
                        break block14;
                        break;
                    }
                    v1 = ++var8_5;
                    v2 = var10_2.substring(v1, v1 + var9_4).toCharArray();
                    v0 = v2;
                    v3 = v2.length;
                    var14_6 = 0;
                    do {
                        v0 = v0;
                        v4 = var14_6;
                        v5 = v0[v4];
                        switch (var14_6 % 7) {
                            case 0: {
                                v6 = 56;
                                break;
                            }
                            case 1: {
                                v6 = 96;
                                break;
                            }
                            case 2: {
                                v6 = 84;
                                break;
                            }
                            case 3: {
                                v6 = 79;
                                break;
                            }
                            case 4: {
                                v6 = 52;
                                break;
                            }
                            case 5: {
                                v6 = 57;
                                break;
                            }
                            default: {
                                v6 = 28;
                            }
                        }
                        v0[v4] = (char)(v5 ^ v6);
                        ++var14_6;
lbl48:

                        v3 = v3;
                    } while (v3 > var14_6);
                    while (true)
                }
                GhostAnchor.a = var13;
                var1_7 = 1457371842721664345L;
                var0_8 = new long[2];
                var4_9 = 0;
                var5_10 = "\u00b8^C\u00b4/\u0014\u00cd@i\u00ef@\u0094/\u0014\u00cdq";
                var6_11 = "\u00b8^C\u00b4/\u0014\u00cd@i\u00ef@\u0094/\u0014\u00cdq".length();
                var3_12 = 0;
                while (true) {
                    break block15;
                    break;
                }
lbl63:

                while (true) {
                    var0_8[v7] = (((long)var7_13[0] & 255L) << 56 | ((long)var7_13[1] & 255L) << 48 | ((long)var7_13[2] & 255L) << 40 | ((long)var7_13[3] & 255L) << 32 | ((long)var7_13[4] & 255L) << 24 | ((long)var7_13[5] & 255L) << 16 | ((long)var7_13[6] & 255L) << 8 | (long)var7_13[7] & 255L) ^ var1_7;
                    if (var3_12 < var6_11) continue;
                    break block16;
                    break;
                }
            }
            var7_13 = var5_10.substring(var3_12, var3_12 += 8).getBytes("ISO-8859-1");
            v7 = var4_9++;
            while (true)
        }
        GhostAnchor.LIFETIME = (int)var0_8[1];
        GhostAnchor.MIN_PING = (int)var0_8[0];
        GhostAnchor.GHOSTS = new HashMap<BlockPos, GhostAnchor$Ghost>();
        GhostAnchor.lastWorld = new WeakReference<Object>(null);
    }
}
