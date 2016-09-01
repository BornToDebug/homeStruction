# Initial setup required
import django
import os
os.environ.setdefault("DJANGO_SETTINGS_MODULE", "homeStruction.settings")
django.setup()
# Importing required models and modules
from project.models import Temperature
from django.utils import timezone

# Inserting into temperature table with current timestamp
Temperature.objects.create(value=25, time_recorded=timezone.now())

