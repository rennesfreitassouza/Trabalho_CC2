from django.urls import include, path
from rest_framework import routers
from . import views



#urls.py criar endpoints customizados...
#proximo: ANTL>léxico>sintático.


router = routers.DefaultRouter()
router.register(r'games', views.GamesViewSet)
router.register(r'releases', views.ReleasesViewSet)
router.register(r'Platforms', views.ReleasesViewSet)

urlpatterns = [
    path('', include(router.urls)),
    path('api-auth/', include('rest_framework.urls', namespace='env'))
]