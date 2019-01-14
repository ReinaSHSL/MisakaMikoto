package MisakaCode.patches;

import MisakaCode.MisakaModInitializer;
import MisakaCode.tools.TextureLoader;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;

public class RenderMagneticCardBackgrounds {
    @SpirePatch(
            clz= AbstractCard.class,
            method="renderCardBg"
    )
    public static class SmallCardBackgroundRender {
        public static void Postfix(AbstractCard __instance, SpriteBatch sb, float x, float y) {
            Color reflectedColor = (Color) ReflectionHacks.getPrivate(__instance, AbstractCard.class, "renderColor");
            boolean isPositive = MisakaModInitializer.isPositive(__instance);
            boolean isNegative = MisakaModInitializer.isNegative(__instance);
            if (isPositive) {
                switch(__instance.type) {
                    case ATTACK:
                        Texture positiveAttack = TextureLoader.getTexture("MisakaResources/images/512/attack_red.png");
                        sb.setColor(reflectedColor);
                        sb.draw(positiveAttack, x, y, 256f, 256f, 512f, 512f, __instance.drawScale * Settings.scale, __instance.drawScale * Settings.scale, __instance.angle, 0, 0, 512, 512, false, false);
                        break;
                    case SKILL: case STATUS: case CURSE:
                        Texture positiveSkill = TextureLoader.getTexture("MisakaResources/images/512/skill_red.png");
                        sb.setColor(reflectedColor);
                        sb.draw(positiveSkill, x, y, 256f, 256f, 512f, 512f, __instance.drawScale * Settings.scale, __instance.drawScale * Settings.scale, __instance.angle, 0, 0, 512, 512, false, false);
                        break;
                    case POWER:
                        Texture positivePower = TextureLoader.getTexture("MisakaResources/images/512/power_red.png");
                        sb.setColor(reflectedColor);
                        sb.draw(positivePower, x, y, 256f, 256f, 512f, 512f, __instance.drawScale * Settings.scale, __instance.drawScale * Settings.scale, __instance.angle, 0, 0, 512, 512, false, false);
                        break;
                }
            }
            if (isNegative) {
                switch(__instance.type) {
                    case ATTACK:
                        Texture negativeAttack = TextureLoader.getTexture("MisakaResources/images/512/attack_blue.png");
                        sb.setColor(reflectedColor);
                        sb.draw(negativeAttack, x, y, 256f, 256f, 512f, 512f, __instance.drawScale * Settings.scale, __instance.drawScale * Settings.scale, __instance.angle, 0, 0, 512, 512, false, false);
                        break;
                    case SKILL: case STATUS: case CURSE:
                        Texture negativeSkill = TextureLoader.getTexture("MisakaResources/images/512/skill_blue.png");
                        sb.setColor(reflectedColor);
                        sb.draw(negativeSkill, x, y, 256f, 256f, 512f, 512f, __instance.drawScale * Settings.scale, __instance.drawScale * Settings.scale, __instance.angle, 0, 0, 512, 512, false, false);
                        break;
                    case POWER:
                        Texture negativePower = TextureLoader.getTexture("MisakaResources/images/512/power_blue.png");
                        sb.setColor(reflectedColor);
                        sb.draw(negativePower, x, y, 256f, 256f, 512f, 512f, __instance.drawScale * Settings.scale, __instance.drawScale * Settings.scale, __instance.angle, 0, 0, 512, 512, false, false);
                        break;
                }
            }
        }
    }

    @SpirePatch(
            clz = SingleCardViewPopup.class,
            method = "renderCardBack"
    )
    public static class RenderBigCardBackground {
        public static void Postfix(SingleCardViewPopup __instance, SpriteBatch sb) {
            AbstractCard c = (AbstractCard) ReflectionHacks.getPrivate(__instance, SingleCardViewPopup.class, "card");
            boolean isPositive = MisakaModInitializer.isPositive(c);
            boolean isNegative = MisakaModInitializer.isNegative(c);
            if (isPositive) {
                switch(c.type) {
                    case ATTACK:
                        Texture positiveAttack = TextureLoader.getTexture("MisakaResources/images/1024/attack_red.png");
                        sb.draw(positiveAttack, Settings.WIDTH / 2.0f - 512.0f, Settings.HEIGHT / 2.0f - 512.0f, 512.0f, 512.0f, 1024.0f, 1024.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 1024, 1024, false, false);
                        break;
                    case SKILL: case STATUS: case CURSE:
                        Texture positiveSkill = TextureLoader.getTexture("MisakaResources/images/1024/skill_red.png");
                        sb.draw(positiveSkill, Settings.WIDTH / 2.0f - 512.0f, Settings.HEIGHT / 2.0f - 512.0f, 512.0f, 512.0f, 1024.0f, 1024.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 1024, 1024, false, false);
                        break;
                    case POWER:
                        Texture positivePower = TextureLoader.getTexture("MisakaResources/images/1024/power_red.png");
                        sb.draw(positivePower, Settings.WIDTH / 2.0f - 512.0f, Settings.HEIGHT / 2.0f - 512.0f, 512.0f, 512.0f, 1024.0f, 1024.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 1024, 1024, false, false);
                        break;
                }
            }
            if (isNegative) {
                switch(c.type) {
                    case ATTACK:
                        Texture negativeAttack = TextureLoader.getTexture("MisakaResources/images/1024/attack_blue.png");
                        sb.draw(negativeAttack, Settings.WIDTH / 2.0f - 512.0f, Settings.HEIGHT / 2.0f - 512.0f, 512.0f, 512.0f, 1024.0f, 1024.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 1024, 1024, false, false);
                        break;
                    case SKILL: case STATUS: case CURSE:
                        Texture negativeSkill = TextureLoader.getTexture("MisakaResources/images/1024/skill_blue.png");
                        sb.draw(negativeSkill, Settings.WIDTH / 2.0f - 512.0f, Settings.HEIGHT / 2.0f - 512.0f, 512.0f, 512.0f, 1024.0f, 1024.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 1024, 1024, false, false);
                        break;
                    case POWER:
                        Texture negativePower = TextureLoader.getTexture("MisakaResources/images/1024/power_blue.png");
                        sb.draw(negativePower, Settings.WIDTH / 2.0f - 512.0f, Settings.HEIGHT / 2.0f - 512.0f, 512.0f, 512.0f, 1024.0f, 1024.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 1024, 1024, false, false);
                        break;
                }
            }
        }
    }
}
