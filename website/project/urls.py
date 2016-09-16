from django.conf.urls import url

from . import views

app_name = 'project'

urlpatterns = [
    url(r'^temperature$', views.TemperatureView.as_view(), name='temperature'),
    url(r'^start$', views.StartView.as_view(), name='start'),
    url(r'^alarm$', views.AlarmView.as_view(), name='alarm'),
    url(r'^doorwind$', views.DoorAndWindowView.as_view(), name='doorwind'),
    url(r'^mult$', views.MultimediaView.as_view(), name='mult'),
    url(r'^light$', views.LightView.as_view(), name='light'),
    url(r'^live$', views.LiveView.as_view(), name='live'),    
    url(r'^contact$', views.ContactView.as_view(), name='contact'),
]
