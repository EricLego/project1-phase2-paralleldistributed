Project 1 Phase 2
CS4504 parallel distributed computing

How to run in powershell
Compile:
cd "c:\Users\Admin\project2\rmi-election"
javac -d out src/main/java/edu/ksu/election/*.java


Start the server (creates/uses the registry on 1099):
cd "c:\Users\Admin\project2\rmi-election\out"
java edu.ksu.election.ElectionServer 127.0.0.1

Start a client (in a new terminal):
cd "c:\Users\Admin\project2\rmi-election\out"
java edu.ksu.election.ElectionClient 127.0.0.1
