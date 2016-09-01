from django.conf.urls import url

from . import views

app_name = 'project'

urlpatterns = [
    url(r'^temperature$', views.TemperatureView.as_view(), name='temperature')
]
