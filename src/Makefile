FLAGS = -g
JC = javac
JVM = java
RM = rm -f
MAIN = Rogue
FOLDER = game

.SUFFIXES: .java .class

build:
	$(JC) $(FOLDER)/$(MAIN).java

%.xml:
	$(JVM) $(FOLDER).$(MAIN) $@

clean:
	$(RM) *.class
	#find src -type f -name "*.class" -delete
