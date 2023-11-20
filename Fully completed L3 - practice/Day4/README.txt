Level 3 - Task 3

Timings - 4:30 to 6:30 PM



1.Food Ordering Application
Create a food order application as link Swiggy and use the following concepts

Abstraction

Encapsulation

Methods

File System

Regex

Class Object

Collections

MultiThreading

 

2.Atom Ray Game

A matrix game was given with 5 rules. We were asked to implement each of the rules separately.

 

  R3 | -   -   - |

  R2 | -   -   - |

  R1 | -   -   - |

       C1  C2  C3  

Each of the 9 cells can either be empty or filled with an atom. R3, R2, R1 are the rays that originate from the left. C1, C2, C3 are the rays that originate from the bottom of the box.

 

Input : Position of the atoms and the rays that gets originated from the outside of the box.

 

  Eg.) 3

       3 1

       2 2

       1 3

       3

       R3 C1 C3

 

Output  : Print the box.

Rule 1:

A ray that has an atom in its path should print ‘H’ (Hit) If it does not have any atoms in its path, the ray should pass to the other side.

 

       C1      C3

  R3 | -   -   - | R3

  H  | -   X   - |

  R1 | -   -   - | R1

       C1  H   C3

Rule 2 & 3:

A ray that has an atom in its diagonal adjacent position should refract.

 

  H  | -   -   - |

  H  | X   -   - |

  R  | -   X   - |

       R   H   R  

 

Input rays: R1, R2, C3

 

  H  | -   X   - |

  R2 | -   -   - | C3

     | -   -   - |

       R2      C3  

Rule 4:

A ray that has atoms in both of the diagonal adjacent positions should reflect back.

 

 

Input ray: C2

   | -   -   - |

   | X   -   X |

   | -   -   - |

         R   

 

Input ray: R2

     | -   X   - |

  R  | -   -   - |

     | -   X   - |

Rule 5:

The deflection of rays should happen in the order of the input rays.

 

Input Rays: R3, R2, C1, C3

   H | -   X   - |

  R2 | -   -   - | C3

     | -   -   - |

       R2      C3

The final task was to implement these rules for dynamic matrix size.

 

 

Input : no of rows, no of columns

  Eg.) 4 4 (row & column)

       2 (No of atoms)

       4 4 (Position of atom)

       2 2 (Position of atom)

       2 (No of rays)

       R4 C2 (Ray number)

 

  H  | -   -   -   X |

     | -   -   -   - |

     | -   X   -   - |

     | -   -   -   - |

           H  

The final task was very confusing and it had to handle all the cases. There are chances for a ray to end at the starting position if the number of rows and columns are more than 5.



3.Traffic Signal

Create a traffic signal application using Multithreading in Java.