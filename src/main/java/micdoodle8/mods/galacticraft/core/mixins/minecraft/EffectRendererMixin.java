package micdoodle8.mods.galacticraft.core.mixins.minecraft;

import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;

import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.entity.Entity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(EffectRenderer.class)
public class EffectRendererMixin {

    @Inject(method = "renderParticles", at = @At("HEAD"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, require = 1)
    private void onRenderParticles(Entity entity, float partialTicks, CallbackInfo callbackInfo) {
        ClientProxyCore.renderFootprints(partialTicks);
    }
}
