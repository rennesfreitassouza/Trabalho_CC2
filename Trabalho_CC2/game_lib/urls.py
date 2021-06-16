from django.urls import include, path
from rest_framework import routers
from .views import *

#urls.py criar endpoints customizados...
#proximo: ANTL>léxico>sintático.


router = routers.DefaultRouter()
router.register(r'games', GamesViewSet)
router.register(r'releases', ReleasesViewSet)
router.register(r'Platforms', ReleasesViewSet)

urlpatterns = [
    path('', include(router.urls)),
    path('api-auth/', include('rest_framework.urls', namespace='env'))
]