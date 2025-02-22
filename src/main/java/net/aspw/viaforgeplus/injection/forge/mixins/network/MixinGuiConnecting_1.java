package net.aspw.viaforgeplus.injection.forge.mixins.network;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import net.aspw.viaforgeplus.ProtocolBase;
import net.aspw.viaforgeplus.api.ExtendedServerData;
import net.aspw.viaforgeplus.network.MinecraftInstance;
import net.minecraft.network.NetworkManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.net.InetAddress;

@Mixin(targets = "net.minecraft.client.multiplayer.GuiConnecting$1")
public class MixinGuiConnecting_1 {

    @Redirect(method = "run", at = @At(value = "INVOKE", target = "Lnet/minecraft/network/NetworkManager;createNetworkManagerAndConnect(Ljava/net/InetAddress;IZ)Lnet/minecraft/network/NetworkManager;"), remap = false)
    public NetworkManager trackVersion(InetAddress address, int i, boolean b) {
        if (MinecraftInstance.mc.getCurrentServerData() instanceof ExtendedServerData) {
            final ProtocolVersion version = ((ExtendedServerData) MinecraftInstance.mc.getCurrentServerData()).viaForge$getVersion();
            if (version != null) {
                ProtocolBase.getManager().setTargetVersionSilent(version);
            }
        }

        return NetworkManager.createNetworkManagerAndConnect(address, i, b);
    }
}