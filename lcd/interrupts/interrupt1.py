import RPi.GPIO as GPIO
GPIO.setmode(GPIO.BCM)

GPIO.setup(23, GPIO.IN, pull_up_down=GPIO.PUD_UP)

print "Make sure you have a button connected so that when pressed"
print "it will connect GPIO port 23 (pin 16) to GND (pin 6)\n"
raw_input("Press Enter when ready\n>")

print "Waiting for falling edge on port 23"

try:
	GPIO.wait_for_edge(23, GPIO.FALLING)
	print "\nFalling edge detected. Now your program can continue with"
	print "whatever was waiting for a button press."
except KeyboardInterrupt:
	GPIO.cleanup()
GPIO.cleanup()
