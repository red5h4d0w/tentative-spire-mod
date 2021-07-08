package mysteryDungeon.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.stances.AbstractStance;

import mysteryDungeon.stances.PositivelyChargedStance;

public class StancePatch {
    @SpirePatch(clz = AbstractStance.class, method = "getStanceFromName")
    public static class DefensiveMode_StancePatch {
        @SpirePrefixPatch
        public static SpireReturn<AbstractStance> returnStance(String name) {
        if (name.equals(PositivelyChargedStance.STANCE_ID))
            return SpireReturn.Return(new PositivelyChargedStance());
        return SpireReturn.Continue();
        }
    }
}