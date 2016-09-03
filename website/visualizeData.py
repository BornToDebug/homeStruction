import numpy as np
from matplotlib import pyplot as plt
from matplotlib import dates
import django
import os
os.environ.setdefault("DJANGO_SETTINGS_MODULE", "homeStruction.settings")
django.setup()
from project.models import Temperature
from django.utils import timezone

# Get temperature data from the last 24 hours
tempArray = Temperature.objects.order_by('-time_recorded').\
    filter(time_recorded__gte=timezone.now() - timezone.timedelta(days=1))

print tempArray

dateTemp = []
valueTemp = []
for item in tempArray:
    dateTemp.append(item.time_recorded)
    valueTemp.append(item.value)

print dateTemp, valueTemp

formating = dates.DateFormatter('%H:%M')

print dates.date2num(dateTemp)

cmap = (0, 0, 0)

plt.plot(dateTemp, valueTemp, color=cmap)
plt.ylabel('temperature C'), plt.xlabel('time')
plt.xticks(rotation='vertical')
plt.subplots_adjust(bottom=.3)
plt.savefig('image.png', bbox_inches='tight', transparent='true')
plt.show()
