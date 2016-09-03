# Initial setup required
import django
import os
os.environ.setdefault("DJANGO_SETTINGS_MODULE", "homeStruction.settings")
django.setup()
# Importing required models and modules
from project.models import Temperature
from django.utils import timezone
import numpy as np

randValue = np.random.randint(10, 35, 100)
randMin = np.random.randint(0, 3600, 100)

# Create 100 temperature objects with random time(last 24 hour) and value
now = timezone.now()
for i in range(100):
    Temperature.objects.create(value=randValue[i], time_recorded=now -
                               timezone.timedelta(minutes=randMin[i]))

