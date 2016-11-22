outside = open('/home/projekt/homeStruction/textSpeech/outsideData', 'r')
inside = open('/home/projekt/homeStruction/textSpeech/insideData', 'r')
merged = open('/home/projekt/homeStruction/textSpeech/mergedData', 'w')

merged.seek(0)
merged.truncate()

merged.write("Hi, Balint,\nLet me brief you on the weather conditions,\n")
c = outside.read(1)
firstRow = 0
while c:
    if c == ':':
        merged.write(",")
    elif c == '\n':
        merged.write(",\n")
        firstRow = 1
    elif (c == 'C') and (firstRow == 0):
        merged.write("degrees Celsius")
    else:
        merged.write(c)
    c = outside.read(1)

for i in range(0, 2):
    string = inside.read()
    merged.write(string)

inside.close()
outside.close()
merged.close()
