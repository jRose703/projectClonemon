package BattleSystem;

import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class DamageCalculation {

    private static final Map<FightingType, ArrayList<HashSet<FightingType>>> effectivenessTable = new HashMap<FightingType, ArrayList<HashSet<FightingType>>>();

    /**
     * This method lets a fighter attack. It calculates the damage inflicted by an attack
     * The damage is the difference between the attackers attack, the effectiveness of the type
     * and the defenders defense, but at least 1.
     */
    public static void calculateDamage(Fighter attacker, Fighter defender) {
        double effectiveness = checkEffectiveness(attacker.getType(), defender.getType());
        Random random = new Random();
        int critMultiplier = (random.nextInt(1, 100) >= 98) ? 2 : 1;

        double calculatedDamage = attacker.getAttackStat() * effectiveness * critMultiplier - defender.getDefenseStat();
        int resultingDamage = Math.max((int) calculatedDamage, 1);
        defender.inflictDamage(resultingDamage);
        System.out.println("Effectiveness: " + effectiveness);
        System.out.println("Damage: " + resultingDamage);
        System.out.println("Crit multiplier: " + critMultiplier);
    }

    /** Looks up the effectiveness of the attackers type against the defenders type in a hashmap. */
    private static double checkEffectiveness(FightingType attackType, FightingType defendingType) {
        if (effectivenessTable.isEmpty())
            createEffectivenessTable();

        List<HashSet<FightingType>> effectiveness = effectivenessTable.getOrDefault(defendingType, null);
        if (effectiveness == null)
            throw new IllegalStateException("Reading the effective table must have gone wrong!");

        Set<FightingType> halfEffective = effectiveness.get(0);
        Set<FightingType> doubleEffective = effectiveness.get(1);
        if (halfEffective.contains(attackType))
            return 0.5;
        if (doubleEffective.contains(attackType))
            return 2;
        return 1;

    }

    /**
     * This method reads the json file that saves how types are effective against each other
     * and saves it in a hashmap.
     */
    private static void createEffectivenessTable() {
        try (Reader reader = new FileReader("SaveFiles/effectivenessTable.json")) {

            JsonReader jsonReader = new JsonReader(reader);
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String key;
                HashSet<FightingType> halfEffective = new HashSet<>();
                HashSet<FightingType> doubleEffective = new HashSet<>();
                ArrayList<HashSet<FightingType>> effectiveness = new ArrayList<>();

                key = jsonReader.nextName();
                jsonReader.beginArray();
                jsonReader.beginArray();
                while (jsonReader.hasNext())
                    halfEffective.add(FightingType.valueOf(jsonReader.nextString()));
                jsonReader.endArray();
                jsonReader.beginArray();
                while (jsonReader.hasNext())
                    doubleEffective.add(FightingType.valueOf(jsonReader.nextString()));
                jsonReader.endArray();
                jsonReader.endArray();
                effectiveness.add(halfEffective);
                effectiveness.add(doubleEffective);
                effectivenessTable.put(FightingType.valueOf(key), effectiveness);
            }
            jsonReader.endObject();

        } catch (IOException e) {
            throw new IllegalStateException("FILE NOT FOUND!");
        }
    }
}
