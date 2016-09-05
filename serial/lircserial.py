import serial
import lirc
from time import sleep

#Initialize infrared connection
lirc.init("myprogram")
#Initialize serial communication
#ser = serial.Serial('/dev/ttyACM0', 9600)
#sleep(1)

def portIsUsable():
	try:
		ser = serial.Serial('/dev/ttyACM0', 9600)
		return True
	except:
		return False

#Function to return datatype
def data(port):
        #print "in data"
	#ser = serial.Serial('/dev/ttyACM0', 9600)
	sleep(1)

        ser.write(port)
        sleep(3)
        mystring = ser.readline()
        print mystring
        words = mystring.split()
	#ser.close()
        return words

print "> "
code = lirc.nextcode()
while code != ["exit"]:
	while not(portIsUsable):
		print "Waiting"
	ser = serial.Serial('/dev/ttyACM0', 9600)
	print code
	if code == ["one"]:
		#Turn on lamp
		ser = serial.Serial('/dev/ttyACM0', 9600)
		#sleep(1)
		ser.write('11')
	if code == ["two"]:
		#Turn off lamp
		ser = serial.Serial('/dev/ttyACM0', 9600)
		#sleep(1)
		ser.write('10')
	if code == ["three"]:
		#Read lamp status
		words = data('12')
		while words[0] != 'lamp':
			words = data('12')
		lampstatus = int(words[1])
		print lampstatus
	if code == ["seven"]:
		#Read temperature
		words = data('20')
		while words[0] != 'temp':
			words = data('20')
		temperature = float(words[1])
		print temperature
	if code == ["eight"]:
		#Read light
		words = data('30')
		while words[0] != 'light':
			words = data('30')
		light = int(words[1])
		print light
	if code == ["door"]:
		#Read door status
		words = data('40')
		while words[0] != 'door':
			words = data('40')
		doorstatus = int(words[1])
		print doorstatus
	if code == ["window"]:
		#Read window status
		words = data('50')
		while words[0] != 'window':
			words = data('50')
		windowstatus = int(words[1])
		print windowstatus
	ser.close()
	print "> "
	code = lirc.nextcode()

#Deinitialize infrared
lirc.deinit()
#Close serial
#ser.close()
	
