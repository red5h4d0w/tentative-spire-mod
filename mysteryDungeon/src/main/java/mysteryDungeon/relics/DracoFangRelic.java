package mysteryDungeon.relics;

import mysteryDungeon.MysteryDungeon;
import mysteryDungeon.abstracts.PokemonRelic;
import mysteryDungeon.pokemons.Charmander;
import mysteryDungeon.util.TextureLoader;

import static mysteryDungeon.MysteryDungeon.makeRelicOutlinePath;
import static mysteryDungeon.MysteryDungeon.makeRelicPath;

import java.util.HashSet;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RelicStrings;

public class DracoFangRelic extends PokemonRelic {

    // ID, images, text.
    public static final String ID = MysteryDungeon.makeID(DracoFangRelic.class);

    private static final RelicStrings relicStrings = CardCrawlGame.languagePack.getRelicStrings(ID);
    public static final String NAME = relicStrings.NAME;
    public static final String[] DESCRIPTIONS = relicStrings.DESCRIPTIONS;
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    public DracoFangRelic() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.CLINK);
        cardColors = new HashSet<CardColor>() {
            {
                add(Charmander.CARD_COLOR);
            }
        };
        counter = 0;
    }

    @Override
    public void onExhaust(AbstractCard card) {
        super.onExhaust(card);
        counter++; 
        if (counter == 9) {
            beginPulse();
            pulse = true;
        }
        if(counter == 10) {
            addToBot(new ExhumeAction(false));
            counter = 0;
            stopPulse();
        }
    }

    @Override
    public void atBattleStart() {
        super.atBattleStart();
        if (counter == 9) {
            beginPulse();
            pulse = true;
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
