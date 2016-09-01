# FIXME: export DJANGO_SETTINGS_MODULE=mysite.settings must be run before this script is functional)

import django

django.setup()
from project.models import Temperature
from django.utils import timezone

Temperature.objects.create(value=25, time_recorded=timezone.now())

