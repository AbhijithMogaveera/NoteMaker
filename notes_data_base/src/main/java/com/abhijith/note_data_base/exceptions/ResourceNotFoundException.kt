package com.abhijith.note_data_base.exceptions

class ResourceNotFoundException(
    id: Any
) : Throwable("Resource with id $id does not exist")

object EmptyResourceException : Throwable("Resource does not contain any data")
