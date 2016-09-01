from django.test import TestCase
from django.utils import timezone
from .models import Temperature
from django.shortcuts import reverse


def create_temperature(temperature, days):
    """
    Inserts an entry into the database for testing with hours difference
    """
    time = timezone.now() + timezone.timedelta(days=days)
    return Temperature.objects.create(value=temperature, time_recorded=time)


class TemperatureViewTest(TestCase):
    def test_temperature_view_no_records(self):
        """
        If there are no records, 'No records found' should be displayed
        """
        response = self.client.get(reverse('project:temperature'))
        self.assertContains(response, 'No records found')

    def test_temperature_view_display_record(self):
        """
        Test with a record of temperature.
        """
        create_temperature(temperature=27.0, days=1)
        response = self.client.get(reverse('project:temperature'))
        print response.context['query_results']

        self.assertQuerysetEqual(response.context['query_results'], ['<Temperature: 27.0>'])
