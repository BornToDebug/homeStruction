import serial
from time import sleep
ser = serial.Serial('/dev/ttyACM0', 9600)
sleep(1)
ser.write('40')
sleep(4)
sound = int(ser.readline())
print sound
ser.close()
