import serial
from time import sleep
ser = serial.Serial('/dev/ttyACM0', 9600)
sleep(1)
ser.write('30')
sleep(4)
light = int(ser.readline())
print light
ser.close()
