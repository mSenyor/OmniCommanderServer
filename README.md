# OmniCommanderServer
This program aims to provide a server to accept virtual key press signals from the OmniCommander android app (https://github.com/mSenyor/OmniCommander). At this point the server is set up to handle several Grandma2 on-PC shortcut keys.

The server still has several know issues:
 - It haven't been tested yet against a compatible program
 - Key combinations still need to be implemnted
 - Several user interface objects don't function (server monitor, start/stop button, and more)
 - Communication between the server and client isn't protected in any way
 - Settings inputs need to be varified before stored
 - The entire GUI needs to be reworked so the server actions aren't handle inside it
