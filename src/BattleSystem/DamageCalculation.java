package BattleSystem;

import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

public class DamageCalculation {

    private static Map<FightingType, ArrayList<HashSet<FightingType>>> effectivenessTable = new HashMap<FightingType, ArrayList<HashSet<FightingType>>>();

    public static void calculateDamage(Fighter attacker, Fighter defender) {
        double effectiveness = checkEffectiveness(attacker.getType(), defender.getType());
        System.out.println(effectiveness);
    }

    private static double checkEffectiveness(FightingType attackType, FightingType defendingType) {
        if (effectivenessTable.isEmpty())
            createEffectivenessTable();

        List<HashSet<FightingType>> effectiveness = effectivenessTable.getOrDefault(defendingType, null);
        Set<FightingType> halfEffective = effectiveness.get(0);
        Set<FightingType> doubleEffective = effectiveness.get(1);
        if (halfEffective.contains(attackType))
            return 0.5;
        if (doubleEffective.contains(attackType))
            return 2;
        return 1;

    }

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
