package fireopal.profundis.util;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.biome.source.util.MultiNoiseUtil.MultiNoiseSampler;
import net.minecraft.world.biome.source.util.MultiNoiseUtil.NoiseValuePoint;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class FireopalMultinoiseCommand {
    public static void register() {
        // CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
        //     dispatcher.register(
        //         (LiteralArgumentBuilder<ServerCommandSource>) CommandManager.literal("multinoise").requires(source -> source.hasPermissionLevel(2))
        //             .then(CommandManager.argument("pos", BlockPosArgumentType.blockPos())
        //                 .executes(context -> FireopalMultinoiseCommand.execute((ServerCommandSource)context.getSource(), BlockPosArgumentType.getLoadedBlockPos(context, "pos"))
        //             )
        //         )
        //     );
        // });
    }

    public static int execute(ServerCommandSource source, BlockPos pos) {
        ServerWorld serverWorld = source.getWorld();
        ChunkGenerator chunkGenerator = serverWorld.getChunkManager().getChunkGenerator();
        MultiNoiseSampler sampler = chunkGenerator.getMultiNoiseSampler();
        NoiseValuePoint n = sampler.sample(pos.getX(), pos.getY(), pos.getZ());

        LiteralText text = new LiteralText("The Multinoise values at " + pos.toString() + " are::\n C: " + MultiNoiseUtil.method_38666(n.continentalnessNoise()) + " E: " + MultiNoiseUtil.method_38666(n.erosionNoise()) + " T: " + MultiNoiseUtil.method_38666(n.temperatureNoise()) + " H: " + MultiNoiseUtil.method_38666(n.humidityNoise()) + " W: " + MultiNoiseUtil.method_38666(n.weirdnessNoise()));

        source.sendFeedback(text, false);
        return 0;
    }

}
