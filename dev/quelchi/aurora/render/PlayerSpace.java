package dev.quelchi.aurora.render;

import dev.quelchi.aurora.render.EffectEngine;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Quaternionfc;
import org.joml.Vector3f;

public final class PlayerSpace {
    public static final float NECK_Y = 1.501f;

    private PlayerSpace() {
    }

    
    public static Matrix4f matrix(AbstractClientPlayerEntity abstractClientPlayerEntity, float f) {
        Matrix4f matrix4f;
        block9: {
            float f2;
            int n;
            float f3;
            block10: {
                int n2;
                block7: {
                    float f4;
                    block8: {
                        matrix4f = new Matrix4f();
                        float f5 = MathHelper.method_17821((float)f, (float)abstractClientPlayerEntity.field_6220, (float)abstractClientPlayerEntity.field_6283);
                        n2 = EffectEngine.a;
                        matrix4f.rotate((Quaternionfc)RotationAxis.field_40716.rotationDegrees(180.0f - f5));
                        f3 = abstractClientPlayerEntity.method_6024(f);
                        n = abstractClientPlayerEntity.method_6128();
                        if (n2 != 0) break block7;
                        if (n == 0) break block8;
                        f2 = (float)abstractClientPlayerEntity.method_6003() + f;
                        float f6 = MathHelper.method_15363((float)(f2 * f2 / 100.0f), (float)0.0f, (float)1.0f);
                        matrix4f.rotate((Quaternionfc)RotationAxis.field_40714.rotationDegrees(f6 * (-90.0f - abstractClientPlayerEntity.method_5695(f))));
                        matrix4f.rotate((Quaternionfc)RotationAxis.field_40716.rotation(PlayerSpace.bank(abstractClientPlayerEntity, f)));
                        if (n2 == 0) break block9;
                    }
                    n = (f4 = f3 - 0.0f) == 0.0f ? 0 : (f4 > 0.0f ? 1 : -1);
                }
                if (n2 != 0) break block10;
                if (n <= 0) break block9;
                n = abstractClientPlayerEntity.method_5799() ? 1 : 0;
            }
            f2 = n != 0 ? -90.0f - abstractClientPlayerEntity.method_5695(f) : -90.0f;
            matrix4f.rotate((Quaternionfc)RotationAxis.field_40714.rotationDegrees(MathHelper.method_16439((float)f3, (float)0.0f, (float)f2)));
        }
        matrix4f.scale(-1.0f, -1.0f, 1.0f);
        matrix4f.translate(0.0f, -1.501f, 0.0f);
        return matrix4f;
    }

    public static Vec3d fromModel(AbstractClientPlayerEntity abstractClientPlayerEntity, float f, float f2, float f3, float f4) {
        Vector3f vector3f = PlayerSpace.matrix(abstractClientPlayerEntity, f).transformPosition(new Vector3f(f2 / 16.0f, f3 / 16.0f, f4 / 16.0f));
        return abstractClientPlayerEntity.method_30950(f).method_1031((double)vector3f.x, (double)vector3f.y, (double)vector3f.z);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static float bank(AbstractClientPlayerEntity abstractClientPlayerEntity, float f) {
        Vec3d vec3d;
        Vec3d vec3d2;
        Vec3d vec3d3;
        block9: {
            block10: {
                int n;
                block8: {
                    vec3d3 = abstractClientPlayerEntity.method_5828(f);
                    n = EffectEngine.a;
                    vec3d2 = abstractClientPlayerEntity.method_18798();
                    vec3d = vec3d2;
                    if (n != 0) break block8;
                    if (vec3d.method_37268() <= 1.0E-5) return 0.0f;
                    vec3d = vec3d3;
                }
                if (n != 0) break block9;
                if (!(vec3d.method_37268() <= 1.0E-5)) break block10;
                return 0.0f;
            }
            vec3d = vec3d2.method_61890().method_1029();
        }
        double d = vec3d.method_1026(vec3d3.method_61890().method_1029());
        double d2 = vec3d2.field_1352 * vec3d3.field_1350 - vec3d2.field_1350 * vec3d3.field_1352;
        return (float)(Math.signum(d2) * Math.acos(Math.min(1.0, Math.abs(d))));
    }
}
