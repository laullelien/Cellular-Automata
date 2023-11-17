# Example de makefile pour compiler le squelette de code distribué
# Vous pouvez compléter ce makefile, mais étant donnée la taille du projet, 
# il est FORTEMENT recommandé d'utiliser un IDE!

# Organisation:
#  1) Les sources (*.java) se trouvent dans le répertoire src
#     Les classes d'un package toto sont dans src/toto
#     Les classes du package par defaut sont dans src
#
#  2) Les bytecodes (*.class) sont générés dans le répertoire bin
#     La hiérarchie des sources (par package) est conservée.
#
#  3) Une librairie gui.jar est distribuée pour l'interface grapique. 
#     Elle se trouve dans le sous-répertoire lib.
#
# Compilation:
#  Options de javac:
#   -d : répertoire dans lequel sont générés les .class compilés
#   -sourcepath : répertoire dans lequel sont cherchés les .java
#   -classpath : répertoire dans lequel sont cherchées les classes compilées (.class et .jar)

all: runTestInvader runTestBalls

compileTestInvader:
	javac -d bin -classpath lib/gui.jar src/TestInvader.java

runTestInvader: compileTestInvader
	java -classpath bin:lib/gui.jar TestInvader

compileTestBallsSimulator:
	javac -d bin -classpath lib/gui.jar src/BallsSimulator.java src/Balls.java src/BallsEvent.java src/EventSimulable.java src/Vector.java src/EventManager.java src/Event.java

runTestBallsSimulator: compileTestBallsSimulator
	java -classpath bin:lib/gui.jar TestBallsSimulator

compileTestGOLSimulator:
	javac -d bin -classpath lib/gui.jar src/TestGameOfLifeSimulator.java src/GameOfLifeSimulator.java src/GameOfLifeEvent.java src/EventSimulable.java src/GridSimulable.java src/EventManager.java src/Event.java

runStandardCellsGOL: compileTestGOLSimulator
	java -classpath bin:lib/gui.jar Test1_StandardCells

runStaticCellsGOL: compileTestGOLSimulator
	java -classpath bin:lib/gui.jar Test2_StaticCells

runTwoStatesCellsGOL: compileTestGOLSimulator
	java -classpath bin:lib/gui.jar Test3_TwoStatesCells

runCellsCanConvergeQuicklyGOL: compileTestGOLSimulator
	java -classpath bin:lib/gui.jar Test4_CellsCanConvergeQuickly

runCellsCondemnedToDieGOL: compileTestGOLSimulator
	java -classpath bin:lib/gui.jar Test5_CellsCondemnedToDie

compileTestImmSimulator:
	javac -d bin -classpath lib/gui.jar src/TestImmigrationSimulator.java src/ImmigrationSimulator.java src/ImmNextEvent.java src/EventSimulable.java src/GridSimulable.java src/EventManager.java src/Event.java src/Immigration.java

runVerySmallTwoColorsImm: compileTestImmSimulator
	java -classpath bin:lib/gui.jar VerySmallTwoColors

runSmallTwoColorsImm: compileTestImmSimulator
	java -classpath bin:lib/gui.jar SmallTwoColors

runSmallFourColours: compileTestImmSimulator
	java -classpath bin:lib/gui.jar SmallFourColors

runMediumThreeColors: compileTestImmSimulator
	java -classpath bin:lib/gui.jar MediumThreeColors

runBigThreeColors: compileTestImmSimulator
	java -classpath bin:lib/gui.jar BigThreeColors

compileTestSchelingSimulator:
	javac -d bin -classpath lib/gui.jar src/TestSchelling.java src/SchellingSimulator.java src/SchellingEvent.java src/EventSimulable.java src/GridSimulable.java src/EventManager.java src/Event.java

runSchellingSmall2Colors: compileTestSchelingSimulator
	java -classpath bin:lib/gui.jar TestShellingSmall2Colors

runSchellingSmall4Colors: compileTestSchelingSimulator
	java -classpath bin:lib/gui.jar TestShellingSmall4Colors

runSchellingMedium3Colors: compileTestSchelingSimulator
	java -classpath bin:lib/gui.jar TestShellingMedium3Colors

runSchellingMedium6Colors: compileTestSchelingSimulator
	java -classpath bin:lib/gui.jar TestShellingMedium6Colors

runSchellingBig3Colors: compileTestSchelingSimulator
	java -classpath bin:lib/gui.jar TestShellingBig3Colors

runSchellingBig6Colors: compileTestSchelingSimulator
	java -classpath bin:lib/gui.jar TestShellingBig6Colors

compileTestBoidsSimulator:
	javac -d bin -classpath lib/gui.jar src/TestBoidsSimulator.java src/Boid.java src/Boids.java src/BoidCharacteristics.java src/BoidsEvent.java src/BoidsSimulator.java src/DVector.java src/Triangle.java src/EventSimulable.java src/GridSimulable.java src/EventManager.java src/Event.java

runTestBoids: compileTestBoidsSimulator
	java -classpath bin:lib/gui.jar TestBoidsSimulator

clean:
	rm -rf bin/

