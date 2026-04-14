package net.noiamnotarobot.nostalgicversions.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.resource.SynchronousResourceReloader;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.noiamnotarobot.nostalgicversions.NostalgicVersions;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin implements SynchronousResourceReloader, AutoCloseable {
    @Unique
    private int starGLCallList;
    @Unique
    private int glSkyList;
    @Unique
    private int glSkyList2;

    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "renderSky(Lnet/minecraft/client/util/math/MatrixStack;Lorg/joml/Matrix4f;FLnet/minecraft/client/render/Camera;ZLjava/lang/Runnable;)V", at = @At("HEAD"), cancellable = true)
    private void renderAlphaDimSky(MatrixStack matrices, Matrix4f projectionMatrix, float tickDelta, Camera camera, boolean thickFog, Runnable fogCallback, CallbackInfo ci) {
        /*if (client.world == null || client.world.getRegistryKey() != NostalgicVersions.ALPHA_DIM) return;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        Vec3d var2 = getSkyColor(tickDelta);
        float var3 = (float)var2.x;
        float var4 = (float)var2.y;
        float var5 = (float)var2.z;
        float var7;
        float var8;

        GL11.glColor3f(var3, var4, var5);
        BufferBuilder var12 = Tessellator.getInstance().getBuffer();
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_FOG);
        GL11.glColor3f(var3, var4, var5);
        GL11.glCallList(this.glSkyList);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_FOG);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
        GL11.glPushMatrix();
        var7 = 0.0F;
        var8 = 0.0F;
        float var9 = 0.0F;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef(var7, var8, var9);
        GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
        GL11.glRotatef(getCelestialAngle(tickDelta) * 360.0F, 1.0F, 0.0F, 0.0F);
        float var10 = 30.0F;
        RenderSystem.setShaderTexture(0, new Identifier("textures/environment/sun.png"));
        var12.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
        var12.vertex(-var10, 100.0D, -var10).light(0, 0).next();
        var12.vertex(var10, 100.0D, -var10).light(1, 0).next();
        var12.vertex(var10, 100.0D, var10).light(1, 1).next();
        var12.vertex(-var10, 100.0D, var10).light(0, 1).next();
        var12.end();
        var10 = 20.0F;
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.renderEngine.getTexture("/terrain/moon.png"));
        var12.startDrawingQuads();
        var12.addVertexWithUV((double)(-var10), -100.0D, (double)var10, 1.0D, 1.0D);
        var12.addVertexWithUV((double)var10, -100.0D, (double)var10, 0.0D, 1.0D);
        var12.addVertexWithUV((double)var10, -100.0D, (double)(-var10), 0.0D, 0.0D);
        var12.addVertexWithUV((double)(-var10), -100.0D, (double)(-var10), 1.0D, 0.0D);
        var12.end();
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        float var11 = getStarBrightness(tickDelta);
        if(var11 > 0.0F) {
            GL11.glColor4f(var11, var11, var11, var11);
            GL11.glCallList(this.starGLCallList);
        }

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_FOG);
        GL11.glPopMatrix();
        GL11.glColor3f(var3 * 0.2F + 0.04F, var4 * 0.2F + 0.04F, var5 * 0.6F + 0.1F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glCallList(this.glSkyList2);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(true);
        ci.cancel();*/
    }

    @Unique
    public Vec3d getSkyColor(float tickDelta) {
        float var3 = MathHelper.clamp(MathHelper.cos(getCelestialAngle(tickDelta) * (float)Math.PI * 2.0F) * 2.0F + 0.5F, 0.0F, 1.0F);

        float var4 = (float)(NostalgicVersions.SKY_COLOR >> 16 & 255L) / 255.0F;
        float var5 = (float)(NostalgicVersions.SKY_COLOR >> 8 & 255L) / 255.0F;
        float var6 = (float)(NostalgicVersions.SKY_COLOR & 255L) / 255.0F;
        var4 *= var3;
        var5 *= var3;
        var6 *= var3;
        return new Vec3d(var4, var5, var6);
    }

    @Unique
    public float getCelestialAngle(float tickDelta) {
        assert client.world != null;
        int var2 = (int)(client.world.getTime() % 24000L);
        float var3 = ((float)var2 + tickDelta) / 24000.0F - 0.25F;
        if(var3 < 0.0F) {
            ++var3;
        }

        if(var3 > 1.0F) {
            --var3;
        }

        float var4 = var3;
        var3 = 1.0F - (float)((Math.cos((double)var3 * Math.PI) + 1.0D) / 2.0D);
        var3 = var4 + (var3 - var4) / 3.0F;
        return var3;
    }

    @Unique
    public float getStarBrightness(float tickDelta) {
        float var2 = getCelestialAngle(tickDelta);
        float var3 = 1.0F - (MathHelper.cos(var2 * (float)Math.PI * 2.0F) * 2.0F + 12.0F / 16.0F);
        if(var3 < 0.0F) {
            var3 = 0.0F;
        }

        if(var3 > 1.0F) {
            var3 = 1.0F;
        }

        return var3 * var3 * 0.5F;
    }
}