package MisakaCode;

import basemod.BaseMod;
import basemod.interfaces.EditStringsSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.*;

@SpireInitializer
public class MisakaModInitializer implements
        EditStringsSubscriber {

    public MisakaModInitializer() {
        BaseMod.subscribe(this);
    }

    @Override
    public void receiveEditStrings() {
        switch (Settings.language) {
            default:
                BaseMod.loadCustomStringsFile(RelicStrings.class, "MisakaResources/localization/MisakaCharacterStrings.json");
                BaseMod.loadCustomStringsFile(PowerStrings.class, "MisakaResources/localization/MisakaCharacterStrings.json");
                BaseMod.loadCustomStringsFile(CardStrings.class, "MisakaResources/localization/MisakaCharacterStrings.json");
                BaseMod.loadCustomStringsFile(UIStrings.class, "MisakaResources/localization/MisakaCharacterStrings.json");
                BaseMod.loadCustomStrings(CharacterStrings.class, "MisakaResources/localization/MisakaCharacterStrings.json");
        }
    }

}
