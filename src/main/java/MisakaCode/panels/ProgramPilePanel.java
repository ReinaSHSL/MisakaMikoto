package MisakaCode.panels;

import MisakaCode.MisakaModInitializer;
import MisakaCode.patches.ProgramCardsPatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.panels.AbstractPanel;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BobEffect;
import com.megacrit.cardcrawl.vfx.DiscardGlowEffect;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

import java.util.ArrayList;
import java.util.Iterator;

public class ProgramPilePanel extends AbstractPanel {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("misaka:ProgramPilePanel");
    public static final String[] TEXT = uiStrings.TEXT;

    private static final int RAW_W = 128;
    private static final float COUNT_CIRCLE_W = 128.0F * Settings.scale;
    private static final float DECK_X = 180.0F * Settings.scale - 64.0F;
    private static final float DECK_Y = 70.0F * Settings.scale - 64.0F;
    private static final float COUNT_X = 134.0F * Settings.scale;
    private static final float COUNT_Y = 48.0F * Settings.scale;
    private static final float COUNT_OFFSET_X = 70.0F * Settings.scale;
    private static final float COUNT_OFFSET_Y = -18.0F * Settings.scale;
    private static final float DECK_TIP_X = 1550.0F * Settings.scale;
    private static final float DECK_TIP_Y = 470.0F * Settings.scale;
    private static final float HITBOX_W = 120.0F * Settings.scale;
    private static final float HITBOX_W2 = 450.0F * Settings.scale;
    private float scale = 1.0F;
    private Color glowColor = Color.WHITE.cpy();
    private float glowAlpha = 0.0F;
    private GlyphLayout gl = new GlyphLayout();
    private BobEffect bob = new BobEffect(1.0F);
    private ArrayList<DiscardGlowEffect> vfxAbove = new ArrayList<>();
    private ArrayList<DiscardGlowEffect> vfxBelow = new ArrayList<>();
    private Hitbox hb = new Hitbox(Settings.WIDTH - HITBOX_W, 0.0F, HITBOX_W, HITBOX_W);
    private Hitbox bannerHb = new Hitbox(Settings.WIDTH - HITBOX_W2, 0.0F, HITBOX_W2, HITBOX_W);

    public ProgramPilePanel() {
        super(Settings.WIDTH/2F * Settings.scale, 0.0F, Settings.WIDTH, -300.0F * Settings.scale, 8.0F * Settings.scale, 0.0F, null, true);
    }


    public void updatePositions() {
        super.updatePositions();
        bob.update();
        updateVfx();

        if (!isHidden) {
            hb.update();
            bannerHb.update();
            updatePop();
        }


        if ((hb.hovered) && ((!AbstractDungeon.isScreenUp) || (AbstractDungeon.screen == ProgramCardsPatch.PROGRAM_VIEW) || (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.HAND_SELECT))) {
            AbstractDungeon.overlayMenu.hoveredTip = true;
            if (com.megacrit.cardcrawl.helpers.input.InputHelper.justClickedLeft) {
                hb.clickStarted = true;
            }
        }


        if (hb.clicked && AbstractDungeon.screen == ProgramCardsPatch.PROGRAM_VIEW) {

            hb.clicked = false;
            hb.hovered = false;
            bannerHb.hovered = false;
            CardCrawlGame.sound.play("DECK_CLOSE");
            if (AbstractDungeon.previousScreen == ProgramCardsPatch.PROGRAM_VIEW) {
                AbstractDungeon.previousScreen = null;
            }
            AbstractDungeon.closeCurrentScreen();
            return;
        }


        glowAlpha += Gdx.graphics.getDeltaTime() * 3.0F;
        if (glowAlpha < 0.0F) {
            glowAlpha *= -1.0F;
        }
        float tmp = MathUtils.cos(glowAlpha);
        if (tmp < 0.0F) {
            glowColor.a = (-tmp / 2.0F);
        } else {
            glowColor.a = (tmp / 2.0F);
        }


        if (hb.clicked && (AbstractDungeon.overlayMenu.combatPanelsShown) &&
                (AbstractDungeon.getMonsters() != null) &&
                (!AbstractDungeon.getMonsters().areMonstersDead()) && (!AbstractDungeon.player.isDead)) {
            hb.clicked = false;
            hb.hovered = false;
            bannerHb.hovered = false;

            if (AbstractDungeon.isScreenUp) {
                if (AbstractDungeon.previousScreen == null) {
                    AbstractDungeon.previousScreen = AbstractDungeon.screen;
                }
            } else {
                AbstractDungeon.previousScreen = null;
            }

                openProgramPile();
        }
    }

    private void openProgramPile() {
        AbstractPlayer p = AbstractDungeon.player;

        if (ProgramCardsPatch.ProgramPileField.programPile.get(AbstractDungeon.player).size() != 0) {
            MisakaModInitializer.programView.open();
        } else {
            AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0F, TEXT[0], true));
        }

        hb.hovered = false;
        InputHelper.justClickedLeft = false;
    }


    private void updateVfx() {
        for (DiscardGlowEffect i : vfxAbove) {
            i.update();
        }
        for (DiscardGlowEffect i : vfxBelow) {
            i.update();
        }
        if ((vfxAbove.size() < 9) && (!Settings.DISABLE_EFFECTS)) {
            vfxAbove.add(new DiscardGlowEffect(true));
        }
        if ((vfxBelow.size() < 9) && (!Settings.DISABLE_EFFECTS)) {
            vfxBelow.add(new DiscardGlowEffect(false));
        }
    }

    private void updatePop() {
        if (scale != 1.0F) {
            scale = MathUtils.lerp(scale, 1.0F, Gdx.graphics.getDeltaTime() * 8.0F);
            if (Math.abs(scale - 1.0F) < 0.003F) {
                scale = 1.0F;
            }
        }
    }

    public void pop() {
        scale = Settings.POP_AMOUNT;
    }

    public void render(SpriteBatch sb) {
        if (!Settings.hideLowerElements) {
            renderButton(sb);


            String msg = Integer.toString(ProgramCardsPatch.ProgramPileField.programPile.get(AbstractDungeon.player).size());
            gl.setText(FontHelper.deckCountFont, msg);
            sb.setColor(Color.WHITE);
            sb.draw(ImageMaster.DECK_COUNT_CIRCLE, current_x + COUNT_OFFSET_X, current_y + COUNT_OFFSET_Y, COUNT_CIRCLE_W, COUNT_CIRCLE_W);

            FontHelper.renderFontCentered(sb, FontHelper.deckCountFont, msg, current_x + COUNT_X, current_y + COUNT_Y);

            if (!isHidden) {
                hb.render(sb);
                if (AbstractDungeon.screen == ProgramCardsPatch.PROGRAM_VIEW) {
                    bannerHb.render(sb);
                }
            }
        }
    }

    private void renderButton(SpriteBatch sb) {
        if ((hb.hovered) || ((bannerHb.hovered) && (AbstractDungeon.screen == ProgramCardsPatch.PROGRAM_VIEW))) {
            scale = 1.2F;
        }

        for (DiscardGlowEffect e : vfxBelow) {
            e.render(sb, current_x - 1664.0F * Settings.scale, current_y + bob.y * 0.5F);
        }

        sb.setColor(Color.WHITE);
        sb.draw(ImageMaster.DISCARD_BTN_BASE, current_x + DECK_X, current_y + DECK_Y + bob.y / 2.0F, 64.0F, 64.0F, 128.0F, 128.0F, scale * Settings.scale, scale * Settings.scale, 0.0F, 0, 0, 128, 128, false, false);


        for (DiscardGlowEffect e : vfxAbove) {
            e.render(sb, current_x - 1664.0F * Settings.scale, current_y + bob.y * 0.5F);
        }
    }
}
