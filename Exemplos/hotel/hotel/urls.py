from django.contrib import admin
from django.urls import include, path
from rest_framework import routers

urlpatterns = [
	path('locacao/', include('locacao.urls')),
	path('admin/', admin.site.urls),
	path('api-auth/', include('rest_framework.urls', namespace='rest_framework'))
]
