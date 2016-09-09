import os
import pty
import sys
import time

pidpar = os.getpid()
print "parent: %d" % pidpar
pid, fd = os.fork()
#print "after forkpty :d" % pid
if pid != 0:
	print "in if: %d" % pid
	time.sleep(5)
	os.write(fd, 's')
	time.sleep(2)
	os.write(fd, 's')
	time.sleep(5)
	os.write(fd, 'q')
else:
	print "else: %d" % pid
	os.spawnl(os.P_WAIT,
		'/usr/bin/mpg123',
		'/usr/bin/mpg123', '-C', sys.argv[1])		
