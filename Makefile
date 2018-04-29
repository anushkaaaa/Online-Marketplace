JCC = javac
compile:
	$(JCC) -cp "mysql-connector-java-5.1.46-bin.jar" *.java
clean:
	$(RM) *.class