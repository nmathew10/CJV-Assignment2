package com.example.digitalvideostore.Models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaItemRepository extends MongoRepository<MediaItem, String>
{

}
