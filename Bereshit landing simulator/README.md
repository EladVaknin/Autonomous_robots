# Bereshit Landing Simulator
The main goal of this project is to land the "Bereshit " spacecraft in peace.
The "Bereshit " spacecraft Take off on February 22, 2019.
On April 19, the spacecraft began maneuvering a landing kilometer from a height of 30 kilometers to the moon.
This maneuver is designed to slow the spacecraft from a speed of 1700 meters per second to almost zero.
During the landing one of the IMU controllers crashed and during its boot the main computer of the spacecraft also crashed, the connection with the spacecraft was lost for several long minutes and thus the spacecraft crashed.

## Our Solution : 
In our solution we try to use the PID algorithm and code thats given us by the Profesor.
This algorithm is designed to be backed up when one of the controllers crashes and does not give information.
In this algorithm we rely on the data that has been up to now and analyze according to which data I can rely on in a crash.

In addition, we have created a GUI which updates the relevant fields and engines working according to the spacecraft data during landing.


## Getting Started : 
When all files located inside eclipse or any other explorer we have the Main class that contain the main process.

- The object **myMoon** represents the moon according to its true data (size, gravity ..).

- The **Thrusther** object represents the main engine and its features - combustion power, fuel consumption.

- The **secondery Thruster** object represents the eight additional engines (see attached image) with their features (heir to the parent Thruster department).

- The object **spaceship** represents the spacecraft in "Bereshit "- its circumference, weight, speed, acceleration and more.










![image](https://user-images.githubusercontent.com/74238558/168485164-e277d45e-c2fc-44d2-a7ed-9f59a1d84ca5.png)


# Participants : 
- Gidon Avziz.
 - Moriya Elgrabli.
- Elad Vaknin.
