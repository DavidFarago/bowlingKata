# bowlingKata
Bowling kata in shippable quality:
* outer quality: correct, robust, usable
* inner quality: maintainable, extensible, readable (especially bowling "business rules").

Master branch uses a Frame class to reify Frames in the architecture (see architecture.jpg), to ease validation and iterative scoring (used when iteratively outputting score board). 

noFrame brach avoids using Frame class, since it turns out that all corner cases in the end game are very intricate. Using an 11th and 12th Frame object might improve the situation, but using dynamic frames while you iterate over rolls really simplifies the solution (no Frame class and in Game class 10 LoC less and lower complexity).
