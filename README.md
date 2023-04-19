# Maze Generator/Solver
 
 <div align="center">
    <img src="https://github.com/Igniiis/Java_Maze_Generator_Swing/blob/main/image/logo.png" width="350"/>
 </div>
 
During my Degree, I had this project of doing both a generator and a solver of Maze. We were free to choose wich method to use. <br>
All the project was on **Java**, the generation and resolution was in *java vanilla* and for the UI we had to use *Java Swing*. <br>
*note : if you are french (or crazy) you can read our pdf wich is in the repo.*

Big s/o to paul, my teammate on this project : 

[<img src='https://github.com/youngboi02.png' style="border-radius: 58%;" alt='Paul Prout' height='58'>](https://github.com/youngboi02)  


## Generation

### Maze generation processus

We hesitate between 2 maze generation algorithms:
* <a href="https://en.wikipedia.org/wiki/Depth-first_search">Depth-First Search</a>
* <a href="https://en.wikipedia.org/wiki/Kruskal%27s_algorithm">Kruskal's algorithm (Randomized)</a>


Finally, I chose the Depth First Search method which seemed a bit eaisier at the time of choice.

### Depth First Search method
Here are the different steps of the algorithm:

1. Randomly select a node (or a cell) N.
2. Push cell N into a Q queue.
3. Mark cell N as visited.
4. Randomly select an adjacent cell A from cell N that has not been visited. If all neighbors of N have been visited:
- Continue removing items from queue Q until a cell is encountered with at least one unvisited neighbor - assign this cell to N and go to step 4.
- If no cell exists: stop.
5. Break the wall between N and A.
6. Assign the value A to N.
7. Go to step 2.

## Resolution

### Maze solving
For this part, we instantly went for the method of Depth-first search (again T.T) because we were forced to by the university. It was pretty the same logic as its counter part in Generation and so this part was made *very* quickly.

## UI
Now that everything was done, we only had to do the UI part, in where we were forced to use Java Swing. We did something pretty classic : 
<div align="center">
   <img src="https://github.com/Igniiis/Java_Maze_Generator_Swing/blob/main/savedImageMaze.png" width="650" />
</div>


## Conclusion
It is always a great project to do a maze generator and/or solver. I was happy to do it and I am proud of the result :rose:. Thank you to everyone who read all of this, hope it was interested :smile:. <br>
See ya
