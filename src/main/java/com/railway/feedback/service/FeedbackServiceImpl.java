package com.railway.feedback.service;

import com.railway.feedback.entity.Feedback;
import com.railway.feedback.repository.FeedbackRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public Feedback addFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Feedback updateFeedback(Long id, Feedback feedback) {
        Feedback existing = feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
        existing.setComments(feedback.getComments());
        existing.setRating(feedback.getRating());
        existing.setName(feedback.getName());
        existing.setEmail(feedback.getEmail());
        existing.setBookingId(feedback.getBookingId());
        return feedbackRepository.save(existing);
    }

    @Override
    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }

    @Override
    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    @Override
    public List<Feedback> getFeedbackByEmail(String email) {
        return feedbackRepository.findByEmail(email);
    }
}
