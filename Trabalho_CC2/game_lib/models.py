# Create your models here.
from django.db import models
from django.db import models

# Create your models here.
class Games(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=255, unique=True)
    publisher = models.CharField(max_length=255)

class Releases(models.Model):
    id = models.IntegerField(primary_key=True)
    game = models.ForeignKey(Games, on_delete=models.CASCADE, to_field="id")
    platform = models.ForeignKey('Platforms', on_delete=models.CASCADE, to_field = "id")
    releasedate = models.DateTimeField('date published')
    version = models.IntegerField(default = 0)

class Platforms(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=255, unique=True)