# proxxapp

<img width="592" alt="Screenshot 2023-03-16 at 14 24 17" src="https://user-images.githubusercontent.com/40858461/225616425-3d5685f3-26c1-4ded-b2ea-36bf4b2313bc.png">


This application is a howework task for the https://proxx.app.

- Choosing NxN board:

    In order to determine a data structure to hold the game board in memory we have to understand the balance between
easement of API that the structure gives us, and the volume of resources it utilizes.
    Of course, we can use 2D arrays, lists of lists, key-value entries, and other similar structures to represent the
game board, however, the best API for that provides the java.util.Map. It has many methods just out of the box
and many of them are useful for us.
    Hash-based maps are usually heavy in memory and left a footprint, and we should be careful when using this structure
for many items. However hash-based implementations are closed to have O(1) complexity for put/get operations,
and having the maximum size of the game board is limited (40X40), the HashMap is closed to have O(1) complexity.
In this case, the memory footprint can be sacrificed in favor of a good API.

- Initialization of the game:
    
    The creation of game board, populating cells, generating black holes, counting the number of black holes is considered
a heavy operations, so it makes sense to do this once at the beginning of game.
When user starts the game and click on some cell firstly, the game engine performs initialization of the game board,
and all the other next clicks will only change the property of visibility of cells, and other properties are
calculated already.

- Location of black holes:
    
    Each cell has it's position, and I decided to use enum structure to determine whether a cell is a BLACK_HOLE,
or not (just a STANDARD type cell).

- Counts of adjacent black holes:
    
    Counts of adjacent black holes is a heavy operation, and I do this calculation once at the beginning of the game.

- Whether a cell is opened:
    
    Each cell has OPENED/HIDDEN status, and this status changes from hidden to opened as the game progresses.
I decided to use enum structure to keep the property whether it is hidden of opened.

- Populate black holes:
    
    Location of black holes is generated randomly, and the first cell user clicks on (at the beginning of game)
should not be a black hole, plus there should not be a black hole in any adjacent cells.
Because of game initialization happens on the first user's click, I guarantee that the click is not happen on black hole.

- The condition of loose, win, and continue the game:
   
   Loose when user clicked on a hidden cell that is black hole. Win when user opened all the standard (non-blackhole) cells.
In both cases (win/loose) the user is getting notified be the notification (see LifecycleNotificationService). User
continues to play when number where are hidden cells to be opened later.
