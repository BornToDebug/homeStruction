from django.shortcuts import render
from django.views import generic
from .models import Temperature


class TemperatureView(generic.ListView):
    template_name = 'project/index.html'
    context_object_name = 'query_results'

    def get_queryset(self):
        """
        Should return the latest 10 temperature records
        """
        return Temperature.objects.order_by('-time_recorded')[:10]
