"""homeStruction URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.10/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconfttern
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url, include
from django.contrib import admin
from django.contrib.auth import views as authviews
from rest_framework import routers
from project import views
from rest_framework.authtoken import views as tokenview
from project import commandview


router = routers.DefaultRouter()
router.register(r'temp', views.TemperatureViewSet)
router.register(r'lamp', views.LampViewSet, 'lampname')
router.register(r'light', views.LightViewSet)
router.register(r'door', views.DoorViewSet)
router.register(r'window', views.WindowViewSet)
router.register(r'humidity', views.HumidityViewSet)

urlpatterns = [
    url('^api/lamp/(?P<startswith>.+)/$', views.LampStartList.as_view()),
    url(r'^login/$', authviews.login, name='login'),
    url(r'^logout/$', authviews.logout, name='logout'),
    url(r'', include('project.urls')),
    url(r'^admin/', admin.site.urls),
    url(r'^api/', include(router.urls)),
    url(r'^api-auth/', include('rest_framework.urls', namespace='rest_framework')),
    url(r'^api-token-auth/', tokenview.obtain_auth_token),
    url(r'^command/$', commandview.controlbasic, name='control'),
    url(r'^androidcommand/$', commandview.androidcontrol, name='androidcontrol'),
    url(r'^stream/start/$', commandview.startstream, name='startstream'),
    url(r'^stream/stop/$',  commandview.stopstream, name='stopstream'),
    url(r'^image/$', commandview.imageView, name='imageview'),
    url(r'^androidimage/$', commandview.androidImageView, name='androidimageview'),
    url(r'^mp3/toggle/$', commandview.togglemp3, name='togglemp3'),
    url(r'^setalarm/$', commandview.setAlarmView, name='setalarm'),
    url(r'^androidsetalarm/$', commandview.androidSetAlarmView, name='androidsetalarm'),
    url(r'^test/$', views.TestViewSet.as_view(), name='tes'),
]
