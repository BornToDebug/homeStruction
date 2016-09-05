import serial
ser = serial.Serial('/dev/ttyACM0', 9600)
ser.write('10')
ser.close
