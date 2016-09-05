import lirc

sockid = lirc.init("myprogram")

while True:
	code = lirc.nextcode()
	if code == ["one"]:
		print "1"
	else:
		print code
lirc.deinit()
