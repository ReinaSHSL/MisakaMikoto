package MisakaCode;

import MisakaCode.cards.MisakaDynamicVariable;
import MisakaCode.character.MisakaMikoto;
import MisakaCode.patches.AbstractCardEnum;
import MisakaCode.patches.MisakaMikotoEnum;
import MisakaCode.tools.CardFilter;
import MisakaCode.tools.CardIgnore;
import MisakaCode.tools.CardNoSeen;
import basemod.BaseMod;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.clapper.util.classutil.ClassFinder;
import javassist.CtClass;
import org.clapper.util.classutil.*;

import java.io.File;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

@SpireInitializer
public class MisakaModInitializer implements
        EditStringsSubscriber,
        EditCharactersSubscriber,
        EditCardsSubscriber {
    public static final Color MAGNETIC = CardHelper.getColor(255.0f, 255.0f, 255.0f);
    private static final String ATTACK_MAGNETIC = "MisakaResources/images/512/attack_silver.png";
    private static final String SKILL_MAGNETIC = "MisakaResources/images/512/skill_silver.png";
    private static final String POWER_MAGNETIC = "MisakaResources/images/512/power_silver.png";
    private static final String ENERGY_ORB_MAGNETIC = "MisakaResources/images/512/energy_orb_magnetic.png";

    private static final String ATTACK_MAGNETIC_1024 = "MisakaResources/images/1024/attack_silver.png";
    private static final String SKILL_MAGNETIC_1024 = "MisakaResources/images/1024/skill_silver.png";
    private static final String POWER_MAGNETIC_1024 = "MisakaResources/images/1024/power_silver.png";
    private static final String ENERGY_ORB_GREY_1024 = "MisakaResources/images/1024/energy_orb_magnetic.png";

    private static CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("MisakaMikoto");

    public MisakaModInitializer() {
        BaseMod.subscribe(this);
        BaseMod.addColor(AbstractCardEnum.MISAKA_MAGNETIC,
                MAGNETIC, MAGNETIC, MAGNETIC, MAGNETIC, MAGNETIC, MAGNETIC, MAGNETIC,
                ATTACK_MAGNETIC, SKILL_MAGNETIC, POWER_MAGNETIC, ENERGY_ORB_MAGNETIC,
                ATTACK_MAGNETIC_1024, SKILL_MAGNETIC_1024, POWER_MAGNETIC_1024,
                ENERGY_ORB_GREY_1024);}

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

        @Override
        public void receiveEditCharacters() {
            BaseMod.addCharacter(new MisakaMikoto(characterStrings.NAMES[0]),
                    "MisakaResources/images/character/button.png", "MisakaResources/images/character/portrait.png",
                    MisakaMikotoEnum.THE_RAILGUN);
        }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new MisakaDynamicVariable());
        try {
            autoAddCards();
        } catch (URISyntaxException | IllegalAccessException | InstantiationException | NotFoundException | CannotCompileException e) {
            e.printStackTrace();
        }
    }

    private static void autoAddCards() throws URISyntaxException, IllegalAccessException, InstantiationException, NotFoundException, CannotCompileException
    {
        ClassFinder finder = new ClassFinder();
        URL url = MisakaModInitializer.class.getProtectionDomain().getCodeSource().getLocation();
        finder.add(new File(url.toURI()));

        ClassFilter filter =
                new AndClassFilter(
                        new NotClassFilter(new InterfaceOnlyClassFilter()),
                        new NotClassFilter(new AbstractClassFilter()),
                        new ClassModifiersClassFilter(Modifier.PUBLIC),
                        new CardFilter()
                );
        Collection<ClassInfo> foundClasses = new ArrayList<>();
        finder.findClasses(foundClasses, filter);

        for (ClassInfo classInfo : foundClasses) {
            CtClass cls = Loader.getClassPool().get(classInfo.getClassName());
            if (cls.hasAnnotation(CardIgnore.class)) {
                continue;
            }
            boolean isCard = false;
            CtClass superCls = cls;
            while (superCls != null) {
                superCls = superCls.getSuperclass();
                if (superCls == null) {
                    break;
                }
                if (superCls.getName().equals(AbstractCard.class.getName())) {
                    isCard = true;
                    break;
                }
            }
            if (!isCard) {
                continue;
            }

            System.out.println(classInfo.getClassName());
            AbstractCard card = (AbstractCard) Loader.getClassPool().toClass(cls).newInstance();
            BaseMod.addCard(card);
            if (cls.hasAnnotation(CardNoSeen.class)) {
                UnlockTracker.hardUnlockOverride(card.cardID);
            } else {
                UnlockTracker.unlockCard(card.cardID);
            }
        }
    }
}
