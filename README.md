# Student Information System - Client

This is a project that I undertook to learn more about Java and MySQL.  I specifically wanted to learn more about JavaFX, 
networking using Sockets, Serialization, JDBC, and multi-threading.  It consists of two parts: a client and a server.  This is just the client 
code.  It uses maven as the build tool.

## Description

This is the client code.  It uses JavaFX as for the gui components.  When it starts, the user is prompted to connect to the server.
From there, the user is asked to indicate if they are a current student or if they are a new student.  If the user selects that they are a new
student then they enter all of their information into a form which is then transmitted to the server to be stored in a student database.
If the user selects that they are a returning student, they enter their student ID #, which was generated when they registered as a new student.  
A request is then made to the server to pull that student from the database and the client displays their information on the screen.  At this point,
the user can then view/edit their information, add a class, or drop a class that they had previously added.

## Getting Started

### Dependencies

* JavaFX 11.0.2
* View the POM.xml file included with the code.

### Installing

* It can be unzipped into any folder on your computer.
* In order for the client to function properly, you first need to have an instance of the server running.
	* If an instance of the server is not running, then it will throw an IOException and exit.


### Executing program

* Build the project with your IDE of choice.


## Authors

Contributors names and contact info

ex. Tim Jonker
ex. tmjonker1@outlook.com

## Version History

