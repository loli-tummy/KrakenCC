package pictures.cunny.kraken;

import meteordevelopment.meteorclient.addons.MeteorAddon;
import meteordevelopment.meteorclient.systems.modules.Modules;

public class Kraken extends MeteorAddon {

    @Override
    public void onInitialize() {
        // Modules
        Modules.get().add(new ConstDeath());
    }

    @Override
    public String getPackage() {
        return "pictures.cunny.kraken";
    }
}
