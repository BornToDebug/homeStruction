import lirc
import serial

#Establish infrared connection
sockid = lirc.init("myprogram")

print "> "
code = lirc.nextcode()

while code != ["exit"]:
	ser = serial.Serial('/dev/ttyACM0', 9600)
	if code == ["one"]:
		ser.write('1')
	if code == ["two"]:
		ser.write('2')
	if code == ["four"]:
		ser.write('4')
	if code == ["five"]:
		ser.write('5')
	if code == ["six"]:
		ser.write('6')
	if code == ["seven"]:
		ser.write('7')
	if code == ["eight"]:
		ser.write('8')
	if code == ["nine"]:
		ser.write('9')
	ser.close()
	print "> "
	code = lirc.nextcode()
lirc.deinit()
