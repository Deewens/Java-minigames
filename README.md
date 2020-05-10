# Java minigames

> **English version** (version française disponible plus bas)  

> **Screenshots available below.**

## Tic Tac Toe:

A simple Tic Tac Toe online game working with Java RMI. You can play with your friends remotely.  
Work only with two players.

### Known issues 
* The game can't be restarted (next party will be bugged if you try it)
    - **Workaround:** close server and client then restart both.
* Sometime, when a player clicks to add a symbol and if it's his turn, it can happen that the symbol dosen't appear (but symbol appear for other player). However, server will have taken into account the symbol, it's only a displaying bug.
    - **Workaround:** You can add a symbol at your next turn, the last and currently symbol will appear.

### Author
Deewens (Adrien Dudon)

## Matches game

https://fr.wikipedia.org/wiki/Jeu_de_Marienbad

The matches game (or Marienbad Game) is a minigame a mini-game where the goal is not to take the last remaining match.  
You can only play with a bot.

### Known issues
* The bot always win, even if you had to win (because bot play even if game is ended, I need to fix that)

### Author
Sofiane Aiche

---

>**Version française**  

>**Screenshots disponible juste en dessous**

## Morpion

Un morpion en ligne vous permettant de jouer à distance.
Fonctionne seulement avec deux joueurs.

### Problèmes connus
* Vous ne pouvez pas recommencer une partie (la prochaine partie sera buggué si vous essayer)
    - **Solution :** fermer le serveur et le client puis redémarrez les.
* Parfois, lorsqu'un joueur clique pour ajouter un symbole et si c'est son tour, il peut arriver que le symbole n'apparaisse pas (mais que le symbole apparait pour l'autre joueur). Cependant, le serveur aura pris en compte le symbole, ce n'est qu'un bug d'affichage.
    - **Solution :** Vous pouvez ajouter un symbole à votre prochain tour, le dernier symbole et le symbole que vous venez de jouer apparaîtront.

### Auteur
Deewens (Adrien Dudon)

## Jeu des allumettes

Page wikipédia : https://fr.wikipedia.org/wiki/Jeu_de_Marienbad

Le jeu de allumettes (ou Jeu de Marienbad) est un mini-jeu où le but est de ne pas prendre la dernière allumette.
Vous ne pouvez jouer qu'avec le bot.

### Problèmes connus
* Le bot gagne toujours, même si vous auriez dû gagner (parce que le bot joue même si le jeu est terminé, je dois corriger ça)

### Auteur
Sofiane Aiche

# Screenshots

**Serveur prêt - Server ready**

![Server](./img/server-server_ready.png)

**Accueil - Home**

![Home](./img/client-home.PNG)

## Tic Tac Toe - Morpion

![WaitPlayer](./img/client-tictactoe_waiting_for_player.png)
![EmptyBoard](./img/client-tictactoe_empty_board.png)
![Playing](./img/client-tictactoe_playing.png)
![Can'tPlay](./img/client-tictactoe_can't_play.png)
![CaseIsn'tEmpty](./img/client-tictactoe_case_isn't_empty.png)
![EndGame](./img/client-tictactoe_endgame.png)

## Jeu des allumettes - Matches Game

![MatchesFirst](./img/client-matches_first.png)
![MatchesSecond](./img/client-matches_second.png)
![MatchesEnd](./img/client-matches_end.png)