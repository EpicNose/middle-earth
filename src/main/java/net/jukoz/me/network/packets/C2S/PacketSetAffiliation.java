package net.jukoz.me.network.packets.C2S;

import net.jukoz.me.MiddleEarth;
import net.jukoz.me.network.contexts.ServerPacketContext;
import net.jukoz.me.network.packets.ClientToServerPacket;
import net.jukoz.me.resources.StateSaverAndLoader;
import net.jukoz.me.resources.persistent_datas.AffiliationData;
import net.jukoz.me.resources.persistent_datas.PlayerData;
import net.jukoz.me.utils.LoggerUtil;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public class PacketSetAffiliation extends ClientToServerPacket<PacketSetAffiliation>
{
    public static final CustomPayload.Id<PacketSetAffiliation> ID = new CustomPayload.Id<>(Identifier.of(MiddleEarth.MOD_ID, "packet_affiliation_packet"));

    public static final PacketCodec<RegistryByteBuf, PacketSetAffiliation> CODEC = PacketCodec.tuple(
            PacketCodecs.STRING, p -> p.alignmentName,
            PacketCodecs.STRING, p -> p.factionName,
            PacketCodecs.STRING, p -> p.spawnName,
            PacketSetAffiliation::new
    );

    private final String alignmentName;
    private final String factionName;
    private final String spawnName;


    public PacketSetAffiliation(String alignmentName, String factionName, String spawnName){
        this.alignmentName = alignmentName;
        this.factionName = factionName;
        this.spawnName = spawnName;
    }

    @Override
    public Id<PacketSetAffiliation> getId() {
        return ID;
    }

    @Override
    public PacketCodec<RegistryByteBuf, PacketSetAffiliation> streamCodec() {
        return CODEC;
    }

    @Override
    public void process(ServerPacketContext context) {
        context.player().getServer().execute(() -> {
            try{
                PlayerData playerState = StateSaverAndLoader.getPlayerState(context.player());

                AffiliationData affiliationData = new AffiliationData(alignmentName, factionName, spawnName);
                playerState.setAffiliationData(affiliationData);
            } catch (Exception e){
                LoggerUtil.logError("AffiliationPacket::Tried getting affiliation packet and couldn't fetch any.", e);
            }
        });
    }
}