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
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
from django.conf.urls import url, include
from django.contrib import admin
from django.contrib.auth import views as authviews
from rest_framework import routers
from project import views
from rest_framework.authtoken import views as tokenview


router = routers.DefaultRouter()
router.register(r'temp', views.TemperatureViewSet)
router.register(r'lamp', views.LampViewSet, 'lampname')
router.register(r'light', views.LightViewSet)
router.register(r'door', views.DoorViewSet)
router.register(r'window', views.WindowViewSet)

urlpatterns = [
    url('^api/lamp/(?P<startswith>.+)/$', views.LampStartList.as_view()),
    url(r'^login/$', authviews.login, name='login'),
    url(r'^logout/$', authviews.logout, name='logout'),
    url(r'', include('project.urls')),
    url(r'^admin/', admin.site.urls),
    url(r'^api/', include(router.urls)),
    url(r'^api-auth/', include('rest_framework.urls', namespace='rest_framework')),
    url(r'^api-token-auth/', tokenview.obtain_auth_token),
]
