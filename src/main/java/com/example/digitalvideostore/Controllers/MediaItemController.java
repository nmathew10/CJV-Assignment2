package com.example.digitalvideostore.Controllers;

import com.example.digitalvideostore.CustomizedResponse;
import com.example.digitalvideostore.Models.MediaItem;
import com.example.digitalvideostore.Services.MediaItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin(origins = {"http://localhost:3000", "https://digital-video-store-n.herokuapp.com"})
@RestController
public class MediaItemController
{
    @Autowired
    private MediaItemService service;

    @GetMapping("/movies")
    public ResponseEntity getMovies()
    {
        var customizedResponse = new CustomizedResponse(" A list of movies" , service.getMediaItems(true));

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @GetMapping("/tvshows")
    public ResponseEntity getTvShows()
    {
        var customizedResponse = new CustomizedResponse(" A list of tv shows" , service.getMediaItems(false));

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

//  /movies/featured?isFeatured=true
    @GetMapping("/movies/featured")
    public ResponseEntity getFeaturedMovies(@RequestParam(value = "isFeatured") Boolean val)
    {
        var customizedResponse = new CustomizedResponse(" A list of featured movies " , service.getFeaturedMovies(val));

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

//  /tvshows/featured?isFeatured=true
    @GetMapping("/tvshows/featured")
    public ResponseEntity getFeaturedTvShows(@RequestParam(value = "isFeatured") Boolean val)
    {

        var customizedResponse = new CustomizedResponse(" A list of featured tv shows" , service.getFeaturedTvShows(val));

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }


    @PostMapping(value = "/media", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addMediaItem(@RequestBody MediaItem mediaItem)
    {
        service.insertIntoMediaItem(mediaItem);
        return new ResponseEntity(mediaItem, HttpStatus.OK);
    }


    @GetMapping("/mediaItem/{name}")
    public ResponseEntity getMediaItemsByName(@PathVariable("name") String name)
    {

        CustomizedResponse customizedResponse = null;
        try
        {
            customizedResponse = new CustomizedResponse(" Items having the word " + name , Collections.singletonList(service.getMediaItemByName(name)));
        }
        catch (Exception e)
        {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);

            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }


    @GetMapping("/media/{id}")
    public ResponseEntity getAMediaItem(@PathVariable("id") String id)
    {

        CustomizedResponse customizedResponse = null;
        try
        {
            customizedResponse = new CustomizedResponse(" Movie with id " + id , Collections.singletonList(service.getAMediaItem(id)));
        }
        catch (Exception e)
        {
            customizedResponse = new CustomizedResponse(e.getMessage(), null);

            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

    @DeleteMapping("/media/{id}")
    public ResponseEntity deleteAMediaItem(@PathVariable("id") String id) throws Exception {
        service.deleteAMediaItem(id);
        return new ResponseEntity(HttpStatus.OK);
    }


    @PutMapping(value = "/media/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity editMediaItem(@PathVariable("id") String id, @RequestBody MediaItem newItem )
    {
        var customizedResponse = new CustomizedResponse(" Movie with ID:  " + id + "was updated successfully " , Collections.singletonList(service.editMediaItem(id, newItem)));
        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

}
