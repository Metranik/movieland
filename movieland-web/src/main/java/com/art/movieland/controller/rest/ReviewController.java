package com.art.movieland.controller.rest;

import com.art.movieland.controller.annotation.ProtectedBy;
import com.art.movieland.entity.Review;
import com.art.movieland.entity.UserRole;
import com.art.movieland.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/review")
public class ReviewController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ProtectedBy(value = {UserRole.ADMIN, UserRole.USER})
    public void addReview(@RequestHeader(name = "uuid") String uuid,
                          @RequestBody Review addReview) {

        logger.debug("RequestBody Review: {}", addReview);
        reviewService.addMovieReview(
                addReview.getMovieId(),
                reviewService.getUserByUuid(uuid),
                addReview.getText());
    }
}