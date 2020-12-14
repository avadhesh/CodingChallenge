# Credit Suisse Coding Challenge

Coding challenge which demonstrates a simple console version of a drawing program.

The program has below features as of now:
 1. Create a new canvas
 2. Start drawing on the canvas by issuing various commands
 3. Quit

## Pre-requisites
[JDK 15](https://www.oracle.com/java/technologies/javase-jdk15-downloads.html) 

## Installation

Use maven [mvn] to install the application.

From the project directory execute the below command
```bash
./mvnw clean install
```

## Run

Use maven [mvn] to run the application.

From the project directory execute the below command
```bash
./mvnw spring-boot:run 
```
| Name | Command | Description | 
|:---:| :---: | :---: | 
| Create canvas | C w h  | Should create a new canvas of width w and height h |
| Draw Line | L x1 y1 x2 y2 |  Should create a new line from (x1,y1) to (x2,y2). Currently only horizontal or vertical lines are supported. Horizontal and vertical lines will be drawn using the 'x' character. |
| Draw Rectangle | R x1 y1 x2 y2 | Should create a new rectangle, whose upper left corner is (x1,y1) and lower right corner is (x2,y2). Horizontal and vertical lines will be drawn using the 'x' character. | 
| Bucket fill | B x y c |  Should fill the entire area connected to (x,y) with "colour" c. The behavior of this is the same as that of the "bucket fill" tool in paint programs. |
| Quit | Q | Should quit the program.|

## Sample steps

```bash
enter command: C 5 5
-------
|     |
|     |
|     |
|     |
|     |
-------

enter command: L 1 2 3 2
-------
|     |
|xxx  |
|     |
|     |
|     |
-------

enter command: R 1 2 4 4
-------
|     |
|xxxx |
|x  x |
|xxxx |
|     |
-------

enter command: B 3 3 o
-------
|     |
|xxxx |
|xoox |
|xxxx |
|     |
-------

enter command: B 5 5 b
-------
|bbbbb|
|xxxxb|
|xooxb|
|xxxxb|
|bbbbb|
-------

enter command: B 5 5 p
-------
|ppppp|
|xxxxp|
|xooxp|
|xxxxp|
|ppppp|
-------

```

### Error Handling
Error messages will be displayed in case the input for any command deviates from the requirements

1. Negative height for canvas
```bash
enter command: C -5 5
2020-12-14 00:57:46.963 ERROR 60785 --- [           main] c.c.a.c.driver.CanvasPaintDriver         : Invalid canvas height/width
```

2. Coordinates outside the canvas boundary
```bash
enter command: L 1 2 3 2
2020-12-14 00:59:44.604 ERROR 61178 --- [           main] c.c.a.c.driver.CanvasPaintDriver         : Coordinates (1 , 2) invalid for canvas ,Coordinates (3 , 2) invalid for canvas 
```

3. Bucket fill attempt using reserved colors ('|', '-', 'x')
```bash
enter command: B 1 1 |
2020-12-14 01:02:04.597 ERROR 61178 --- [           main] c.c.a.c.driver.CanvasPaintDriver         : Color | is not permitted
```

4. Number of arguments provided to commands invalid
```bash
enter command: L 1 2 3
2020-12-14 01:03:49.022 ERROR 61178 --- [           main] c.c.a.c.driver.CanvasPaintDriver         : Invalid number of arguments, expected 4
```

## Test

Execute the unit test cases using command:
```bash
./mvnw test 
```

If all the unit test cases are successful, then the above command should display below results: 
```bash
[INFO] Results:
[INFO] 
[INFO] Tests run: 33, Failures: 0, Errors: 0, Skipped: 0
```

## Assumptions

* Create Canvas

      i.   Two arguments expected one each for height and width, in order
      ii.  Both the arguments followed by the command character, should be non-zero positive numbers
      iii. Vertical boundaries will be filled by '|' character and horizontal boundaries with '-'
      
* Draw Line
      
      i.   Four arguments are expected, a pair for two coordinates
      ii.  Both the coordinates are expected to be non-zero positive numbers and within the the canvas boundary
      iii. Only horizontal or vertical lines are supported and if the coordinates provided does not correspond to either of them then the command will be ignored
      iv.  The lines will be drawn using 'x' character
      v.   Line will be drawn from the first coordinate to the second if all the above holds true
      
* Draw Rectangle

      i.   Four arguments are expected, a pair for two coordinates
      ii.  Both the coordinates are expected to be non-zero positive numbers and within the the canvas boundary
      iii. Rectangle will be drawn from top left coordinate to bottom right coordinate. Any of the two coordinates can act as either of them
      iv.  Rectangles will be drawn using the 'x' character
      
* Bucket fill
      
      i.   Three arguments are expected, first 2 for the start coordinate and third for the color
      ii.  Both the arguments are expected to be non-zero positive numbers and within the the canvas boundary
      iii. '|', '-', 'x' are reserved colors and should not be used
      iv.  Buckets can be overriden using a different valid color
      v.   When the start coordinate provided is already occupied by a reserved color, then the command will be ignored

* Quit
    
      i.   No arguements are expected, however if any is provided the program will still quit
      