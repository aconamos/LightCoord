package net.aconamos.lightcoord.mixin;

import net.aconamos.lightcoord.Config;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class HUDMixin {
    MinecraftClient client = MinecraftClient.getInstance();

    @Inject(method = "render", at = @At("HEAD"))
    private void render(MatrixStack matrices, float tickDelta, CallbackInfo callbackInfo) {
        if (this.client.options.debugEnabled) return;
        assert this.client.player != null;
        Vec3d pos = this.client.player.getPos();
        this.client.textRenderer.drawWithShadow(matrices, String.format("x: %.1f", pos.x), Config.x_offset, Config.y_offset + Config.y_margin * 0, Config.color);
        this.client.textRenderer.drawWithShadow(matrices, String.format("y: %.1f", pos.y), Config.x_offset, Config.y_offset + Config.y_margin * 1, Config.color);
        this.client.textRenderer.drawWithShadow(matrices, String.format("z: %.1f", pos.z), Config.x_offset, Config.y_offset + Config.y_margin * 2, Config.color);
    }
}
