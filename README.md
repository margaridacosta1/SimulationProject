# SimulationProject - DeliveryGame

A Simulation for university's subject Decision Supporting Methods (https://www.dcc.fc.up.pt/~jpp/mad/trabalho-3.pdf). The goal is to simulate the costs of delivery service, where people can pickup packages at the locker in the neighborhood or the packages are delivered to home.
A person who goes to collect in the locker can become courier, that is, he can deliver a package that would be delivered at home, thus receiving compensation. 
Packages that are not picked up by the courier will be delivered the next day by a professional.

## Compile and Run 
Compile
```bash
javac -d classes -cp ./jars/*.jar src/*.java
```
Run 
```bash 
java -cp ./classes:./jars/commons-math3-3.6.1.jar Simulation
```

## Files
--| src |-- <br />
Accumulated.java - file that contains information for accumulated values of all days; <br />
CsvWtriter.java - file that contains create and write ".csv"; <br />
Day.java - file that contains day's informations;<br />
Interval.java - file that contains a function to calculate a trust interval;<br />
Simulation.java - file that contains simulation functions.<br />
--| jars |-- <br />
commons-math3-3.6.1.jar - jar file of JAVA external library. <br />
--| classes |-- <br />
Class files of src's files compiled.<br />

## Input 
The program asks as input the number of observations (integer), the number of days (integer) and the degree of confidence (double, with comma).
Then you will be asked if you want to use the default values ​​in the pdf or choose the compensation and the probability.


