import django
import os
from django.utils import timezone

# Initial setup required
os.environ.setdefault("DJANGO_SETTINGS_MODULE", "homeStruction.settings")
django.setup()

from project.models import Temperature, Light, Humidity

import matplotlib
matplotlib.use("Agg")
from matplotlib.dates import DateFormatter
from matplotlib import pyplot as plt

def generateImage(Model, ytext):
    modelArray = Model.objects.order_by('-time_recorded').\
        filter(time_recorded__gte=timezone.now() - timezone.timedelta(days=1))

    dateArray = []
    valueArray = []
    for item in modelArray:
        dateArray.append(item.time_recorded)
        valueArray.append(item.value)

    formating = DateFormatter('%H:%M')

    cmap = (0, 0, 0)

    plt.plot(dateArray, valueArray, color=cmap, linewidth=2.0)
    plt.ylabel(ytext), plt.xlabel('time')
    plt.xticks(rotation='vertical')
    plt.subplots_adjust(bottom=.3)
    plt.savefig(ytext + '.png', bbox_inches='tight', transparent='true')
    plt.close()

generateImage(Temperature, 'Temperature')
generateImage(Humidity, 'Humidity')
generateImage(Light, 'Light')


