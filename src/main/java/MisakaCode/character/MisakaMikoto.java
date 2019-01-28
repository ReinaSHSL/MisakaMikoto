package MisakaCode.character;

import java.util.ArrayList;

import MisakaCode.MisakaModInitializer;
import MisakaCode.cards.Defend_Magnetic;
import MisakaCode.cards.Neodymium;
import MisakaCode.cards.Strike_Magnetic;
import MisakaCode.cards.Thunderjolt;
import MisakaCode.monsters.NeodymiumMagnet;
import MisakaCode.patches.AbstractCardEnum;
import MisakaCode.patches.MisakaMikotoEnum;
import MisakaCode.relics.ArcadeToken;
import com.megacrit.cardcrawl.cards.blue.Strike_Blue;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.esotericsoftware.spine.AnimationState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;

import basemod.animations.AbstractAnimation;
import basemod.animations.SpriterAnimation;

import java.util.ArrayList;

public class MisakaMikoto extends CustomPlayer {
    public static final int ENERGY_PER_TURN = 3;
    public static final int STARTING_HP = 75;
    public static final int MAX_HP = 75;
    public static final int STARTING_GOLD = 99;
    public static final int CARD_DRAW = 9;
    public static final int ORB_SLOTS = 0;
    private static CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString("MisakaMikoto");

    public MisakaMikoto(String name) {
        super(name, MisakaMikotoEnum.THE_RAILGUN, null, null, new SpriterAnimation("MisakaResources/images/character/animations.scml"));
        this.dialogX = (this.drawX + 0.0F * Settings.scale);
        this.dialogY = (this.drawY + 220.0F * Settings.scale);

        initializeClass(null, "MisakaResources/images/character/shoulder2.png", "MisakaResources/images/character/shoulder.png", "MisakaResources/images/character/corpse.png",
                getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(characterStrings.NAMES[2], characterStrings.TEXT[2],
                STARTING_HP, MAX_HP, ORB_SLOTS, STARTING_GOLD, CARD_DRAW, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> starterDeck = new ArrayList<>();
        starterDeck.add(Strike_Magnetic.ID);
        starterDeck.add(Strike_Magnetic.ID);
        starterDeck.add(Strike_Magnetic.ID);
        starterDeck.add(Strike_Magnetic.ID);
        starterDeck.add(Defend_Magnetic.ID);
        starterDeck.add(Defend_Magnetic.ID);
        starterDeck.add(Defend_Magnetic.ID);
        starterDeck.add(Defend_Magnetic.ID);
        starterDeck.add(Thunderjolt.ID);
        starterDeck.add(NeodymiumMagnet.ID);
        return starterDeck;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> starterRelics = new ArrayList<>();
        starterRelics.add(ArcadeToken.ID);
        return starterRelics;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("ATTACK_MAGIC_BEAM_SHORT", 0.5f);
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "ATTACK_MAGIC_BEAM_SHORT";
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 7;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return AbstractCardEnum.MISAKA_MAGNETIC;
    }

    @Override
    public Color getCardTrailColor() {
        return MisakaModInitializer.MAGNETIC;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public String getLocalizedCharacterName() {
        return characterStrings.NAMES[0];
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Neodymium();
    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return characterStrings.NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new MisakaMikoto(this.name);
    }

    @Override
    public Color getCardRenderColor() {
        return MisakaModInitializer.MAGNETIC;
    }

    @Override
    public Color getSlashAttackColor() {
        return MisakaModInitializer.MAGNETIC;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[] {
                AbstractGameAction.AttackEffect.BLUNT_HEAVY
        };
    }

    @Override
    public String getSpireHeartText() {
        return characterStrings.TEXT[0];
    }

    @Override
    public String getVampireText() {
        return characterStrings.TEXT[1];
    }
}
