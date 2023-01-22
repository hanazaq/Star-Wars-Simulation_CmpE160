# Star-Wars-Simulation_CmpE160
Object Oriented Principles
# About project 
The main objective is to analyze the requests
provided in input and carry out the necessary actions. It is also possible to earn bonus score if you can find
an optimized way of simulating Assault event. Even though the theme is Star-Wars, please do not assume
anything related to the original Star Wars to be true unless the project clearly states it (ie. Normally there can
be at most 2 Sith at the same time in Star-Wars Universe but in this project there can be many.)
In this Star Wars setup, there are two organizations in conflict. Simply, the good side is called Republic and
the evil side is called Separatists. Besides that, we have mainly three entities (Sector, Warship and Crewman)
and their respective subclasses. <br/><br/>
A sector is an area of space affiliated with one of the two organizations. Each sector can be represented as a
horizontal line so warship locations can be described by giving a sector and an x-coordinate (since there is only
one coordinate we will just say coordinate instead of x-coordinate). <br/><br/>
Crewman is an abstract class that covers all personnel types which includes Generals and Officers. Generals
are candidates of warship commanders and they can use Force (a special ability to do fancy stuff like moving
objects). General is also an abstract class and has two subclasses as Jedi and Sith. A warship can only be
commanded either by a Jedi (a republic warship) or a Sith (a separatist warship). Officers are crewmen that
have an intrinsic in one of the 5 areas (TACTICAL,PILOTING,GUNNERY,ENGINEERING,COMMAND) and
an intrinsic level between 1 and 10, which indicates how good they are at their expertise. <br/><br/>
In the Star Wars universe, the ability of using force of a force user is proportional to user’s midichlorian
level. Since darkside is a pathway to many abilities a Sith can produce more force power then a Jedi with equal
amount of midichlorian. While officers have intrinsic level, Generals have experience. So a General have, <br/>
• Experience - (in common)<br/>
• Midichlorian - (in common)<br/>
• Sanity - (only for Jedi)<br/>
• Intelligence - (only for Jedi)<br/>
• Persuasion - (only for Sith)<br/>
in addition to common Crewman properties.
In this project, Sith will try to convince Jedi to join the dark side. Each Sith has a property of persuasion
that indicates his ability to convince Jedi and Jedi has a property called intelligence to resist the persuasion
tricks of Sith. In addition, Jedi also has a sanity value (initially 100), if a Jedi lost all of his sanity he will join
the darkside.<br/><br/>
Officers have following properties<br/>
• Intrinsic<br/>
• Intrinsic Level<br/>
Warship is an abstract class that covers all 4 different warships in this project. There is RepublicCruiser,
a warship used by Republic forces and SeparatistDestroyer, a warship used by Separatist forces. SeparatistDestroyer has two more subclass derived from it called SeparatistFrigate and SeparatistBattleship. So
there is only one type of Republic warship and three types of Separatist warships. 
