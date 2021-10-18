package net.blay09.mods.clienttweaks.tweak;

import net.blay09.mods.balm.api.Balm;
import net.blay09.mods.clienttweaks.ClientTweaksConfig;
import net.blay09.mods.clienttweaks.ClientTweaksConfigData;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OffhandTorchWithToolOnly extends AbstractClientTweak {

    public OffhandTorchWithToolOnly() {
        super("offhandTorchWithToolOnly");
    }

    @SubscribeEvent
    public void onRightClick(InputEvent.ClickInputEvent event) {
        if (isEnabled() && event.getHand() == InteractionHand.OFF_HAND) {
            Minecraft mc = Minecraft.getInstance();
            ItemStack heldItem = mc.player != null ? mc.player.getItemInHand(event.getHand()) : ItemStack.EMPTY;
            if (!heldItem.isEmpty()) {
                ResourceLocation registryName = Balm.getRegistries().getKey(heldItem.getItem());
                if (registryName != null) {
                    if (ClientTweaksConfig.getActive().customization.torchItems.contains(registryName.toString())) {
                        ItemStack mainItem = mc.player.getMainHandItem();
                        ResourceLocation mainRegistryName = !mainItem.isEmpty() ? mainItem.getItem().getRegistryName() : null;
                        if (mainRegistryName == null || !ClientTweaksConfig.getActive().customization.torchTools.contains(mainRegistryName.toString())) {
                            event.setCanceled(true);
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean isEnabled() {
        return ClientTweaksConfig.getActive().tweaks.offhandTorchWithToolOnly;
    }

    @Override
    public void setEnabled(boolean enabled) {
        Balm.getConfig().updateConfig(ClientTweaksConfigData.class, it -> it.tweaks.offhandTorchWithToolOnly = enabled);
    }
}
