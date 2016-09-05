import serial
from time import sleep
ser = serial.Serial('/dev/ttyACM0', 9600)
sleep(1)
ser.write('20')
sleep(3)
temperature = ser.readline()
print float(temperature)
ser.close()
