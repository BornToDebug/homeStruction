from sys import argv
import serial

script, todo = argv

ser = serial.Serial('/dev/ttyACM0', 9600)

if todo == '1lampon':
	ser.write('7')
if todo == '1lampoff':
	ser.write('8')
if todo == '2lampon':
	ser.write('1')
if todo == '2lampoff':
	ser.write('2')
if todo == '3lampon':
	ser.write('6')
if todo == '3lampoff':
	ser.write('9')
if todo == 'opendoor':
	ser.write('5')
if todo == 'closedoor':
	ser.write('4')
ser.close()
