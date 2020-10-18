# InvadedSoup v1.0 by Yochran
A replica of InvadedLand's soup core.

# Note
This plugin has been completed! If I do not recieve a response from MegaPvP (or luke/hyper) in a few days, I will release this plugin onto spigot and mc-market.

### Dependancies:
  - WorldGuard (5.7 or higher)
  - FastAsyncWorldEdit (Any version should work, 1.8.8 spigot version works best.)

# Features

### Current Kits:
  - PvP (no permission required)
  - Potion (no permission required)
  - Archer (no permission required)
  - Switcher (no permission required)
  - Urgal (no permission required)
  - Sonic (no permission required)
  - Viking (no permission required)
  - Stomper (no permission required)
  - Fisherman (Permission: soup.kits.fisherman)
  - Snowman (Permission: soup.kits.snowman)
  - Viper (Permission: soup.kits.viper)
  - Thor (Permission: soup.kits.thor)
  - Kangaroo (Permission: soup.kits.kangaroo)
  - Ninja (Permission: soup.kits.ninja)
  
### Kits to add:
  - None! I'm done with kits :)

### Commands:
  - Soup: Get a list of the subcommands for the soup core.
  - SetSoupSpawn: Set the spawnpoint for the server.
  - Help: Get the help for the server.
  - Spawn: Teleports you to the spawnpoint.
  - Toggledrops: Toggles item droppings (minus soups)
  - Message: Message another player on the server.
  - Reply: Reply to a player currently messaging you.
  - ToggleMessages: Toggle off private messages.
  - ToggleGlobalChat: Toggle off global chat.
  - ToggleMessageSounds: Toggle off private message sound.
  - Refill: Open the refill box when using this command.
  
### Listeners:
  - A Kit selector GUI.
  - A Help item that is binded with the /help command.
  - Login events, that sets the players spawnpoint, clears their inventory, kit, and gives them the spawn items.
  - Custom Kit GUI  that has a bunch of kits you can choose from.
  - Custom help listener, when you right click the item it displays the help page.
  - Urgal listener, when you kill a player with this kit you get 5s of strength 1.
  - Switcher listener, when you throw a snowball at a player with switcher, you switch places with them. When you kill a player you get 5 extra snowballs.
  - Thor listener, when you right click thor's weapon you smite nearby players.
  - Snowman listener, You get infinite snowballs!
  - Fisherman listener, when you reel in your rod after fishing a player, they teleport to you.
  - Stomper listener, when you jump from a high place, you damage a nearby player.
  - Kangaroo listener, when you use the rocket, you get launched in the direction your facing. If you're shifting, you go further.
  - Ninja listener, when you sneak (off cooldown), if another player has hit you, you teleport behind them.
  - Item Drop listener, prevents item drops if /tdp is not enabled.
  - Death item drop listener, removes all item drops on death, except for player's soups.
  - Viper listener, when attacking a player, 1 out of every 10 hits they get Poison II for 3 seconds.
  
### Runnables:
  - Added potion runnable that replaces all soups with potions if using the potion kit.
  - Added a spawn runnable that does a countdown for the /spawn command, and if you move it cancels.
  - Added a runnable for the kangaroo ability cooldown.
  - Added a runnable for the ninja ability cooldown.
  - Added a runnable for the refill countdown.
  - Added a runnable for the thor ability cooldown.
  
The original InvadedLands soup server core was created by MegaPVP, this is simply a replica.
