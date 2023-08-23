package com.abhijith.note_data_base.exceptions

class ResourceNotExists(
    id:Any
): Throwable("Resource with id $id dose not exists")

object EmptyResource:Throwable("Resource dose not contains any data")