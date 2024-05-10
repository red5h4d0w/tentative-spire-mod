package mysteryDungeon.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.RelicStrings;

import mysteryDungeon.MysteryDungeon;
import mysteryDungeon.abstracts.PokemonRelic;
import mysteryDungeon.pokemons.Bulbasaur;
import mysteryDungeon.util.TextureLoader;

import static mysteryDungeon.MysteryDungeon.makeRelicOutlinePath;
import static mysteryDungeon.MysteryDungeon.makeRelicPath;

import java.util.HashSet;

public class RoseIncenseRelic extends PokemonRelic {
    public static final String ID = MysteryDungeon.makeID(RoseIncenseRelic.class);

    private static final RelicStrings relicStrings = CardCrawlGame.languagePack.getRelicStrings(ID);
    public static final String NAME = relicStrings.NAME;
    public static final String[] DESCRIPTIONS = relicStrings.DESCRIPTIONS;
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("rose-incense.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("rose-incense.png"));

    public RoseIncenseRelic() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.CLINK);
        cardColors = new HashSet<CardColor>() {
            {
                add(Bulbasaur.CARD_COLOR);
            }
        };
    }

    @Override
    public int onPlayerHeal(int healAmount) {
        return 1 + super.onPlayerHeal(healAmount);
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
