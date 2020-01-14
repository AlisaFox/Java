# Buildings

The assignment pdf describes the project pretty well.

## What:
A tertiary tree representing separate buildings, organized by age, represented by `Building.java`.
We build it up from the `OneBuilding.java` objects, which have attributes name, yearOfConstruction, height, yearForRepair, costForRepair. 
`Essai.java` and `Tester.java` are both testers. 

## What is happening?

We have functions to build up the tree: `addBuilding (OneBuilding b), addBuildings (Building b), removeBuilding (OneBuilding b)`.
Then there are functions to show properties like
- oldest building
- highest (height being a property of OneBuilding)
- highestFromYear (highest within a given year of construction)
- numberFromYears (number of buildings built in a given time range)
- costPlanning (cost to repair buildings for the next n years) 

## To do
 I should add proper documentation in the actual file before each function. Sure I have comments, but without the pdf you would not get what the function is doing exactly.



