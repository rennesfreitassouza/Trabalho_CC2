from django.db import models
from django.db import models

# Create your models here.
class Games(models.Model):
    _id = models.ForeignKey('Releases', on_delete=models.CASCADE)
    name = models.CharField(max_length=255)
    publisher = models.CharField(max_length=255)

class Releases(models.Model):
    game = models.ForeignKey(Games, on_delete=models.CASCADE)
    platform = models.ForeignKey('Platforms', null=True, on_delete=models.SET_NULL)
    releasedate = models.DateTimeField('date published')
    version = models.IntegerField(default = 0)

class Platforms(models.Model):
    _id = models.ForeignKey(Releases, on_delete=models.CASCADE)
    name = models.CharField(max_length=255)
