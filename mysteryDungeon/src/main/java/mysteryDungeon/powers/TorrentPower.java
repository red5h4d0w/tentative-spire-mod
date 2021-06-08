package mysteryDungeon.powers;

import basemod.interfaces.CloneablePowerInterface;
import mysteryDungeon.MysteryDungeon;
import mysteryDungeon.interfaces.onShufflePowerInterface;
import mysteryDungeon.util.TextureLoader;

import static mysteryDungeon.MysteryDungeon.makePowerPath;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;


//Gain 1 dex for the turn for each card played.

public class TorrentPower extends MysteryDungeonPower implements CloneablePowerInterface, onShufflePowerInterface {
    public AbstractCreature source;

    public static final String POWER_ID = MysteryDungeon.makeID("TorrentPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    

    // We create 2 new textures *Using This Specific Texture Loader* - an 84x84 image and a 32x32 one.
    // There's a fallback "missing texture" image, so the game shouldn't crash if you accidentally put a non-existent file.
    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    public TorrentPower(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;

        // We load those txtures here.
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void onShufflePower() {
        addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, amount), amount));
        addToBot(new ApplyPowerAction(owner, owner, new LoseStrengthPower(owner, amount), amount));
    }

    @Override
    public AbstractPower makeCopy() {
        return new TorrentPower(owner, amount);
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], amount);
    }
}
