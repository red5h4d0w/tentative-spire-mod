package mysteryDungeon.ui;


import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mysteryDungeon.MysteryDungeon;
import mysteryDungeon.characters.Pokemon;
import mysteryDungeon.stances.NegativeStance;
import mysteryDungeon.stances.PositiveStance;
import mysteryDungeon.util.TextureLoader;

public class PikachuMeter {
    public Logger logger = LogManager.getLogger(PikachuMeter.class);

    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(MysteryDungeon.makeID(PikachuMeter.class)); 

    private static final String TITLE = uiStrings.TEXT[0];

    private static final String DESCRIPTION = uiStrings.TEXT[1];

    private static int counterPosition = 0;

    public float x = 128f * Settings.xScale;
    public float y = Settings.HEIGHT/2;
    
    

    public Texture sliderBar = ImageMaster.OPTION_SLIDER_BG;

    public Hitbox gaugeHitbox = new Hitbox(x - sliderBar.getHeight(), y - sliderBar.getWidth()/2, 2*sliderBar.getHeight(), sliderBar.getWidth());

    public Texture sliderCursor = TextureLoader.getTexture("mysteryDungeonResources/images/ui/ChargeGaugeCursor.png");

    public Texture plusSymbol = TextureLoader.getTexture("mysteryDungeonResources/images/ui/add.png");

    public Hitbox plusHitbox = new Hitbox(x - plusSymbol.getWidth()/2, y + sliderBar.getWidth()/2, plusSymbol.getWidth(), plusSymbol.getHeight());

    public Texture minusSymbol = TextureLoader.getTexture("mysteryDungeonResources/images/ui/sub.png");

    public Hitbox minusHitbox = new Hitbox(x - plusSymbol.getWidth()/2, y - sliderBar.getWidth()/2 - plusSymbol.getHeight(), plusSymbol.getWidth(), plusSymbol.getHeight());

    public PikachuMeter() { }
    
    public void update() {
        plusHitbox.update();
        gaugeHitbox.update();
        minusHitbox.update();
    }
    
    public void render(SpriteBatch sb, AbstractPlayer player) {
        if (AbstractDungeon.getCurrMapNode() != null && 
            AbstractDungeon.getCurrRoom() != null && 
            (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT && !player.isDead) {
            ArrayList<PowerTip> powerTips = new ArrayList<PowerTip>();

            sb.draw(sliderBar, x - sliderBar.getWidth()/2, y - sliderBar.getHeight()/2, sliderBar.getWidth()/2, sliderBar.getHeight()/2, 250.0f, sliderBar.getHeight(), Settings.xScale, Settings.yScale, 90.0f, 0, 0, 250, 24, false, false);
            sb.draw(sliderCursor, x - 22f, y - 22f + Settings.yScale*counterPosition*112.5f/Pokemon.maxPikachuChargeCounter, 0, 0, 44f, 44f, Settings.xScale, Settings.yScale, 0, 0, 0, sliderCursor.getWidth(), sliderCursor.getHeight(), false, false);
            sb.draw(plusSymbol, x - plusSymbol.getWidth()/2, y + sliderBar.getWidth()/2);
            sb.draw(minusSymbol, x - plusSymbol.getWidth()/2, y - sliderBar.getWidth()/2 - plusSymbol.getHeight());
            
            if(plusHitbox.hovered) {
                StanceStrings positiveStanceStrings = CardCrawlGame.languagePack.getStanceString(PositiveStance.STANCE_ID);
                PowerTip positiveStanceTip = new PowerTip(positiveStanceStrings.NAME, positiveStanceStrings.DESCRIPTION[0]);
                
                powerTips.add(positiveStanceTip);
            }
            if(minusHitbox.hovered) {
                StanceStrings negativeStanceStrings = CardCrawlGame.languagePack.getStanceString(NegativeStance.STANCE_ID);
                PowerTip secondStanceTip = new PowerTip(negativeStanceStrings.NAME, negativeStanceStrings.DESCRIPTION[0]);
                powerTips.add(secondStanceTip);
            }
            if(gaugeHitbox.hovered) {
                PowerTip gaugeTip = new PowerTip(TITLE, DESCRIPTION);
                powerTips.add(gaugeTip);
            }

            TipHelper.queuePowerTips(InputHelper.mX + 60.0f*Settings.xScale, InputHelper.mY, powerTips);
            // FontHelper.renderFontLeftTopAligned(sb, FontHelper.tipHeaderFont, TITLE, x + TEXT_OFFSET_X, y + HEADER_OFFSET_Y, Settings.GOLD_COLOR);
            // FontHelper.renderSmartText(sb, FontHelper.tipBodyFont, DESCRIPTION, x + TEXT_OFFSET_X, y + BODY_OFFSET_Y, BODY_TEXT_WIDTH, TIP_DESC_LINE_SPACING, BASE_COLOR);
        } 
    }
    public void setCounterPosition(int newCounterPosition) {
        counterPosition = newCounterPosition;
    }
}
