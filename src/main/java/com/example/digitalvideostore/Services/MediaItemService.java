package com.example.digitalvideostore.Services;

import com.example.digitalvideostore.Models.MediaItem;
import com.example.digitalvideostore.Models.MediaItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediaItemService
{
    @Autowired
    private MediaItemRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<MediaItem> getAllMediaItems()
    {
        return repository.findAll();
    }

    public void insertIntoMediaItem(MediaItem item)
    {
        repository.insert(item);
    }

    //gets all movies or tvshows
    public List<MediaItem> getMediaItems(boolean value)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("movie" ).is(value));
        List<MediaItem> items = mongoTemplate.find(query, MediaItem.class);
        return items;
    }

    public List<MediaItem> getFeaturedMovies(boolean value)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("featured" ).is(value)).addCriteria(Criteria.where("movie" ).is(true));
        List<MediaItem> items = mongoTemplate.find(query, MediaItem.class);
        return items;
    }

    public List<MediaItem> getFeaturedTvShows(boolean value)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("featured" ).is(value)).addCriteria(Criteria.where("movie" ).is(false));
        List<MediaItem> items = mongoTemplate.find(query, MediaItem.class);
        return items;
    }


    public List<MediaItem> getMediaItemByName(String name)
    {
        Query query = new Query();
        query.addCriteria(Criteria.where("title" ).
        regex(".*" + name + ".*", "i"));
        List<MediaItem> items = mongoTemplate.find(query, MediaItem.class);
        return items;
    }


    public Optional<MediaItem> getAMediaItem(String id) throws Exception
    {
        Optional<MediaItem> mediaItem = repository.findById(id);

        if (!mediaItem.isPresent())
        {
            throw new Exception (" Movie with " + id + " is not found ");
        }
        return mediaItem;
    }

    public void deleteAMediaItem( String id) throws Exception
    {
        Optional<MediaItem> mediaItem = repository.findById(id);
        if (!mediaItem.isPresent())
        {
            throw new Exception (" Movie with " + id + " is not found ");
        }
        else
        {
            repository.deleteById(id);
        }

    }

    public MediaItem editMediaItem(String id, MediaItem newMediaItem)
    {
        // get the resource based on the id
        Optional<MediaItem> mediaItem = repository.findById(id);

        // validation code to validate the id
        mediaItem.get().setTitle(newMediaItem.getTitle());
        mediaItem.get().setDescription(newMediaItem.getDescription());
        mediaItem.get().setFeatured(newMediaItem.isFeatured());
        mediaItem.get().setMovie(newMediaItem.isMovie());
        mediaItem.get().setLargePoster(newMediaItem.getLargePoster());
        mediaItem.get().setSmallPoster(newMediaItem.getSmallPoster());
        mediaItem.get().setPrice(newMediaItem.getPrice());
        mediaItem.get().setPurchasePrice(newMediaItem.getPurchasePrice());
        mediaItem.get().setRentPrice(newMediaItem.getRentPrice());

        MediaItem updateMediaItem = repository.save(mediaItem.get());

        return updateMediaItem;
    }

}
