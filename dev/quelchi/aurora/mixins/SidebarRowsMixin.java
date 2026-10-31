package dev.quelchi.aurora.mixins;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.quelchi.aurora.perf.HudData;
import dev.quelchi.aurora.perf.Tweaks;
import java.util.Collection;
import java.util.List;
import java.util.function.IntFunction;
import java.util.stream.Stream;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardEntry;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value={InGameHud.class})
public abstract class SidebarRowsMixin {
    private static final String SIDEBAR = "renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/scoreboard/ScoreboardObjective;)V";
    @Unique
    private Object[] aurora$rows;
    @Unique
    private ScoreboardObjective aurora$rowsFor;
    @Unique
    private int aurora$rowsGen;
    @Unique
    private long aurora$rowsAt;

    @WrapOperation(method={"method_1757(Lnet/minecraft/class_332;Lnet/minecraft/class_266;)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/class_269;method_1184(Lnet/minecraft/class_266;)Ljava/util/Collection;")})
    private Collection<ScoreboardEntry> aurora$skipEntries(Scoreboard scoreboard, ScoreboardObjective scoreboardObjective, Operation<Collection<ScoreboardEntry>> operation) {
        if (this.aurora$rows != null && scoreboardObjective == this.aurora$rowsFor && this.aurora$rowsGen == HudData.scoreboard && Util.method_658() - this.aurora$rowsAt < 50L) {
            return List.of();
        }
        this.aurora$rows = null;
        this.aurora$rowsFor = scoreboardObjective;
        return (Collection)operation.call(new Object[]{scoreboard, scoreboardObjective});
    }

    @WrapOperation(method={"method_1757(Lnet/minecraft/class_332;Lnet/minecraft/class_266;)V"}, at={@At(value="INVOKE", target="Ljava/util/stream/Stream;toArray(Ljava/util/function/IntFunction;)[Ljava/lang/Object;")})
    private Object[] aurora$keepRows(Stream<?> stream, IntFunction<?> intFunction, Operation<Object[]> operation) {
        if (this.aurora$rows != null) {
            return this.aurora$rows;
        }
        Object[] objectArray = (Object[])operation.call(new Object[]{stream, intFunction});
        if (Tweaks.SIDEBAR_ROWS) {
            this.aurora$rows = objectArray;
            this.aurora$rowsGen = HudData.scoreboard;
            this.aurora$rowsAt = Util.method_658();
        }
        return objectArray;
    }
}
