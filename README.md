# InvadedSoup v1.0 by Yochran
A replica of InvadedLand's soup core.

# Note
This plugin is FAR from complete. Once it is done (and after I get MegaPvP's permission), I will release this plugin to the public. Until then, it remains open source and incomplete, until of course I finish it.

# Features

### Commands:
  - Soup: Get a list of the subcommands for the soup core.
  - SetSoupSpawn: Set the spawnpoint for the server.
  - Help: Get the help for the server.
  - Spawn: Teleports you to the spawnpoint.
  - Toggledrops: Toggles item droppings (minus soups)
  - More coming soon...
  
### Listeners:
  - A Kit selector GUI.
  - A Help item that is binded with the /help command.
  - Login events, that sets the players spawnpoint, clears their inventory, kit, and gives them the spawn items.
  - Custom Kit GUI  that has a bunch of kits you can choose from.
  - Custom help listener, when you right click the item it displays the help page.
  - Urgal listener, when you kill a player with this kit you get 5s of strength 1.
  - Switcher listener, when you throw a snowball at a player with switcher, you switch places with them. When you kill a player you get 5 extra snowballs.
  - Item Drop listener, prevents item drops if /tdp is not enabled.
  - Death item drop listener, removes all item drops on death, except for player's soups.
  - More coming soon...
  
### Runnables:
  - Added potion runnable that replaces all soups with potions if using the potion kit.
  - Added a spawn runnable that does a countdown for the /spawn command, and if you move it cancels.
  
The original InvadedLands soup server core was created by MegaPVP, this is simply a replica.
