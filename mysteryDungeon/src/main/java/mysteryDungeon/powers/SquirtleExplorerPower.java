package mysteryDungeon.powers;

import basemod.interfaces.CloneablePowerInterface;
import mysteryDungeon.MysteryDungeon;
import mysteryDungeon.abstracts.PokemonTwoAmountPower;
import mysteryDungeon.actions.SimpleAction;
import mysteryDungeon.util.TextureLoader;

import static mysteryDungeon.MysteryDungeon.makePowerPath;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;


//Gain 1 dex for the turn for each card played.

public class SquirtleExplorerPower extends PokemonTwoAmountPower implements CloneablePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = MysteryDungeon.makeID(SquirtleExplorerPower.class);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath(SquirtleExplorerPower.class)+"84.png");
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath(SquirtleExplorerPower.class)+"32.png");

    public SquirtleExplorerPower(final AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;

        this.amount = 1;
        this.amount2 = 1;
        this.owner = owner;

        type = PowerType.BUFF;

        // We load those txtures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        this.amount2++;
    }

    @Override
    public AbstractPower makeCopy() {
        return new SquirtleExplorerPower(owner, amount);
    }

    @Override
    public void updateDescription() {
        if(amount==1) {
            description = String.format(DESCRIPTIONS[0], amount2, amount);
        }
        else {
            description = String.format(DESCRIPTIONS[1], amount2, amount);
        }
    }
    
    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new DrawCardAction(amount2));
        addToBot(new SimpleAction(() -> {
            amount2+=amount;
            updateDescription();
        }));
        super.atStartOfTurnPostDraw();
    }
}
    