package net.jukoz.me.item.items;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.jukoz.me.config.ModServerConfigs;
import net.jukoz.me.network.packets.OnboardingDetailsRequestPacket;
import net.jukoz.me.network.packets.SpawnDataPacket;
import net.jukoz.me.resources.StateSaverAndLoader;
import net.jukoz.me.resources.persistent_datas.PlayerData;
import net.jukoz.me.utils.LoggerUtil;
import net.jukoz.me.world.dimension.ModDimensions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.joml.Vector3i;

public class StarlightPhialItem extends Item {
    public StarlightPhialItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if(world.isClient){
            ClientPlayNetworking.send(new OnboardingDetailsRequestPacket(ModDimensions.isInMiddleEarth(world),false, false));
        }
        return TypedActionResult.success(player.getStackInHand(hand));
    }
}
