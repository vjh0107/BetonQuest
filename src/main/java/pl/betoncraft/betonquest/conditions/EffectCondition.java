/**
 * BetonQuest - advanced quests for Bukkit
 * Copyright (C) 2015  Jakub "Co0sh" Sapalski
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pl.betoncraft.betonquest.conditions;

import org.bukkit.potion.PotionEffectType;

import pl.betoncraft.betonquest.api.Condition;
import pl.betoncraft.betonquest.utils.Debug;
import pl.betoncraft.betonquest.utils.PlayerConverter;

/**
 * Requires the player to have active potion effect
 * 
 * @author Co0sh
 */
public class EffectCondition extends Condition {

    private PotionEffectType type;

    public EffectCondition(String playerID, String instructions) {
        super(playerID, instructions);
        String[] parts = instructions.split(" ");
        if (parts.length < 2) {
            Debug.error("Effect type not defined in effect condition: " + instructions);
            isOk = false;
            return;
        }
        type = PotionEffectType.getByName(parts[1]);
        if (type == null) {
            Debug.error("Effect does not exist: " + parts[1]);
            isOk = false;
            return;
        }
    }

    @Override
    public boolean isMet() {
        if (!isOk) {
            Debug.error("There was an error, returning false.");
            return false;
        }
        if (PlayerConverter.getPlayer(playerID).hasPotionEffect(type)) {
            return true;
        }
        return false;
    }

}
