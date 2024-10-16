package com.example.MoviesList.Movie;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewService service;

    @PostMapping()
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {

        return new ResponseEntity<Review>(service.createReview(payload.get("reviewBody"), payload.get("imdbId")), HttpStatus.OK);
    }
}

 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody Map<String, String> payload) {
        String reviewBody = payload.get("reviewBody");
        String imdbId = payload.get("imdbId");

        // Validate payload
        if (reviewBody == null || reviewBody.isEmpty() || imdbId == null || imdbId.isEmpty()) {
            return ResponseEntity.badRequest().body("Review body and IMDb ID are required.");
        }

        // Create review
        Review createdReview = service.createReview(reviewBody, imdbId);
        if (createdReview == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create review.");
        }

        return ResponseEntity.ok(createdReview);
    }
}
