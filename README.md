Adds a world type for a "void" world that works for multiplayer and teams.



Basic Instructions

Make sure CompatLayer is installed (1.10/1.11 Only)
Choose the world type Void? World in single-player, or voidworld on servers.
Type /island create <optional type> to start on an island


Comes with 5 default island types that all have some customize configs and can enabled/disabled:

- Grass
- Sand
- Snow
- Wood
Garden of Glass (**Only if enabled in config and Botania and Garden of Glass are installed**)


Custom Islands

Uses structure block data (located in the "save folder/structures")
Place the .nbt files made using structure blocks in the "voidislandcontrolstructures" config folder
To set a spawn point, place down a structure block in data mode.  Set the tag to "spawn_point".
Add the island to the Custom Islands list in the config


These island types work with Sky Resources as starting islands

(Incompatible with Sky Resources on 1.10)


Customize some of these included configs:

- Fill blocks for under the islands
- Get rid of bedrock
- Generate an overworld (and even with a single biome)
- Cloud and horizon Y level
- Island Y level, Size, Buffer Distance
- Have a void nether dimension, and have portals link 1-1 to the overworld
- One Chunk Mode on 1 island
- Starter Empty Chest
- Starting Inventory
  - Use the startingInv command to easily set it!
  - Just put the items in your inventory and use the command!
- Have command blocks spawn for each island
- Run commands on world start
- Along with more advanced configs!

The /island command has many sub-commands.

- create (optional int/string)[type] - The create command creates a new island at a position in the world.
- invite [player] - The invite command asks another player join your island.
- join - The join command will have you join an island if recently asked by the invite command.
- leave - The leave command has you leave the island you're on, go to spawn, and clears the inventory. If you are the last person on the island, the island can't be claimed again.
- home - The home command teleports the player back to their home island, but has to be by default 500 blocks away.
- spawn - The spawn command teleports the player to spawn.
- reset (optional int/string)[type] - The reset command clears the island, the inventory, and starts brand new like the create command does.
- onechunk - The onechunk command creates a world border of one chunk size at spawn and resets spawn. This command is disabled by default and has to be changed in the config.